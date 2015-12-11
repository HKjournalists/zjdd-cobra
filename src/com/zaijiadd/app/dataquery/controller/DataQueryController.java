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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.ParseUtils;
import com.zaijiadd.app.dataquery.dto.YjsReqMsgDTO;
import com.zaijiadd.app.dataquery.entity.AllotLogEntity;
import com.zaijiadd.app.dataquery.service.AllotLogService;
import com.zaijiadd.app.dataquery.service.AutoAllotFlowService;
import com.zaijiadd.app.dataquery.service.DataChangeLogService;
import com.zaijiadd.app.dataquery.service.DataQueryService;
import com.zaijiadd.app.dataquery.utils.DataTransUtils;
import com.zaijiadd.app.user.dto.CallingLogDTO;

@RequestMapping ( "/query" )
@Controller
public class DataQueryController {

	@Autowired
	private AllotLogService allotLogService;
	
	@Autowired
	private DataQueryService service;
	
	@Autowired
	private DataChangeLogService dataChangeLogService;
	
	@Autowired
	private AutoAllotFlowService autoAllotFlowService;

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
		String statusType = jsonRequest.getString( "statusType" );
		String page = jsonRequest.getString( "page" );
		String searchStr = jsonRequest.getString( "searchStr" );
		Integer pageCount = jsonRequest.getInteger( "pageCount" );
		String beginDate = jsonRequest.getString( "beginDate" );
		String endDate = jsonRequest.getString( "endDate" );
		String queryDate = jsonRequest.getString( "queryDate" );
		Integer memberId = jsonRequest.getInteger( "memberId" );
		Integer orgId = jsonRequest.getInteger( "orgId" );
		
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
		param.put( "statusType", statusType );
		param.put( "start", ( Integer.parseInt( page ) - 1 ) * pageCount );
		param.put( "end", pageCount );
		param.put( "searchStr", searchStr );
		param.put( "beginDate", beginDate );
		param.put( "endDate", endDate );
		param.put( "queryDate", queryDate );
		param.put( "memberId", memberId );
		param.put( "orgId", orgId );

		Map<String, Object> user = service.userInfo( param );
		String roleid = user.get( "roleid" ).toString();
		Integer dataCount = 0;
		
		if ( statusType != null || hzlx != null || searchStr != null || beginDate != null || endDate != null || memberId != null || orgId != null ) {
			uid = null;
			param.put( "uid", null );
		}
		
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
		param.put( "statusType", jsonRequest.getString( "statusType" ) );
		param.put( "remark", URLDecoder.decode(
				( jsonRequest.getString( "remark" ) == null ? "" : jsonRequest
						.getString( "remark" ) ), "UTF-8" ) );
		param.put( "cgroup", jsonRequest.getString( "cgroup" ) );
		param.put( "provinceId", jsonRequest.getInteger( "provinceId" ) );
		param.put( "cityId", jsonRequest.getInteger( "cityId" ) );

		service.updReqMsg( param );
		
		String remark = jsonRequest.getString( "remark" );
		if ( remark != null && !remark.trim().equals( "" ) ) {
			dataChangeLogService.addRemarkChangeLog( jsonRequest.getInteger( "userId" ), remark, jsonRequest.getInteger( "id" ) );
		}
		
		Integer statusType = jsonRequest.getInteger( "statusType" );
		if ( statusType != null ) {
			dataChangeLogService.addStatusTypeChangeLog( jsonRequest.getInteger( "userId" ), statusType, jsonRequest.getInteger( "id" ) );
		}
		
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
			param.put( "cuser", map.get( "user_id" ) );
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
		
		AllotLogEntity allotLogEntity = new AllotLogEntity();
		allotLogEntity.setAllotStatusTypeId( 1 );
		allotLogEntity.setAllotToUserId( jsonRequest.getInteger( "orgid" ) );
		allotLogEntity.setAllotUserId( jsonRequest.getInteger( "userId" ) );
		allotLogEntity.setIsGroupLeader( 1 );
		allotLogEntity.setMsgId( jsonRequest.getInteger( "wid" ) );
		
		allotLogService.addAllotLog( allotLogEntity );
		
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
		
		AllotLogEntity allotLogEntity = new AllotLogEntity();
		allotLogEntity.setAllotStatusTypeId( 1 );
		allotLogEntity.setAllotToUserId( jsonRequest.getInteger( "uid" ) );
		allotLogEntity.setAllotUserId( jsonRequest.getInteger( "userId" ) );
		allotLogEntity.setIsGroupLeader( 0 );
		allotLogEntity.setMsgId( jsonRequest.getInteger( "wid" ) );
		
		allotLogService.addAllotLog( allotLogEntity );
		
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
		Map<String, Object> user = service.userInfo(param);

		if ( user == null || user.size() == 0 ) {
			return ContainerUtils.buildHeadMap( res, -1, "用户不存在" );

		}

		res.put( "uid", user.get( "id" ) );
		res.put( "username", user.get( "realname" ) );
		res.put( "roleid", user.get( "roleid" ) );
		res.put( "isleader", user.get( "isleader" ) );
		res.put( "orgid", user.get( "orgid" ) );
		
		Integer autoAllotStatus = autoAllotFlowService.QueryAutoAllotStatus( Integer.parseInt( String.valueOf( user.get( "id" ) ) ) );
		Integer autoAllotAuth = 0;
		
		String roleId = user.get( "roleid" ) + "";
		Integer allotAuth = 1;
		if ( roleId != null && !roleId.equals( "" ) && roleId.equals( "0" ) ) {
			allotAuth = 0;
		}
		
		res.put( "allotAuth", allotAuth );
		res.put( "autoAllotStatus", autoAllotStatus );
		res.put( "autoAllotAuth", autoAllotAuth );

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
	
	@RequestMapping ( value = "/dispatchDetail", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> dispatchDetail( HttpServletRequest request ) {

		Map<String, Object> param = new HashMap<String, Object>();

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		Integer wId = jsonRequest.getInteger( "wId" );
		
		List<Map<String, Object>> res = allotLogService.getDispatchDetail( wId );
		
		param.put( "dispatchDetail", res );

		return ContainerUtils.buildResSuccessMap( param );

	}
	
	@RequestMapping ( value = "/remarkLog", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> remarkChangeLog( HttpServletRequest request ) {

		Map<String, Object> param = new HashMap<String, Object>();

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		Integer wId = jsonRequest.getInteger( "wId" );
		
		List<Map<String, Object>> res = dataChangeLogService.queryRemarkChangeLog( wId );
		
		param.put( "remarkLogList", res );

		return ContainerUtils.buildResSuccessMap( param );

	}
	
	@RequestMapping ( value = "/statusLog", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> statusLog( HttpServletRequest request ) {

		Map<String, Object> param = new HashMap<String, Object>();

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		Integer wId = jsonRequest.getInteger( "wId" );
		
		List<Map<String, Object>> res = dataChangeLogService.queryStatusChangeLog( wId );
		
		param.put( "statusLogList", res );

		return ContainerUtils.buildResSuccessMap( param );

	}
	
	@RequestMapping ( value = "/callingLog", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> callingLog( HttpServletRequest request ) {

		Map<String, Object> param = new HashMap<String, Object>();

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		Integer wId = jsonRequest.getInteger( "wId" );
		
		List<CallingLogDTO> res = dataChangeLogService.queryCallingLog( wId );
		
		param.put( "callingLog", res );

		return ContainerUtils.buildResSuccessMap( param );

	}
	
	@RequestMapping ( value = "/addMsg", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> addMsg( HttpServletRequest request ) {

		Map<String, Object> param = new HashMap<String, Object>();

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		
		YjsReqMsgDTO yjsReqMsgDto = DataTransUtils.transViewmodelToDtoByYjsReqMsg( jsonRequest );
		
		if ( yjsReqMsgDto.getMobile() != null && !yjsReqMsgDto.getMobile().equals( "" ) ) {
			if ( service.isMobileExist( yjsReqMsgDto.getMobile() ) ) {
				return ContainerUtils.buildHeadMap( param, 0, "用户已存在" );
			}
		}
		
		if ( yjsReqMsgDto.getCcity() == null ) {
			yjsReqMsgDto.setCcity( 21 );
		}
		
		if ( yjsReqMsgDto.getCoType() == -1 ) {
			yjsReqMsgDto.setCoType( 2 );
		}
		
		yjsReqMsgDto.setCreatedType( 2 );
		
		service.addMsg( yjsReqMsgDto );
		
		String remark = yjsReqMsgDto.getRemark();
		if ( remark != null && !remark.trim().equals( "" ) ) {
			dataChangeLogService.addRemarkChangeLog( yjsReqMsgDto.getOperatorUserId(), remark, yjsReqMsgDto.getMsgId() );
		}

		return ContainerUtils.buildResSuccessMap( param );

	}
	
	@RequestMapping ( value = "/recoveryMsg", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> recoveryMsg( HttpServletRequest request ) {

		Map<String, Object> param = new HashMap<String, Object>();

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
		
		Integer userId = jsonRequest.getInteger( "userId" );
		Integer msgId = jsonRequest.getInteger( "msgId" );
		
		if ( service.recoverMsg( userId, msgId ) ) {
			return ContainerUtils.buildResSuccessMap( param );
		}

		return ContainerUtils.buildResFailMap();

	}

}
