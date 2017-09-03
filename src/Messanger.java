import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Messanger {
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;
    public Messanger(Socket socket){
        this.socket = socket;
        try {
            writer = new PrintWriter(socket.getOutputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void message(String message){
        writer.println(message);
    }
}
