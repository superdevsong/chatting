import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerManager {
    HttpServer server = null;
    private static final int DEFAULT_PORT = 8881;

    public HttpServerManager() {
        try {
            createServer(DEFAULT_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HttpServerManager(int port) {
        try {
            createServer(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createServer(int port) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        this.server.createContext("/main", new RootHttpHandler());
        this.server.createContext("/web", new ResourceHttpHandler());

    }

    public void start() {
        if (this.server != null)
            this.server.start();
    }

    public static void main(String[] args) {
        HttpServerManager serverManager = new HttpServerManager();
        System.out.println("start");
        serverManager.start();
    }
}
