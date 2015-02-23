package metaStoreServerApi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("Demo/")

public class restApi {
	@Path("helloWorld")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorld() {
		return Response.status(200).entity("{\"msg\" : \"Hello World\"}").build();
	}
}
