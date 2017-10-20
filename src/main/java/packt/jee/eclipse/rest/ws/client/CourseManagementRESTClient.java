package packt.jee.eclipse.rest.ws.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Form; 

/**
 * This is a simple test class for invoking REST web service
 * using JAX-RS client APIs
 */
public class CourseManagementRESTClient {
  public static void main(String[] args) {
    //testGetCoursesJSON();
	//testAddCourseJSON();
	testAddCourseForm();
  }
  
  //Test getCourse method (XML or JSON) of CourseService
  public static void testGetCoursesJSON() {
    //Create JAX-RS client
    Client client = ClientBuilder.newClient();
    //Get webTarget for a URL
    WebTarget webTarget = client.target("http://localhost:8080/CourseManagementREST/services/course");
    //Add paths to URL
    webTarget = webTarget.path("get").path("10");
    
    //We could also have created webTarget in one call with the full URL -
    //WebTarget webTarget = client.target("http://localhost:8080/CourseManagementREST/services/course/get/10");
    //Execute HTTP get method
    Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
    
    //Check response code. 200 is OK
    if (response.getStatus() != 200) {
      System.out.println("Error invoking REST Web SErvice - " + response.getStatusInfo().getReasonPhrase());
      return;
    } else {
      //REST call was successful. Print the response
      System.out.println(response.readEntity(String.class));
    }
  }
  
  //Test addCourse method (JSON version) of CourseService
  public static void testAddCourseJSON() {
    //Create JAX-RS client
    Client client = ClientBuilder.newClient();
    
    //Get WebTarget for a URL
    WebTarget webTarget = client.target("http://localhost:8080/CourseManagementREST/services/course/add");
    
    //Create JSON representation of Course,
    //with course_name and credits fields. Instead of creating
    //JSON manually, you could also use JAXB to create JSON from
    //Java object
    String courseJSON = "{\"course_name\":\"Course-4\", \"credits\":5}";
    
    //Execute HTTP post method
    Response response = webTarget.request().post(Entity.entity(courseJSON, MediaType.APPLICATION_JSON_TYPE));
    
    //Check response code. 200 is OK
    if (response.getStatus() != 200) {
      System.out.println("Error invoking REST Web Service - " + response.getStatusInfo().getReasonPhrase() + ", Error Code: " + response.getStatus());
    }
    System.out.println(response.readEntity(String.class));
  }
  
  //Test addCourse method (Form-Encoded version) of CourseService
  public static void testAddCourseForm() {
    //create JAX-RS client
    Client client = ClientBuilder.newClient();
    
    WebTarget webTarget = client.target("http://localhost:8080/CourseManagementREST/services/course/add");
    
    //Create Form object and populate fields
    Form form = new Form();
    form.param("name", "Course-5");
    form.param("credits", "5");
    
    //Execute HTTP post method
    Response response = webTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
    
    //check response code. 200 is OK
    if (response.getStatus() != 200) {
      System.out.println("Error invoking REST Web Service - " + response.getStatusInfo().getReasonPhrase() + ", Error Code: " + response.getStatus());
    }
    System.out.println(response.readEntity(String.class));
  }
}
