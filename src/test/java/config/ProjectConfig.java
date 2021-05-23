package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({"system:properties",
        "classpath:project.properties"})
public interface ProjectConfig extends Config {

    @Key("api.base.url")
    String getApiUrl();

    @Key("web.base.url")
    String getWebUrl();

}
