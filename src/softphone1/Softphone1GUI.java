package softphone1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import splibraries.*;

public class Softphone1GUI extends JFrame {
  JPanel contentPane;
  JButton jButton1 = new JButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField4 = new JTextField();
  Softphone1Listener list;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JButton jButton2 = new JButton();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JButton jButton4 = new JButton();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JTabbedPane jTabbedPane1 = new JTabbedPane();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JTextField jTextField5 = new JTextField();
  JTextField jTextField6 = new JTextField();
  JLabel jLabel11 = new JLabel();
  JComboBox jComboBox1 = new JComboBox();
  JLabel jLabel12 = new JLabel();
  JComboBox jComboBox2 = new JComboBox();
  JLabel jLabel13 = new JLabel();
  JComboBox jComboBox3 = new JComboBox();
  JLabel jLabel14 = new JLabel();
  JButton jButton3 = new JButton();
  JLabel jLabel16 = new JLabel();
  JLabel jLabel17 = new JLabel();
  JButton jButton5 = new JButton();
  JLabel jLabel8 = new JLabel();

  //Construct the frame
  public Softphone1GUI() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }


public void display(String text) {
  jTextArea1.append(text);

}


public void showStatus(String text) {
  jLabel2.setText(text);
}

  //Component initialization
  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    jButton1.setBounds(new Rectangle(8, 246, 65, 34));
    jButton1.setText("Yes");
    jButton1.addActionListener(new zphone2GUI_jButton1_actionAdapter(this));
    contentPane.setLayout(null);
    this.setSize(new Dimension(518, 386));
    this.setTitle("Internet Multimedia Communications with SIP. Softphone1");
    contentPane.setBackground(SystemColor.control);
    contentPane.setEnabled(false);
    contentPane.setForeground(Color.black);
    contentPane.setAlignmentY((float) 0.5);
    contentPane.setDebugGraphicsOptions(0);
    contentPane.setDoubleBuffered(true);
    contentPane.setMaximumSize(new Dimension(2147483647, 2147483647));
    contentPane.setMinimumSize(new Dimension(380, 244));
    contentPane.setOpaque(true);
    contentPane.setPreferredSize(new Dimension(380, 244));
    contentPane.setRequestFocusEnabled(true);
    jLabel1.setText("My user ID");
    jLabel1.setBounds(new Rectangle(147, 49, 60, 15));
    jLabel3.setText("Destination:");
    jLabel3.setBounds(new Rectangle(4, 189, 63, 15));
    jTextField1.setSelectionEnd(18);
    jTextField1.setText("john");
    jTextField1.setBounds(new Rectangle(207, 48, 111, 18));
    jTextField1.addActionListener(new Softphone1GUI_jTextField1_actionAdapter(this));
    jTextField3.setText("sip:169.254.220.184:5060");
    jTextField3.setBounds(new Rectangle(4, 204, 151, 23));
    jLabel4.setText("My SIP port");
    jLabel4.setBounds(new Rectangle(6, 75, 54, 19));
    jTextField4.setToolTipText("");
    jTextField4.setText("5060");
    jTextField4.setBounds(new Rectangle(32, 94, 81, 18));
    jButton2.setBounds(new Rectangle(18, 39, 59, 25));
    jButton2.setText("On");
    jButton2.addActionListener(new zphone2GUI_jButton2_actionAdapter(this));
    jButton4.setBounds(new Rectangle(79, 246, 73, 34));
    jButton4.setText("No");
    jButton4.addActionListener(new zphone2GUI_jButton4_actionAdapter(this));
    jLabel6.setText("My Name");
    jLabel6.setBounds(new Rectangle(6, 33, 54, 15));
    jTextField2.setText("John Smith");
    jTextField2.setBounds(new Rectangle(32, 48, 81, 18));
    jLabel2.setForeground(Color.red);
    jLabel2.setText("                              ");
    jLabel2.setBounds(new Rectangle(15, 140, 124, 41));
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel5.setText("                              ");
    jLabel5.setBounds(new Rectangle(4, 108, 129, 32));
    jLabel9.setText("Copyright 2007");
    jLabel9.setBounds(new Rectangle(4, 299, 82, 57));
    jTabbedPane1.setTabPlacement(JTabbedPane.TOP);
    jTabbedPane1.setEnabled(true);
    jTabbedPane1.setForeground(Color.black);
    jTabbedPane1.setAlignmentY((float) 0.5);
    jTabbedPane1.setDebugGraphicsOptions(0);
    jTabbedPane1.setDoubleBuffered(false);
    jTabbedPane1.setMaximumSize(new Dimension(32767, 32767));
    jTabbedPane1.setMinimumSize(new Dimension(5, 5));
    jTabbedPane1.setOpaque(false);
    jTabbedPane1.setPreferredSize(new Dimension(5, 5));
    jTabbedPane1.setRequestFocusEnabled(true);
    jTabbedPane1.setToolTipText("");
    jTabbedPane1.setVerifyInputWhenFocusTarget(true);
    jTabbedPane1.setBounds(new Rectangle(162, 31, 342, 317));
    jPanel1.setLayout(null);
    jPanel2.setLayout(null);
    jLabel7.setText("Voice Port");
    jLabel7.setBounds(new Rectangle(7, 192, 54, 16));
    jLabel10.setText("Video Port");
    jLabel10.setBounds(new Rectangle(7, 239, 54, 21));
    jTextField5.setText("40000");
    jTextField5.setBounds(new Rectangle(61, 189, 58, 19));
    jTextField6.setText("50000");
    jTextField6.setBounds(new Rectangle(61, 241, 58, 18));
    jLabel11.setText("Media");
    jLabel11.setBounds(new Rectangle(7, 147, 32, 19));
    jLabel12.setText("Codec audio");
    jLabel12.setBounds(new Rectangle(149, 189, 65, 20));
    jLabel13.setText("Codec video");
    jLabel13.setBounds(new Rectangle(146, 237, 67, 22));
    jLabel14.setText("Rogelio Martinez Perea");
    jLabel14.setBounds(new Rectangle(4, 311, 130, 60));
    jButton3.setBounds(new Rectangle(155, 146, 167, 30));
    jButton3.setFont(new java.awt.Font("SansSerif", 0, 11));
    jButton3.setText("Apply media config.");
    jButton3.addActionListener(new zphone2GUI_jButton3_actionAdapter(this));
    jLabel16.setText("----------------------media configuration----------------------");
    jLabel16.setBounds(new Rectangle(11, 112, 285, 24));
    jLabel17.setText("----------------------user configuration----------------------");
    jLabel17.setBounds(new Rectangle(12, 17, 273, 16));
    jButton5.setBounds(new Rectangle(86, 40, 58, 25));
    jButton5.setText("Off");
    jButton5.addActionListener(new Softphone1GUI_jButton5_actionAdapter(this));
    jComboBox3.setBounds(new Rectangle(222, 240, 108, 19));
    jComboBox2.setBounds(new Rectangle(220, 188, 110, 20));
    jComboBox1.setBounds(new Rectangle(39, 147, 93, 19));
    jScrollPane1.setBounds(new Rectangle(2, 3, 332, 285));
    contentPane.add(jTabbedPane1, null);
    jTabbedPane1.add(jPanel2,    "Tracer");
    jPanel2.add(jScrollPane1, null);
    jTabbedPane1.add(jPanel1,   "Configuration");
    jScrollPane1.getViewport().add(jTextArea1, null);
    jPanel1.add(jLabel16, null);
    jPanel1.add(jLabel17, null);
    jPanel1.add(jTextField5, null);
    jPanel1.add(jComboBox2, null);
    jPanel1.add(jLabel8, null);
    jPanel1.add(jLabel6, null);
    jPanel1.add(jTextField2, null);
    jPanel1.add(jTextField4, null);
    jPanel1.add(jComboBox3, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jTextField1, null);
    jPanel1.add(jButton3, null);
    jPanel1.add(jLabel12, null);
    jPanel1.add(jLabel13, null);
    jPanel1.add(jComboBox1, null);
    jPanel1.add(jLabel11, null);
    jPanel1.add(jLabel10, null);
    jPanel1.add(jTextField6, null);
    jPanel1.add(jLabel4, null);
    jPanel1.add(jLabel7, null);
    jComboBox1.addItem("Audio only");
    jComboBox1.addItem("Audio and Video");
    jComboBox2.addItem("GSM");
    jComboBox2.addItem("G723");
    jComboBox3.addItem("JPEG");
    jComboBox3.addItem("H263");

    contentPane.add(jButton1, null);
    contentPane.add(jLabel2, null);
    contentPane.add(jTextField3, null);
    contentPane.add(jLabel5, null);
    contentPane.add(jLabel3, null);
    contentPane.add(jButton4, null);
    contentPane.add(jButton2, null);
    contentPane.add(jButton5, null);
    contentPane.add(jLabel9, null);
    contentPane.add(jLabel14, null);
    jTabbedPane1.setSelectedIndex(-1);


  }

  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  void jButton1_actionPerformed(ActionEvent e)  {
    try {
      list.userInput(0,jTextField3.getText());
    } catch (Exception ex){

    }
  }

  void jButton2_actionPerformed(ActionEvent e) {
    try{

      Configuration config=new Configuration();
      config.sipPort=Integer.parseInt(jTextField4.getText());
      config.name=jTextField2.getText();
      config.userID=jTextField1.getText();
      config.audioPort=Integer.parseInt(jTextField5.getText());
      if (jComboBox2.getSelectedItem().equals("GSM")) {
        config.audioCodec=3;
      }
      else config.audioCodec=4;

      if (jComboBox1.getSelectedItem().equals("Audio and Video")) {
        config.videoPort=Integer.parseInt(jTextField6.getText());
        if (jComboBox3.getSelectedItem().equals("JPEG")) {
          config.videoCodec=26;
        }
        else config.videoCodec=34;
      }
      else config.videoPort=-1;

       list = new Softphone1Listener(config, this);

    }catch (Exception exc){

    }
  }


  void jButton4_actionPerformed(ActionEvent e) {
    list.userInput(1,null);
  }

  void jButton3_actionPerformed(ActionEvent e) {

    Configuration config=new Configuration();
    config.sipPort=Integer.parseInt(jTextField4.getText());
    config.name=jTextField2.getText();
    config.userID=jTextField1.getText();
    config.audioPort=Integer.parseInt(jTextField5.getText());
    if (jComboBox2.getSelectedItem().equals("GSM")) {
      config.audioCodec=3;
    }
    else config.audioCodec=4;

    if (jComboBox1.getSelectedItem().equals("Audio and Video")) {
      config.videoPort=Integer.parseInt(jTextField6.getText());
      if (jComboBox3.getSelectedItem().equals("JPEG")) {
        config.videoCodec=26;
      }
      else config.videoCodec=34;
    }
    else config.videoPort=-1;

     list.updateConfiguration(config);

  }

  void jTextField1_actionPerformed(ActionEvent e) {

  }

  void jButton5_actionPerformed(ActionEvent e) {
    list.setOff();
    list=null;
    jLabel5.setText("");
  }

}

class zphone2GUI_jButton1_actionAdapter implements java.awt.event.ActionListener {
  Softphone1GUI adaptee;

  zphone2GUI_jButton1_actionAdapter(Softphone1GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class zphone2GUI_jButton2_actionAdapter implements java.awt.event.ActionListener {
  Softphone1GUI adaptee;

  zphone2GUI_jButton2_actionAdapter(Softphone1GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class zphone2GUI_jButton4_actionAdapter implements java.awt.event.ActionListener {
  Softphone1GUI adaptee;

  zphone2GUI_jButton4_actionAdapter(Softphone1GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}

class zphone2GUI_jButton3_actionAdapter implements java.awt.event.ActionListener {
  Softphone1GUI adaptee;

  zphone2GUI_jButton3_actionAdapter(Softphone1GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class Softphone1GUI_jTextField1_actionAdapter implements java.awt.event.ActionListener {
  Softphone1GUI adaptee;

  Softphone1GUI_jTextField1_actionAdapter(Softphone1GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField1_actionPerformed(e);
  }
}

class Softphone1GUI_jButton5_actionAdapter implements java.awt.event.ActionListener {
  Softphone1GUI adaptee;

  Softphone1GUI_jButton5_actionAdapter(Softphone1GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton5_actionPerformed(e);
  }
}
