<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="weaver.hrm.User"%>
<%@ page import="weaver.hrm.HrmUserVarify"%>
<%@ page import="java.io.PrintWriter" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
	//User user = HrmUserVarify.getUser(request, response);
	
	String callback = request.getParameter("callback");
	
	//String json = "{\"id\":\""+user.getUID()+"\"}";
	
	if(null != callback){
		response.setContentType("application/json; charset=UTF-8");
		//pw.print(callback+"("+ json +")");
		out.print(callback+"({'id':'4'})");
		out.flush();
		out.close();
	}else{
		response.setContentType("application/json; charset=UTF-8");
		out.print("{'id':'4'}");
	}
 %>
