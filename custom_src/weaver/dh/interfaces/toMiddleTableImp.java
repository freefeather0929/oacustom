/*  1:   */ package weaver.dh.interfaces;
/*  2:   */ 
/*  3:   */ import java.util.Iterator;
/*  4:   */ import java.util.List;
/*  5:   */ import org.dom4j.Document;
/*  6:   */ import org.dom4j.DocumentHelper;
/*  7:   */ import org.dom4j.Element;
/*  8:   */ import weaver.conn.RecordSet;
/*  9:   */ import weaver.general.BaseBean;
/* 10:   */ 
/* 11:   */ public class toMiddleTableImp
/* 12:   */   implements toMiddleTable
/* 13:   */ {
/* 14:22 */   BaseBean bs = new BaseBean();
/* 15:   */   
/* 16:   */   public void execute(String xml, String tableName, String flag)
/* 17:   */   {
/* 18:   */     try
/* 19:   */     {
/* 20:27 */       this.bs.writeLog(" 接口XML " + xml + " ");
/* 21:   */       
/* 22:29 */       Document doc = DocumentHelper.parseText(xml);
/* 23:   */       
/* 24:   */ 
/* 25:   */ 
/* 26:   */ 
/* 27:   */ 
/* 28:   */ 
/* 29:   */ 
/* 30:   */ 
/* 31:   */ 
/* 32:39 */       Element root = doc.getRootElement();
/* 33:40 */       List<Element> childList = root.elements();
/* 34:41 */       for (int i = 0; i < childList.size(); i++)
/* 35:   */       {
/* 36:42 */         RecordSet rst = new RecordSet();
/* 37:43 */         String insertSqlBase = "insert into " + tableName + "(";
/* 38:44 */         String insertSqlBaseValue = "values(";
/* 39:45 */         Iterator fields = ((Element)childList.get(i)).elementIterator();
/* 40:46 */         while (fields.hasNext())
/* 41:   */         {
/* 42:47 */           Element Field = (Element)fields.next();
/* 43:48 */           String ColName = Field.attributeValue("ColName");
/* 44:49 */           String Value = Field.attributeValue("Value");
/* 45:50 */           insertSqlBase = insertSqlBase + ColName + ",";
/* 46:51 */           insertSqlBaseValue = insertSqlBaseValue + "'" + Value + "',";
/* 47:   */         }
/* 48:53 */         if (insertSqlBase.endsWith(",")) {
/* 49:54 */           insertSqlBase = insertSqlBase.substring(0, insertSqlBase.length() - 1) + ")";
/* 50:   */         }
/* 51:55 */         if (insertSqlBaseValue.endsWith(",")) {
/* 52:56 */           insertSqlBaseValue = insertSqlBaseValue.substring(0, insertSqlBaseValue.length() - 1) + ")";
/* 53:   */         }
/* 54:58 */         this.bs.writeLog("executeSqlBase " + insertSqlBase + " " + insertSqlBaseValue);
/* 55:59 */         rst.executeSql(insertSqlBase + " " + insertSqlBaseValue);
/* 56:   */       }
/* 57:   */     }
/* 58:   */     catch (Exception e)
/* 59:   */     {
/* 60:65 */       e.printStackTrace();
/* 61:   */     }
/* 62:   */   }
/* 63:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     weaver.dh.interfaces.toMiddleTableImp
 * JD-Core Version:    0.7.0.1
 */