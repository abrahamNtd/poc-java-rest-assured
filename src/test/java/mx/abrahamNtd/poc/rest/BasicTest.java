package mx.abrahamNtd.poc.rest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import static org.junit.Assert.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BasicTest {

    @Test
    public void testHiApi() {
    	RestAssured.baseURI  = "http://localhost:8080";
    	Response res = given()
    		.contentType("application/json")
    		.when().get("/hi?name=abraham")
    		.then()
    		.assertThat()
    		.statusCode( 200 ).extract().response();
    }

}