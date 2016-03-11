package splibraries;

import java.net.*;
import javax.media.*;
import javax.media.protocol.*;
import javax.media.format.*;
import javax.media.control.*;
import javax.media.rtp.*;
import javax.media.rtp.event.*;
import com.sun.media.rtp.*;
import java.awt.*;




public class VideoTool implements ReceiveStreamListener {

  private RTPSessionMgr myVideoSessionManager=null;
  private Processor myProcessor=null;
  private SendStream ss=null;
  private ReceiveStream rs=null;
  private Player player=null;
  private VideoFormat vf=null;
  private VideoFrame vframe;
  private DataSource ds=null;

public int startMedia(String peerIP,int peerPort,int recvPort, int fmt) {

  try{
    int m=0;
    MediaLocator ml=new MediaLocator("vfw://0");
    DataSource daso=Manager.createDataSource(ml);

    myProcessor = Manager.createProcessor(daso);
    myProcessor.configure();
    while (myProcessor.getState()!=Processor.Configured) {}
    myProcessor.setContentDescriptor(new ContentDescriptor(ContentDescriptor.RAW_RTP));
    TrackControl track[] = myProcessor.getTrackControls();
    switch (fmt) {
      case 26: vf=new VideoFormat(VideoFormat.JPEG_RTP);
      case 34: vf=new VideoFormat(VideoFormat.H263_RTP);
    }

    Format f[]=track[0].getSupportedFormats();
    for (int j=0;j<f.length;j++) {
      if (vf.matches(f[j])) m=1;
    }
    if (m==0) return -1;

    ((FormatControl) track[0]).setFormat(vf);
    myProcessor.realize();
    while (myProcessor.getState() != Processor.Realized) {}


    ds = myProcessor.getDataOutput();

 }catch (Exception ex){
 ex.printStackTrace();
 }

 try{
   //We create a SessionManager
    myVideoSessionManager = new RTPSessionMgr();

    //We register our interest in receiving ReceiveStreamEvents
    myVideoSessionManager.addReceiveStreamListener(this);

    //We initialize the session
    SessionAddress senderAddr = new SessionAddress();
    myVideoSessionManager.initSession(senderAddr, null, 0.05, 0.25);

    //We construct the arguments to the startSession method from the parameters
    // passed to startMedia() and invoke startSession() on the SessionManager
    InetAddress destAddr = InetAddress.getByName(peerIP);
    SessionAddress localAddr =
        new SessionAddress (InetAddress.getLocalHost(),recvPort,InetAddress.getLocalHost(),recvPort + 1);
    SessionAddress remoteAddr = new SessionAddress(destAddr, peerPort, destAddr, peerPort + 1);
    myVideoSessionManager.startSession(localAddr , localAddr , remoteAddr,null);


   // We obtain a SendStream from the Datasource obtained as output to the processor
   ss = myVideoSessionManager.createSendStream(ds, 0);

   //We start capture and transmission
   ss.start();
   myProcessor.start();

 }catch (Exception ex){
 ex.printStackTrace();
 return -1;
 }
 return 1;

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
      myVideoSessionManager.closeSession("terminated");
      myVideoSessionManager.dispose();

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
        Component comp=player.getVisualComponent();
        Dimension d=comp.getSize();
        vframe=new VideoFrame();
        vframe.jPanel1.add(comp);
        vframe.setSize(d);
        vframe.pack();
        vframe.setVisible(true);
        player.start();
      }catch (Exception ex){
        ex.printStackTrace();
      }

    }
  }
}

