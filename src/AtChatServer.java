import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class AtChatServer {

    public static void main(String[] Args){
        final int PORT = 8888;
        ServerSocket serverSocket = null;
        Socket socket = null;
        try{
            serverSocket = new ServerSocket(PORT);
        }catch(IOException e) {
            e.printStackTrace();
        }
        while(true){
            try{
                socket = serverSocket.accept();
            }catch(IOException e){
                e.printStackTrace();
            }
            new UserThread(socket).start();
        }
    }
}
