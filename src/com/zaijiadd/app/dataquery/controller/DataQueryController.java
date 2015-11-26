package com.zaijiadd.app.dataquery.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.ParseUtils;
import com.zaijiadd.app.dataquery.service.DataQueryService;

@RequestMapping ( "/query" )
@Controller
public class DataQueryController {

	@Autowired
	private DataQueryService service;

	@RequestMapping ( value = "/dataList", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> dataList( HttpServletRequest request ) {

		List<Map<String, Object>> resData = new ArrayList<Map<String, Object>>();

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		String khly = jsonRequest.getString( "khly" );
		String hzlx = jsonRequest.getString( "hzlx" );
		String cs = jsonRequest.getString( "cs" );
		String zt = jsonRequest.getString( "zt" );
		String uid = jsonRequest.getString( "uid" );
		String dtlsts = jsonRequest.getString( "dtlsts" );
		String page = jsonRequest.getString( "page" );
		String searchStr = jsonRequest.getString( "searchStr" );
		Integer pageCount = jsonRequest.getInteger( "pageCount" );
		if ( pageCount == null || pageCount == 0 ) {
			pageCount = 20;
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "cussrc", khly );
		param.put( "custype", hzlx );
		param.put( "city", cs );
		param.put( "txnsts", zt );
		param.put( "cuser", uid );
		param.put( "uid", uid );
		param.put( "dtlsts", dtlsts );
		param.put( "start", ( Integer.parseInt( page ) - 1 ) * pageCount );
		param.put( "end", pageCount );
		param.put( "searchStr", searchStr );

		Map<String, Object> user = service.userInfo( param );
		String roleid = user.get( "roleid" ).toString();
		Integer dataCount = 0;
		if ( "0".equals( roleid ) ) {// 管理员
			param.put( "cuser", null );
			resData = service.queryReqMsg( param );
			dataCount = service.queryReqMsgByCount( param );
		} else if ( "1".equals( roleid ) ) {// 城市ceo
			param.put( "cuser", null );
			param.put( "city", user.get( "orgid" ) );
			resData = service.queryReqMsg( param );
			dataCount = service.queryReqMsgByCount( param );
		} else if ( "2".equals( roleid ) ) {
			param.put( "cuser", null );
			param.put( "city", user.get( "upid" ) );
			param.put( "cgroup", user.get( "orgid" ) );
			resData = service.queryReqMsg( param );
			dataCount = service.queryReqMsgByCount( param );
		} else {// 业务人员
			param.put( "city", user.get( "upid" ) );
			param.put( "ugroup", user.get( "orgid" ) );
			resData = service.queryReqMsg( param );
			dataCount = service.queryReqMsgByCount( param );
		}
		param.put( "result", resData );
		param.put( "dataCount", dataCount );
		return ContainerUtils.buildResSuccessMap( param );

	}

	@RequestMapping ( value = "/updateData", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> dataUpdate( HttpServletRequest request )
			throws UnsupportedEncodingException {

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );

		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "id", jsonRequest.getString( "id" ) );
		param.put( "name", URLDecoder.decode(
				( jsonRequest.getString( "name" ) == null ? "" : jsonRequest
						.getString( "name" ) ), "UTF-8" ) );
		param.put( "phone", jsonRequest.getString( "phone" ) );
		param.put( "wx", jsonRequest.getString( "wx" ) );
		param.put( "qq", jsonRequest.getString( "qq" ) );
		param.put( "ccity", jsonRequest.getString( "ccity" ) );
		param.put( "custype", jsonRequest.getString( "custype" ) );
		param.put( "lb", jsonRequest.getString( "lb" ) );
		param.put( "dtlsts", jsonRequest.getString( "dtlsts" ) );
		param.put( "remark", URLDecoder.decode(
				( jsonRequest.getString( "remark" ) == null ? "" : jsonRequest
						.getString( "remark" ) ), "UTF-8" ) );
		param.put( "cgroup", jsonRequest.getString( "cgroup" ) );

		service.updReqMsg( param );

		return ContainerUtils.buildResSuccessMap( param );

	}

	@RequestMapping ( value = "/groupList", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> groupList( HttpServletRequest request ) {

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );

		List<Map<String, Object>> resData = new ArrayList<Map<String, Object>>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "uid", jsonRequest.getString( "uid" ) );
		param.put( "urole", jsonRequest.getString( "urole" ) );
		resData = service.groupList( param );
		if ( resData == null || resData.size() == 0 ) {
			resData.add( service.groupInfo( param ) );
		}
		for ( Map<String, Object> map : resData ) {
			param.put( "orgid", map.get( "orgid" ) );
			List<Map<String, Object>> workData = new ArrayList<Map<String, Object>>();
			workData = service.workCount( param );
			map.put( "workcount", workData == null ? 0 : workData.size() );
			List<Map<String, Object>> userData = new ArrayList<Map<String, Object>>();
			userData = service.userList( param );
			map.put( "usercount", userData == null ? 0 : userData.size() );
		}
		param.put( "result", resData );
		return ContainerUtils.buildResSuccessMap( param );
	}

	@RequestMapping ( value = "/userList", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> userList( HttpServletRequest request ) {

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );

		List<Map<String, Object>> resData = new ArrayList<Map<String, Object>>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "orgid", jsonRequest.getString( "orgid" ) );
		resData = service.userList( param );
		for ( Map<String, Object> map : resData ) {
			param.put( "cuser", map.get( "userid" ) );
			List<Map<String, Object>> workData = new ArrayList<Map<String, Object>>();
			workData = service.workCountUser( param );
			map.put( "workcount", workData == null ? 0 : workData.size() );
		}
		param.put( "result", resData );
		return ContainerUtils.buildResSuccessMap( param );
	}

	@RequestMapping ( value = "/dispatchGroupWork", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> dispatchGroupWork( HttpServletRequest request ) {

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );

		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "wid", jsonRequest.getString( "wid" ) );
		param.put( "orgid", jsonRequest.getString( "orgid" ) );
		service.dispatchGroupWork( param );
		return ContainerUtils.buildResSuccessMap( param );

	}

	@RequestMapping ( value = "/dispatchPersonWork", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> dispatchPersonWork( HttpServletRequest request ) {

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );

		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "wid", jsonRequest.getString( "wid" ) );
		param.put( "uid", jsonRequest.getString( "uid" ) );
		param.put( "orgid", service.groupInfo( param ).get( "orgid" ) );

		service.dispatchPersonWork( param );
		return ContainerUtils.buildResSuccessMap( param );
	}

	@RequestMapping ( value = "/login", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> login( HttpServletRequest request,
			HttpServletResponse response ) throws ServletException, IOException {

		Map<String, Object> res = new HashMap<String, Object>();

		String password = "";
		String uid = "";
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		if ( jsonRequest == null ) {
			password = request.getParameter( "passwd" ) + "";
			uid = request.getParameter( "uid" ) + "";
		} else {
			password = jsonRequest.getString( "passwd" );
			uid = jsonRequest.getString( "uid" );
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "passwd", password );
		param.put( "mobile", uid );
		Map<String, Object> user = service.userInfo( param );

		if ( user == null || user.size() == 0 ) {

			return ContainerUtils.buildHeadMap( res, -1, "用户不存在" );

		}

		res.put( "uid", user.get( "id" ) );
		res.put( "username", user.get( "realname" ) );
		res.put( "roleid", user.get( "roleid" ) );
		res.put( "isleader", user.get( "isleader" ) );
		res.put( "orgid", user.get( "orgid" ) );

		return ContainerUtils.buildResSuccessMap( res );

	}

	@RequestMapping ( value = "/query/msgQuery", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> msgQuery( HttpServletRequest request,
			HttpServletResponse response ) throws ServletException, IOException {

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );

		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "msgid", jsonRequest.getString( "wid" ) );
		param.put( "userid", jsonRequest.getString( "uid" ) );

		return ContainerUtils.buildResSuccessMap( param );

	}

	@RequestMapping ( value = "/statusType", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> statusType( HttpServletRequest request ) {

		Map<String, Object> param = new HashMap<String, Object>();

		param = service.getStatusDict();

		return ContainerUtils.buildResSuccessMap( param );

	}

}
