package com.card.api.strategy.service;

import java.util.List;

import javax.transaction.Transactional;

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
	 * @param strategy<br>
	 * @return<br>
	 * @return List<StrategyBean>
	 */
	public List<StrategyBean> list(StrategyBean strategy)
	{
		//创建查询对象
		Criteria<StrategyBean> criteria = new Criteria<>();
		//添加根据主键查询
		criteria.add(Restrictions.eq("id", strategy.getId()));
		//设置排序对象参数
		Sort sort = ValidatorUtils.isZero(strategy.getHot()) ? new Sort(Direction.DESC, "id")
				: new Sort(Direction.DESC, "clickCount");
		//执行查询
		return strategyDAO.findAll(criteria, new PageRequest(strategy.getPage() - 1, strategy.getSize(),sort)).getContent();
	}
	
	@Override
	public StrategyBean findById(Long pk) {
		return strategyDAO.findOne(pk);
	}

}
