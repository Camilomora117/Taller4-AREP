package edu.escuelaing.arem.ASE.app.webapps;

import edu.escuelaing.arem.ASE.app.HttpServer;

import java.io.IOException;

public class FirstApp {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Inicio la APP!");
        HttpServer server = HttpServer.getInstance();
        server.run(args);
    }
}
