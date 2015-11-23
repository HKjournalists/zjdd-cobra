package com.zaijiadd.app.callback.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.callback.service.AlipayCallBackService;
import com.zaijiadd.app.external.service.JdRequestService;
import com.zaijiadd.app.external.service.ZaijiaddRequestService;
import com.zaijiadd.app.order.dao.OrderInfoDAO;
import com.zaijiadd.app.order.entity.OrderInfoEntity;
import com.zaijiadd.app.user.dao.UserProductDAO;
import com.zaijiadd.app.user.entity.UserProductEntity;
import com.zaijiadd.app.utils.constants.ConstantsForOrder;

public class AlipayCallBackServiceImpl implements AlipayCallBackService {

	@Autowired
	private OrderInfoDAO orderInfoDao;
	
	@Autowired
	private UserProductDAO userProductDao;
	
	@Autowired
	private ZaijiaddRequestService zaijiaddRequestService;
	
	@Autowired
	private JdRequestService jdRequestService;
	
	@Override
	public void processAlipayCallBack( String orderId ) {

		/*
		 * update order_info status
		 */
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "orderId", orderId );
		params.put( "transStatus", ConstantsForOrder.TRANS_STATUS_SUCCESS );
		orderInfoDao.updateOrderInfo( params );
		
		/*
		 * get order_info entity
		 */
		OrderInfoEntity orderInfoEntity = orderInfoDao.getOrderInfo( orderId );
		
		/*
		 * insert user_product
		 */
		params = new HashMap<String, Object>();
		params.put( "userId", orderInfoEntity.getUserId() );
		params.put( "amount", orderInfoEntity.getAmount() );
		params.put( "productId", orderInfoEntity.getProductId() );
		params.put( "transCode", ConstantsForOrder.TRANS_CODE_RECHARGE );
		params.put( "count", 1 );
		
		userProductDao.insertUserProduct( params );
		
		/*
		 * call zaijiadd's php update goods
		 */
		
		
		/*
		 * call jd build order
		 */
		
		
	}

}
