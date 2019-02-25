package com.efe.ms.serviceconsumer.service;

import com.efe.ms.serviceconsumer.domain.Product;
import com.efe.ms.serviceconsumer.vo.Pagination;

/**
 * 
 * <p>服务消费业务接口: </p> 
 * @author Liu TianLong
 * 2019年2月25日 下午3:15:54
 */
public interface ConsumerService {

	/**
	 * 
	 * <p>根据条件分页获取产品信息: </p>
	 * @param
	 * @author Liu TianLong
	 * @date 2019年2月25日 下午3:17:16
	 * @return Pagination<Product>
	 */
	Pagination<Product> getProducts(Pagination<Product> page,Product product) throws Exception;
	
}
