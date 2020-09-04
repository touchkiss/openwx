package com.touchkiss.openwx.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2020/09/03 11:22
 *
 * @author Touchkiss
 */
@NoArgsConstructor
@Data
public class ModifyAppDetailResponse extends BasicPostResponse{

    /**
     * base_resp : {"ret":0,"err_msg":"ok","token":"55b1172a6008413353c9d4da3e29dd7531c7ecef","lang":"zh_CN"}
     * user_info : {"nick_name":"sik8728@163.com","fake_id":3534415512,"wxverify_annual_review":0,"wxverify_expired_time":1606448342,"has_main_admin":1,"registered_country":1017}
     */

    private UserInfoBean user_info;

    @NoArgsConstructor
    @Data
    public static class UserInfoBean {
        /**
         * nick_name : sik8728@163.com
         * fake_id : 3534415512
         * wxverify_annual_review : 0
         * wxverify_expired_time : 1606448342
         * has_main_admin : 1
         * registered_country : 1017
         */

        private String nick_name;
        private long fake_id;
        private int wxverify_annual_review;
        private int wxverify_expired_time;
        private int has_main_admin;
        private int registered_country;
    }
}
