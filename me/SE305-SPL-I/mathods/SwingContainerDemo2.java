package mathods;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingContainerDemo2 {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JLabel msglabel;

   public SwingContainerDemo2(){
      prepareGUI();
   }

   public static void main(String[] args){
      SwingContainerDemo2  swingContainerDemo = new SwingContainerDemo2();  
      swingContainerDemo.showJFrameDemo();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("Java Swing Examples");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3,1));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    

     // statusLabel.setSize(50,50);

      msglabel = new JLabel("Welcome to TutorialsPoint SWING Tutorial.", JLabel.CENTER);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }

   private void showJFrameDemo(){
      headerLabel.setText("Container in action: JFrame");   

      final JFrame frame = new JFrame();
      frame.setSize(300, 300);
      frame.setLayout(new FlowLayout());       
      frame.add(msglabel);
      frame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            frame.dispose();
         }        
      });    
      JButton okButton = new JButton("Open a Frame");
      okButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            statusLabel.setText("A Frame shown to the user.");
            frame.setVisible(true);
         }
      });
      controlPanel.add(okButton);
      mainFrame.setVisible(true);  
   }
}