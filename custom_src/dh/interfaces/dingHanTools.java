/*  1:   */ package weaver.dh.interfaces;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;
/*  4:   */ import org.apache.commons.logging.Log;
/*  5:   */ import org.apache.commons.logging.LogFactory;
/*  6:   */ import weaver.conn.RecordSet;
/*  7:   */ import weaver.general.BaseBean;
/*  8:   */ import weaver.general.Util;
/*  9:   */ 
/* 10:   */ public class dingHanTools
/* 11:   */ {
/* 12:15 */   private Log log = LogFactory.getLog(dingHanTools.class.getName());
/* 13:   */   
/* 14:   */   public Log getLog()
/* 15:   */   {
/* 16:18 */     return this.log;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void setLog(Log log)
/* 20:   */   {
/* 21:22 */     this.log = log;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public HashMap<String, String> getJJR(String hrid, String day)
/* 25:   */   {
/* 26:29 */     BaseBean bs = new BaseBean();
/* 27:30 */     HashMap<String, String> jjr_Map = new HashMap();
/* 28:31 */     RecordSet rsA = new RecordSet();
/* 29:32 */     RecordSet rsA2 = new RecordSet();
/* 30:33 */     String sql = "select departmentid from hrmresource where id = '" + hrid + "'";
/* 31:34 */     String departmentid = "";
/* 32:35 */     rsA.executeSql(sql);
/* 33:36 */     while (rsA.next()) {
/* 34:37 */       departmentid = Util.null2String(rsA.getString("departmentid"));
/* 35:   */     }
/* 36:39 */     String sql2 = "select t1.kssj,t1.jssj,t1.OverNum,t.jrmc,t.roles from  uf_holiday t,uf_holiday_dt1 t1  where t1.mainid=t.id and t1.day ='" + 
/* 37:40 */       day + "' and (','+CAST(t.Person AS VARCHAR(1000))+','  like '%," + hrid + ",%' ";
/* 38:41 */     if (!"".equals(departmentid)) {
/* 39:42 */       sql2 = sql2 + " or ','+CAST(t.dept AS VARCHAR(1000))+',' like '%," + departmentid + ",%'";
/* 40:   */     }
/* 41:44 */     sql2 = sql2 + ")";
/* 42:45 */     this.log.error("节假日 sql2:" + sql2);
/* 43:46 */     rsA2.executeSql(sql2);
/* 44:47 */     while (rsA2.next())
/* 45:   */     {
/* 46:48 */       String jrmc = Util.null2String(rsA2.getString("jrmc"));
/* 47:49 */       String kssj = Util.null2String(rsA2.getString("kssj"));
/* 48:50 */       String jssj = Util.null2String(rsA2.getString("jssj"));
/* 49:51 */       String OverNum = Util.null2String(rsA2.getString("OverNum"));
/* 50:52 */       jjr_Map.put(hrid + "_jrlx", jrmc);
/* 51:53 */       jjr_Map.put(hrid + "_kssj", kssj);
/* 52:54 */       jjr_Map.put(hrid + "_jssj", jssj);
/* 53:55 */       jjr_Map.put(hrid + "_jbxs", OverNum);
/* 54:   */     }
/* 55:57 */     sql = "select roleid from hrmrolemembers where resourceid = '" + hrid + "'";
/* 56:58 */     rsA.executeSql(sql);
/* 57:59 */     for (; rsA.next(); rsA2.next())
/* 58:   */     {
/* 59:60 */       String roleid = Util.null2String(rsA.getString("roleid"));
/* 60:61 */       sql2 = "select t1.kssj,t1.jssj,t1.OverNum,t.jrmc,t.roles from  uf_holiday t,uf_holiday_dt1 t1  where t1.mainid=t.id and t1.day ='" + 
/* 61:62 */         day + "' and " + 
/* 62:63 */         " ','+CAST(t.roles AS VARCHAR(1000))+','  like  '%," + roleid + ",%'";
/* 63:64 */       this.log.error("节假日 sql22:" + sql2);
/* 64:65 */       rsA2.executeSql(sql2);
/* 65:66 */       continue;
/* 66:67 */       String jrmc = Util.null2String(rsA2.getString("jrmc"));
/* 67:68 */       String kssj = Util.null2String(rsA2.getString("kssj"));
/* 68:69 */       String jssj = Util.null2String(rsA2.getString("jssj"));
/* 69:70 */       String OverNum = Util.null2String(rsA2.getString("OverNum"));
/* 70:71 */       jjr_Map.put(hrid + "_jrlx", jrmc);
/* 71:72 */       jjr_Map.put(hrid + "_kssj", kssj);
/* 72:73 */       jjr_Map.put(hrid + "_jssj", jssj);
/* 73:74 */       jjr_Map.put(hrid + "_jbxs", OverNum);
/* 74:   */     }
/* 75:77 */     return jjr_Map;
/* 76:   */   }
/* 77:   */   
/* 78:   */   public int getOverTimeNo(String hrid, String date)
/* 79:   */   {
/* 80:86 */     RecordSet rs = new RecordSet();
/* 81:87 */     int overTimeNo = 1;
/* 82:88 */     String sql = "SELECT OverNum FROM uf_holiday_dt1 t where  dy='" + date + "' and " + 
/* 83:89 */       "exists (select 1 from uf_holiday m where t.mainid = m.id and ('%" + hrid + "%' like Person " + 
/* 84:90 */       "or (select roleid from hrmrolemembers where resourceid = '" + hrid + "') like roles or " + 
/* 85:91 */       "(select departmentid from hrmresource where id = '" + hrid + "') like dept))";
/* 86:92 */     rs.executeSql(sql);
/* 87:93 */     while (rs.next()) {
/* 88:94 */       overTimeNo = rs.getInt(1);
/* 89:   */     }
/* 90:96 */     return overTimeNo;
/* 91:   */   }
/* 92:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\dh\interfaces\
 * Qualified Name:     weaver.dh.interfaces.dingHanTools
 * JD-Core Version:    0.7.0.1
 */