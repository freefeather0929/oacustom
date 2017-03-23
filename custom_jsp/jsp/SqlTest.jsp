<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.security.*,weaver.general.Util,weaver.hrm.settings.RemindSettings,weaver.file.Prop,weaver.rtx.RTXConfig" %>
<%@ page import="java.lang.reflect.*" %>
<%@ page import="weaver.file.FileUpload" %>
<%@ page import="java.util.*" %>
<%@ page import="weaver.conn.RecordSetDataSource" %>
<%@page import="net.sf.json.*"%>
<%@ page import="javax.servlet.*" %>
<jsp:useBean id="recordSet" class="weaver.conn.RecordSet" scope="page" />
<%@ page import="weaver.hrm.User"%>
<%@ page import="weaver.hrm.HrmUserVarify"%>
<%@page import="weaver.formmode.setup.ModeRightInfo"%>
<jsp:useBean id="ModeShareManager" class="weaver.formmode.view.ModeShareManager" scope="page" />

<%
	User user = HrmUserVarify.getUser(request, response);
	if(user == null){
		response.sendRedirect("/login/Login.jsp");
		return;
	}

	String sql = "select d.departmentmark, d.id from cus_fielddata c,HrmDepartment d "
				+ "where c.field1=d.id and c.id = 4";
	System.out.println(sql);
	recordSet.executeSql(sql);
	int deptId = 0;
	String deptName = "";
	while(recordSet.next()){
		System.out.println(recordSet.getInt("id"));
		deptId = recordSet.getInt("id");
		deptName = recordSet.getString("departmentmark");
	}
	
	//out.close();
%>
<!DOCTYPE html>
<html>
	<body>
		sql = <%=sql%></br>
		部门ID：<%=deptId %></br>
		部门简称：<%=deptName %></br>
	</body>
</html>