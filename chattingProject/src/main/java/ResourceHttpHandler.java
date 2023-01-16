import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class ResourceHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Initialize Response Body
        OutputStream respBody = exchange.getResponseBody();

        try {
            String path = exchange.getRequestURI().getPath();
            File file = new File(getClass().getClassLoader().getResource("static"+ path).getFile());

            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-Type", String.format("text/%s;",path.substring(path.lastIndexOf(".")+1)));
            headers.add("Content-Length", String.valueOf(file.length()));

            exchange.sendResponseHeaders(200, file.length());

            respBody.write(Files.readAllBytes(file.toPath()));

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
