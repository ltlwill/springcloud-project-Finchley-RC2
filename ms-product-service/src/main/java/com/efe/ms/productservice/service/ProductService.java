package com.efe.ms.productservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.efe.ms.productservice.domain.Product;


/**
 * 
 * <p>产品(Product)业务接口: </p> 
 * @author Liu TianLong
 * 2018年8月24日 下午5:51:44
 */
public interface ProductService {
	
	/**
	 * 
	 * <p>根据SKU获取产品信息: </p>
	 * @param
	 * @author Liu TianLong
	 * @date 2018年10月9日 上午10:30:18
	 * @return Product
	 */
	Product getProductBySku(String sku);
	
	Page<Product> getAllProducts(Integer pageNo,Integer pageSize,Product product);
	
}
