package com.phonebook.testsRA;

import com.phomebook.dto.ContactDto;
import com.phomebook.dto.UpdateContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateContactTests extends TestBase {

    String id/* = "10e0277b-a414-4393-b883-66729d1e3399"*/;

    @BeforeMethod
    public void precondition() {
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

        String[] split = message.split(": ");
        id = split[1];

    }

    @Test
    public void updateContactTest() {

        UpdateContactDto updateContactDto = UpdateContactDto.builder()
                .id(id)
                .name("Bill_test3")
                .lastName("Trump1")
                .email("test@mail.com")
                .phone("1234567890")
                .address("London")
                .description("Doc")
                .build();

        /*String messageAboutUpdate =*/
        given()
                .header(AUTH, TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .body(updateContactDto)
                .put("contacts")
                .then()
                .assertThat().statusCode(200)
                /*.extract().path("message");*/
                .assertThat().body("message", equalTo("Contact was updated"));
        /*System.out.println(messageAboutUpdate);*/

    }

    @AfterMethod(enabled = true)
    public void postcondition() {

        given()
                .header(AUTH, TOKEN)
                .delete("contacts/" + id);
    }

}