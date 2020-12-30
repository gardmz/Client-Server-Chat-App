package testing;

import java.net.*;
import java.io.*;

public class SimpleServer {

	private ServerSocket listenSocket = null;
	private Socket connectSocket = null;
	private DataInputStream in = null;
	File outputFile = new File("C:\\Users\\Earls lips\\eclipse-workspace\\DataComm\\src\\testing\\outputTest.txt");
	OutputWriter outp = new OutputWriter(outputFile);

	public Socket getConnectSocket() {
		return connectSocket;
	}

	public SimpleServer(int portNo) throws IOException {
		try {
			listenSocket = new ServerSocket(portNo);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			connectSocket = listenSocket.accept();
			System.out.println("Client accepted");

			in = new DataInputStream(new BufferedInputStream(connectSocket.getInputStream()));
			
			System.out.println("Contents are being written to a new file....");

			String line = in.readUTF();
			while (line != null && line.length() > 0) {
				outp.writeOutput(line);
				line = in.readUTF();
			}
			System.out.println("Closing connection");
			in.close();
			connectSocket.close();

		} catch (EOFException e) {
			System.out.println("Closing connection in eof");
			in.close();
			connectSocket.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) throws IOException {

		int portNo;
		File outputFile;
		File dir = new File(".");
		
		if (args.length > 0) {
			portNo = Integer.parseInt(args[0]);
		} else {
			portNo = 10000;
		}
		if (args.length == 2) {
			outputFile = new File(args[1]);
		} else {
			outputFile = new File(dir.getCanonicalPath() + File.separator + "DataComm/testing/testOutput.txt");
		}
				
		SimpleServer testServer = new SimpleServer(7564);
	}
	
}
