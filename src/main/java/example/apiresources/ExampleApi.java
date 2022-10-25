package example.apiresources;

import example.businesslogic.ExampleLogic;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
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

    @POST
    @Path("/square")
    @Consumes(MediaType.TEXT_PLAIN)
    public int getSquare(int param) {
        return logic.getSquare(param);
    }

    @POST
    @Path("/asyncResult")
    public void getAsyncResult(@Suspended final AsyncResponse asyncResponse) {
        new Thread(() -> {
            String result = logic.getSlowResultsString();
            asyncResponse.resume(result);
        }).start();
    }

    @POST
    @Path("/asyncSquare")
    @Consumes(MediaType.TEXT_PLAIN)
    public void getAsyncSquare(@Suspended final AsyncResponse asyncResponse, int param) {
        new Thread(() -> {
            int result = logic.getSlowSquare(param);
            asyncResponse.resume(result);
        }).start();
    }

}
