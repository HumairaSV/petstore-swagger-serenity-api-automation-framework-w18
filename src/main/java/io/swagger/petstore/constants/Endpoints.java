package io.swagger.petstore.constants;

public class Endpoints {


    //endpoints for user

    public static final String CREATE_USER = "/v2/user";
    public static final String GET_USER_BY_NAME = "/v2/user/{userName}";
    public static final String UPDATE_USER_BY_NAME = "/v2/user/{userName}";
    public static final String DELETE_USER_BY_NAME = "/v2/user/{userName}";

    //endpoints for pet
    public static final String CREATE_PET = "/v2/pet";
    public static final String GET_PET_BY_ID = "/v2/pet/{petId}";
    public static final String UPDATE_PET_BY_NAME = "/v2/pet/{name}";
    public static final String DELETE_PET_BY_ID = "/v2/pet/{petId}";
}
