package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ReqresAPISteps {
    private Response response;
    private final String baseURI = "https://reqres.in";
    private final String postReq = "/api/users";
    private final String putReq = "/api/users/3";
    private final String deleteReq = "/api/users/_ID_";
    private final String getSingleUSerReq = "/api/users/_ID_";

    private String name = "Pablo Picasso";
    private String job = "Painter";
    private String emailToValidate = "emma.wong@reqres.in";
    private String newName = "Vincent van Gogh";
    private String newJob = "Writer";
    private String userId;


    @Given("^I create a new user$")
    public void createAUSer(){

        JSONObject jsonUser = new JSONObject();
        jsonUser.put("name", name);
        jsonUser.put("job", job);

        response = given()
                .baseUri(baseURI)
                .body(jsonUser)
                .when()
                .post(postReq);

        response
                .then()
                .statusCode(201);

        userId = response
                .then()
                .extract()
                .path("id");
    }

    @And("^I update the user information$")
    public void updateUserInformation(){
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("name", newName);
        jsonUser.put("job", newJob);

        response = given()
                .baseUri(baseURI)
                .body(jsonUser)
                .when()
                .put(putReq.replace("_ID_", userId));

        response
                .then()
                .statusCode(200);
    }

    @And("^I list the user information$")
    public void listUserInformation(){
        response = given()
                .baseUri(baseURI)
                .when()
                .get(getSingleUSerReq.replace("_ID_", "2"));

        response
                .then()
                .statusCode(200)
                .extract()
                .path("data.email")
                .equals(emailToValidate);
    }

    @And("^I delete the user$")
    public void deleteUser(){
        response = given()
                .baseUri(baseURI)
                .when()
                .delete(deleteReq.replace("_ID_", userId));

        response
                .then()
                .statusCode(204);
    }

    @Then("^I confirm new user has been deleted.$")
    public void GETUserDefunktRequest(){
        response = given()
                .baseUri(baseURI)
                .when()
                .get(getSingleUSerReq.replace("_ID_", "2"));

        response
                .then()
                .statusCode(200);
    }
    


}
