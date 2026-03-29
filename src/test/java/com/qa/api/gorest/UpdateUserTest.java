package com.qa.api.gorest;

import com.qa.api.base.Base;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.UserPojo;
import com.qa.api.utility.StringUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserTest extends Base {

    @Test
    public void updateUserTest()
    {
        UserPojo userPojo = new UserPojo("priyanka", StringUtils.getEmailId(),"female","active");
        Response response = restClient.post(GOREST_BASE_URL,GOREST_END_POINT,userPojo,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.jsonPath().getString("name"),"priyanka");
        Assert.assertNotNull(response.jsonPath().getString("id"));

        String userId = response.jsonPath().getString("id");
        System.out.println(userId);

        Response responseGet = restClient.get(GOREST_BASE_URL,GOREST_END_POINT+"/"+userId,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(responseGet.statusLine().contains("OK"));
        Assert.assertEquals(responseGet.jsonPath().getString("id"),userId);

        userPojo.setName("NewUser");
        Response responsePut =restClient.put(GOREST_BASE_URL,GOREST_END_POINT+"/"+userId,userPojo,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(responsePut.statusLine().contains("OK"));
        Assert.assertEquals(responsePut.jsonPath().getString("id"),userId);
        Assert.assertEquals(responsePut.jsonPath().getString("name"),"NewUser");

    }
}
