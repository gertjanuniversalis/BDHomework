package GJ.EE.resources;

import GJ.EE.domain.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("helloworld")
public class HelloWorldResource {
    private List<Student> students;

    @GET
    public String getHelloWorld(){
        return "hallo there general kenobi";
    }

    @GET
    @Path("ok")
    public Response ok(@QueryParam("lastname") String lastname) {
        return Response.ok().entity(lastname).build();
    }

    @GET
    @Path("hello/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response hello(@PathParam("id") String id){
        return Response.ok().entity("<hello>HOI" + id + "</hello>").build();
    }

    @GET
    @Path("error")
    public Response get(){
        return Response.serverError().entity("This didn't go as planned").build();
    }

    @POST
    @Path("post")
    public Student post(Student student){
        if(student == null){
            students = new ArrayList<>();
        }

        students.add(student);
        System.out.println("Amount of students = " + students.size());
        return student;
    }

}
