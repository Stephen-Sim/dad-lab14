package part2.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;

import part2.server.serverside.SentenceProcessor;

/**
 * An example of server-Side application using UDP
 * 
 * How to run this application
 * 1. Open terminal
 * 2. Change to directory to /workspace-dad/udpdemo/bin
 * 3. java UDPServerSideApp
 * 4. Initial output: 
 * 	UDPServerSideApp: Demonstration of UDP Server-Side Application.
 * 5. Final output: 
 * 	Message received: Good morning.
 * 
 * 
 * @author emalianakasmuri
 *
 */

public class UDPServerSideApp {

	public static void main(String[] args) {
		
		System.out.println("UDPServerSideApp: Demonstration of UDP Server-Side "
				+ "Application.");
		
		
		// Permissible port for this application
		int portNo = 8083;
        
        try {
        	
    		// 1. Bind a DatagramSocket's object to a port number
            DatagramSocket datagramSocket = new DatagramSocket(portNo);
        	
            // Continually listen for request
        	while (true) {
                
                // 2. Variable to received data from the port
        		// 65535 is the maximum size for UDP packet
                byte[] receivedData = new byte[65535];
          
                // 3. Object represents packet from client
                DatagramPacket receivedPacket = 
                		new DatagramPacket(receivedData, receivedData.length);
                
                // 4. Receive packet
				datagramSocket.receive(receivedPacket);
				
				// 5. Extract data from packet
				receivedData = receivedPacket.getData();
				
				// 6. Further processing
				SentenceProcessor processor = 
						new SentenceProcessor(receivedData);
				String sentence = processor.getSentence();
				System.out.println("\nMessage received: " + sentence + ".\n");
				
				// This is not used because it give a misleading result
	            // int length = sentence.length();
	            
	            // More processing
	            int totalCharacters =  processor.countCharacters();
	            byte[] outData1 = processor.convertToByteArray(totalCharacters);
	            
	            
	            int totalVowels = processor.countVowels();
	            byte[] outData2 = processor.convertToByteArray(totalVowels);
	            
	            int totalConsonant = processor.countConsonants();
	            byte[] outData3 = processor.convertToByteArray(totalConsonant);
	            
	            int totalPunctuation = processor.countPunctuations();
	            byte[] outData4 = processor.convertToByteArray(totalPunctuation);
	            
	            // 7. Get the client information
	            InetAddress clientAddress =  receivedPacket.getAddress();
	            int clientPort = receivedPacket.getPort();
	            
	            // 8. Wrap data into datagram packet
	            DatagramPacket outPacket1 = new DatagramPacket(outData1, 
	            		outData1.length, clientAddress, clientPort);
	            
	            DatagramPacket outPacket2 = new DatagramPacket(outData2, 
	            		outData2.length, clientAddress, clientPort);
	            
	            DatagramPacket outPacket3 = new DatagramPacket(outData3, 
	            		outData3.length, clientAddress, clientPort);
	            
	            DatagramPacket outPacket4 = new DatagramPacket(outData4, 
	            		outData4.length, clientAddress, clientPort);
	            
	            // 9. Reply to client
	            datagramSocket.send(outPacket1);
	            datagramSocket.send(outPacket2);
	            datagramSocket.send(outPacket3);
	            datagramSocket.send(outPacket4);
	            
	            System.out.println("Message sent (total Characters) : " 
	            		+ totalCharacters  + ".\n");
	            System.out.println("Message sent (total vowels) : " 
	            		+ totalVowels  + ".\n");
	            System.out.println("Message sent (total Consonants) : " 
	            		+ totalConsonant  + ".\n");
	            System.out.println("Message sent (total Punctuations) : " 
	            		+ totalPunctuation  + ".\n");
	            
        	}
				
		} catch (IOException e) {
				
			e.printStackTrace();
        }
        
        System.out.println("UDPClientSideApp: End of program.");
    }
}
