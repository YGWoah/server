package com.javaserver.routeHandlers;

import java.io.IOException;
import java.net.Socket;

public class NotFound extends RouteHandler {
    public NotFound(Socket socket) {
        super(socket);
    }

    @Override
    public void respond() {
        try {
            socket.getOutputStream().write("HTTP/1.1 404 OK\r\n\r\n".getBytes());
            socket.getOutputStream().write("<h1>Hello Page Not Found!</h1>".getBytes());
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
