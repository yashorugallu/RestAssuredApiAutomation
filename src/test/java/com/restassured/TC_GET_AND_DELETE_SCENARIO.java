package com.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;


public class TC_GET_AND_DELETE_SCENARIO {

    @Test
    public void getRequestValidation(){
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET,"/employee/1");

        response.getBody().prettyPrint();

        // status code validation
        Assert.assertEquals("status code validation",200, response.getStatusCode());
        Assert.assertEquals("id validation","1",response.getBody().path("data.id").toString());
        LinkedHashMap hm = response.getBody().path("data");
        Assert.assertEquals("employee name validation", "Tiger Nixon", hm.get("employee_name"));
        Assert.assertEquals("employee salary validation", 320800, hm.get("employee_salary"));
        Assert.assertEquals("employee age validation", 61, hm.get("employee_age"));
        Assert.assertEquals("status message validation", "Successfully! Record has been fetched.",response.getBody().path("message").toString());
    }

    @Test
    public void deleteRequestValidation(){
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.DELETE, "/delete/2");

        // status code validation
        Assert.assertEquals("status code validation",200, response.getStatusCode());

        // status message validation
        Assert.assertEquals("status message validation","Successfully! Record has been deleted", response.getBody().path("message").toString());
    }
}
