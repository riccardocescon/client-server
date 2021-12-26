import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String serverIp = "188.228.195.100";
        int port = 24680;

        try (Socket connection = new Socket(serverIp, port)) {
            System.out.println("Connected to server!");
            doThingswithConnection(connection);
        } catch (IOException e) {
            System.out.println("Failed to connect! Error : " + e.getMessage());
        }
    }

    private static void doThingswithConnection(Socket connection){
        // Start sending data to the server
        boolean sent = sendDataToServer(connection, "The client is sending this message");
        if(!sent){
            // Handle error. You may try sending it 3 times, and if it fails then close the connection
        }
    }

    private static boolean sendDataToServer(Socket socket, String message){
        try {
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            dOut.writeUTF(message);
            dOut.flush();
            dOut.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
    

    /*
    
    String serverIp = "188.228.195.100";
    
}
*/