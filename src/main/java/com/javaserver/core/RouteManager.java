package com.javaserver.core;

import java.io.BufferedReader;
import java.net.Socket;

import com.javaserver.routeHandlers.RouteHandler;
import com.javaserver.routeHandlers.User;

import java.util.HashMap;

public class RouteManager {

    private HashMap<String, RouteHandler> routes;

    private Socket socket;

    public RouteManager(Socket socket) {
        this.socket = socket;
    }

    public static RouteHandler assignRouter(BufferedReader reader) {
        String line;

        return new User(null);
    }

    public String getRoute(BufferedReader reader) {
        String line;
        String route = "";
        try {
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                // Parse the header fields
                System.out.println("line: " + line);
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    route = parts[1].trim();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return route;
    }

}
