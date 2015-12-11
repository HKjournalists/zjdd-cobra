package com.zaijiadd.app.dataquery.dao;

import java.util.List;
import java.util.Map;

import com.zaijiadd.app.dataquery.dto.YjsReqMsgDTO;
import com.zaijiadd.app.dataquery.entity.CobraRepeatFlowEntity;

public interface YjsReqMsgDao {
    
    /**条件查询信息*/
    public List<Map<String,Object>> queryByParam(Map<String,Object> param);
    /**查询信息总数*/
    public Integer queryByParamCount(Map<String,Object> param);
    
    /**更新信息*/
    public void updReqMsg(Map<String,Object> param);
    
    /**查询用户组信息*/
    public List<Map<String,Object>> groupList(Map<String,Object> param);
    /**查询组信息*/
    public Map<String,Object> groupInfo(Map<String,Object> param);
    /**查询任务数息*/
    public List<Map<String,Object>> workCount(Map<String,Object> param);
    /**查询用户任务数息*/
    public List<Map<String,Object>> workCountUser(Map<String,Object> param);
    /**查询用户信息*/
    public List<Map<String,Object>> userList(Map<String,Object> param);
    /**查询用户信息*/
    public Map<String,Object> userInfo(Map<String,Object> param);
    /**分配记录到组*/
    public void dispatchGroupWork(Map<String,Object> param);
    /**分配记录到人*/
    public void dispatchPersonWork(Map<String,Object> param);
    /**数据导入*/
    public List<Map<String,Object>> dataImport(Map<String,Object> param);
    /**数据新增*/
    public void dataInsert(Map<String,Object> param);
    /**查询最新记录时间*/
    public Map<String,Object> timeInfo(Map<String,Object> param);
    
    public List<Map<String, Object>> getStatusType();
    
    public void updatePhoneInfo( Map<String, Object> params );
    
    public void insertYjsReqMsg( YjsReqMsgDTO dto );
    
    public Integer getReqMsgIdByMobile( String mobile );
    
    public Integer insertCobraRepeatFlow( CobraRepeatFlowEntity repeatFlowEntity );
    
    public Integer recoveryMsg( Integer msgId );
    
    public Integer getChannelIdByUri( String uri );
    
    public Integer getUserRoleByUserId( Integer userId );
    
    public Integer recoveryMsgByCEO( Integer msgId );
    
    public Integer recoveryMsgByLeader( Integer msgId );
    
    public YjsReqMsgDTO getMsgInfoByMsgId( Integer msgId );
    
    public Integer updateAutoAllotStatus( Map<String, Object> params );
    
    public Integer queryAutoAllotStatus( Integer userId );
    
}