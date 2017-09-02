import java.lang.Thread;
import java.net.Socket;

public class UserThread extends Thread{
    private Socket socket;

    public UserThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
    }
}
