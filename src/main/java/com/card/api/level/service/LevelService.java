package com.card.api.level.service;

import com.card.api.level.bean.LevelBean;
import com.card.api.level.dao.LevelDAO;
import com.card.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.level.service
 * 创建人：yuqy
 * 创建时间：2017/1/13 14:53
 * 修改人：yuqy
 * 修改时间：2017/1/13 14:53
 * 修改备注：
 */
@Service
public class LevelService extends BaseService<LevelBean>
{
    @Autowired
    private LevelDAO levelDAO;

    /**
     * 查询信用卡等级列表
     * @return
     */
    public List<LevelBean> list()
    {
        return levelDAO.findAll();
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
    public LevelBean findById(Long pk) {
        return levelDAO.findOne(pk);
    }
}
