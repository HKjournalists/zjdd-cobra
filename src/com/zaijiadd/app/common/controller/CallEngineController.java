package com.zaijiadd.app.common.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.common.service.CallEngineService;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.ParseUtils;

@RequestMapping ( "/callEngine" )
@Controller
public class CallEngineController {
	
	@Autowired
	private CallEngineService callEngineService;
	
	@RequestMapping ( value = "/dial", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> dial( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		String destMobile = jsonRequest.getString( "destMobile" );
		Integer userId = jsonRequest.getInteger( "userId" );
		Integer wId = jsonRequest.getInteger( "wId" );
		
		if ( callEngineService.dial( destMobile, userId, wId ) ) {
			return ContainerUtils.buildResSuccessMap( resData );
		}

		return ContainerUtils.buildHeadMap( resData, -2, "呼叫失败" );

	}
	
	@RequestMapping ( value = "/hangup", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> hangup( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		String destMobile = jsonRequest.getString( "destMobile" );
		Integer userId = jsonRequest.getInteger( "userId" );
		
		if ( callEngineService.hangup( destMobile, userId ) ) {
			return ContainerUtils.buildResSuccessMap( resData );
		}

		return ContainerUtils.buildHeadMap( resData, -2, "挂断失败" );

	}
	
	@RequestMapping ( value = "/callback", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> callback( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		String cdrData = request.getParameter( "cdr" );
		JSONObject jsonData = new JSONObject();
		try {
			jsonData = JSONObject.parseObject( cdrData );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		Map requestParams = request.getParameterMap();
		for ( Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
			String name = ( String ) iter.next();
			String[] values = ( String[] ) requestParams.get( name );
			String valueStr = "";
			for ( int i = 0; i < values.length; i++ ) {
				valueStr = ( i == values.length - 1 ) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			try {
				valueStr = new String( valueStr.getBytes( "ISO-8859-1" ), "gbk" );
				valueStr = URLDecoder.decode( valueStr );
			} catch ( UnsupportedEncodingException e ) {
				e.printStackTrace();
			}
			resData.put( name, valueStr );
		}
		
		JSONObject json = new JSONObject( resData );
		try {
			JSONObject data = json.getJSONObject( "cdr" );
			
		} catch ( Exception e ) {
			
		}
		
		System.out.println( json );
		
		System.out.println( jsonData );
		
		callEngineService.handleCallback( jsonData );
		
		return ContainerUtils.buildResSuccessMap( resData );


	}

}
