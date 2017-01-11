package com.card.api.strategy.service;

import java.util.List;

import javax.transaction.Transactional;

import com.card.api.strategy.bean.QStrategyBean;
import com.card.core.utils.ParseUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.card.api.strategy.bean.StrategyBean;
import com.card.api.strategy.dao.StrategyDAO;
import com.card.core.criteria.Criteria;
import com.card.core.criteria.Restrictions;
import com.card.core.service.BaseService;
import com.card.core.utils.ValidatorUtils;

/**
 * 动态业务接口<br>
 * 
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：StrategyService <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月6日 上午11:33:16 <br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月6日 上午11:33:16 <br>
 * 修改备注：
 */
@Service
@Transactional
public class StrategyService extends BaseService<StrategyBean> {

	@Autowired
	private StrategyDAO strategyDAO;
	
	/**  
	* 序列号
	* {@value}
	*/ 
	private static final long serialVersionUID = 4626007311775124611L;
	
	/**
	 * 查询动态列表<br>
	 * 2017年1月6日 上午11:42:30<br>
	 * list<br>
	 *
	 * @return<br>
	 * @return List<StrategyBean>
	 */
	public List<StrategyBean> list(Object...params)
	{
		//主键
		Object id = params[0];
		//分页属性
		Object size = params[1];
		//分页属性
		Object page = params[2];
		//热门
		Object hot = params[3];

		QStrategyBean _query = QStrategyBean.strategyBean;
		//查询条件
		BooleanExpression expression = null;
		//追加主键查询
		if(!ValidatorUtils.isEmpty(id) && !ValidatorUtils.isZero(ParseUtils.toInteger(id))) {
			expression = _query.id.eq(Long.valueOf(ParseUtils.toString(id)));
		}

		//设置排序对象参数
		Sort sort = ValidatorUtils.isEmpty(hot) ? new Sort(Direction.DESC, "id")
				: new Sort(Direction.DESC, "clickCount");
		//执行查询
		return strategyDAO.findAll(
				expression,
				new PageRequest(ParseUtils.toInteger(page) - 1, ParseUtils.toInteger(size),sort)
		).getContent();
	}
	
	@Override
	public StrategyBean findById(Long pk) {
		return strategyDAO.findOne(pk);
	}

}
