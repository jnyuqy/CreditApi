package com.card.api.tutorial.service;

import com.card.api.tutorial.bean.QTutorialBean;
import com.card.api.tutorial.bean.TutorialBean;
import com.card.api.tutorial.dao.TutorialDAO;
import com.card.core.criteria.Criteria;
import com.card.core.criteria.Restrictions;
import com.card.core.service.BaseService;
import com.card.core.utils.JSONUtils;
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
     * @param tutorialBean 教程查询实体
     * @return
     */
    public List<TutorialBean> list(TutorialBean tutorialBean)
    {
        //构建查询实体
        QTutorialBean _query = QTutorialBean.tutorialBean;
        BooleanExpression expression = null;
        //如果主键编号不为空，根据主键查询
        if(!ValidatorUtils.isEmpty(tutorialBean.getId()))
        {
            expression = _query.id.eq(tutorialBean.getId());
        }
        //如果类型不为空，根据类型查询
        if(!ValidatorUtils.isZero(tutorialBean.getType()))
        {
            if(expression == null) expression = _query.type.eq(tutorialBean.getType());
            else if (expression != null) expression.and(_query.type.eq(tutorialBean.getType()));
        }
        //如果标签不为空,根据标签查询
        if(!ValidatorUtils.isEmpty(tutorialBean.getTag()) && tutorialBean.getTag().getId() != 0)
        {
            if(expression == null) expression = _query.tag.id.eq(tutorialBean.getTag().getId());
            else if (expression != null) expression.and(_query.tag.id.eq(tutorialBean.getTag().getId()));
        }
        //根据点击量排序
        Sort sort = new Sort(Sort.Direction.DESC, "clickCount");
        //分页对象
        Pageable pageable = new PageRequest(tutorialBean.getPage() - 1, tutorialBean.getSize(),sort);
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
