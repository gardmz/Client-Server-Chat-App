package simpleServerAndClient;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.util.List;


public class SimpleClient {
	String serverAddress;
	int portNo;
	Socket socket = null;
    private DataOutputStream out = null;     
    static List<String> lines;
  
	public SimpleClient(String serverAddress, int portNo) {
		
		try
        { 
            socket = new Socket(serverAddress, portNo); 
            System.out.println("Connected"); 
            
            out = new DataOutputStream(socket.getOutputStream()); 
           
            for (int i = 0; i < lines.size();i++) { 		      
            	out.writeUTF(lines.get(i));
  	      	}   
            
            out.close(); 
            socket.close(); 
            
        } 
        catch(Exception u) 
        { 
            System.out.println(u); 
        } 
	}
	public static void main(String[] args) throws IOException {
		File inputFile;
		File dir = new File(".");

		String serverIPAddr;
		int portNo;
		
		SimpleClient client;
		
		if (args.length > 2) {
			serverIPAddr = args[0];
			portNo = Integer.parseInt(args[1]);
			inputFile = new File(args[2]);
		} else {
			serverIPAddr = "127.0.0.1";
			portNo = 10000;
			inputFile = new File(dir.getCanonicalPath() + File.separator + "Data Comm and Networking/simpleServerAndClient/testInput.txt");
		}

		lines = Files.readAllLines(inputFile.toPath());
		
		client = new SimpleClient("10.0.0.194", 7564);

		
	}
}

