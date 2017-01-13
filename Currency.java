import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class Currency {
    
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JPanel controlPanel;

   public Currency(){
      prepareGUI();
   }

   public static void main(String[] args){
      Currency  Currency = new Currency();      
      Currency.showTextFieldDemo();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("Currency Converter");
      mainFrame.setSize(400,350);
      mainFrame.setLayout(new GridLayout(3, 1));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
     
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.setVisible(true);  
   }

   private void showTextFieldDemo(){
      headerLabel.setText("Enter Desired Currency Value in One to check all"); 
      JLabel  la = new JLabel("Rupees: ", JLabel.CENTER);
      final JTextField val = new JTextField(6);
      JLabel  la4= new JLabel("Yen: ", JLabel.RIGHT);
      JLabel  la1 = new JLabel("Dollar: ", JLabel.CENTER);
      final JTextField val4 = new JTextField(6);
      final JTextField val1 = new JTextField(6);      
      JLabel  la2= new JLabel("Pounds: ", JLabel.RIGHT);
      JLabel  la3 = new JLabel("Euro: ", JLabel.CENTER);
      final JTextField val2 = new JTextField(6);
      final JTextField val3 = new JTextField(6);
      JLabel  la5 = new JLabel("Kuwaiti Dinar: ", JLabel.CENTER);
      final JTextField val5 = new JTextField(6);
      ImageIcon n=new ImageIcon("D:\\KND\\ACADEMICS\\Cse CCVT 5Sem\\Java Programming\\Java Lab\\Java_Programes\\Java_Progs\\src\\b.png");
      JButton  convert= new JButton("Convert",n);
      
      convert.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {   
        	String data = "Rupees: " + val.getText();
            double n=Double.valueOf(val.getText());
            n*=0.015; 
            val1.setText(String.valueOf(String.format("%.2f", n))); 
            double n1=Double.valueOf(val.getText());
            n1*=0.012;
            val2.setText(String.valueOf(String.format("%.2f", n1))); 
            double n2=Double.valueOf(val.getText());
            n2*=0.014; 
            val3.setText(String.valueOf(String.format("%.2f", n2))); 
            double n3=Double.valueOf(val.getText());
            n3*=1.55; 
            val4.setText(String.valueOf(String.format("%.2f", n3))); 
            double n4=Double.valueOf(val.getText());
            n4*=0.0045; 
            val5.setText(String.valueOf(String.format("%.2f", n4))); 
               }
      });

      controlPanel.add(la);
      controlPanel.add(val);
      controlPanel.add(la1);       
      controlPanel.add(val1);
      controlPanel.add(la2);       
      controlPanel.add(val2);
      controlPanel.add(la3);       
      controlPanel.add(val3);
      controlPanel.add(la4);       
      controlPanel.add(val4);
      controlPanel.add(la5);       
      controlPanel.add(val5);
      controlPanel.add(convert);
      mainFrame.setVisible(true);  
   }
}