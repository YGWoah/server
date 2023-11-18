package com.javaserver.routeHandlers;

import java.net.Socket;

public abstract class RouteHandler {
    protected String method;
    protected Socket socket;

    public RouteHandler(Socket socket) {
        method = "GET";
        this.socket = socket;
    }

    public RouteHandler(String method, Socket socket) {
        this.method = method;
        this.socket = socket;
    }

    public abstract void respond();
}
