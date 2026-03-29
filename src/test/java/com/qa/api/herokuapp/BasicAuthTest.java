package com.qa.api.herokuapp;

import com.qa.api.base.Base;
import com.qa.api.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BasicAuthTest extends Base {

    @Test
    public void basicAuth()
    {
        Response response = restClient.get(HEROKUAPP_BASE_URL,HEROKUAPP_END_POINT,null,null, AuthType.BASIC_AUTH, ContentType.ANY);
        Assert.assertTrue(response.getBody().asString().contains("Congratulations! You must have the proper credentials."));
    }
}
