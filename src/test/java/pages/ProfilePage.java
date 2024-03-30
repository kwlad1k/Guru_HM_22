package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    private final SelenideElement
            noDataElement = $(".rt-noData");

    @Step("Открытия страницы профиля")
    public ProfilePage openProfilePage() {
        open("/profile");

        return this;
    }

    @Step("Проверка видимости элемента")
    public ProfilePage checkDataElement() {

        noDataElement.shouldBe(visible);
        noDataElement.shouldHave(text("No rows found"));

        return this;
    }
}
