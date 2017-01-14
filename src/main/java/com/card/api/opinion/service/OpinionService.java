package com.card.api.opinion.service;

import com.card.api.opinion.bean.OpinionBean;
import com.card.api.opinion.dao.OpinionDAO;
import com.card.api.user.bean.QUserBean;
import com.card.api.user.bean.UserBean;
import com.card.api.user.dao.UserDAO;
import com.card.core.constants.SysMsgConstants;
import com.card.core.exception.LogicException;
import com.card.core.service.BaseService;
import com.card.core.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 意见反馈业务逻辑
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.opinion.service
 * 创建人：yuqy
 * 创建时间：2017/1/9 19:17
 * 修改人：yuqy
 * 修改时间：2017/1/9 19:17
 * 修改备注：
 */
@Service
public class OpinionService extends BaseService<OpinionBean>
{
    //意见反馈数据接口
    @Autowired
    private OpinionDAO opinionDAO;

    @Autowired
    private UserDAO userDAO;

    /**
     * 添加意见反馈
     * @param phone 手机号码
     * @param opinionBean 反馈实体
     */
    public void add(String phone,OpinionBean opinionBean)
    {
        //手机号码验证
        if(ValidatorUtils.isEmpty(phone) || !ValidatorUtils.isMobile(phone))
        {
            throw new LogicException(SysMsgConstants.PARAM_IS_NULL,"手机号码");
        }
        //意见反馈类型
        if(ValidatorUtils.isEmpty(opinionBean.getType()) || ValidatorUtils.isEmpty(opinionBean.getType().getId()))
        {
            throw new LogicException(SysMsgConstants.PARAM_IS_NULL,"意见反馈类型");
        }
        //意见反馈内容
        if(ValidatorUtils.isEmpty(opinionBean.getContent()))
        {
            throw new LogicException(SysMsgConstants.PARAM_IS_NULL,"意见反馈内容");
        }
        //查询用户是否存在
        QUserBean _user = QUserBean.userBean;
        UserBean user = userDAO.findOne(_user.phone.eq(phone));
        if(ValidatorUtils.isEmpty(user))
        {
            throw new LogicException(SysMsgConstants.XX_ERROR,"未检索到该用户","添加意见反馈");
        }
        //设置所属用户
        opinionBean.setUserId(user.getId());
        //添加意见反馈
        opinionDAO.save(opinionBean);
    }


    @Override
    public OpinionBean findById(Long pk) {
        return opinionDAO.findOne(pk);
    }
}
