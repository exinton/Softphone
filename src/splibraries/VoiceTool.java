package splibraries;

import java.net.*;
import javax.media.*;
import javax.media.protocol.*;
import javax.media.format.*;
import javax.media.control.*;
import javax.media.rtp.*;
import javax.media.rtp.event.*;
import java.util.*;
import com.sun.media.rtp.*;




public class VoiceTool implements ReceiveStreamListener {

  private RTPSessionMgr myVoiceSessionManager=null;
  private Processor myProcessor=null;
  private SendStream ss=null;
  private ReceiveStream rs=null;
  private Player player=null;
  private AudioFormat af=null;


public void startMedia(String peerIP,int peerPort,int recvPort, int fmt) {

  try{
    //We obtain the DataSource correponding to the microphone capture
    AudioFormat df=new AudioFormat(AudioFormat.LINEAR,8000,8,1);
    Vector devices=CaptureDeviceManager.getDeviceList(df);
    CaptureDeviceInfo di=(CaptureDeviceInfo) devices.elementAt(0);
    DataSource daso=Manager.createDataSource(di.getLocator());

   //We process the DataSource in order to change it into a streamable format
   myProcessor = Manager.createProcessor(daso);
   myProcessor.configure();
   while (myProcessor.getState()!=Processor.Configured) {
   Thread.sleep(10);
  }
   myProcessor.setContentDescriptor(new ContentDescriptor(ContentDescriptor.RAW_RTP));
   TrackControl track[] = myProcessor.getTrackControls();
   switch (fmt) {
     case 3: af=new AudioFormat(AudioFormat.GSM_RTP,8000,4,1);
     case 4: af=new AudioFormat(AudioFormat.G723_RTP,8000,4,1);
   }
   track[0].setFormat(af);
   myProcessor.realize();
   while (myProcessor.getState() != Processor.Realized) {}
   DataSource ds = myProcessor.getDataOutput();

   //We create a SessionManager
    myVoiceSessionManager = new RTPSessionMgr();

    //We register our interest in receiving ReceiveStreamEvents
    myVoiceSessionManager.addReceiveStreamListener(this);

    //We initialize the session
    SessionAddress senderAddr = new SessionAddress();
    myVoiceSessionManager.initSession(senderAddr, null, 0.05, 0.25);

    //We construct the arguments to the startSession method from the parameters
    // passed to startMedia() and invoke startSession() on the SessionManager
    InetAddress destAddr = InetAddress.getByName(peerIP);
    SessionAddress localAddr =
        new SessionAddress (InetAddress.getLocalHost(),recvPort,InetAddress.getLocalHost(),recvPort + 1);
    SessionAddress remoteAddr = new SessionAddress(destAddr, peerPort, destAddr, peerPort + 1);
    myVoiceSessionManager.startSession(localAddr , localAddr , remoteAddr,null);


   // We obtain a SendStream from the Datasource obtained as output to the processor
   ss = myVoiceSessionManager.createSendStream(ds, 0);

   //We start capture and transmission
   ss.start();
   myProcessor.start();

 }catch(Exception e){
       e.printStackTrace();
     }
  }


  public void stopMedia() {
    try{

      //stop and close the player
      player.stop();
      player.deallocate();
      player.close();

      //stop sending
      ss.stop();

      //stop capture and procesing
      myProcessor.stop();
      myProcessor.deallocate();
      myProcessor.close();

      //close the RTP session and free the used source ports
      myVoiceSessionManager.closeSession("terminated");
      myVoiceSessionManager.dispose();

    }catch(Exception ex) {
       ex.printStackTrace();
    }
  }






  public void update(ReceiveStreamEvent event) {
    if (event instanceof NewReceiveStreamEvent){
      rs=event.getReceiveStream();
      DataSource myDS=rs.getDataSource();
      try{
        player = Manager.createRealizedPlayer(myDS);
      }catch (Exception ex){
        ex.printStackTrace();
      }
      player.start();
    }
  }
}

