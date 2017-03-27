/*  1:   */ package dinghan.workflow.action;
/*  2:   */ 
/*  3:   */ import dinghan.workflow.beans.Jbtemp;
/*  4:   */ import dinghan.workflow.beans.JiaBan;
/*  5:   */ import dinghan.workflow.beans.JiaBan1;
/*  6:   */ import dinghan.workflow.beans.UserInfo;
/*  7:   */ import java.util.ArrayList;
/*  8:   */ import org.apache.commons.logging.Log;
/*  9:   */ import org.apache.commons.logging.LogFactory;
/* 10:   */ import weaver.interfaces.workflow.action.Action;
/* 11:   */ import weaver.soa.workflow.request.RequestInfo;
/* 12:   */ import weaver.workflow.request.RequestManager;
/* 13:   */ 
/* 14:   */ public class JbGenerateDetail
/* 15:   */   implements Action
/* 16:   */ {
/* 17:16 */   private Log log = LogFactory.getLog(YcGenerateDetail.class.getName());
/* 18:   */   
/* 19:   */   public Log getLog()
/* 20:   */   {
/* 21:19 */     return this.log;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void setLog(Log log)
/* 25:   */   {
/* 26:23 */     this.log = log;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public String execute(RequestInfo request)
/* 30:   */   {
/* 31:29 */     String returninfo = "0";
/* 32:   */     try
/* 33:   */     {
/* 34:32 */       String requestid = request.getRequestid();
/* 35:   */       
/* 36:34 */       int nodeid = request.getRequestManager().getNodeid();
/* 37:   */       
/* 38:36 */       JiaBan jb_main = new JiaBan(requestid);
/* 39:38 */       if ((nodeid != 602) && (nodeid != 601))
/* 40:   */       {
/* 41:39 */         JiaBan1.update(jb_main.getId(), 0);
/* 42:   */       }
/* 43:40 */       else if ((nodeid == 602) || (nodeid == 601))
/* 44:   */       {
/* 45:41 */         JiaBan1.update(jb_main.getId(), 2);
/* 46:   */         
/* 47:43 */         UserInfo userInfo = new UserInfo(jb_main.getProposer());
/* 48:   */         
/* 49:45 */         ArrayList<JiaBan1> jb_onelist = JiaBan1.queryList(jb_main
/* 50:46 */           .getId());
/* 51:48 */         for (int i = 0; i < jb_onelist.size(); i++)
/* 52:   */         {
/* 53:49 */           JiaBan1 jb_one = (JiaBan1)jb_onelist.get(i);
/* 54:   */           
/* 55:51 */           Jbtemp jbtemp = new Jbtemp();
/* 56:52 */           jbtemp.setDataid(String.valueOf(jb_main.getRequestId()));
/* 57:53 */           jbtemp.setFlowno(jb_main.getSqdh());
/* 58:54 */           jbtemp.setHdgs(jb_one.getHdgs());
/* 59:55 */           jbtemp.setHrmid(jb_main.getProposer());
/* 60:56 */           jbtemp.setHrmno(userInfo.getCode());
/* 61:   */           
/* 62:58 */           int jbdd = jb_main.getJbgzdd();
/* 63:59 */           if (jbdd == 0) {
/* 64:60 */             jbtemp.setJbdd("北京总部");
/* 65:61 */           } else if (jbdd == 1) {
/* 66:62 */             jbtemp.setJbdd("深圳分部");
/* 67:63 */           } else if (jbdd == 2) {
/* 68:64 */             jbtemp.setJbdd("东莞生产基地");
/* 69:65 */           } else if (jbdd == 3) {
/* 70:66 */             jbtemp.setJbdd("检测公司");
/* 71:   */           } else {
/* 72:68 */             jbtemp.setJbdd("其他");
/* 73:   */           }
/* 74:70 */           jbtemp.setJbrq(jb_one.getJbrq());
/* 75:71 */           jbtemp.setJbxs(jb_one.getJbxs());
/* 76:   */           
/* 77:73 */           jbtemp.setMainid(String.valueOf(jb_main.getId()));
/* 78:74 */           jbtemp.setSfztx(jb_one.getSfztx());
/* 79:75 */           jbtemp.setXq(jb_one.getXq());
/* 80:76 */           jbtemp.setXxsj(jb_one.getXxsj());
/* 81:77 */           jbtemp.setYxgs(jb_one.getYxgs());
/* 82:   */           
/* 83:79 */           Jbtemp.delete(jb_main.getId(), jb_one.getJbrq());
/* 84:   */           
/* 85:81 */           jbtemp.insert();
/* 86:   */         }
/* 87:   */       }
/* 88:86 */       returninfo = "1";
/* 89:   */     }
/* 90:   */     catch (Exception e)
/* 91:   */     {
/* 92:89 */       this.log.error("修改异常核定状态出错：" + e);
/* 93:   */     }
/* 94:   */     finally
/* 95:   */     {
/* 96:91 */       return returninfo;
/* 97:   */     }
/* 98:   */   }
/* 99:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.action.JbGenerateDetail
 * JD-Core Version:    0.7.0.1
 */