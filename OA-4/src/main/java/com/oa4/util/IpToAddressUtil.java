package com.oa4.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IpToAddressUtil {
    public static void main(String[] args) {
//        System.out.println(getV4IP());
//        先获取坐标
//        String cor = getCityInfo(getV4IP() , "location" , "");
//        再获取地址
        System.out.println(getCityInfo("" , "geocoder" , "26.080417,119.16418"));
    }
    //使用腾讯的接口通过ip拿到城市信息
    private static final String KEY = "JSTBZ-UEPWK-SEMJX-APSBD-YRJFK-IMBIM";
    public static String getCityInfo(String ip,String url,String cor)  {
        String s = sendGet(ip, KEY,url,cor);
        Map map = JSONObject.parseObject(s, Map.class);
        String message = (String) map.get("message");
        if("query ok".equals(message)){
            if(url.equals("location")){
                Map result = (Map) map.get("result");
                Map location = (Map) result.get("location");
                return location.get("lat")+","+location.get("lng");
            }else if (url.equals("geocoder")){
                Map result = (Map) map.get("result");
                return (String) result.get("address");
            }
        }else{
            return null;
        }
        return null;
    }
    //根据在腾讯位置服务上申请的key进行请求操作
    public static String sendGet(String ip, String key,String url,String cor) {
        String result = "";
        String urlNameString = "";
        BufferedReader in = null;
        try {
            if(url.equals("geocoder")){
                urlNameString = "https://apis.map.qq.com/ws/geocoder/v1/?location="+cor+"&key="+key+"&get_poi=0&poi_options=radius=1";
            }else if(url.equals("location")){
                 urlNameString = "https://apis.map.qq.com/ws/location/v1/ip?ip="+ip+"&key="+key;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
//        System.out.println(result);
        return result;
    }

    public static String getV4IP(){
        String ip = "";
        String chinaz = "https://ip.chinaz.com/";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            while((read=in.readLine())!=null){
                inputLine.append(read+"\r\n");
            }
//            System.out.println(inputLine.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if(m.find()){
            String ipstr = m.group(1);
            ip = ipstr;
//            System.out.println(ipstr);
        }
        return ip;
    }
}
