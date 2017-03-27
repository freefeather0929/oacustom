/*  1:   */ package dinghan.workflow.action.seasonkpiaction;
/*  2:   */ 
/*  3:   */ import dinghan.workflow.action.ChangeReDelStatueAction;
/*  4:   */ import dinghan.workflow.beans.SeasonKpi;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import org.apache.commons.logging.Log;
/*  7:   */ import org.apache.commons.logging.LogFactory;
/*  8:   */ import weaver.conn.RecordSet;
/*  9:   */ import weaver.interfaces.workflow.action.Action;
/* 10:   */ import weaver.soa.workflow.request.RequestInfo;
/* 11:   */ import weaver.workflow.request.RequestManager;
/* 12:   */ 
/* 13:   */ public class CopyKpiListToMonthSammaryListAction
/* 14:   */   implements Action
/* 15:   */ {
/* 16:   */   private RequestManager requestManager;
/* 17:18 */   private Log log = LogFactory.getLog(ChangeReDelStatueAction.class.getName());
/* 18:   */   
/* 19:   */   public String execute(RequestInfo request)
/* 20:   */   {
/* 21:22 */     this.requestManager = request.getRequestManager();
/* 22:23 */     String requestid = request.getRequestid();
/* 23:24 */     String workflowId = request.getWorkflowid();
/* 24:25 */     int formid = this.requestManager.getFormid();
/* 25:26 */     int billId = this.requestManager.getBillid();
/* 26:27 */     this.log.error("billId = " + billId);
/* 27:28 */     CopyKLToMSL(requestid, workflowId, formid);
/* 28:29 */     return "1";
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void CopyKLToMSL(String requestId, String workflowId, int formid)
/* 32:   */   {
/* 33:34 */     this.log.error("开始执行拷贝KPI项目到月度总结列表action，requestId = " + requestId + 
/* 34:35 */       "; workFlowId = " + workflowId + "; formid = " + formid + "...");
/* 35:   */     
/* 36:37 */     String sql = "select top 1 id from formtable_main_" + Math.abs(formid) + " where requestId = " + requestId;
/* 37:38 */     RecordSet rs = new RecordSet();
/* 38:   */     
/* 39:40 */     rs.executeSql(sql);
/* 40:   */     
/* 41:42 */     int mainId = -1;
/* 42:44 */     while (rs.next()) {
/* 43:45 */       mainId = rs.getInt("id");
/* 44:   */     }
/* 45:49 */     sql = "delete from formtable_main_" + Math.abs(formid) + "_dt3 where mainid = " + mainId;
/* 46:50 */     rs.executeSql(sql);
/* 47:51 */     sql = "delete from formtable_main_" + Math.abs(formid) + "_dt4 where mainid = " + mainId;
/* 48:52 */     rs.executeSql(sql);
/* 49:   */     
/* 50:54 */     sql = "select id,mainid,d2_kpitarget,d2_kpistandard,d2_kpiweight from formtable_main_" + Math.abs(formid) + "_dt2 where mainid = " + mainId;
/* 51:   */     
/* 52:56 */     rs.executeSql(sql);
/* 53:   */     
/* 54:58 */     ArrayList<SeasonKpi> kpiList = new ArrayList();
/* 55:60 */     while (rs.next())
/* 56:   */     {
/* 57:61 */       SeasonKpi kpi = new SeasonKpi();
/* 58:62 */       kpi.setDtId(rs.getInt("id"));
/* 59:63 */       kpi.setMainId(rs.getInt("mainid"));
/* 60:64 */       kpi.setKpitarget(rs.getString("d2_kpitarget"));
/* 61:65 */       kpi.setStandard(rs.getString("d2_kpistandard"));
/* 62:66 */       kpi.setWeight(rs.getInt("d2_kpiweight"));
/* 63:67 */       kpiList.add(kpi);
/* 64:   */     }
/* 65:70 */     for (int i = 0; i < kpiList.size(); i++)
/* 66:   */     {
/* 67:71 */       sql = 
/* 68:   */       
/* 69:   */ 
/* 70:   */ 
/* 71:   */ 
/* 72:   */ 
/* 73:77 */         "insert into formtable_main_" + Math.abs(formid) + "_dt3 (mainid,d_m1_kpitarget,d_m1_kpistandard,d_m1_kpiweight)" + " values(" + "'" + ((SeasonKpi)kpiList.get(i)).getMainId() + "'," + "'" + ((SeasonKpi)kpiList.get(i)).getKpitarget() + "'," + "'" + ((SeasonKpi)kpiList.get(i)).getStandard() + "'," + "'" + ((SeasonKpi)kpiList.get(i)).getWeight() + "'" + ")";
/* 74:78 */       this.log.error("执行插入操作：" + sql);
/* 75:79 */       rs.executeSql(sql);
/* 76:   */       
/* 77:81 */       sql = "insert into formtable_main_" + Math.abs(formid) + "_dt4 (mainid,d_m2_kpitarget,d_m2_kpistandard,d_m2_kpiweight)" + 
/* 78:82 */         " values(" + 
/* 79:83 */         "'" + ((SeasonKpi)kpiList.get(i)).getMainId() + "'," + 
/* 80:84 */         "'" + ((SeasonKpi)kpiList.get(i)).getKpitarget() + "'," + 
/* 81:85 */         "'" + ((SeasonKpi)kpiList.get(i)).getStandard() + "'," + 
/* 82:86 */         "'" + ((SeasonKpi)kpiList.get(i)).getWeight() + "'" + 
/* 83:87 */         ")";
/* 84:88 */       this.log.error("执行插入操作：" + sql);
/* 85:89 */       rs.executeSql(sql);
/* 86:   */     }
/* 87:92 */     this.log.error("执行拷贝KPI项目到月度总结列表action结束");
/* 88:   */   }
/* 89:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.action.seasonkpiaction.CopyKpiListToMonthSammaryListAction
 * JD-Core Version:    0.7.0.1
 */