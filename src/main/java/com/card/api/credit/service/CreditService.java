package com.card.api.credit.service;

import com.card.api.credit.bean.CreditBean;
import com.card.api.credit.dao.CreditDAO;
import com.card.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.credit.service
 * 创建人：yuqy
 * 创建时间：2017/1/11 20:08
 * 修改人：yuqy
 * 修改时间：2017/1/11 20:08
 * 修改备注：
 */
@Service
public class CreditService
        extends BaseService<CreditBean>
{
    @Autowired
    private CreditDAO creditDAO;



    @Override
    public CreditBean findById(Long pk) {
        return creditDAO.findOne(pk);
    }

}
