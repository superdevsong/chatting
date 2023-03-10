import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class RootHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // Initialize Response Body
        OutputStream respBody = exchange.getResponseBody();
        try {
            byte[] file = getClass().getClassLoader().getResourceAsStream("static/web/index.html").readAllBytes();

            // Set Response Headers
            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-Type", "text/html;charset=UTF-8");
            headers.add("Content-Length", String.valueOf(file.length));

            // Send Response Headers
            exchange.sendResponseHeaders(200, file.length);

            respBody.write(file);

            // Close Stream
            respBody.close();

        } catch ( IOException e ) {
            e.printStackTrace();

            if( respBody != null ) {
                respBody.close();
            }
        } finally {
            exchange.close();
        }
    }
}
