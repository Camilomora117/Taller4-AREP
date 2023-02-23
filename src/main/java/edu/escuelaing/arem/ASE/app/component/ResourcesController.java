package edu.escuelaing.arem.ASE.app.component;

import edu.escuelaing.arem.ASE.app.HttpServer;
import edu.escuelaing.arem.ASE.app.springBoot.Component;
import edu.escuelaing.arem.ASE.app.springBoot.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ResourcesController {

    @RequestMapping("/app")
    public static String readFileJS() {
        return  getHeader() + getResponseFileJS();
    }

    @RequestMapping("/html")
    public static String readFileHTML() {
        return  getHeaderHTML() + getResponseFileHTML();
    }

    @RequestMapping("/css")
    public static String readFileCSS() {
        return  getHeader() + getResponseFileCSS();
    }

    @RequestMapping("/image")
    public static String readFileImage() {
        return  getResponseImg();
    }


    public static String getHeader() {
        return "HTTP/1.1 200 \r\n" +
                "Content-Type: application/json \r\n" +
                "\r\n";
    }

    public static String getHeaderHTML() {
        return "HTTP/1.1 200 \r\n" +
                "Content-Type: text/html \r\n" +
                "\r\n";
    }

    public static String getResponseFileJS() {
        byte[] file;
        try{
            file = Files.readAllBytes(Paths.get("src/main/resources/public/app.js"));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return new String(file);
    }

    public static String getResponseFileCSS() {
        byte[] file;
        try{
            file = Files.readAllBytes(Paths.get("src/main/resources/public/style.css"));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return new String(file);
    }

    public static String getResponseFileHTML() {
        byte[] file;
        try{
            file = Files.readAllBytes(Paths.get("src/main/resources/public/index.html"));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return new String(file);
    }

    public static String getResponseImg() {
        String file = "";
        try {
            file = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: image/jpg\r\n"
                    + "\r\n";
            BufferedImage bufferedImage = ImageIO.read(new File("src/main/resources/public/imagen.jpg"));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            HttpServer server = HttpServer.getInstance();
            DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
            dataOutputStream.writeBytes(file);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
