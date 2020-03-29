package me.thrid.auth.utils;


import me.thrid.auth.po.WxPublicKey;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class WxPublicKeyFactory {

    /**
     * key容器
     */
    public static ConcurrentHashMap<String, WxPublicKey> concurrentHashMap=new ConcurrentHashMap<>(480);

    /***
     * @TODO 需要改成定时任务
     * 过期淘汰
     */
    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (concurrentHashMap.size() > 0) {
                        Iterator<Map.Entry<String, WxPublicKey>> sets = concurrentHashMap.entrySet().iterator();
                        while (sets.hasNext()) {
                            Map.Entry<String, WxPublicKey> entity = sets.next();
                            if (entity.getValue().getExpires_in() <= System.currentTimeMillis()) {
                                concurrentHashMap.remove(entity.getKey());
                                System.out.println("回收用户"+entity.getKey());
                            }
                        }
                    }
                    try {
                        int time=2000;
                        if (concurrentHashMap.size()<200){
                            time=4000;
                        }
                        System.out.println("sleep");
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 生成key
     * @param fromUserName
     * @return
     */
    public static String generate(String fromUserName){
        if (null!=fromUserName&&!"".equals(fromUserName)){
            String uid= UUID.randomUUID().toString();
            WxPublicKey wxPublicKey=new WxPublicKey();
            wxPublicKey.setExpires_in(System.currentTimeMillis()+60000);
            wxPublicKey.setKey(uid);
            concurrentHashMap.put(fromUserName,wxPublicKey);
            return uid;
        }
        return null;
    }


    /**
     * 校验并返回用户name
     * @param key
     * @return
     */
    public static String getFromUserName(String key){
        String fromUserName=null;
        Iterator<Map.Entry<String, WxPublicKey>> sets = concurrentHashMap.entrySet().iterator();
        while (sets.hasNext()) {
            Map.Entry<String, WxPublicKey> entity = sets.next();
            if (key.equals(entity.getValue().getKey())) {
                concurrentHashMap.remove(entity.getKey());
                fromUserName= entity.getKey();
                break;
            }
        }
        return fromUserName;
    }
}
