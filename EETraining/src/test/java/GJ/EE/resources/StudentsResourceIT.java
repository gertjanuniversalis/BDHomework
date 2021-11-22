package GJ.EE.resources;

import GJ.EE.App;
import GJ.EE.DAO.StudentDao;
import GJ.EE.domain.Student;
import GJ.EE.domain.StudentValues;
import GJ.EE.domain.Students;
import GJ.EE.services.StudentService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.net.URL;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class StudentsResourceIT {

    @ArquillianResource
    URL deploymentURL;

    private String studentResource;

    private String getStudentListResult() {
        return ClientBuilder.newClient()
                .target(studentResource)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
    }

    @Before
    public void setup()
    {
        studentResource = deploymentURL + "resources/students";
    }

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class,"test.war")
                .addClass(App.class)
                .addClass(StudentService.class)
                .addClass(StudentDao.class)
                .addPackage(StudentsResource.class.getPackage())
                .addPackage(Students.class.getPackage());

        System.out.println(archive.toString(true));

        return archive;
    }

    @Test
    public void getStudentsReturnsStudents(){
        System.out.println("studentsRes =" + studentResource);
        System.out.println(getStudentListResult());

        assertThat(getStudentListResult(), containsString("{\"students\":["));
    }

    @Test
    public void getAllReturnsAll(){
        long returnedStudents = getStudentListResult().chars().filter(ch->ch=='{').count() + 1; //+1 because we also count the opening accolade from the JSON file
        long expectedAmount = StudentValues.STUDENTS.size();

        assertEquals(returnedStudents,expectedAmount);
    }

    @Test
    public void deleteDeletesOne(){
        ClientBuilder.newClient().target(studentResource+"/2").request(MediaType.APPLICATION_JSON).delete();

        long returnedStudents = getStudentListResult().chars().filter(ch->ch=='{').count() + 1; //+1 because we also count the opening accolade from the JSON file
        long expectedAmount = StudentValues.STUDENTS.size()-1;

        assertEquals(returnedStudents,expectedAmount);
    }

    @Test
    public void post(){
        Student response = ClientBuilder.newClient()
                .target(studentResource)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(StudentValues.JANSSENS), Student.class);

        assertNotNull(response.getId());
    }
}
