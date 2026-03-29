package com.qa.api.base;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.client.RestClient;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(ChainTestListener.class)
public class Base {

    protected RestClient restClient;

    protected final static String GOREST_BASE_URL = "https://gorest.co.in";

    protected final static String GOREST_END_POINT = "/public/v2/users";

    protected final static String CONTACTS_BASE_URL = "https://thinking-tester-contact-list.herokuapp.com";

    protected final static String CONTACTS_LOGIN_END_POINT = "/users/login";

    protected final static String CONTACTS_END_POINT = "/contacts";

    protected final static String HEROKUAPP_BASE_URL = "https://the-internet.herokuapp.com";

    protected final static String HEROKUAPP_END_POINT = "/basic_auth";

    protected final static String PRODUCTS_BASE_URL = "https://fakestoreapi.com";

    protected final static String PRODUCTS_END_POINT = "/products";

    @BeforeSuite
    public void setupAllure()
    {
        RestAssured.filters(new AllureRestAssured());
    }

    @BeforeMethod
    public void setup()
    {
        restClient=new RestClient();
    }




}
