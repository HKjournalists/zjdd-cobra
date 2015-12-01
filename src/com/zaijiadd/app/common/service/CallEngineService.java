package com.zaijiadd.app.common.service;

import com.alibaba.fastjson.JSONObject;

public interface CallEngineService {
	
	public Boolean dial( String destMobile, Integer userId, Integer wId );
	
	public Boolean hangup( String destMobile, Integer userId );
	
	public void handleCallback( JSONObject data );

}
