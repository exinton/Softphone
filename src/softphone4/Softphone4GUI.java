package softphone4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import splibraries.*;


public class Softphone4GUI extends JFrame {
  JPanel contentPane;
  JButton jButton1 = new JButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField4 = new JTextField();
  Softphone4Listener list;
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
  JLabel jLabel15 = new JLabel();
  JTextField jTextField7 = new JTextField();
  JLabel jLabel16 = new JLabel();
  JLabel jLabel17 = new JLabel();
  JButton jButton5 = new JButton();
  JButton jButton6 = new JButton();
  JTextField jTextField8 = new JTextField();
  JLabel jLabel8 = new JLabel();
  JPanel jPanel3 = new JPanel();
  JTextArea jTextArea2 = new JTextArea();


  public Softphone4GUI() {
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

public void displayMessage(String text) {
  jTextArea2.append(text + "\n");
}


public void showStatus(String text) {
  jLabel2.setText(text);
}

  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    jButton1.setBounds(new Rectangle(3, 240, 52, 34));
    jButton1.setText("Yes");
    jButton1.addActionListener(new zphone2GUI_jButton1_actionAdapter(this));
    contentPane.setLayout(null);
    this.setSize(new Dimension(518, 400));
    this.setTitle("Internet Multimedia Communications Using SIP. Softphone4");
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
    jLabel1.setText("My user ID:");
    jLabel1.setBounds(new Rectangle(113, 39, 64, 15));
    jLabel3.setText("Destination:");
    jLabel3.setBounds(new Rectangle(3, 174, 63, 15));
    jTextField1.setSelectionEnd(18);
    jTextField1.setText("john@ocean.com");
    jTextField1.setBounds(new Rectangle(123, 65, 145, 18));
    jTextField3.setText("sip:192.168.2.160:5060");
    jTextField3.setBounds(new Rectangle(3, 189, 198, 23));
    jLabel4.setText("My SIP port");
    jLabel4.setBounds(new Rectangle(1, 94, 54, 19));
    jTextField4.setToolTipText("");
    jTextField4.setText("5060");
    jTextField4.setBounds(new Rectangle(19, 121, 82, 18));
    jButton2.setBounds(new Rectangle(9, 5, 59, 23));
    jButton2.setText("On");
    jButton2.addActionListener(new zphone2GUI_jButton2_actionAdapter(this));
    jButton4.setBounds(new Rectangle(55, 240, 54, 34));
    jButton4.setText("No");
    jButton4.addActionListener(new zphone2GUI_jButton4_actionAdapter(this));
    jLabel6.setText("My Name");
    jLabel6.setBounds(new Rectangle(1, 40, 54, 15));
    jTextField2.setText("John Smith");
    jTextField2.setBounds(new Rectangle(15, 64, 79, 18));
    jLabel2.setForeground(Color.red);
    jLabel2.setText("                              ");
    jLabel2.setBounds(new Rectangle(3, 148, 119, 26));
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel5.setText("                              ");
    jLabel5.setBounds(new Rectangle(260, 7, 155, 20));
    jLabel9.setText("Copyright 2007");
    jLabel9.setBounds(new Rectangle(3, 265, 107, 64));
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
    jTabbedPane1.setBounds(new Rectangle(211, 29, 299, 334));
    jPanel1.setLayout(null);
    jPanel2.setLayout(null);
    jLabel7.setText("Voice Port");
    jLabel7.setBounds(new Rectangle(7, 230, 57, 16));
    jLabel10.setText("Video Port");
    jLabel10.setBounds(new Rectangle(8, 264, 56, 21));
    jTextField5.setText("40000");
    jTextField5.setBounds(new Rectangle(64, 228, 51, 19));
    jTextField6.setText("50000");
    jTextField6.setBounds(new Rectangle(64, 266, 51, 18));
    jLabel11.setText("Media");
    jLabel11.setBounds(new Rectangle(1, 179, 32, 19));
    jLabel12.setText("Codec audio");
    jLabel12.setBounds(new Rectangle(129, 226, 72, 20));
    jLabel13.setText("Codec video");
    jLabel13.setBounds(new Rectangle(127, 262, 67, 22));
    jLabel14.setText("Rogelio Martinez Perea");
    jLabel14.setBounds(new Rectangle(3, 284, 130, 63));
    jButton3.setBounds(new Rectangle(149, 177, 128, 30));
    jButton3.setFont(new java.awt.Font("SansSerif", 0, 11));
    jButton3.setText("Apply media config.");
    jButton3.addActionListener(new zphone2GUI_jButton3_actionAdapter(this));
    jLabel15.setText("My SIP server:");
    jLabel15.setBounds(new Rectangle(119, 95, 134, 19));
    jTextField7.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField7.setText("192.168.2.160:5061");
    jTextField7.setBounds(new Rectangle(125, 121, 141, 18));
    jTextField7.addActionListener(new Softphone3GUI_jTextField7_actionAdapter(this));
    jLabel16.setText("----------------------media configuration-----------------");
    jLabel16.setBounds(new Rectangle(8, 139, 266, 24));
    jLabel17.setText("----------------------user configuration-----------------");
    jLabel17.setBounds(new Rectangle(1, 12, 254, 17));
    jButton5.setBounds(new Rectangle(129, 5, 58, 24));
    jButton5.setText("Off");
    jButton5.addActionListener(new Softphone3GUI_jButton5_actionAdapter(this));
    jButton6.setBounds(new Rectangle(110, 240, 91, 34));
    jButton6.setText("Send MSG  ");
    jButton6.addActionListener(new Softphone3GUI_jButton6_actionAdapter(this));
    jTextField8.setSelectionStart(11);
    jTextField8.setText("");
    jTextField8.setBounds(new Rectangle(10, 81, 191, 67));
    jLabel8.setText("Message");
    jLabel8.setBounds(new Rectangle(11, 65, 90, 16));
    jPanel3.setLayout(null);
    jScrollPane1.setBounds(new Rectangle(0, 0, 294, 308));
    jComboBox3.setBounds(new Rectangle(190, 265, 84, 19));
    jComboBox2.setBounds(new Rectangle(190, 226, 78, 20));
    jComboBox1.setBounds(new Rectangle(33, 178, 78, 19));
    jTextArea2.setBounds(new Rectangle(4, 4, 284, 298));
    contentPane.add(jTabbedPane1, null);
    jTabbedPane1.add(jPanel2,    "Tracer");
    jPanel2.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(jTextArea1, null);
    jTabbedPane1.add(jPanel1,    "Configuration");
    jPanel1.add(jComboBox3, null);
    jPanel1.add(jLabel10, null);
    jPanel1.add(jTextField6, null);
    jPanel1.add(jTextField5, null);
    jPanel1.add(jLabel7, null);
    jPanel1.add(jComboBox2, null);
    jPanel1.add(jComboBox1, null);
    jPanel1.add(jLabel11, null);
    jPanel1.add(jButton3, null);
    jPanel1.add(jLabel16, null);
    jPanel1.add(jTextField4, null);
    jPanel1.add(jLabel4, null);
    jPanel1.add(jTextField7, null);
    jPanel1.add(jLabel15, null);
    jPanel1.add(jTextField1, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jTextField2, null);
    jPanel1.add(jLabel6, null);
    jPanel1.add(jLabel17, null);
    jPanel1.add(jLabel13, null);
    jPanel1.add(jLabel12, null);
    jTabbedPane1.add(jPanel3,  "Messaging");
    jPanel3.add(jTextArea2, null);
    jComboBox1.addItem("Audio only");
    jComboBox1.addItem("Audio and Video");
    jComboBox2.addItem("GSM");
    jComboBox2.addItem("G723");
    jComboBox3.addItem("JPEG");
    jComboBox3.addItem("H263");

    contentPane.add(jButton2, null);
    contentPane.add(jTextField3, null);
    contentPane.add(jLabel3, null);
    contentPane.add(jLabel2, null);
    contentPane.add(jTextField8, null);
    contentPane.add(jLabel8, null);
    contentPane.add(jLabel5, null);
    contentPane.add(jLabel14, null);
    contentPane.add(jLabel9, null);
    contentPane.add(jButton1, null);
    contentPane.add(jButton4, null);
    contentPane.add(jButton6, null);
    contentPane.add(jButton5, null);
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
      list.userInput(0,jTextField3.getText(),null);
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

      list = new Softphone4Listener(config, this, jTextField7.getText());

    }catch (Exception exc){

    }
  }


  void jButton4_actionPerformed(ActionEvent e) {
    list.userInput(1,null,null);
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

  void jButton6_actionPerformed(ActionEvent e) {
    list.userInput(2,jTextField3.getText(),jTextField8.getText());
  }

  void jTextField7_actionPerformed(ActionEvent e) {

  }

  void jButton5_actionPerformed(ActionEvent e) {
    list.setOff();
    list=null;
    jLabel5.setText("");
  }
}

class zphone2GUI_jButton1_actionAdapter implements java.awt.event.ActionListener {
  Softphone4GUI adaptee;

  zphone2GUI_jButton1_actionAdapter(Softphone4GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class zphone2GUI_jButton2_actionAdapter implements java.awt.event.ActionListener {
  Softphone4GUI adaptee;

  zphone2GUI_jButton2_actionAdapter(Softphone4GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class zphone2GUI_jButton4_actionAdapter implements java.awt.event.ActionListener {
  Softphone4GUI adaptee;

  zphone2GUI_jButton4_actionAdapter(Softphone4GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}

class zphone2GUI_jButton3_actionAdapter implements java.awt.event.ActionListener {
  Softphone4GUI adaptee;

  zphone2GUI_jButton3_actionAdapter(Softphone4GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class Softphone3GUI_jButton6_actionAdapter implements java.awt.event.ActionListener {
  Softphone4GUI adaptee;

  Softphone3GUI_jButton6_actionAdapter(Softphone4GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton6_actionPerformed(e);
  }
}

class Softphone3GUI_jTextField7_actionAdapter implements java.awt.event.ActionListener {
  Softphone4GUI adaptee;

  Softphone3GUI_jTextField7_actionAdapter(Softphone4GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField7_actionPerformed(e);
  }
}

class Softphone3GUI_jButton5_actionAdapter implements java.awt.event.ActionListener {
  Softphone4GUI adaptee;

  Softphone3GUI_jButton5_actionAdapter(Softphone4GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton5_actionPerformed(e);
  }
}
