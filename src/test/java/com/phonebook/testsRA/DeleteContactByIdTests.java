package com.phonebook.testsRA;

import com.phomebook.dto.ContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactByIdTests extends TestBase {

    String id = "29108cfe-1692-4a07-a662-87d87ec7dbb9";

    /*@BeforeMethod
    public void recondition() {
        ContactDto contactDto = ContactDto.builder()
                .name("Jo")
                .lastName("Brad")
                .email("test@mail.com")
                .phone("1234567890")
                .address("London")
                .description("Doc")
                .build();

        String message = given()
                .header(AUTH, TOKEN)
                .body(contactDto)
                .contentType(ContentType.JSON)
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().path("message");

        System.out.println(message);

        String[] split = message.split(": ");
        id = split[1];

    }*/

    @Test
    public void deleteContactByIdSuccessTest() {

        /* String message = */
        given()
                .header(AUTH, TOKEN)
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(200)
                /*.extract().path("message");*/
                .assertThat().body("message", equalTo("Contact was deleted!"));

        /*System.out.println(message);*/

    }

    @Test
    public void deleteContactByIdAnyFormatErrorTest() {

        String nonExistentId = "10e0277b-a414-4393-b883-66729d1e3399";
        /*String message =*/
        given()
                .header(AUTH, TOKEN)
                .delete("contacts/" + nonExistentId)
                .then()
                .assertThat().statusCode(400)
                /*.extract().path("message");*/
                .assertThat().body("message", equalTo("Contact with id: " + nonExistentId + " not found in your contacts!")); //согласно API это должна быть 404 ошибка, неправильный формат также 400 ошибка

        /*  System.out.println(message);*/

    }

    @Test
    public void deleteContactByIdUnauthorizedTest() {

        /*String message =*/
        given()
                .header(AUTH, TOKEN_WRONG)
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(401)
                /*.extract().path("message");*/
                .assertThat().body("message", equalTo("JWT strings must contain exactly 2 period characters. Found: 0"));

        /*System.out.println(message);*/

    }

    @Test
    public void deleteContactByIdContactNotFoundTest() {

        given()
                .header(AUTH, TOKEN)
                .delete("contactssss/" + id)//ошибка в поинде - 403 код отсутствует в API
                .then()
                .assertThat().statusCode(403);

    }


}
