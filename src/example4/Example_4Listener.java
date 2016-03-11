package example4;

import javax.sip.*;
import javax.sip.message.*;
import javax.sip.header.*;
import javax.sip.address.*;
import java.net.*;
import java.util.*;


public class Example_4Listener implements SipListener{

  private SipFactory mySipFactory;
  private SipStack mySipStack;
  private ListeningPoint myListeningPoint;
  private SipProvider mySipProvider;
  private MessageFactory myMessageFactory;
  private HeaderFactory myHeaderFactory;
  private AddressFactory myAddressFactory;
  private String myIP;
  private int myPort;
  private Example_4GUI myGUI;

  private Properties myProperties;
  private ContactHeader myContactHeader;
  private ViaHeader myViaHeader;
  private Address fromAddress;
  private Dialog myDialog;
  private ClientTransaction myClientTransaction;
  private ServerTransaction myServerTransaction;

  static final int CALL=0;
  static final int ACCEPT=1;

  public Example_4Listener(int port,Example_4GUI GUI)  {

    try{

      myPort=port;
      myGUI=GUI;
      myIP=InetAddress.getLocalHost().getHostAddress();
      myGUI.jLabel5.setText("Initialized at IP "+ myIP+", port "+myPort);

      mySipFactory=SipFactory.getInstance();
      mySipFactory.setPathName("gov.nist");

      myMessageFactory=mySipFactory.createMessageFactory();
      myHeaderFactory=mySipFactory.createHeaderFactory();
      myAddressFactory=mySipFactory.createAddressFactory();

      myProperties=new Properties();
      myProperties.setProperty("javax.sip.STACK_NAME", "myStack");
      mySipStack=mySipFactory.createSipStack(myProperties);

      myListeningPoint=mySipStack.createListeningPoint(myIP, myPort, "udp");
      mySipProvider=mySipStack.createSipProvider(myListeningPoint);
      mySipProvider.addSipListener(this);

      Address contactAddress = myAddressFactory.createAddress("sip:"+myIP+":"+myPort);
      myContactHeader = myHeaderFactory.createContactHeader(contactAddress);
      myViaHeader = myHeaderFactory.createViaHeader(myIP, myPort,"udp", null);

    }catch (Exception e) {
      System.err.println(e);
     e.printStackTrace();
    }

  }

public void processRequest(RequestEvent requestReceivedEvent) {
  Request myRequest=requestReceivedEvent.getRequest();
  String method=myRequest.getMethod();
  try{

  if (method.equals("INVITE")) {

    myServerTransaction=mySipProvider.getNewServerTransaction(myRequest);
    Response myResponse=myMessageFactory.createResponse(180,myRequest);
    ToHeader responseToHeader=(ToHeader) myResponse.getHeader("To");
    responseToHeader.setTag("454326");
    myResponse.addHeader(myContactHeader);

    myServerTransaction.sendResponse(myResponse);
    myDialog=myServerTransaction.getDialog();

    myGUI.display("<<< "+myRequest.toString());
    myGUI.display(">>> "+myResponse.toString());
    myGUI.showStatus("Dialog status: "+myDialog.getState().toString());
  }

  else if  (method.equals("ACK")) {

    myGUI.display("<<< "+myRequest.toString());
    myGUI.showStatus("Dialog status: "+myDialog.getState().toString());
  }

  }catch (Exception e){
    e.printStackTrace();
  }

}
public void processResponse(ResponseEvent responseReceivedEvent) {
  Response myResponse=responseReceivedEvent.getResponse();
  myClientTransaction=responseReceivedEvent.getClientTransaction();
  myGUI.display("<<< "+myResponse.toString());

 try{

   if (myResponse.getStatusCode()==180) {
     myDialog=myClientTransaction.getDialog();
     myGUI.showStatus("Dialog status: "+myDialog.getState().toString());
   }
   else if (myResponse.getStatusCode()==200) {
     myDialog=myClientTransaction.getDialog();
     Request ackRequest = myDialog.createAck(1);
     ackRequest.addHeader(myContactHeader);
     myDialog.sendAck(ackRequest);

     myGUI.display(">>> "+ackRequest.toString());
     myGUI.showStatus("Dialog status: "+myDialog.getState().toString());
   }

 }catch (Exception e){
   e.printStackTrace();
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

public void userInput(int inputType, String destination,String aor)  {

try{

switch (inputType) {

  case CALL:

    Address destinationAddress = myAddressFactory.createAddress(destination);
    javax.sip.address.URI myRequestURI=destinationAddress.getURI();
    Address addressOfRecord = myAddressFactory.createAddress(aor);

    ArrayList viaHeaders=new ArrayList();
    viaHeaders.add(myViaHeader);
    MaxForwardsHeader myMaxForwardsHeader = myHeaderFactory.createMaxForwardsHeader(70);
    CallIdHeader myCallIdHeader = mySipProvider.getNewCallId();
    CSeqHeader myCSeqHeader=myHeaderFactory.createCSeqHeader(1L,"INVITE");
    FromHeader myFromHeader=myHeaderFactory.createFromHeader(addressOfRecord,"456248");
    ToHeader myToHeader=myHeaderFactory.createToHeader(destinationAddress,null);

    Request myRequest = myMessageFactory.createRequest(myRequestURI,"INVITE",
        myCallIdHeader, myCSeqHeader,myFromHeader,myToHeader, viaHeaders, myMaxForwardsHeader);

    myRequest.addHeader(myContactHeader);

    ClientTransaction myClientTransaction=mySipProvider.getNewClientTransaction(myRequest);
    myClientTransaction.sendRequest();

    myGUI.display(">>> "+myRequest.toString());

    break;

  case ACCEPT:

    Request originalRequest=myServerTransaction.getRequest();
    Response myResponse=myMessageFactory.createResponse(200,originalRequest);
    ToHeader responseToHeader=(ToHeader) myResponse.getHeader("To");
    responseToHeader.setTag("454326");
    myResponse.addHeader(myContactHeader);

    myServerTransaction.sendResponse(myResponse);
    myDialog=myServerTransaction.getDialog();
    myGUI.display(">>> "+myResponse.toString());
    myGUI.showStatus("Dialog status: "+myDialog.getState().toString());
  }

}catch (Exception e){
  e.printStackTrace();
}
}
}


