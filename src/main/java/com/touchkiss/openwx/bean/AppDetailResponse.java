package com.touchkiss.openwx.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created on 2020/09/02 18:24
 *
 * @author Touchkiss
 */
@NoArgsConstructor
@Data
public class AppDetailResponse {

    /**
     * appid : wxee57b961934c8a73
     * status : 2
     * name : 花生快讯精品版
     * ename :
     * desc : 看一看资讯，抢一抢红包。 邀请亲朋好友一起玩更有现金奖励哦！ 花生为您提供头条资讯、娱乐资讯、养生资讯、搞笑内容及各类型热点头条资讯
     * edesc :
     * imgs : [{"type":0,"url":"http://mmbiz.qpic.cn/mmbiz_png/7Ed5FIlIMjfjN3IX50ibf1Zf0icPtZ7t2iaSDKQJR6L3JOMEK1fZzSzibvRf4tseiaqY5kgmV1lCBF3Pp7omyOvjvog/0?wx_fmt=png"},{"type":3,"url":"http://mmbiz.qpic.cn/mmbiz_png/7Ed5FIlIMjfjN3IX50ibf1Zf0icPtZ7t2iaKjkj2flQUy57rbKiaxHzfrYkwGjicye1eDI7moUfly2Hewh86c7Z03RQ/0?wx_fmt=png"}]
     * app_url : www.huashengnews.com
     * platform : {"android":{"url":"https://appgallery.cloud.huawei.com/ag/n/app/C100626425?locale=zh_CN&amp;amp;amp;source=appshare&amp;amp;amp;subsource=C100626425&amp;amp;amp;shareTo=com.android.mms&amp;amp;amp;shareFrom=appmarket","sign":"com.zs.hsand","package_name":"com.zs.hsand"},"type":["android"]}
     * auth_domain :
     * check_msg : 你好，同一个app下载链接建议用户提审与应用下载名称相符的1移动应用即可，暂不支持重复提审。
     * app_type : mobile
     * auth_scan_file :
     * wx_login : -1
     * pay_result : 0
     * card_result : 0
     * check : 0
     * wxjump : 0
     * open_wx_msg : 0
     * subscribe_msg_status : 1
     * yyb_micro_download_url : https://appgallery.cloud.huawei.com/ag/n/app/C100626425?locale=zh_CN&amp;amp;amp;source=appshare&amp;amp;amp;subsource=C100626425&amp;amp;amp;shareTo=com.android.mms&amp;amp;amp;shareFrom=appmarket
     * auth_scan_file_list : []
     * app_already_published : 2
     * app_mp4_url :
     * app_flow_chart_pic_list : ["KIJgQiFl5iK82J3wXRMKbNePYcKEyCPGgZS8QHb6nfd5SSBTOlIQz-XyBN9GF6YL","wIjEzVsWoM-JaiT3Yhc2wxR4ph7ZJxS5dUd9O8v_a4ATnJk49hwyVFm7AACjmbNw","ixTe6zUJabvh3xa25DuXzAww5DOI_WZhmlVr8y9c9Swg5DF7UqqRgrP3vPqIDdNz","J4bcoHBM3iUOZEAB9Sc4OZg8JmKVj4zK7u9dzH0oH68xmep1wDy0UjTF8AOKKTow","-_4mwF0WQCpSmTg2eYBNPjQMdV77oC10GTpqQSAEX7NDbanzJ6JmObCktdmTR2Oy"]
     * app_create_modify_desc : 一款阅读资讯的APP
     * app_category_desc : 生活服务-休闲娱乐
     */

    private String appid;
    /**
     * 审核状态
     *
     * "-1": "完善资料",
     * 1: "审核中",
     * 2: "未通过",
     * 3: "已通过",
     * 4: "已通过",
     * 5: "已通过",
     * 6: "已通过",
     * 7: "已通过",
     * 8: "已通过"
     */
    private int status;
    private String name;
    private String ename;
    private String desc;
    private String edesc;
    private String app_url;
    private PlatformBean platform;
    private String auth_domain;
    private String check_msg;
    private String app_type;
    private String auth_scan_file;
    private int wx_login;
    private int pay_result;
    private int card_result;
    private int check;
    private int wxjump;
    private int open_wx_msg;
    private int subscribe_msg_status;
    private String yyb_micro_download_url;
    /**
     * 上架状态
     * 1：未上架
     * 2：已上架
     * 其他：未设置
     */
    private int app_already_published;
    private String app_mp4_url;
    private String app_create_modify_desc;
    private String app_category_desc;
    private List<ImgsBean> imgs;
    private List<?> auth_scan_file_list;
    private List<String> app_flow_chart_pic_list;

    @NoArgsConstructor
    @Data
    public static class PlatformBean {
        /**
         * android : {"url":"https://appgallery.cloud.huawei.com/ag/n/app/C100626425?locale=zh_CN&amp;amp;amp;source=appshare&amp;amp;amp;subsource=C100626425&amp;amp;amp;shareTo=com.android.mms&amp;amp;amp;shareFrom=appmarket","sign":"com.zs.hsand","package_name":"com.zs.hsand"}
         * type : ["android"]
         */

        private AndroidBean android;
        private List<String> type;

        @NoArgsConstructor
        @Data
        public static class AndroidBean {
            /**
             * url : https://appgallery.cloud.huawei.com/ag/n/app/C100626425?locale=zh_CN&amp;amp;amp;source=appshare&amp;amp;amp;subsource=C100626425&amp;amp;amp;shareTo=com.android.mms&amp;amp;amp;shareFrom=appmarket
             * sign : com.zs.hsand
             * package_name : com.zs.hsand
             */

            private String url;
            /**
             * 应用签名
             */
            private String sign;
            /**
             * 应用包名
             */
            private String package_name;
        }
    }

    @NoArgsConstructor
    @Data
    public static class ImgsBean {
        /**
         * type : 0
         * url : http://mmbiz.qpic.cn/mmbiz_png/7Ed5FIlIMjfjN3IX50ibf1Zf0icPtZ7t2iaSDKQJR6L3JOMEK1fZzSzibvRf4tseiaqY5kgmV1lCBF3Pp7omyOvjvog/0?wx_fmt=png
         */

        private int type;
        private String url;
    }
}
