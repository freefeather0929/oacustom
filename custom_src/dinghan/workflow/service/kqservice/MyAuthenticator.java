/*  1:   */ package dinghan.workflow.service.kqservice;
/*  2:   */ 
/*  3:   */ import javax.mail.Authenticator;
/*  4:   */ import javax.mail.PasswordAuthentication;
/*  5:   */ 
/*  6:   */ public class MyAuthenticator
/*  7:   */   extends Authenticator
/*  8:   */ {
/*  9: 8 */   private String userName = null;
/* 10: 9 */   private String password = null;
/* 11:   */   
/* 12:   */   public MyAuthenticator() {}
/* 13:   */   
/* 14:   */   public MyAuthenticator(String username, String password)
/* 15:   */   {
/* 16:14 */     this.userName = username;
/* 17:15 */     this.password = password;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public PasswordAuthentication getPasswordAuthentication()
/* 21:   */   {
/* 22:18 */     return new PasswordAuthentication(this.userName, this.password);
/* 23:   */   }
/* 24:   */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.service.kqservice.MyAuthenticator
 * JD-Core Version:    0.7.0.1
 */