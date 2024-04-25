package com.phonebook.testsRA;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {


    public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYXV0ZXN0QG1haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE3MTQ2Mzg3ODQsImlhdCI6MTcxNDAzODc4NH0.IMSeFc7F4VJK-smhF-sikE-iGqjaUV-P0wjocguygDo";
    public static final String TOKEN_WRONG = "fails token";
    public static final String AUTH = "Authorization";


    @BeforeMethod
    public void init() {

        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";

    }

}
