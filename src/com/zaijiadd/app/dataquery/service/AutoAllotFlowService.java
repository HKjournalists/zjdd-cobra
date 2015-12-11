package com.zaijiadd.app.dataquery.service;

public interface AutoAllotFlowService {
	
	public Boolean AutoAllotSetting( Integer userId, String[] allotUserArr );
	
	public Boolean CloseAutoAllot( Integer userId );
	
	public Integer QueryAutoAllotStatus( Integer userId );
	
	public void handleAutoAllotFlow();

}
