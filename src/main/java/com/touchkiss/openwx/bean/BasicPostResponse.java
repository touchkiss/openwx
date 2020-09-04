package com.touchkiss.openwx.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2020/09/03 11:28
 *
 * @author Touchkiss
 */
@Data
public abstract class BasicPostResponse {
    private BaseRespBean base_resp;

    @NoArgsConstructor
    @Data
    public static class BaseRespBean {
        /**
         * ret : 0
         * err_msg : ok
         * token : 1416e63baff3b078d4d2b92626c98fd785f713b3
         * lang : zh_CN
         */

        private int ret;
        private String err_msg;
        private String token;
        private String lang;
    }
}
