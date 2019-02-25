package com.efe.ms.serviceconsumer.feignclients;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.efe.ms.serviceconsumer.domain.Product;
import com.efe.ms.serviceconsumer.vo.Pagination;

//@FeignClient(value = "product-service",fallback=ProductServiceFeignClientFallback.class)
@FeignClient(value = "product-service")
public interface ProductServiceFeignClient {

	/**
	 * 
	 * <p>
	 * 注: 如果不加@RequestParam(""),feign会报Method has too many Body parameters的错误
	 * </p>
	 * 
	 * @param
	 * @author Liu TianLong
	 * @date 2019年2月25日 下午3:48:32
	 * @return Pagination<Product>
	 */
	/*@GetMapping("/products")
	Pagination<Product> getProductsByPage(
			@RequestParam("pageNo") Integer pageNo,
			@RequestParam("pageSize") Integer pageSize,@RequestParam("product") Product product);*/
	
	/**
	 * feign 的get方式不支持复杂的数据类型，如果服务端的get接口为复杂类型（如：一个实体类），可以在feign client请求时将参数设置为Map形式
	 * <p>Description: </p>
	 * @param
	 * @author Liu TianLong
	 * @date 2019年2月25日 下午5:33:06
	 * @return Pagination<Product>
	 */
	@GetMapping("/products")
	Pagination<Product> getProductsByPage(@RequestParam Map<String,Object> map);
}
