package com.card.core.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 
 * 简单查询条件的判断字符串<br>
 * 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：Criterion <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月5日 下午6:22:04<br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月5日 下午6:22:04<br>
 * 修改备注：
 */
public interface Criterion {
	//查询条件关键字枚举
	public enum Operator {
		EQ, NE, LIKE, GT, LT, GTE, LTE, AND, OR
	}
	//添加查询条件Predicate对象
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder);
}
