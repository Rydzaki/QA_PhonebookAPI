package com.phonebook.testsRA;

import com.phomebook.dto.AllContactsDto;
import com.phomebook.dto.ContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetAllContactsTests extends TestBase {

    @Test
    public void getAllContactsSuccessTest(){
        AllContactsDto contactsDto = given()
                .header(AUTH, TOKEN)
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(AllContactsDto.class);

        for (ContactDto contact: contactsDto.getContacts()) {
            System.out.println(contact.getId()+ "***" + contact.getName());
            System.out.println("================================");

        }
    }

    @Test
    public void getAllContactsNegativeTest(){
        /*AllContactsDto contactsDto */
        /*String wrongRequest = */given()
                .header(AUTH, TOKEN_WRONG)
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(401)
               /* .extract().path("message");*/
        .assertThat().body("message", equalTo("JWT strings must contain exactly 2 period characters. Found: 0"));
        /*System.out.println(wrongRequest);*/

    }
    @Test
    public void getAllContactsNegativeTestWrongUrl(){
        /*AllContactsDto contactsDto */
        given()
                .header(AUTH, TOKEN)
                .when()
                .get("contactssss")
                .then()
                .assertThat().statusCode(403);

    }


    
}
