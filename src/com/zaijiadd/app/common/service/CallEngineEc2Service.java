package com.zaijiadd.app.common.service;

public interface CallEngineEc2Service {
	
	public Boolean dialEc2( Integer jobId, String destMobile );
	
	public Boolean hangupEc2( Integer jobId );

}
