package example;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPI {


    public UserAPI() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }


    @Step("Метод удаления Пользователя")
    public Response deleteUser(String token) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .and()
                .when()
                .delete("/api/auth/user");
    }
    @Step("Метод создания Пользователя")
    public Response createUser(String name, String email, String password) {
        String jsonBody = String.format("{\n" +
                "    \"name\": \"%s\",\n" +
                "    \"email\": \"%s\",\n" +
                "    \"password\": \"%s\"\n" +
                "}", name, email, password);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonBody)
                .when()
                .post("/api/auth/register");
    }
    @Step("Метод получения токена")
    public String getToken(String name, String email, String password) {
        String jsonBody = String.format("{\n" +
                "    \"name\": \"%s\",\n" +
                "    \"email\": \"%s\",\n" +
                "    \"password\": \"%s\"\n" +
                "}", name, email, password);
        Response loginResponse = given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonBody)
                .when()
                .post("/api/auth/login");

        if (loginResponse.getStatusCode() == 200) {
            return loginResponse.path("accessToken");
        } else {
            throw new RuntimeException("Login failed with status code: " + loginResponse.getStatusCode());
        }

    }

    public String fullFlow(String name, String email, String password) {
        String jsonBody = String.format("{\n" +
                "    \"name\": \"%s\",\n" +
                "    \"email\": \"%s\",\n" +
                "    \"password\": \"%s\"\n" +
                "}", name, email, password);

        Response registerResponse = given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonBody)
                .when()
                .post("/api/auth/register");

        if (registerResponse.getStatusCode() == 200 || registerResponse.getStatusCode() == 403) {

        Response loginResponse = given()
                .header("Content-type", "application/json")
                .and()
                .body(jsonBody)
                .when()
                .post("/api/auth/login");

            if (loginResponse.getStatusCode() == 200) {
                return loginResponse.path("accessToken");
            } else {
                throw new RuntimeException("Login failed with status code: " + loginResponse.getStatusCode());
            }
        } else {
            throw new RuntimeException("Registration failed with status code: " + registerResponse.getStatusCode());
        }

    }

}
