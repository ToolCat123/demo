package com.cn.client1.pojo;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yc
 */

@ConfigurationProperties(prefix = "cas.server")
public class CasServerProperties {
    private String prefix;
    private String login;
    private String logout;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }
}
