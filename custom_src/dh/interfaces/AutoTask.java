/*  1:   */ package weaver.dh.interfaces;
/*  2:   */ 
/*  3:   */ import dinghan.workflow.beans.ChuChai2;
/*  4:   */ import dinghan.workflow.beans.Jbtemp;
/*  5:   */ import dinghan.workflow.beans.JiaBan1;
/*  6:   */ import dinghan.workflow.beans.QingJia;
/*  7:   */ import dinghan.workflow.beans.WaiChu;
/*  8:   */ import org.apache.commons.logging.Log;
/*  9:   */ import org.apache.commons.logging.LogFactory;
/* 10:   */ import weaver.interfaces.schedule.BaseCronJob;
/* 11:   */ 
/* 12:   */ public class AutoTask
/* 13:   */   extends BaseCronJob
/* 14:   */ {
/* 15:14 */   private Log log = LogFactory.getLog(AutoTask.class.getName());
/* 16:   */   
/* 17:   */   public Log getLog()
/* 18:   */   {
/* 19:17 */     return this.log;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void setLog(Log log)
/* 23:   */   {
/* 24:21 */     this.log = log;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void execute()
/* 28:   */   {
/* 29:   */     try
/* 30:   */     {
/* 31:27 */       this.log.error("核定明细开始");
/* 32:28 */       QingJia.checkList(0);
/* 33:29 */       ChuChai2.checkList(0);
/* 34:30 */       WaiChu.checkList(0);
/* 35:31 */       JiaBan1.checkList(0);
/* 36:32 */       Jbtemp.insertJb();
/* 37:   */     }
/* 38:   */     catch (Exception e)
/* 39:   */     {
/* 40:35 */       e.printStackTrace();
/* 41:   */     }
/* 42:   */   }
/* 43:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\dh\interfaces\
 * Qualified Name:     weaver.dh.interfaces.AutoTask
 * JD-Core Version:    0.7.0.1
 */