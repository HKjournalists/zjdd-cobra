package com.zaijiadd.app.common.service;

public interface CallEngineService {
	
	public Boolean dial( String destMobile, Integer userId, Integer wId );
	
	public Boolean hangup( String destMobile, Integer userId );

}
