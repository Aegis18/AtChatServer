import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.Thread;
import java.net.Socket;
import java.sql.*;

public class UserThread extends Thread{
    private Socket socket;
    private BufferedReader reader;
    private Messanger messanger;
    private ChatProtocol chat;

    public UserThread(Socket socket){
        this.socket = socket;
        messanger = new Messanger(socket);
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException e) {
            e.printStackTrace();
        }
        chat = new ChatProtocol();
    }

    @Override
    public void run(){
//        Manager man = new Manager();
//        if(man.checkForUser("rangeroverkill@gmail.com"))
//            System.out.println("person found");
//        else
//            System.out.println("no one was found");
        while(true){
            String inputLine = "";
            try {
                while ((inputLine = reader.readLine()) != null) {
                    chat.ProccessInput("/000/ "+ inputLine);
                }
            }catch(IOException e){
            }
        }
    }
}
