package weaver.dh.interfaces;

import java.util.HashMap;
import java.util.Map;

import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.interfaces.schedule.BaseCronJob;

import com.weaver.formmodel.util.DateHelper;

public class toU9 extends BaseCronJob{
	
	private String month = DateHelper.getCurrentMonth();
	private String year = DateHelper.getCurrentYear();
	String mon = "";
	String account = "";
	int userId = 0;
	
	private Map<String,String> DkMap = new HashMap<String,String>();//临时code
	HashMap<String,String> cus_Map = new HashMap<String,String>();
	HashMap<String,String> hz_Map = new HashMap<String,String>();
	HashMap<String,String> Map = new HashMap<String,String>();
	RecordSet rsA = new RecordSet();
	RecordSet rs = new RecordSet();

	public void getJump(){
//		this.month = month;
		this.execute();
//		String account  = user.getAccount();
//		this.account = account;
//		this.userId = user.getUID();
	}
	
	@Override
	public void execute() {
		String sql = "select * from uf_kqhz where hzyf = '"+month+"'";
		rsA.executeSql(sql);
		while(rsA.next()){
			//String kqlb = Util.null2String(rsA.getString("kqlb"));//考勤类别
			String gh = Util.null2String(rsA.getString("gh"));//工号
			String U9id = Util.null2String(Map.get(gh+"_ID"));//人员在U9中的id
			String U9orgId = Util.null2String(Map.get(gh+"_ORG"));//人员在U9中的orgid
			String exsisid = Util.null2String(hz_Map.get(U9id));//是否有
			String hzyf = Util.null2String(rsA.getString("hzyf"));//月份
			//String kqrq = Util.null2String(rsA.getString("kqrq"));//考勤日期
			String trgs =  Util.null2String(rsA.getString("trgs"));//他人工伤
			String brj =  Util.null2String(rsA.getString("brj"));//哺乳假
			//String wdk =  Util.null2String(rsA.getString("wdk"));//忘打卡
			String kgsj =  Util.null2String(rsA.getString("kg"));//旷工时间
			String xm = Util.null2String(rsA.getString("xm"));//姓名  id
			String cd = Util.null2String(rsA.getString("cd"));;//迟到次数 
			//String cdtimes = Util.null2String(rsA.getString("cdTime"));;//迟到
			String zaot = Util.null2String(rsA.getString("zt"));//早退次数
			//String zaotimes = Util.null2String(rsA.getString("ztTime"));//早退时间
			//String chuchai = "";
		//	String qjlx = Util.null2String(rsA.getString("qjlx"));//请假类型
		//	String qjgs = Util.null2String(rsA.getString("qjgs"));//请假工时
		//	String ssgs = Util.null2String(rsA.getString("ssgs"));//所属公司
		//	String yjbm = Util.null2String(rsA.getString("yjbm"));//一级部门
		//	String ejbm =  Util.null2String(rsA.getString("ejbm"));//二级部门
		//	String rzzt = Util.null2String(rsA.getString("rzzt"));//状态
		//	String gw = Util.null2String(rsA.getString("gw"));//岗位
			String jbgs = Util.null2String(rsA.getString("jbgs"));//加班工时
		//	String jbgsTimes = "";
			String sj = Util.null2String(rsA.getString("sj"));//事假
			String bj = Util.null2String(rsA.getString("bj"));//病假
			String nxj = Util.null2String(rsA.getString("nxj"));//年休假
			String txj = Util.null2String(rsA.getString("txj"));//调休假
			String hj = Util.null2String(rsA.getString("hj"));//婚假
			String sangj = Util.null2String(rsA.getString("sangj"));//丧假
			String cj = Util.null2String(rsA.getString("cj"));;//产假
			String cjj = Util.null2String(rsA.getString("cjj"));//产检假
			String lcj = Util.null2String(rsA.getString("lcj"));//有指标流产假
			String jyj = Util.null2String(rsA.getString("jyj"));//节育假
			String jsj = Util.null2String(rsA.getString("jsj"));//计生假
			String pcj = Util.null2String(rsA.getString("pcj"));//pcj
			String ActualAttendHours = "";//实际出勤时间
		//	String ActualAttendTimes = "";//实际出勤次数
			String grgs = Util.null2String(rsA.getString("grgs"));//个人工伤
			String wdkcs = "";//未打卡次数
			String exesql = "";
			if("".equals(exsisid)){
				String id = "";
				String curentTime = DateHelper.getCurDateTimeStr();
				long currenttime = System.currentTimeMillis();
				RecordSet rcs = new RecordSet();
				rcs.executeSql("SELECT NEXT VALUE FOR U9idseq");
				while(rcs.next()){
					int seq = rcs.getInt(1);
					id = currenttime + ""+seq;
				}
				/**
				 * CREATE SEQUENCE U9idseq AS bigint
                START WITH 1000000
                INCREMENT BY 1;
				 */
				exesql= "insert into HI_AttendResult(ID,CreatedBy,CreatedOn,ModifiedBy,ModifiedOn,SysVersion,AbsentHours," +
				"ActualAttendHours,BornLeaveHours,CreateOrg,DeathLeaveHours," +
				"EarlyLeaveTimes,Employee,LateTimes," +
				"OvertimeHours,OwnerOrg,PeriodNum,PeriodType,Person,PrivateLeaveHours," +
				"SickLeaveHours,WeddingLeaveHours," +
				"WorkOrg,Year,YearLeaveHours,DescFlexField_PrivateDescSeg1,DescFlexField_PrivateDescSeg2," +
				"DescFlexField_PrivateDescSeg3,DescFlexField_PrivateDescSeg4,DescFlexField_PrivateDescSeg5,DescFlexField_PrivateDescSeg6," +
				"DescFlexField_PrivateDescSeg7,DescFlexField_PrivateDescSeg8,DescFlexField_PrivateDescSeg9,DescFlexField_PrivateDescSeg10," +
				"DescFlexField_PrivateDescSeg11,DescFlexField_PrivateDescSeg12) values('" +id+"','OA','"+curentTime+"','','','',0,'"+kgsj+"'," +
				"'"+ActualAttendHours+"','"+cj+"','','"+sangj+"','"+zaot+"','"+U9id+"'," +
				"'"+cd+"','"+jbgs+"','"+U9orgId+"','"+mon+"',0,'','"+sj+"','"+bj+"'," +
				"'"+hj+"','"+U9orgId+"','"+year+"','"+nxj+"','"+grgs+"','','"+wdkcs+"','"+trgs+"','"+lcj+"','0'," +
				"'"+jyj+"','"+jsj+"','"+txj+"','"+cjj+"','"+pcj+"','"+brj+"')";
			}else{
				exesql = "update HI_AttendResult set AbsentHours='"+kgsj+"'," +
				"ActualAttendHours='"+ActualAttendHours+"'," +
				"BornLeaveHours='"+cj+"',CreateOrg='',DeathLeaveHours='"+sangj+"'," +
				"EarlyLeaveTimes='"+zaot+"',Employee='"+U9id+"'," +
				"LateTimes='"+cd+"'," +
				"OvertimeHours='"+jbgs+"',OwnerOrg='"+U9orgId+"',PeriodNum='"+mon+"',PeriodType=0," +
				"Person='',PrivateLeaveHours='"+sj+"'," +
				"SickLeaveHours='"+bj+"',WeddingLeaveHours='"+hj+"'," +
				"WorkOrg='"+U9orgId+"',Year='"+year+"',YearLeaveHours='"+nxj+"'," +
				"DescFlexField_PrivateDescSeg1='"+grgs+"',DescFlexField_PrivateDescSeg2=''," +
				"DescFlexField_PrivateDescSeg3='"+wdkcs+"',DescFlexField_PrivateDescSeg4='"+trgs+"',DescFlexField_PrivateDescSeg5='"+lcj+"'," +
				"DescFlexField_PrivateDescSeg6='0'," +
				"DescFlexField_PrivateDescSeg7='"+jyj+"',DescFlexField_PrivateDescSeg8='"+jsj+"',DescFlexField_PrivateDescSeg9='"+txj+"'," +
						"DescFlexField_PrivateDescSeg10='"+cjj+"'," +
				"DescFlexField_PrivateDescSeg11='"+pcj+"',DescFlexField_PrivateDescSeg12='"+brj+"' where id = '"+exsisid+"'";
			}
			rs.executeSql(exesql,"U9");
		}
		
	}
	
	
	public void HrmData(){
		String sql = "select ID,EmployeeCode,OwnerOrg from CBO_EmployeeArchive";
		rsA.executeSql(sql,"");
		while(rsA.next()){
			String code = Util.null2String(rsA.getString("EmployeeCode"));
			String U9id = Util.null2String(rsA.getString("ID"));
			String OwnerOrg = Util.null2String(rsA.getString("OwnerOrg"));
			Map.put(code+"_ID", U9id);
			Map.put(code+"_ORG", OwnerOrg);
		}
	}
		
	/**
	 * 汇总
	 */
	public void getData(){
		if(month.length()>=5){
			mon = month.substring(5, 7);
		}
		String sql = "select * from HI_AttendResult where PeriodNum = '"+mon+"'";
		rsA.executeSql(sql,"");
		while(rsA.next()){
			String u9hrid = Util.null2String(rsA.getString("Employee"));
			String id = Util.null2String(rsA.getString("id"));
			hz_Map.put(u9hrid, id);
		}
	}
}
