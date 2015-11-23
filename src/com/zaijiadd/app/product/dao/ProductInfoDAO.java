package com.zaijiadd.app.product.dao;

import java.util.List;

import com.zaijiadd.app.product.dto.ProductGoodsDTO;
import com.zaijiadd.app.product.entity.ProductInfoEntity;

public interface ProductInfoDAO {
	
	public ProductInfoEntity getProductInfo( String productId );
	
	public List<ProductInfoEntity> getProductInfoList();
	
	public List<ProductGoodsDTO> getProductGoodsList( String productId );

}
