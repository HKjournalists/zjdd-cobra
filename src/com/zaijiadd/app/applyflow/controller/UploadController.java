package com.zaijiadd.app.applyflow.controller;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.ParseUtils;
import com.zaijiadd.app.user.entity.UserInfoEntity;
import com.zaijiadd.app.utils.constants.ConstantsForAccount;

import sun.misc.BASE64Decoder;



@Controller
@RequestMapping("/file")
public class UploadController { 
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest request) throws Exception{
		String path = request.getSession().getServletContext().getRealPath("upload"); 
		List<String> fileUrlList = new ArrayList<>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Integer userId = jsonRequest.getInteger("userId");
		String fileBoby = jsonRequest.getString("file");
		String fileType = jsonRequest.getString("fileType");
		if(userId == null) {
			UserInfoEntity userInfoEntity = (UserInfoEntity) request.getSession().getAttribute("user");
			userId = userInfoEntity.getUserId();
		}
		
		String newFileName = new Date().getTime() +"." + fileType.split("/")[1];
		path = path + File.separator + userId + File.separator ;
		File file = new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
		 path = path + newFileName;
		
		decoderBase64File(fileBoby, path);
		fileUrlList.add(ConstantsForAccount.IMG_URL + request.getContextPath() + "/upload/" + userId + "/" + newFileName);
				
	
		param.put("fileUrl", fileUrlList);
		return ContainerUtils.buildResSuccessMap(param);
	}
	
	
	
/*	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Map<String, Object> upload(@RequestBody String body, HttpServletRequest request) {
		List<String> fileUrlList = new ArrayList<>();
		String path = request.getSession().getServletContext().getRealPath("upload") + "\\";
		String newFileName = new Date().getTime() + ".png";
		body.getBytes();
		string2File(body, path + newFileName);
		fileUrlList.add(ConstantsForAccount.IMG_URL + request.getContextPath() + "/upload/" + newFileName);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fileUrls", fileUrlList);
		return ContainerUtils.buildResSuccessMap(param);
		
	}*/
	
	/**
	  * 将base64字符解码保存文件
	  * @param base64Code
	  * @param targetPath
	  * @throws Exception
	  */

	 public static void decoderBase64File(String base64Code, String targetPath)
	   throws Exception {
	  byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
	  FileOutputStream out = new FileOutputStream(targetPath);
	  out.write(buffer);
	  out.close();

	 }
	
	/** 
     * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！) 
     * 
     * @param res            原字符串 
     * @param filePath 文件路径 
     * @return 成功标记 
     */ 
    public static boolean string2File(String res, String filePath) { 
            boolean flag = true; 
            BufferedReader bufferedReader = null; 
            BufferedWriter bufferedWriter = null; 
            try { 
                    File distFile = new File(filePath); 
                    if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs(); 
                    bufferedReader = new BufferedReader(new StringReader(res)); 
                    bufferedWriter = new BufferedWriter(new FileWriter(distFile)); 
                    char buf[] = new char[1024];         //字符缓冲区 
                    int len; 
                    while ((len = bufferedReader.read(buf)) != -1) { 
                            bufferedWriter.write(buf, 0, len); 
                    } 
                    bufferedWriter.flush(); 
                    bufferedReader.close(); 
                    bufferedWriter.close(); 
            } catch (IOException e) { 
                    e.printStackTrace(); 
                    flag = false; 
                    return flag; 
            } finally { 
                    if (bufferedReader != null) { 
                            try { 
                                    bufferedReader.close(); 
                            } catch (IOException e) { 
                                    e.printStackTrace(); 
                            } 
                    } 
            } 
            return flag; 
    }
	public static String getBody(HttpServletRequest request) throws IOException {

	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();
	    return body;
	}
	 public static final byte[] input2byte(InputStream inStream)  
	            throws IOException {  
	        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	        byte[] buff = new byte[100];  
	        int rc = 0;  
	        while ((rc = inStream.read(buff, 0, 100)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        }  
	        byte[] in2b = swapStream.toByteArray();  
	        return in2b;  
	    }  
	
}

