package com.zaijiadd.app.common.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.common.service.ImportFlowService;
import com.zaijiadd.app.dataquery.dao.YjsReqMsgDao;
import com.zaijiadd.app.dataquery.entity.CobraRepeatFlowEntity;
import com.zaijiadd.app.dataquery.service.DataChangeLogService;

public class ImportFlowServiceImpl implements ImportFlowService {

	@Autowired
	private DataChangeLogService dataChangeLogService;
	
	@Autowired
	private YjsReqMsgDao yjsReqMsgDao;
	
	@Override
	public Boolean checkImportRepeatFlow(Map<String, Object> inputs) {

		String mobile = inputs.get( "phone" ) + "";
		String remark = inputs.get( "remark" ) + "";
		
		Integer msgId = yjsReqMsgDao.getReqMsgIdByMobile( mobile );
		
		if ( msgId == null ) {
			return true;
		}
		
		if ( remark != null && !remark.trim().equals( "" ) && !remark.trim().equals( "null" ) ) {
			
			// 保存至重复流量表中
			CobraRepeatFlowEntity repeatFlowEntity = new CobraRepeatFlowEntity();
			repeatFlowEntity.setCity( inputs.get( "city" ) + "" );
			repeatFlowEntity.setCreatedAt( inputs.get( "created_at" ) + "" );
			repeatFlowEntity.setCreatedType( 1 );
			repeatFlowEntity.setEmail( inputs.get( "email" ) + "" );
			repeatFlowEntity.setMobile( inputs.get( "phone" ) + "" );
			repeatFlowEntity.setName( inputs.get( "name" ) + "" );
			repeatFlowEntity.setQq( "" );
			repeatFlowEntity.setRemark( inputs.get( "remark" ) + "" );
			repeatFlowEntity.setResourceChannel( inputs.get( "channel" ) + "" );
			repeatFlowEntity.setUri( inputs.get( "uri" ) + "" );
			repeatFlowEntity.setWechat( "" );
			
			yjsReqMsgDao.insertCobraRepeatFlow( repeatFlowEntity );
						
			// 生成remark日志
			dataChangeLogService.addRemarkChangeLog( -1, remark, msgId );
			
		}
		
		return false;
	
	}

}
