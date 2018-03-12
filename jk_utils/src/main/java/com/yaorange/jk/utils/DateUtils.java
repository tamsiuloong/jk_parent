package com.yaorange.jk.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;

/**
 * @author coach tam
 * @date 2017/12/28
 */
public class DateUtils {
    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("111111"));;
    }
}
