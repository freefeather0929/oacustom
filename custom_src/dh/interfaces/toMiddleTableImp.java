package weaver.dh.interfaces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.Util;

public class toMiddleTableImp implements toMiddleTable {
	BaseBean bs = new BaseBean();

	@Override
	public void execute(String xml,String tableName, String flag) {
		try{
		bs.writeLog(" 接口XML "+xml+" ");

		 Document doc = DocumentHelper.parseText(xml);
        /* OutputFormat format = OutputFormat.createPrettyPrint();  
         //设置编码  
         format.setEncoding("UTF-8");  
         //XMLWriter 指定输出文件以及格式  
         XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(new File("/synHr.xml")),"UTF-8"), format);  
         //写入新文件  
         writer.write(doc);  
         writer.flush();  
         writer.close(); */
		 Element root = doc.getRootElement();
		 List<Element> childList = root.elements();
		 for(int i=0;i<childList.size();i++){
			 RecordSet rst = new RecordSet();
			 String insertSqlBase ="insert into "+tableName+"(";
			 String insertSqlBaseValue ="values(";
			 Iterator fields =  childList.get(i).elementIterator();
			 while(fields.hasNext()){
				 Element Field = (Element)fields.next();
				 String ColName = Field.attributeValue("ColName");
				 String Value = Field.attributeValue("Value");
				 insertSqlBase+=ColName+",";
				 insertSqlBaseValue += "'"+Value+"',";
			 }
			if(insertSqlBase.endsWith(",")){
				insertSqlBase= insertSqlBase.substring(0, insertSqlBase.length()-1)+")";
			}if(insertSqlBaseValue.endsWith(",")){
				insertSqlBaseValue = insertSqlBaseValue.substring(0, insertSqlBaseValue.length()-1)+")";
			}
			bs.writeLog("executeSqlBase "+insertSqlBase+" "+insertSqlBaseValue);
			rst.executeSql(insertSqlBase+" "+insertSqlBaseValue);
			
		/*	ModeRightInfo ModeRightInfo = new ModeRightInfo();
			ModeRightInfo.editModeDataShare(Util.getIntValue(RequestInfo.getCreatorid(),0),modeid,m_billid);*/
		 }
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	}

	

}
