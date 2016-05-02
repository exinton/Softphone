package softphone_A;

import javax.sip.*;
import javax.sip.message.*;

import MediaAdapter.SdpInfo;
import MediaAdapter.SdpManager;
import MediaAdapter.TonesTool;
import MediaAdapter.VideoTool;
import MediaAdapter.VoiceTool_secure;
import Security.SecureAccountManagerImpl;

import javax.sip.header.*;
import javax.sip.address.*;

import java.net.*;
import java.util.*;

import gov.nist.javax.sip.SipStackExt;
import gov.nist.javax.sip.clientauthutils.AuthenticationHelper;

public class Softphone5Listener implements SipListener{

	private SipFactory mySipFactory;
	private SipStack mySipStack;
	private ListeningPoint myListeningPoint;
	private SipProvider mySipProvider;
	private MessageFactory myMessageFactory;
	private HeaderFactory myHeaderFactory;
	private AddressFactory myAddressFactory;
	private Properties myProperties;
	private Softphone5GUI myGUI;
	private ContactHeader myContactHeader;
	private ViaHeader myViaHeader;
	private RouteHeader myRouteHeader;
	private Address fromAddress;
	private Dialog myDialog;
	private ClientTransaction myClientTransaction;
	private ServerTransaction myServerTransaction;
	private int status;
	private String myIP;
	private String toTag;
	private SdpManager mySdpManager;
	private VoiceTool_secure myVoiceTool;
	private VideoTool myVideoTool;
	private TonesTool myAlertTool;
	private TonesTool myRingTool;
	private SdpInfo answerInfo;
	private SdpInfo offerInfo;
	
	private SecureAccountManagerImpl mySecureAccountManager;
	private int myPort;
	private String myServer;
	private int myAudioPort;
	private int myVideoPort;
	private String myAudioCodec;
	private int myVideoCodec;
	private SoftPhoneConfiguration myconfig;
	
	static final int YES=0;
	static final int NO=1;
	  static final int SEND_MESSAGE=2;
	
	  static final int UNREGISTERED=-3;
	static final int AUTHENTICATING=-2;
	
	static final int REGISTERING=-1;
	
	static final int IDLE=0;
	static final int WAIT_PROV=1;
	static final int WAIT_FINAL=2;
	static final int ESTABLISHED=4;
	static final int RINGING=5;
	static final int WAIT_ACK=6;
	static  String transport;


class MyTimerTask extends TimerTask {
        Softphone5Listener myListener;
        public MyTimerTask (Softphone5Listener myListener){
                this.myListener=myListener;
              }
        public void run() {
          try{
            Request myBye = myListener.myDialog.createRequest("BYE");
            myBye.addHeader(myListener.myContactHeader);
            myListener.myClientTransaction =
                myListener.mySipProvider.getNewClientTransaction(myBye);
            myListener.myDialog.sendRequest(myListener.myClientTransaction);
          }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

  //public Zphone1Listener(int port,String name,String ID,Zphone1GUI GUI)  {
  public Softphone5Listener(SoftPhoneConfiguration conf,Softphone5GUI GUI, String sipserver)  {
    try{
      myconfig=conf;
      transport=myconfig.transport;
      myServer=sipserver;
      myGUI = GUI;
      myIP = InetAddress.getLocalHost().getHostAddress();
      myPort = myconfig.myPort;
      myAudioPort=myconfig.myAudioPort;
      myAudioCodec=myconfig.myAudioCodec;

      mySdpManager=new SdpManager();
      myVoiceTool=new VoiceTool_secure();
      myVideoTool=new VideoTool();
      answerInfo=new SdpInfo();
      offerInfo=new SdpInfo();

      myAlertTool=new TonesTool();
      myRingTool=new TonesTool();
      URL location = Softphone5Listener.class.getProtectionDomain().getCodeSource().getLocation();
      System.out.println(location.getFile());
      
      myAlertTool.prepareTone("file:///"+location.getFile()+"ring/alert.wav");
      myRingTool.prepareTone("file:///"+location.getFile()+"ring/ring.wav");
      //myAlertTool.prepareTone("file://c:\\alert.wav");
      //myRingTool.prepareTone("file://c:\\ring.wav");
      mySecureAccountManager = new SecureAccountManagerImpl();
      
      mySecureAccountManager.setCredential(myconfig.userID, myconfig.password);
      //mySecureAccountManager.setCredential(conf.userID, conf.password);


      mySipFactory = SipFactory.getInstance();
      mySipFactory.setPathName("gov.nist");
      myProperties = new Properties();
      myProperties.setProperty("javax.sip.STACK_NAME", "myStack");
      mySipStack = mySipFactory.createSipStack(myProperties);
      myMessageFactory = mySipFactory.createMessageFactory();
      myHeaderFactory = mySipFactory.createHeaderFactory();
      myAddressFactory = mySipFactory.createAddressFactory();
      myListeningPoint = mySipStack.createListeningPoint(myIP, myPort, transport);  //modify here to tls
      mySipProvider = mySipStack.createSipProvider(myListeningPoint);
      mySipProvider.addSipListener(this);

      //modify 
      // Create the contact name address.
      SipURI contactURI = myAddressFactory.createSipURI(transport,myIP);	//modify here for tls
  
      contactURI.setPort(myPort);
      contactURI.setTransportParam(transport);
      if(transport.equalsIgnoreCase("tls")){//An application that wishes to create a 'sips' URI should call the SipURI.setSecure(boolean) with an argument of 'true' on the returned SipURI. 
      contactURI.setSecure( true );
      }
      Address contactAddress = myAddressFactory.createAddress(contactURI);	//tls
      //Address contactAddress = myAddressFactory.createAddress("sip:"+myIP+":"+myPort);
      myContactHeader = myHeaderFactory.createContactHeader(contactAddress);

      myViaHeader = myHeaderFactory.createViaHeader(myIP, myPort,transport, null);   //modify here

      Address routeAddress = myAddressFactory.createAddress("sip:"+myServer+";lr");
      myRouteHeader= myHeaderFactory.createRouteHeader(routeAddress);

      fromAddress=myAddressFactory.createAddress(myconfig.name+ " <sip:"+myconfig.userID+">");

      myGUI.jLabel5.setText("Initialized at IP "+ myIP+", port "+myPort);


      Address registrarAddress=myAddressFactory.createAddress("sip:"+myServer);
      Address registerToAddress = fromAddress;
      Address registerFromAddress=fromAddress;

      ToHeader myToHeader = myHeaderFactory.createToHeader(registerToAddress, null);
      FromHeader myFromHeader = myHeaderFactory.createFromHeader(registerFromAddress, "647554");

      ArrayList myViaHeaders = new ArrayList();
      myViaHeaders.add(myViaHeader);

      MaxForwardsHeader myMaxForwardsHeader = myHeaderFactory.createMaxForwardsHeader(70);
      CSeqHeader myCSeqHeader = myHeaderFactory.createCSeqHeader(1L,"REGISTER");
      ExpiresHeader myExpiresHeader=myHeaderFactory.createExpiresHeader(60000);
      CallIdHeader myCallIDHeader = mySipProvider.getNewCallId();
      //javax.sip.address.URI myRequestURI = registrarAddress.getURI();
      SipURI myRequestURI = (SipURI) registrarAddress.getURI();
      Request myRegisterRequest = myMessageFactory.createRequest(myRequestURI,"REGISTER",
          myCallIDHeader, myCSeqHeader, myFromHeader, myToHeader,myViaHeaders, myMaxForwardsHeader);
      myRegisterRequest.addHeader(myContactHeader);
      myRegisterRequest.addHeader(myExpiresHeader);

      myClientTransaction = mySipProvider.getNewClientTransaction(myRegisterRequest);
      String bid=myClientTransaction.getBranchId();
      myClientTransaction.sendRequest();

      myGUI.display(">>> " + myRegisterRequest.toString());
      status=REGISTERING;
      /*myGUI.showStatus("Status: REGISTERING");
      myGUI.showStatus("Status: REGISTERING");*/ //move to processResponse when Response.trying is received

    }catch (Exception e) {
     e.printStackTrace();
    }
  }

  public void setOff(){
     
	 try{

     mySipProvider.removeSipListener(this);
     mySipProvider.removeListeningPoint(myListeningPoint);
     mySipStack.deleteListeningPoint(myListeningPoint);
     mySipStack.deleteSipProvider(mySipProvider);
     myListeningPoint=null;
     mySipProvider=null;
     mySipStack.stop();
     mySipStack=null;
     myAlertTool=null;
     myRingTool=null;
     myGUI.showStatus("");
    
     }
     catch(Exception e){
    	 System.out.println(e.toString());
    	 
         }
  
  }

  public int convertSDPfmt(String str){
	  
	  Map<String,Integer> fmtMap =new  HashMap<String,Integer>();
	  fmtMap.put("GSM", 3);
	  fmtMap.put("DVI", 5);
	  fmtMap.put("ULAW", 0);
	  return fmtMap.get(str);
	  
  }
  public String reverseSDPfmt(int fmt){
	  
	  Map<Integer,String> fmtMap =new  HashMap<Integer,String>();
	  fmtMap.put(3,"GSM");
	  fmtMap.put(5,"DVI");
	  fmtMap.put(0,"ULAW");
	  return fmtMap.get(fmt);
	  
  }

public void processRequest(RequestEvent requestReceivedEvent) {
  Request myRequest=requestReceivedEvent.getRequest();
  String method=myRequest.getMethod();
  myGUI.display("<<< "+myRequest.toString());
  if (!method.equals("CANCEL")) {
  myServerTransaction=requestReceivedEvent.getServerTransaction();
  }

  try{

  switch (status) {

    case IDLE:
      if (method.equals("INVITE")) {
        if (myServerTransaction == null) {
                myServerTransaction = mySipProvider.getNewServerTransaction(myRequest);
        }

        myAlertTool.playTone();
        //RECIBE SDP OFFER. GUARDA EL SDP (el SDP se tiene que poner a null al comenzar y cuando se envia 200OK)

        byte[] cont=(byte[]) myRequest.getContent();
        offerInfo=mySdpManager.getSdp(cont);

        answerInfo.IpAddress=myIP;
        answerInfo.aport=myAudioPort;
        answerInfo.aformat=offerInfo.aformat;

    

        //**************************************************************



        Response myResponse=myMessageFactory.createResponse(180,myRequest);
        myResponse.addHeader(myContactHeader);
        ToHeader myToHeader = (ToHeader) myResponse.getHeader("To");
        myToHeader.setTag("454326");
        myServerTransaction.sendResponse(myResponse);
        myDialog=myServerTransaction.getDialog();
        myGUI.display(">>> "+myResponse.toString());
        status=RINGING;
        myGUI.showStatus("Status: RINGING");
      }
      else if (method.equals("MESSAGE")) {
       if (myServerTransaction == null) {
               myServerTransaction = mySipProvider.getNewServerTransaction(myRequest);
       }
       Response myResponse=myMessageFactory.createResponse(200,myRequest);
       myResponse.addHeader(myContactHeader);
       ToHeader myToHeader = (ToHeader) myResponse.getHeader("To");


       FromHeader myFromHeader = (FromHeader) myRequest.getHeader("From");
       javax.sip.address.Address messageFromAddress=myFromHeader.getAddress();
       SipURI fromURI=(SipURI) messageFromAddress.getURI();
       String name=fromURI.getUser();

       byte[] myByteContent=myRequest.getRawContent();
       String myContent=new String(myByteContent);

       myToHeader.setTag("454326");
       myServerTransaction.sendResponse(myResponse);

       myGUI.displayMessage(name+ ":  "+myContent);

       myGUI.display(">>> "+myResponse.toString());
     }

     break;
    case ESTABLISHED:
      if (method.equals("BYE")) {
        Response myResponse=myMessageFactory.createResponse(200,myRequest);
        myResponse.addHeader(myContactHeader);
        myServerTransaction.sendResponse(myResponse);
        myGUI.display(">>> "+myResponse.toString());

        myVoiceTool.stopMedia();

        if (answerInfo.vport>0) {
          myVideoTool.stopMedia();
        }

        status=IDLE;
        myGUI.showStatus("Status: IDLE");
      }
      else if (method.equals("MESSAGE")) {
       Response myResponse=myMessageFactory.createResponse(200,myRequest);
       myResponse.addHeader(myContactHeader);

       FromHeader myFromHeader = (FromHeader) myRequest.getHeader("From");
       javax.sip.address.Address messageFromAddress=myFromHeader.getAddress();
       SipURI fromURI=(SipURI) messageFromAddress.getURI();
       String name=fromURI.getUser();

       byte[] myByteContent=myRequest.getRawContent();
       String myContent=new String(myByteContent);

       myServerTransaction.sendResponse(myResponse);
       myGUI.displayMessage(name+ ":  "+myContent);
       myGUI.display(">>> "+myResponse.toString());

     }

    break;

    case RINGING:
      if (method.equals("CANCEL")) {
        ServerTransaction myCancelServerTransaction=requestReceivedEvent.getServerTransaction();
        Request originalRequest=myServerTransaction.getRequest();
        Response myResponse=myMessageFactory.createResponse(487,originalRequest);
        myServerTransaction.sendResponse(myResponse);
        Response myCancelResponse=myMessageFactory.createResponse(200,myRequest);
        myCancelServerTransaction.sendResponse(myCancelResponse);

        myAlertTool.stopTone();

        myGUI.display(">>> "+myResponse.toString());
        myGUI.display(">>> "+myCancelResponse.toString());

        status=IDLE;
        myGUI.showStatus("Status: IDLE");
      }
      break;

      case WAIT_ACK:
        if (method.equals("ACK")) {
        status=ESTABLISHED;
        myGUI.showStatus("Status: ESTABLISHED");
      }



  }

  }catch (Exception e) {
    e.printStackTrace();
  }
}


public void processResponse(ResponseEvent responseReceivedEvent) {
  try{
  Response myResponse=responseReceivedEvent.getResponse();
  myGUI.display("<<< "+myResponse.toString());
  ClientTransaction thisClientTransaction=responseReceivedEvent.getClientTransaction();
  if (!thisClientTransaction.equals(myClientTransaction)) {return;}
  int myStatusCode=myResponse.getStatusCode();
  CSeqHeader originalCSeq=(CSeqHeader) myClientTransaction.getRequest().getHeader(CSeqHeader.NAME);
  long numseq=originalCSeq.getSeqNumber();

switch(status){

  case WAIT_PROV:
    if (myStatusCode<200) { //180 ring
      status=WAIT_FINAL;
      myDialog=thisClientTransaction.getDialog();
      myRingTool.playTone();
      myGUI.showStatus("Status: ALERTING");
    }
    else if (myStatusCode<300) {
      myDialog=thisClientTransaction.getDialog();
      Request myAck = myDialog.createAck(numseq);
      myAck.addHeader(myContactHeader);
      myDialog.sendAck(myAck);
      myGUI.display(">>> "+myAck.toString());
      myRingTool.stopTone();
      status=ESTABLISHED;
      myGUI.showStatus("Status: ESTABLISHED");

      //LAST STEP IN SDP OFFER/ANSWER

      byte[] cont=(byte[]) myResponse.getContent();
      answerInfo=mySdpManager.getSdp(cont);
////////////////////////////////////
      String voiceAformat=reverseSDPfmt(offerInfo.aformat);
      
      myVoiceTool.startMedia(offerInfo.IpAddress,offerInfo.aport,offerInfo.aport,voiceAformat,myconfig.zrtpPath);





      //************************************************

    }
    else {

      status=IDLE;
      Request myAck = myDialog.createAck(numseq);
      myAck.addHeader(myContactHeader);
      myDialog.sendAck(myAck);
      myRingTool.stopTone();
      myGUI.display(">>> "+myAck.toString());
      myGUI.showStatus("Status: IDLE");

    }
    break;

  case WAIT_FINAL:
    if (myStatusCode<200) {
      status=WAIT_FINAL;
      myDialog=thisClientTransaction.getDialog();
      myRingTool.playTone();
      myGUI.showStatus("Status: ALERTING");
    }
    else if (myStatusCode<300) { //get 200
      status=ESTABLISHED;
      myDialog=thisClientTransaction.getDialog();
      Request myAck = myDialog.createAck(numseq);
      myAck.addHeader(myContactHeader);
      myDialog.sendAck(myAck);		//issue
      myGUI.display(">>> "+myAck.toString());
      myRingTool.stopTone();
      myGUI.showStatus("Status: ESTABLISHED");

      //LAST STEP IN SDP OFFER/ANSWER

      byte[] cont=(byte[]) myResponse.getContent();
      answerInfo=mySdpManager.getSdp(cont);
////////////////////////
      String voiceAformat=reverseSDPfmt(answerInfo.aformat);
      myVoiceTool.startMedia(answerInfo.IpAddress,answerInfo.aport,offerInfo.aport,voiceAformat,myconfig.zrtpPath);
      System.out.println("EL LLAMANTE ESCUCHA EN"+offerInfo.aport);


        if (answerInfo.vport>0) {

          myVideoTool.startMedia(answerInfo.IpAddress,answerInfo.vport,offerInfo.vport,answerInfo.vformat);


        }

      //************************************************


    }
    else {
      //en este caso el ACK lo envia la aplicaci�n.

      myRingTool.stopTone();
      status=IDLE;
      myGUI.showStatus("Status: IDLE");
    }
    break;

  case REGISTERING:

  if (myStatusCode==200) {
    status=IDLE;
    myGUI.showStatus("Status: IDLE");
    System.out.println(status);
  }
  
  else if(myStatusCode == Response.TRYING)//100
  {
	  System.out.println("Client: trying");
	  myGUI.showStatus("Status: REGISTERING");
      myGUI.showStatus("Status: REGISTERING");
      myGUI.displayMessage(myResponse.toString());
  }
  
  else if(myStatusCode == Response.UNAUTHORIZED
		  || myStatusCode == Response.PROXY_AUTHENTICATION_REQUIRED)//401 or 407
  {
	  System.out.println("CLient:UNAUTHORIZED RESPONSE");
	  status=AUTHENTICATING;
	  
	  ProxyAuthenticateHeader authHeader = 
			  (ProxyAuthenticateHeader) myResponse.getHeader(ProxyAuthenticateHeader.NAME);
      System.out.println("authentication header: " + authHeader);
      String realm = authHeader.getRealm();
      String username = fromAddress.getDisplayName();//conf.name//authHeader.getName();
	  AuthenticationHelper authenticationHelper = 
              ((SipStackExt) mySipStack).getSecureAuthenticationHelper(mySecureAccountManager, 
            		  myHeaderFactory);
	  myClientTransaction = authenticationHelper.handleChallenge(myResponse, thisClientTransaction, mySipProvider, 5);
      
	  myClientTransaction.sendRequest();
	  myGUI.displayMessage(myResponse.toString());
	  
    
  }
  else {
    status=UNREGISTERED;
    myGUI.showStatus("Status: UNREGISTERED");
  }
  break;
  case AUTHENTICATING:
	  System.out.println("Authenticating: code = " + myStatusCode);
	  if (myStatusCode==200) {
		    status=IDLE;
		    myGUI.showStatus("Status: IDLE");
		    System.out.println(status);
		  }
	  else if(myStatusCode == 100)
	  {
		  break;
	  }
	  else{
		  status=UNREGISTERED;
		  myGUI.showStatus("Registration Failed!");
		  this.setOff();
	  }
	  break;

}
  }catch(Exception excep){
    excep.printStackTrace();
  }
}

public void processTimeout(TimeoutEvent timeoutEvent) {
}

public void processTransactionTerminated(TransactionTerminatedEvent tevent) {

}

  public void processDialogTerminated(DialogTerminatedEvent tevent) {

  }

  public void processIOException(IOExceptionEvent tevent) {

   }

public void userInput(int type, String destination, String message){
     try {
       switch (status) {
         case IDLE:
           if (type == YES) {//make sip phone call
             Address toAddress = myAddressFactory.createAddress(destination);
             ToHeader myToHeader = myHeaderFactory.createToHeader(toAddress, null);

             //Double r=new Double(Math.random()*89000+10000);
             //String fromTag=r.toString();

             FromHeader myFromHeader = myHeaderFactory.createFromHeader(
                 fromAddress, "56438");

             myViaHeader = myHeaderFactory.createViaHeader(myIP, myPort,transport, null);
             ArrayList myViaHeaders = new ArrayList();
             myViaHeaders.add(myViaHeader);
             MaxForwardsHeader myMaxForwardsHeader = myHeaderFactory.
                 createMaxForwardsHeader(70);
             CSeqHeader myCSeqHeader = myHeaderFactory.createCSeqHeader(1L,
                 "INVITE");
             CallIdHeader myCallIDHeader = mySipProvider.getNewCallId();
             javax.sip.address.URI myRequestURI = toAddress.getURI();
             Request myRequest = myMessageFactory.createRequest(myRequestURI,
                 "INVITE",
                 myCallIDHeader, myCSeqHeader, myFromHeader, myToHeader,
                 myViaHeaders, myMaxForwardsHeader);

             myRequest.addFirst(myRouteHeader);
             myRequest.addHeader(myContactHeader);

             //HERE GOES SDP AND MEDIA TOOL (SEND OFFER)
   
             

             offerInfo=new SdpInfo();
             offerInfo.IpAddress=myIP;
             offerInfo.aport=myAudioPort;
             offerInfo.aformat=convertSDPfmt(myAudioCodec);
             offerInfo.vport=myVideoPort;
             offerInfo.vformat=myVideoCodec;
             //add crypto
            

             ContentTypeHeader contentTypeHeader=myHeaderFactory.createContentTypeHeader("application","sdp");
             byte[] content=mySdpManager.createSdp(offerInfo);
             myRequest.setContent(content,contentTypeHeader);
///////////////////////////
          //  myVoiceTool.startRx(offerInfo.aport);
           // if (offerInfo.vport!=-1) myVideoTool.startRx(offerInfo.vport,myGUI);

            //****************************************************

             myClientTransaction = mySipProvider.getNewClientTransaction(myRequest);
             String bid=myClientTransaction.getBranchId();

             myClientTransaction.sendRequest();
             myDialog = myClientTransaction.getDialog();
             myGUI.display(">>> " + myRequest.toString());
             status = WAIT_PROV;
             myGUI.showStatus("Status: WAIT_PROV");

           }
           else if (type==SEND_MESSAGE) {
             Address toAddress = myAddressFactory.createAddress(destination);
             ToHeader myToHeader = myHeaderFactory.createToHeader(toAddress, null);

             //Double r=new Double(Math.random()*89000+10000);
             //String fromTag=r.toString();

             FromHeader myFromHeader = myHeaderFactory.createFromHeader(
                 fromAddress, "685354");

             ViaHeader myViaHeader = myHeaderFactory.createViaHeader(myIP, myPort,transport, null);
             ArrayList myViaHeaders = new ArrayList();
             myViaHeaders.add(myViaHeader);
             MaxForwardsHeader myMaxForwardsHeader = myHeaderFactory.
                 createMaxForwardsHeader(70);
             CSeqHeader myCSeqHeader = myHeaderFactory.createCSeqHeader(1L,
                 "MESSAGE");
             CallIdHeader myCallIDHeader = mySipProvider.getNewCallId();
             javax.sip.address.URI myRequestURI = toAddress.getURI();
             Request myRequest = myMessageFactory.createRequest(myRequestURI,
                 "MESSAGE",
                 myCallIDHeader, myCSeqHeader, myFromHeader, myToHeader,
                 myViaHeaders, myMaxForwardsHeader);
             myRequest.addFirst(myRouteHeader);
             myRequest.addHeader(myContactHeader);

             ContentTypeHeader myContentTypeHeader=myHeaderFactory.createContentTypeHeader("text","plain");

             byte[] contents = message.getBytes();
             myRequest.setContent(contents,myContentTypeHeader);

             myClientTransaction = mySipProvider.getNewClientTransaction(myRequest);
             String bid=myClientTransaction.getBranchId();

             myClientTransaction.sendRequest();

             SipURI fromURI=(SipURI) fromAddress.getURI();
             String name=fromURI.getUser();

             myGUI.displayMessage(name+ ":  "+message);

             myGUI.display(">>> " + myRequest.toString());

           }
           break;


         case WAIT_FINAL:
           if (type == NO) {
             Request myCancelRequest = myClientTransaction.createCancel();
             ClientTransaction myCancelClientTransaction = mySipProvider.
                 getNewClientTransaction(myCancelRequest);
             myCancelClientTransaction.sendRequest();
             myGUI.display(">>> " + myCancelRequest.toString());
             myRingTool.stopTone();

             status = IDLE;
             myGUI.showStatus("Status: IDLE");
             break;

           }

         case ESTABLISHED:
           if (type == NO) {
             Request myBye = myDialog.createRequest("BYE");
             myBye.addHeader(myContactHeader);
             myClientTransaction= mySipProvider.getNewClientTransaction(myBye);
             myDialog.sendRequest(myClientTransaction);
             myGUI.display(">>> " + myBye.toString());

             myVoiceTool.stopMedia();

             if (answerInfo.vport>0) {
               myVideoTool.stopMedia();
             }

             status = IDLE;

             myGUI.showStatus("Status: IDLE");
           }

           else if (type==SEND_MESSAGE) {
            Request myMessage = myDialog.createRequest("MESSAGE");

            myMessage.addHeader(myContactHeader);
            ContentTypeHeader myContentTypeHeader=myHeaderFactory.createContentTypeHeader("text","plain");

            byte[] contents = message.getBytes();
            myMessage.setContent(contents,myContentTypeHeader);

            myClientTransaction= mySipProvider.getNewClientTransaction(myMessage);
            myDialog.sendRequest(myClientTransaction);

            SipURI fromURI=(SipURI) fromAddress.getURI();
            String name=fromURI.getUser();

            myGUI.displayMessage(name+ ":  "+message);
            myGUI.display(">>> " + myMessage.toString());

          }
             break;


         case RINGING:
           if (type == NO) {
             Request originalRequest = myServerTransaction.getRequest();
             Response myResponse = myMessageFactory.createResponse(486,
                 originalRequest);
             myServerTransaction.sendResponse(myResponse);
             myGUI.display(">>> " + myResponse.toString());
              myAlertTool.stopTone();

             status = IDLE;
             myGUI.showStatus("Status: IDLE");
             break;
           }
           else if (type == YES) {
             //Request re=myDialog.getFirstTransaction().getRequest();
             Request originalRequest = myServerTransaction.getRequest();
             Response myResponse = myMessageFactory.createResponse(200,
                 originalRequest);
             ToHeader myToHeader = (ToHeader) myResponse.getHeader("To");
             myToHeader.setTag("454326");
             myResponse.addHeader(myContactHeader);

            myAlertTool.stopTone();
             //SEND ANSWER SDP

             ContentTypeHeader contentTypeHeader=myHeaderFactory.createContentTypeHeader("application","sdp");
             byte[] content=mySdpManager.createSdp(answerInfo);
             myResponse.setContent(content,contentTypeHeader);
             
             String voiceAformat=reverseSDPfmt(answerInfo.aformat);
             
             myVoiceTool.startMedia(offerInfo.IpAddress,offerInfo.aport,offerInfo.aport,voiceAformat,myconfig.zrtpPath);

           

             myServerTransaction.sendResponse(myResponse);
             myDialog = myServerTransaction.getDialog();

             new Timer().schedule(new MyTimerTask(this),500000);
             myGUI.display(">>> " + myResponse.toString());
             status = WAIT_ACK;
             myGUI.showStatus("Status: WAIT_ACK");
             break;
           }
       }
     }
     catch (Exception e){
     e.printStackTrace();
   }

   }

}


