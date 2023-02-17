package edu.escuelaing.arem.ASE.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import edu.escuelaing.arem.ASE.app.controller.RequestMapping;
import edu.escuelaing.arem.ASE.app.spark.StaticFiles;
import org.json.*;

public class HttpServer {

    private static  HttpServer _instance = new HttpServer();

    private OutputStream outputStream = null;

    private StaticFiles staticFiles = StaticFiles.getIstance();

    private HashMap<String, Method> methodsReqMap = new HashMap<>();

    public void run(String[] args) throws IOException, ClassNotFoundException {

        String className = args[0];

        String urlTitle = "";
        ServerSocket serverSocket = null;

        //cargar la class con forname
        Class c = Class.forName(className);
        Method[] methods = c.getMethods();
        System.out.println("Metodos " + methods);
        //extraer metodos con @RequestMapping
        for (Method m: methods) {
            if (m.isAnnotationPresent(RequestMapping.class)) {

                methodsReqMap.put(m.getAnnotation(RequestMapping.class).value(), m);
            }
        }
        System.out.println(methodsReqMap);
        //extraer el valor del path
        //extrager una instancia del metodo
        //poner en la tabla el metodo con llave path

        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream()));
            String inputLine, outputLine, path = "/simple";
            String method = "GET";
            Boolean boolFirstLine = true;
            outputStream = clientSocket.getOutputStream();

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);

                if(inputLine.contains("hello?name=")){
                    String[] res = inputLine.split("name=");
                    urlTitle = (res[1].split("HTTP")[0]).replace(" ", "");
                }
                if (boolFirstLine) {
                    path = inputLine.split(" ")[1];
                    method = inputLine.split(" ")[0];
                    boolFirstLine = false;
                }
                if (!in.ready()) {
                    break;
                }
            }

            if (path.startsWith("/apps/")) {
                String value = path.substring(5);
                try {
                    outputLine = (String) methodsReqMap.get(value).invoke(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (Objects.equals(method, "GET")){
                String ext = path.split("\\.")[1];
                String header = staticFiles.getHeader("text/"+ext);
                String body = staticFiles.readFile(path);
                if (Objects.equals(body, "404")) {
                    body = staticFiles.readFile("/notFound.html");
                }
                outputLine = header + body;
            } else {
                outputLine = htmlWithForms();
            }

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * method that returns the server instance
     * @return Instance Server
     */
    public static HttpServer getInstance() {
        return _instance;
    }

    /**
     * method that gets the OutputStream
     * @return OutputStream
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Method that creates a table in String(JSON) format
     * @param response String movie information
     * @return String(JSON)
     */
    private static String doTable(String response){
        HashMap<String,String> dict = new HashMap<String, String>();
        JSONArray jsonArray = new JSONArray(response);
        for (int i=0; i<jsonArray.length();i++){
            JSONObject object = jsonArray.getJSONObject(i);
            for (String key: object.keySet()) {
                dict.put(key.toString(), object.get(key).toString());
            }
        }
        String table = "<tr> \n";
        for (String keys: dict.keySet()){
            String value = dict.get(keys);
            table += "<td>" + keys + "</td>\n";
            table += "<td>" + value + "</td>\n";
            table += "<tr> \n";
        }
        return table;
    }

    /**
     * Method createv view html
     * @return view html
     */
    public static String htmlWithForms(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Search Films</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Search Movies</h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                if (nameVar) {\n" +
                "                   console.log(\"Nombre \" + nameVar)\n" +
                "                   const xhttp = new XMLHttpRequest();\n" +
                "                   xhttp.onload = function() {\n" +
                "                       document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                       this.responseText;\n" +
                "                   }\n" +
                "                   xhttp.open(\"GET\", \"/hello?name=\"+nameVar);\n" +
                "                   xhttp.send();\n" +
                "                };\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "    </body>\n" +
                "</html>";
    }
}