package sipproxy_secure_auth;

import javax.sip.*;
import javax.sip.message.*;
import javax.sip.header.*;
import javax.sip.address.*;
import java.net.*;
import java.util.*;

import splibraries.*;


public class SipproxySecureListener implements SipListener{

private SipFactory mySipFactory;
private SipStack mySipStack;
private ListeningPoint myListeningPoint;
private SipProvider mySipProvider;
private MessageFactory myMessageFactory;
private HeaderFactory myHeaderFactory;
private AddressFactory myAddressFactory;
private Properties myProperties;
private String myIP;
private String mySipURI;

private String myDomain;
private int myPort;
boolean recordRoute;
SipproxySecureGUI myGUI;

private HashMap locationService;
private ArrayList transactionContext;


  public SipproxySecureListener(String domain, int port, SipproxySecureGUI GUI, boolean recroute)  {

    try{

      myDomain=domain;
      recordRoute=recroute;
      myGUI = GUI;

      myIP = InetAddress.getLocalHost().getHostAddress();
      myPort = port;
      myGUI.jLabel5.setText("Initialized at IP "+ myIP+", port "+myPort);

      mySipURI="sip:"+myIP+":"+Integer.toString(myPort)+";lr";
      mySipFactory = SipFactory.getInstance();
      mySipFactory.setPathName("gov.nist");
      myProperties = new Properties();
      myProperties.setProperty("javax.sip.STACK_NAME", "myStack");
      myProperties.setProperty("javax.sip.AUTOMATIC_DIALOG_SUPPORT", "OFF");
      mySipStack = mySipFactory.createSipStack(myProperties);
      myMessageFactory = mySipFactory.createMessageFactory();
      myHeaderFactory = mySipFactory.createHeaderFactory();
      myAddressFactory = mySipFactory.createAddressFactory();
      myListeningPoint = mySipStack.createListeningPoint(myIP, myPort, "tls");  //modify to tls
      mySipProvider = mySipStack.createSipProvider(myListeningPoint);
      mySipProvider.addSipListener(this);

      locationService=new HashMap();
      transactionContext=new ArrayList();

    }catch (Exception e) {
      System.err.println(e);
     e.printStackTrace();
    }

  }

public void setRR(boolean val) {
  recordRoute=val;
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
  }
  catch(Exception e){}


}


public void processRequest(RequestEvent requestReceivedEvent) {

  Request myRequest=requestReceivedEvent.getRequest();
  ServerTransaction myServerTransaction=requestReceivedEvent.getServerTransaction();
  String method=myRequest.getMethod();
  myGUI.displayServer("<<< "+myRequest.toString());

  try{
    if (method.equals(Request.REGISTER)) {

      if (myServerTransaction==null) {
        myServerTransaction=mySipProvider.getNewServerTransaction(myRequest);
      }

      ToHeader registerToHeader = (ToHeader) myRequest.getHeader(ToHeader.NAME);
      javax.sip.address.URI addressOfRecord = registerToHeader.getAddress().getURI();
      ContactHeader registerContactHeader = (ContactHeader) myRequest.getHeader(ContactHeader.NAME);
      javax.sip.address.URI contactAddress = registerContactHeader.getAddress().getURI();
      ExpiresHeader expH=(ExpiresHeader) myRequest.getHeader(ExpiresHeader.NAME);
      int exp=expH.getExpires();

      if (exp==0) locationService.remove(addressOfRecord);
      else locationService.put(addressOfRecord, contactAddress);

      Set keys=locationService.keySet();
      Iterator iter=keys.iterator();
      myGUI.clearLocationServiceDisplay();

      while(iter.hasNext()) {
        javax.sip.address.URI aor=(javax.sip.address.URI) iter.next();
        myGUI.jTextArea4.append(aor+ "  "+locationService.get(aor)+"\n");
    }


      Response registerOK= myMessageFactory.createResponse(200,myRequest);
      registerOK.addHeader(registerContactHeader);
      registerOK.addHeader(expH);

      myServerTransaction.sendResponse(registerOK);
      myGUI.displayServer(">>> "+ registerOK.toString());

   }

   else if (method.equals(Request.CANCEL)) {

     if (myServerTransaction==null) {
       myServerTransaction=mySipProvider.getNewServerTransaction(myRequest);
     }

     Iterator iter = transactionContext.iterator();

     while (iter.hasNext()) {

       Context con=(Context) iter.next();

       if (con.serverTrans.getBranchId().equals(myServerTransaction.getBranchId())) {

         Request originalRequest = (Request) con.requestIn;
         Response originalTransactionResponse = myMessageFactory.
             createResponse(487, originalRequest);
         Response cancelResponse = myMessageFactory.createResponse(200,
             myRequest);

         Request newCancelRequest = con.clientTrans.createCancel();

         con.serverTrans.sendResponse(originalTransactionResponse);
         myGUI.displayServer(">>> " + originalTransactionResponse.toString());

         myServerTransaction.sendResponse(cancelResponse);
         myGUI.displayServer(">>> " + cancelResponse.toString());

         ClientTransaction cancelClientTransaction = mySipProvider.
             getNewClientTransaction(newCancelRequest);
         cancelClientTransaction.sendRequest();
         myGUI.displayClient(">>> " + newCancelRequest.toString());
         break;
       }
     }

   }

   else if (method.equals(Request.ACK)){

     RouteHeader receivedRouteHeader=(RouteHeader)myRequest.getHeader(RouteHeader.NAME);
     SipURI receivedRouteHeaderSipURI=(SipURI) receivedRouteHeader.getAddress().getURI();
     String receivedRouteHeaderDomain=receivedRouteHeaderSipURI.getHost();

     Request newRequest = (Request) myRequest.clone();

     if (receivedRouteHeaderDomain.equals(myIP)) {
      newRequest.removeFirst(RouteHeader.NAME);
     }

     SipURI receivedRequestURI=(SipURI)myRequest.getRequestURI();
     String receivedRequestURIDomain=receivedRequestURI.getHost();

     if (receivedRequestURIDomain.equals(myDomain)) {
     javax.sip.address.URI newRequestURI=(javax.sip.address.URI) locationService.get(receivedRequestURI);
     newRequest.setRequestURI(newRequestURI);
     }

     mySipProvider.sendRequest(newRequest);
     myGUI.displayClient(">>> "+ newRequest.toString());

     }

   else {

     if (myServerTransaction==null) {
       myServerTransaction=mySipProvider.getNewServerTransaction(myRequest);
     }

     RouteHeader receivedRouteHeader=(RouteHeader)myRequest.getHeader(RouteHeader.NAME);
     SipURI receivedRouteHeaderSipURI=(SipURI) receivedRouteHeader.getAddress().getURI();
     String receivedRouteHeaderDomain=receivedRouteHeaderSipURI.getHost();

     Request newRequest = (Request) myRequest.clone();

     if (receivedRouteHeaderDomain.equals(myIP)) {
      newRequest.removeFirst(RouteHeader.NAME);
     }

     SipURI receivedRequestURI=(SipURI)myRequest.getRequestURI();
     String receivedRequestURIDomain=receivedRequestURI.getHost();


     if (receivedRequestURIDomain.equals(myDomain)) {
     javax.sip.address.URI newRequestURI=(javax.sip.address.URI) locationService.get(receivedRequestURI);
     newRequest.setRequestURI(newRequestURI);
     }

     MaxForwardsHeader newMaxForwardsHeader=(MaxForwardsHeader)newRequest.getHeader(MaxForwardsHeader.NAME);
     newMaxForwardsHeader.decrementMaxForwards();

     if (recordRoute) {
     Address proxyAddress = myAddressFactory.createAddress(mySipURI);
     RecordRouteHeader recordRouteHeader = myHeaderFactory.createRecordRouteHeader(proxyAddress);
     newRequest.addHeader(recordRouteHeader);
     }

     ViaHeader vH = myHeaderFactory.createViaHeader(myIP, myPort,"tls", null); //modify to tls
     newRequest.addFirst(vH);

     ClientTransaction myClientTransaction= mySipProvider.getNewClientTransaction(newRequest);
     String bid=myClientTransaction.getBranchId();
     myClientTransaction.sendRequest();
     myGUI.displayClient(">>> "+ newRequest.toString());

     Context ctxt=new Context();
     ctxt.clientTrans=myClientTransaction;
     ctxt.serverTrans=myServerTransaction;
     ctxt.method=method;
     ctxt.requestIn=myRequest;
     ctxt.requestOut=newRequest;

     transactionContext.add(ctxt);

     myGUI.appendOngoingTransactionsDisplay(ctxt.method+ "  "+ctxt.clientTrans.toString().substring(25)+"   "+
                                ctxt.serverTrans.toString().substring(25)+"\n");

}

  }catch (Exception e){
    e.printStackTrace();
  }


}
public void processResponse(ResponseEvent responseReceivedEvent) {
  Response myResponse=responseReceivedEvent.getResponse();
  ClientTransaction myClientTransaction=responseReceivedEvent.getClientTransaction();
  myGUI.displayClient("<<< "+ myResponse.toString());

  int statusCode=myResponse.getStatusCode();
  CSeqHeader originalCSeq=(CSeqHeader) myClientTransaction.getRequest().getHeader(CSeqHeader.NAME);
  String method=originalCSeq.getMethod();

  if ( (statusCode == 100)||(statusCode==487) ) return;
  if ( method.equals("CANCEL") ) return;

  try{
    if (myClientTransaction != null) {
      Response newResponse = (Response) myResponse.clone();
      newResponse.removeFirst(ViaHeader.NAME);

      Iterator iter = transactionContext.iterator();

     while (iter.hasNext()) {
       Context con=(Context) iter.next();
       if (con.clientTrans.equals(myClientTransaction)) {
         con.serverTrans.sendResponse(newResponse);
         myGUI.displayServer(">>> " + newResponse.toString());
         break;
       }
      }

      } else {
      Response newResponse = (Response) myResponse.clone();
      newResponse.removeFirst(ViaHeader.NAME);
      mySipProvider.sendResponse(newResponse);
      myGUI.displayServer(">>> "+ newResponse.toString());

    }
}catch (Exception e){
e.printStackTrace();
}

}
public void processTimeout(TimeoutEvent timeoutEvent) {
}

public void processTransactionTerminated(TransactionTerminatedEvent transactionTerminatedEvent) {

  if (transactionTerminatedEvent.isServerTransaction()) {
    ServerTransaction st = transactionTerminatedEvent.getServerTransaction();
    Iterator iter = transactionContext.iterator();
    myGUI.clearOngoingTransactionsDisplay();
    while(iter.hasNext()) {
      Context con=(Context) iter.next();
      if (con.serverTrans.equals(st)) {
        iter.remove();
      }
      else myGUI.appendOngoingTransactionsDisplay(con.method+ "  "+con.clientTrans.toString().substring(25)+"   "+
                                con.serverTrans.toString().substring(25)+"\n");
    }
  }
}


  public void processDialogTerminated(DialogTerminatedEvent tevent) {

  }

  public void processIOException(IOExceptionEvent tevent) {
    System.out.println("TRANSPORT EXCEPTION");
   }

}

class Context {

  ClientTransaction clientTrans;
  ServerTransaction serverTrans;
  String method;
  Request requestIn;
  Request requestOut;

  public Context () {

    clientTrans=null;
    serverTrans=null;
  }

}
