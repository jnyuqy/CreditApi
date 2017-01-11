package com.card.core.bean;

import java.io.Serializable;

/**
 * 该类为所有实体的父类，包含了排序字段 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：BaseBean <br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月16日 上午11:49:34 <br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月16日 上午11:49:34 <br>
 * 修改备注：<br>
 */
public class BaseBean implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -8098630494275570392L;

	/**
	 * <p>
	 * 分页页码,默认页码为1
	 * <p>
	 */
	protected int page = 1;

	/**
	 * <p>
	 * 分页每页数量,默认20条
	 * <p>
	 */
	protected int size = 20;

	/**
	 * <p>
	 * 排序列名称,默认为id
	 * <p>
	 */
	protected String sidx = "id";

	/**
	 * <p>
	 * 排序正序
	 * <p>
	 */
	protected String sord = "asc";

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

}
