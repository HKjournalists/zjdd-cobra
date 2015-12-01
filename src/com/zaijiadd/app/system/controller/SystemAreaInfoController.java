package com.zaijiadd.app.system.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.system.dao.SystemAreaDAO;
import com.zaijiadd.app.system.service.SystemAreaInfoService;

@RequestMapping ( "/system" )
@Controller
public class SystemAreaInfoController {
	
	@Autowired
	private SystemAreaInfoService systemAreaInfoService;
	
	@Autowired
	private SystemAreaDAO systemAreaDao;
	
	@RequestMapping ( value = "/cityInfo", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> cityInfo( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		StringBuffer info = new StringBuffer();
		InputStream in;
		try {
			in = request.getInputStream();
			BufferedInputStream buf = new BufferedInputStream( in );
			byte[] buffer = new byte[1024];
			int iRead;
			while ( ( iRead = buf.read( buffer ) ) != -1 ) {
				info.append( new String( buffer, 0, iRead, "UTF-8" ) );
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		JSONArray jsonArr = ( JSONArray ) JSONArray.parse( info.toString() );
		for ( int i = 0; i < jsonArr.size(); i++ ) {
			JSONObject json = ( JSONObject ) jsonArr.get( i );
			Integer cityId = json.getInteger( "cityId" );
			String cityName = json.getString( "cityName" );
			Integer provinceId = json.getInteger( "provinceId" );
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "provinceId", provinceId );
			params.put( "cityId", cityId );
			params.put( "cityName", cityName );
			
			systemAreaDao.insertCityInfo( params );
		}

		return ContainerUtils.buildHeadMap( resData, -2, "呼叫失败" );

	}
	
	@RequestMapping ( value = "/provinceInfo", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> provinceInfo( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		StringBuffer info = new StringBuffer();
		InputStream in;
		try {
			in = request.getInputStream();
			BufferedInputStream buf = new BufferedInputStream( in );
			byte[] buffer = new byte[1024];
			int iRead;
			while ( ( iRead = buf.read( buffer ) ) != -1 ) {
				info.append( new String( buffer, 0, iRead, "UTF-8" ) );
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		JSONArray jsonArr = ( JSONArray ) JSONArray.parse( info.toString() );
//		JSONObject inputJson = JSONObject.parseObject( info.toString() );
//		JSONArray jsonArr = jsonRequest.getJSONArray( "provinceList" );
		for ( int i = 0; i < jsonArr.size(); i++ ) {
			JSONObject json = ( JSONObject ) jsonArr.get( i );
			Integer provinceId = json.getInteger( "provinceId" );
			String provinceName = json.getString( "provinceName" );
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "provinceId", provinceId );
			params.put( "provinceName", provinceName );
			
			systemAreaDao.insertProviceInfo( params );
		}

		return ContainerUtils.buildHeadMap( resData, -2, "呼叫失败" );

	}
	
	@RequestMapping ( value = "/channelInfo", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> channelInfo( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		List<Map<String, Object>> res = systemAreaInfoService.getSystemChannelInfo();
		resData.put( "channelInfo", res );

		return ContainerUtils.buildHeadMap( resData, 1, "操作成功" );

	}

}
