package com.zaijiadd.app.applyflow.controller;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.ParseUtils;



@Controller
@RequestMapping("/file")
public class UploadController { 
	
	/*@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Map<String, Object> upload(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("upload"); 
		List<String> fileUrlList = new ArrayList<>();
		Map<String, Object> param = new HashMap<String, Object>();
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		if(userId == null) {
			UserInfoEntity userInfoEntity = (UserInfoEntity) request.getSession().getAttribute("user");
			userId = userInfoEntity.getUserId();
		}
		path = path + File.separator + userId + File.separator;
		for(int i = 0; i < files.length; i++) {
			if(!files[i].isEmpty()) {
				try {
					String newFileName = new Date().getTime() + files[i].getOriginalFilename();
					File targetFile = new File(path, newFileName);  
				        if(!targetFile.exists()) {
				            targetFile.mkdirs();
				        }  
				  files[i].transferTo(targetFile);
				  fileUrlList.add(ConstantsForAccount.IMG_URL + request.getContextPath() + "/upload/" + userId + "/" + newFileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
		param.put("fileUrls", fileUrlList);
		return ContainerUtils.buildResSuccessMap(param);
	}*/
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Map<String, Object> upload(HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("upload"); 
		List<String> fileUrlList = new ArrayList<>();
		Map<String, Object> param = new HashMap<String, Object>();
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		//JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		 try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}//防止中文名乱码
	        int sizeThreshold=1024*6; //缓存区大小
	        String basePath = request.getSession().getServletContext().getRealPath("upload");
	        File repository = new File(basePath); //缓存区目录
	        long sizeMax = 1024 * 1024 * 2;//设置文件的大小为2M
	        final String allowExtNames = "jpg,gif,bmp,rar,rar,txt,docx";
	        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
	        diskFileItemFactory.setRepository(repository);
	        diskFileItemFactory.setSizeThreshold(sizeThreshold);
	        ServletFileUpload servletFileUpload=new ServletFileUpload(diskFileItemFactory);
	        servletFileUpload.setSizeMax(sizeMax);
	        
	        List<FileItem> fileItems = null;
	        try{
	            fileItems = servletFileUpload.parseRequest(request);
	            
	            for(FileItem fileItem:fileItems){
	                long size=0;
	                String filePath = fileItem.getName();
	                System.out.println(filePath);
	                if(filePath==null || filePath.trim().length()==0)
	                    continue;
	                String fileName=filePath.substring(filePath.lastIndexOf(File.separator)+1);
//	                String fileName=String.valueOf(System.currentTimeMillis());
	                String extName=filePath.substring(filePath.lastIndexOf(".")+1);
//	                fileName+="."+extName;
	                if(allowExtNames.indexOf(extName)!=-1)
	                {
	                    try {
	                        fileItem.write(new File(basePath+File.separator+fileName));
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
	                else{
	                    throw new FileUploadException("file type is not allowed");
	                }
	            }
	        }catch(FileSizeLimitExceededException e){
	            System.out.println("file size is not allowed");
	        }catch(FileUploadException e1){
	            e1.printStackTrace();
	        }
	        
	        
		/*Integer userId = Integer.parseInt(request.getParameter("userId"));
		path = path + File.separator + userId + File.separator;
		for(int i = 0; i < files.length; i++) {
			if(!files[i].isEmpty()) {
				try {
					String newFileName = new Date().getTime() + files[i].getOriginalFilename();
					File targetFile = new File(path, newFileName);  
				        if(!targetFile.exists()) {
				            targetFile.mkdirs();
				        }  
				  files[i].transferTo(targetFile);
				  fileUrlList.add(ConstantsForAccount.IMG_URL + request.getContextPath() + "/upload/" + userId + "/" + newFileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}*/
	
		param.put("fileUrls", fileUrlList);
		return ContainerUtils.buildResSuccessMap(param);
	}
	
}

