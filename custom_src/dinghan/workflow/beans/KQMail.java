/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import dinghan.workflow.service.kqservice.MyAuthenticator;
/*   4:    */ import java.util.Date;
/*   5:    */ import java.util.Properties;
/*   6:    */ import javax.mail.BodyPart;
/*   7:    */ import javax.mail.Message;
/*   8:    */ import javax.mail.Message.RecipientType;
/*   9:    */ import javax.mail.MessagingException;
/*  10:    */ import javax.mail.Multipart;
/*  11:    */ import javax.mail.Session;
/*  12:    */ import javax.mail.Transport;
/*  13:    */ import javax.mail.internet.InternetAddress;
/*  14:    */ import javax.mail.internet.MimeBodyPart;
/*  15:    */ import javax.mail.internet.MimeMessage;
/*  16:    */ import javax.mail.internet.MimeMultipart;
/*  17:    */ 
/*  18:    */ public class KQMail
/*  19:    */ {
/*  20: 24 */   private String host = null;
/*  21: 25 */   private String port = "25";
/*  22: 26 */   private boolean validate = false;
/*  23: 27 */   private String[] recipients = null;
/*  24: 28 */   private String[] recipientsName = null;
/*  25: 29 */   private String subject = null;
/*  26: 30 */   private String content = null;
/*  27: 31 */   private String contentType = null;
/*  28: 32 */   private String filePath = null;
/*  29: 33 */   private String from = null;
/*  30: 34 */   private String fromName = null;
/*  31: 35 */   private String fromPwd = null;
/*  32: 36 */   private MyAuthenticator auth = null;
/*  33:    */   
/*  34:    */   public void sendMail()
/*  35:    */     throws MessagingException, Exception
/*  36:    */   {
/*  37: 41 */     if ((this.recipients != null) && (this.recipients.length > 0))
/*  38:    */     {
/*  39: 42 */       Properties props = new Properties();
/*  40:    */       
/*  41: 44 */       props.put("mail.smtp.host", this.host);
/*  42: 45 */       props.put("mail.smtp.port", this.port);
/*  43: 46 */       props.put("mail.smtp.auth", Boolean.valueOf(this.validate));
/*  44: 47 */       props.put("mail.smtp.connectiontimeout", "30000");
/*  45: 51 */       if (this.validate) {
/*  46: 52 */         this.auth = new MyAuthenticator(this.from, this.fromPwd);
/*  47:    */       }
/*  48: 55 */       Session session = Session.getDefaultInstance(props, this.auth);
/*  49:    */       
/*  50: 57 */       Message msg = new MimeMessage(session);
/*  51:    */       
/*  52: 59 */       InternetAddress addressFrom = new InternetAddress(this.from, this.fromName);
/*  53: 60 */       msg.setFrom(addressFrom);
/*  54:    */       
/*  55: 62 */       InternetAddress[] addressTo = new InternetAddress[this.recipients.length];
/*  56: 63 */       for (int i = 0; i < this.recipients.length; i++) {
/*  57: 64 */         addressTo[i] = new InternetAddress(this.recipients[i], this.recipientsName[i]);
/*  58:    */       }
/*  59: 66 */       msg.setRecipients(Message.RecipientType.TO, addressTo);
/*  60:    */       
/*  61:    */ 
/*  62: 69 */       msg.setSubject(this.subject);
/*  63: 71 */       if ((this.contentType == null) || (this.contentType.equals("text")))
/*  64:    */       {
/*  65: 72 */         msg.setText(this.content);
/*  66:    */       }
/*  67: 73 */       else if (this.contentType.equals("html"))
/*  68:    */       {
/*  69: 75 */         Multipart mmp = new MimeMultipart();
/*  70: 76 */         BodyPart mbp1 = new MimeBodyPart();
/*  71: 77 */         mbp1.setContent(this.content, "text/html;charset=utf-8");
/*  72: 78 */         mmp.addBodyPart(mbp1);
/*  73:    */         
/*  74:    */ 
/*  75:    */ 
/*  76:    */ 
/*  77:    */ 
/*  78:    */ 
/*  79:    */ 
/*  80:    */ 
/*  81:    */ 
/*  82:    */ 
/*  83:    */ 
/*  84:    */ 
/*  85:    */ 
/*  86:    */ 
/*  87:    */ 
/*  88: 94 */         msg.setContent(mmp);
/*  89:    */       }
/*  90: 98 */       msg.setSentDate(new Date());
/*  91:    */       
/*  92:100 */       Transport.send(msg);
/*  93:    */     }
/*  94:    */   }
/*  95:    */   
/*  96:    */   public String getHost()
/*  97:    */   {
/*  98:106 */     return this.host;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public void setHost(String host)
/* 102:    */   {
/* 103:110 */     this.host = host;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public String getPort()
/* 107:    */   {
/* 108:114 */     return this.port;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public void setPort(String port)
/* 112:    */   {
/* 113:118 */     this.port = port;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public boolean isValidate()
/* 117:    */   {
/* 118:122 */     return this.validate;
/* 119:    */   }
/* 120:    */   
/* 121:    */   public void setValidate(boolean validate)
/* 122:    */   {
/* 123:126 */     this.validate = validate;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public String[] getRecipients()
/* 127:    */   {
/* 128:130 */     return this.recipients;
/* 129:    */   }
/* 130:    */   
/* 131:    */   public void setRecipients(String[] recipients)
/* 132:    */   {
/* 133:134 */     this.recipients = recipients;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public String[] getRecipientsName()
/* 137:    */   {
/* 138:138 */     return this.recipientsName;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public void setRecipientsName(String[] recipientsName)
/* 142:    */   {
/* 143:142 */     this.recipientsName = recipientsName;
/* 144:    */   }
/* 145:    */   
/* 146:    */   public String getSubject()
/* 147:    */   {
/* 148:146 */     return this.subject;
/* 149:    */   }
/* 150:    */   
/* 151:    */   public void setSubject(String subject)
/* 152:    */   {
/* 153:150 */     this.subject = subject;
/* 154:    */   }
/* 155:    */   
/* 156:    */   public String getContent()
/* 157:    */   {
/* 158:154 */     return this.content;
/* 159:    */   }
/* 160:    */   
/* 161:    */   public void setContent(String content)
/* 162:    */   {
/* 163:158 */     this.content = content;
/* 164:    */   }
/* 165:    */   
/* 166:    */   public String getContentType()
/* 167:    */   {
/* 168:162 */     return this.contentType;
/* 169:    */   }
/* 170:    */   
/* 171:    */   public void setContentType(String contentType)
/* 172:    */   {
/* 173:166 */     this.contentType = contentType;
/* 174:    */   }
/* 175:    */   
/* 176:    */   public String getFilePath()
/* 177:    */   {
/* 178:170 */     return this.filePath;
/* 179:    */   }
/* 180:    */   
/* 181:    */   public void setFilePath(String filePath)
/* 182:    */   {
/* 183:174 */     this.filePath = filePath;
/* 184:    */   }
/* 185:    */   
/* 186:    */   public String getFrom()
/* 187:    */   {
/* 188:178 */     return this.from;
/* 189:    */   }
/* 190:    */   
/* 191:    */   public void setFrom(String from)
/* 192:    */   {
/* 193:182 */     this.from = from;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public String getFromName()
/* 197:    */   {
/* 198:186 */     return this.fromName;
/* 199:    */   }
/* 200:    */   
/* 201:    */   public void setFromName(String fromName)
/* 202:    */   {
/* 203:190 */     this.fromName = fromName;
/* 204:    */   }
/* 205:    */   
/* 206:    */   public String getFromPwd()
/* 207:    */   {
/* 208:194 */     return this.fromPwd;
/* 209:    */   }
/* 210:    */   
/* 211:    */   public void setFromPwd(String fromPwd)
/* 212:    */   {
/* 213:198 */     this.fromPwd = fromPwd;
/* 214:    */   }
/* 215:    */   
/* 216:    */   public MyAuthenticator getAuth()
/* 217:    */   {
/* 218:201 */     return this.auth;
/* 219:    */   }
/* 220:    */   
/* 221:    */   public void setAuth(MyAuthenticator auth)
/* 222:    */   {
/* 223:204 */     this.auth = auth;
/* 224:    */   }
/* 225:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.KQMail
 * JD-Core Version:    0.7.0.1
 */