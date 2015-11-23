package com.zaijiadd.app.external.service;

import java.util.Map;

public interface JdRequestService {
	
	public String getAccessTokenByCurrent();
	
	public String getAccessTokenInfo();
	
	public Map<String, Object> getProvinceInfo();
	
	public Map<String, Object> getCityInfo( Integer provinceId );
	
	public Map<String, Object> getCountyInfo( Integer cityId );
	
	public Map<String, Object> getTownInfo( Integer counryId );
	
	public void priceSubmit(  );

}
