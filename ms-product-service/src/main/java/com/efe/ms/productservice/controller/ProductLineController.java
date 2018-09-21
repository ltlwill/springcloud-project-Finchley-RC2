package com.efe.ms.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efe.ms.productservice.domain.ProductLine;
import com.efe.ms.productservice.service.ProductLineService;

/**
 * 
 * <p>
 * 产品线控制器:
 * </p>
 * 
 * @author Liu TianLong 2018年8月24日 下午5:21:23
 */
@RefreshScope
@RestController
@RequestMapping("/productline")
public class ProductLineController extends BaseController {

	@Autowired
	private ProductLineService productLineService;
	
	@Value("${customProps.remark}")
	private String configRemark;
	
//	@Value("${commonConfig.testValue}")
	private String commonConfigValue;

	@GetMapping
	public List<ProductLine> getAllProductLine() {
		logger.info("开始查询产品线......");
		return productLineService.findAll();
	}
	
	@GetMapping("/getConfigProp")
	public String getConfigProperties(){
		return configRemark;
	}
	
	@GetMapping("/getCommonConfig")
	public String getCommonConfig(){
		return commonConfigValue;
	}

}
