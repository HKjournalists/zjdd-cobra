package com.zaijiadd.app.dataquery.utils;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.dataquery.dto.YjsReqMsgDTO;

public class DataTransUtils {
	
	public static YjsReqMsgDTO transViewmodelToDtoByYjsReqMsg( JSONObject data ) {
		
		YjsReqMsgDTO dto = new YjsReqMsgDTO();
		dto.setChannelId( data.getInteger( "channelId" ) );
		dto.setCityId( data.getInteger( "cityId" ) );
		dto.setCoType( data.getInteger( "coType" ) );
		dto.setMobile( data.getString( "phone" ) );
		dto.setOperatorUserId( data.getInteger( "userId" ) );
		dto.setProvinceId( data.getInteger( "provinceId" ) );
		dto.setQq( data.getString( "qq" ) );
		dto.setRealname( data.getString( "name" ) );
		dto.setRemark( data.getString( "remark" ) );
		dto.setWechat( data.getString( "wechat" ) );
		dto.setCcity( data.getInteger( "ccity" ) );
		
		return dto;
		
	}

}
