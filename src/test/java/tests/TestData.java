package tests;

import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;

public class TestData {
    final AuthConfig authConfig = ConfigFactory.create(AuthConfig.class);
    public final String username = authConfig.userName();
    public final String password = authConfig.password();
}
