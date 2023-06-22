package part3.server.app;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import part3.model.Customer;
import part3.server.controller.CustomerController;

public class TCPCustomerServerApp {
	public static void main(String[] args) {

        int portNo = 8088;

        CustomerController cusController = new CustomerController();

        System.out.println("\n\tExecuting TCPCustomerServerApp");

        try {

            System.out.println("\tWaiting for next request");

            // 1. Bind to a port
            ServerSocket serverSocket = new ServerSocket(portNo);

            // 2. Server needs to continually run to listen to requests
            while (true) {
                // 3. Accept client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("\tAccepted connection from client: " + 
                		clientSocket.getInetAddress());

                // 4. Read client data
                InputStream inputStream = clientSocket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                String customerName = dataInputStream.readUTF();
                System.out.println("\tReceived customer name from client: " + customerName);

                // 5. Process data and find the customer
                Customer customer = cusController.searchCustomerByName(customerName);

                // 6. Send customer object back to the client
                OutputStream outputStream = clientSocket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(customer);
                System.out.println("\tSending : " + customer.getCustomerId() 
				+ " " + customer.getCustomerName());

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}
