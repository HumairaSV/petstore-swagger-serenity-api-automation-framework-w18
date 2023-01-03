package io.swagger.petstore.usernpetinfo;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.testbase.TestBase;
import io.swagger.petstore.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTestWithSteps extends TestBase {
    @Steps
    UserSteps userSteps;

    static int id= 2251;
    static String username = "User"+ TestUtils.getRandomValue();
    static String firstName = "Name" + TestUtils.getRandomValue();
    static String lastName = "Cocomelon";
    static String email = "usermelon"+ TestUtils.getRandomValue()+"@gmail.com";
    static String password = "UserPass123456";
    static String phone = "7845451244";
    static int userStatus = 0;

    @Title("T1-This will create a new user")
    @Test
    public void test001(){
        ValidatableResponse response = userSteps.createUser(id,username,firstName,lastName,email,password,phone,userStatus);
        response.log().all().statusCode(200);
    }

    @Title("T2-Verify if the user was created")
    @Test
    public void test002(){
        HashMap<String, Object>userMap = userSteps.findUserByUserName(username);
        Assert.assertThat(userMap, hasValue(firstName));
    }

    @Title("T3-Update the user details and verify the updated information")
    @Test
    public void test003(){
        username = username + "_updated";
        userSteps.updateUser(id, username,firstName,lastName,email,password,phone,userStatus);
        //verifying if the information has been updated
        HashMap<String, Object>userMap = userSteps.findUserByUserName(username);
        Assert.assertThat(userMap, hasValue(firstName));

    }

    @Title("T4-Delete the store and verify if the store has been deleted")
    @Test
    public void test004(){
        userSteps.deleteUserById(username).statusCode(200);
        userSteps.getUserByName(username).statusCode(404);
    }


}
