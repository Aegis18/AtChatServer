import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class AtChatServer {

    public static void main(String[] Args){
        final int PORT = 18888;
        Selector selector;
        ServerSocketChannel serverSocket;
        ByteBuffer buffer;
        Charset charset = Charset.forName("UTF-8");
        try{
            selector = Selector.open();
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress("localhost", PORT));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            buffer = ByteBuffer.allocate(2000);
            ChatProtocol chat = new ChatProtocol();
            //DataBaseManager dbm = new DataBaseManager();
            //String[] s =dbm.collectData("mendel");
            //System.out.println(s[0] + " " + s[1]);
            while(true){
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator iter = selectedKeys.iterator();
                while(iter.hasNext()){
                    SelectionKey key =  (SelectionKey)iter.next();

                    if(key.isAcceptable()){
                        SocketChannel client = serverSocket.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                        System.out.println("isAcceptable");
                    }

                    if(key.isReadable()){
                        String response;
                        SocketChannel client = (SocketChannel)key.channel();
                        System.out.println("Read Started");
                        if(client.read(buffer) == -1) {
                            client.close();
                        }
                        response = new String(buffer.array()).trim();
                        buffer.clear();
                        chat.ProccessInput(response);
                    }
                    iter.remove();
                }
            }
        }catch(IOException e){
        }
//        ServerSocket serverSocket = null;
//        Socket socket = null;
//        try{
//            serverSocket = new ServerSocket(PORT);
//        }catch(IOException e) {
//            e.printStackTrace();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        while(true){
//            try{
//                socket = serverSocket.accept();
//            }catch(IOException e) {
//            }
//            new UserThread(socket).start();
//        }
    }
}
