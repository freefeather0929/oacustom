package dinghan.workflow.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weaver.conn.RecordSet;
import weaver.dh.interfaces.dingHanTools;
import weaver.general.Util;

public class QingJia {
	private static Log log = LogFactory.getLog(QingJia.class.getName());

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		QingJia.log = log;
	}

	private int userid; // 人员id
	private int mainid; // 主表id
	private String rq; // 日期
	private String qjlb; // 请假类别
	private String kssj; // 开始时间
	private String jssj; // 结束时间
	private int hdzt; // 核定状态
	private String xq; // 星期
	private int id; // id
	private String hdkssj; // 核定开始时间
	private String hdjssj; // 核定结束时间
	private String dkjl; // 打卡记录
	private String appnom; // 流水号
	private String gh; // 工号
	private double hdgs; // 核定工时
	private String jdid; // 节点id
	private String bzgzsj; // 标准工作时间
	private String kqhdyf; // 考勤核定月份
	private String lcspjsrq;// 流程审批结束日期
	private int kqy; // 考勤员
	private int row_id;// 明细行id

	public QingJia() {
	}

	/**
	 * 功能：插入表3信息
	 * 
	 */
	public void insert() throws Exception {
		try {
			String sql = "INSERT INTO formtable_main_89_dt3 (userid,mainid,rq,qjlb,kssj,jssj,hdzt,xq,hdkssj,hdjssj,dkjl,appnom,gh,hdgs,jdid,bzgzsj,kqhdyf,lcspjsrq,kqy,row_id)";
			sql += " VALUES  (";
			sql += "'" + this.userid + "',";
			sql += "'" + this.mainid + "',";
			sql += "'" + this.rq + "',";
			sql += "'" + this.qjlb + "',";
			sql += "'" + this.kssj + "',";
			sql += "'" + this.jssj + "',";
			sql += "'" + this.hdzt + "',";
			sql += "'" + this.xq + "',";
			sql += "'" + this.hdkssj + "',";
			sql += "'" + this.hdjssj + "',";
			sql += "'" + this.dkjl + "',";
			sql += "'" + this.appnom + "',";
			sql += "'" + this.gh + "',";
			sql += "'" + this.hdgs + "',";
			sql += "'" + this.jdid + "',";
			sql += "'" + this.bzgzsj + "',";
			sql += "'" + this.kqhdyf + "',";
			sql += "'" + this.lcspjsrq + "',";
			sql += "'" + this.kqy + "',";
			sql += "'" + this.row_id + "'";
			sql += ")";
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("插入请假明细表三：" + e);
			throw e;
		}
	}

	/**
	 * 功能：根据主表id 明细行id得到表3信息
	 * 
	 */
	public QingJia(int mainid, int row_id) throws Exception {
		String sql = "select * from formtable_main_89_dt3  where mainid="
				+ mainid;
		sql += " and row_id = " + row_id;

		try {
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
			this.appnom = rs.getString("appnom");
			this.bzgzsj = rs.getString("bzgzsj");
			this.dkjl = rs.getString("dkjl");
			this.gh = rs.getString("gh");
			this.hdgs = rs.getDouble("hdgs");
			this.hdjssj = rs.getString("hdjssj");
			this.hdkssj = rs.getString("hdkssj");
			this.hdzt = rs.getInt("hdzt");
			this.id = rs.getInt("id");
			this.jdid = rs.getString("jdid");
			this.jssj = rs.getString("jssj");
			this.kqhdyf = rs.getString("kqhdyf");
			this.kqy = rs.getInt("kqy");
			this.kssj = rs.getString("kssj");
			this.lcspjsrq = rs.getString("lcspjsrq");
			this.mainid = rs.getInt("mainid");
			this.qjlb = rs.getString("qjlb");
			this.row_id = rs.getInt("row_id");
			this.rq = rs.getString("rq");
			this.userid = rs.getInt("userid");
			this.xq = rs.getString("xq");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("得到请假明细表三：" + e);
			throw e;
		}
	}

	/**
	 * 功能：根据mainid得到表三List
	 */
	public static ArrayList<QingJia> queryList(int mainid) throws Exception {
		ArrayList<QingJia> aQJ = new ArrayList<QingJia>();
		String sql = "select * from formtable_main_89_dt3  where mainid="
				+ mainid;
		try {
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
			while (rs.next()) {
				QingJia oQj = new QingJia();
				oQj.setAppnom(rs.getString("appnom"));
				oQj.setBzgzsj(rs.getString("bzgzsj"));
				oQj.setDkjl(rs.getString("dkjl"));
				oQj.setGh(rs.getString("gh"));
				oQj.setHdgs(rs.getDouble("hdgs"));
				oQj.setHdjssj(rs.getString("hdjssj"));
				oQj.setHdkssj(rs.getString("hdkssj"));
				oQj.setHdzt(rs.getInt("hdzt"));
				oQj.setId(rs.getInt("id"));
				oQj.setJdid(rs.getString("jdid"));
				oQj.setJssj(rs.getString("jssj"));
				oQj.setKqhdyf(rs.getString("kqhdyf"));
				oQj.setKqy(rs.getInt("kqy"));
				oQj.setKssj(rs.getString("kssj"));
				oQj.setLcspjsrq(rs.getString("lcspjsrq"));
				oQj.setMainid(rs.getInt("mainid"));
				oQj.setQjlb(rs.getString("qjlb"));
				oQj.setRow_id(rs.getInt("row_id"));
				oQj.setRq(rs.getString("rq"));
				oQj.setUserid(rs.getInt("userid"));
				oQj.setXq(rs.getString("xq"));
				aQJ.add(oQj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("根据mainid得到请假明细三：" + e);
			throw e;
		}
		return aQJ;
	}

	/**
	 * 功能：根据用户id 开始日期、结束日期 获取表3信息 得到表三List
	 */
	public static ArrayList<QingJia> queryList(String userid, String ksrq,
			String jsrq, String requestid) throws Exception {
		ArrayList<QingJia> aQJ = new ArrayList<QingJia>();
		String sql = "select * from formtable_main_89 left JOIN formtable_main_89_dt3 on formtable_main_89.id=formtable_main_89_dt3.mainid  where userid=";
		sql += userid + " and rq BETWEEN '" + ksrq + "' and '" + jsrq
				+ "'  and requestId!='" + requestid + "'";
		try {
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
			while (rs.next()) {
				QingJia oQj = new QingJia();
				oQj.setUserid(rs.getInt("userid"));
				oQj.setRq(rs.getString("rq"));
				oQj.setHdzt(rs.getInt("hdzt"));
				oQj.setJssj(rs.getString("jssj"));
				oQj.setKssj(rs.getString("kssj"));
				oQj.setMainid(rs.getInt("mainid"));
				oQj.setQjlb(rs.getString("qjlb"));
				oQj.setXq(rs.getString("xq"));
				aQJ.add(oQj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return aQJ;
	}

	/**
	 * 功能：根据主表id 明细行id删除表3信息。 如果row_id大于0：删除mainid和row_id相匹配的记录，否则：删除mainid所有明细
	 * 
	 */
	public static void delete(int mainid, int row_id) throws Exception {
		String sql = "";
		try {
			sql = "delete from formtable_main_89_dt3 where mainid=" + mainid;
			if (row_id > 0) {
				sql += " and row_id=" + row_id;
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
	 * 功能：根据主表id 明细行id删除表3信息。 如果row_id大于0：删除mainid和row_id相匹配的记录，否则：删除mainid所有明细
	 * 
	 */
	public static void delete(int id) throws Exception {
		String sql = "";
		try {
			sql = "delete from formtable_main_89_dt3 where id=" + id;

			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除请假明细表三信息：" + e);
			throw e;
		}
	}

	/**
	 * 考勤员复核时 修改核定状态，0：未核定，1：系统核定，2：考勤员复核
	 */
	public static void update(int mainid, int type) throws Exception {
		try {
			String sql = "update formtable_main_89_dt3 set hdzt=" + type;
			sql += " where mainid=" + mainid;
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("更新请假明细表三hdzt出错：" + e);
			throw e;
		}
	}

	public static void check(int mainid) throws Exception {
		String nowDate = PublicVariant.DateToStr(new Date(), "YYYY-MM-dd");
		String sql = "";
		if (mainid == 0) {
			sql = "SELECT * FROM formtable_main_89_dt3 WHERE rq<'" + nowDate;
			sql += "' AND hdzt=0 ORDER BY mainid,row_id";
		} else {
			sql = "SELECT * FROM formtable_main_89_dt3 WHERE mainid=" + mainid
					+ " ORDER BY row_id";
		}
		RecordSet rs = new RecordSet();
		rs.executeSql(sql);
		System.out.println(nowDate);
	}

	/**
	 * 核定明细
	 * 
	 * @throws Exception
	 */
	public static void checkList(int mainid) throws Exception {
		log.error("请假明细核定：");
		String nowDate = PublicVariant.DateToStr(new Date(), "YYYY-MM-dd");
		String sql = "";
		if (mainid == 0) {
			sql = "SELECT * FROM formtable_main_89_dt3 WHERE rq<'" + nowDate;
			sql += "' AND hdzt=0 ORDER BY mainid,row_id";
		} else {
			sql = "SELECT * FROM formtable_main_89_dt3 WHERE mainid=" + mainid
					+ " ORDER BY row_id";
		}
		RecordSet rs = new RecordSet();
		rs.executeSql(sql);
		rs.getDouble("");
		while (rs.next()) {
			// 得到请假明细三对象

			QingJia qj_three = new QingJia();
			qj_three.setId(rs.getInt("id"));
			qj_three.setMainid(rs.getInt("mainid"));
			qj_three.setRq(rs.getString("rq"));
			qj_three.setKssj(rs.getString("kssj"));
			qj_three.setJssj(rs.getString("jssj"));
			qj_three.setQjlb(rs.getString("qjlb"));
			qj_three.setUserid(rs.getInt("userid"));
			qj_three.setHdzt(rs.getInt("hdzt"));
			qj_three.setXq(rs.getString("xq"));
			qj_three.setRow_id(rs.getInt("row_id"));
			QingJia0 qj_main = new QingJia0(qj_three.getMainid());
			if (qj_main.getAppnom() == null || qj_main.getAppnom().equals(" ")) {
			} else {
				// 得到user对象
				UserInfo userInfo = new UserInfo(rs.getInt("userid"));
				qj_three.setGh(userInfo.getCode());
				if (qj_three.getRq().compareTo(nowDate) < 0) {
					boolean flag = false;
					DaKaRecord oDK = new DaKaRecord(userInfo.getCode(),
							rs.getString("rq"));// 打卡记录
					log.error("打卡记录：" + oDK.getDate() + oDK.getDk_list());

					// 考勤记录去除空值
					for (int i = 0; i < oDK.getDk_list().size(); i++) {
						if (oDK.getDk_list().get(i).equals("")
								|| oDK.getDk_list().get(i)
										.equalsIgnoreCase("null")) {
							oDK.getDk_list().remove(i);
							i--;
						}
					}

					// 考勤记录排序
					Collections.sort(oDK.getDk_list());
					if (oDK.getDk_list().size() == 0) {
						oDK.setFirsttime("");
						oDK.setLasttime("");
						qj_three.setDkjl(""); // 打卡记录
					} else {
						oDK.setFirsttime(oDK.getDk_list().get(0));
						oDK.setLasttime(oDK.getDk_list().get(
								oDK.getDk_list().size() - 1));
						qj_three.setDkjl(oDK.getDk_list().toString()); // 打卡记录
					}
					switch (qj_three.getXq()) {
					case "星期六":
						flag = false;
						break;
					case "星期日":
						flag = false;
						break;
					default:
						flag = true;
						break;
					}
					String bz_Stime = userInfo.getStartWorkTime();// 标准上班时间
					String bz_Etime = userInfo.getEndWorkTime();// 标准下班时间

					dingHanTools dht = new dingHanTools();
					HashMap<String, String> map = dht.getJJR(
							String.valueOf(userInfo.getName()),
							qj_three.getRq());
					log.error("name:" + userInfo.getName() + "   rq:"
							+ qj_three.getRq());
					log.error("1212" + map.toString() + "   " + map.size());
					if (map != null && map.size() > 0) {
						String jrlx = Util.null2String(map.get(userInfo
								.getName() + "_jrlx"));
						if ("8".equals(jrlx)) {// 上班调整
							String kssj = Util.null2String(map.get(userInfo
									.getName() + "_kssj"));
							if (kssj.compareTo(bz_Stime) > 0) {
								bz_Stime = Util.null2String(map.get(userInfo
										.getName() + "_kssj"));
							}
							bz_Etime = Util.null2String(map.get(userInfo
									.getName() + "_jssj"));
							flag = true;
						} else {
							flag = false;
						}
					}
					log.error("上下班时间：" + bz_Etime + bz_Stime);
					// 婚假等以自然天机算,婚假，丧假，产假，陪产假
					switch (qj_three.getQjlb()) {
					case "4":// 婚假
					case "5":// 丧假
					case "6":// 产假
					case "14":// 陪产假
						flag = false;
						break;
					default:
						break;
					}
					if (flag) {
						// 核定开始时间
						qj_three.setHdkssj(qj_three.getKssj());
						if (qj_three.getKssj().compareTo(bz_Stime) < 0) {
							qj_three.setHdkssj(bz_Stime);
						} else if (qj_three.getHdkssj().compareTo(
								userInfo.getAmStartWorkTime()) >= 0
								&& qj_three.getHdkssj().compareTo(
										userInfo.getPmEndWorkTime()) < 0) {
							qj_three.setHdkssj(userInfo.getPmEndWorkTime());
						}
						qj_three.setHdjssj(qj_three.getJssj());
						String hdjssj = nowDate + " " + qj_three.getJssj();
						// 核定结束时间
						for (int i = 0; i < oDK.getDk_list().size(); i++) {
							String dksj = nowDate + " "
									+ oDK.getDk_list().get(i);
							double hd = 0; // 核定开始时间
							hd = PublicVariant.getTimeDifference(dksj, hdjssj);
							hd = Arith.div(hd, 60 * 60 * 1000, 2) + 0.5;
							if (0 <= hd && hd <= 1) {
								break;
							} else if (dksj.compareTo(hdjssj) > 0) {
								qj_three.setHdjssj(oDK.getDk_list().get(i));
								break;
							}
						}
						// 请假结束时间最大为正常下班时间
						if (qj_three.getHdjssj().compareTo(bz_Etime) > 0) {
							qj_three.setHdjssj(bz_Etime);
						} else if (userInfo.getPmEndWorkTime().compareTo(
								qj_three.getHdjssj()) < 0
								&& userInfo.getPmEndWorkTime().compareTo(
										qj_three.getHdjssj()) >= 0) {
							qj_three.setHdjssj(userInfo.getAmStartWorkTime());
						}
						// 计算请假工时
						String startTime = nowDate + " " + qj_three.getHdkssj();
						String endTime = nowDate + " 00:00";
						double t1 = 0; // 核定开始时间
						t1 = PublicVariant
								.getTimeDifference(startTime, endTime);
						t1 = Arith.div(t1, 60 * 60 * 1000, 2);
						double t2 = 12; // 中午下班时间，统一12点
						double t3 = 0; // 核定结束时间
						startTime = nowDate + " " + qj_three.getHdjssj();
						t3 = PublicVariant
								.getTimeDifference(startTime, endTime);
						t3 = Arith.div(t3, 60 * 60 * 1000, 2);
						double t4 = 13; // 下午上班时间
						if (userInfo.getPmEndWorkTime().equals("13:30")) {
							t4 = 13.5;
						}
						double h = 0;
						if (t3 < t2) {
							h = Arith.sub(t3, t1);
						} else if (t3 < t4) {
							h = Arith.sub(t2, t1);
						} else if (t1 < t2) {
							h = Arith.sub(t3, t1) - userInfo.getRest();
						} else if (t1 < t4) {
							h = Arith.sub(t3, t4);
						} else {
							h = Arith.sub(t3, t1);
						}

						int h1 = (int) h;
						if (h < 0) {
							h = 0;
						} else if (h < h1 + 0.25) {
							h = h1;
						} else if (h < h1 + 0.75) {
							h = h1 + 0.5;
						} else {
							h = h1 + 1;
						}
						// 保留1位小数
						h = Arith.round(h, 1);
						qj_three.setHdgs((float) h);
					} else {
						// 正常休息不计入请假。（婚假、产假等以自然天为单位计算？？）
						qj_three.setHdkssj("");
						qj_three.setHdjssj("");
						qj_three.setHdgs(0);
						switch (qj_three.getQjlb()) {
						case "4":// 婚假
						case "5":// 丧假
						case "6":// 产假
						case "14":// 陪产假
							qj_three.setHdgs(8);
							qj_three.setHdkssj(bz_Etime);
							qj_three.setHdjssj(bz_Etime);
							break;
						default:
							break;
						}
					}
					// 更新核定后明细
					qj_three.setHdzt(1);
					// QingJia.delete(qj_three.getMainid(),
					// qj_three.getRow_id());
					QingJia.delete(qj_three.getId());
					log.error("明细表：" + qj_three.toString());
					qj_three.insert();
				}

				/**
				 * 插入数据到中间表中
				 */
				String nowMon = nowDate.substring(5, 7);
				QJtemp qJtemp = new QJtemp();

				qJtemp.setFlowno(qj_main.getAppnom());
				qJtemp.setHdgs(String.valueOf(qj_three.getHdgs()));
				qJtemp.setHdjssj(qj_three.getHdjssj());
				qJtemp.setHdkssj(qj_three.getHdkssj());
				qJtemp.setHdyf(nowMon);
				qJtemp.setHrmid(qj_three.getUserid());
				qJtemp.setHrmno(qj_three.getGh());
				qJtemp.setKqr(qj_three.getKqy());
				qJtemp.setMainid(String.valueOf(qj_three.getMainid()));
				qJtemp.setQjlx(Integer.parseInt(qj_three.getQjlb()));
				qJtemp.setQjrq(qj_three.getRq());
				qJtemp.setXq(qj_three.getXq());
				qJtemp.setDataid(String.valueOf(qj_main.getRequestId()));

				QJtemp.delete(qj_three.getMainid(), qj_three.getRq());// 删除中间表信息

				qJtemp.insert();// 插入中间表信息

			}

		}

	}

	@Override
	public String toString() {
		return "QingJia [userid=" + userid + ", mainid=" + mainid + ", rq="
				+ rq + ", qjlb=" + qjlb + ", kssj=" + kssj + ", jssj=" + jssj
				+ ", hdzt=" + hdzt + ", xq=" + xq + ", id=" + id + ", hdkssj="
				+ hdkssj + ", hdjssj=" + hdjssj + ", dkjl=" + dkjl
				+ ", appnom=" + appnom + ", gh=" + gh + ", hdgs=" + hdgs
				+ ", jdid=" + jdid + ", bzgzsj=" + bzgzsj + ", kqhdyf="
				+ kqhdyf + ", lcspjsrq=" + lcspjsrq + ", kqy=" + kqy
				+ ", row_id=" + row_id + "]";
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getMainid() {
		return mainid;
	}

	public void setMainid(int mainid) {
		this.mainid = mainid;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public String getQjlb() {
		return qjlb;
	}

	public void setQjlb(String qjlb) {
		this.qjlb = qjlb;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDkjl() {
		return dkjl;
	}

	public void setDkjl(String dkjl) {
		this.dkjl = dkjl;
	}

	public String getAppnom() {
		return appnom;
	}

	public void setAppnom(String appnom) {
		this.appnom = appnom;
	}

	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	public double getHdgs() {
		return hdgs;
	}

	public void setHdgs(double hdgs) {
		this.hdgs = hdgs;
	}

	public String getJdid() {
		return jdid;
	}

	public void setJdid(String jdid) {
		this.jdid = jdid;
	}

	public String getBzgzsj() {
		return bzgzsj;
	}

	public void setBzgzsj(String bzgzsj) {
		this.bzgzsj = bzgzsj;
	}

	public String getKqhdyf() {
		return kqhdyf;
	}

	public void setKqhdyf(String kqhdyf) {
		this.kqhdyf = kqhdyf;
	}

	public String getLcspjsrq() {
		return lcspjsrq;
	}

	public void setLcspjsrq(String lcspjsrq) {
		this.lcspjsrq = lcspjsrq;
	}

	public int getKqy() {
		return kqy;
	}

	public void setKqy(int kqy) {
		this.kqy = kqy;
	}

	public int getRow_id() {
		return row_id;
	}

	public void setRow_id(int row_id) {
		this.row_id = row_id;
	}

	public int getHdzt() {
		return hdzt;
	}

	public void setHdzt(int hdzt) {
		this.hdzt = hdzt;
	}
}
