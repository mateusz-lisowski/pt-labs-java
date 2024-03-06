package lab3.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (DataInputStream input = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {

            while (true) {
                // Read incoming data
                int command = input.readInt();

                // Process incoming data based on the defined protocol
                if (command == ServerProtocol.COMMAND_HELLO) {
                    System.out.println("Received HELLO command from client: " + clientSocket.getInetAddress());
                    // Send response
                    output.writeInt(ServerProtocol.RESPONSE_HELLO);
                    output.flush();
                } else if (command == ServerProtocol.COMMAND_GOODBYE) {
                    System.out.println("Received GOODBYE command from client: " + clientSocket.getInetAddress());
                    // Send response
                    output.writeInt(ServerProtocol.RESPONSE_GOODBYE);
                    output.flush();
                    // Close connection
                    clientSocket.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
