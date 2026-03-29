package com.qa.api.gorest;

import com.qa.api.base.Base;
import com.qa.api.client.RestClient;
import com.qa.api.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class GetUserTest extends Base {

    @Test
    public void getAllUserTest()
    {
        Response response = restClient.get(GOREST_BASE_URL,GOREST_END_POINT,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(response.statusLine().contains("OK"));
    }

    @Test
    public void getUserQueryParam()
    {
        HashMap<String,String> queryMap = new HashMap<>();
        queryMap.put("name","naveen");
        queryMap.put("status","active");
        Response response = restClient.get(GOREST_BASE_URL,GOREST_END_POINT,queryMap,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(response.statusLine().contains("OK"));
    }

    @Test
    public void getSingleUser()
    {
        String userId="8414520";
        Response response = restClient.get(GOREST_BASE_URL,GOREST_END_POINT+"/"+userId,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(response.statusLine().contains("OK"));
        Assert.assertEquals(response.jsonPath().getString("id"),userId);
    }


}
