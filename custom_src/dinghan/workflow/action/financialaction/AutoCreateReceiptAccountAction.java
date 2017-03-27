/*   1:    */ package dinghan.workflow.action.financialaction;
/*   2:    */ 
/*   3:    */ import dinghan.workflow.action.ChangeReDelStatueAction;
/*   4:    */ import dinghan.workflow.beans.ReceiptAccount;
/*   5:    */ import dinghan.workflow.beans.ReceiptList;
/*   6:    */ import java.io.PrintStream;
/*   7:    */ import java.util.ArrayList;
/*   8:    */ import org.apache.commons.logging.Log;
/*   9:    */ import org.apache.commons.logging.LogFactory;
/*  10:    */ import weaver.conn.RecordSet;
/*  11:    */ import weaver.formmode.setup.ModeRightInfo;
/*  12:    */ import weaver.interfaces.workflow.action.Action;
/*  13:    */ import weaver.soa.workflow.request.RequestInfo;
/*  14:    */ import weaver.workflow.request.RequestManager;
/*  15:    */ 
/*  16:    */ public class AutoCreateReceiptAccountAction
/*  17:    */   implements Action
/*  18:    */ {
/*  19:    */   private RequestManager requestManager;
/*  20: 27 */   private Log log = LogFactory.getLog(ChangeReDelStatueAction.class.getName());
/*  21:    */   
/*  22:    */   public String execute(RequestInfo request)
/*  23:    */   {
/*  24: 31 */     this.requestManager = request.getRequestManager();
/*  25: 32 */     String lastOpter = request.getLastoperator();
/*  26: 33 */     this.log.error("最后操作人ID：：" + request.getLastoperator());
/*  27: 34 */     String requestid = request.getRequestid();
/*  28: 35 */     int formid = this.requestManager.getFormid();
/*  29: 36 */     int workFlowId = this.requestManager.getWorkflowid();
/*  30: 37 */     createReceiptList(requestid, formid, workFlowId, lastOpter);
/*  31: 38 */     return "1";
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void createReceiptList(String requestid, int formid, int workFlowId, String lastOperatorId)
/*  35:    */   {
/*  36: 42 */     this.log.error("当前流程类型ID为：" + workFlowId);
/*  37: 43 */     RecordSet rs = new RecordSet();
/*  38: 44 */     String sql = "";
/*  39: 45 */     if (workFlowId != 117) {
/*  40: 46 */       sql = "select id,appno,apppsn,hkdate,pjtype,kppsn,kpdate,contratype,unitname,apptotal from formtable_main_" + Math.abs(formid) + " where requestId = " + requestid;
/*  41:    */     } else {
/*  42: 48 */       sql = "select id,appno,apppsn,hkdate,pjtype,kppsn,kpdate,unitname,apptotal from formtable_main_" + Math.abs(formid) + " where requestId = " + requestid;
/*  43:    */     }
/*  44: 51 */     rs.executeSql(sql);
/*  45: 52 */     ReceiptAccount ra = new ReceiptAccount();
/*  46: 53 */     int wfMainId = -1;
/*  47: 55 */     while (rs.next())
/*  48:    */     {
/*  49: 56 */       wfMainId = rs.getInt("id");
/*  50: 57 */       System.out.println(rs.getString("appno"));
/*  51: 58 */       ra.setAppno(rs.getString("appno"));
/*  52: 59 */       ra.setApppsn(rs.getInt("apppsn"));
/*  53: 60 */       if (workFlowId != 117) {
/*  54: 61 */         ra.setContraType(rs.getInt("contratype"));
/*  55:    */       }
/*  56: 63 */       ra.setHkDate(rs.getString("hkdate"));
/*  57: 64 */       ra.setKpDate(this.requestManager.getCurrentDate());
/*  58: 65 */       ra.setKppsn(rs.getInt("kppsn"));
/*  59: 66 */       ra.setKptotal(rs.getDouble("apptotal"));
/*  60: 67 */       ra.setKpUnit(rs.getString("unitname"));
/*  61: 68 */       ra.setPjType(rs.getInt("pjtype"));
/*  62: 69 */       ra.setRequestId(Integer.parseInt(requestid));
/*  63:    */     }
/*  64: 72 */     sql = "select * from formtable_main_" + Math.abs(formid) + "_dt2 where mainid = " + wfMainId;
/*  65: 73 */     rs.executeSql(sql);
/*  66: 74 */     ArrayList<ReceiptList> rList = new ArrayList();
/*  67: 75 */     while (rs.next())
/*  68:    */     {
/*  69: 76 */       ReceiptList r = new ReceiptList();
/*  70: 77 */       r.setMainId(wfMainId);
/*  71: 78 */       r.setCode(rs.getString("d_code"));
/*  72: 79 */       r.setContraNo(rs.getString("d_contracode"));
/*  73: 80 */       r.setContraMoney(rs.getDouble("d_contramoney"));
/*  74: 81 */       r.setMoney(rs.getDouble("d_kpmoney"));
/*  75: 82 */       r.setStatue(0);
/*  76: 83 */       r.setMark(rs.getString("d_mark"));
/*  77: 84 */       rList.add(r);
/*  78:    */     }
/*  79: 86 */     ra.setReceiptList(rList);
/*  80:    */     
/*  81: 88 */     String receiptFormName = "uf_invoiceaccount";
/*  82: 89 */     int modeId = 18;
/*  83: 91 */     if (workFlowId != 117)
/*  84:    */     {
/*  85: 92 */       receiptFormName = "uf_receiptaccount";
/*  86: 93 */       modeId = 32;
/*  87:    */     }
/*  88: 96 */     sql = "select id from " + receiptFormName + " where requestId = " + requestid;
/*  89: 97 */     rs.executeSql(sql);
/*  90: 98 */     int raId = -1;
/*  91: 99 */     if (rs.getCounts() > 0)
/*  92:    */     {
/*  93:100 */       if (rs.next()) {
/*  94:101 */         raId = rs.getInt("id");
/*  95:    */       }
/*  96:103 */       sql = "delete from " + receiptFormName + " where requestId = " + requestid;
/*  97:104 */       rs.executeSql(sql);
/*  98:105 */       sql = "delete from " + receiptFormName + "_dt1 where mainid = " + raId;
/*  99:106 */       rs.executeSql(sql);
/* 100:    */     }
/* 101:109 */     if (workFlowId != 117) {
/* 102:110 */       sql = 
/* 103:111 */         "insert into " + receiptFormName + " (requestId, appno, apppsn, kpdate, pjtype, kppsn, hkdate, contratype, kpunit, kptotal, formmodeid, modedatacreater, modedatacreatertype, modedatacreatedate, modedatacreatetime)" + " values('" + requestid + "','" + ra.getAppno() + "','" + ra.getApppsn() + "','" + ra.getKpDate() + "','" + ra.getPjType() + "','" + lastOperatorId + "','" + ra.getHkDate() + "','" + ra.getContraType() + "','" + ra.getKpUnit() + "','" + ra.getKptotal() + "','32','" + lastOperatorId + "','0','" + this.requestManager.getCurrentDate() + "','" + this.requestManager.getCurrentTime() + "')";
/* 104:    */     } else {
/* 105:113 */       sql = 
/* 106:114 */         "insert into " + receiptFormName + " (requestId, appno, apppsn, kpdate, pjtype, kppsn, hkdate, kpunit, kptotal, formmodeid, modedatacreater, modedatacreatertype, modedatacreatedate, modedatacreatetime)" + " values('" + requestid + "','" + ra.getAppno() + "','" + ra.getApppsn() + "','" + ra.getKpDate() + "','" + ra.getPjType() + "','" + lastOperatorId + "','" + ra.getHkDate() + "','" + ra.getKpUnit() + "','" + ra.getKptotal() + "','18','" + lastOperatorId + "','0','" + this.requestManager.getCurrentDate() + "','" + this.requestManager.getCurrentTime() + "')";
/* 107:    */     }
/* 108:117 */     this.log.error("执行：" + sql);
/* 109:    */     
/* 110:119 */     rs.executeSql(sql);
/* 111:120 */     sql = "select id from " + receiptFormName + " where requestId =" + requestid;
/* 112:121 */     rs.executeSql(sql);
/* 113:122 */     int receiptAccountId = -1;
/* 114:123 */     while (rs.next()) {
/* 115:124 */       receiptAccountId = rs.getInt("id");
/* 116:    */     }
/* 117:126 */     for (int i = 0; i < ra.getReceiptList().size(); i++)
/* 118:    */     {
/* 119:127 */       sql = 
/* 120:128 */         "insert into " + receiptFormName + "_dt1 (mainid, d_contracode, d_contramoney, d_code, d_kpmoney, d_kpstatus, d_mark)" + " values ('" + receiptAccountId + "','" + ((ReceiptList)ra.getReceiptList().get(i)).getContraNo() + "','" + ((ReceiptList)ra.getReceiptList().get(i)).getContraMoney() + "','" + ((ReceiptList)ra.getReceiptList().get(i)).getCode() + "','" + ((ReceiptList)ra.getReceiptList().get(i)).getMoney() + "','" + ((ReceiptList)ra.getReceiptList().get(i)).getStatue() + "','" + ((ReceiptList)ra.getReceiptList().get(i)).getMark() + "')";
/* 121:129 */       rs.executeSql(sql);
/* 122:    */     }
/* 123:131 */     ModeRightInfo modeRightInfo = new ModeRightInfo();
/* 124:132 */     modeRightInfo.rebuildModeDataShareByEdit(Integer.parseInt(lastOperatorId), modeId, receiptAccountId);
/* 125:    */   }
/* 126:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.action.financialaction.AutoCreateReceiptAccountAction
 * JD-Core Version:    0.7.0.1
 */