package io;

import domain.StatusCode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ResponseWriter {
    private static final byte[] HTTP_OK_STATUS_LINE = ("HTTP/1.1 " + StatusCode.OK).getBytes();
    private static final byte[] HTTP_NEW_LINE = "\r\n".getBytes();

    public static void writeOk(InputStream contentArray, OutputStream outputStream) throws IOException {
        outputStream.write(HTTP_OK_STATUS_LINE);
        outputStream.write(HTTP_NEW_LINE);
        outputStream.write(HTTP_NEW_LINE);
        byte[] buffer = new byte[2048];
        int count;
        while ((count = contentArray.read(buffer)) != -1) {
            outputStream.write((buffer), 0, count);
        }

    }

    public static void writeError(OutputStream outputStream, StatusCode statusCode) throws IOException {
        outputStream.write(("HTTP/1.1 " + statusCode.getCode() + " " + statusCode.getStatusMessage()).getBytes());
        outputStream.write(HTTP_NEW_LINE);
        outputStream.write(HTTP_NEW_LINE);
        outputStream.write((statusCode.getDisplayMessage()).getBytes());
    }

}
