package me.thrid.auth;

import com.alibaba.fastjson.JSONObject;
import me.thrid.auth.config.ThridAuthConfig;
import me.thrid.auth.interfaces.GiteeService;
import me.thrid.auth.po.GiteeToken;
import me.thrid.auth.po.GiteeUser;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GiteeServiceImpl implements GiteeService {

    @Autowired
    private ThridAuthConfig loginConfig;

    @Override
    public GiteeUser getGiteeUser(String code) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
       List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("grant_type" , "authorization_code"));
        params.add(new BasicNameValuePair("code" , code));
        params.add(new BasicNameValuePair("client_id" ,loginConfig.getGiteeClientId()));
        params.add(new BasicNameValuePair("redirect_uri" ,loginConfig.getGiteeCallBackUrl()));
        params.add(new BasicNameValuePair("client_secret" ,loginConfig.getGiteeScrect()));
        CloseableHttpResponse response = null;
        try {
        HttpPost httpPost = new HttpPost("https://gitee.com/oauth/token");
        httpPost.setHeader("User-Agent" , "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0");
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        response = httpClient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());
        GiteeToken token= JSONObject.parseObject(result , GiteeToken.class);
        return geAccessTokentUserInfo(token.getAccess_token());
    } catch (Exception e) {
        throw new Exception("获取token请求异常，参考：%s" +e.getMessage());
    }
}

    /**
     * 根据授权token获取对应的用户详细信息
     * @return
     */
    private GiteeUser geAccessTokentUserInfo(String accessToken) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://gitee.com/api/v5/user?access_token=" + accessToken);
        httpGet.setHeader("User-Agent" , "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            String result = EntityUtils.toString(response.getEntity());
            return JSONObject.parseObject(result , GiteeUser.class);
        } catch (Exception e) {
           throw new Exception(String.format("获取token请求异常，参考：%s" , e.getMessage()));
        }
    }
}
