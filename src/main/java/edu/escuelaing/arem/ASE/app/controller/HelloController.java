package edu.escuelaing.arem.ASE.app.controller;

public class HelloController {

    @RequestMapping("/hello")
    public static String index() {
        return  getHeader() + "Greetings from Spring Boot!";
    }

    public static String getHeader() {
        return "HTTP/1.1 200 \r\n" +
                "Content-Type: text/html \r\n" +
                "\r\n";
    }
}
