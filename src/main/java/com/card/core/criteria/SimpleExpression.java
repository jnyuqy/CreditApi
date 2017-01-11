package com.card.core.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

/**
 * 简单表达式<br>
 * 
 * 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：SimpleExpression <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月5日 下午6:29:16 <br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月5日 下午6:29:16 <br>
 * 修改备注：<br>
 */
public class SimpleExpression implements Criterion {

	private String fieldName; // 属性名
	private Object value; // 对应值
	private Operator operator; // 关键字
	
	protected SimpleExpression(String fieldName, Object value, Operator operator) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getValue() {
		return value;
	}

	public Operator getOperator() {
		return operator;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		//表达式
		Path expression = null;
		//如果存在xx.xx这种形式，循环层次获取Path
		if (fieldName.contains(".")) {
			String[] names = StringUtils.split(fieldName, ".");
			expression = root.get(names[0]);
			for (int i = 1; i < names.length; i++) {
				expression = expression.get(names[i]);
			}
		}
		//如果不存在层次关系，直接返回Path
		else {
			expression = root.get(fieldName);
		}
		//根据不同的枚举关键字，对应创建Predicate对象
		switch (operator) {
		case EQ:
			return builder.equal(expression, value);
		case NE:
			return builder.notEqual(expression, value);
		case LIKE:
			return builder.like((Expression<String>) expression, "%" + value + "%");
		case LT:
			return builder.lessThan(expression, (Comparable) value);
		case GT:
			return builder.greaterThan(expression, (Comparable) value);
		case LTE:
			return builder.lessThanOrEqualTo(expression, (Comparable) value);
		case GTE:
			return builder.greaterThanOrEqualTo(expression, (Comparable) value);
		default:
			return null;
		}
	}

}
