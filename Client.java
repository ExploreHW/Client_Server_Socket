package guess_animal_game;  
  
  
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.*;

import net.sf.json.JSONObject;  
  
public class Client extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JButton button1 = new JButton("YES");
	JButton button2 = new JButton("NO");
	JPanel p = new JPanel(); 
	private DataOutputStream outputToServer;  
	private BufferedReader myreader;
	JTextArea jta = new JTextArea();
	int i=0;
	public void actionPerformed(ActionEvent e) {  
		       if (e.getSource() == button1){
		    	try {
				outputToServer.writeBoolean(true);
				outputToServer.flush();
				i++;
				
				//String nextQuestion = inputFromServer.readLine();
				 String nextQuestion = myreader.readLine();
				 JSONObject jb = JSONObject.fromObject(nextQuestion);
				 String next =  jb.getString("Q");
		        // Display to the text area  
				 
		        jta.append("Your Answer is Yes" + "\n");  
		        jta.append("Next : "+ next + '\n');  
		        if(i==3){
					 jta.append("Again : Can it fly ?" + "\n");  
					 i=0;
				 }
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
	            
		       }
	           else if (e.getSource() == button2)
	           {  
	        	   try {
				outputToServer.writeBoolean(false);
				outputToServer.flush();
				i++;
				
				//String nextQuestion = inputFromServer.readLine();
				 String nextQuestion = myreader.readLine();
				JSONObject jb = JSONObject.fromObject(nextQuestion);
				String next =  jb.getString("Q");
		        // Display to the text area  
		        jta.append("Your Answer is No" + "\n");  
		        jta.append("Next : "+ next + '\n');  
		        if(i==3){
					 jta.append("Again : Can it fly ?" + "\n");
					 i=0;
				 }
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
               }
               
               
		      }
	
  public static void main(String[] args) {  
    new Client();  
  }  
  
   Client() {  
    // Panel p to hold the label and text field  
    
   
   
    getContentPane().add(jta, BorderLayout.CENTER); 
    
    p.add(new JLabel("SELECT YES/NO"), BorderLayout.WEST);  
    
   
    
    p.add(button1, BorderLayout.WEST);
    p.add(button2, BorderLayout.WEST);
    button1.addActionListener(this);
    button2.addActionListener(this);
    jta.append("First Question : Can it fly ?" + "\n");  
    getContentPane().setLayout(new BorderLayout());  //default is FlowLayout
    getContentPane().add(p, BorderLayout.SOUTH);  
    getContentPane().add(new JScrollPane(jta), BorderLayout.CENTER);  
    
    
    setTitle("Client_End");  
    setSize(500, 200);  
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    setVisible(true);  
    
    try {
    	
    	@SuppressWarnings("resource")
		Socket socket = new Socket("localhost", 8000);
    	outputToServer = new DataOutputStream(socket.getOutputStream());
    	
    	InputStreamReader inputFromServer=new InputStreamReader(socket.getInputStream());
    	myreader =new BufferedReader(inputFromServer);
    	//inputFromServer.close();
    	//outputToServer.close();
    	//socket.close();
        }
    	catch (IOException ex) {  
    	      jta.append(ex.toString() + '\n');  
    	    }  
    	
    
  }  

}  

