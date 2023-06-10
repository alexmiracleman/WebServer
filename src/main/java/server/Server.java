package server;


import io.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;



public class Server {
    private int port;
    private String webAppPath;

    public void setPort(int port) {
        this.port = port;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }
    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     InputStream inputStream = socket.getInputStream();
                     OutputStream outputStream = socket.getOutputStream()

                ) {
                    RequestHandler requestHandler = new RequestHandler(webAppPath, inputStream, outputStream);
                    requestHandler.handle();
                }
            }
        }
    }
}



