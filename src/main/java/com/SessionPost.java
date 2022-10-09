package com;

import cn.hutool.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SessionPost {
    public static void main(String[] args) throws IOException {

        //刷取类型 1 是话题 2 是羊群 默认羊群
        int rand_state = 2;

        //过关时间默认是 5 分钟 所以（5 * 60） 可修改为 1
        int rank_time = 1;

        //请输入你刷取的次数
        int nums = 1;

        //请输入你的游戏UID
//        String uid = "";

        //填入你的昵称（可选）
        String nick_name = "梦境";

        HttpGet GetMethod = null;

        //创建httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建get请求方式实例
        HttpGet httpGet = new HttpGet("https://cat-match.easygame2021.com/sheep/v1/game/user_info?uid=24289147&t=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTQ0NzgwMTAsIm5iZiI6MTY2MzM3NTgxMCwiaWF0IjoxNjYzMzc0MDEwLCJqdGkiOiJDTTpjYXRfbWF0Y2g6bHQxMjM0NTYiLCJvcGVuX2lkIjoiIiwidWlkIjo0ODUwNDY1MCwiZGVidWciOiIiLCJsYW5nIjoiIn0.IEjNoHJiJPqlh86DqDS3-SMTwErTCatQF6ykZk4o-Yc");


        //为get请求添加Header
        httpGet.addHeader("content-type", "application/json;charset=utf-8");
        httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
        httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        httpGet.addHeader("Accept-Encoding", "gzip, deflate");
        httpGet.addHeader("Connection", "keep-alive");
        httpGet.addHeader("Upgrade-Insecure-Requests", "1");
        httpGet.addHeader("Pragma", "no-cache");
        httpGet.addHeader("Cache-Control", "no-cache");
        httpGet.addHeader("Host", "cat-match.easygame2021.com");
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 15_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/8.0.27(0x18001b36) NetType/WIFI Language/zh_HK");


        CloseableHttpResponse getResponse = httpClient.execute(httpGet);

        HttpEntity entity = getResponse.getEntity();
//        System.out.println("=================");
//        System.out.println("=================");
//        System.out.println("entity:"+entity);
//        System.out.println("entitye.getContent:"+entity.getContent());
//        System.out.println("entity字符串:"+EntityUtils.toString(entity));
        String res = EntityUtils.toString(entity);
        JSONObject dataJson = new JSONObject(res);
        Object arr = dataJson.get("data");
        JSONObject data = new JSONObject(arr);
        System.out.println("wx_open_id:" + data.get("wx_open_id"));
        System.out.println("avatar:" + data.get("avatar"));

        String openId = (String) data.get("wx_open_id");
        String avatar = (String) data.get("avatar");

//        HttpPost httpPost = new HttpPost("https://cat-match.easygame2021.com/sheep/v1/user/login_oppo");
//
//        //为post请求添加Header
//        httpPost.addHeader("content-type","application/json;charset=utf-8");
//        httpPost.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
//        httpPost.addHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
//        httpPost.addHeader("Accept-Encoding","gzip, deflate");
//        httpPost.addHeader("Connection","keep-alive");
//        httpPost.addHeader("Upgrade-Insecure-Requests","1");
//        httpPost.addHeader("Pragma","no-cache");
//        httpPost.addHeader("Cache-Control","no-cache");
//        httpPost.addHeader("Host","cat-match.easygame2021.com");
//        httpPost.addHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 15_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/8.0.27(0x18001b36) NetType/WIFI Language/zh_HK");
//
//        JSONObject postJson = new JSONObject();
//        postJson.set("uid",openId);
//        postJson.set("nick_name",nick_name);
//        postJson.set("avatar"," ");
//        postJson.set("sex",1);
//
//
//        httpPost.setEntity(new StringEntity(postJson.toString(),"UTF-8"));
//
//        CloseableHttpResponse postResponse = httpClient.execute(httpPost);
//        HttpEntity postEntity = postResponse.getEntity();
//        System.out.println("状态码:"+postResponse.getStatusLine());
//        System.out.println("postResponse:"+postResponse);
//        System.out.println("postEntity:"+postEntity);
//        String postRes = EntityUtils.toString(postEntity);
//        System.out.println("postRes:"+postRes);
//        //获取token
//        JSONObject datajson = new JSONObject(postRes);
//        System.out.println("datajson:"+datajson.get("data"));
//        Object arr1 = datajson.get("data");
//        JSONObject tokenData = new JSONObject(arr1);
//        System.out.println("token:"+tokenData.get("token"));
//        String token = (String) tokenData.get("token");

//        for (int i = 0; i < 10; i++) {
//          HttpGet httpGet1 = new HttpGet("https://cat-match.easygame2021.com/sheep/v1/game/0?rank_score=1&rank_state=1&rank_time=1&rank_role=2&skin=1&t=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTQ3MDAwNDksIm5iZiI6MTY2MzU5Nzg0OSwiaWF0IjoxNjYzNTk2MDQ5LCJqdGkiOiJDTTpjYXRfbWF0Y2g6bHQxMjM0NTYiLCJvcGVuX2lkIjoiIiwidWlkIjoyNDI4OTE0NywiZGVidWciOiIiLCJsYW5nIjoiIn0.XAcdQkCK7Mf_LtC73NG7ZPSfWrNQy8HkOq8pbU3mq58");
        HttpGet httpGet1 = new HttpGet("http://cat-match.easygame2021.com/sheep/v1/game/game_over?rank_score=1&rank_state=1&rank_time=1&rank_role=1&skin=1&t=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTQyNDI3NDEsIm5iZiI6MTY2MzE0MDU0MSwiaWF0IjoxNjYzMTM4NzQxLCJqdGkiOiJDTTpjYXRfbWF0Y2g6bHQxMjM0NTYiLCJvcGVuX2lkIjoiIiwidWlkIjoyNDI4OTE0NywiZGVidWciOiIiLCJsYW5nIjoiIn0.uoYFVBfrAOamLWPjNvbp882AFke-bYm1a4AHW4trV4I");
        CloseableHttpResponse response = httpClient.execute(httpGet1);
        HttpEntity getEntity1 = response.getEntity();
//        }
        String da = EntityUtils.toString(getEntity1);
        System.out.println("###" + getEntity1);
        System.out.println("res:" + da);
    }
}
