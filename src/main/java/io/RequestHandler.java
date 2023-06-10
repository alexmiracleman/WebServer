package io;

import domain.Request;
import exceptions.ServerException;

import java.io.*;
public class RequestHandler {
    private final String webAppPath;
    private final InputStream reader;
    private final OutputStream outputStream;

    public RequestHandler(String webAppPath, InputStream reader, OutputStream outputStream) {
        this.webAppPath = webAppPath;
        this.reader = reader;
        this.outputStream = outputStream;
    }
    public void handle() throws ServerException, IOException {
        try {
            Request request = RequestParser.parse(reader);
            String uri = request.getUri();

            ResourceReader resourceReader = new ResourceReader(webAppPath);
            try (InputStream content = resourceReader.read(uri); outputStream) {
                ResponseWriter.writeOk(content, outputStream);
            }
        } catch (ServerException e) {
            ResponseWriter.writeError(outputStream, e.getStatusCode());

        }
    }
}
