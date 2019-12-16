package com.example.newnetty.router;

public class App {
    public static void main(String[] args) throws Exception {
        HttpServer httpServer = new HttpServer(8899);
        httpServer.start();
    }
}
