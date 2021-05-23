package tests;

import com.codeborne.selenide.Configuration;
import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    static ProjectConfig projectConfig = ConfigFactory.create
            (ProjectConfig.class, System.getProperties());

    @BeforeAll
    public static void init() {
        Configuration.baseUrl = projectConfig.getWebUrl();
    }

}
