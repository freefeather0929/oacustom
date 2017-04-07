package weaver.dh.interfaces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weaver.interfaces.schedule.BaseCronJob;
import dinghan.workflow.beans.ChuChai2;
import dinghan.workflow.beans.Jbtemp;
import dinghan.workflow.beans.JiaBan1;
import dinghan.workflow.beans.QingJia;
import dinghan.workflow.beans.WaiChu;

public class AutoTask extends BaseCronJob {
	private Log log = LogFactory.getLog(AutoTask.class.getName());

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	@Override
	public void execute() {
		try {
			log.error("核定明细开始");
			QingJia.checkList(0);
			ChuChai2.checkList(0);
			WaiChu.checkList(0);
			JiaBan1.checkList(0);
			Jbtemp.insertJb();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
