package com.qa.api.client;

import com.qa.api.constants.AuthType;
import com.qa.api.manager.configmanager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Base64;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.*;

public class RestClient {

    private ResponseSpecification responseSpec200 = expect().statusCode(200);
    private ResponseSpecification responseSpec201 = expect().statusCode(201);
    private ResponseSpecification responseSpec204 = expect().statusCode(204);
    private ResponseSpecification responseSpec400 = expect().statusCode(400);
    private ResponseSpecification responseSpec200or201 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));
    private ResponseSpecification responseSpec200or404 = expect().statusCode(anyOf(equalTo(200),equalTo(404)));


    public RequestSpecification setupRequest(String baseURL, AuthType authType, ContentType contentType)
    {
        RequestSpecification request = given().log().all()
                .baseUri(baseURL)
                .contentType(contentType)
                .accept(contentType);

        switch (authType)
        {
            case BEARER_TOKEN:
                request.header("Authorization", "Bearer "+ configmanager.get("bearerToken"));
                break;
            case OATH2:
                request.header("Authorization", "Bearer "+"");
                break;
            case BASIC_AUTH:
                request.header("Authorization", "Basic "+generateBasicAuth());
                break;
            case API_KEY:
                request.header("Authorization", "Bearer "+"");
                break;
            case NO_AUTH:
                System.out.println("Auth is not required");
                break;
            default:
                break;
        }
        return request;
    }

    public void applyParams(RequestSpecification request, Map<String,String> queryMap,Map<String,String> pathMap)
    {
        if(queryMap!=null)
        {
            request.queryParams(queryMap);
        }
        if(pathMap!=null)
        {
            request.pathParams(pathMap);
        }
    }

    public Response get(String baseURL, String endPoint, Map<String,String> queryParam,
                        Map<String,String> pathParam, AuthType authType, ContentType contentType)
    {
        RequestSpecification request = setupRequest(baseURL,authType,contentType);
        applyParams(request,queryParam,pathParam);
        Response response = request.get(endPoint).then().spec(responseSpec200or404).extract().response();
        response.prettyPrint();
        return response;
    }

    public <T>Response post(String baseURL, String endPoint, T body, Map<String,String> queryParam, Map<String,String> pathParam, AuthType authType, ContentType contentType)
    {
        RequestSpecification request = setupRequest(baseURL,authType,contentType);
        applyParams(request,queryParam,pathParam);
        Response response =request.body(body).post(endPoint).then().spec(responseSpec200or201).extract().response();
        response.prettyPrint();
        return response;
    }

    public <T>Response put(String baseURL, String endPoint, T body, Map<String,String> queryParam, Map<String,String> pathParam, AuthType authType, ContentType contentType)
    {
        RequestSpecification request = setupRequest(baseURL,authType,contentType);
        applyParams(request,queryParam,pathParam);
        Response response =request.body(body).put(endPoint).then().spec(responseSpec200).extract().response();
        response.prettyPrint();
        return response;
    }

    public <T>Response patch(String baseURL, String endPoint, T body, Map<String,String> queryParam, Map<String,String> pathParam, AuthType authType, ContentType contentType)
    {
        RequestSpecification request = setupRequest(baseURL,authType,contentType);
        applyParams(request,queryParam,pathParam);
        Response response =request.body(body).patch(endPoint).then().spec(responseSpec200).extract().response();
        response.prettyPrint();
        return response;
    }

    public Response delete(String baseURL, String endPoint, Map<String,String> queryParam, Map<String,String> pathParam, AuthType authType, ContentType contentType)
    {
        RequestSpecification request = setupRequest(baseURL,authType,contentType);
        applyParams(request,queryParam,pathParam);
        Response response =request.delete(endPoint).then().spec(responseSpec204).extract().response();
        response.prettyPrint();
        return response;
    }

    public String generateBasicAuth()
    {
        String credentials = configmanager.get("username")+":"+configmanager.get("password");
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }








}
