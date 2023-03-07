import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 3000);
        OutputStream outputStream = socket.getOutputStream();
        String outputMessage = "Hello from the client!";
        outputStream.write(outputMessage.getBytes());

        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[100];
        int count = inputStream.read(buffer);

        System.out.println(new String(buffer, 0, count));

    }
}
