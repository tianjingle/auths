package me.thrid.auth.interfaces;


import me.thrid.auth.po.GiteeUser;
//import me.zhyd.oauth.model.AuthCallback;

public interface GiteeService {

//    GiteeUser getGiteeUser(AuthCallback callback) throws Exception;
    GiteeUser getGiteeUser(String code) throws Exception;
}
