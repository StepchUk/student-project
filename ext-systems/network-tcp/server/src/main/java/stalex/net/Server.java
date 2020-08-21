package stalex.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket socket = new ServerSocket(25225, 2000);

        Map<String, Greetabl> handlers = loadHeandlers();

        System.out.println("Server is started");
        while (true) {
            Socket client = socket.accept();
            new SimpleServer(client, handlers).start();
        }
    }

    private static Map<String, Greetabl> loadHeandlers() {
        Map<String, Greetabl> result = new HashMap<>();

        try (InputStream is = Server.class.getClassLoader()
                .getResourceAsStream("server.properties")) {
            Properties properties = new Properties();
            properties.load(is);

            for (Object command : properties.keySet()) {
                String className = properties.getProperty(command.toString());
                Class<Greetabl> aClass = (Class<Greetabl>) Class.forName(className);

                Greetabl handler = aClass.getConstructor().newInstance();
                result.put(command.toString(), handler);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }
}

class SimpleServer extends Thread {

    private final Socket client;
    private final Map<String, Greetabl> handlers;

    public SimpleServer(Socket client, Map<String, Greetabl> handlers) {
        this.client = client;
        this.handlers = handlers;
    }

    @Override
    public void run() {
        handleRequest();
    }

    private void handleRequest() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            String request = br.readLine();
            String[] lines = request.split("\\s+");
            String command = lines[0];
            String userName = lines[1];
            System.out.println("Server got string 1: " + command);
            System.out.println("Server go string 2: " + userName);
//            Thread.sleep(2000);

            String response = buildResponse(command, userName);
            bw.write(response);
            bw.newLine();
            bw.flush();

            br.close();
            bw.close();

            client.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private String buildResponse(String command, String userName) {
        Greetabl handler = handlers.get(command);

        if (handler != null) {
            return handler.buildResponse(userName);
        }

        return "Hello " + userName;
    }
}
