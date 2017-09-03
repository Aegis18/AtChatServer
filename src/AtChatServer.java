import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.DriverManager;

public class AtChatServer {

    public static void main(String[] Args){
        final int PORT = 18888;
        ServerSocket serverSocket = null;
        Socket socket = null;
        try{
            serverSocket = new ServerSocket(PORT);
        }catch(IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        while(true){
            try{
                socket = serverSocket.accept();
            }catch(IOException e) {
            }
            new UserThread(socket).start();
        }
    }
}
