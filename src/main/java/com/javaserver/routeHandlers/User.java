package com.javaserver.routeHandlers;

import java.io.IOException;
import java.net.Socket;

public class User extends RouteHandler {

    public User(Socket socket) {
        super(socket);
    }

    public User(String method, Socket socket) {
        super(method, socket);
    }

    public String getMethod() {
        return this.method;
    }

    @Override
    public void respond() {
        try {
            socket.getOutputStream().write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
            socket.getOutputStream().write("<h1>Hello World!</h1>".getBytes());
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
