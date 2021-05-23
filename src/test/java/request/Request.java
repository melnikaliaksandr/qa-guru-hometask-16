package request;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Request {

    public static Response post(String path, String body, String cookieName, String cookie) {
        return given()
                .spec(Specification.getRequestSpecification())
                .body(body)
                .cookie(cookieName, cookie)
                .post(path)
                .then()
                .spec(Specification.getResponseSpecification())
                .extract()
                .response();
    }

}
