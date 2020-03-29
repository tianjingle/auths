package me.thrid.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "thrid.login")
public class ThridAuthConfig {

    /**
     * gitee
     */
    private String giteeClientId;

    private String giteeScrect;

    private String giteeCallBackUrl;

    private String openWXClientId;

    private String openWxScrect;

    private String openWxCallBackUrl;

    private String publicWxToken;

    private String publicWxScrect;

    private String appId;

    private String appSecret;

    /**
     * 登录失败跳转的页面
     */
    private String failLoginPage;

    /**
     * 登录成功的页面
     */
    private String successLoginPage;

    public String getGiteeClientId() {
        return giteeClientId;
    }

    public void setGiteeClientId(String giteeClientId) {
        this.giteeClientId = giteeClientId;
    }

    public String getGiteeScrect() {
        return giteeScrect;
    }

    public void setGiteeScrect(String giteeScrect) {
        this.giteeScrect = giteeScrect;
    }

    public String getGiteeCallBackUrl() {
        return giteeCallBackUrl;
    }

    public void setGiteeCallBackUrl(String giteeCallBackUrl) {
        this.giteeCallBackUrl = giteeCallBackUrl;
    }

    public String getOpenWXClientId() {
        return openWXClientId;
    }

    public void setOpenWXClientId(String openWXClientId) {
        this.openWXClientId = openWXClientId;
    }

    public String getOpenWxScrect() {
        return openWxScrect;
    }

    public void setOpenWxScrect(String openWxScrect) {
        this.openWxScrect = openWxScrect;
    }

    public String getOpenWxCallBackUrl() {
        return openWxCallBackUrl;
    }

    public void setOpenWxCallBackUrl(String openWxCallBackUrl) {
        this.openWxCallBackUrl = openWxCallBackUrl;
    }

    public String getPublicWxToken() {
        return publicWxToken;
    }

    public void setPublicWxToken(String publicWxToken) {
        this.publicWxToken = publicWxToken;
    }

    public String getPublicWxScrect() {
        return publicWxScrect;
    }

    public void setPublicWxScrect(String publicWxScrect) {
        this.publicWxScrect = publicWxScrect;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getFailLoginPage() {
        return failLoginPage;
    }

    public void setFailLoginPage(String failLoginPage) {
        this.failLoginPage = failLoginPage;
    }

    public String getSuccessLoginPage() {
        return successLoginPage;
    }

    public void setSuccessLoginPage(String successLoginPage) {
        this.successLoginPage = successLoginPage;
    }
}
