package com.zaijiadd.app.dataquery.dao;

import java.util.List;
import java.util.Map;

public interface DataChangeLogDAO {
	
	public Integer insertRemarkChangeLog( Map<String, Object> params );
	
	public Integer insertStatusChangeLog( Map<String, Object> params );
	
	public List<Map<String, Object>> queryRemarkChangeLog( Integer wId );
	
	public List<Map<String, Object>> queryStatusChangeLog( Integer wId );

}
