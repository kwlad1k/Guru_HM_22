package extensions;

import io.qameta.allure.Step;
import models.LoginBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;
import tests.TestData;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.LoginSpec.defaultLoggingSpec;
import static specs.LoginSpec.loginUserResponseSpec;

public class LoginExtension implements BeforeEachCallback {

    final TestData testData = new TestData();
    @Step("Авторизация юзера через API")
    @Override
    public void beforeEach(ExtensionContext context) {
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
        step("Открытие предварительной ссылки", () ->
                open("/images/Toolsqa.jpg"));

        step("Подкладывание куки в браузер для авторизации учетной записи", () -> {
            getWebDriver().manage().addCookie(
                    new Cookie("userID", loginResponseModel.getUserId()));
            getWebDriver().manage().addCookie(
                    new Cookie("token", loginResponseModel.getToken())
            );
            getWebDriver().manage().addCookie(
                    new Cookie("expires", loginResponseModel.getExpires())
            );
        });

    }
}
