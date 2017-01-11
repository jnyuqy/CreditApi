package com.card.api.systemMsg.bean;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.systemMsg.bean
 * 创建人：yuqy
 * 创建时间：2017/1/10 12:07
 * 修改人：yuqy
 * 修改时间：2017/1/10 12:07
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.COMMON_COMMON_USER_MESSAGE_UNI)
public class SystemMsgUserUniBean extends BaseBean
{

    @Id
    @GeneratedValue
    @Column(name = "cumu_id")
    private Long id;

    //消息所属用户
    @Column(name = "cumu_user_id")
    private Long userId;

    @Column(name = "cumu_mess_id")
    private Long messId;

    @Column(name = "cumu_status")
    private int status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMessId() {
        return messId;
    }

    public void setMessId(Long messId) {
        this.messId = messId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }
}
