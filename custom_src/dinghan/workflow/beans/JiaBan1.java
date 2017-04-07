package dinghan.workflow.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weaver.conn.RecordSet;
import weaver.dh.interfaces.dingHanTools;
import weaver.general.Util;

public class JiaBan1 {
	private static Log log = LogFactory.getLog(JiaBan1.class.getName());

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		JiaBan1.log = log;
	}

	private int id; // id
	private int mainid;// 主表id
	private String jbrq;// 加班日期
	private int sfztx;// 是否转调休
	private String hdkssj;// 核定开始时间
	private String hdjssj;// 核定结束时间
	private double xxsj;// 休息时间
	private double yxgs;// 有效工时
	private double jbxs;// 加班系数
	private double hdgs;// 核定工时
	private String yjkssj;// 预计开始时间
	private String yjjssj;// 预计结束时间
	private String xq;// 星期
	private int hdzt;// 核定状态
	private String dkjl;

	public String getDkjl() {
		return dkjl;
	}

	public void setDkjl(String dkjl) {
		this.dkjl = dkjl;
	}

	/**
	 * 功能：插入表3信息
	 * 
	 */
	public void insert() throws Exception {
		try {
			String sql = "INSERT INTO formtable_main_94_dt1 (mainid,jbrq,sfztx,hdkssj,hdjssj,xxsj,yxgs,jbxs,hdgs,yjkssj,yjjssj,xq,dkjl,hdzt)";
			sql += " VALUES  (";
			sql += "'" + this.mainid + "',";
			sql += "'" + this.jbrq + "',";
			sql += "'" + this.sfztx + "',";
			sql += "'" + this.hdkssj + "',";
			sql += "'" + this.hdjssj + "',";
			sql += "'" + this.xxsj + "',";
			sql += "'" + this.yxgs + "',";
			sql += "'" + this.jbxs + "',";
			sql += "'" + this.hdgs + "',";
			sql += "'" + this.yjkssj + "',";
			sql += "'" + this.yjjssj + "',";
			sql += "'" + this.xq + "',";
			sql += "'" + this.dkjl + "',";
			sql += "'" + this.hdzt + "'";
			sql += ")";
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("插入加班明细表一：" + e);
			throw e;
		}
	}

	/**
	 * 通过用户id，加班日期，得到明细表1信息
	 * 
	 * @return List<JiaBan1>
	 */

	public static ArrayList<JiaBan1> queryList(String userid, String jbrq,
			String requestid) throws Exception {
		ArrayList<JiaBan1> aJB = new ArrayList<JiaBan1>();
		try {
			String sql = "select * from formtable_main_94 left JOIN formtable_main_94_dt1 on formtable_main_94.id=formtable_main_94_dt1.mainid";
			sql += "  where formtable_main_94.proposer='" + userid;
			sql += "' and formtable_main_94_dt1.jbrq='" + jbrq
					+ "' and  formtable_main_94.requestId!='" + requestid + "'";

			RecordSet rs = new RecordSet();
			rs.executeSql(sql);

			while (rs.next()) {
				JiaBan1 oJB = new JiaBan1();
				oJB.setHdgs(rs.getDouble("hdgs"));
				oJB.setHdjssj(rs.getString("hdjssj"));
				oJB.setHdkssj(rs.getString("hdkssj"));
				oJB.setJbrq(rs.getString("jbrq"));
				oJB.setJbxs(rs.getDouble("jbxs"));
				oJB.setMainid(rs.getInt("mainid"));
				oJB.setSfztx(rs.getInt("sfztx"));
				oJB.setXq(rs.getString("xq"));
				oJB.setXxsj(rs.getDouble("xxsj"));
				oJB.setYjjssj(rs.getString("yjjssj"));
				oJB.setYxgs(rs.getDouble("yxgs"));
				oJB.setYjkssj(rs.getString("yjkssj"));
				oJB.setId(rs.getInt("id1"));
				oJB.setHdzt(rs.getInt("hdzt"));
				aJB.add(oJB);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("得到加班明细2信息出错：" + e);
			throw e;
		}
		return aJB;
	}

	/**
	 * 通过mainid明细表1信息
	 * 
	 * @return List<JiaBan1>
	 */

	public static ArrayList<JiaBan1> queryList(int mainid) throws Exception {
		ArrayList<JiaBan1> aJB = new ArrayList<JiaBan1>();
		try {
			String sql = "select * from formtable_main_94_dt1 where mainid="
					+ mainid;

			RecordSet rs = new RecordSet();
			rs.executeSql(sql);

			while (rs.next()) {
				JiaBan1 oJB = new JiaBan1();
				oJB.setHdgs(rs.getDouble("hdgs"));
				oJB.setHdjssj(rs.getString("hdjssj"));
				oJB.setHdkssj(rs.getString("hdkssj"));
				oJB.setJbrq(rs.getString("jbrq"));
				oJB.setJbxs(rs.getDouble("jbxs"));
				oJB.setMainid(rs.getInt("mainid"));
				oJB.setSfztx(rs.getInt("sfztx"));
				oJB.setXq(rs.getString("xq"));
				oJB.setXxsj(rs.getDouble("xxsj"));
				oJB.setYjjssj(rs.getString("yjjssj"));
				oJB.setYxgs(rs.getDouble("yxgs"));
				oJB.setYjkssj(rs.getString("yjkssj"));
				oJB.setId(rs.getInt("id"));
				oJB.setHdzt(rs.getInt("hdzt"));
				aJB.add(oJB);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("得到加班明细2信息出错：" + e);
			throw e;
		}
		return aJB;
	}

	/**
	 * 功能：根据主表id 状态（为0时，删除mainid=id所有明细；否则，删除明细id=id的明细行）删除表3信息
	 * 
	 */
	public static void delete(int id, int type) throws Exception {
		String sql = "";
		try {
			if (type == 0)
				sql = "delete from formtable_main_94_dt1 where mainid=" + id;
			else {
				sql = "delete from formtable_main_94_dt1 where id=" + id;
			}
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除加班明细表1信息：" + e);
			throw e;
		}
	}

	/**
	 * 开始环节结束时，核定状态为0
	 * 
	 * @param requestId
	 * @param type
	 * @throws Exception
	 */
	public static void update(int mainid, int type) throws Exception {
		try {
			String sql = "update formtable_main_94_dt1 set hdzt=" + type;
			sql += " where mainid=" + mainid;
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("更新考勤异常出错：" + e);
			throw e;
		}
	}

	/**
	 * 根据mainid核定明细
	 * 
	 * @throws Exception
	 */
	public static void checkList(int mainid) throws Exception {
		log.error("加班明细核定：");
		try {
			RecordSet rs = new RecordSet();
			String nowDate = PublicVariant.DateToStr(new Date(), "YYYY-MM-dd");
			String sql = "";
			if (mainid == 0) {
				sql = "SELECT * FROM formtable_main_94_dt1 WHERE jbrq<'"
						+ nowDate + "'";
				sql += " AND hdzt=0 ORDER BY mainid,id";
			} else {
				sql = "SELECT * FROM formtable_main_94_dt1 WHERE mainid="
						+ mainid + " ORDER BY id";
			}

			rs.executeSql(sql);

			ArrayList<Wctemp> list_wc;
			ArrayList<Cctemp> list_cc;

			while (rs.next()) {
				JiaBan1 oJB = new JiaBan1();
				oJB.setHdgs(rs.getDouble("hdgs"));
				oJB.setHdjssj(rs.getString("hdjssj"));
				oJB.setHdkssj(rs.getString("hdkssj"));
				oJB.setId(rs.getInt("id"));
				oJB.setJbrq(rs.getString("jbrq"));
				oJB.setJbxs(rs.getDouble("jbxs"));
				oJB.setMainid(rs.getInt("mainid"));
				oJB.setSfztx(rs.getInt("sfztx"));
				oJB.setXq(rs.getString("xq"));
				oJB.setXxsj(rs.getDouble("xxsj"));
				oJB.setYjjssj(rs.getString("yjjssj"));
				oJB.setYjkssj(rs.getString("yjkssj"));
				oJB.setYxgs(rs.getDouble("yxgs"));
				oJB.setDkjl(rs.getString("dkjl"));
				JiaBan jb_main = new JiaBan(rs.getInt("mainid"));
				if (jb_main.getSqdh().equals("")) {
				} else {

					UserInfo userInfo = new UserInfo(jb_main.getProposer());
					/**
					 * 加班时间和打卡时间的交集 计算出差、外出核定的时间
					 */

					if (oJB.getJbrq().compareTo(nowDate) < 0) {
						// 考勤机记录
						DaKaRecord oDK = new DaKaRecord(userInfo.getCode(),
								oJB.getJbrq());
						list_wc = Wctemp.queryList(userInfo.getName(),
								oJB.getJbrq());
						for (int i = 0; i < list_wc.size(); i++) {
							oDK.getDk_list().add(list_wc.get(i).getHdkssj());
							oDK.getDk_list().add(list_wc.get(i).getHdjssj());
						}
						list_cc = Cctemp.queryList(userInfo.getName(),
								oJB.getJbrq());
						for (int i = 0; i < list_cc.size(); i++) {
							oDK.getDk_list().add(list_cc.get(i).getHdkssj());
							oDK.getDk_list().add(list_cc.get(i).getHdjssj());
						}
						// 考勤记录排序
						for (int i = 0; i < oDK.getDk_list().size(); i++) {
							if (oDK.getDk_list().get(i).equals("")
									|| oDK.getDk_list().get(i)
											.equalsIgnoreCase("null")) {
								oDK.getDk_list().remove(i);
								i--;
							}
						}
						Collections.sort(oDK.getDk_list());
						log.error("打卡记录：" + oDK.getDk_list().toString());
						// 首末次打卡
						if (oDK.getDk_list().size() == 0) {
							oDK.setFirsttime("");
							oDK.setLasttime("");
						} else {
							oDK.setFirsttime(oDK.getDk_list().get(0));
							oDK.setLasttime(oDK.getDk_list().get(
									oDK.getDk_list().size() - 1));
							oJB.setDkjl(oDK.getDk_list().toString());
							log.error("打卡记录：" + oJB.getDkjl());
						}
						// *****加班系数******
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date date = format.parse(oJB.getJbrq());
						SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
						String a = dateFm.format(date);
						switch (a) {
						case "星期六":
						case "星期日":
							oJB.setJbxs(2);
							break;
						default:
							oJB.setJbxs(1.5);
							// oJB.setJbxs(0);
							break;
						}
						String bz_Stime = userInfo.getStartWorkTime();// 标准上班时间
						String bz_Etime = userInfo.getEndWorkTime();// 标准下班时间

						dingHanTools dht = new dingHanTools();
						HashMap<String, String> map = dht.getJJR(
								String.valueOf(userInfo.getName()),
								oJB.getJbrq());
						if (map != null && map.size() > 0) {
							Double jbxs = Double.parseDouble(map.get(userInfo
									.getName() + "_jbxs"));
							String jrlx = Util.null2String(map.get(userInfo
									.getName() + "_jrlx"));
							if ("8".equals(jrlx)) {// 上班调整
								oJB.setJbxs(jbxs);
								oJB.setXq("上班调整");
								bz_Stime = Util.null2String(map.get(userInfo
										.getName() + "__kssj"));
								bz_Etime = Util.null2String(map.get(userInfo
										.getName() + "_jssj"));
							} else {
								if ("0".equals(jrlx)) {
									oJB.setXq("元旦");
								} else if ("1".equals(jrlx)) {
									oJB.setXq("清明节");
								} else if ("2".equals(jrlx)) {
									oJB.setXq("劳动节");
								} else if ("3".equals(jrlx)) {
									oJB.setXq("端午节");
								} else if ("4".equals(jrlx)) {
									oJB.setXq("中秋节");
								} else if ("5".equals(jrlx)) {
									oJB.setXq("国庆节");
								} else if ("6".equals(jrlx)) {
									oJB.setXq("春节");
								} else if ("7".equals(jrlx)) {
									oJB.setXq("抗战胜利日");
								}
								oJB.setJbxs(jbxs);

							}
						}

						if (oJB.getSfztx() == 0) {
							// 加班转调休
							oJB.setJbxs(1);
						}
						// 核定开始时间
						if (oDK.getFirsttime().compareTo(oJB.getYjkssj()) < 0) {
							oJB.setHdkssj(oJB.getYjkssj());
						} else {
							oJB.setHdkssj(oDK.getFirsttime());
						}
						// 核定结束时间
						if (oDK.getLasttime().compareTo(oJB.getYjjssj()) < 0) {
							oJB.setHdjssj(oDK.getLasttime());
						} else {
							oJB.setHdjssj(oJB.getYjjssj());
						}
						// 检测核定结束时间是否比开始时间早
						if (oJB.getHdkssj().compareTo(oJB.getHdjssj()) > 0) {
							oJB.setHdjssj(oJB.getHdkssj());
						}

						// *******核定加班工时************

						if (oJB.getHdkssj().equals("")
								|| oJB.getHdjssj().equals("")
								|| oJB.getHdkssj().equals(oJB.getHdjssj())) {
							oJB.setYxgs(0);
							oJB.setHdgs(0);
							oJB.setXxsj(0);
						} else {

							boolean type = false;
							if ("星期一".endsWith(a) || "星期二".endsWith(a)
									|| "星期三".endsWith(a) || "星期四".endsWith(a)
									|| "星期五".endsWith(a) || "上班调整".endsWith(a)) {
								if (oJB.getHdkssj().compareTo(bz_Stime) < 0) {
									if (oJB.getHdjssj().compareTo(bz_Stime) < 0) {
									} else if (oJB.getHdjssj().compareTo(
											bz_Etime) <= 0) {
										oJB.setHdjssj(bz_Stime);
									} else {
										type = true;
									}
								} else if (oJB.getHdkssj().compareTo(bz_Etime) <= 0) {
									if (oJB.getHdjssj().compareTo(bz_Etime) <= 0) {
										oJB.setHdjssj(oJB.getHdkssj());
									} else {
										oJB.setHdkssj(bz_Etime);
									}
								}
							}
							String startTime = nowDate + " " + oJB.getHdkssj();
							String endTime = nowDate + " 00:00";
							double t1 = 0; // 核定开始时间
							t1 = PublicVariant.getTimeDifference(startTime,
									endTime);
							t1 = Arith.div(t1, 60 * 60 * 1000, 2);

							double t3 = 0; // 核定结束时间
							startTime = nowDate + " " + oJB.getHdjssj();
							t3 = PublicVariant.getTimeDifference(startTime,
									endTime);
							t3 = Arith.div(t3, 60 * 60 * 1000, 2);

							double t4 = 13; // 下午上班时间
							if (userInfo.getPmEndWorkTime().equals("13:30")) {
								t4 = 13.5;
							}

							double h = t3 - t1; // 始末差工时

							/**
							 * 休息时间计算标准 早于12：00（含）开始 下午上班时间（含）前结束，不扣休息时间
							 * 18：30（含）前结束，扣中午休息时间 超过18：30结束，扣中午和晚上的休息时间
							 * 晚于12：00开始 18：30（含）前结束，扣中午休息时间 超过18：30结束，扣晚上的休息时间
							 * 晚于18:30（含18:30）开始，不扣除任何休息时间
							 */
							if (userInfo.getRest() == 1) {// 中午休息时间1个小时的
								if (t1 < 10.5) {
									// 早于10：30开始的
									if (t3 > 20) {
										// 晚于20：00结束
										oJB.setXxsj(2);
									} else if (t3 > 13) {
										// 在13：00到20：00间结束
										oJB.setXxsj(1);
									} else {
										// 早于13：00结束
										oJB.setXxsj(0);
									}
								} else {
									// 晚于10：30开始的
									if (t3 > 20) {
										// 晚于20：00结束
										oJB.setXxsj(1);
									} else {
										// 早于20：00结束
										oJB.setXxsj(0);
									}
								}
							} else {// 中午休息时间1.5个小时的
								if (t1 < 10.5) {
									if (t3 > 19) {
										oJB.setXxsj(2.5);
									} else if (t3 > 13) {
										oJB.setXxsj(1.5);
									} else {
										oJB.setXxsj(0);
									}
								} else if (t1 < 13.5) {
									if (t3 > 19) {
										oJB.setXxsj(2.5);
									} else {
										oJB.setXxsj(1.5);
									}
								} else if (t1 < 19) {
									if (t3 > 19) {
										oJB.setXxsj(1);
									} else {
										oJB.setXxsj(0);
									}
								} else {
									oJB.setXxsj(0);
								}

							}

							h = Arith.sub(h, oJB.getXxsj());

							if (h < 1) {
								oJB.setYxgs(0);
								oJB.setHdgs(0);
							} else {
								// 保留2位小数
								int h1 = (int) h;
								if (h < h1 + 0.25) {
									h = h1;
								} else if (h < h1 + 0.75) {
									h = h1 + 0.5;
								} else {
									h = h1 + 1;
								}
								if (type) {
									h = h - 8;
								}
								oJB.setYxgs((float) Arith.round(h, 1));
								h = Arith.mul(h, oJB.getJbxs());
								oJB.setHdgs((float) Arith.round(h, 2));
								if (oJB.getSfztx() == 0) {
									// 加班转调休
									double sytx = userInfo.getSYTiaoXiuJia();
									sytx = sytx + h;
									userInfo.updateholiday(
											jb_main.getProposer(), sytx, 1);
								}

							}

						}
						// 更新核定后明细
						oJB.setHdzt(1);
						JiaBan1.delete(oJB.getId(), 1);
						oJB.insert();
					}

					/*	*//**
					 * 插入数据到中间表中
					 */
					/*
					 * Jbtemp jbtemp = new Jbtemp();
					 * jbtemp.setDataid(String.valueOf(jb_main.getRequestId()));
					 * jbtemp.setFlowno(jb_main.getSqdh());
					 * jbtemp.setHdgs(oJB.getHdgs());
					 * jbtemp.setHrmid(jb_main.getProposer());
					 * jbtemp.setHrmno(userInfo.getCode()); log.error("加班地点：" +
					 * jb_main.getJbgzdd()); int jbdd = jb_main.getJbgzdd(); if
					 * (jbdd == 0) { jbtemp.setJbdd("北京总部"); } else if (jbdd ==
					 * 1) { jbtemp.setJbdd("深圳分部"); } else if (jbdd == 2) {
					 * jbtemp.setJbdd("东莞生产基地"); } else if (jbdd == 3) {
					 * jbtemp.setJbdd("检测公司"); } else { jbtemp.setJbdd("其他"); }
					 * log.error("加班地点：" + jbtemp.getJbdd());
					 * jbtemp.setJbrq(oJB.getJbrq());
					 * jbtemp.setJbxs(oJB.getJbxs()); // jbtemp.setKqr();
					 * jbtemp.setMainid(String.valueOf(oJB.getMainid()));
					 * jbtemp.setSfztx(oJB.getSfztx());
					 * jbtemp.setXq(oJB.getXq()); jbtemp.setXxsj(oJB.getXxsj());
					 * jbtemp.setYxgs(oJB.getYxgs());
					 * 
					 * Jbtemp.delete(oJB.getMainid(), oJB.getJbrq());//
					 * 删除原来中间表信息
					 * 
					 * jbtemp.insert();// 插入中间表信息
					 */}

			}

		} catch (Exception e) { // TODO Auto-generated catch block
			log.error("加班自动检测：" + e);
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

	public String getJbrq() {
		return jbrq;
	}

	public void setJbrq(String jbrq) {
		this.jbrq = jbrq;
	}

	public int getSfztx() {
		return sfztx;
	}

	public void setSfztx(int sfztx) {
		this.sfztx = sfztx;
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

	public double getXxsj() {
		return xxsj;
	}

	public void setXxsj(double xxsj) {
		this.xxsj = xxsj;
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

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public int getHdzt() {
		return hdzt;
	}

	public void setHdzt(int hdzt) {
		this.hdzt = hdzt;
	}

}
