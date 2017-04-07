package dinghan.workflow.beans;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weaver.conn.RecordSet;

public class ChuChai2 {
	private static Log log = LogFactory.getLog(ChuChai2.class.getName());

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		ChuChai2.log = log;
	}

	private int id; // id
	private int mainid; // 主表id
	private int userid; // 用户id
	private int kqy; // 考勤员
	private int hdzt; // 核定状态
	private String ccrq; // 出差日期
	private String yjkssj; // 预计开始时间
	private String yjjssj; // 预计结束时间
	private String hdkssj; // 核定开始时间
	private String hdjssj; // 核定结束时间
	private String appnom; // 流水号
	private String xm; // 姓名
	private String gh; // 工号
	private String sqrq; // 申请日期
	private String lcjssj; // 流程结束时间
	private int row_id;// 行id

	/**
	 * 功能：根据sql插入明细表2信息
	 */
	public void insert() throws Exception {
		try {
			String sql = "INSERT INTO formtable_main_92_dt2 (mainid,userid,kqy,hdzt,ccrq,yjkssj,yjjssj,hdkssj,hdjssj,appnom,xm,gh,sqrq,lcjssj,row_id)  VALUES    (";
			sql += "'" + this.mainid + "',";
			sql += "'" + this.userid + "',";
			sql += "'" + this.kqy + "',";
			sql += "'" + this.hdzt + "',";
			sql += "'" + this.ccrq + "',";
			sql += "'" + this.yjkssj + "',";
			sql += "'" + this.yjjssj + "',";
			sql += "'" + this.hdkssj + "',";
			sql += "'" + this.hdjssj + "',";
			sql += "'" + this.appnom + "',";
			sql += "'" + this.xm + "',";
			sql += "'" + this.gh + "',";
			sql += "'" + this.sqrq + "',";
			sql += "'" + this.lcjssj + "',";
			sql += "'" + this.row_id + "'";
			sql += ")";
			RecordSet recordSet = new RecordSet();
			recordSet.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("插入出差明细表2信息：" + e);
			throw e;
		}
	}

	/**
	 * 功能：根据用户id 开始日期 结束日期得到明细表二数组
	 */
	public static ArrayList<ChuChai2> queryList(String userid, String ksrq,
			String jsrq) throws Exception {
		ArrayList<ChuChai2> ccList = new ArrayList<ChuChai2>();
		try {
			String sql = "select * from formtable_main_92_dt2  where userid='";
			sql += userid + "' and ccrq BETWEEN '" + ksrq + "' and '" + jsrq
					+ "'";
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
			while (rs.next()) {
				ChuChai2 occ = new ChuChai2();
				occ.setAppnom(rs.getString("appnom"));
				occ.setCcrq(rs.getString("ccrq"));
				occ.setGh(rs.getString("gh"));
				occ.setHdjssj(rs.getString("hdjssj"));
				occ.setHdkssj(rs.getString("hdkssj"));
				occ.setHdzt(rs.getInt("hdzt"));
				occ.setId(rs.getInt("id"));
				occ.setKqy(rs.getInt("kqy"));
				occ.setLcjssj(rs.getString("lcjssj"));
				occ.setMainid(rs.getInt("mainid"));
				occ.setSqrq(rs.getString("sqrq"));
				occ.setUserid(rs.getInt("userid"));
				occ.setXm(rs.getString("xm"));
				occ.setYjjssj(rs.getString("yjjssj"));
				occ.setYjkssj(rs.getString("yjkssj"));
				ccList.add(occ);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("得到出差明细表二数据失败：" + e);
			throw e;
		}
		return ccList;
	}

	/**
	 * 功能：根据主表id删除出差明细表2信息
	 * 
	 */
	public static void delete(int mainid, int row_id) throws Exception {
		String sql = "";
		try {
			if (row_id <= 0) {// 如果type=0时，将主表下的明细全部删除
				sql = "delete from formtable_main_92_dt2 where mainid="
						+ mainid;
			} else {// 如果type不等于0 删除id=mainid的明细
				sql = "delete from formtable_main_92_dt2 where mainid="
						+ mainid + " and row_id=" + row_id;
			}
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除请假明细表三信息：" + e);
			throw e;
		}
	}

	/**
	 * 自动核定明细行
	 * 
	 * :0表示全部核定，否则表示核定具体单据
	 * 
	 * @throws Exception
	 */
	public static void checkList(int mainid) throws Exception {
		log.error("出差明细核定：");
		try {
			String nowDate = PublicVariant.DateToStr(new Date(), "YYYY-MM-dd");
			String sql = "";
			if (mainid == 0) {
				sql = "SELECT * FROM formtable_main_92_dt2 WHERE ccrq<'"
						+ nowDate + "'";
				sql += " AND hdzt=0 ORDER BY mainid,row_id";
			} else {
				sql = "SELECT * FROM formtable_main_92_dt2 WHERE mainid="
						+ mainid + " ORDER BY row_id";
			}
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
			log.error("sql=" + sql);
			while (rs.next()) {
				ChuChai2 oCC = new ChuChai2();
				oCC.setMainid(rs.getInt("mainid"));
				oCC.setRow_id(rs.getInt("row_id"));
				oCC.setUserid(rs.getInt("userid"));
				oCC.setCcrq(rs.getString("ccrq"));
				oCC.setYjkssj(rs.getString("yjkssj"));
				oCC.setYjjssj(rs.getString("yjjssj"));
				oCC.setAppnom(rs.getString(""));
				oCC.setGh(rs.getString(""));
				oCC.setKqy(rs.getInt(""));
				ChuChai cc_main = new ChuChai(oCC.getMainid());
				if (cc_main.getLsh2().equals("")
						&& cc_main.getAppnom().equals("")) {
				} else {
					UserInfo userInfo = new UserInfo(cc_main.getProposer());

					if (oCC.getCcrq().compareTo(nowDate) < 0) {
						oCC.setHdkssj(oCC.getYjkssj());
						oCC.setHdjssj(oCC.getYjjssj());
						oCC.setHdzt(1);
						ChuChai2.delete(oCC.getMainid(), oCC.getRow_id());
						oCC.insert();
					}
					Cctemp cctemp = new Cctemp();
					cctemp.setCcrq(oCC.getCcrq());
					cctemp.setDataid(String.valueOf(cc_main.getRequestId()));
					cctemp.setFlowno(cc_main.getAppnom());
					cctemp.setHdjssj(oCC.getHdjssj());
					cctemp.setHdkssj(oCC.getHdkssj());
					cctemp.setHrmid(cc_main.getProposer());
					cctemp.setHrmno(userInfo.getCode());
					// cctemp.setKqr(0);
					cctemp.setMainid(String.valueOf(oCC.getMainid()));
					cctemp.setXq("");
					cctemp.toString();

					Cctemp.delete(oCC.getMainid(), oCC.getCcrq());// 删除出差中间表的信息

					cctemp.insert();// 插入出差中间表的信息
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("核定出差明细：" + e);
			throw e;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMainid() {
		return mainid;
	}

	public void setMainid(int mainid) {
		this.mainid = mainid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getKqy() {
		return kqy;
	}

	public void setKqy(int kqy) {
		this.kqy = kqy;
	}

	public int getHdzt() {
		return hdzt;
	}

	public void setHdzt(int hdzt) {
		this.hdzt = hdzt;
	}

	public String getCcrq() {
		return ccrq;
	}

	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}

	public String getYjkssj() {
		return yjkssj;
	}

	public void setYjkssj(String yjkssj) {
		this.yjkssj = yjkssj;
	}

	public String getYjjssj() {
		return yjjssj;
	}

	public void setYjjssj(String yjjssj) {
		this.yjjssj = yjjssj;
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

	public String getAppnom() {
		return appnom;
	}

	public void setAppnom(String appnom) {
		this.appnom = appnom;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	public String getSqrq() {
		return sqrq;
	}

	public void setSqrq(String sqrq) {
		this.sqrq = sqrq;
	}

	public String getLcjssj() {
		return lcjssj;
	}

	public void setLcjssj(String lcjssj) {
		this.lcjssj = lcjssj;
	}

	public int getRow_id() {
		return row_id;
	}

	public void setRow_id(int row_id) {
		this.row_id = row_id;
	}
}
