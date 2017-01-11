package com.card.core.bean;

import java.util.List;

/**
 * 分页实体bean，该bean继承了BaseBean <br>
 * 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：PagerBean <br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月26日 下午5:03:23 <br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月26日 下午5:03:23 <br>
 * 修改备注：<br>
 */
public class PagerBean extends BaseBean {

	/**
	 * 序列号 {@value}
	 */
	private static final long serialVersionUID = -5459045627430113147L;

	// 记录总条数
	protected long records;
	// 当前页码
	protected int page;
	// 总页数
	protected int total;
	// 当前页的数据列表
	protected List<BaseBean> rows;

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<BaseBean> getRows() {
		return rows;
	}

	public void setRows(List<BaseBean> rows) {
		this.rows = rows;
	}

}
