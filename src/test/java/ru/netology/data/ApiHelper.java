package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public static RequestSpecification requestSpec = new RequestSpecBuilder()
        .setBaseUri("http://localhost")

        .setPort(8080)
        .setAccept(ContentType.JSON)
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();

    public static String requestApi(DataHelper.CardInfo card, String path){
        return given()
                .spec(requestSpec)
                .body( card )
                .when()
                .post( path )
                .then()
                .statusCode( 200 )
                .extract().response().asString();
    }


}