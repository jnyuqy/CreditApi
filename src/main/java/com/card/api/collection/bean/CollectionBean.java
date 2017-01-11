package com.card.api.collection.bean;

import com.card.core.bean.BaseBean;

import java.sql.Timestamp;

/**
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.collection.controller.bean
 * 创建人：yuqy
 * 创建时间：2017/1/9 18:28
 * 修改人：yuqy
 * 修改时间：2017/1/9 18:28
 * 修改备注：
 */
public class CollectionBean extends BaseBean
{
    private Long id;
    private Long userId;
    private Timestamp time;
    private Long creditId;
}
