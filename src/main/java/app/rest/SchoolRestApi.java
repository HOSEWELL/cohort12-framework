package app.rest;

import app.bean.SchoolBean;
import app.model.School;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/school")
public class SchoolRestApi extends GenericApi<School>{

    @EJB
    private SchoolBean schoolBean;

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(School school){
        schoolBean.save(school);

        return Response.ok(new ResponseStatus()).build();
    }

    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public School find(@PathParam("id") Long id){
        return schoolBean.findById(id);
    }

    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public School query(@QueryParam("id") Long id){
        return schoolBean.findById(id);
    }

    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<School> list(){
        return schoolBean.list(new School());
    }

}
