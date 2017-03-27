/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Date;
/*   5:    */ import weaver.conn.RecordSet;
/*   6:    */ 
/*   7:    */ public class DaKaRecord
/*   8:    */ {
/*   9:    */   private String code;
/*  10:    */   private String date;
/*  11:    */   private String firsttime;
/*  12:    */   private String lasttime;
/*  13:    */   private ArrayList<String> dk_list;
/*  14:    */   
/*  15:    */   public DaKaRecord() {}
/*  16:    */   
/*  17:    */   public DaKaRecord(String code, String date)
/*  18:    */     throws Exception
/*  19:    */   {
/*  20:    */     try
/*  21:    */     {
/*  22: 29 */       Date endDate = PublicVariant.StrToDate(date, "yyyy-MM-dd");
/*  23: 30 */       endDate = PublicVariant.AdjustDateTime(endDate, new int[] { 0, 0, 1 });
/*  24: 31 */       String sql = "SELECT * FROM vwCheckTime  WHERE Code='" + code + 
/*  25: 32 */         "' AND Date BETWEEN '" + date + "' AND '";
/*  26: 33 */       sql = sql + PublicVariant.DateToStr(endDate, "yyyy-MM-dd") + 
/*  27: 34 */         "' order BY Date,Time ";
/*  28:    */       
/*  29: 36 */       RecordSet rs = new RecordSet();
/*  30: 37 */       rs.executeSqlWithDataSource(sql, "kqj");
/*  31:    */       
/*  32: 39 */       String stuH = "02:00:00";
/*  33: 40 */       ArrayList<String> list = new ArrayList();
/*  34: 41 */       while (rs.next())
/*  35:    */       {
/*  36: 43 */         this.code = rs.getString("Code");
/*  37: 44 */         this.date = date;
/*  38: 45 */         if (date.equalsIgnoreCase(rs.getString("Date")))
/*  39:    */         {
/*  40: 46 */           String time = rs.getString("Time");
/*  41: 47 */           time = time.substring(0, 5);
/*  42: 48 */           list.add(time);
/*  43:    */         }
/*  44: 52 */         else if (rs.getString("Time").trim().compareTo(stuH) < 0)
/*  45:    */         {
/*  46: 53 */           list.add("23:59");
/*  47:    */         }
/*  48:    */       }
/*  49: 57 */       this.dk_list = list;
/*  50: 58 */       if (!list.isEmpty())
/*  51:    */       {
/*  52: 60 */         this.firsttime = ((String)list.get(0));
/*  53: 61 */         this.lasttime = ((String)list.get(list.size() - 1));
/*  54:    */       }
/*  55:    */     }
/*  56:    */     catch (Exception e)
/*  57:    */     {
/*  58: 66 */       throw e;
/*  59:    */     }
/*  60:    */   }
/*  61:    */   
/*  62:    */   public String getCode()
/*  63:    */   {
/*  64: 71 */     return this.code;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void setCode(String code)
/*  68:    */   {
/*  69: 75 */     this.code = code;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public String getDate()
/*  73:    */   {
/*  74: 79 */     return this.date;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void setDate(String date)
/*  78:    */   {
/*  79: 83 */     this.date = date;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public String getFirsttime()
/*  83:    */   {
/*  84: 87 */     return this.firsttime;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void setFirsttime(String firsttime)
/*  88:    */   {
/*  89: 91 */     this.firsttime = firsttime;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public String getLasttime()
/*  93:    */   {
/*  94: 95 */     return this.lasttime;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void setLasttime(String lasttime)
/*  98:    */   {
/*  99: 99 */     this.lasttime = lasttime;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public ArrayList<String> getDk_list()
/* 103:    */   {
/* 104:103 */     return this.dk_list;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void setDk_list(ArrayList<String> dk_list)
/* 108:    */   {
/* 109:107 */     this.dk_list = dk_list;
/* 110:    */   }
/* 111:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.DaKaRecord
 * JD-Core Version:    0.7.0.1
 */