/*  1:   */ package dinghan.workflow.action.financialaction;
/*  2:   */ 
/*  3:   */ import dinghan.workflow.action.ChangeReDelStatueAction;
/*  4:   */ import dinghan.workflow.beans.ReceiptList;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import org.apache.commons.logging.Log;
/*  7:   */ import org.apache.commons.logging.LogFactory;
/*  8:   */ import weaver.conn.RecordSet;
/*  9:   */ import weaver.interfaces.workflow.action.Action;
/* 10:   */ import weaver.soa.workflow.request.RequestInfo;
/* 11:   */ import weaver.workflow.request.RequestManager;
/* 12:   */ 
/* 13:   */ public class AutoCopyReceiptListAction
/* 14:   */   implements Action
/* 15:   */ {
/* 16:   */   private RequestManager requestManager;
/* 17:20 */   private Log log = LogFactory.getLog(ChangeReDelStatueAction.class.getName());
/* 18:   */   
/* 19:   */   public String execute(RequestInfo request)
/* 20:   */   {
/* 21:24 */     this.requestManager = request.getRequestManager();
/* 22:25 */     String requestId = request.getRequestid();
/* 23:26 */     int formid = this.requestManager.getFormid();
/* 24:27 */     copyReiptList(requestId, formid);
/* 25:28 */     this.log.error("CurrentOperator :: " + this.requestManager.getCurrentOperator());
/* 26:29 */     this.log.error("Lastoperator :: " + this.requestManager.getLastoperator());
/* 27:30 */     this.log.error("Creater :: " + this.requestManager.getCreater());
/* 28:   */     
/* 29:32 */     return "1";
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void copyReiptList(String requestid, int formid)
/* 33:   */   {
/* 34:36 */     this.log.error("开始自动复制票据台账信息到明细表中...");
/* 35:37 */     RecordSet rs = new RecordSet();
/* 36:   */     
/* 37:39 */     int wfMainId = -1;
/* 38:   */     
/* 39:41 */     String sql = "select id from formtable_main_" + Math.abs(formid) + " where requestId = " + requestid;
/* 40:   */     
/* 41:43 */     rs.executeSql(sql);
/* 42:45 */     while (rs.next()) {
/* 43:46 */       wfMainId = rs.getInt("id");
/* 44:   */     }
/* 45:49 */     sql = "delete from formtable_main_" + Math.abs(formid) + "_dt2 where mainid = " + wfMainId;
/* 46:   */     
/* 47:51 */     rs.executeSql(sql);
/* 48:   */     
/* 49:53 */     sql = "select mainid,d_contracode,d_contramenoy,d_appmoney from formtable_main_" + Math.abs(formid) + "_dt1 where mainid = " + wfMainId;
/* 50:   */     
/* 51:55 */     rs.executeSql(sql);
/* 52:   */     
/* 53:57 */     ArrayList<ReceiptList> rList = new ArrayList();
/* 54:59 */     while (rs.next())
/* 55:   */     {
/* 56:60 */       ReceiptList r = new ReceiptList();
/* 57:61 */       r.setMainId(wfMainId);
/* 58:62 */       r.setCode("");
/* 59:63 */       r.setContraNo(rs.getString("d_contracode"));
/* 60:64 */       r.setContraMoney(rs.getDouble("d_contramenoy"));
/* 61:65 */       r.setMoney(rs.getDouble("d_appmoney"));
/* 62:66 */       r.setStatue(0);
/* 63:67 */       r.setMark("");
/* 64:68 */       rList.add(r);
/* 65:   */     }
/* 66:71 */     for (int i = 0; i < rList.size(); i++)
/* 67:   */     {
/* 68:72 */       sql = 
/* 69:73 */         "insert into formtable_main_" + Math.abs(formid) + "_dt2 (mainid, d_contracode, d_contramoney, d_code, d_kpmoney, d_kpstatus, d_mark)" + " values('" + ((ReceiptList)rList.get(i)).getMainId() + "','" + ((ReceiptList)rList.get(i)).getContraNo() + "','" + ((ReceiptList)rList.get(i)).getContraMoney() + "','" + ((ReceiptList)rList.get(i)).getCode() + "','" + ((ReceiptList)rList.get(i)).getMoney() + "','" + ((ReceiptList)rList.get(i)).getStatue() + "','" + ((ReceiptList)rList.get(i)).getMark() + "')";
/* 70:74 */       rs.executeSql(sql);
/* 71:   */     }
/* 72:77 */     this.log.error("自动复制票据台账信息到明细表结束...");
/* 73:   */   }
/* 74:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.action.financialaction.AutoCopyReceiptListAction
 * JD-Core Version:    0.7.0.1
 */