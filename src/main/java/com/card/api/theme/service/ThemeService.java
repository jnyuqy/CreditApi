package com.card.api.theme.service;

import com.card.api.theme.bean.ThemeBean;
import com.card.api.theme.dao.ThemeDAO;
import com.card.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.theme.service
 * 创建人：yuqy
 * 创建时间：2017/1/13 16:45
 * 修改人：yuqy
 * 修改时间：2017/1/13 16:45
 * 修改备注：
 */
@CacheConfig(cacheNames = "theme")
@Service
public class ThemeService extends BaseService<ThemeBean>
{

    @Autowired
    private ThemeDAO themeDAO;


    /**
     *查询信用卡主题列表
     * @return
     */
    @Cacheable(value = "demo")
    public List<ThemeBean> list()
    {
        return themeDAO.findAll();
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
    public ThemeBean findById(Long pk) {
        return themeDAO.findOne(pk);
    }
}
