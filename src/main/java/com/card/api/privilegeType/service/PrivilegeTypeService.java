package com.card.api.privilegeType.service;

import com.card.api.privilegeType.bean.PrivilegeTypeBean;
import com.card.api.privilegeType.dao.PrivilegeTypeDAO;
import com.card.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.privilegeType.service
 * 创建人：yuqy
 * 创建时间：2017/1/13 16:24
 * 修改人：yuqy
 * 修改时间：2017/1/13 16:24
 * 修改备注：
 */
@Service
public class PrivilegeTypeService extends BaseService<PrivilegeTypeBean>
{
    @Autowired
    private PrivilegeTypeDAO privilegeTypeDAO;

    /**
     * 查询特权类型列表
     * @return
     */
    @Cacheable(value = "demo")
    public List<PrivilegeTypeBean> list(){
        return privilegeTypeDAO.findAll();
    }
    /**
     * 子类必须实现该函数，用于操作日志以及单条数据读取
     * 2016年12月29日 下午6:29:03
     * findById
     *
     * @param pk
     * @return T
     */
    @Override
    public PrivilegeTypeBean findById(Long pk) {
        return privilegeTypeDAO.findOne(pk);
    }
}
