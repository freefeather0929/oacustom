/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import weaver.conn.RecordSet;
/*   5:    */ 
/*   6:    */ public class QingJia1
/*   7:    */ {
/*   8:    */   private int id;
/*   9:    */   private int mainid;
/*  10:    */   private String ksrq;
/*  11:    */   private String jsrq;
/*  12:    */   private String kssj;
/*  13:    */   private String jssj;
/*  14:    */   private int qjlb;
/*  15:    */   private String qjsy;
/*  16:    */   
/*  17:    */   public static ArrayList<QingJia1> queryList(int mainid)
/*  18:    */     throws Exception
/*  19:    */   {
/*  20: 24 */     ArrayList<QingJia1> aQJ = new ArrayList();
/*  21: 25 */     String sql = "select * from formtable_main_89_dt1  where mainid=";
/*  22: 26 */     sql = sql + mainid + " ORDER BY ksrq,kssj";
/*  23:    */     try
/*  24:    */     {
/*  25: 28 */       RecordSet rs = new RecordSet();
/*  26: 29 */       rs.executeSql(sql);
/*  27: 30 */       QingJia1 oQj = null;
/*  28: 31 */       while (rs.next())
/*  29:    */       {
/*  30: 32 */         oQj = new QingJia1();
/*  31: 33 */         oQj.setId(rs.getInt("id"));
/*  32: 34 */         oQj.setJsrq(rs.getString("jsrq"));
/*  33: 35 */         oQj.setJssj(rs.getString("jssj"));
/*  34: 36 */         oQj.setKsrq(rs.getString("ksrq"));
/*  35: 37 */         oQj.setKssj(rs.getString("kssj"));
/*  36: 38 */         oQj.setMainid(rs.getInt("mainid"));
/*  37: 39 */         oQj.setQjlb(rs.getInt("qjlb"));
/*  38: 40 */         oQj.setQjsy(rs.getString("qjsy"));
/*  39: 41 */         aQJ.add(oQj);
/*  40:    */       }
/*  41:    */     }
/*  42:    */     catch (Exception e)
/*  43:    */     {
/*  44: 45 */       throw e;
/*  45:    */     }
/*  46: 47 */     return aQJ;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public int getId()
/*  50:    */   {
/*  51: 51 */     return this.id;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void setId(int id)
/*  55:    */   {
/*  56: 55 */     this.id = id;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public int getMainid()
/*  60:    */   {
/*  61: 59 */     return this.mainid;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void setMainid(int mainid)
/*  65:    */   {
/*  66: 63 */     this.mainid = mainid;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public String getKsrq()
/*  70:    */   {
/*  71: 67 */     return this.ksrq;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void setKsrq(String ksrq)
/*  75:    */   {
/*  76: 71 */     this.ksrq = ksrq;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public String getJsrq()
/*  80:    */   {
/*  81: 75 */     return this.jsrq;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void setJsrq(String jsrq)
/*  85:    */   {
/*  86: 79 */     this.jsrq = jsrq;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public String getKssj()
/*  90:    */   {
/*  91: 83 */     return this.kssj;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public void setKssj(String kssj)
/*  95:    */   {
/*  96: 87 */     this.kssj = kssj;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public String getJssj()
/* 100:    */   {
/* 101: 91 */     return this.jssj;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public void setJssj(String jssj)
/* 105:    */   {
/* 106: 95 */     this.jssj = jssj;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public int getQjlb()
/* 110:    */   {
/* 111: 99 */     return this.qjlb;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void setQjlb(int qjlb)
/* 115:    */   {
/* 116:103 */     this.qjlb = qjlb;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public String getQjsy()
/* 120:    */   {
/* 121:107 */     return this.qjsy;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public void setQjsy(String qjsy)
/* 125:    */   {
/* 126:111 */     this.qjsy = qjsy;
/* 127:    */   }
/* 128:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.QingJia1
 * JD-Core Version:    0.7.0.1
 */