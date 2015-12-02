package com.zaijiadd.app.applyflow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zaijiadd.app.applyflow.entity.StoreInfo;
import com.zaijiadd.app.applyflow.service.StoreInfoService;
import com.zaijiadd.app.common.utils.ContainerUtils;

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
	public Map<String, Object> addInviteUserMsg(StoreInfo info) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			this.storeInfoService.insert(info);
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
			param.put("detail", this.storeInfoService.selectByPrimaryKey(storeId));
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
			String[] fileUrls = request.getParameterValues("fileUrls");
			Long storeId = Long.valueOf(request.getParameter("storeId"));
			this.storeInfoService.applicationShop(fileUrls, storeId);
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
	@RequestMapping(value = "/addressAudit ", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> addressAudit(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			//店铺ID
			Long storeId = Long.parseLong(request.getParameter("storeId"));
			String auditOpinion = request.getParameter("auditOpinion");
			//1：通过，0：不通过
			StoreInfo storeInfo = new StoreInfo();
			storeInfo.setAddressAuditOpinion(auditOpinion);
			storeInfo.setStoreId(storeId);
			int status = Integer.parseInt(request.getParameter("status"));
			storeInfo.setStatus(1);
			//地址审核通过
			if(status == 1) {
				storeInfo.setAddressAuditStatus(1);
			} else {
				storeInfo.setAddressAuditStatus(-1);
			}
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
	@RequestMapping(value = "/imgAudit ", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> imgAudit(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			//店铺ID
			Long storeId = Long.parseLong(request.getParameter("storeId"));
			String auditOpinion = request.getParameter("auditOpinion");
			
			StoreInfo storeInfo = new StoreInfo();
			storeInfo.setImgsAuditOpinion(auditOpinion);
			storeInfo.setStoreId(storeId);
			//1：通过，0：不通过
			int status = Integer.parseInt(request.getParameter("status"));
			storeInfo.setStatus(3);
			//图片审核通过
			if(status == 1) {
				storeInfo.setImgsAuditStatus(1);
			} else {
				storeInfo.setImgsAuditStatus(-1);
			}
			this.storeInfoService.updateByPrimaryKeySelective(storeInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);

	}

}
