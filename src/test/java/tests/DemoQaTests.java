package tests;

import io.qameta.allure.Owner;
import models.LoginBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.ProfilePage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.LoginSpec.*;

public class DemoQaTests extends TestBase {

    ProfilePage profilePage = new ProfilePage();
    TestData testData = new TestData();

    @Test
    @Owner("Kwlad1ck")
    @DisplayName("Авторизация юзреа")
    void emptyBookListTest() {
        LoginBodyModel loginData = new LoginBodyModel();
        loginData.setUserName(testData.username);
        loginData.setPassword(testData.password);

        LoginResponseModel loginResponseModel = step("Make request", () ->
                given(defaultLoggingSpec)
                        .body(loginData)

                        .when()
                        .post("/Account/v1/Login")

                        .then()
                        .spec(loginUserResponseSpec)
                        .statusCode(200)
                        .extract().as(LoginResponseModel.class));
        
        open("https://demoqa.com/images/Toolsqa.jpg");
        getWebDriver().manage().addCookie(
                new Cookie("userID", loginResponseModel.getUserId()));
        getWebDriver().manage().addCookie(
                new Cookie("token", loginResponseModel.getToken())
        );
        getWebDriver().manage().addCookie(
                new Cookie("expires", loginResponseModel.getExpires())
        );

        profilePage.openPage()
                .checkDataElement();
    }
}
