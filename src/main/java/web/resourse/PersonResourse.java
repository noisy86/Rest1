package web.resourse;

import web.managers.PersonManager;
import web.entities.PersonEntity;
import web.managers.PersonManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("people")
@Produces(MediaType.APPLICATION_JSON)

public class PersonResourse {
    @Inject
    private PersonManager manager;

    @GET
    public Response getPeople() {
        return Response.ok(manager.getPeople()).build();
    }

    @GET
    @Path("{id}")
    public Response getPerson(@PathParam("id") int id) {
        return  Response.ok(manager.getPerson(id)).build();
    }

    @POST
    public Response createPerson(PersonEntity person){
        if(!manager.create(person))
            return Response.status(400).build();

        return Response.ok(person).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePerson(@PathParam("id") int id) {
        if(manager.removePerson(id)){
            return Response.ok("Post deleted").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
