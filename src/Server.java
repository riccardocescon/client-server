import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    // TODO: Challenge
    // Currently once the server connects with a client, it start waiting for the client
    // to send data inside [waitToReceiveMessage]
    // This is bad because if another client tries to connect to the server, the request
    // will timeout because the server is busy.
    //
    // To fix this, change the main, make sure the server is Always available to accept new requests
    // (hint : once the server accepts the request you should create a new thread to communicate with it)

    
    public static void main(String[] args) throws Exception {
        int port = 24680;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket connectionRequest = null;

            while(true){
                System.out.println("Waiting for connections...");
                connectionRequest = serverSocket.accept();
                doThingsWithConnection(connectionRequest);
            }
        }
    }

    private static void doThingsWithConnection(Socket client){
        System.out.println("Connected with : " + client.getInetAddress().getHostAddress());
        waitToReceiveMessage(client);
    }

    private static void waitToReceiveMessage(Socket socket){
        boolean received = false;
        while(!received){
            try {
                DataInputStream dIn = new DataInputStream(socket.getInputStream());
                String receivedMessage = dIn.readUTF();
                System.out.println("Client sent : " + receivedMessage);
                received = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}