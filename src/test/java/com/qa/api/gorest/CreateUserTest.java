package com.qa.api.gorest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.qa.api.base.Base;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.UserPojo;
import com.qa.api.utility.StringUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserTest extends Base {


    @Test
    public void createUserTest()
    {
        UserPojo userPojo = new UserPojo("priyanka", StringUtils.getEmailId(),"female","active");
        Response response = restClient.post(GOREST_BASE_URL,GOREST_END_POINT,userPojo,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.jsonPath().getString("name"),"priyanka");
        Assert.assertNotNull(response.jsonPath().getString("id"));
    }


}
