package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    private SelenideElement
            noDataElemet = $(".rt-noData");

    @Step("Открытия страницы профиля")
    public ProfilePage openPage() {
        open("/profile");

        return this;
    }

    @Step("Проверка видимости элемента")
    public ProfilePage checkDataElement() {

        noDataElemet.shouldBe(visible);
        noDataElemet.shouldHave(text("No rows found"));

        return this;
    }
}
