package com.card.api.use.service;

import com.card.api.use.bean.UseBean;
import com.card.api.use.dao.UseDAO;
import com.card.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.use.service
 * 创建人：yuqy
 * 创建时间：2017/1/13 14:34
 * 修改人：yuqy
 * 修改时间：2017/1/13 14:34
 * 修改备注：
 */
@Service
public class UseService extends BaseService<UseBean>
{

    @Autowired
    private UseDAO useDAO;

    /**
     * 查询用途列表
     * @return
     */
    @Cacheable(value = "demo")
    public List<UseBean> list()
    {
        return useDAO.findAll();
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
    public UseBean findById(Long pk) {
        return useDAO.findOne(pk);
    }
}
