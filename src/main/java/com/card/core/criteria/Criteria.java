package com.card.core.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.card.core.utils.ValidatorUtils;
/**
 * 封装Specification接口实现类作为spring data jpa 复杂条件查询<br>
 * 
*    
* 项目名称：CreditCardCore
* 项目版本：V1.0   
* 类名称：Criteria   
* 创建人：yuqy   
* 创建时间：2017年1月5日 下午6:21:22   
* 修改人：yuqy   
* 修改时间：2017年1月5日 下午6:21:22   
* 修改备注：
 */
public class Criteria<T> implements Specification<T> {
	//查询条件列表
	private List<Criterion> criterions = new ArrayList<Criterion>();
	//实现接口方法添加查询条件
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		//如果查询条件列表不为空，追加查询条件
		if (!criterions.isEmpty()) {
			List<Predicate> _and_predicates = new ArrayList<Predicate>();
			List<Predicate> _or_predicates = new ArrayList<Predicate>();
			for (Criterion c : criterions) {
				if(c instanceof SimpleExpression)
					_and_predicates.add(c.toPredicate(root, query, builder));
				else if(c instanceof LogicalExpression)
					_or_predicates.add(c.toPredicate(root, query, builder));
			}
			Predicate predicate = null; 
			// 将所有条件用 and 联合起来
			if (_and_predicates.size() > 0) {
				predicate = builder.and(_and_predicates.toArray(new Predicate[_and_predicates.size()]));
			}
			// 添加or关联查询
			if (_or_predicates.size() > 0) {
				for (Predicate or_p : _or_predicates) {
					
					//如果predicate为null,生成一次对象
					if (ValidatorUtils.isEmpty(predicate)) {
						predicate = builder.or(or_p);
					} else {
						predicate = builder.or(predicate, or_p);
					}
				}
			}
			return predicate;
		}
		return builder.conjunction();
	}

	/**
	 * 添加查询条件<br>
	 * 2017年1月5日 下午6:23:56 add
	 * 
	 * @param criterion 
	 * @return void
	 */
	public void add(Criterion criterion) {
		if (criterion != null) {
			criterions.add(criterion);
		}
	}
}
