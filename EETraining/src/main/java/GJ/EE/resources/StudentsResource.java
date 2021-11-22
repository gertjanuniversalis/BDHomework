package GJ.EE.resources;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import GJ.EE.domain.Student;
import GJ.EE.domain.Students;
import GJ.EE.services.StudentService;

@Path("students")
@Produces(MediaType.APPLICATION_JSON)
@SessionScoped
public class StudentsResource {

    @Inject
    private StudentService studentService;

    @GET
    @Path("{id}")
    public Student get(@PathParam("id") int id){
        return studentService.getById(id);
    }

    public Student getById(int id){
       return new StudentService().getById(id);
    }

    @GET
    @Path("q")
    public Students getByLastname(@QueryParam("lastname") String name) {
        return studentService.getByLastName(name);
    }

    @GET
    public Student[] getAll(){
        return studentService.getAll();
    }

    @DELETE
    @Path("{id}")
    public Student delete(@PathParam("id") int id){
        return studentService.remove(id);
    }

    @POST
    public Student post(Student student){
        if(studentService.add(student)){
            return student;
        }
        throw new RuntimeException("Not added");
    }
}
