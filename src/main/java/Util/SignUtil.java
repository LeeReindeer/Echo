package Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
/**
 * @Author :lee
 * @Createtime :8/4/2017.
 */
public class SignUtil {

    //根据微信公众号里填写的写入
    private static final String token="Echo";

    public static Boolean checkSignsure(String signature,String timestamp,String nonce){
        Boolean isTrue=false;
        String array[]=new String[]{token,timestamp,nonce};
        //根据开发者文档进行排序
        Arrays.sort(array);
        //重组字符串
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<array.length;i++){
            builder.append(array[i]);
        }
        //sha1加密
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(builder.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        builder= null;

        if (tmpStr!=null&tmpStr.equals(signature.toUpperCase())){
            isTrue=true;
        }
        //return sha1;
        return isTrue;
    }


    //字节数组转换16进制
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    //字节转换陈16进制
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A','B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

}