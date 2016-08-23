package guess_animal_game;
import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import net.sf.json.JSONObject;


public class Server extends JFrame{
       
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel p = new JPanel(); 
//************main************************//
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     new Server();
	}
   //***************************************//
public Server(){
   //******configure Frame and Panel*********//
	JTextArea jta = new JTextArea(5,10);
    getContentPane().add(jta, BorderLayout.CENTER); 
    getContentPane().setLayout(new BorderLayout());  //default is FlowLayout
    getContentPane().add(p, BorderLayout.SOUTH);  
    getContentPane().add(new JScrollPane(jta), BorderLayout.CENTER);  
    
    
    setTitle("Server_End");  
    setSize(500, 200);  
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    setVisible(true);  
//****use array to store Questions and Answers*****//   
    String[] AnswerArray1 = {"It is Seagull","It is eagle"};  
    String[] AnswerArray2 = {"It is astronaut","It is monkey"};  
    String[] AnswerArray3 = {"It is Number 3","It is chicken"};  
    String[] AnswerArray4 = {"It is dinosaur","It could be anything"};  
    String[] QuesArray1 = {"Does it eat fish?","Does it have long legs?"};  
    String[] QuesArray2 = {"Is it a Number?","Is it extinct?"};  
    String[] QuesArray3 = {"Does it have feathers?","Does it have fur?"};  
    //***initialize variables and objects used 
    int i=0,answercount=1;
    JSONObject json = new JSONObject();
	String str="";
	Boolean answer=true;
	
 
try {  
	// Create a server socket  
    @SuppressWarnings("resource")
	ServerSocket serverSocket = new ServerSocket(8000);  
    // Listen for a connection request  
    Socket socket = serverSocket.accept();  
    DataInputStream inputfromClient = new DataInputStream(socket.getInputStream());  
   //*******************************************
while(true){
	answer = inputfromClient.readBoolean();
  if(answer==true){
        if(answercount==1){
		    str=QuesArray3[0]; 
		   answercount++;
		   i=0;
		}
	   else if(answercount==2){
		   if(i==0){
			    str=QuesArray1[0]; 
			   answercount++;
			   i=2;
			}
		   else if(i==1){
			    str=QuesArray2[0]; 
			   answercount++;
			   i=4;
			}
	   }
	   else if(answercount==3){
  	   if(i==2){
  		    str=AnswerArray1[0];
  		   answercount=1;
  		}
  	   else if(i==3){
  		    str=AnswerArray2[0];
  		   answercount=1;
  		}
  	   else if(i==4){
  		    str=AnswerArray3[0];
  		  answercount=1;
  		}
  	   else if(i==5){
  		    str=AnswerArray4[0];
  		  answercount=1;
  		}
     }
	   
 }
  //********************************************//
 else if(answer==false){
	   if(answercount==1){
		   str=QuesArray3[1]; 
		   answercount++;
		  i=1;
		}
	   else if(answercount==2){
		   if(i==0){
			    str=QuesArray1[1]; 
			   answercount++;
			   i=3;
			}
		   else if(i==1){
			    str=QuesArray2[1]; 
			   answercount++;
			  i=5;
			}
	   }
	   else if(answercount==3){
   	   if(i==2){
   		    str=AnswerArray1[1];
   		 answercount=1;
   		}
   	   else if(i==3){
   		    str=AnswerArray2[1];
   		 answercount=1;
   		}
   	   else if(i==4){
   		    str=AnswerArray3[1];
   		 answercount=1;
   	}
   	   else if(i==5){
   		    str=AnswerArray4[1];
   		 answercount=1;
   		}
      }
 }	  
json.put("Q", str); 
OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8);
out.write(json.toString()+"\n");//  "\n" add newline otherwise client cannot get the string
out.flush();
}
}
catch(IOException ex) {  
	      System.err.println(ex);  
	    }  

}
}

 	       
 	     
 	



// serverSocket.close();

	   
	 



