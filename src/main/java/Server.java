import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

public class Server {
    public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(3000);
            while(true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[100];
            int count = inputStream.read(buffer);
            String inputMessage = new String(buffer, 0, count);

            String outputMessage = "echo: " + inputMessage;
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(outputMessage.getBytes());
             }
    }
}
