package org.example.common.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:config.properties"})
public interface PropertiesConfig extends Config {

    @Key("URL")
    String url();

    @Key("MODE")
    String mode();

    @Key("TIMEOUT")
    int timeout();

    @Key("ADMIN_LOGIN")
    String adminLogin();

    @Key("ADMIN_PASSWORD")
    String adminPassword();

    @Key("BASE_GOOD_NAME")
    String baseGoodName();

    @Key("BASE_GOOD_PRICE")
    int baseGoodPrice();

    @Key("GOOD_NAME")
    String goodName();

    @Key("LOG_MODE")
    String logMode();
}