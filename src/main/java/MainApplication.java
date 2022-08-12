import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class MainApplication {
    public static void main(String[] args) throws Exception {
        ServletContextHandler handler = buildUsingInitParameter();
        Server server = new Server(8888);
        server.setHandler(handler);
        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }

    static ServletContextHandler buildUsingInitParameter() {
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        handler.setContextPath("/");

        ServletHolder servletHolder = handler.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter(
                "jersey.config.server.provider.packages",
                "apiresources;io.swagger.v3.jaxrs2.integration.resources"
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
