package edu.escuelaing.arem.ASE.app.spark;

import edu.escuelaing.arem.ASE.app.HttpServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Spark {

    public static Map<String, Response> gets = new HashMap<>();

    public static void get(String path, Route route) {
        Request req = new Request();
        Response res = new Response();
        String var = route.application(req, res);
        res.setBody(var);
        res.setPath(path);
        gets.put(path, res);
    }

    public static void getFile(String path, Route route) {
        Request req = new Request();
        Response res = new Response();
        String var = route.application(req, res);
        res.setBody(var);
        res.setPath(path);
        gets.put(path, res);
    }


    public static String getBody(String path) {
        byte[] file;
        try{
            file = Files.readAllBytes(Paths.get("src/main/resources/" + path));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return new String(file);
    }

    public static String getBodyImg(String path) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: image/jpg\r\n"
                + "\r\n";
        BufferedImage bufferedImage = ImageIO.read(new File("src/main/resources" + path));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HttpServer server = HttpServer.getInstance();
        DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        dataOutputStream.writeBytes(response);
        dataOutputStream.write(byteArrayOutputStream.toByteArray());
        return response;
    }

}
