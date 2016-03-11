package example2;

import javax.sip.*;
import javax.sip.message.*;
import javax.sip.header.*;
import javax.sip.address.*;
import java.net.*;
import java.util.*;

public class example2Listener implements SipListener{

private SipFactory mySipFactory;
private SipStack mySipStack;
private ListeningPoint myListeningPoint;
private SipProvider mySipProvider;
private MessageFactory myMessageFactory;
private HeaderFactory myHeaderFactory;
private AddressFactory myAddressFactory;
private Properties myProperties;
private String myIP;
private int myPort;
Example2GUI myGUI;

  public example2Listener(int port,Example2GUI GUI) throws Exception {

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
  }

public void processRequest(RequestEvent requestReceivedEvent) {
  Request myRequest=requestReceivedEvent.getRequest();
  myGUI.display("<<< "+ myRequest.toString());
}
public void processResponse(ResponseEvent responseReceivedEvent) {
}
public void processTimeout(TimeoutEvent timeoutEvent) {
}
public void processTransactionTerminated(TransactionTerminatedEvent tevent) {
}
public void processDialogTerminated(DialogTerminatedEvent tevent) {
}
public void processIOException(IOExceptionEvent tevent) {
}

public void userInput(String destination,String aor) throws Exception {

    Address destinationAddress = myAddressFactory.createAddress(destination);
    javax.sip.address.URI myRequestURI=destinationAddress.getURI();
    Address addressOfRecord = myAddressFactory.createAddress(aor);
    Address contactAddress = myAddressFactory.createAddress("sip:"+myIP+":"+myPort);

    ArrayList viaHeaders=new ArrayList();
    ViaHeader myViaHeader = myHeaderFactory.createViaHeader(myIP, myPort,"udp",
        "z9hG4bKnashds7");
    viaHeaders.add(myViaHeader);
    MaxForwardsHeader myMaxForwardsHeader = myHeaderFactory.createMaxForwardsHeader(70);
    CallIdHeader myCallIdHeader = mySipProvider.getNewCallId();
    CSeqHeader myCSeqHeader=myHeaderFactory.createCSeqHeader(1L,"REGISTER");
    FromHeader myFromHeader=myHeaderFactory.createFromHeader(addressOfRecord,"456248");
    ToHeader myToHeader=myHeaderFactory.createToHeader(addressOfRecord,null);

    Request myRequest = myMessageFactory.createRequest(myRequestURI,"REGISTER",
        myCallIdHeader, myCSeqHeader,myFromHeader,myToHeader, viaHeaders, myMaxForwardsHeader);

    ContactHeader myContactHeader = myHeaderFactory.createContactHeader(contactAddress);
    myRequest.addHeader(myContactHeader);

    mySipProvider.sendRequest(myRequest);
    myGUI.display(">>> "+myRequest.toString());
  }
}


