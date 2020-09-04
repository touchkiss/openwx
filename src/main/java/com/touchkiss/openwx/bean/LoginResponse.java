package com.touchkiss.openwx.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2020/09/02 17:55
 *
 * @author Touchkiss
 */
@NoArgsConstructor
@Data
public class LoginResponse extends BasicPostResponse{

    /**
     * base_resp : {"ret":0,"err_msg":"ok","token":"1416e63baff3b078d4d2b92626c98fd785f713b3","lang":"zh_CN"}
     * nick_name : sik8728@163.com
     */

    private String nick_name;

}
