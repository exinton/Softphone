package sipproxy;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SipproxyGUI extends JFrame {
  JPanel contentPane;
  JLabel jLabel4 = new JLabel();
  JTextField jTextField4 = new JTextField();
  SipproxyListener list;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JButton jButton2 = new JButton();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JCheckBox jCheckBox1 = new JCheckBox();
  JScrollPane jScrollPane2 = new JScrollPane();
  JTextArea jTextArea2 = new JTextArea();
  JScrollPane jScrollPane3 = new JScrollPane();
  JTextArea jTextArea3 = new JTextArea();
  JScrollPane jScrollPane4 = new JScrollPane();
  JTextArea jTextArea4 = new JTextArea();
  JTabbedPane jTabbedPane1 = new JTabbedPane();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JButton jButton1 = new JButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();

  public SipproxyGUI() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }


public void displayServer(String text) {
  jTextArea1.append(text);
}

public void displayClient(String text) {
  jTextArea2.append(text);
}

public void clearLocationServiceDisplay() {
   jTextArea4.setText("");
}

public void appendLocationServiceDisplay(String text) {
  jTextArea4.append(text);
}

public void clearOngoingTransactionsDisplay() {
   jTextArea3.setText("");
}

public void appendOngoingTransactionsDisplay(String text) {
  jTextArea3.append(text);
}

  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    jButton1.setBounds(new Rectangle(15, 35, 58, 24));
    jButton1.setSelected(false);
    jButton1.setText("Off");
    jButton1.addActionListener(new SipproxyGUI_jButton1_actionAdapter(this));
    contentPane.setLayout(null);
    this.setSize(new Dimension(587, 367));
    this.setTitle("Internet Multimedia Communications Using SIP. Sipproxy");
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
    jLabel4.setText("Proxy port");
    jLabel4.setBounds(new Rectangle(93, 12, 58, 19));
    jTextField4.setToolTipText("");
    jTextField4.setText("5061");
    jTextField4.setBounds(new Rectangle(157, 12, 50, 23));
    jButton2.setBounds(new Rectangle(14, 6, 59, 25));
    jButton2.setText("On");
    jButton2.addActionListener(new SipproxyGUI_jButton2_actionAdapter(this));
    jTextArea1.setFont(new java.awt.Font("Arial", 0, 10));
    jTextArea1.setColumns(0);
    jLabel3.setText("Record-Route");
    jLabel3.setBounds(new Rectangle(94, 41, 81, 18));
    jCheckBox1.setText("jCheckBox1");
    jCheckBox1.setBounds(new Rectangle(175, 42, 20, 18));
    jCheckBox1.addActionListener(new SipproxyGUI_jCheckBox1_actionAdapter(this));
    jTextArea2.setFont(new java.awt.Font("Arial", 0, 10));
    jTextArea2.setText("");
    jTextArea3.setText("");
    jPanel1.setLayout(null);
    jPanel2.setLayout(null);
    jPanel3.setLayout(null);
    jPanel3.setDebugGraphicsOptions(0);
    jLabel5.setForeground(Color.red);
    jLabel5.setHorizontalAlignment(SwingConstants.LEADING);
    jLabel5.setText("");
    jLabel5.setBounds(new Rectangle(272, 49, 244, 25));
    jTabbedPane1.setFont(new java.awt.Font("MS Sans Serif", 0, 11));
    jTabbedPane1.setBounds(new Rectangle(12, 68, 563, 262));
    jLabel1.setText("Copyright 2007 Rogelio Martinez Perea");
    jLabel1.setBounds(new Rectangle(383, 5, 195, 17));
    jLabel2.setText("Home Domain");
    jLabel2.setBounds(new Rectangle(209, 14, 100, 18));
    jTextField1.setText("ocean.com");
    jTextField1.setBounds(new Rectangle(309, 16, 88, 18));
    jLabel6.setText("Server Transaction");
    jLabel6.setBounds(new Rectangle(73, 0, 159, 13));
    jLabel7.setText("Client Transaction");
    jLabel7.setBounds(new Rectangle(363, 0, 116, 13));
    jScrollPane2.setBounds(new Rectangle(282, 16, 273, 217));
    jScrollPane1.setBounds(new Rectangle(0, 16, 277, 217));
    jScrollPane4.setBounds(new Rectangle(13, 10, 529, 215));
    jScrollPane3.setBounds(new Rectangle(10, 9, 539, 220));
    contentPane.add(jTabbedPane1, null);
    jTabbedPane1.add(jPanel1,  "Tracer");
    jPanel1.add(jScrollPane1, null);
    jPanel1.add(jScrollPane2, null);
    jPanel1.add(jLabel6, null);
    jPanel1.add(jLabel7, null);
    jScrollPane2.getViewport().add(jTextArea2, null);
    jScrollPane1.getViewport().add(jTextArea1, null);
    jTabbedPane1.add(jPanel2,  "LocationService");
    jPanel2.add(jScrollPane4, null);
    jTabbedPane1.add(jPanel3,  "Transactions");
    jPanel3.add(jScrollPane3, null);
    jScrollPane3.getViewport().add(jTextArea3, null);
    jScrollPane4.getViewport().add(jTextArea4, null);
    contentPane.add(jLabel3, null);
    contentPane.add(jTextField4, null);
    contentPane.add(jLabel4, null);
    contentPane.add(jButton2, null);
    contentPane.add(jButton1, null);
    contentPane.add(jCheckBox1, null);
    contentPane.add(jLabel2, null);
    contentPane.add(jTextField1, null);
    contentPane.add(jLabel5, null);
    contentPane.add(jLabel1, null);
    jTabbedPane1.setSelectedComponent(jPanel1);


  }

  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }


  void jButton2_actionPerformed(ActionEvent e) {
    try{
      String t = jTextField4.getText();
      list = new SipproxyListener(jTextField1.getText(),Integer.parseInt(t),this,jCheckBox1.isSelected());
    }catch (Exception exc){

    }
  }

  void jCheckBox1_actionPerformed(ActionEvent e) {
    if (list!=null){
      if (jCheckBox1.isSelected()) list.setRR(true);
      else list.setRR(false);
    }
  }

  void jButton1_actionPerformed(ActionEvent e) {
    list.setOff();
    list=null;
    jLabel5.setText("");
  }

}

class SipproxyGUI_jButton2_actionAdapter implements java.awt.event.ActionListener {
  SipproxyGUI adaptee;

  SipproxyGUI_jButton2_actionAdapter(SipproxyGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class SipproxyGUI_jCheckBox1_actionAdapter implements java.awt.event.ActionListener {
  SipproxyGUI adaptee;

  SipproxyGUI_jCheckBox1_actionAdapter(SipproxyGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCheckBox1_actionPerformed(e);
  }
}

class SipproxyGUI_jButton1_actionAdapter implements java.awt.event.ActionListener {
  SipproxyGUI adaptee;

  SipproxyGUI_jButton1_actionAdapter(SipproxyGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}
