import demo.EmployeeRequest;
import demo.EmployeeResponse;
import demo.controller.EmployeeController;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class Employee {
    EmployeeController employeeController = new EmployeeController();
    @Test
    public void getEmployee(){
        Response response = RestAssured
                .given()
                .baseUri("http://dummy.restapiexample.com")
                .basePath("/api")
                .log()
                .all()
                .header("Content-type", "application/json")
                .header("Accept","*/*")
                .get("/v1/employees");

        //response.getBody().prettyPrint();
        //System.out.println(response.getStatusCode());

        //Assert.assertEquals(200, response.getStatusCode()); //code
        //Assert.assertThat("Lama cui", response.getTime(), Matchers.lessThan(3000L)); //time
        //Assert.assertEquals("success", response.path("status")); //body
        //Assert.assertEquals("Tiger Nixon", response.path("data[0].employee_name"));

        EmployeeResponse employeeResponse = response.as(EmployeeResponse.class);
        System.out.println(employeeResponse.getStatus());

    }

    @Test
    public void createEmployee(){
        //String requestBody = "{\n" +
          //      "  \"name\": \"Dana\",\n" +
            //    "  \"salary\": \"123\",\n" +
              //  "  \"age\": \"23\"\n" +
                //"}";
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("Vans");
        employeeRequest.setAge("23");
        employeeRequest.setSalary("20000000");
//        Response response = RestAssured
//                .given()
//                .baseUri("http://dummy.restapiexample.com")
//                .basePath("/api")
//                .log()
//                .all()
//                .header("Content-type", "application/json")
//                .header("Accept","*/*")
//                .body(employeeRequest)
//                .get("/v1/create");
                //.body(requestBody)
        Response response = employeeController.createEmployee(employeeRequest);
        response.getBody().prettyPrint();
        System.out.println(response.getStatusCode());

    }
}
