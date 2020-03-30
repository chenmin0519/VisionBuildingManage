package com.visionbuilding.manage.controller.test;

import com.alibaba.fastjson.JSONObject;
import com.visionbuilding.manage.controller.BaseController;
import com.visionbuilding.manage.modle.ResultPOBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;

@Controller
@RequestMapping("/test/paper")
public class PaperController extends BaseController {
    private String key = "KihiWpDokpITW6sIjRFUt7X70vt19bhWwhAn4XvBsKXwMxM6tnEJPZFn3dR0Ka9bGsw8F88sLXretOi3KNEqwtQUT03bF0EP8OIjZ7wAKEqGs9YkzOY6P2Ft5eZZQ4Zb";

    @RequestMapping("/getPay")
    @ResponseBody
    public String getPay() throws Exception {
        ResultPOBean<String> resultBean = new ResultPOBean<>();
        URL url = new URL("http://payment.fudan.edu.cn/pay/queryPR3.html");
        URLConnection conn = url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();
        StringBuffer params = new StringBuffer();
        String sysCert = key;
        String firstSC = sysCert.charAt(0) + "";
        String sysId = "040";
        String itemId = "040-001";
        String objId = "04269";  // 一般为学号或报名号
//        String otherId = "";
        String objName = "朱亚晓";
        String amount = "0.02";
//        String specialValue = "";
        String returnURL = "http://www.chenmin0519.club/swagger-ui.html";
//        params.append("sign=").append(createSignString(
//                new String[]{
//                        firstSC, sysId, firstSC, itemId, firstSC, objId, firstSC, "20-2", firstSC, objName,
//                        firstSC, amount, firstSC, firstSC, "data", firstSC, null, firstSC, returnURL,
//                        sysCert
//                }
//        ));
        params.append("sign=").append(createSignString(
                new String[]{
                        firstSC, sysId, firstSC, itemId, firstSC, objId, firstSC, "20-2",
                        firstSC, null, firstSC, null, firstSC, null, sysCert
                }
        ));
        params.append("&sysId=").append(sysId);
        params.append("&itemId=").append(itemId);
        params.append("&objId=").append(objId);
        params.append("&objName=").append(URLEncoder.encode(objName, "UTF-8"));
        params.append("&otherId=").append(URLEncoder.encode("20-2", "UTF-8"));
//        params.append("&specialValue=").append(URLEncoder.encode(null, "UTF-8"));
        params.append("&returnURL=").append(URLEncoder.encode(returnURL, "UTF-8"));
        params.append("&amount=").append(amount);
        params.append("&returnType=").append("data");
        out.write(params.toString().getBytes("UTF-8"));
        InputStream in = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(in, "UTF-8");
        char[] buf = new char[1024];
        int count = isr.read(buf);
        String result = new String(buf, 0, count);
// JSONObject 请去http://www.json.org/下载
        System.out.println(result);
        JSONObject obj = JSONObject.parseObject(result);
        resultBean.success(obj.getString("payPassword"));
        return JSONObject.toJSONString(resultBean);
    }


    @RequestMapping("/pay")
    @ResponseBody
    public String addPay() throws Exception {
        ResultPOBean<String> resultBean = new ResultPOBean<>();
        URL url = new URL("http://payment.fudan.edu.cn/pay/itemDeal3.html");
        URLConnection conn = url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();
        StringBuffer params = new StringBuffer();
        String sysCert = key;
        String firstSC = sysCert.charAt(0) + "";
        String sysId = "040";
        String itemId = "040-001";
        String objId = "04269";  // 一般为学号或报名号
//        String otherId = "";
        String objName = "朱亚晓";
        String amount = "0.02";
//        String specialValue = "";
        String returnURL = "http://www.chenmin0519.club/swagger-ui.html";
        params.append("sign=").append(createSignString(
                new String[]{
                        firstSC, sysId, firstSC, itemId, firstSC, objId, firstSC, "20-2", firstSC, objName,
                        firstSC, amount, firstSC, firstSC, "data", firstSC, null, firstSC, returnURL,
                        sysCert
                }
        ));
        params.append("&sysId=").append(sysId);
        params.append("&itemId=").append(itemId);
        params.append("&objId=").append(objId);
        params.append("&objName=").append(URLEncoder.encode(objName, "UTF-8"));
        params.append("&otherId=").append(URLEncoder.encode("20-2", "UTF-8"));
//        params.append("&specialValue=").append(URLEncoder.encode(null, "UTF-8"));
        params.append("&returnURL=").append(URLEncoder.encode(returnURL, "UTF-8"));
        params.append("&amount=").append(amount);
        params.append("&returnType=").append("data");
        out.write(params.toString().getBytes("UTF-8"));
        InputStream in = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(in, "UTF-8");
        char[] buf = new char[1024];
        int count = isr.read(buf);
        String result = new String(buf, 0, count);
// JSONObject 请去http://www.json.org/下载
        System.out.println(result);
        JSONObject obj = JSONObject.parseObject(result);
        resultBean.success(obj.getString("payPassword"));
        return JSONObject.toJSONString(resultBean);
    }

    public String createSignString(String[] values)
            throws Exception
    {
        StringBuffer buf = new StringBuffer(512);
        for (int i = 0; i < values.length; i++)
        {
            String value = values[i];
            if (value == null)
            {
                continue;
            }
            buf.append(value);
        }
        byte[] bufTemp = buf.toString().getBytes("UTF-8");
        MessageDigest mdTemp = MessageDigest.getInstance("MD5");
        mdTemp.update(bufTemp);
        byte[] md5Result = mdTemp.digest();
        return byte2String(md5Result);
    }
    public String byte2String(byte[] buf)
    {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < buf.length; i++)
        {
            if ((buf[i] & 0xff) < 0x10)
            {
                result.append('0');
            }
            result.append((Integer.toHexString(buf[i] & 0xff)));
        }
        return result.toString();
    }
}
