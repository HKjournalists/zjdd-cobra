/**
 * @(#)Tets.java 2015年12月1日 Copyright 2015 it.kedacom.com, Inc. All rights
 *               reserved.
 */

package com.zaijiadd.app.applyflow.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
