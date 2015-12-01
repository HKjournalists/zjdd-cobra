package com.zaijiadd.app.applyflow.controller;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zaijiadd.app.common.utils.ContainerUtils;



@Controller
@RequestMapping("/file")
public class UploadController { 
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Map<String, Object> addUser(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request){
		
		String path = request.getSession().getServletContext().getRealPath("upload"); 
		List<String> fileUrlList = new ArrayList<>();
		for(int i = 0; i < files.length; i++){
			System.out.println("fileName---------->" + files[i].getOriginalFilename());
		
			if(!files[i].isEmpty()){
				int pre = (int) System.currentTimeMillis();
				try {
					String newFileName = new Date().getTime() + files[i].getOriginalFilename();
					//拿到输出流，同时重命名上传的文件
					FileOutputStream os = new FileOutputStream(path + newFileName);
					//拿到上传文件的输入流
					FileInputStream in = (FileInputStream) files[i].getInputStream();
					
					//以写字节的方式写文件
					int b = 0;
					while((b=in.read()) != -1){
						os.write(b);
					}
					os.flush();
					os.close();
					in.close();
					int finaltime = (int) System.currentTimeMillis();
					System.out.println(finaltime - pre);
					fileUrlList.add(request.getContextPath() + "/upload/" + newFileName);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("上传出错");
				}
		}
	}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fileUrls", fileUrlList);
		return ContainerUtils.buildResSuccessMap(param);
}
	
	
	
	@RequestMapping("/toUpload"	) 
	public String toUpload() {
		
		return "/upload";
	}
	
}

