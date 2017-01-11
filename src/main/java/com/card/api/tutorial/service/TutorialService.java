package com.card.api.tutorial.service;

import com.card.api.tutorial.bean.QTutorialBean;
import com.card.api.tutorial.bean.TutorialBean;
import com.card.api.tutorial.dao.TutorialDAO;
import com.card.core.criteria.Criteria;
import com.card.core.criteria.Restrictions;
import com.card.core.service.BaseService;
import com.card.core.utils.JSONUtils;
import com.card.core.utils.ParseUtils;
import com.card.core.utils.ValidatorUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教程业务逻辑
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.tutorial.service
 * 创建人：yuqy
 * 创建时间：2017/1/10 14:47
 * 修改人：yuqy
 * 修改时间：2017/1/10 14:47
 * 修改备注：
 */
@Service
public class TutorialService extends BaseService<TutorialBean>
{

    @Autowired
    private TutorialDAO tutorialDAO;


    /**
     * 查询出教程列表
     * 可根据条数
     * @return
     */
    public List<TutorialBean> list(Object...params)
    {
        //主键
        Object id = params[0];
        //类型
        Object type = params[1];
        //分页属性
        Integer page = ParseUtils.toInteger(params[2]);
        //分页属性
        Integer size = ParseUtils.toInteger(params[3]);

        //构建查询实体
        QTutorialBean _query = QTutorialBean.tutorialBean;
        BooleanExpression expression = null;
        //如果主键编号不为空，根据主键查询
        if(!ValidatorUtils.isEmpty(id))
        {
            expression = _query.id.eq(Long.valueOf(ParseUtils.toString(id)));
        }
        //如果类型不为空，根据类型查询
        if(!ValidatorUtils.isZero(ParseUtils.toInteger(type)))
        {
            if(expression == null) expression = _query.type.eq(ParseUtils.toInteger(type));
            else if (expression != null) expression = expression.and(_query.type.eq(ParseUtils.toInteger(type)));
        }
        //根据点击量排序
        Sort sort = new Sort(Sort.Direction.DESC, "clickCount");
        //分页对象
        Pageable pageable = new PageRequest(page - 1, size,sort);
        //返回对象
        return tutorialDAO.findAll(expression,pageable).getContent();
    }


    /**
     * 根据主键获取教程详情
     * @param pk
     * @return
     */
    @Override
    public TutorialBean findById( Long pk ) {
        return tutorialDAO.findOne(pk);
    }
}
