package com.efe.ms.productservice.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.efe.ms.productservice.dao.ProductRepository;
import com.efe.ms.productservice.domain.Product;

/**
 * 
 * <p>
 * 产品(Product)业务实现类:
 * </p>
 * 
 * @author Liu TianLong 2018年8月24日 下午5:52:37
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product getProductBySku(String sku) {
		if (StringUtils.isBlank(sku)) {
			return null;
		}
		return productRepository.getProductBySku(sku);
	}

	@SuppressWarnings("serial")
	@Override
	public Page<Product> getAllProducts(final Integer pageNo,
			final Integer pageSize, final Product product) {
		return productRepository.findAll(new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.and(cb.conjunction(),
						cb.equal(root.get("status"), "archive")); // 产品状态必须是"archive"
				if (StringUtils.isNotBlank(product.getId())) { // 产品ID
					predicate = cb.and(predicate,
							cb.equal(root.get("id"), product.getId()));
				}
				if (StringUtils.isNotBlank(product.getSku())) { // SKU条件(即：ID)条件
					predicate = cb.and(predicate,
							cb.equal(root.get("id"), product.getSku()));
				}
				if (StringUtils.isNotBlank(product.getEname())) { // 产品名称条件
					predicate = cb.and(
							predicate,
							cb.like(root.get("ename"), "%" + product.getEname()
									+ "%"));
				}
				if (StringUtils.isNotBlank(product.getWareHouse())) {
					predicate = cb.and(
							predicate,
							cb.like(root.get("wareHouse"),
									"%" + product.getWareHouse() + "%"));
				}
				if (product.getProductLine() != null) { // 产品线条件
					predicate = cb.and(
							predicate,
							cb.equal(root.get("productLine"),
									product.getProductLine()));
				}
				if (StringUtils.isNotBlank(product.getProductLinesStr())) { // 产品线集合条件
					predicate = cb.and(
							predicate,
							root.get("productLine").in(
									product.getProductLinesStr()));
				}
				if (StringUtils.isNotBlank(product.getKeyword())) { // 关键字查询
					Predicate orPredicate = cb.and(cb.like(root.get("ename"),
							"%" + product.getKeyword() + "%"));
					orPredicate = cb.or(
							orPredicate,
							cb.like(root.get("wareHouse"),
									"%" + product.getKeyword() + "%"));
					orPredicate = cb.or(
							orPredicate,
							cb.like(root.get("id"), "%" + product.getKeyword()
									+ "%"));
					predicate = cb.and(predicate, orPredicate);
				}
				return predicate;
			}
		}, PageRequest.of(pageNo, pageSize, Sort.by(Direction.DESC, "id"))); // 按ID字段降序排序
	}

}
