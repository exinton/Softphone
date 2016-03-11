package example2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Example2GUI extends JFrame {
  JPanel contentPane;
  JButton jButton1 = new JButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField4 = new JTextField();
  example2Listener list;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JButton jButton2 = new JButton();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel6 = new JLabel();


  public Example2GUI() {
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
  //Component initialization
  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    jButton1.setBounds(new Rectangle(43, 183, 80, 43));
    jButton1.setText("Send");
    jButton1.addActionListener(new Example2GUI_jButton1_actionAdapter(this));
    contentPane.setLayout(null);
    this.setResizable(false);
    this.setSize(new Dimension(540, 307));
    this.setTitle("Internet Multimedia Communications Using SIP. Chapter 8");
    contentPane.setBackground(SystemColor.control);
    contentPane.setEnabled(false);
    contentPane.setForeground(Color.black);
    contentPane.setAlignmentY((float) 0.5);
    contentPane.setDebugGraphicsOptions(0);
    contentPane.setDoubleBuffered(true);
    contentPane.setMaximumSize(new Dimension(380, 244));
    contentPane.setMinimumSize(new Dimension(380, 244));
    contentPane.setOpaque(true);
    contentPane.setPreferredSize(new Dimension(380, 244));
    contentPane.setRequestFocusEnabled(true);
    jLabel1.setText("AOR:");
    jLabel1.setBounds(new Rectangle(12, 111, 29, 15));
    jLabel3.setText("registrar:");
    jLabel3.setBounds(new Rectangle(10, 149, 48, 15));
    jTextField1.setMaximumSize(new Dimension(112, 20));
    jTextField1.setPreferredSize(new Dimension(112, 20));
    jTextField1.setSelectionEnd(18);
    jTextField1.setText("sip:john@ocean.com");
    jTextField1.setHorizontalAlignment(SwingConstants.LEADING);
    jTextField1.setScrollOffset(0);
    jTextField1.setBounds(new Rectangle(65, 110, 132, 21));
    jTextField3.setText("sip:127.0.0.1:5062");
    jTextField3.setBounds(new Rectangle(65, 145, 131, 20));
    jLabel4.setText("own port:");
    jLabel4.setBounds(new Rectangle(71, 31, 49, 19));
    jTextField4.setToolTipText("");
    jTextField4.setText("5060");
    jTextField4.setBounds(new Rectangle(142, 31, 43, 23));
    jButton2.setBounds(new Rectangle(11, 31, 54, 35));
    jButton2.setText("On");
    jButton2.addActionListener(new Example2GUI_jButton2_actionAdapter(this));
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 16));
    jLabel2.setForeground(Color.blue);
    jLabel2.setText("Example 2");
    jLabel2.setBounds(new Rectangle(340, 3, 111, 28));
    jLabel6.setToolTipText("");
    jLabel6.setText("Copyright 2007 Rogelio Martinez Perea");
    jLabel6.setBounds(new Rectangle(6, 236, 214, 22));
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 9));
    jLabel5.setMaximumSize(new Dimension(100, 10));
    jLabel5.setMinimumSize(new Dimension(100, 10));
    jLabel5.setOpaque(false);
    jLabel5.setPreferredSize(new Dimension(100, 10));
    jLabel5.setBounds(new Rectangle(11, 66, 201, 30));
    jScrollPane1.setMaximumSize(new Dimension(23, 23));
    jScrollPane1.setMinimumSize(new Dimension(23, 23));
    jScrollPane1.setPreferredSize(new Dimension(23, 23));
    jScrollPane1.setBounds(new Rectangle(210, 37, 305, 218));
    contentPane.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(jTextArea1, null);
    contentPane.add(jButton2, null);
    contentPane.add(jTextField3, null);
    contentPane.add(jLabel4, null);
    contentPane.add(jLabel1, null);
    contentPane.add(jLabel2, null);
    contentPane.add(jLabel5, null);
    contentPane.add(jTextField4, null);
    contentPane.add(jButton1, null);
    contentPane.add(jLabel3, null);
    contentPane.add(jTextField1, null);
    contentPane.add(jLabel6, null);


  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  void jButton1_actionPerformed(ActionEvent e)  {
    try {
      list.userInput(jTextField3.getText(), jTextField1.getText());
    } catch (Exception ex){

    }
  }

  void jButton2_actionPerformed(ActionEvent e) {
    try{
      String t = jTextField4.getText();
      list = new example2Listener(Integer.parseInt(t), this);
    }catch (Exception exc){

    }
  }

}

class Example2GUI_jButton1_actionAdapter implements java.awt.event.ActionListener {
  Example2GUI adaptee;

  Example2GUI_jButton1_actionAdapter(Example2GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class Example2GUI_jButton2_actionAdapter implements java.awt.event.ActionListener {
  Example2GUI adaptee;

  Example2GUI_jButton2_actionAdapter(Example2GUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}
