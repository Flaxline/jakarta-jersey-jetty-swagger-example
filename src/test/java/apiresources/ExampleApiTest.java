package apiresources;

import example.MainApplication;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.jetty.server.Server;
import jakarta.ws.rs.client.WebTarget;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleApiTest {

    private Server server;
    private WebTarget target;


    @BeforeEach
    public void setUp() throws Exception {
        server = MainApplication.createServer(9999);
        server.start();

        Client c = ClientBuilder.newClient();
        target = c.target("http://localhost:9999").path("example");
    }

    @AfterEach
    public void tearDown() throws Exception {
        if(server != null) {
            server.stop();
        }
    }

    @Test
    public void testGetResult() {
        String response = target.path("result").request().get(String.class);
        assertEquals("I am your result!", response);
    }

    @Test
    public void testGetAsyncResult() {
        String response = target.path("asyncResult").request().post(null, String.class);
        assertEquals("I am your result!", response);
    }

    @Test
    public void testGetSquare() {
        int response = target.path("square").request().post(Entity.text("5"), Integer.class);
        assertEquals(25, response);
    }

    @Test
    public void testGetAsyncSquare() {
        int response = target.path("asyncSquare").request().post(Entity.text("5"), Integer.class);
        assertEquals(25, response);
    }
}