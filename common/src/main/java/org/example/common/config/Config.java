package org.example.common.config;

import org.aeonbits.owner.ConfigFactory;

public class Config {
    public static final PropertiesConfig INSTANCE =
            ConfigFactory.create(PropertiesConfig.class, System.getProperties());
}