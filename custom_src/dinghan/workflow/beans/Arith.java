/*   1:    */ package dinghan.workflow.beans;
/*   2:    */ 
/*   3:    */ import java.math.BigDecimal;
/*   4:    */ 
/*   5:    */ public class Arith
/*   6:    */ {
/*   7:    */   private static final int DEF_DIV_SCALE = 10;
/*   8:    */   
/*   9:    */   public static double add(double v1, double v2)
/*  10:    */   {
/*  11: 24 */     BigDecimal b1 = new BigDecimal(Double.toString(v1));
/*  12: 25 */     BigDecimal b2 = new BigDecimal(Double.toString(v2));
/*  13: 26 */     return b1.add(b2).doubleValue();
/*  14:    */   }
/*  15:    */   
/*  16:    */   public static double sub(double v1, double v2)
/*  17:    */   {
/*  18: 40 */     BigDecimal b1 = new BigDecimal(Double.toString(v1));
/*  19: 41 */     BigDecimal b2 = new BigDecimal(Double.toString(v2));
/*  20: 42 */     return b1.subtract(b2).doubleValue();
/*  21:    */   }
/*  22:    */   
/*  23:    */   public static double mul(double v1, double v2)
/*  24:    */   {
/*  25: 56 */     BigDecimal b1 = new BigDecimal(Double.toString(v1));
/*  26: 57 */     BigDecimal b2 = new BigDecimal(Double.toString(v2));
/*  27: 58 */     return b1.multiply(b2).doubleValue();
/*  28:    */   }
/*  29:    */   
/*  30:    */   public static double div(double v1, double v2)
/*  31:    */   {
/*  32: 72 */     return div(v1, v2, 10);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public static double div(double v1, double v2, int scale)
/*  36:    */   {
/*  37: 88 */     if (scale < 0) {
/*  38: 89 */       throw new IllegalArgumentException(
/*  39: 90 */         "The scale must be a positive integer or zero");
/*  40:    */     }
/*  41: 92 */     BigDecimal b1 = new BigDecimal(Double.toString(v1));
/*  42: 93 */     BigDecimal b2 = new BigDecimal(Double.toString(v2));
/*  43: 94 */     return b1.divide(b2, scale, 4).doubleValue();
/*  44:    */   }
/*  45:    */   
/*  46:    */   public static double round(double v, int scale)
/*  47:    */   {
/*  48:108 */     if (scale < 0) {
/*  49:109 */       throw new IllegalArgumentException(
/*  50:110 */         "The scale must be a positive integer or zero");
/*  51:    */     }
/*  52:112 */     BigDecimal b = new BigDecimal(Double.toString(v));
/*  53:113 */     BigDecimal one = new BigDecimal("1");
/*  54:114 */     return b.divide(one, scale, 4).doubleValue();
/*  55:    */   }
/*  56:    */ }


/* Location:           F:\oa_back\oacustom\custom_class\
 * Qualified Name:     dinghan.workflow.beans.Arith
 * JD-Core Version:    0.7.0.1
 */