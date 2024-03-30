package tests;

import extensions.WithLogin;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

public class DemoQaTests extends TestBase {

    ProfilePage profilePage = new ProfilePage();

    @Test
    @WithLogin
    @Owner("Kwlad1ck")
    @Feature("Profile")
    @DisplayName("Проверка пустого списка книг")
    void emptyBookListWithLoginExtensionsTest() {

        profilePage.openPage()
                .checkDataElement();
    }
}
