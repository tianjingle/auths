package me.thrid.auth.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.thrid.auth.po.WxToken;

public class TokenUtils {

    public static WxToken wxToken=null;

    private static String appId="wxdc1a0fca5ed27731";

    private static String appSecret="ac57fab1e553323d5bd584f97d31511a";


    public void TokenUtils() {
        System.out.println("-----启动AccessTokenServlet-----");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //获取accessToken
                        TokenUtils.wxToken = getAccessToken(appId, appSecret);
                        //获取成功
                        if (TokenUtils.wxToken != null) {
                            //获取到access_token 休眠7000秒,大约2个小时左右
                            Thread.sleep(7000 * 1000);
                        } else {
                            //获取失败
                            Thread.sleep(1000 * 3); //获取的access_token为空 休眠3秒
                        }
                    } catch (Exception e) {
                        System.out.println("发生异常：" + e.getMessage());
                        e.printStackTrace();
                        try {
                            Thread.sleep(1000 * 10); //发生异常休眠1秒
                        } catch (Exception e1) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    private WxToken getAccessToken(String appId, String appSecret) {
        NetWorkUtil netHelper = new NetWorkUtil();
        /**
         * 接口地址为https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET，其中grant_type固定写为client_credential即可。
         */
        String Url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", TokenUtils.appId, TokenUtils.appSecret);
        //此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
        String result = netHelper.getHttpsResponse(Url, "");
        System.out.println("获取到的access_token="+result);

        //使用FastJson将Json字符串解析成Json对象
        JSONObject json = JSON.parseObject(result);
        WxToken token = new WxToken();
        token.setAccess_token(json.getString("access_token"));
        token.setExpires_in(json.getInteger("expires_in"));
        return token;
    }
}
