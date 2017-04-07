package dinghan.workflow.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weaver.conn.RecordSet;

public class QJtemp {
	private static Log log = LogFactory.getLog(QJtemp.class.getName());

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		QJtemp.log = log;
	}

	private int id;// id
	private int hrmid;// 人员id
	private int requestId;// 请求id
	private int qjlx;// 请假类型
	private int kqr;// 考勤员
	private String flowno;// 流水单号
	private String hrmno;// 工号
	private String qjrq;// 请假日期
	private String xq;// 星期
	private String hdkssj;// 核定开始时间
	private String hdjssj;// 核定结束时间
	private String hdgs;// 核定工时
	private String hdyf;// 核定月份
	private String dataid;// 数据id
	private String mainid;// 主流程id

	/**
	 * 功能：插入请假中间表信息
	 * 
	 */
	public void insert() throws Exception {
		try {
			String sql = "INSERT INTO uf__qj_temp (hrmid,qjlx,kqr,flowno,hrmno,qjrq,xq,hdkssj,hdjssj,hdgs,hdyf,dataid,mainid)";
			sql += " VALUES  (";
			sql += "'" + this.hrmid + "',";
			sql += "'" + this.qjlx + "',";
			sql += "'" + this.kqr + "',";
			sql += "'" + this.flowno + "',";
			sql += "'" + this.hrmno + "',";
			sql += "'" + this.qjrq + "',";
			sql += "'" + this.xq + "',";
			sql += "'" + this.hdkssj + "',";
			sql += "'" + this.hdjssj + "',";
			sql += "'" + this.hdgs + "',";
			sql += "'" + this.hdyf + "',";
			sql += "'" + this.dataid + "',";
			sql += "'" + this.mainid + "'";
			sql += ")";
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("插入请假中间表：" + e);
			throw e;
		}
	}

	/**
	 * 功能：根据主表id删除中间表信息
	 * 
	 */
	public static void delete(int mainid, String qjrq) throws Exception {
		try {
			String sql = "delete from uf__qj_temp where qjrq='" + qjrq
					+ "' and mainid=" + mainid;
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除请假中间表信息：" + e);
			throw e;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHrmid() {
		return hrmid;
	}

	public void setHrmid(int hrmid) {
		this.hrmid = hrmid;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getQjlx() {
		return qjlx;
	}

	public void setQjlx(int qjlx) {
		this.qjlx = qjlx;
	}

	public int getKqr() {
		return kqr;
	}

	public void setKqr(int kqr) {
		this.kqr = kqr;
	}

	public String getFlowno() {
		return flowno;
	}

	public void setFlowno(String flowno) {
		this.flowno = flowno;
	}

	public String getHrmno() {
		return hrmno;
	}

	public void setHrmno(String hrmno) {
		this.hrmno = hrmno;
	}

	public String getQjrq() {
		return qjrq;
	}

	public void setQjrq(String qjrq) {
		this.qjrq = qjrq;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getHdkssj() {
		return hdkssj;
	}

	public void setHdkssj(String hdkssj) {
		this.hdkssj = hdkssj;
	}

	public String getHdjssj() {
		return hdjssj;
	}

	public void setHdjssj(String hdjssj) {
		this.hdjssj = hdjssj;
	}

	public String getHdgs() {
		return hdgs;
	}

	public void setHdgs(String hdgs) {
		this.hdgs = hdgs;
	}

	public String getHdyf() {
		return hdyf;
	}

	public void setHdyf(String hdyf) {
		this.hdyf = hdyf;
	}

	public String getDataid() {
		return dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	public String getMainid() {
		return mainid;
	}

	public void setMainid(String mainid) {
		this.mainid = mainid;
	}

}
