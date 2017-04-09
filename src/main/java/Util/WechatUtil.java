package Util;

//import org.json.JSONObject;
//import com.google.gson.Gson;

import Bean.Menu.Button;
import Bean.Menu.ClickButton;
import Bean.Menu.Menu;
import Bean.Menu.ViewButton;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lee on 4/8/17.
 */
public class WechatUtil {

    private final static String APPID = "wx65bef057c253d2e1";
    private final static String APPSECRET = "760f1f6aab839a8cffde57bad5c114be";
    private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private final static String BUtton_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //private String response;

    public JSONObject doGet(String url) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        JSONObject jsonObject = null;
        try {

            URL url1 = new URL(url);
            connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            InputStream in = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            jsonObject = JSONObject.fromObject(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return jsonObject;
    }

    public JSONObject doPost(String url,String outStr) {
        JSONObject jsonObject=null;
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            if (outStr != null) {
                out.write(outStr);
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            jsonObject=JSONObject.fromObject(result.toString());
            //showResponse(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return jsonObject;
    }

    /*private void showGet(String response) {
        this.response = response;
    }*/

    public AccessToken getAcessToken() {
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        JSONObject jsonObject = doGet(url);
        if (jsonObject != null) {
            token.setAccess_token(jsonObject.getString("access_token"));
            token.setExpires_in(jsonObject.getInt("expires_in"));
        }
        return token;
    }

    /*
    *
    * 初始化菜单
    * */
    public Menu initMenu(){
        Menu menu=new Menu();
        ClickButton clickButton1=new ClickButton();
        clickButton1.setName("clickButton");
        clickButton1.setType("click");
        clickButton1.setKey("11");

        ViewButton viewButton1=new ViewButton();
        viewButton1.setName("viewButton");
        viewButton1.setUrl("http://leezoom.xyz");
        viewButton1.setType("view");

        //子菜单
        ClickButton clickButton2=new ClickButton();
        clickButton2.setName("ScanCode");
        clickButton2.setType("scancode_push");
        clickButton2.setKey("21");

        //子菜单
        ClickButton clickButton3=new ClickButton();
        clickButton3.setName("ScanCode");
        clickButton3.setType("scancode_push");
        clickButton3.setKey("21");

        Button button=new Button();
        button.setName("主菜单");
        button.setSub_button(new Button[]{clickButton2,clickButton3});

        menu.setButton(new Button[]{clickButton1,viewButton1,button});
        return menu;
    }


    public int creatMenu(String menu,String token){
        int errcode=0;
        String url=BUtton_URL.replace("ACCESS_TOKEN",token);
        JSONObject jsonObject=doPost(url,menu);
        if (jsonObject!=null){
            errcode=jsonObject.getInt("errcode");
        }
        return  errcode;
    }
}