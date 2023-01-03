package io.swagger.petstore.usernpetinfo;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.constants.Endpoints;
import io.swagger.petstore.model.UserPojo;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {



    @Step("Create user with id: {0}, username: {1}, firstname: {2}, lastname: {3}, email: {4}, password: {5}, phone: {6}, userStatus: {7}")
    public ValidatableResponse createUser(int id, String username, String firstName, String lastName,String email, String password, String phone, int userStatus){

        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setUsername(username);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(userStatus);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post(Endpoints.CREATE_USER)
                .then();
    }

    @Step("Find the user by username: {0}")
    public HashMap<String, Object> findUserByUserName(String username){

        return SerenityRest.given().log().all()
                .pathParam("userName", username)
                .when()
                .get(Endpoints.GET_USER_BY_NAME)
                .then()
                .statusCode(200)
                .extract()
                .path("");
    }

    @Step("Update user information with id: {0}, username: {1}, firstname: {2}, lastname: {3}, email: {4}, password: {5}, phone: {6}, userStatus: {7}")
    public ValidatableResponse updateUser(int id, String username, String firstName, String lastName,String email, String password, String phone, int userStatus){

        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setUsername(username);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(userStatus);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("userName", username)
                .when()
                .body(userPojo)
                .put(Endpoints.UPDATE_USER_BY_NAME)
                .then();
    }

    @Step("Deleting the user by username: {0}")
    public ValidatableResponse deleteUserById(String username) {

        return SerenityRest.given().log().all()
                .pathParam("userName", username)
                .when()
                .delete(Endpoints.DELETE_USER_BY_NAME)
                .then();
    }

    @Step("Getting the user by username: {0}")
    public ValidatableResponse getUserByName(String username) {

        return SerenityRest.given().log().all()
                .pathParam("userName", username)
                .when()
                .get(Endpoints.GET_USER_BY_NAME)
                .then();
    }
}
