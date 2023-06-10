package io;

import domain.HttpMethod;
import domain.Request;
import exceptions.ServerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static domain.StatusCode.BAD_REQUEST;
import static domain.StatusCode.METHOD_NOT_ALLOWED;

public class RequestParser {

    public static Request parse(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Request request = new Request();
        String topLine = bufferedReader.readLine();
        getMethodAndURI(topLine, request);
        getHeaders(bufferedReader, request);
        return request;

    }

    public static void getMethodAndURI(String line, Request request) {
        if (Objects.isNull(line)) {
            throw new ServerException(BAD_REQUEST);
        }
        String httpMethodType = line.substring(0, line.indexOf(" "));
        HttpMethod httpMethod = HttpMethod.valueOf(httpMethodType);
        if (httpMethod != HttpMethod.GET) {
            throw new ServerException(METHOD_NOT_ALLOWED);
        }
        request.setMethod(httpMethod);
        String emptyUri = "/";
        String homePageUri = "/index.html";
        String uri = line.substring(line.indexOf("/"), line.indexOf(" HTTP"));
        if (uri.equals(emptyUri)) {
            request.setUri(homePageUri);
        }
        else {
            request.setUri(uri);
        }
    }

    public static void getHeaders(BufferedReader reader, Request request) throws IOException {
        Map<String, String> headers = new HashMap<>();
        while (true) {
            String line = reader.readLine();
            if (Objects.isNull(line) || line.isBlank()) {
                break;
            }
            int separator = line.indexOf(":");
            String name = line.substring(0, separator);
            String value = line.substring(separator + 1).trim();
            headers.put(name, value);
        }
        request.setHeaders(headers);
    }

}
