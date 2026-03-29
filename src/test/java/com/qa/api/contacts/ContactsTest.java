package com.qa.api.contacts;

import com.qa.api.base.Base;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.configmanager;
import com.qa.api.pojo.ContactCredentialsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactsTest extends Base {

    private String tokenId;

    @BeforeMethod
    public void setup()
    {
        ContactCredentialsPojo contactCredentialsPojo = new ContactCredentialsPojo("rapanomik@gmail.com","vicky123");

        Response response = restClient.post(CONTACTS_BASE_URL,CONTACTS_LOGIN_END_POINT,contactCredentialsPojo,null,null, AuthType.NO_AUTH, ContentType.JSON);
        tokenId = response.jsonPath().getString("token");
        System.out.println("Token is "+tokenId);
        configmanager.set("bearerToken",tokenId);
    }

    @Test
    public void getAllContacts()
    {

        restClient.get(CONTACTS_BASE_URL,CONTACTS_END_POINT,null,null,AuthType.BEARER_TOKEN,ContentType.JSON);

    }


}
