package com.zaijiadd.app.external.service;

import java.util.Map;

public interface ZaijiaddRequestService {
	
	public Boolean authUser( String zjtoken, String storeId );
	
	public Map<String, Object> getStoreInfo( String zjtoken, String storeId );

}
