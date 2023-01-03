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


import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class PetCRUDTestWithSteps extends TestBase {

    static long petId;
    static HashMap<String, Object> category;
    static String name = "Vincent" + TestUtils.getRandomValue();
    static ArrayList<String> photoUrls;
    static ArrayList<HashMap<String, Object>> tags;
    static String status = "available";

    @Steps
    PetSteps petSteps;

    @Title("T1-This will create a new pet")
    @Test
    public void test001(){
        HashMap<String, Object> category = new HashMap<>();
        category.put("name", "Snowy");
        category.put("id", "2");

        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add("https://example.com/cat.jpg");

        ArrayList<HashMap<String, Object>> tags = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tagMap = new HashMap<>();
        tagMap.put("id", 5);
        tagMap.put("name", "British Short Hair");
        tags.add(tagMap);

        ValidatableResponse response = petSteps.createPet(petId,category,name,photoUrls,tags,status).statusCode(200);
        petId = response.log().all().extract().path("id");
    }

    @Title("T2-Verify if the pet was created")
    @Test
    public void test002(){
        HashMap<String, Object> petMap = petSteps.findPetById(petId);
        Assert.assertThat(petMap, hasValue(name));
    }

    @Title("T3-Update the pet details and verify the updated information")
    @Test
    public void test003(){
        status = status + "_updated";

        HashMap<String, Object> category = new HashMap<>();
        category.put("name", "Snowy");
        category.put("id", "2");

        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add("https://example.com/cat.jpg");

        ArrayList<HashMap<String, Object>> tags = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tagMap = new HashMap<>();
        tagMap.put("id", 5);
        tagMap.put("name", "BSH");
        tags.add(tagMap);

        petSteps.updatePet(petId,category,name,photoUrls,tags,status);
        //verifying if the information has been updated
        HashMap<String, Object> petMap = petSteps.findPetById(petId);
        Assert.assertThat(petMap, hasValue(status));
    }

    @Title("T4-Delete the pet and verify if the pet has been deleted")
    @Test
    public void test004(){
        petSteps.deletePetById(petId);
        petSteps.getPetById(petId);
    }

}
