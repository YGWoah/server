package com.javaserver.core;

import java.io.BufferedReader;
import java.net.Socket;

import com.javaserver.routeHandlers.RouteHandler;
import com.javaserver.routeHandlers.User;
import com.javaserver.routeHandlers.NotFound;
import com.javaserver.routeHandlers.Root;

import java.util.HashMap;

public class RouteManager {

    private HashMap<String, Class<?>> routes;
    private Socket socket;
    private String route;
    private String method;

    public RouteManager(Socket socket) {
        this.socket = socket;
        this.routes = new HashMap<String, Class<?>>();
        routes.put("/user", User.class);
        routes.put("/", Root.class);
        routes.put("notfound", NotFound.class);

        BufferedReader reader = null; // Initialize the reader variable
        try {
            reader = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            // TODO: handle exception
        }
        this.route = this.getRoute(reader);
        this.method = this.getMethod(reader);

    }

    public RouteHandler assignRouter() {

        String route = this.route;
        // switch (route) in key of the map routes
        if (routes.containsKey(route)) {
            try {
                return (RouteHandler) routes.get(route).getConstructor(Socket.class).newInstance(this.socket);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return new NotFound(this.socket);
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

    public String getMethod(BufferedReader reader) {
        String line;
        String method = "";
        try {
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                // Parse the header fields
                System.out.println("line: " + line);
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    method = parts[0].trim();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return method;
    }

}
