package dinghan.workflow.action;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import dinghan.workflow.beans.Jbtemp;
import dinghan.workflow.beans.JiaBan;
import dinghan.workflow.beans.JiaBan1;
import dinghan.workflow.beans.UserInfo;

public class JbGenerateDetail implements Action {
	private Log log = LogFactory.getLog(YcGenerateDetail.class.getName());

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	@SuppressWarnings("finally")
	@Override
	public String execute(RequestInfo request) {
		String returninfo = FAILURE_AND_CONTINUE;
		// TODO Auto-generated method stub
		try {
			String requestid = request.getRequestid();// 得到requestid

			int nodeid = request.getRequestManager().getNodeid();// 得到节点状态

			JiaBan jb_main = new JiaBan(requestid);

			if (nodeid != 602 && nodeid != 601) {
				JiaBan1.update(jb_main.getId(), 0);// 考勤异常的核定状态为0，此时异常信息待核定
			} else if (nodeid == 602 || nodeid == 601) {
				JiaBan1.update(jb_main.getId(), 2);// 修改考勤异常的核定状态为2，此时异常信息有效

				UserInfo userInfo = new UserInfo(jb_main.getProposer());

				ArrayList<JiaBan1> jb_onelist = JiaBan1.queryList(jb_main
						.getId());// 得到明细表list

				for (int i = 0; i < jb_onelist.size(); i++) {
					JiaBan1 jb_one = jb_onelist.get(i);

					Jbtemp jbtemp = new Jbtemp();
					jbtemp.setDataid(String.valueOf(jb_main.getRequestId()));
					jbtemp.setFlowno(jb_main.getSqdh());
					jbtemp.setHdgs(jb_one.getHdgs());
					jbtemp.setHrmid(jb_main.getProposer());
					jbtemp.setHrmno(userInfo.getCode());
					// jbtemp.setJbdd(jb_main.getJbgzdd());
					int jbdd = jb_main.getJbgzdd();
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
					jbtemp.setJbrq(jb_one.getJbrq());
					jbtemp.setJbxs(jb_one.getJbxs());
					// jbtemp.setKqr();
					jbtemp.setMainid(String.valueOf(jb_main.getId()));
					jbtemp.setSfztx(jb_one.getSfztx());
					jbtemp.setXq(jb_one.getXq());
					jbtemp.setXxsj(jb_one.getXxsj());
					jbtemp.setYxgs(jb_one.getYxgs());

					Jbtemp.delete(jb_main.getId(), jb_one.getJbrq());// 删除加班中间表信息

					jbtemp.insert();// 插入中间表信息
				}

			}

			returninfo = Action.SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("修改异常核定状态出错：" + e);
		} finally {
			return returninfo;
		}
	}

}
