package MediaAdapter;

import java.io.IOException;
import java.net.*;
import javax.media.*;
import javax.media.protocol.*;
import javax.media.format.*;
import javax.media.control.*;
import javax.media.rtp.*;
import javax.media.rtp.event.*;
import javax.media.rtp.rtcp.SourceDescription;

import gnu.java.zrtp.ZrtpCodes;
import gnu.java.zrtp.ZrtpConfigure;
import gnu.java.zrtp.ZrtpConstants;
import gnu.java.zrtp.ZrtpUserCallback;
import gnu.java.zrtp.jmf.transform.TransformManager;
import gnu.java.zrtp.jmf.transform.zrtp.ZRTPTransformEngine;
import gnu.java.zrtp.jmf.transform.zrtp.ZrtpTransformConnector;

import java.util.*;





public class VoiceTool_secure implements ReceiveStreamListener{

  private RTPManager rtpManager=null;
  private Processor myProcessor=null;
  private SendStream ss=null;
  private ReceiveStream rs=null;
  private Player player=null;
  private AudioFormat af=null;
  private ZrtpTransformConnector transConnector = null;
  private ZRTPTransformEngine zrtpEngine = null;

  protected class MyCallback extends ZrtpUserCallback {
      MyCallback() {
      }
      
      public void secureOn(String cipher) {
          System.err.println("Tx Cipher: " + cipher);
      }

      public void showSAS(String sas, boolean verified) {
          System.err.println("Tx SAS: " + sas);
      }

      public void showMessage(ZrtpCodes.MessageSeverity sev, EnumSet<?> subCode) {
          Iterator<?> ii = subCode.iterator();
          if (sev == ZrtpCodes.MessageSeverity.Info) {
              ZrtpCodes.InfoCodes inf = (ZrtpCodes.InfoCodes)ii.next();
              System.err.println("Tx show message sub code: " + inf);
              if (inf == ZrtpCodes.InfoCodes.InfoSecureStateOn) {
            	  //may need to triger something here
            	  
            	  
                  System.err.println("Tx peer hello hash: " + zrtpEngine.getPeerHelloHash());
              }
              return;
          }
          System.err.println("Tx show message sub code: " + ii.next());
      }

      public void zrtpNegotiationFailed(ZrtpCodes.MessageSeverity severity, EnumSet<?> subCode) {
          Iterator<?> ii = subCode.iterator();
          System.err.println("Tx negotiation failed sub code: " + ii.next());
      }
      
      public void secureOff() {
          System.err.println("Tx Security off");
      }

      public void zrtpNotSuppOther() {
          System.err.println("Tx ZRTP not supported");
      }

  }
  

public void startMedia(String peerIP,int peerPort,int recvPort, String fmt) {

  try{
    //We obtain the DataSource correponding to the microphone capture

    AudioFormat df=new AudioFormat(AudioFormat.LINEAR,8000,16,1);
    Vector devices=CaptureDeviceManager.getDeviceList(df);
    CaptureDeviceInfo di=(CaptureDeviceInfo) devices.elementAt(0);
    
    //prepare media stream
    DataSource daso=Manager.createDataSource(di.getLocator());
    //format of track
    Format wantedFormat=di.getFormats()[7];
    System.out.println(di.getFormats()[7]);
    if (requestCaptureFormat(wantedFormat,daso) == false){
    	System.out.println("Could not change the audio format to LINEAR, 8000.0 Hz, 16-bit, Mono, LittleEndian, Signed ");
    }
    
   //We process the DataSource in order to change it into a streamable format
   myProcessor = Manager.createProcessor(daso);
   myProcessor.configure();
   while (myProcessor.getState()!=Processor.Configured) {
   Thread.sleep(20);
  }
   System.out.println("start process2");
   myProcessor.setContentDescriptor(new ContentDescriptor(ContentDescriptor.RAW_RTP));
   TrackControl track[] = myProcessor.getTrackControls();
   Format mySupportedFormats[]=track[0].getSupportedFormats();
   
   
   
   switch (fmt) {

     case "DVI": af=new AudioFormat(AudioFormat.DVI_RTP,8000,4,1);
     		break;
     case "ULAW": af=new AudioFormat(AudioFormat.ULAW_RTP,8000,16,1);
     		break;
     case "GSM": af=new AudioFormat(AudioFormat.GSM_RTP,8000,8,1);
     	break;		
     		
   }
   track[0].setFormat(af);
   System.out.println("start process3");
   myProcessor.realize();
   System.out.println("start process4");
   
   while (myProcessor.getState() != Processor.Realized) {
	   Thread.sleep(50);
   }
   
   System.out.println("start process5");
   DataSource ds = myProcessor.getDataOutput();		//source encoded data stream
   
   
   InetAddress destAddr = InetAddress.getByName(peerIP);
   SessionAddress localAddr =
       new SessionAddress (InetAddress.getLocalHost(),recvPort,InetAddress.getLocalHost(),recvPort + 1);
   SessionAddress remoteAddr = new SessionAddress(destAddr, peerPort, destAddr, peerPort + 1);
   //We create a SessionManager
    rtpManager = RTPManager.newInstance();

    // create a ZRTP connector with own bind address
    transConnector = (ZrtpTransformConnector)TransformManager.createZRTPConnector(localAddr);
    zrtpEngine = transConnector.getEngine();
    ZrtpConfigure config = new ZrtpConfigure();
    config.setStandardConfig();
        config.clear();
        config.addPubKeyAlgo(ZrtpConstants.SupportedPubKeys.E255);
        config.addPubKeyAlgo(ZrtpConstants.SupportedPubKeys.DH3K);
        config.addPubKeyAlgo(ZrtpConstants.SupportedPubKeys.EC25);
        config.addHashAlgo(ZrtpConstants.SupportedHashes.S384);

        config.setMandatoryOnly();
        config.addSasTypeAlgo(ZrtpConstants.SupportedSASTypes.B32E);

    zrtpEngine.setUserCallback(new MyCallback());
    
    
    
    
    if (!zrtpEngine.initialize("test_t.zid", config))
        System.err.println("Initialize failed");

    int versions = zrtpEngine.getNumberSupportedVersions();
    for (int idx = 0; idx < versions; idx++)
        System.err.println("Tx Hello hash: " + zrtpEngine.getHelloHash(idx));
    
    //rtpManager.addReceiveStreamListener(this);
   //rtpManager.addSendStreamListener(this);
    //We register our interest in receiving ReceiveStreamEvents
    rtpManager.addReceiveStreamListener(this);

    // initialize the RTPManager using the ZRTP connector
    rtpManager.initialize(transConnector);

    transConnector.addTarget(remoteAddr);  

    ss = rtpManager.createSendStream(ds, 0);
    boolean result=zrtpEngine.isEnableZrtp();
    System.out.println("zrtp engine"+result);
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
      
      rtpManager.removeTargets("Terminated");
      rtpManager.dispose();

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
  
  public boolean requestCaptureFormat(Format requested_format, DataSource ds) {

      if (ds instanceof CaptureDevice) {
          FormatControl[] fcs = ((CaptureDevice) ds).getFormatControls();
          for (FormatControl fc : fcs) {
              Format[] formats = ((FormatControl) fc).getSupportedFormats();
              for (Format format : formats) {
                  if (requested_format.matches(format)) {
                      ((FormatControl) fc).setFormat(format);
                      return true;
                  }
              }
          }
      }
      return false;
  }
  
  /*
   * Method required by BufferTransferHandler
   */
  public void transferData(PushBufferStream stream) {
      // System.err.println("Received a transferData request from: " +
      // stream.toString());
      Buffer buf = new Buffer();
      try {
          stream.read(buf);
      } catch (java.io.IOException ex) {
          System.err.println("Buffer read exception: " + ex.getMessage());
      }
      Format fmt = buf.getFormat();
      Class<?> cls = fmt.getDataType();
      //System.err.println("buf length: " + buf.getLength() + ", timestamp: "
      //        + buf.getTimeStamp());
      // System.err.println("buffer: " + buf.getFormat().toString());

      if (cls == Format.byteArray) {
          byte[] data = (byte[]) buf.getData();
          System.err.println("RX Data: '"
                  + new String(data, buf.getOffset(), buf.getLength()) + "'");
      }
  }



  

  
}

