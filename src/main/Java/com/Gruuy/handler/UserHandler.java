package com.Gruuy.handler;


import com.Gruuy.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
public class UserHandler {
    @Resource
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public String login(HttpServletRequest request)
    {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        JSON json=new JSONObject();
        if(username!=""&&password!=""){
            System.out.println(userService.checkLogin(username,password));
            if(userService.checkLogin(username,password)){
                ((JSONObject) json).put("flag","true");
                return JSON.toJSONString(json);
            }
            else {
                ((JSONObject) json).put("flag","false");
                return JSON.toJSONString(json);
            }
        }
        ((JSONObject) json).put("flag","null");
        return JSON.toJSONString(json);
    }
    @RequestMapping("upload")
    public String upload(MultipartFile[] file, HttpSession session)throws Exception{
        for(MultipartFile item:file){
            if(!item.isEmpty()){
                //获取上传的文件名
                String filename=item.getOriginalFilename();
                //获取绝对路径
                String leftPath=session.getServletContext().getRealPath("/data");
                //文件路径拼接
                String filePath=leftPath+"\\"+filename;
                item.transferTo(new File(filePath));
            }else {
                return "ERROR";
            }
        }
        return "uploadsuccess";
    }
    @RequestMapping("download")
    public ResponseEntity<byte[]> download(String filename,HttpSession session)throws Exception{
        //读取文件
        String filePath=session.getServletContext().getRealPath("/data")+"\\"+filename;
        File file=new File(filePath);
        //通过一次性读取获取文件并返回
        InputStream is=new FileInputStream(file);
        byte[] body=null;
        //available是获取大小    这里是定义一个有文件大小的byte数组
        body=new byte[is.available()];
        //读取文件
        is.read(body);
        //定义返回类型
        HttpHeaders headers=new HttpHeaders();
        //响应头
        //Content-Disposition属性有两种类型：inline 和 attachment
        // inline ：将文件内容直接显示在页面
        // attachment：弹出对话框让用户下载
        headers.add("Content-Disposition","attchement;filename="+file.getName());
        //Http状态码
        HttpStatus statusCode=HttpStatus.OK;
        //定义ResponseEntity对象，分别为二进制流数组，响应头，还有状态码
        ResponseEntity<byte[]> entity=new ResponseEntity<byte[]>(body,headers,statusCode);
        return entity;
    }
    @RequestMapping("bigfiledown")
    public void bigfiledown(String filename, HttpServletRequest request, HttpServletResponse response,HttpSession session)throws Exception{
        //设置响应头ContentType内容
        response.setContentType("text/html;charset=utf-8");
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        //定义缓冲流
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;
        //路径获取
        String filePath=session.getServletContext().getRealPath("/data")+"\\"+filename;
        if(filePath!=null&&filename!=null) {
            //获取文件长度(大小)
            long fileLength = new File(filePath).length();
            //设置响应头为下载
            response.setContentType("application/x-msdownload;");
            //设置响应头弹出下载对话框
            response.setHeader("Content-Disposition","attachment;filename="+filename);
            //设置输出长度
            response.setHeader("Content-Length",String.valueOf(fileLength));
            //获取流
            bis=new BufferedInputStream(new FileInputStream(filePath));
            bos=new BufferedOutputStream(response.getOutputStream());
            //定义缓冲区大小
            byte[] buff=new byte[2048];
            int bytesRead;
            //off为起始下标
            while (-1!=(bytesRead=bis.read(buff,0,buff.length))){
                bos.write(buff,0,bytesRead);
            }
            if(bis!=null)
                bis.close();
            if(bos!=null)
                bos.close();
        }
    }
}
