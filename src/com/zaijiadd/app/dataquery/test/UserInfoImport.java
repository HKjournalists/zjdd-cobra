package com.zaijiadd.app.dataquery.test;

import java.io.File;
import java.io.PrintWriter;



public class UserInfoImport {
	
	static String[] roles = new String[]{"","部门负责人","主管","销售"};
	static String[] citys = new String[]{"","大客户","北京","宁波","南京","苏州","广州","杭州","深圳","武汉","天津","上海"};
	
	public static void main(String[] args) {
//		File userInfo = new File("C:\\Users\\hefengwen\\Desktop\\眼镜蛇项目\\03-开发\\crm系统使用人员 招商服务部xlsx.xls");
//		Workbook readwb = null;
//		File file = new File("e:\\data.txt");
//		PrintWriter w = null;
//		System.out.println("row");
//		try {
//			w = new PrintWriter(file);
//			readwb = Workbook.getWorkbook(userInfo);
//			Sheet sheet = readwb.getSheet(0);
//			int rows = sheet.getRows();
//			System.out.println("row:"+rows);
//			for(int i=1;i<rows-1;i++){
//				Cell c0 = sheet.getCell(0, i);
//				Cell c1 = sheet.getCell(1, i);
//				Cell c2 = sheet.getCell(2, i);
//				Cell c3 = sheet.getCell(3, i);
//				Cell c4 = sheet.getCell(4, i);
//				Cell c5 = sheet.getCell(5, i);
//				String username = String.valueOf(c0.getContents()).trim();
//				String role = String.valueOf(c1.getContents()).trim();
//				for(int j=1;j<roles.length;j++){
//					if(role.equals(roles[j])){
//						role = j+"";
//					}
//				}
//				String userid = String.valueOf(c2.getContents()).trim();
//				String email = String.valueOf(c3.getContents()).trim();
//				String isleader = String.valueOf(c4.getContents()).trim();
//				if(role.equals("3")){
//					isleader = "0";
//				}else{
//					isleader = "1";
//				}
//				String city = String.valueOf(c5.getContents()).trim();
//				for(int j=1;j<citys.length;j++){
//					if(city.equals(citys[j])){
//						city = j+"";
//					}
//				}
//				String d = new StringBuilder("insert into yjs_user values('").append(userid+"','").
//						append(username+"',").append("'123456','").append(email+"',").
//						append(role+",'").append(isleader+"',").append(city+"").append(");").toString();
//				System.out.println(d);
//				w.println(d);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally{
//			if(w!=null){
//				w.close();
//			}
//		}
	}

}
