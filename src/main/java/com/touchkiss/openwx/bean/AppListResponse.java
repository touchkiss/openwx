package com.touchkiss.openwx.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created on 2020/09/03 10:34
 *
 * @author Touchkiss
 */
@NoArgsConstructor
@Data
public class AppListResponse {

    /**
     * app_list : [{"appid":"wxee57b961934c8a73","name":"花生快讯精品版","ename":"","img":"http://mmbiz.qpic.cn/mmbiz_png/7Ed5FIlIMjfjN3IX50ibf1Zf0icPtZ7t2iaKjkj2flQUy57rbKiaxHzfrYkwGjicye1eDI7moUfly2Hewh86c7Z03RQ/0?wx_fmt=png","status":2,"time":0,"share":0,"fav":0,"login_times":0,"app_type":"mobile","pay_result":0,"check":0,"wx_login":-1},{"appid":"wx7bd19795b807ff3d","name":"花生快讯升级版","ename":"","img":"http://mmbiz.qpic.cn/mmbiz_png/7Ed5FIlIMjfjN3IX50ibf1Zf0icPtZ7t2iaKjkj2flQUy57rbKiaxHzfrYkwGjicye1eDI7moUfly2Hewh86c7Z03RQ/0?wx_fmt=png","status":2,"time":0,"share":0,"fav":0,"login_times":0,"app_type":"mobile","pay_result":0,"check":0,"wx_login":-1},{"appid":"wx575451fd92f80df6","name":"花生快讯SP版","ename":"","img":"http://mmbiz.qpic.cn/mmbiz_png/7Ed5FIlIMjfjN3IX50ibf1Zf0icPtZ7t2iaKjkj2flQUy57rbKiaxHzfrYkwGjicye1eDI7moUfly2Hewh86c7Z03RQ/0?wx_fmt=png","status":2,"time":0,"share":0,"fav":0,"login_times":0,"app_type":"mobile","pay_result":0,"check":0,"wx_login":-1}]
     * first_apply : true
     * limit : [{"type":"mobile","openapptype":1,"same_realname":{},"diff_realname":{"max":50,"curr_cnt":3}},{"type":"web","openapptype":512,"diff_realname":{"max":10}},{"type":"biz","openapptype":16,"same_realname":{"max":50},"diff_realname":{"max":5,"freq_limit_max":5,"freq_limit_cur_cnt":0},"sandbox":{"max":5}},{"type":"weapp","openapptype":65536,"same_realname":{"max":50},"diff_realname":{"max":5,"freq_limit_max":5,"freq_limit_cur_cnt":0}}]
     * openapptype : 1
     * total_cnt : 3
     */

    private boolean first_apply;
    private int openapptype;
    private int total_cnt;
    private List<AppListBean> app_list;
    private List<LimitBean> limit;

    @NoArgsConstructor
    @Data
    public static class AppListBean {
        /**
         * appid : wxee57b961934c8a73
         * name : 花生快讯精品版
         * ename :
         * img : http://mmbiz.qpic.cn/mmbiz_png/7Ed5FIlIMjfjN3IX50ibf1Zf0icPtZ7t2iaKjkj2flQUy57rbKiaxHzfrYkwGjicye1eDI7moUfly2Hewh86c7Z03RQ/0?wx_fmt=png
         * status : 2
         * time : 0
         * share : 0
         * fav : 0
         * login_times : 0
         * app_type : mobile
         * pay_result : 0
         * check : 0
         * wx_login : -1
         */

        private String appid;
        private String name;
        private String ename;
        private String img;
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
        private int time;
        private int share;
        private int fav;
        private int login_times;
        private String app_type;
        private int pay_result;
        private int check;
        private int wx_login;
    }

    @NoArgsConstructor
    @Data
    public static class LimitBean {
        /**
         * type : mobile
         * openapptype : 1
         * same_realname : {}
         * diff_realname : {"max":50,"curr_cnt":3}
         * sandbox : {"max":5}
         */

        private String type;
        private int openapptype;
        private SameRealnameBean same_realname;
        private DiffRealnameBean diff_realname;
        private SandboxBean sandbox;

        @NoArgsConstructor
        @Data
        public static class SameRealnameBean {
        }

        @NoArgsConstructor
        @Data
        public static class DiffRealnameBean {
            /**
             * max : 50
             * curr_cnt : 3
             */

            private int max;
            private int curr_cnt;
        }

        @NoArgsConstructor
        @Data
        public static class SandboxBean {
            /**
             * max : 5
             */

            private int max;
        }
    }
}
