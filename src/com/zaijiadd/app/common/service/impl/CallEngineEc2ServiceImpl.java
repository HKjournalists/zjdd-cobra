package com.zaijiadd.app.common.service.impl;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.common.service.CallEngineEc2Service;
import com.zaijiadd.app.common.utils.HttpRequestUtils;
import com.zaijiadd.app.utils.constants.ConstantsForAccount;

public class CallEngineEc2ServiceImpl implements CallEngineEc2Service {

	@Override
	public Boolean dialEc2( Integer jobId, String destMobile ) {

		StringBuffer sb = new StringBuffer();
		sb.append( "accessId=" + ConstantsForAccount.CALLENGINE_ACCESS_ID );
		sb.append( "&accessKey=" + ConstantsForAccount.CALLENGINE_ACCESS_KEY );
		sb.append( "&username=" + jobId  );
		sb.append( "&destNum=" + destMobile );
		
		System.out.println( sb.toString() );
		
		System.out.println( new Date() );
		
		String res = HttpRequestUtils.sendUTF8Post( ConstantsForAccount.CALLENGINE_URL, ConstantsForAccount.CALLENGINE_DIAL_METHOD, sb.toString(), "" );
		System.out.println( res );
		
		if ( res == null || res.equals( "" ) ) {
			return false;
		}
		
		JSONObject resJson = JSONObject.parseObject( res );
		String code = resJson.getString( "code" );
		if ( code != null && code.equals( "0" ) ) {
			return true;
		} else {
			return false;
		}
	
	}

	@Override
	public Boolean hangupEc2( Integer jobId ) {
		
		StringBuffer sb = new StringBuffer();
		sb.append( "accessId=" + ConstantsForAccount.CALLENGINE_ACCESS_ID );
		sb.append( "&accessKey=" + ConstantsForAccount.CALLENGINE_ACCESS_KEY );
		sb.append( "&username=" + jobId  );
		
		String res = HttpRequestUtils.sendUTF8Post( ConstantsForAccount.CALLENGINE_URL, ConstantsForAccount.CALLENGINE_HANGUP_METHOD, sb.toString(), "" );

		if ( res == null || res.equals( "" ) ) {
			return false;
		}
		
		JSONObject resJson = JSONObject.parseObject( res );
		String code = resJson.getString( "code" );
		if ( code != null && code.equals( "0" ) ) {
			return true;
		} 
		return false;
		
	}
	
	public static void main( String[] args ) {
		
		CallEngineEc2ServiceImpl e = new CallEngineEc2ServiceImpl();
		e.dialEc2( 8080, "13611920055" );
		
	}
	
}
