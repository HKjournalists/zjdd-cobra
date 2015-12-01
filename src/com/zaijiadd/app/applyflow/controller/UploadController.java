package com.zaijiadd.app.applyflow.controller;


import java.io.File;
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
import com.zaijiadd.app.utils.constants.ConstantsForAccount;



@Controller
@RequestMapping("/file")
public class UploadController { 
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Map<String, Object> upload(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("upload"); 
		List<String> fileUrlList = new ArrayList<>();
		Map<String, Object> param = new HashMap<String, Object>();
		for(int i = 0; i < files.length; i++) {
			System.out.println("fileName---------->" + files[i].getOriginalFilename());
		
			if(!files[i].isEmpty()) {
				try {
					String newFileName = new Date().getTime() + files[i].getOriginalFilename();
					File targetFile = new File(path, newFileName);  
				        if(!targetFile.exists()){  
				            targetFile.mkdirs();  
				        }  
				  files[i].transferTo(targetFile);
				  fileUrlList.add(ConstantsForAccount.CALLENGINE_URL + request.getContextPath() + "/upload/" + newFileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
		param.put("fileUrls", fileUrlList);
		return ContainerUtils.buildResSuccessMap(param);
	}
	
}

