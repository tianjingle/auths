package me.thrid.auth.po;


import java.util.Date;

public class UserTempDTO {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户手机号码
     */
    private String phone;

    /**
     * 用户头像地址
     */
    private String avatarUrl;

    /**
     * 用户创建的时间
     */
    private Date createTime;

    /**
     * gitee用户的login
     */
    private String giteeLogin;

    /**
     * gitee用户的名称
     */
    private String giteeName;

    /**
     * 微信用户的id
     */
    private String wxUserid;

    /**
     * 微信用户的名称
     */
    private String wxUserName;

    /**
     * 用户状态
     */
    private Integer userState;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGiteeLogin() {
        return giteeLogin;
    }

    public void setGiteeLogin(String giteeLogin) {
        this.giteeLogin = giteeLogin;
    }

    public String getGiteeName() {
        return giteeName;
    }

    public void setGiteeName(String giteeName) {
        this.giteeName = giteeName;
    }

    public String getWxUserid() {
        return wxUserid;
    }

    public void setWxUserid(String wxUserid) {
        this.wxUserid = wxUserid;
    }

    public String getWxUserName() {
        return wxUserName;
    }

    public void setWxUserName(String wxUserName) {
        this.wxUserName = wxUserName;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }
}
