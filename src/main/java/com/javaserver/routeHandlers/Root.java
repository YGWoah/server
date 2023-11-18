package com.javaserver.routeHandlers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

public class Root extends RouteHandler {

    public Root(String method, Socket socket) {
        super(method, socket);
    }

    public Root(Socket socket) {
        super(socket);
    }

    @Override
    public void respond() {

        this.respondWithHTMLFile(
                method.equals("GET") ? "src/main/resources/static/index.html" : "src/main/resources/static/404.html");
    }

    public void respondWithHTMLFile(String filePath) {
        try {
            // Read the HTML file contents
            String htmlContent = readFile(filePath);

            // Send the HTTP response header
            socket.getOutputStream().write("HTTP/1.1 200 OK\r\n".getBytes());
            socket.getOutputStream().write("Content-Type: text/html\r\n".getBytes());
            socket.getOutputStream().write("\r\n".getBytes());

            // Send the HTML file contents
            socket.getOutputStream().write(htmlContent.getBytes());
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String readFile(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString();
    }

}
