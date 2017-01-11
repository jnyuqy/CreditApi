package com.card.api.resource.bean;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.resource.bean
 * 创建人：yuqy
 * 创建时间：2017/1/9 20:27
 * 修改人：yuqy
 * 修改时间：2017/1/9 20:27
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.COMMON_COMMON_BANNER_INFO)
public class BannerBean extends BaseBean
{
    //主键
    @Id
    @GeneratedValue
    @Column(name = "cbi_id")
    private Long id;

    //图片路径
    @Column(name = "cbi_img_path")
    private String path;

    //类型1app,2官网
    @Column(name = "cbi_type")
    private int type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
