package dinghan.workflow.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weaver.conn.RecordSet;

public class Jbtemp {
	private static Log log = LogFactory.getLog(Jbtemp.class.getName());

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		Jbtemp.log = log;
	}

	private int id;// id
	private int requestId;// 请求id
	private int hrmid;// 姓名
	private int sfztx;// 是否转调休
	private int kqr;// 考勤员
	private String flowno;// 流水号
	private String hrmno;// 工号
	private String jbdd;// 加班地点
	private String jbrq;// 加班日期
	private String xq;// 星期
	private String dataid;// 数据id
	private String mainid;// 主流程id
	private double yxgs;// 有效工时
	private double jbxs;// 加班系数
	private double hdgs;// 核定工时
	private double xxsj;// 休息时间

	/**
	 * 功能：插入请假中间表信息
	 * 
	 */
	public void insert() throws Exception {
		try {
			String sql = "INSERT INTO uf__jb_temp (hrmid,sfztx,kqr,flowno,hrmno,jbdd,jbrq,xq,dataid,mainid,yxgs,jbxs,hdgs,xxsj)";
			sql += " VALUES  (";
			sql += "'" + this.hrmid + "',";
			sql += "'" + this.sfztx + "',";
			sql += "'" + this.kqr + "',";
			sql += "'" + this.flowno + "',";
			sql += "'" + this.hrmno + "',";
			sql += "'" + this.jbdd + "',";
			sql += "'" + this.jbrq + "',";
			sql += "'" + this.xq + "',";
			sql += "'" + this.dataid + "',";
			sql += "'" + this.mainid + "',";
			sql += "'" + this.yxgs + "',";
			sql += "'" + this.jbxs + "',";
			sql += "'" + this.hdgs + "',";
			sql += "'" + this.xxsj + "'";
			sql += ")";
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("插入j加班中间表：" + e);
			throw e;
		}
	}

	/**
	 * 功能：根据主表id删除中间表信息
	 * 
	 */
	public static void delete(int mainid, String jbrq) throws Exception {
		try {
			String sql = "delete from uf__jb_temp where jbrq='" + jbrq
					+ "' and mainid=" + mainid;
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除加班中间表信息：" + e);
			throw e;
		}
	}

	/**
	 * 功能：插入中间表信息
	 * 
	 * @return
	 */
	public static void insertJb() throws Exception {
		
		try {
			String sql = "select f.requestId,f.sqdh,f.proposer,f.jbgzdd, k.id,k.mainid,k.jbrq,k.sfztx,k.hdkssj,k.hdjssj,k.xxsj,k.yxgs,k.jbxs,k.hdgs,k.yjkssj,k.yjjssj,k.xq,k.hdzt,k.dkjl from workflow_requestbase w, formtable_main_94 f, formtable_main_94_dt1 k where w.requestid=f.requestId and w.currentnodetype=3 and k.mainid=f.id and k.hdzt=1";
			RecordSet rs = new RecordSet();
			RecordSet rs1 = new RecordSet();
			rs.executeSql(sql);

			while (rs.next()) {
				UserInfo userInfo = new UserInfo(rs.getInt("proposer"));

				Jbtemp jbtemp = new Jbtemp();
				jbtemp.setDataid(String.valueOf(rs.getInt("requestId")));
				jbtemp.setFlowno(rs.getString("sqdh"));
				jbtemp.setHdgs(rs.getDouble("hdgs"));
				jbtemp.setHrmid(rs.getInt("proposer"));
				jbtemp.setHrmno(userInfo.getCode());
				log.error("加班地点：" + rs.getInt("jbgzdd"));
				int jbdd = rs.getInt("jbgzdd");
				if (jbdd == 0) {
					jbtemp.setJbdd("北京总部");
				} else if (jbdd == 1) {
					jbtemp.setJbdd("深圳分部");
				} else if (jbdd == 2) {
					jbtemp.setJbdd("东莞生产基地");
				} else if (jbdd == 3) {
					jbtemp.setJbdd("检测公司");
				} else {
					jbtemp.setJbdd("其他");
				}

				int requestid = rs.getInt("requestId");
				String sqlString = "select lastoperatedate from workflow_requestbase where requestid="
						+ requestid;
				rs1.executeSql(sqlString);
				if (rs1.next()) {
					jbtemp.setJbrq(rs1.getString("lastoperatedate"));
				}
				// jbtemp.setJbrq(rs.getString("jbrq"));
				jbtemp.setJbxs(rs.getDouble("jbxs"));
				jbtemp.setMainid(String.valueOf(rs.getInt("mainid")));
				jbtemp.setSfztx(rs.getInt("sfztx"));
				jbtemp.setXq(rs.getString("xq"));
				jbtemp.setXxsj(rs.getDouble("xxsj"));
				jbtemp.setYxgs(rs.getDouble("yxgs"));

				Jbtemp.delete(rs.getInt("mainid"),
						rs1.getString("lastoperatedate"));// 删除原来中间表信息

				jbtemp.insert();// 插入中间表信息

				JiaBan1.update(rs.getInt("mainid"), 3);
			}

		} catch (Exception e) {
			// TODO: handle exception
			log.error("插入加班中间表信息：" + e);
			throw e;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getHrmid() {
		return hrmid;
	}

	public void setHrmid(int hrmid) {
		this.hrmid = hrmid;
	}

	public int getSfztx() {
		return sfztx;
	}

	public void setSfztx(int sfztx) {
		this.sfztx = sfztx;
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

	public String getJbdd() {
		return jbdd;
	}

	public void setJbdd(String jbdd) {
		this.jbdd = jbdd;
	}

	public String getJbrq() {
		return jbrq;
	}

	public void setJbrq(String jbrq) {
		this.jbrq = jbrq;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
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

	public double getYxgs() {
		return yxgs;
	}

	public void setYxgs(double yxgs) {
		this.yxgs = yxgs;
	}

	public double getJbxs() {
		return jbxs;
	}

	public void setJbxs(double jbxs) {
		this.jbxs = jbxs;
	}

	public double getHdgs() {
		return hdgs;
	}

	public void setHdgs(double hdgs) {
		this.hdgs = hdgs;
	}

	public double getXxsj() {
		return xxsj;
	}

	public void setXxsj(double xxsj) {
		this.xxsj = xxsj;
	}

}
