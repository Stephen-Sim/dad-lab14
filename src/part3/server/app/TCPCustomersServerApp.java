package part3.server.app;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import part3.model.Customer;
import part3.server.controller.CustomerController;

public class TCPCustomersServerApp {
public static void main(String[] args) {
		
		int portNo = 8088;
		
		CustomerController cusController = new CustomerController();
		
		System.out.println("\n\tExecuting TCPProductsServerApp");
		
		try {
			
			System.out.println("\tWaiting for next request");
			
			// 1. Bind to a port
			ServerSocket serverSocket = new ServerSocket(portNo); 
			
			// 2. Server need to continually run to listen to request
			while (true) {
				
				// 3. Accept request from client
				Socket clientSocket = serverSocket.accept();
								
				// Get product
				List<Customer> customers = cusController.getCustomerList();
				
				// 4. Respond to client
				OutputStream os  = clientSocket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(customers);
				System.out.println("\tSending : " + customers.size() 
					+ " customers");
				
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}

	}
}
