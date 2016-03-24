package splibraries;

import javax.media.*;
import javax.media.protocol.*;


public class TonesTool implements ControllerListener {

  private Player player=null;
  private DataSource dsource;
  private boolean end=true;

public synchronized void controllerUpdate(ControllerEvent cEvent) {

  if (cEvent instanceof EndOfMediaEvent){
    if (!end) {
      player.start();
    }
  }
}

public void prepareTone(String filename) {
   try{

  MediaLocator ml=new MediaLocator(filename);
  dsource=Manager.createDataSource(ml);
  player=Manager.createPlayer(dsource);
  player.addControllerListener(this);

  }catch(Exception ex){
      ex.printStackTrace();
    }
}

  public void playTone()  {
    try{
      end=false;
      player.start();
    }catch(Exception ex){
      ex.printStackTrace();
    }
  }

  public synchronized void stopTone(){
    try{
      end=true;
      notify();
      player.stop();
    }catch(Exception ex){
      ex.printStackTrace();
    }
}
}
