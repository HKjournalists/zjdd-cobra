package com.zaijiadd.app.order.dao;

import java.util.Map;

import com.zaijiadd.app.order.entity.OrderInfoEntity;

public interface OrderInfoDAO {
	
	public Integer insertOrderInfo( OrderInfoEntity entity );
	
	public Integer updateOrderInfo( Map<String, Object> params );

	public OrderInfoEntity getOrderInfo( String orderId );
	
}
