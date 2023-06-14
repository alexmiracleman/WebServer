package io;


import domain.HttpMethod;
import domain.Request;
import exceptions.ServerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import server.Server;


import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestHandlerTest {
    private Request request = new Request();

    public RequestHandlerTest() {
    }

    @Test
    @DisplayName("Get URI and method, where HTTP method is  \"Null\"")
    void test1() {
        ServerException exception = assertThrows(ServerException.class, () ->
                RequestParser.getMethodAndURI(null, request));

        assertEquals("Bad Request", exception.getStatusCode().getStatusMessage());
    }

    @Test
    @DisplayName("Get URI and method, where HTTP method is other than \"Get\"")
    void test2() {
        String testLine = "POST /test HTTP/1.1";

        ServerException exception = assertThrows(ServerException.class, () ->
                RequestParser.getMethodAndURI(testLine, request));

        assertEquals("Method Not Allowed", exception.getStatusCode().getStatusMessage());
    }

    @Test
    @DisplayName("Get URI and method and compare them with the expected ones")
    void test3() {

        String testLine = "GET /index.html HTTP/1.1";
        RequestParser.getMethodAndURI(testLine, request);
        HttpMethod httpMethod = HttpMethod.valueOf("GET");
        assertEquals("/index.html", request.getUri());
        assertEquals(httpMethod, request.getMethod());
    }



}