<%@page import="java.io.PrintWriter"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    
<%
    String st = "123";
    
    PrintWriter pw = response.getWriter();
    
    pw.print("123");
    pw.flush();
    
    pw.close();
    
 %>