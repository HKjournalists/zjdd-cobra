package com.zaijiadd.app.dataquery.schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.applyflow.service.ApplyFlowService;
import com.zaijiadd.app.common.service.ImportFlowService;
import com.zaijiadd.app.dataquery.dao.YjsReqMsgDao;
import com.zaijiadd.app.dataquery.service.DataQueryService;
import com.zaijiadd.app.external.dao.ExternalDataDAO;

public class ScheduleTask {

	private static Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

	@Autowired
	private ImportFlowService importFlowService;
	
	@Autowired
	private DataQueryService service;

	@Autowired
	private ExternalDataDAO externalDataDao;
//	@Autowired
//	private ApplyFlowService applyFlowService;
	
	@Autowired
	private YjsReqMsgDao yjsReqMsgDao;

	public void dataImport() {

		// logger.info( "数据定时导入... ..." );
		// LogUtils.asyncLog( "定时任务同步流量数据" );

//		try {

			Map<String, Object> param = new HashMap<String, Object>();
			// 获取最新导入数据的时间，查询比其时间晚的所有数据，即为最新产生的数据
			Map<String, Object> timeInfo = service.timeInfo(param);
			if (timeInfo != null && timeInfo.get("protim") != null) param.put("created_at", timeInfo.get("protim"));
			List<Map<String, Object>> resData = externalDataDao.importHomeVisitorData(param);
			for (Map<String, Object> map : resData) {
				// 数据插入数据表
				// LogUtils.asyncLog( "【asyn】同步数据" + map.get( "name" ) );
				if ( !importFlowService.checkImportRepeatFlow( map ) ) {
					continue;
				}
				param.put("cussrc", map.get("channel"));// 客户来源
				param.put("srcdtl", map.get("uri"));// 来源详情
				param.put("protim", map.get("created_at"));// 处理时间
				param.put("lastphntim", null);// 上次通话时间
				param.put("phncnt", 0);// 通话次数
				param.put("name", map.get("name"));// 客户名称
				param.put("phone", map.get("phone"));// 客户电话
				param.put("email", map.get("email"));// 电子邮件
				param.put("wx", "");// 微信
				param.put("qq", "");// QQ
				param.put("city", map.get("city"));// 客户所在城市

				Integer channelId = parseChannelIdByUri( map.get("uri") + "" );
				Integer cityId = parseCityIdByName(map.get("city") + "");

				param.put( "channelId", channelId );
				param.put("ccity", cityId);// 分配城市，目前默认上海
				param.put("custype", "2");// 客户类别 小店OR经销商，目前默认小店
				param.put("cgroup", null);// 分配组
				param.put("cuser", null);// 分配客服
				// param.put( "txnsts", "0" );// 状态，默认未分配
				// param.put( "dtlsts", "01" );// 状态，默认无组未分配
				param.put("statusType", 0);
				param.put("salman", "");// 销售代表
				param.put("remark", map.get("remark"));// 备注

				service.dataInsert(param);
				// applyFlowService.cleanLoseEfficacyApplyStore();
			}

//		} catch (Exception e) {
			// logger.error( "同步流量数据异常", e );
			// LogUtils.error( "同步流量数据异常", e );
//		}

	}

	private Integer parseChannelIdByUri( String uri ) {
		
		if ( uri == null || uri.equals("") || uri.equals( "null" ) ) {
			return 0;
		}
		Integer channelId = yjsReqMsgDao.getChannelIdByUri( uri );
		
		return channelId;
		
	}
	
	private Integer parseCityIdByName(String cityName) {

		Integer cityId = 0;
		if (cityName == null || cityName.equals("")) {
			return cityId;
		}
		if (cityName.contains("大客户")) {
			cityId = 11;
		} else if (cityName.contains("北京")) {
			cityId = 12;
		} else if (cityName.contains("宁波")) {
			cityId = 13;
		} else if (cityName.contains("南京")) {
			cityId = 14;
		} else if (cityName.contains("苏州")) {
			cityId = 15;
		} else if (cityName.contains("广州")) {
			cityId = 16;
		} else if (cityName.contains("杭州")) {
			cityId = 17;
		} else if (cityName.contains("深圳")) {
			cityId = 18;
		} else if (cityName.contains("武汉")) {
			cityId = 19;
		} else if (cityName.contains("天津")) {
			cityId = 20;
		} else if (cityName.contains("上海")) {
			cityId = 21;
		}

		return cityId;

	}

	public void processExpiration() {
		System.out.println("定时任务");
	}

	public static void main(String[] args) {

		ScheduleTask s = new ScheduleTask();
		int i = s.parseCityIdByName("上海徐汇");
		System.out.println(i);
	}

	// public void DataImport(){
	// logger.info("数据定时导入... ...");
	// Map<String,Object> param = new HashMap<String,Object>();
	// List<Map<String,Object>> resData = service.dataImport(param);
	// File file = new File("e:\\data.txt");
	// PrintWriter w = null;
	// try {
	// w = new PrintWriter(file);
	// for(Map<String,Object> map:resData){
	// logger.info("data:"+map.get("id"));
	// String d = new
	// StringBuilder("insert into home_visitor values(").append(map.get("id")).append(",'"+map.get("ip"))
	// .append("','"+map.get("name")).append("','"+map.get("phone")).append("','"+map.get("email")).append("','"+map.get("remark"))
	// .append("','"+map.get("created_at")).append("','"+map.get("updated_at")).append("','"+map.get("channel")).append("','"+map.get("uri"))
	// .append("','"+map.get("city")).append("','"+map.get("u_country")).append("','"+map.get("u_province")).append("','"+map.get("u_city"))
	// .append("','"+map.get("u_district")).append("','"+map.get("u_carrier")).append("','"+map.get("host")).append("');").toString();
	// w.println(d);
	// }
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } finally{
	// if(w!=null){
	// w.close();
	// }
	// }
	//
	// }

	// public static void main(String[] args) {
	// Map<String,Object> map = new HashMap<String,Object>();
	// String d = new
	// StringBuilder("insert into home_visitor values('").append(map.get("id")).append("','"+map.get("ip"))
	// .append("','"+map.get("name")).append("','"+map.get("phone")).append("','"+map.get("email")).append("','"+map.get("remark"))
	// .append("','"+map.get("created_at")).append("','"+map.get("updated_at")).append("','"+map.get("channel")).append("','"+map.get("uri"))
	// .append("','"+map.get("city")).append("','"+map.get("u_country")).append("','"+map.get("u_province")).append("','"+map.get("u_city"))
	// .append("','"+map.get("u_district")).append("','"+map.get("u_carrier")).append("','"+map.get("host")).append("');").toString();
	// System.out.println(d);
	// }
}
