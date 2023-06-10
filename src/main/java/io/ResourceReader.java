package io;

import domain.StatusCode;
import exceptions.ServerException;

import java.io.*;

import static domain.StatusCode.NOT_FOUND;

public class ResourceReader {
    private final String webAppPath;

    public ResourceReader(String path) {
        webAppPath = path;
    }

    public InputStream read(String uri) throws IOException {
        File pathToFile = new File(webAppPath, uri);
        byte[] contentArray = new byte[(int) pathToFile.length()];

        try (FileInputStream fileInputStream = new FileInputStream(pathToFile)) {
            fileInputStream.read(contentArray);
        } catch (FileNotFoundException e) {
            throw new ServerException(NOT_FOUND);
        }
        return new FileInputStream(webAppPath + uri);

        }


}
