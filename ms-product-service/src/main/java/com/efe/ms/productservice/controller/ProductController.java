package com.efe.ms.productservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efe.ms.productservice.domain.Product;
import com.efe.ms.productservice.service.ProductService;

/**
 * 
 * <p>
 * 产品控制器:
 * </p>
 * 
 * @author Liu TianLong 2018年10月9日 上午10:28:47
 */
@Api(tags = "产品API")
@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	@ApiOperation(value = "分页获取产品信息")
	@GetMapping
	public Page<Product> getProductBySku(Integer pageNo,Integer pageSize,
			Product product) {
		return productService.getAllProducts(pageNo,pageSize, product);
	}

	@ApiOperation(value = "根据SKU获取产品信息")
	@GetMapping("/{sku}")
	public Product getProductBySku(@PathVariable String sku) {
		return productService.getProductBySku(sku);
	}
}
