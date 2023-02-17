package edu.escuelaing.arem.ASE.app.webapps;

import edu.escuelaing.arem.ASE.app.HttpServer;
import edu.escuelaing.arem.ASE.app.spark.Route;
import edu.escuelaing.arem.ASE.app.spark.Spark;
import edu.escuelaing.arem.ASE.app.spark.StaticFiles;

import java.io.IOException;

public class FirstApp {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Hello World!");
        HttpServer server = HttpServer.getInstance();

//        StaticFiles staticFiles = StaticFiles.getIstance();
//        staticFiles.setFolder("/public");
//        //Get
//        Spark.get("apps/path", (req, res) -> {
//            res.setType("application/json");
//            return "{\"name\"; \"Daniel\"}";
//        });

        server.run(args);
    }
}
