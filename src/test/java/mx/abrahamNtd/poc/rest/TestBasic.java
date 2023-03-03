package mx.abrahamNtd.poc.rest;

import mx.abrahamNtd.poc.reports.ExtentTestManager;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TestBasic {

    @Test(groups = {"smokeTest"})
    public void say_hi_for_a_given_name() {

        ExtentTestManager.getTest()
            .given("")
            .contentType("application/json")
            .when()
            .get("/hi?name=abraham")
            .then()
            .assertThat()
            .statusCode( 200 ).extract().response();
    }

    @Test(groups = {"smokeTest"})
    public void calculate_the_factorial_value_for_a_given_number() {

        ExtentTestManager.getTest()
            .given("")
            .contentType("application/json")
            .when()
            .get("/factorial?number=3")
            .then()
            .assertThat()
            .statusCode( 200 ).extract().response();
    }

    @Test(groups = {"smokeTest"})
    public void calculate_the_addition_of_two_numbers() {

        ExtentTestManager.getTest()
            .given("")
            .contentType("application/json")
            .when()
            .get("/plus?a=3&b=4")
            .then()
            .assertThat()
            .statusCode( 200 ).extract().response();
    }
}