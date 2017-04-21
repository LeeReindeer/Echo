package Util;

import ObjectC.OAuth2AccessToken;
import ObjectC.UserInfo;
import net.sf.json.JSONObject;

/**
 * @Author lee
 * @Time 15/4/17
 */
public class WebUtil extends WechatUtil{

    /**
     * 通过网页授权获取用户信息
     * @param code
     * @return oAuth2AccessToken
     */
    public OAuth2AccessToken getOAuth2AccessToken(String code){
        OAuth2AccessToken oAuth2AccessToken=new OAuth2AccessToken();
        String url = WEB_ACCESS_TOKEN_URL.replace("APPID", getAPPID()).replace("SECRET", getAPPSECRET()).replace("CODE",code);
        JSONObject jsonObject = doGet(url);
        if (jsonObject != null) {
            oAuth2AccessToken.setAccess_token(jsonObject.getString("access_token"));
            oAuth2AccessToken.setExpires_in(jsonObject.getInt("expires_in"));
            oAuth2AccessToken.setRefresh_token(jsonObject.getString("refresh_token"));
            oAuth2AccessToken.setOpenid(jsonObject.getString("openid"));
            oAuth2AccessToken.setScope(jsonObject.getString("scope"));
        }
        return oAuth2AccessToken;
    }

    /**
     * 获取用户信息
     * @param webAccessToken
     * @param openid
     * @return userInfo
     */
    public UserInfo getUserInfo(String webAccessToken, String openid){
        UserInfo userInfo=new UserInfo();
        String url=USER_INFO_URL.replace("ACCESS_TOKEN",webAccessToken).replace("OPENID",openid);
        JSONObject jsonObject = doGet(url);
        if (jsonObject!=null) {
            userInfo.setOpenid(jsonObject.getString("openid"));
            userInfo.setNickname(jsonObject.getString("nickname"));
            userInfo.setSex(jsonObject.getString("sex"));
            userInfo.setProvince(jsonObject.getString("province"));
            userInfo.setCity(jsonObject.getString("city"));
            userInfo.setCountry(jsonObject.getString("country"));
            userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
            userInfo.setPrivilege(jsonObject.getString("privilege"));
        }
        return userInfo;
    }

    /**
     * 刷新access_token
     * @param refreshToken
     * @return oAuth2AccessToken
     */
    public OAuth2AccessToken refreshOAuth2AcessToken(String refreshToken){
        OAuth2AccessToken oAuth2AccessToken=new OAuth2AccessToken();
        String url=REFRESH_ACCESS_TOKEN_URL.replace("APPID",getAPPID()).replace("REFRESH_TOKEN",refreshToken);
        JSONObject jsonObject = doGet(url);
        if (jsonObject != null) {
            oAuth2AccessToken.setAccess_token(jsonObject.getString("access_token"));
            oAuth2AccessToken.setExpires_in(jsonObject.getInt("expires_in"));
            oAuth2AccessToken.setRefresh_token(jsonObject.getString("refresh_token"));
            oAuth2AccessToken.setOpenid(jsonObject.getString("openid"));
            oAuth2AccessToken.setScope(jsonObject.getString("scope"));
        }
        return oAuth2AccessToken;
    }
}
