package edu.escuelaing.arem.ASE.app.spark;

import spark.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StaticFiles {

    private static StaticFiles _instance = new StaticFiles();

    public static StaticFiles getIstance() {
        return _instance;
    }
    private String folder = "src/main/resources";
    private final String defauldFolder = "src/main/resources";


    public void setFolder(String path) {
        folder = defauldFolder + path;
    }

    public String readFile(String path) {
        byte[] file;
        try{
            file = Files.readAllBytes(Paths.get(folder + path));
        }catch (IOException e){
            return "404";
        }
        return new String(file);
    }


    public String getHeader(String type) {
        return "HTTP/1.1 200 \r\n" +
                "Content-Type: " + type + " \r\n" +
                "\r\n";
    }

}
