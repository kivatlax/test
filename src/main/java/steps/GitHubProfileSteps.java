package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GitHubProfileSteps {
    private Response response;
    private String baseUri = "https://api.github.com";
    private String bootstrapRepo = "/repos/twbs/bootstrap";
    private int bootstrapUserId = 2918581;
    private String defunktProfile = "/users/defunkt";
    private String defunktName = "Chris Wanstrath";


    @Given("^I send a GET request for user defunkt$")
    public void GETUserDefunktRequest(){
        response = given()
                .baseUri(baseUri)
                .when()
                .get(defunktProfile);
    }

    @Then("^I get info to confirm Chris Wanstrath is owner of this profile.$")
    public void confirmWanstrathProfileInformation(){
        response
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("name")
                .equals(defunktName);
    }

    @Given("^I send a GET request for bootstrap repository$")
    public void GETBootstrapRepositoryRequest(){
        response = given()
                .baseUri(baseUri)
                .when()
                .get(bootstrapRepo);
    }

    @Then("^I get info to confirm bootstrap repository$")
    public void confirmBootstrapRepositoryInformation(){
        response
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("owner.id")
                .equals(bootstrapUserId);
    }

}
