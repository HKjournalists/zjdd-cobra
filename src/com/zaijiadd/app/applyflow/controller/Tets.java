/**
 * @(#)Tets.java 2015年12月1日 Copyright 2015 it.kedacom.com, Inc. All rights
 *               reserved.
 */

package com.zaijiadd.app.applyflow.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.applyflow.entity.ApplyStore;
import com.zaijiadd.app.common.utils.DateUtils;

/**
 * (用一句话描述类的主要功能)
 * @author chentao
 * @date 2015年12月1日
 */

public class Tets {

	/**
	 * (用一句话描述方法的主要功能)
	 * @param args
	 */

	public static void main(String[] args) {
		// testTime();
		// testBigDecimal();
		// testJson();
		// testrand();
		// testTime1();
		// json2Map();
		// testBigDecimal2();
		testInteger();
	}

	/**
	 * (用一句话描述方法的主要功能)
	 */

	private static void testInteger() {
		Integer q = 100;
		Integer q1 = 8;
		if (q - q1 < 0) {
			System.out.println("ss");
		}
		System.out.println(q - q1);

	}

	/**
	 * (用一句话描述方法的主要功能)
	 */

	private static void testBigDecimal2() {
		Integer q = 7;
		BigDecimal bigDecimal = new BigDecimal(q);
		System.out.println(bigDecimal);

	}

	private static void json2Map() {
		String jsonData = "{type:1,userId:1,page:1,pageCount:10}";
		Map<String, Object> jsonMap = JSON.parseObject(jsonData, HashMap.class);

		//
		if (jsonMap.containsKey("page")) {
			int pageCount = (int) jsonMap.get("pageCount");// 每页的数量
			int page = (int) jsonMap.get("page");
			jsonMap.put("start", (page - 1) * pageCount);
		}
		;
		List<HashMap> configDataList = (List<HashMap>) jsonMap.get("configs");

		for (HashMap configData : configDataList) {// 这行出错
			int roleId = (Integer) configData.get("type");
			System.out.println("config.type:" + roleId);
		}

		// testTime1();
		testIf();
	}

	/**
	 * (用一句话描述方法的主要功能)
	 */

	private static void testIf() {
		if (3 > 2) {
			System.out.println(1);
		}
		System.out.println(2);
		System.out.println(3);
		if (3 > 2) {
			System.out.println(1);
		} else {
			System.out.println(2);
		}

		System.out.println(3);
	}

	/**
	 * (用一句话描述方法的主要功能)
	 */

	private static void testTime1() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		System.out.println(date);
		System.out.println(simpleDateFormat.format(date));
		long time = date.getTime();
		System.out.println(date.getTime());
		Long thirdDayTime = (long) (1 * 72 * 60 * 60 * 1000);
		System.out.println("thirdDay:" + thirdDayTime);
		long thirdDay = time + thirdDayTime;
		System.out.println(thirdDay);
		Date date2 = new Date(thirdDay);

		String format = simpleDateFormat.format(date2);
		System.out.println(format);
		// date.getTime()+thirdDay;thirdDay
		// System.out.println("TIAN"+);
	}

	/**
	 * (用一句话描述方法的主要功能)
	 */

	private static void testrand() {
		Random r = new Random();
		Double d = r.nextDouble();
		System.out.println(d);
		String s = d + "";
		s = s.substring(3, 3 + 6);
		System.out.println(s);
	}

	/**
	 * (用一句话描述方法的主要功能)
	 */

	private static void testJson() {
		ApplyStore applyStore = new ApplyStore();
		String jsonString = JSONObject.toJSONString(applyStore);
		System.out.println(jsonString);
	}

	/**
	 * (用一句话描述方法的主要功能)
	 */

	private static void testBigDecimal() {
		BigDecimal bigDecimal1 = new BigDecimal(0);
		BigDecimal bigDecimal2 = new BigDecimal(2);
		BigDecimal add = bigDecimal1.add(bigDecimal2);
		System.out.println(add);

	}

	/**
	 * (用一句话描述方法的主要功能)
	 */

	private static void testTime() {
		String visitTime = "2015-12-01 09:46:26";
		Date transStringToDate = DateUtils.transStringToDate(visitTime, "yyyy-MM-dd ");
		String string = transStringToDate.toString();
		System.out.println(string);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String format2 = format.format(transStringToDate);
		System.out.println(format2);
		String[] split = visitTime.split(" ");
		System.out.println(split[0] + "11");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("a", "a");
		param.put("a", "b");
		Date date2 = new Date();
		date2.toString();
		System.out.println(date2.getTime());
		System.out.println(param.get("a"));
		long time = 1440432000000L;
		Date date = new Date(date2.getTime());
		String format3 = format.format(date);
		System.err.println("format3" + format3);
	}
}
