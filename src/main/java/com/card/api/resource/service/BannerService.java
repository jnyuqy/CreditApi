package com.card.api.resource.service;

import com.card.api.resource.bean.BannerBean;
import com.card.api.resource.dao.BannerDAO;
import com.card.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * app首页滚动图片业务
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.resource.service
 * 创建人：yuqy
 * 创建时间：2017/1/9 20:31
 * 修改人：yuqy
 * 修改时间：2017/1/9 20:31
 * 修改备注：
 */
@Service
public class BannerService extends BaseService<BannerBean>
{
    //数据接口
    @Autowired
    private BannerDAO bannerDAO;

    /**
     * 查询全部banner图片信息
     * @return
     */
    public List<BannerBean> list()
    {
        //查询全部banner
        List<BannerBean> banners = bannerDAO.findAll();
        //遍历banner设置全路径
        for (BannerBean banner:
             banners) {
            banner.setPath("http://192.168.1.116:8080/api"+banner.getPath());
        }
        return banners;
    }

    @Override
    public BannerBean findById(Long pk) {
        return bannerDAO.findOne(pk);
    }
}
