package edu.escuelaing.arem.ASE.app.spark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Response {

    private String path;
    private String type;
    private String body;

    public String getHeader() {
        return "HTTP/1.1 200 \r\n" +
                "Content-Type: " + type + " \r\n" +
                "\r\n";
    }

    public String getBody() {
        byte[] file;
        try{
            file = Files.readAllBytes(Paths.get("src/main/resources/" + path));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return new String(file);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setBody(String body) {
        this.body = body;
    }

    public String getResponse() {
        return getHeader() + getBody();
    }
}
