package com.ruoyi.dylive.utils;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.dylive.model.FormData;
import com.ruoyi.dylive.model.HeaderModel;
import com.ruoyi.dylive.model.RequestResult;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class CommenMethod {

    /**
     * 发送请求方法
     * @param url 请求地址
     * @param headers 请求头信息
     * @param requestMethod 请求方法
     * @param formDataList Form表单请求内容
     * @param content 其他请求体请求内容
     * @return 请求结果信息
     * @throws Exception 异常信息
     */
    public static RequestResult sendRequest(String url, Map<String, String> headers,
                                            String requestMethod, List<FormData> formDataList, String content) throws Exception {
        //url转换
        URL mUrl = new URL(url);
        HttpURLConnection mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
        //设置链接超时时间
        mHttpURLConnection.setConnectTimeout(15000);
        //设置读取超时时间
        mHttpURLConnection.setReadTimeout(15000);
        //设置请求方法
        mHttpURLConnection.setRequestMethod(requestMethod);

        //设置请求头
        headers.entrySet().stream()
                .forEach(entry -> {
            String key = entry.getKey();
            String value = entry.getValue();
            mHttpURLConnection.setRequestProperty(key, value);
        });

        //接收输入流
        mHttpURLConnection.setDoInput(true);

        if ("POST".equals(requestMethod)){
            //Post方式不能缓存,需手动设置为false
            mHttpURLConnection.setUseCaches(false);
        }

        mHttpURLConnection.connect();

        //处理form表单请求体
        if (formDataList != null && !formDataList.isEmpty()){
            List<String> formInfoList = new ArrayList<>();
            String postContent = "";
            for (FormData formData : formDataList){
                String key = formData.getKey();
                String value = formData.getValue();
                String type = formData.getType();
                if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)){
                    continue;
                }
                if (StringUtils.isNotEmpty(type)){
                    //form表单格式，可能有文件
                    if("File".equals(type)){
                        //文件格式
                        //文件单独处理
                    }
                }
                //普通form表单
                String fromInfoStr = key + "=" + value;

                formInfoList.add(fromInfoStr);
            }
            postContent = String.join("&", formInfoList);
            DataOutputStream dos = new DataOutputStream(mHttpURLConnection.getOutputStream());
            dos.write(postContent.getBytes(StandardCharsets.UTF_8));
            dos.flush();
            // 执行完dos.close()后，POST请求结束
            dos.close();
        }

        //处理字符串请求体
        if (StringUtils.isNotEmpty(content)){
            DataOutputStream dos = new DataOutputStream(mHttpURLConnection.getOutputStream());
            dos.write(content.getBytes(StandardCharsets.UTF_8));
            dos.flush();
            // 执行完dos.close()后，POST请求结束
            dos.close();
        }

        RequestResult result = new RequestResult();
        // 获取代码返回值
        int respondCode = mHttpURLConnection.getResponseCode();
        result.setCode(respondCode);
        if (respondCode == 200) {
            // 获取响应的输入流对象
            InputStream is = mHttpURLConnection.getInputStream();
            // 创建字节输出流对象
            ByteArrayOutputStream message = new ByteArrayOutputStream();
            // 定义读取的长度
            int len = 0;
            // 定义缓冲区
            byte[] buffer = new byte[1024];
            // 按照缓冲区的大小，循环读取
            while ((len = is.read(buffer)) != -1) {
                // 根据读取的长度写入到os对象中
                message.write(buffer, 0, len);
            }
            // 释放资源
            is.close();
            message.close();
            // 返回字符串
            result.setResult(message.toString("UTF-8"));
            return result;
        }
        result.setMsg("出现错误");
        return result;
    }




    public static String postGetJson(String url, String content) {
        try {
            URL mUrl = new URL(url);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            //设置链接超时时间
            mHttpURLConnection.setConnectTimeout(15000);
            //设置读取超时时间
            mHttpURLConnection.setReadTimeout(15000);
            //设置请求参数
            mHttpURLConnection.setRequestMethod("POST");
            //添加Header
            mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");

            mHttpURLConnection.setRequestProperty("Content-type", "application/json; charset=utf-8");
            //接收输入流
            mHttpURLConnection.setDoInput(true);
            //传递参数时需要开启
            mHttpURLConnection.setDoOutput(true);
            //Post方式不能缓存,需手动设置为false
            mHttpURLConnection.setUseCaches(false);

            mHttpURLConnection.connect();

            if(content != null && !content.isEmpty()){
            
	            DataOutputStream dos = new DataOutputStream(mHttpURLConnection.getOutputStream());

                dos.write(content.getBytes(StandardCharsets.UTF_8));
	            dos.flush();
	            // 执行完dos.close()后，POST请求结束
	            dos.close();
            }
            // 获取代码返回值
            int respondCode = mHttpURLConnection.getResponseCode();
            // 获取返回内容类型
            //String type = mHttpURLConnection.getContentType();
            
            // 获取返回内容的字符编码
            //String encoding = mHttpURLConnection.getContentEncoding();
            // 获取返回内容长度，单位字节
            //int length = mHttpURLConnection.getContentLength();
//            // 获取头信息的Key
//            String key = mHttpURLConnection.getHeaderField(idx);
//            Log.d("key", "key="+key);
            // 获取完整的头信息Map
            //Map<String, List<String>> map = mHttpURLConnection.getHeaderFields();
//            InputStream erroris ;
//            if (mHttpURLConnection.getResponseCode() >= 400 ) {
//            	erroris = mHttpURLConnection.getErrorStream();
//               }
//               else{
//            	erroris = mHttpURLConnection.getInputStream();
//               }
//               BufferedReader br = new BufferedReader(new InputStreamReader(erroris, "UTF-8"));
//               StringBuffer sbf = new StringBuffer();
//               String temp = null;
//               // 循环遍历一行一行读取数据
//              while ((temp = br.readLine()) != null) {
//                   sbf.append(temp);
//                   sbf.append("\r\n");
//               }
//               String result = sbf.toString();
//               
               
            if (respondCode == 200) {
                // 获取响应的输入流对象
                InputStream is = mHttpURLConnection.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    message.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                message.close();
                // 返回字符串
                return new String(message.toByteArray(), StandardCharsets.UTF_8);
            }
            return "fail";
        }catch(Exception e){
            return "error";
        }
    }
    
    
    public static String postFormJson(String url, List<FormData> params) {
        try {
            URL mUrl = new URL(url);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            //设置链接超时时间
            mHttpURLConnection.setConnectTimeout(15000);
            //设置读取超时时间
            mHttpURLConnection.setReadTimeout(15000);
            //设置请求参数
            mHttpURLConnection.setRequestMethod("POST");
            //添加Header
            mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");

//            mHttpURLConnection.setRequestProperty("Content-type", "application/json; charset=utf-8");
            //接收输入流
            mHttpURLConnection.setDoInput(true);
            //传递参数时需要开启
            mHttpURLConnection.setDoOutput(true);
            //Post方式不能缓存,需手动设置为false
            mHttpURLConnection.setUseCaches(false);

            mHttpURLConnection.connect();

            if(!params.isEmpty()){
                
	            DataOutputStream dos = new DataOutputStream(mHttpURLConnection.getOutputStream());
	
	            StringBuilder sb = new StringBuilder();
	            int count = 0;
	            for(FormData formParam : params){
	            	if(count == 0){
	            		sb.append(formParam.getKey()).append("=").append(formParam.getValue());
	            		count += 1;
	            	}else{
	            		sb.append("&").append(formParam.getKey()).append("=").append(formParam.getValue());
	            	}
	            }
	            
	            String postContent = sb.toString();
	            System.out.println("******** " + postContent);
	            dos.write(postContent.getBytes(StandardCharsets.UTF_8));
	            dos.flush();
	            // 执行完dos.close()后，POST请求结束
	            dos.close();
            }

            // 获取代码返回值
            int respondCode = mHttpURLConnection.getResponseCode();
            
            if (respondCode == 200) {
                // 获取响应的输入流对象
                InputStream is = mHttpURLConnection.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte[] buffer = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    message.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                message.close();
                // 返回字符串
                return new String(message.toByteArray(), StandardCharsets.UTF_8);
            }
            return "fail";
        }catch(Exception e){
            return "error";
        }
    }
}
