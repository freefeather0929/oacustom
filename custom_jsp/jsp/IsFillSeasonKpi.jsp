<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.security.*,weaver.general.Util"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="weaver.hrm.User"%>
<%@ page import="weaver.hrm.HrmUserVarify"%>
<jsp:useBean id="recordSet" class="weaver.conn.RecordSet" scope="page" />
<jsp:useBean id="calendar" class="java.util.GregorianCalendar" scope="page" />
<%
	/*
	 * 功能：用于判断当前用户当前年份和季度的季度绩效考核流程是否已经填写过了
	 * 编写人：张肖宇
	 * 编写时间：2016-12-14
	 */
	User user = HrmUserVarify.getUser(request, response);
	if(user == null){
		response.sendRedirect("/login/Login.jsp");
		return;
	}
	int flag = 1;
	int formid = Math.abs(Integer.parseInt(request.getParameter("formid")));
	int curUserId = user.getUID();
	int curYear = calendar.get(Calendar.YEAR);	//当前年份
	int curMonth = calendar.get(Calendar.MONTH)+1;
	int seasonIndex = 0;
	String seasonOpt = "一";		//当前季度
	
	if(curMonth>9){
		seasonOpt = "四";
		seasonIndex = 3;
        }else if(curMonth>5){
		seasonOpt = "三";
		seasonIndex = 2;
	}else if(curMonth>3){
		seasonOpt = "二";
		seasonIndex = 1;
	}

	String sql = "select requestId from formtable_main_" + formid + " where apppsn = " + curUserId + " and appyear = "+curYear+" and appseason ='"+seasonOpt+"'";
	
	recordSet.executeSql(sql);
	
	int count = recordSet.getCounts();
	int requestId = -1;
	while(recordSet.next()){
		requestId = recordSet.getInt("requestId");
	}
	StringBuffer json = new StringBuffer();
	if(count>0){	//查询到已经填写过时，直接返回以下数据
		json.append("{'flag':'"+flag+"','filled':'"+count+"','requestid':'"+requestId+"'}");
	}else{	//为检测到时，则获取：1. 部门kpi列表；2.考核关系；3.跨部门项目考核关系；
		int deptId = user.getUserDepartment();	//用户所在的分公司（分部）;
		int tLevel = -1;
		int dept1Id = -1;
		int dept2Id = -1;
		int dept3Id = -1;
		
		sql = "select top 1 tlevel from HrmDepartment where id = " + deptId;
		
		recordSet.executeSql(sql);
		
		while(recordSet.next()){
			tLevel = recordSet.getInt("tlevel");
		}
		
		//从自定义字段表中查询当前人员的一级部门（field1），二级部门（field0），三级部门（field17）
		sql = "select top 1 field1,field0,field17 from cus_fielddata where id = "+curUserId;
		recordSet.executeSql(sql);
		while(recordSet.next()){
			dept1Id = recordSet.getInt("field1");
			dept2Id = recordSet.getInt("field0");
			dept3Id = recordSet.getInt("field17");
		}
		
		switch(tLevel){
			case 3 :
			   sql = "select d.target,d.standard from uf_seasonkpicfgform_dt1 d,uf_seasonkpicfgform m";
			   sql += " where m.id = d.mainid and m.cfgyear = "+curYear+" and m.cfgseason = "+seasonIndex;
			   sql += " and m.cfgdept1 = " + dept1Id + " and m.cfgdept2 = " + dept2Id;
			   recordSet.executeSql(sql);
			   if(recordSet.getCounts()==0){
			       sql = "select d.target,d.standard from uf_seasonkpicfgform_dt1 d,uf_seasonkpicfgform m";
			   	   sql += " where m.id = d.mainid and m.cfgyear = "+curYear+" and cfgseason = "+seasonIndex;
			   	   sql += " and cfgdept1 = " + dept1Id;
			   }
			break;
			
			case 4 :
			   sql = "select d.target,d.standard from uf_seasonkpicfgform_dt1 d,uf_seasonkpicfgform m";
			   sql += " where m.id = d.mainid and m.cfgyear = "+curYear+" and cfgseason = "+seasonIndex;
			   sql += " and cfgdept1 = " + dept1Id + " and cfgdept2 = " + dept2Id + " and cfgdept3 = "+dept3Id;
			   recordSet.executeSql(sql);
			   if(recordSet.getCounts()==0){
			       sql = "select d.target,d.standard from uf_seasonkpicfgform_dt1 d,uf_seasonkpicfgform m";
			       sql += " where m.id = d.mainid and m.cfgyear = "+curYear+" and cfgseason = "+seasonIndex;
			       sql += " and cfgdept1 = " + dept1Id + " and cfgdept2 = " + dept2Id;
			       recordSet.executeSql(sql);
			       if(recordSet.getCounts()==0){
			           sql = "select d.target,d.standard from uf_seasonkpicfgform_dt1 d,uf_seasonkpicfgform m";
			           sql += " where m.id = d.mainid and m.cfgyear = "+curYear+" and cfgseason = "+seasonIndex;
			           sql += " and cfgdept1 = " + dept1Id;
			       }
			   }
			break;
			
			default :
			   sql = "select d.target,d.standard from uf_seasonkpicfgform_dt1 d,uf_seasonkpicfgform m";
			   sql += " where m.id = d.mainid and m.cfgyear = "+curYear+" and cfgseason = "+seasonIndex;
			   sql += " and cfgdept1 = " + dept1Id;
		}
		recordSet.execute(sql);
        json.append("{'kpicount':'"+recordSet.getCounts()+"','kpis':[");
        int i = 0;
        while(recordSet.next()){
            if(i>0){
            	json.append(",");
            }
            json.append("{'target':'"+recordSet.getString("target")+"','standard':'"+recordSet.getString("standard")+"'}");
            i++;
        }
        json.append("],");
        //开始获取考核关系
        sql = "select top 1 exampsn,reviewpsn from uf_relationlistform where cfgrempl = " + curUserId + " and cfgyear = " + curYear + " and cfgseason = "+seasonIndex;
        recordSet.execute(sql);
        int exampsnId = -1;
        int reviewpsnId = -1;
        while(recordSet.next()){
        	exampsnId = recordSet.getInt("exampsn");
        	reviewpsnId = recordSet.getInt("reviewpsn");
        }
        User exampsn = User.getUser(exampsnId, 0);
        User reviewpsn = User.getUser(reviewpsnId, 0);
        json.append("'exampsn':'"+exampsnId+"','exampsnname':'"+exampsn.getLastname()+"',");
        json.append("'reviewpsn':'"+reviewpsnId+"','reviewpsnname':'"+reviewpsn.getLastname()+"'");
        //获取跨部门项目负责人
        sql = "select d.d_crossagent,d.d_crossdept from uf_relationlistform_dt1 d,uf_relationlistform m" 
        		+ " where m.id = d.mainid and cfgrempl = " + curUserId + " and cfgyear = " + curYear + " and cfgseason = "+seasonIndex;
        recordSet.executeSql(sql);
        i=0;
        json.append(",'cross':[");
        ArrayList<User> userList = new ArrayList<User>();
        while(recordSet.next()){
        	if(i>0){
        		json.append(",");
        	}
        	User agent = User.getUser(recordSet.getInt("d_crossagent"), 0);
        	if(agent!=null){
        		userList.add(agent);
        	}
        }
        for(int j=0;j<userList.size();j++){
            if(j>0){
            	json.append(",");
            }
        	sql = "select top 1 departmentmark from HrmDepartment where id = "+userList.get(j).getUserDepartment();
        	recordSet.executeSql(sql);
        	String deptMark = "";
        	while(recordSet.next()){
        		deptMark = recordSet.getString("departmentmark");
        	}
        	json.append("{'agentid':'"+userList.get(j).getUID()+"',");
        	json.append("'agendname':'"+userList.get(j).getLastname()+"',");
        	json.append("'detpid':'"+userList.get(j).getUserDepartment()+"',");
        	json.append("'detmrak':'"+deptMark+"'}");
        }
        json.append("]");
        json.append("}");		
	}
	out.print(json.toString());
	out.flush();
	out.close();
%>