<%@page contentType="text/html; charset=UTF-8" language="java"  %>
<%@page import="weaver.conn.WeaverConnection"%>
<%@page import="weaver.conn.RecordSet"%>
<%@page import="dinghan.workflow.beans.*"%>
<%@page import="java.security.*,weaver.general.Util,weaver.hrm.settings.RemindSettings,weaver.file.Prop,weaver.rtx.RTXConfig"%>
<%@page import="weaver.file.FileUpload"%>
<%@page import="java.util.*"%>
<%@page import="weaver.conn.RecordSetDataSource"%>
<%@page import="net.sf.json.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="weaver.hrm.User"%>
<%@page import="weaver.hrm.HrmUserVarify"%>
<%@page import="java.text.*"%>
<%@page import="org.apache.commons.logging.*"%>
<%@page import="org.apache.commons.logging.*"%>

<jsp:useBean id="userinfo" class="dinghan.workflow.beans.UserInfo" scope="page" />

   <%
	User user = HrmUserVarify.getUser(request, response);
	if(user == null){
		response.sendRedirect("/login/Login.jsp");
		return;
	}
    String userid=request.getParameter("userid");
    String h=request.getParameter("num");//请假工时
    String leibie=request.getParameter("b");//请假类别

    JSONObject returnData = new JSONObject();
    userinfo=new UserInfo(Integer.parseInt(userid));

   	if(userinfo.getId()!=0){

   		if(leibie.endsWith("2")){//年休假
   			double synx =userinfo.getSYNianXiuJia();//剩余年休
   			double num = Double.parseDouble(h);//请假工时
   			double sy=synx-num;
   			if(sy<0){
   				returnData.put("info", "剩余年休假时间不足，请调整假别");
	            returnData.put("flag", "false");
	            returnData.put("synx", synx);
	           	out.print(returnData.toString());
				out.flush();
				out.close();
   			}else{
   			   	returnData.put("info", "年休假充足");
	            returnData.put("flag", "true");
	            returnData.put("synx", sy);
	           	out.print(returnData.toString());
				out.flush();
				out.close();
   			}
   			
   		}else if(leibie.endsWith("3")){//调休假
   			double sytx = userinfo.getSYTiaoXiuJia();//剩余调休
   			
   			double num = Double.parseDouble(h);//请假工时
   			double sy=sytx-num;

   			if(sy<0){
   				returnData.put("info", "剩余调休假时间不足，请调整假别");
	            returnData.put("flag", "false");
	            returnData.put("sytx", sytx);
	           	out.print(returnData.toString());
				out.flush();
				out.close();
   			}else{
   			   	returnData.put("info", "调休假充足");
	            returnData.put("flag", "true");
	            returnData.put("sytx", sy);
	           	out.print(returnData.toString());
				out.flush();
				out.close();
   			}
   		}
   		
   	}else{
   			returnData.put("info", "未查到年休假信息，请联系考勤员核实人员信息！");
	        returnData.put("flag", "false");
	        returnData.put("sytx", "null");
	        returnData.put("synx", "null");
	        out.print(returnData.toString());
			out.flush();
			out.close();
   	}
    %>