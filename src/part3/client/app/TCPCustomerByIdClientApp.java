package part3.client.app;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import part3.model.Customer;

public class TCPCustomerByIdClientApp {

public static void main (String[] args) {
		
		try {
			
			System.out.println("\tExecuting TCPCustomerClientApp");
		
			// Server information
			int serverPortNo = 8088;
			InetAddress serverAddress = InetAddress.getLocalHost();
			
			// 1. Connect to remote machine
			Socket socket = new Socket(serverAddress, serverPortNo);
			
			// Create stream to send request
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			// 2. Send request to the server
			int customerId = 1;
			dos.writeInt(customerId);
			System.out.println("\tRequesting customer id " + customerId + "\n");
			
			// Create stream to receive response from the server
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
			
			// 3. Read respond from the server - cast object
			Customer customer = (Customer) ois.readObject();
			
			// 4. Process response - display the object
			System.out.println("\tCustomer Information (From the server)");
			System.out.println("\tCustomer Id: " + customer.getCustomerId());
			System.out.println("\tName: " + customer.getCustomerName());
			
			
		} catch (Exception ex) {
			
			
			
		}
	}
}
