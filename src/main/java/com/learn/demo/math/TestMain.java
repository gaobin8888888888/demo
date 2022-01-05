package com.learn.demo.math;

import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;

/**
 * @author gaobin
 * @date 2021/8/18 11:05 下午
 * @desc
 */
public class TestMain {

    public static void mai22n(String[] args) {
        int[] a = {1,3,5,6,7,8};
        int[] b = {1,2,3,4,5,6,7,8,9};
        int i = 0, j = 0;
        for ( ; i < a.length && j < b.length;) {
            if (a[i] == b[j]) {
                i++;
                j++;
            } else if (a[i] > b[j]) {
                System.out.println(b[j++]);
            } else {
                i++;
            }
        }

        for (; j < b.length; j++){
            System.out.println(b[j]);
        }
    }

    public static void main(String[] args) {
        Long time = System.currentTimeMillis();
        String nonce = "1234566";
        String sign = SHA256("tinet_dev_octopus" + time + nonce + "f34ed53631d0cf513283bfd5c01ddca48d2950c98ba0fdfec2bcc98e7f6444d2");
        System.out.println(time);
        System.out.println(sign);
    }

    /**
     * 这个方法是为了满足定制开发不同的客户对加密要求不一致增加的
     */
    private boolean validateSignature(String k1, String k2, String signature, String encodeMode) {
        if (Objects.equals(encodeMode, "sha256")) {
            String sha256;
            if (StringUtils.isEmpty(k2)) {
                sha256 = SHA256(k1);
            } else {
                sha256 = SHA256(k1 + k2);
            }
            return Objects.equals(sha256, signature);
        }
        return false;
    }

    public static String SHA256(String object) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(object.getBytes(StandardCharsets.UTF_8));
            return byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
