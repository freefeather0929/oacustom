package dinghan.workflow.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import dinghan.workflow.beans.Arith;
import dinghan.workflow.beans.PublicVariant;
import dinghan.workflow.beans.QJtemp;
import dinghan.workflow.beans.QingJia;
import dinghan.workflow.beans.QingJia0;
import dinghan.workflow.beans.QingJia1;
import dinghan.workflow.beans.UserInfo;

public class GenerateLeaveDetails implements Action {
	private Log log = LogFactory.getLog(GenerateLeaveDetails.class.getName());

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	/**
	 * 生成明细
	 */
	@SuppressWarnings("finally")
	@Override
	public String execute(RequestInfo request) {
		String returninfo = FAILURE_AND_CONTINUE;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE");
		try {
			String requestid = request.getRequestid();// 得到请求id

			int nodeid = request.getRequestManager().getNodeid();// 得到节点状态

			QingJia0 qj_main = new QingJia0(requestid);// 得到主表信息
			int userid = qj_main.getProposer();
			int mainid = qj_main.getId();

			int type = 0;// 0未核定， 1系统核定 2考勤员复核
			if (580 == nodeid) {

				UserInfo userInfo = new UserInfo(userid);// 得到人员信息表信息

				QingJia.delete(mainid, 0);// 删除明细表三的数据

				ArrayList<QingJia1> qj_oneList = QingJia1.queryList(mainid);// 查询出明细表1的数据List

				for (int i = 0; i < qj_oneList.size(); i++) {
					QingJia1 qj_one = qj_oneList.get(i);// 单条明细1数据

					Date deDate = sdf.parse(qj_one.getJsrq());// 开始日期
					Date dsDate = sdf.parse(qj_one.getKsrq());// 结束日期

					double num = 0;// 每条明细请假工时
					String startTime = qj_one.getKsrq() + " "
							+ qj_one.getKssj();// 请假开始时间
					String endTime = qj_one.getJsrq() + " " + qj_one.getJssj();// 请假结束时间
					// 计算此条明细请假天数
					String se = qj_one.getKsrq() + " "
							+ userInfo.getEndWorkTime();
					String ee = qj_one.getJsrq() + " "
							+ userInfo.getEndWorkTime();
					String es = qj_one.getJsrq() + " "
							+ userInfo.getStartWorkTime();
					double dnum = PublicVariant.getTimeDifference(se, ee);
					dnum = Arith.div(dnum, 60 * 60 * 1000 * 24, 2);// 天数

					if (dnum > 0) {// 如果大于一天
						if (qj_one.getKssj().equals("08:30")
								|| qj_one.getKssj().equals("09:00")) {
							num = 8 + (dnum - 1) * 8;
						} else {
							double q = PublicVariant.getTimeDifference(
									startTime, se);
							q = Arith.div(q, 60 * 60 * 1000, 2);
							num = num + q + (dnum - 1) * 8;
						}
						if (qj_one.getJssj().equals("12:00")) {
							double q = PublicVariant.getTimeDifference(es,
									endTime);
							q = Arith.div(q, 60 * 60 * 1000, 2);
							num = num + q;
						} else {
							num = num + 8;
						}
					} else {// 一天
						if ((qj_one.getKssj().equals("08:30") || qj_one
								.getKssj().equals("09:00"))
								&& (qj_one.getJssj().equals("17:30") || qj_one
										.getJssj().equals("18:00"))) {
							num = 8;
						} else {
							num = PublicVariant.getTimeDifference(startTime,
									endTime);
							num = Arith.div(num, 60 * 60 * 1000, 2);
						}
					}

					if (qj_one.getQjlb() == 2) {
						// 修改年休假信息
						double synx = userInfo.getSYNianXiuJia() - num;
						userInfo.updateholiday(userid, synx, 0);
					} else if (qj_one.getQjlb() == 3) {
						// 修改调休信息
						double sytx = userInfo.getSYTiaoXiuJia() - num;
						userInfo.updateholiday(userid, sytx, 1);
					}

					long qjNum = (deDate.getTime() - dsDate.getTime())
							/ (1000 * 60 * 60 * 24) + 1;// 请假天数

					for (int j = 0; j < qjNum; j++) {

						Calendar calendar = new GregorianCalendar();
						calendar.setTime(dsDate);
						calendar.add(calendar.DATE, j);
						Date dsDate1 = calendar.getTime(); // 明细日期
						String dsDateString = sdf.format(dsDate1);// 明细日期字符串
						String Week = sdf1.format(dsDate1);// 星期

						// 根据日期的不同进行区分插入
						QingJia qj_three = new QingJia();
						qj_three.setHdzt(type);
						qj_three.setRq(dsDateString);
						qj_three.setQjlb(String.valueOf(qj_one.getQjlb()));
						qj_three.setXq(Week);
						qj_three.setMainid(mainid);
						qj_three.setUserid(userid);
						qj_three.setRow_id(i + 1);
						qj_three.setAppnom("");
						qj_three.setDkjl("");
						qj_three.setHdgs(0);
						qj_three.setHdjssj("");
						qj_three.setHdkssj("");
						if (dsDate.equals(deDate)) {// 当开始日期等于结束日期时
							qj_three.setKssj(qj_one.getKssj());
							qj_three.setJssj(qj_one.getJssj());
						} else { // 开始日期不等于结束日期时
							if (j == 0) { // 第一天
								qj_three.setKssj(qj_one.getKssj());
								qj_three.setJssj(userInfo.getEndWorkTime());
							} else if (j + 1 == qjNum) {// 最后一天
								qj_three.setKssj(userInfo.getStartWorkTime());
								qj_three.setJssj(qj_one.getJssj());
							} else { // 中间
								qj_three.setKssj(userInfo.getStartWorkTime());
								qj_three.setJssj(userInfo.getEndWorkTime());
							}
						}
						qj_three.insert();

					}
				}

			} else if (587 == nodeid) {// 587考勤员复核节点
				type = 2;
				QingJia.update(mainid, type);// 更新请假明细表三的核定状态 将状态修改为2

				ArrayList<QingJia> qj_threeList = QingJia.queryList(mainid);// 得到明细表list

				String nowDate = PublicVariant.DateToStr(new Date(),
						"YYYY-MM-dd");

				String nowMon = nowDate.substring(5, 7);// 核定月份

				for (int i = 0; i < qj_threeList.size(); i++) {

					QingJia qj_three = qj_threeList.get(i);
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
					qJtemp.setDataid("");

					QJtemp.delete(mainid, qj_three.getRq());// 删除请假中间表信息

					qJtemp.insert();// 插入请假中间表信息
				}
			} else {
				return returninfo;
			}

			returninfo = Action.SUCCESS;

		} catch (Exception e) {
			// TODO: handle exception
			log.error("生成明细失败" + e);

		} finally {
			return returninfo;
		}
	}
}