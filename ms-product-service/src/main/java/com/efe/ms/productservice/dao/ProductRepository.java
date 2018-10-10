package com.efe.ms.productservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.efe.ms.productservice.domain.Product;

/**
 * 
 * <p>产品dao </p> 
 * @author Liu TianLong
 * 2018年10月8日 上午11:58:38
 */
public interface ProductRepository extends JpaRepository<Product, Long>,JpaSpecificationExecutor<Product> {
	
	
//	@Query(value="select * from products where id = ?1",nativeQuery=true) // 原生SQL语法
	@Query(value="select a from Product a where a.id = ?1") // JPQL的SQL语法
	Product getProductBySku(String sku);

}
