package com.card.core.criteria;

import java.util.Collection;

import org.hibernate.criterion.MatchMode;
import org.springframework.util.StringUtils;

import com.card.core.criteria.Criterion.Operator;

/**
 * 添加查询表达式<br>
 * 
 * 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：Restrictions <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月5日 下午6:26:54 <br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月5日 下午6:26:54 <br>
 * 修改备注：
 */
public class Restrictions {
	/**
	 * 等于
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreNull
	 * @return
	 */
	public static SimpleExpression eq(String fieldName, Object value) {
		if (StringUtils.isEmpty(value))
			return null;
		return new SimpleExpression(fieldName, value, Operator.EQ);
	}

	/**
	 * 不等于
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreNull
	 * @return
	 */
	public static SimpleExpression ne(String fieldName, Object value) {
		if (StringUtils.isEmpty(value))
			return null;
		return new SimpleExpression(fieldName, value, Operator.NE);
	}

	/**
	 * 模糊匹配
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreNull
	 * @return
	 */
	public static SimpleExpression like(String fieldName, String value) {
		if (StringUtils.isEmpty(value))
			return null;
		return new SimpleExpression(fieldName, value, Operator.LIKE);
	}

	/**
	 * 
	 * @param fieldName
	 * @param value
	 * @param matchMode
	 * @param ignoreNull
	 * @return
	 */
	public static SimpleExpression like(String fieldName, String value, MatchMode matchMode) {
		if (StringUtils.isEmpty(value))
			return null;
		return new SimpleExpression(fieldName, value, Operator.LIKE);
	}

	/**
	 * 大于
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreNull
	 * @return
	 */
	public static SimpleExpression gt(String fieldName, Object value) {
		if (StringUtils.isEmpty(value))
			return null;
		return new SimpleExpression(fieldName, value, Operator.GT);
	}

	/**
	 * 小于
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreNull
	 * @return
	 */
	public static SimpleExpression lt(String fieldName, Object value) {
		if (StringUtils.isEmpty(value))
			return null;
		return new SimpleExpression(fieldName, value, Operator.LT);
	}

	/**
	 * 大于等于
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreNull
	 * @return
	 */
	public static SimpleExpression lte(String fieldName, Object value) {
		if (StringUtils.isEmpty(value))
			return null;
		return new SimpleExpression(fieldName, value, Operator.LTE);
	}

	/**
	 * 小于等于
	 * 
	 * @param fieldName
	 * @param value
	 * @param ignoreNull
	 * @return
	 */
	public static SimpleExpression gte(String fieldName, Object value) {
		if (StringUtils.isEmpty(value))
			return null;
		return new SimpleExpression(fieldName, value, Operator.GTE);
	}

	/**
	 * 并且
	 * 
	 * @param criterions
	 * @return
	 */
	public static LogicalExpression and(Criterion... criterions) {
		return new LogicalExpression(criterions, Operator.AND);
	}

	/**
	 * 或者
	 * 
	 * @param criterions
	 * @return
	 */
	public static LogicalExpression or(Criterion... criterions) {
		return new LogicalExpression(criterions, Operator.OR);
	}

	/**
	 * 包含于
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static LogicalExpression in(String fieldName, Collection value,boolean ignoreNull) {
		if (ignoreNull && (value == null || value.isEmpty())) {
			return null;
		}
		SimpleExpression[] ses = new SimpleExpression[value.size()];
		int i = 0;
		for (Object obj : value) {
			ses[i] = new SimpleExpression(fieldName, obj, Operator.EQ);
			i++;
		}
		return new LogicalExpression(ses, Operator.OR);
	}
}
