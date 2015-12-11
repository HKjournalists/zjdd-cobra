package com.zaijiadd.app.dataquery.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.ParseUtils;
import com.zaijiadd.app.dataquery.service.AutoAllotFlowService;

@RequestMapping ( "/allot" )
@Controller
public class AutoAllotFlowController {
	
	@Autowired
	private AutoAllotFlowService autoAllotFlowService;
	
	@RequestMapping ( value = "/autoAllotOpen", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> autoAllotOpen( HttpServletRequest request ) {

		Map<String, Object> param = new HashMap<String, Object>();
		
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		
		String userIds = jsonRequest.getString( "allotUserIdArr" );
		if ( userIds == null || userIds.equals( "" ) ) {
			return ContainerUtils.buildHeadMap( param, 0, "请选择自动分配用户" );
		}
		Integer userId = jsonRequest.getInteger( "userId" );
		String[] userIdArr = userIds.split( ";" );
		
		if ( autoAllotFlowService.AutoAllotSetting( userId,  userIdArr ) ) {
			return ContainerUtils.buildResSuccessMap( param );
		}

		return ContainerUtils.buildHeadMap( param, 0, "自动分配设置失败" );

	}
	
	@RequestMapping ( value = "/autoAllotClose", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> autoAllotClose( HttpServletRequest request ) {

		Map<String, Object> param = new HashMap<String, Object>();
		
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		
		Integer userId = jsonRequest.getInteger( "userId" );
		
		if ( autoAllotFlowService.CloseAutoAllot( userId ) ) {
			return ContainerUtils.buildResSuccessMap( param );
		}

		return ContainerUtils.buildHeadMap( param, 0, "自动分配设置失败" );

	}

}
