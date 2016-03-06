package com.ifusion.configuration;

import org.apache.commons.lang3.StringUtils;

public class ApplicationConfiguration {

    private static final int APP_PORT = 8306;
    private static final String APP_HOST = "http://localhost";

    public static int appPort() {
        String port = System.getenv("PORT");
        if(StringUtils.isEmpty(port)) {
            return APP_PORT;
        }
        return Integer.parseInt(port);
    }

    public static String appHost() {
        String host = System.getenv("HOST");
        if(StringUtils.isEmpty(host)) {
            return APP_HOST + ":" + appPort();
        }
        return host;
    }
}
