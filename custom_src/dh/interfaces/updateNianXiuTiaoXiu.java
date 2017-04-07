package weaver.dh.interfaces;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weaver.conn.RecordSet;
import weaver.interfaces.schedule.BaseCronJob;
import dinghan.workflow.beans.PublicVariant;
import dinghan.workflow.beans.UserInfo;

public class updateNianXiuTiaoXiu extends BaseCronJob {
	private Log log = LogFactory.getLog(updateNianXiuTiaoXiu.class.getName());

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	@Override
	public void execute() {
		try {
			String sql = "select * from uf_hr_userinfo1 ";
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
			while (rs.next()) {
				UserInfo userInfo = new UserInfo(rs.getInt("Name"));
				double sytx = userInfo.getSYTiaoXiuJia();
				double synx = userInfo.getSYNianXiuJia();
				sytx = synx + sytx;
				userInfo.updateholiday(rs.getInt("Name"), sytx, 1);

				String rzrq = userInfo.getJoinDate();
				String now = PublicVariant.DateToStr(new Date(), "yyyy-MM-dd");
				long num = PublicVariant.getTimeDifference(rzrq, now)
						/ (1000 * 3600 * 24);// 天数
				float nnum = num / 365;// 年数
				if (nnum > 20) {
					synx = 120;
				} else if (nnum > 10) {
					synx = 80;
				} else if (nnum > 1) {
					synx = 40;
				} else {
					int ts = (int) nnum * 5;
					synx = ts * 8;
				}
				userInfo.updateholiday(rs.getInt("Name"), synx, 0);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
