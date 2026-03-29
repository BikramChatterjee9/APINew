package com.qa.api.products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qa.api.base.Base;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.ProductPojo;
import com.qa.api.utility.JsonUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductTest extends Base {

    @Test
    public void getProductTest() throws JsonProcessingException {
        Response response = restClient.get(PRODUCTS_BASE_URL,PRODUCTS_END_POINT,null,null, AuthType.NO_AUTH, ContentType.ANY);
        Assert.assertEquals(response.statusCode(),200);

        ProductPojo[] productPojo= JsonUtils.deserelise(response, ProductPojo[].class);






    }




}
