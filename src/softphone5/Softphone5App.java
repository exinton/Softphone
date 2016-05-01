package softphone5;

import javax.swing.UIManager;

import Configuration.SoftPhoneConfiguration;

import java.awt.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Softphone5App {
  boolean packFrame = false;

  //Construct the application
  public Softphone5App() {
    Softphone5GUI frame = new Softphone5GUI();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

  //Main method
  public static void main(String[] args) {
	// setup TLS properties
	  //put this into gui
	  
	  String keyStoreFilePath;
	  String keyStorePassword;
	  String keyStoreType;
	  SoftPhoneConfiguration myconfig;
	// setup TLS properties
	  myconfig = SoftPhoneConfiguration.getInstance();
	  keyStoreFilePath=myconfig.keyStoreFilePath;
	  keyStorePassword=myconfig.keyStorePassword;
	  keyStoreType=myconfig.keyStoreType;
	  
	  String currentDir=System.getProperty("user.dir");
      System.setProperty( "javax.net.ssl.keyStore",  currentDir+keyStoreFilePath );
      System.setProperty( "javax.net.ssl.trustStore",currentDir+keyStoreFilePath);
      System.setProperty( "javax.net.ssl.keyStorePassword", keyStorePassword );
      System.setProperty( "javax.net.ssl.keyStoreType", keyStoreType );
      
	  
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new Softphone5App();
  }
}