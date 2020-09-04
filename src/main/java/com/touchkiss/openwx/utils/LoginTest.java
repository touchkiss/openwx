package com.touchkiss.openwx.utils;

import com.touchkiss.openwx.bean.AppDetailResponse;
import com.touchkiss.openwx.bean.AppListResponse;
import com.touchkiss.openwx.bean.LoginResponse;
import com.touchkiss.openwx.bean.ModifyAppDetailResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2020/09/02 17:24
 *
 * @author Touchkiss
 */
public class LoginTest {
    static String token = "";
    static List<Header> basicHeaders = new ArrayList<>();
    static Map<String, String> cookies = new HashMap<>();

    static {
        basicHeaders.add(new BasicHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"));
        basicHeaders.add(new BasicHeader("accept-encoding", "gzip, deflate, br"));
        basicHeaders.add(new BasicHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8"));
        basicHeaders.add(new BasicHeader("dnt", "1"));
        basicHeaders.add(new BasicHeader("sec-fetch-dest", "document"));
        basicHeaders.add(new BasicHeader("sec-fetch-mode", "navigate"));
        basicHeaders.add(new BasicHeader("sec-fetch-site", "none"));
        basicHeaders.add(new BasicHeader("sec-fetch-user", "?1"));
        basicHeaders.add(new BasicHeader("upgrade-insecure-requests", "1"));
        basicHeaders.add(new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36"));
    }

    public static List<Header> getHeaders() {
        List<Header> headers = new ArrayList(basicHeaders);
        final StringBuilder stringBuilder = new StringBuilder();
        if (MapUtils.isNotEmpty(cookies)) {
            cookies.forEach((k, v) -> {
                stringBuilder.append(k);
                stringBuilder.append("=");
                stringBuilder.append(v);
                stringBuilder.append(";");
            });
            headers.add(new BasicHeader("cookie", stringBuilder.toString()));
        }
        return headers;
    }

    public static void homePage() {
        try {
            CloseableHttpResponse response = HttpUtil.getResponse("https://open.weixin.qq.com/", HttpUtil.DEFAULT_CONNECT_TIMEOUT, HttpUtil.DEFAULT_SOCKET_TIMEOUT, getHeaders());
            Header[] responseAllHeaders = response.getAllHeaders();
            for (Header responseHeader : responseAllHeaders) {
                if ("Set-Cookie".equals(responseHeader.getName())) {
                    String value = responseHeader.getValue();
                    cookies.put(value.substring(0, value.indexOf("=")), value.substring(value.indexOf("=") + 1, value.indexOf(";")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void login(String account, String passwd) {
        Map<String, String> params = new HashMap<>();
        params.put("token", "");
        params.put("lang", "zh_CN");
        params.put("f", "json");
        params.put("ajax", "1");
        params.put("key", "1");
        params.put("account", account);
        String pwd = MD5Util.MD5(passwd).toLowerCase();
        System.out.println(pwd);
        params.put("passwd", pwd);
        try {
            List<Header> headers = getHeaders();
            headers.add(new BasicHeader("referer", "https://open.weixin.qq.com/"));
            CloseableHttpResponse loginResponse = HttpUtil.postResponse("https://open.weixin.qq.com/cgi-bin/login"
                    , HttpUtil.DEFAULT_CONNECT_TIMEOUT
                    , HttpUtil.DEFAULT_SOCKET_TIMEOUT
                    , HttpUtil.DEFAULT_CHARSET
                    , params
                    , headers);
            Header[] responseAllHeaders = loginResponse.getAllHeaders();
            for (Header responseHeader : responseAllHeaders) {
                if ("Set-Cookie".equals(responseHeader.getName())) {
                    String value = responseHeader.getValue();
                    cookies.put(value.substring(0, value.indexOf("=")), value.substring(value.indexOf("=") + 1, value.indexOf(";")));
                }
            }
            String response = HttpUtil.response(HttpUtil.DEFAULT_CHARSET, loginResponse);
            System.out.println(response);
            LoginResponse login = GsonUtil.fromJson(response, LoginResponse.class);
            if ("ok".equals(login.getBase_resp().getErr_msg())) {
                System.out.println("登陆成功");
                token = login.getBase_resp().getToken();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<AppListResponse.AppListBean> applist() {
        int page = 0, num = 20;
        List<AppListResponse.AppListBean> applist = new ArrayList<>();
        try {
            for (; ; ) {
                String applistHtml = HttpUtil.get("https://open.weixin.qq.com/cgi-bin/applist?t=manage/list&page=" + page + "&num=" + num + "&openapptype=1&lang=zh_CN&token=" + token
                        , HttpUtil.DEFAULT_CONNECT_TIMEOUT
                        , HttpUtil.DEFAULT_SOCKET_TIMEOUT
                        , HttpUtil.DEFAULT_CHARSET
                        , getHeaders());
                applistHtml = applistHtml.substring(applistHtml.indexOf("{\"app_list\""));
                applistHtml = applistHtml.substring(0, applistHtml.indexOf("\n"));
                applistHtml = applistHtml.substring(0, applistHtml.lastIndexOf(","));
                System.out.println(applistHtml);

                AppListResponse appListResponse = GsonUtil.fromJson(applistHtml, AppListResponse.class);
                if (appListResponse != null && CollectionUtils.isNotEmpty(appListResponse.getApp_list())) {
                    appListResponse.getApp_list().forEach(app -> {
                        System.out.println(app.getName());
                        applist.add(app);
                    });
                    if (num * page + appListResponse.getApp_list().size() < appListResponse.getTotal_cnt()) {
                        page++;
                        continue;
                    }
                }
                break;
            }

        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return applist;
    }

    public static AppDetailResponse appDetail(String appid) {
        try {
            String appdetailHtml = HttpUtil.get("https://open.weixin.qq.com/cgi-bin/appdetail?t=manage/detail&type=app&lang=zh_CN&token=" + token + "&appid=" + appid
                    , HttpUtil.DEFAULT_CONNECT_TIMEOUT
                    , HttpUtil.DEFAULT_SOCKET_TIMEOUT
                    , HttpUtil.DEFAULT_CHARSET
                    , getHeaders());
            System.out.println("============================================================");
            System.out.println(appdetailHtml);
            System.out.println("============================================================");
            appdetailHtml = appdetailHtml.substring(appdetailHtml.indexOf("{\"appid\""));
            appdetailHtml = appdetailHtml.substring(0, appdetailHtml.indexOf("\n"));
            appdetailHtml = appdetailHtml.substring(0, appdetailHtml.lastIndexOf(","));
            System.out.println(appdetailHtml);
            AppDetailResponse appDetailResponse = GsonUtil.fromJson(appdetailHtml, AppDetailResponse.class);
            if (appDetailResponse != null) {
                System.out.println(appDetailResponse.getCheck_msg());
                return appDetailResponse;
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return null;
    }

    public static void modifyAppDetail(AppDetailResponse appDetailResponse) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("appid", appDetailResponse.getAppid());
            params.put("name", appDetailResponse.getName());
            params.put("ename", appDetailResponse.getEname());
            params.put("desc", appDetailResponse.getDesc());
            params.put("edesc", appDetailResponse.getEdesc());
            params.put("imgs", GsonUtil.toJson(appDetailResponse.getImgs()));
            params.put("app_url", appDetailResponse.getApp_url());
            params.put("platform", GsonUtil.toJson(appDetailResponse.getPlatform()));
            params.put("app_already_published", String.valueOf(appDetailResponse.getApp_already_published()));
            params.put("app_mp4_url", appDetailResponse.getApp_mp4_url());
            params.put("app_create_modify_desc", appDetailResponse.getApp_create_modify_desc());
            params.put("app_flow_chart_pic_list", GsonUtil.toJson(appDetailResponse.getApp_flow_chart_pic_list()));
            params.put("app_category_desc", appDetailResponse.getApp_category_desc());
            params.put("action", "extend");
            params.put("token", token);
            params.put("lang", "zh_CN");
            params.put("f", "json");
            params.put("ajax", "1");
            params.put("key", "1");
            List<Header> headers = getHeaders();
            headers.add(new BasicHeader("referer", "https://open.weixin.qq.com/cgi-bin/appdetail?action=modify&t=manage/modify&type=app&lang=zh_CN&token=" + token + "&modifytype=dev&apptype=mobile&action=modify&appid=" + appDetailResponse.getAppid()));
            String modifyAppDetailResponseStr = HttpUtil.post("https://open.weixin.qq.com/cgi-bin/appmodify", params, headers);
            System.out.println(modifyAppDetailResponseStr);
            ModifyAppDetailResponse modifyAppDetailResponse = GsonUtil.fromJson(modifyAppDetailResponseStr, ModifyAppDetailResponse.class);

            if ("ok".equals(modifyAppDetailResponse.getBase_resp().getErr_msg())) {
                System.out.println("应用名包名修改成功");
            }
        } catch (Exception ignore) {
        }
    }

    public static void main(String[] args) {
        homePage();
        login("", "");
        List<AppListResponse.AppListBean> applist = applist();
        applist.forEach(app -> {
            AppDetailResponse appDetailResponse = appDetail(app.getAppid());
            System.out.println(appDetailResponse.getName());
            System.out.println(appDetailResponse.getCheck_msg());
        });
    }
}
