package com.card.api.systemMsg.service;

import com.card.api.systemMsg.bean.QSystemMsgBean;
import com.card.api.systemMsg.bean.QSystemMsgUserUniBean;
import com.card.api.systemMsg.bean.SystemMsgBean;
import com.card.api.systemMsg.dao.SystemMsgDAO;
import com.card.api.user.bean.QUserBean;
import com.card.api.user.bean.UserBean;
import com.card.api.user.dao.UserDAO;
import com.card.core.constants.SysMsgConstants;
import com.card.core.exception.LogicException;
import com.card.core.service.BaseService;
import com.card.core.utils.ValidatorUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.systemMsg.service
 * 创建人：yuqy
 * 创建时间：2017/1/10 11:31
 * 修改人：yuqy
 * 修改时间：2017/1/10 11:31
 * 修改备注：
 */
@Service
public class SystemMsgService extends BaseService<SystemMsgBean>{

    @Autowired
    private SystemMsgDAO systemMsgDAO;

    @Autowired
    private UserDAO userDAO;

    /**
     * 查询用户下的所有消息包含用户消息，系统消息
     * @return
     */
    public List<SystemMsgBean> findByUser(String phone,Long id)
    {
        //验证参数有效性
        if(ValidatorUtils.isEmpty(phone) || !ValidatorUtils.isMobile(phone)) {
            throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "手机号码");
        }
        //查询用户是否存在
        UserBean userBean = userDAO.findByPhone(phone);
        if(ValidatorUtils.isEmpty(userBean))
        {
            throw new LogicException(SysMsgConstants.XX_ERROR,"未检索到该用户","获取消息列表");
        }
        //构建关联查询
        JPAQuery<SystemMsgBean> jpaQuery = new JPAQuery<SystemMsgBean>(entityManager)
                .select(QSystemMsgBean.systemMsgBean)
                .from(QSystemMsgBean.systemMsgBean)
                .leftJoin(QSystemMsgUserUniBean.systemMsgUserUniBean)
                .on(QSystemMsgBean.systemMsgBean.id.eq(QSystemMsgUserUniBean.systemMsgUserUniBean.messId));
        //默认根据用户编号查询
        BooleanExpression[] expressions = new BooleanExpression[2];

        //添加根据用户编号查询
        expressions[0] = QSystemMsgUserUniBean.systemMsgUserUniBean.userId.eq(userBean.getId());
        //添加根据消息编号查询
        if(!ValidatorUtils.isEmpty(id))
        {
            expressions[1] = QSystemMsgUserUniBean.systemMsgUserUniBean.messId.eq(id);
        }
        //追加where条件使用默认and链接
        jpaQuery.where(expressions);
        //获取列表
        return jpaQuery.fetch();
    }

    @Override
    public SystemMsgBean findById(Long pk) {
        return systemMsgDAO.findOne(pk);
    }
}
