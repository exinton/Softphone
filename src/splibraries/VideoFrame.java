package splibraries;

import javax.swing.*;
import java.awt.*;

public class VideoFrame extends JFrame {
  JPanel jPanel1 = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();

  public VideoFrame() {
    try {
      this.setTitle("Remote video");
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    this.getContentPane().setLayout(flowLayout1);
    jPanel1.setLayout(flowLayout2);
    this.getContentPane().add(jPanel1, null);
  }
}

