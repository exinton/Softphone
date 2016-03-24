package example5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Example_5GUI extends JFrame {
  JPanel contentPane;
  JButton jButton1 = new JButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField4 = new JTextField();
  Example_5Listener list;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JButton jButton2 = new JButton();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JButton jButton3 = new JButton();
  JLabel jLabel7 = new JLabel();
  JButton jButton4 = new JButton();

  public Example_5GUI() {
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
  jLabel7.setText(text);
}

  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    jButton1.setBounds(new Rectangle(3, 164, 62, 36));
    jButton1.setText("Initiate");
    jButton1.addActionListener(new Example2GUI_jButton1_actionAdapter(this));
    contentPane.setLayout(null);
    this.setResizable(false);
    this.setSize(new Dimension(512, 270));
    this.setTitle("Internet Multimedia Communications using SIP. Chapter 8");
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
    jLabel1.setText("AOR:");
    jLabel1.setBounds(new Rectangle(14, 110, 67, 15));
    jLabel3.setText("destination:");
    jLabel3.setBounds(new Rectangle(14, 141, 63, 15));
    jTextField1.setMaximumSize(new Dimension(112, 20));
    jTextField1.setMinimumSize(new Dimension(112, 20));
    jTextField1.setPreferredSize(new Dimension(112, 20));
    jTextField1.setMargin(new Insets(1, 5, 2, 4));
    jTextField1.setSelectionEnd(18);
    jTextField1.setText("sip:john@ocean.com");
    jTextField1.setBounds(new Rectangle(81, 104, 135, 21));
    jTextField3.setText("sip:127.0.0.1:5062");
    jTextField3.setBounds(new Rectangle(81, 138, 134, 20));
    jLabel4.setText("port:");
    jLabel4.setBounds(new Rectangle(99, 35, 30, 19));
    jTextField4.setMaximumSize(new Dimension(35, 20));
    jTextField4.setMinimumSize(new Dimension(35, 20));
    jTextField4.setPreferredSize(new Dimension(35, 20));
    jTextField4.setToolTipText("");
    jTextField4.setMargin(new Insets(1, 5, 2, 4));
    jTextField4.setText("5060");
    jTextField4.setBounds(new Rectangle(129, 37, 80, 23));
    jButton2.setBounds(new Rectangle(10, 31, 54, 35));
    jButton2.setText("On");
    jButton2.addActionListener(new Example2GUI_jButton2_actionAdapter(this));
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 16));
    jLabel2.setForeground(Color.blue);
    jLabel2.setText("Example 5");
    jLabel2.setBounds(new Rectangle(266, 3, 104, 28));
    jLabel6.setToolTipText("");
    jLabel6.setText("Copyright 2007 Rogelio Martinez Perea");
    jLabel6.setBounds(new Rectangle(9, 200, 215, 24));
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel5.setMaximumSize(new Dimension(150, 5));
    jLabel5.setMinimumSize(new Dimension(150, 5));
    jLabel5.setPreferredSize(new Dimension(150, 5));
    jLabel5.setBounds(new Rectangle(3, 66, 213, 20));
    jButton3.setBounds(new Rectangle(65, 163, 67, 37));
    jButton3.setText("Accept");
    jButton3.addActionListener(new Example_5GUI_jButton3_actionAdapter(this));
    jLabel7.setForeground(Color.red);
    jLabel7.setMaximumSize(new Dimension(150, 5));
    jLabel7.setMinimumSize(new Dimension(150, 5));
    jLabel7.setPreferredSize(new Dimension(150, 5));
    jLabel7.setVerifyInputWhenFocusTarget(true);
    jLabel7.setText("");
    jLabel7.setBounds(new Rectangle(13, 86, 196, 18));
    jButton4.setBounds(new Rectangle(132, 164, 90, 36));
    jButton4.setText("Terminate");
    jButton4.addActionListener(new Example_5GUI_jButton4_actionAdapter(this));
    jScrollPane1.setBounds(new Rectangle(235, 37, 268, 187));
    contentPane.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(jTextArea1, null);
    contentPane.add(jButton2, null);
    contentPane.add(jLabel4, null);
    contentPane.add(jTextField4, null);
    contentPane.add(jLabel6, null);
    contentPane.add(jTextField3, null);
    contentPane.add(jLabel1, null);
    contentPane.add(jTextField1, null);
    contentPane.add(jLabel3, null);
    contentPane.add(jLabel5, null);
    contentPane.add(jLabel7, null);
    contentPane.add(jButton1, null);
    contentPane.add(jButton4, null);
    contentPane.add(jButton3, null);
    contentPane.add(jLabel2, null);


  }

  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  void jButton1_actionPerformed(ActionEvent e)  {
    try {
      list.userInput(0,jTextField3.getText(), jTextField1.getText());
    } catch (Exception ex){

    }
  }

  void jButton2_actionPerformed(ActionEvent e) {
    try{
      String t = jTextField4.getText();
      list = new Example_5Listener(Integer.parseInt(t), this);
    }catch (Exception exc){

    }
  }

  void jButton3_actionPerformed(ActionEvent e) {
    list.userInput(1,null, null);
  }

  void jButton4_actionPerformed(ActionEvent e) {
    list.userInput(2,null, null);
  }

}

class Example2GUI_jButton1_actionAdapter implements java.awt.event.ActionListener {
  Example_5GUI adaptee;

  Example2GUI_jButton1_actionAdapter(Example_5GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class Example2GUI_jButton2_actionAdapter implements java.awt.event.ActionListener {
  Example_5GUI adaptee;

  Example2GUI_jButton2_actionAdapter(Example_5GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class Example_5GUI_jButton3_actionAdapter implements java.awt.event.ActionListener {
  Example_5GUI adaptee;

  Example_5GUI_jButton3_actionAdapter(Example_5GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class Example_5GUI_jButton4_actionAdapter implements java.awt.event.ActionListener {
  Example_5GUI adaptee;

  Example_5GUI_jButton4_actionAdapter(Example_5GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}