package softphone2;

import javax.sip.*;
import javax.sip.message.*;
import javax.sip.header.*;
import javax.sip.address.*;

import java.net.*;
import java.util.*;
import java.lang.*;

import softphone1.Softphone1Listener;
import splibraries.*;


public class Softphone2Listener implements SipListener{

private SipFactory mySipFactory;
private SipStack mySipStack;
private ListeningPoint myListeningPoint;
private SipProvider mySipProvider;
private MessageFactory myMessageFactory;
private HeaderFactory myHeaderFactory;
private AddressFactory myAddressFactory;
private Properties myProperties;
private Softphone2GUI myGUI;
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
private VoiceTool myVoiceTool;
private VideoTool myVideoTool;
private TonesTool myAlertTool;
private TonesTool myRingTool;
private SdpInfo answerInfo;
private SdpInfo offerInfo;

private int myPort;
private String myServer;
private int myAudioPort;
private int myVideoPort;
private int myAudioCodec;
private int myVideoCodec;

static final int YES=0;
static final int NO=1;

static final int UNREGISTERED=-2;
static final int REGISTERING=-1;

static final int IDLE=0;
static final int WAIT_PROV=1;
static final int WAIT_FINAL=2;
static final int ESTABLISHED=4;
static final int RINGING=5;
static final int WAIT_ACK=6;

class MyTimerTask extends TimerTask {
        Softphone2Listener myListener;
        public MyTimerTask (Softphone2Listener myListener){
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


  public Softphone2Listener(Configuration conf,Softphone2GUI GUI, String sipserver)  {
    try{
      myServer=sipserver;
      myGUI = GUI;
      myIP = InetAddress.getLocalHost().getHostAddress();
      myPort = conf.sipPort;
      myAudioPort=conf.audioPort;
      myVideoPort=conf.videoPort;
      myAudioCodec=conf.audioCodec;
      myVideoCodec=conf.videoCodec;

      mySdpManager=new SdpManager();
      myVoiceTool=new VoiceTool();
      myVideoTool=new VideoTool();
      answerInfo=new SdpInfo();
      offerInfo=new SdpInfo();

      myAlertTool=new TonesTool();
      myRingTool=new TonesTool();
      URL location = Softphone1Listener.class.getProtectionDomain().getCodeSource().getLocation();
      System.out.println(location.getFile());
      
      myAlertTool.prepareTone("file:///"+location.getFile()+"ring/alert.wav");
      myRingTool.prepareTone("file:///"+location.getFile()+"ring/ring.wav");
      //myAlertTool.prepareTone("file://c:\\alert.wav");
      //myRingTool.prepareTone("file://c:\\ring.wav");


      mySipFactory = SipFactory.getInstance();
      mySipFactory.setPathName("gov.nist");
      myProperties = new Properties();
      myProperties.setProperty("javax.sip.STACK_NAME", "myStack");
      mySipStack = mySipFactory.createSipStack(myProperties);
      myMessageFactory = mySipFactory.createMessageFactory();
      myHeaderFactory = mySipFactory.createHeaderFactory();
      myAddressFactory = mySipFactory.createAddressFactory();
      myListeningPoint = mySipStack.createListeningPoint(myIP, myPort, "udp");
      mySipProvider = mySipStack.createSipProvider(myListeningPoint);
      mySipProvider.addSipListener(this);

      Address contactAddress = myAddressFactory.createAddress("sip:"+myIP+":"+myPort);
      myContactHeader = myHeaderFactory.createContactHeader(contactAddress);

      myViaHeader = myHeaderFactory.createViaHeader(myIP, myPort,"udp", null);

      Address routeAddress = myAddressFactory.createAddress("sip:"+myServer+";lr");
      myRouteHeader= myHeaderFactory.createRouteHeader(routeAddress);

      fromAddress=myAddressFactory.createAddress(conf.name+ " <sip:"+conf.userID+">");

      myGUI.jLabel5.setText("At "+ myIP+", port "+myPort);


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
      myGUI.showStatus("Status: REGISTERING");
      myGUI.showStatus("Status: REGISTERING");

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
     mySipStack=null;
     myAlertTool=null;
     myRingTool=null;
     myGUI.showStatus("");
     }
     catch(Exception e){}
   }


public void updateConfiguration(Configuration conf) {
  myPort = conf.sipPort;
  myAudioPort=conf.audioPort;
  myVideoPort=conf.videoPort;
  myAudioCodec=conf.audioCodec;
  myVideoCodec=conf.videoCodec;

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

        byte[] cont=(byte[]) myRequest.getContent();
        offerInfo=mySdpManager.getSdp(cont);

        answerInfo.IpAddress=myIP;
        answerInfo.aport=myAudioPort;
        answerInfo.aformat=offerInfo.aformat;

        if (offerInfo.vport==-1) {
          answerInfo.vport=-1;
        }
        else if (myVideoPort==-1) {
          answerInfo.vport=0;
          answerInfo.vformat=offerInfo.vformat;
        }
        else {
          answerInfo.vport=myVideoPort;
          answerInfo.vformat=offerInfo.vformat;
        }

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
    if (myStatusCode<200) {
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

      byte[] cont=(byte[]) myResponse.getContent();
      answerInfo=mySdpManager.getSdp(cont);
      myVoiceTool.startMedia(answerInfo.IpAddress,answerInfo.aport,offerInfo.aport,answerInfo.aformat);

      if (answerInfo.vport>0) {

      myVideoTool.startMedia(answerInfo.IpAddress,answerInfo.vport,offerInfo.vport,answerInfo.vformat);

      }

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
    else if (myStatusCode<300) {
      status=ESTABLISHED;
      myDialog=thisClientTransaction.getDialog();
      Request myAck = myDialog.createAck(numseq);
      myAck.addHeader(myContactHeader);
      myDialog.sendAck(myAck);
      myGUI.display(">>> "+myAck.toString());
      myRingTool.stopTone();
      myGUI.showStatus("Status: ESTABLISHED");


      byte[] cont=(byte[]) myResponse.getContent();
      answerInfo=mySdpManager.getSdp(cont);
      myVoiceTool.startMedia(answerInfo.IpAddress,answerInfo.aport,offerInfo.aport,answerInfo.aformat);
      System.out.println("EL LLAMANTE ESCUCHA EN"+offerInfo.aport);

        if (answerInfo.vport>0) {

          myVideoTool.startMedia(answerInfo.IpAddress,answerInfo.vport,offerInfo.vport,answerInfo.vformat);

        }


    }
    else {

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
  else {
    status=UNREGISTERED;
    myGUI.showStatus("Status: UNREGISTERED");
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

public void userInput(int type, String destination){
     try {
       switch (status) {
         case IDLE:
           if (type == YES) {
             Address toAddress = myAddressFactory.createAddress(destination);
             ToHeader myToHeader = myHeaderFactory.createToHeader(toAddress, null);

             FromHeader myFromHeader = myHeaderFactory.createFromHeader(
                 fromAddress, "56438");

             myViaHeader = myHeaderFactory.createViaHeader(myIP, myPort,"udp", null);
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

             offerInfo=new SdpInfo();
             offerInfo.IpAddress=myIP;
             offerInfo.aport=myAudioPort;
             offerInfo.aformat=myAudioCodec;
             offerInfo.vport=myVideoPort;
             offerInfo.vformat=myVideoCodec;

             ContentTypeHeader contentTypeHeader=myHeaderFactory.createContentTypeHeader("application","sdp");
             byte[] content=mySdpManager.createSdp(offerInfo);
             myRequest.setContent(content,contentTypeHeader);


             myClientTransaction = mySipProvider.getNewClientTransaction(myRequest);
             String bid=myClientTransaction.getBranchId();

             myClientTransaction.sendRequest();
             myDialog = myClientTransaction.getDialog();
             myGUI.display(">>> " + myRequest.toString());
             status = WAIT_PROV;
             myGUI.showStatus("Status: WAIT_PROV");
             break;
           }

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
             break;
           }

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
             Request originalRequest = myServerTransaction.getRequest();
             Response myResponse = myMessageFactory.createResponse(200,
                 originalRequest);
             ToHeader myToHeader = (ToHeader) myResponse.getHeader("To");
             myToHeader.setTag("454326");
             myResponse.addHeader(myContactHeader);

            myAlertTool.stopTone();


             ContentTypeHeader contentTypeHeader=myHeaderFactory.createContentTypeHeader("application","sdp");
             byte[] content=mySdpManager.createSdp(answerInfo);
             myResponse.setContent(content,contentTypeHeader);

             myVoiceTool.startMedia(answerInfo.IpAddress,answerInfo.aport,offerInfo.aport,answerInfo.aformat);

            if (answerInfo.vport>0) {
              myVideoTool.startMedia(answerInfo.IpAddress,answerInfo.vport,offerInfo.vport,answerInfo.vformat);
            }

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


