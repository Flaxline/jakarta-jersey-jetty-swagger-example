package example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class MainApplication {
    public static void main(String[] args) throws Exception {
        Server server = null;
        try {
            server = createServer(8888);
            server.start();
            server.join();
        } finally {
            if(server != null) {
                server.stop();
                server.destroy();
            }
        }
    }

    public static Server createServer(int port) throws Exception {
        ServletContextHandler handler = buildUsingInitParameter();
        Server server = new Server(port);
        server.setHandler(handler);

        //server.start();

        return server;
    }

    static ServletContextHandler buildUsingInitParameter() {
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        handler.setContextPath("/");

        ServletHolder servletHolder = handler.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter(
                "jersey.config.server.provider.packages",
                "example.apiresources;io.swagger.v3.jaxrs2.integration.resources"
        );

        // provide swagger UI at base path
        // https://gist.github.com/nosmokingpistol/302c4c3ef30f183cf70e
        var resourceBasePath = MainApplication.class.getResource("/webapp").toExternalForm();
        handler.setWelcomeFiles(new String[] {"index.html"});
        handler.setResourceBase(resourceBasePath);
        handler.addServlet(new ServletHolder(new DefaultServlet()), "/ui/*");

        return handler;
    }
}
