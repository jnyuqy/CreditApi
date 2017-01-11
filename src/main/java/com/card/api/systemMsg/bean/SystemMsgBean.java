package com.card.api.systemMsg.bean;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 系统消息实体
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.systemMsg.bean
 * 创建人：yuqy
 * 创建时间：2017/1/10 11:23
 * 修改人：yuqy
 * 修改时间：2017/1/10 11:23
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.COMMON_COMMON_MESSAGE_INFO)
public class SystemMsgBean extends BaseBean
{
    //主键编号
    @Id
    @GeneratedValue
    @Column(name = "cmi_id")
    private Long id;

    //消息内容
    @Column(name = "cmi_content")
    private String content;

    //消息上线状态,0：离线，1：在线
    @Column(name = "cmi_status")
    private int status;

    //消息类型：1：系统消息，2：用户推送消息
    @Column(name = "cmi_type")
    private int type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
