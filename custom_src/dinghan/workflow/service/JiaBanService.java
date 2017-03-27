/*   1:    */ package dinghan.workflow.service;
/*   2:    */ 
/*   3:    */ import dinghan.workflow.beans.JiaBan1;
/*   4:    */ import dinghan.workflow.beans.QingJia;
/*   5:    */ import java.text.ParseException;
/*   6:    */ import java.text.SimpleDateFormat;
/*   7:    */ import java.util.ArrayList;
/*   8:    */ import java.util.Calendar;
/*   9:    */ import java.util.GregorianCalendar;
/*  10:    */ import java.util.HashMap;
/*  11:    */ import org.apache.commons.logging.Log;
/*  12:    */ import org.apache.commons.logging.LogFactory;
/*  13:    */ import weaver.conn.RecordSet;
/*  14:    */ 
/*  15:    */ public class JiaBanService
/*  16:    */ {
/*  17: 18 */   private static Log log = LogFactory.getLog(QingJia.class.getName());
/*  18: 19 */   private RecordSet rs = new RecordSet();
/*  19: 21 */   private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  20:    */   
/*  21:    */   public HashMap<String, ArrayList<JiaBan1>> getAllJiaBanRecordByUserCode(String startDate, String endDate, String UserCode)
/*  22:    */     throws ParseException
/*  23:    */   {
/*  24: 34 */     HashMap<String, ArrayList<JiaBan1>> JiaBanMap = new HashMap();
/*  25:    */     
/*  26: 36 */     String sql = "select id from HrmResource where workcode = '" + UserCode + "'";
/*  27:    */     
/*  28: 38 */     this.rs = new RecordSet();
/*  29: 39 */     int userId = -1;
/*  30: 40 */     this.rs.executeSql(sql);
/*  31: 42 */     while (this.rs.next()) {
/*  32: 43 */       userId = this.rs.getInt("id");
/*  33:    */     }
/*  34: 47 */     Calendar start = new GregorianCalendar();
/*  35: 48 */     start.setTime(this.sdf.parse(startDate));
/*  36:    */     
/*  37: 50 */     Calendar end = new GregorianCalendar();
/*  38: 51 */     end.setTime(this.sdf.parse(endDate));
/*  39: 53 */     while (start.compareTo(end) < 0)
/*  40:    */     {
/*  41: 54 */       ArrayList<JiaBan1> jiaBanList = new ArrayList();
/*  42: 55 */       jiaBanList = queryList(String.valueOf(userId), this.sdf.format(start.getTime()));
/*  43: 57 */       if (jiaBanList.size() > 0) {
/*  44: 58 */         JiaBanMap.put(this.sdf.format(start.getTime()), jiaBanList);
/*  45:    */       }
/*  46: 61 */       start.add(5, 1);
/*  47:    */     }
/*  48: 64 */     return JiaBanMap;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public ArrayList<JiaBan1> queryList(String userid, String jbrq)
/*  52:    */   {
/*  53: 74 */     ArrayList<JiaBan1> aJB = new ArrayList();
/*  54:    */     
/*  55: 76 */     String sql = "select * from formtable_main_94 left JOIN formtable_main_94_dt1 on formtable_main_94.id=formtable_main_94_dt1.mainid";
/*  56: 77 */     sql = sql + "  where formtable_main_94.proposer='" + userid;
/*  57: 78 */     sql = sql + "' and formtable_main_94_dt1.jbrq='" + jbrq + "'";
/*  58:    */     
/*  59: 80 */     RecordSet rs = new RecordSet();
/*  60: 81 */     rs.executeSql(sql);
/*  61: 83 */     while (rs.next())
/*  62:    */     {
/*  63: 84 */       JiaBan1 oJB = new JiaBan1();
/*  64: 85 */       oJB.setHdgs(rs.getDouble("hdgs"));
/*  65: 86 */       oJB.setHdjssj(rs.getString("hdjssj"));
/*  66: 87 */       oJB.setHdkssj(rs.getString("hdkssj"));
/*  67: 88 */       oJB.setJbrq(rs.getString("jbrq"));
/*  68: 89 */       oJB.setJbxs(rs.getDouble("jbxs"));
/*  69: 90 */       oJB.setMainid(rs.getInt("mainid"));
/*  70: 91 */       oJB.setSfztx(rs.getInt("sfztx"));
/*  71: 92 */       oJB.setXq(rs.getString("xq"));
/*  72: 93 */       oJB.setXxsj(rs.getDouble("xxsj"));
/*  73: 94 */       oJB.setYjjssj(rs.getString("yjjssj"));
/*  74: 95 */       oJB.setYxgs(rs.getDouble("yxgs"));
/*  75: 96 */       oJB.setYjkssj(rs.getString("yjkssj"));
/*  76: 97 */       oJB.setId(rs.getInt("id1"));
/*  77: 98 */       oJB.setHdzt(rs.getInt("hdzt"));
/*  78: 99 */       aJB.add(oJB);
/*  79:    */     }
/*  80:102 */     return aJB;
/*  81:    */   }
/*  82:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.service.JiaBanService
 * JD-Core Version:    0.7.0.1
 */