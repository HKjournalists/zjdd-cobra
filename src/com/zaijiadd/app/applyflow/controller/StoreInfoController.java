package com.zaijiadd.app.applyflow.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.applyflow.dto.StoreAddressDTO;
import com.zaijiadd.app.applyflow.dto.StoreInfoVO;
import com.zaijiadd.app.applyflow.entity.StoreInfo;
import com.zaijiadd.app.applyflow.service.StoreInfoService;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.ParseUtils;
import com.zaijiadd.app.user.entity.UserInfoEntity;

/**
 * 店铺开户上架入口
 * @author Guo Gary
 * @create date  2015/12/01 16:00
 * @version <b>1.0.0</b>
 */
@RequestMapping("/storeinfo")
@RestController
public class StoreInfoController {
	
	@Autowired
	private StoreInfoService storeInfoService;
	
	/**
	 * 开户申请
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(StoreInfo info, HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
			info = jsonRequest.parseObject(jsonRequest.toJSONString(), StoreInfo.class);
			Integer userId = Integer.parseInt(jsonRequest.getString("userId"));
			UserInfoEntity userInfoEntity = (UserInfoEntity) request.getSession().getAttribute("user");
			info.setApplicant(userId);
			info.setApplicantTime(new Timestamp(new Date().getTime()));
			StoreInfoVO storeInfoVO = new StoreInfoVO();
			this.storeInfoService.insert(info);
			PropertyUtils.copyProperties(storeInfoVO, info);
			param.put("detail", storeInfoVO);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);

	}
	
	/**
	 * 开户申请查看
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detail/{storeId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> detail(@PathVariable Long storeId) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			StoreInfoVO storeInfoVO = new StoreInfoVO();
			//PropertyUtils.copyProperties(storeInfoVO, this.storeInfoService.selectByPrimaryKey(storeId));
			param.put("detail", this.storeInfoService.selectByPrimaryKey(storeId));
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);

	}
	
	/**
	 * 地址审核初始化
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addressAuditInit/{storeId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> addressAuditInit(@PathVariable Long storeId) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			StoreInfo storeInfo = this.storeInfoService.selectByPrimaryKey(storeId);
			StoreAddressDTO storeAddressDTO = new StoreAddressDTO();
			PropertyUtils.copyProperties(storeAddressDTO, storeInfo);
			storeAddressDTO.setDetailAddress(storeInfo.getCapitalName() + storeInfo.getCityName() 
					+ storeInfo.getDistrictName() + storeInfo.getStreetName() + storeInfo.getDetailAddress());
			param.put("detail", storeAddressDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);
	}
	
	
	
	/**
	 * 申请开店
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/applicationShop", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addInviteUserMsg(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
			JSONArray fileUrls = jsonRequest.getJSONArray("fileUrls");
			//String[] fileUrls = request.getParameterValues("fileUrls");
			Long storeId = jsonRequest.getLong("storeId");
			Integer userId = jsonRequest.getInteger("userId");
			this.storeInfoService.applicationShop(fileUrls, storeId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);

	}
	
	/**
	 * 店铺图片列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detail/imgs/{storeId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> imgsDetail(@PathVariable Long storeId) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			param.put("detail", this.storeInfoService.selectImgsByStoreId(storeId));
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);

	}
	/**
	 * 地址审核
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addressAudit ", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addressAudit(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
			//店铺ID
			Long storeId = jsonRequest.getLong("storeId");
			String auditOpinion = jsonRequest.getString("auditOpinion");
			//1：通过，0：不通过
			StoreInfo storeInfo = new StoreInfo();
			storeInfo.setAddressAuditOpinion(auditOpinion);
			storeInfo.setStoreId(storeId);
			int status = jsonRequest.getIntValue("status");
			storeInfo.setStatus(1);
			//地址审核通过
			if(status == 1) {
				storeInfo.setAddressAuditStatus(1);
			} else {
				storeInfo.setAddressAuditStatus(-1);
			}
			storeInfo.setAddressApprovalTime(new Timestamp(new Date().getTime()));
			storeInfo.setAddressApprover(jsonRequest.getInteger("userId"));
			//storeInfo.setAddressApprover(((UserInfoEntity)request.getSession().getAttribute("user")).getUserId());
			this.storeInfoService.updateByPrimaryKeySelective(storeInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);

	}
	
	/**
	 * 图片审核
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/imgAudit ", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> imgAudit(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
			//店铺ID
			Long storeId = jsonRequest.getLong("storeId");
			String auditOpinion = jsonRequest.getString("auditOpinion");
			
			StoreInfo storeInfo = new StoreInfo();
			storeInfo.setImgsAuditOpinion(auditOpinion);
			storeInfo.setStoreId(storeId);
			//1：通过，0：不通过
			int status = jsonRequest.getIntValue("status");
			storeInfo.setStatus(3);
			//图片审核通过
			if(status == 1) {
				storeInfo.setImgsAuditStatus(1);
			} else {
				storeInfo.setImgsAuditStatus(-1);
			}
			storeInfo.setImgsApprovalTime(new Timestamp(new Date().getTime()));
			//storeInfo.setImgsApprover(((UserInfoEntity)request.getSession().getAttribute("user")).getUserId());
			storeInfo.setImgsApprover(jsonRequest.getInteger("userId"));
			this.storeInfoService.updateByPrimaryKeySelective(storeInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);

	}

	
	/**
	 * 我的开户申请
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/applicationAccount/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> applicationList(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
			int type = jsonRequest.getIntValue("type");
			Map<String, Object> param = new HashMap<String, Object>();
			String page = jsonRequest.getString("page");// 当前页
			Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
			param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
			param.put("end", pageCount);
			param.put("status", type);
			//param.put("applicant",  ((UserInfoEntity)request.getSession().getAttribute("user")).getUserId());
			param.put("userId",  jsonRequest.getString("userId"));
			result = this.storeInfoService.selectByApplicant(param);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(result);

	}
	/**
	 * 我的申请
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/applicationShop/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> applicationShop(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
			int type = jsonRequest.getIntValue("type");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("status", type);
			param.put("applicant",  jsonRequest.getString("userId"));
			String page = jsonRequest.getString("page");// 当前页
			Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
			param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
			param.put("end", pageCount);
			//param.put("applicant",  ((UserInfoEntity)request.getSession().getAttribute("user")).getUserId());
			result = this.storeInfoService.selectByApplicant(param);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(result);
	}
	
	/**
	 * 我的审批（运营）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/myApproval/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> myApproval(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
			int type = jsonRequest.getIntValue("type");
			param.put("type", type);
			String page = jsonRequest.getString("page");// 当前页
			Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
			param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
			param.put("end", pageCount);
			param.put("userId",  jsonRequest.getString("userId"));
			result = this.storeInfoService.getMyApproval(param);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(result);

	}
}
