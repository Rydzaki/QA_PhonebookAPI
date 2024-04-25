package com.phonebook.testsRA;

import com.phomebook.dto.AuthRequestDto;
import com.phomebook.dto.AuthResponseDto;
import com.phomebook.dto.ErrorDto;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LoginTests extends TestBase
{
    AuthRequestDto auth = AuthRequestDto.builder()
            .username("autest@mail.com")
            .password("Pass12345!")
            .build();

    @Test
    public void loginSuccessTest(){

        AuthResponseDto dto = given()
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDto.class);

        System.out.println(dto.getToken());

    }

    @Test
    public void loginSuccessTest2(){

        String responseToken = given()
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .body(containsString("token"))
                .extract().path("token");

        System.out.println(responseToken);
    }

    @Test
    public void logonWithWrongEmail(){
        /*ErrorDto errorDto = */given().body(AuthRequestDto.builder().username("autestmail.com").password("Pass12345!")
                .build())
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
               /*.extract().response().as(ErrorDto.class);*/
                .assertThat().body("message", equalTo("Login or Password incorrect"));


        /*System.out.println(errorDto.getMessage());
        System.out.println(errorDto.getError());*/
    }


}
