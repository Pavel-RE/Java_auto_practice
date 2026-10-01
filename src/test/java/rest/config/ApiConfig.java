package rest.config;

import org.aeonbits.owner.Config;
@Config.LoadPolicy(
        Config.LoadType.FIRST
)
@Config.Sources(
        {
        "classpath:config.properties",
                "system:env"}
)

public interface ApiConfig extends Config {
    @Key("URL")
    public String url();

    @Key("MODE")
    public String mode();

    @Key("GOOD_NAME")
    public String goodName();

}



