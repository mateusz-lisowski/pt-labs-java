package lab3.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        while (true) {
            try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                 DataInputStream input = new DataInputStream(socket.getInputStream());
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

                // Sending command to the server
                output.writeInt(ClientProtocol.COMMAND_HELLO);
                output.flush();

                // Waiting for response
                int response = input.readInt();
                if (response == ClientProtocol.RESPONSE_HELLO) {
                    System.out.println("Server responded with HELLO");
                } else {
                    System.out.println("Unexpected response from server");
                }

                // Sending another command to the server
                output.writeInt(ClientProtocol.COMMAND_GOODBYE);
                output.flush();

                // Waiting for response
                response = input.readInt();
                if (response == ClientProtocol.RESPONSE_GOODBYE) {
                    System.out.println("Server responded with GOODBYE");
                } else {
                    System.out.println("Unexpected response from server");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
