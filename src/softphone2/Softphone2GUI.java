package softphone2;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
//import com.borland.jbcl.layout.*;
import splibraries.*;


public class Softphone2GUI extends JFrame {
  JPanel contentPane;
  JButton jButton1 = new JButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField4 = new JTextField();
  Softphone2Listener list;
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

  public Softphone2GUI() {
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

  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    jButton1.setBounds(new Rectangle(9, 195, 66, 34));
    jButton1.setText("Yes");
    jButton1.addActionListener(new zphone2GUI_jButton1_actionAdapter(this));
    contentPane.setLayout(null);
    this.setSize(new Dimension(518, 375));
    this.setTitle("Internet Multimedia Communications with SIP. Softphone2");
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
    jLabel1.setBounds(new Rectangle(135, 50, 64, 15));
    jLabel3.setText("Destination:");
    jLabel3.setBounds(new Rectangle(9, 138, 63, 15));
    jTextField1.setSelectionEnd(18);
    jTextField1.setText("john@ocean.com");
    jTextField1.setBounds(new Rectangle(148, 65, 167, 18));
    jTextField3.setText("sip:169.254.220.184:5060");
    jTextField3.setBounds(new Rectangle(9, 153, 143, 23));
    jLabel4.setText("My SIP port");
    jLabel4.setBounds(new Rectangle(3, 97, 54, 19));
    jTextField4.setToolTipText("");
    jTextField4.setText("5060");
    jTextField4.setBounds(new Rectangle(50, 116, 43, 18));
    jButton2.setBounds(new Rectangle(17, 40, 59, 25));
    jButton2.setText("On");
    jButton2.addActionListener(new zphone2GUI_jButton2_actionAdapter(this));
    jButton4.setBounds(new Rectangle(79, 195, 70, 34));
    jButton4.setText("No");
    jButton4.addActionListener(new zphone2GUI_jButton4_actionAdapter(this));
    jLabel6.setText("My Name");
    jLabel6.setBounds(new Rectangle(3, 50, 54, 15));
    jTextField2.setText("John Smith");
    jTextField2.setBounds(new Rectangle(26, 65, 71, 18));
    jLabel2.setForeground(Color.red);
    jLabel2.setText("                              ");
    jLabel2.setBounds(new Rectangle(15, 99, 130, 26));
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel5.setText("                             ");
    jLabel5.setBounds(new Rectangle(9, 79, 140, 20));
    jLabel9.setText("Copyright 2007");
    jLabel9.setBounds(new Rectangle(19, 262, 107, 66));
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
    jTabbedPane1.setBounds(new Rectangle(159, 31, 335, 327));
    jPanel1.setLayout(null);
    jPanel2.setLayout(null);
    jLabel7.setText("Voice Port");
    jLabel7.setBounds(new Rectangle(3, 218, 57, 16));
    jLabel10.setText("Video Port");
    jLabel10.setBounds(new Rectangle(3, 260, 65, 21));
    jTextField5.setText("40000");
    jTextField5.setBounds(new Rectangle(60, 216, 59, 19));
    jTextField6.setText("50000");
    jTextField6.setBounds(new Rectangle(60, 262, 59, 18));
    jLabel11.setText("Media");
    jLabel11.setBounds(new Rectangle(3, 173, 32, 19));
    jLabel12.setText("Codec audio");
    jLabel12.setBounds(new Rectangle(142, 214, 72, 20));
    jLabel13.setText("Codec video");
    jLabel13.setBounds(new Rectangle(140, 252, 67, 22));
    jLabel14.setText("Rogelio Martinez Perea");
    jLabel14.setBounds(new Rectangle(21, 286, 130, 55));
    jButton3.setBounds(new Rectangle(140, 171, 174, 30));
    jButton3.setFont(new java.awt.Font("SansSerif", 0, 11));
    jButton3.setText("Apply media config.");
    jButton3.addActionListener(new zphone2GUI_jButton3_actionAdapter(this));
    jLabel15.setText("My SIP server");
    jLabel15.setBounds(new Rectangle(136, 97, 80, 19));
    jTextField7.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextField7.setText("169.254.235.127:5061");
    jTextField7.setBounds(new Rectangle(150, 116, 165, 18));
    jLabel16.setText("----------------------media configuration----------------------");
    jLabel16.setBounds(new Rectangle(27, 134, 285, 24));
    jLabel17.setText("----------------------user configuration----------------------");
    jLabel17.setBounds(new Rectangle(23, 12, 273, 16));
    jButton5.setBounds(new Rectangle(77, 40, 58, 25));
    jButton5.setText("Off");
    jButton5.addActionListener(new Softphone2GUI_jButton5_actionAdapter(this));
    jScrollPane1.setBounds(new Rectangle(2, 3, 324, 293));
    jComboBox3.setBounds(new Rectangle(216, 255, 99, 19));
    jComboBox2.setBounds(new Rectangle(216, 214, 99, 20));
    jComboBox1.setBounds(new Rectangle(35, 172, 95, 19));
    contentPane.add(jTabbedPane1, null);
    jTabbedPane1.add(jPanel2,    "Tracer");
    jPanel2.add(jScrollPane1, null);
    jTabbedPane1.add(jPanel1,  "Configuration");
    jScrollPane1.getViewport().add(jTextArea1, null);
    jPanel1.add(jLabel10, null);
    jPanel1.add(jTextField6, null);
    jPanel1.add(jLabel7, null);
    jPanel1.add(jTextField5, null);
    jPanel1.add(jButton3, null);
    jPanel1.add(jLabel11, null);
    jPanel1.add(jComboBox1, null);
    jPanel1.add(jComboBox2, null);
    jPanel1.add(jComboBox3, null);
    jPanel1.add(jLabel4, null);
    jPanel1.add(jTextField4, null);
    jPanel1.add(jTextField2, null);
    jPanel1.add(jLabel6, null);
    jPanel1.add(jTextField1, null);
    jPanel1.add(jLabel16, null);
    jPanel1.add(jLabel17, null);
    jPanel1.add(jLabel12, null);
    jPanel1.add(jLabel13, null);
    jPanel1.add(jTextField7, null);
    jPanel1.add(jLabel15, null);
    jPanel1.add(jLabel1, null);
    jComboBox1.addItem("Audio only");
    jComboBox1.addItem("Audio and Video");
    jComboBox2.addItem("GSM");
    jComboBox2.addItem("G723");
    jComboBox3.addItem("JPEG");
    jComboBox3.addItem("H263");

    contentPane.add(jButton1, null);
    contentPane.add(jButton2, null);
    contentPane.add(jLabel2, null);
    contentPane.add(jLabel3, null);
    contentPane.add(jTextField3, null);
    contentPane.add(jLabel5, null);
    contentPane.add(jButton4, null);
    contentPane.add(jButton5, null);
    contentPane.add(jLabel14, null);
    contentPane.add(jLabel9, null);
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

      list = new Softphone2Listener(config, this, jTextField7.getText());

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

  void jButton5_actionPerformed(ActionEvent e) {
    list.setOff();
    list=null;
    jLabel5.setText("");
  }

}

class zphone2GUI_jButton1_actionAdapter implements java.awt.event.ActionListener {
  Softphone2GUI adaptee;

  zphone2GUI_jButton1_actionAdapter(Softphone2GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class zphone2GUI_jButton2_actionAdapter implements java.awt.event.ActionListener {
  Softphone2GUI adaptee;

  zphone2GUI_jButton2_actionAdapter(Softphone2GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class zphone2GUI_jButton4_actionAdapter implements java.awt.event.ActionListener {
  Softphone2GUI adaptee;

  zphone2GUI_jButton4_actionAdapter(Softphone2GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}

class zphone2GUI_jButton3_actionAdapter implements java.awt.event.ActionListener {
  Softphone2GUI adaptee;

  zphone2GUI_jButton3_actionAdapter(Softphone2GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class Softphone2GUI_jButton5_actionAdapter implements java.awt.event.ActionListener {
  Softphone2GUI adaptee;

  Softphone2GUI_jButton5_actionAdapter(Softphone2GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton5_actionPerformed(e);
  }
}
