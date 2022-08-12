package apiresources;

import businesslogic.ExampleLogic;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("example")
public class ExampleApi {

    private ExampleLogic logic;

    public ExampleApi() {
        logic = new ExampleLogic();
    }

    @GET
    @Path("/result")
    @Produces(MediaType.TEXT_PLAIN)
    public String getResult() {
        return logic.getResultString();
    }

}
