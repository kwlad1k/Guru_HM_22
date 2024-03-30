package tests;

import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;

public class TestData {
    AuthConfig authConfig = ConfigFactory.create(AuthConfig.class);
    public String username = authConfig.userName();
    public String password = authConfig.password();
}
