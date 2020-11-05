package com.rongli.service;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rongli.common.utils.XmlUtil;
import com.rongli.entities.ResultBody;
import com.rongli.entities.enums.DataTypeEnum;
import com.rongli.entities.params.Code;
import com.rongli.entities.params.Input;
import com.rongli.entities.params.Output;
import com.rongli.entities.params.RetCodeRuler;
import com.rongli.entities.params.ServiceEntity;
import com.rongli.exception.BaseException;
import com.rongli.service.imp.InputDicHandleServiceImp;
import com.rongli.service.imp.OutputDicHandleServiceImp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataHandleService {

	public String conversionInput(ServiceEntity serviceEntity,JSONObject recv,JSONObject dbData) throws Exception{
		String ret="";
		
		if(serviceEntity==null)
			throw new BaseException("服务模板为空");
		
		
		Input input=JSON.parseObject(serviceEntity.getInput(), Input.class) ;
		if(input==null)
			throw new BaseException("输入参数模板为空");
		
		if(recv==null)
			throw new BaseException("接收数据为空");
		
		String datatype=serviceEntity.getDataType();
		if(StringUtils.isEmpty(datatype))
			throw new BaseException("数据类型为空");
		
		//serviceEntity.setFromThirdData(recv.toJSONString());
		
		List<Code> titles=input.getTitles();
		
		String recordRoot=input.getRecordRoot();
		
		if(titles.isEmpty())
			throw new BaseException("输入参数模板单节点记录集为空");
		
		if(datatype.equals(DataTypeEnum.JSON.getCode())) {
			JSONObject obj=new JSONObject();
			for(Code code:titles) {
				String val="";
	        	String val_default=code.getValue()==null?"":code.getValue();
				
				val=recv.getString(code.getCodeRuler());
				if(StringUtils.isEmpty(val))
					val=val_default;
				
				Boolean notNull=(code.getNotNull()==null?false:true);
				if(notNull&&StringUtils.isEmpty(val))//校验必填项目
					throw new BaseException("入参["+code.getCodeRuler()+"]为必填字段");
				
				if(val.length()>code.getLength()) //校验长度
					throw new BaseException("入参["+code.getCodeRuler()+"]长度大于"+code.getLength());
				
				if(!StringUtils.isEmpty(code.getDbfield())) //保存需要存储数据库的字段json
					dbData.put(code.getDbfield(), val);
				
				
				if(!StringUtils.isEmpty(code.getChangeRuler())) { //不为空才添加
					if(!StringUtils.isEmpty(code.getFunction())) { //函数反射
						val=this.inputInvoke(code.getFunction(), val);
						val=(val==null?"":val);
					}
					obj.put(code.getChangeRuler(), val);
				}
				
			}
			
			if(!StringUtils.isEmpty(recordRoot)) {//根节点不能为空
				JSONArray array=new JSONArray();
	        	 for(int i=0;i<recv.getJSONArray("records").size();i++) {
	        		 JSONObject record=recv.getJSONArray("records").getJSONObject(i);
	        		 List<Code> recordList=input.getRecords();
	        		 
	        		 JSONObject data=new JSONObject();
	        		 for(Code code:recordList) {
	        			 /***************添加记录集*****************/

		        		if(record.getString(code.getCodeRuler()).length()>code.getLength())
		        			 throw new BaseException("多记录集节点超过指定长度");
		        		 
		        		 Boolean notNull=(code.getNotNull()==null?false:true);
		        		 
		        		 String val=record.getString(code.getCodeRuler());
		        		 
		 				if(notNull&&StringUtils.isEmpty(val))//校验必填项目
		 					throw new BaseException("入参["+code.getCodeRuler()+"]为必填字段");
		 				
		        		 
		 				data.put(code.getChangeRuler(), val);
	        		 }
	        		 
	        		 array.add(data);
	             }
	        	 obj.put(recordRoot, array);
	        	
	        }
			 ret=obj.toJSONString();
		}else if(datatype.equals(DataTypeEnum.XML.getCode())) {
			SAXReader saxb = new SAXReader();
	        Document doc=saxb.read(new java.io.StringReader("<"+input.getReqRoot()+"></"+input.getReqRoot()+">"));
	        Element root = doc.getRootElement();
	        
	        for(Code code:titles) {
	        	String val="";
	        	String val_default=code.getValue()==null?"":code.getValue();
				
				val=recv.getString(code.getCodeRuler());
				if(StringUtils.isEmpty(val))
					val=val_default;

				Boolean notNull=code.getNotNull()==null?false:code.getNotNull();
				if(notNull&&StringUtils.isEmpty(val))//校验必填项目
					throw new BaseException("入参["+code.getCodeRuler()+"]为必填字段");
				
				if(val.length()>code.getLength()) //校验长度
					throw new BaseException("入参["+code.getCodeRuler()+"]长度大于"+code.getLength());
				
				if(!StringUtils.isEmpty(code.getDbfield())) //保存需要存储数据库的字段json
					dbData.put(code.getDbfield(), val);
				
				if(code.getChangeRuler().startsWith("/")) { //只有第一位为/才添加
					if(!StringUtils.isEmpty(code.getFunction())) { //函数反射
						val=this.inputInvoke(code.getFunction(), val);
						val=(val==null?"":val);
					}
					XmlUtil.addNode(doc, code.getChangeRuler(), val);
				}
				
			}
	        
	        
	        if(!StringUtils.isEmpty(recordRoot)) {//根节点不能为空
	        	 XmlUtil.addNode(doc, input.getRecordRoot(), "");
	        	 Element element=(Element) root.selectSingleNode(recordRoot);
	        	 for(int i=0;i<recv.getJSONArray("records").size();i++) {
	        		 JSONObject obj=recv.getJSONArray("records").getJSONObject(i);
	        		 List<Code> Records=input.getRecords();
	        		 if(!Records.get(0).getChangeRuler().startsWith("/")) {
	        			 throw new BaseException("Records集合规则错误:startsWith(\"/\")");
	        		 }
	        		 //System.out.println(Records.get(0).getChangeRuler().substring(1).split("/"));
	        		 if(Records.get(0).getChangeRuler().substring(1).split("/").length!=2) {
	        			 throw new BaseException("Records集合规则错误:split(\"/\").length!=2");
	        		 }
	        		 String first=Records.get(0).getChangeRuler().substring(1).split("/")[0];
	        		 /***************创建记录集*****************/
	        		 Element son_element = element.addElement(first);
	        		 
	        		 for(Code code:Records) {
	        			 /***************添加记录集*****************/
	        			 if(!code.getChangeRuler().startsWith("/")) 
		        			 throw new BaseException("Records集合规则错误:startsWith(\"/\")");
		        		 
		        		 if(code.getChangeRuler().substring(1).split("/").length!=2) 
		        			 throw new BaseException("Records集合规则错误:split(\"/\").length!=2");
		        		 
		        		 String second=code.getChangeRuler().substring(1).split("/")[1];
		        		 
		        		 if(obj.getString(code.getCodeRuler()).length()>code.getLength())
		        			 throw new BaseException("多记录集节点超过指定长度");
		        		 
	        			 son_element.addElement(second)
	                       .addText(obj.getString(code.getCodeRuler()));
	        		 }
	        		 
	    
	             }
	        }
	        doc.setRootElement(root);
	        ret=doc.asXML();
		}else {
			throw new BaseException("数据类型非法");
		}
		return ret;
	}
	
	public String conversionOutput(ServiceEntity se,String recv,JSONObject dbData) {
		if(se==null)
			throw new BaseException("服务模板为空");
		
		String ret="";
		
		Output output=JSON.parseObject(se.getOutput(), Output.class) ;
		if(output==null)
			throw new BaseException("输出参数模板为空");
		
		if(recv==null)
			throw new BaseException("接收数据为空");
		
		//se.setFromHisData(recv);
		
		String datatype=se.getDataType();
		if(StringUtils.isEmpty(datatype))
			throw new BaseException("数据类型为空");
		
		List<Code> titles=output.getTitles();
		
		List<Code> records=output.getRecords();
		
		RetCodeRuler retcode_obj=output.getRetCodeRuler();

		if(datatype.equals(DataTypeEnum.JSON.getCode())) {
			
			//返回码
			
			JSONObject json_his=JSON.parseObject(recv);
			
            String hiscode = json_his.getString(retcode_obj.getHiscode()) ;
            
            String hismsg = json_his.getString(retcode_obj.getHismsg()) ;

            if(!hiscode.equals(retcode_obj.getRightcode())) 
            	throw new BaseException(hismsg);
            
            JSONObject retObj=ResultBody.success(hismsg).toJson();
            if(titles!=null) {
	            for(Code code:titles) {
	            	String val="";
	            	if(!StringUtils.isEmpty(code.getChangeRuler())) {
	            		val=json_his.getString(code.getCodeRuler());
	            		
	            		if(!StringUtils.isEmpty(code.getFunction())) {
	            			val=this.outputInvoke(code.getFunction(), val);
	            			val=(val==null?"":val);
	            		} //函数反射
							
	            		retObj.put(code.getChangeRuler(), val);
	            	}
	            	if(!StringUtils.isEmpty(code.getDbfield())) {
	            		dbData.put(code.getDbfield(), val);
	            	}
	            }
            }
            
            if(!StringUtils.isEmpty(output.getRecordRoot())) {
            	JSONArray array=json_his.getJSONArray("records");
            	JSONArray arrat_ret=new JSONArray();
            	 for(int i=0;i<array.size();i++) {
	        		 JSONObject obj=array.getJSONObject(i);
	        		 JSONObject obj_ret=new JSONObject();
	        		 for(Code code:records) {
	        			 obj_ret.put(code.getChangeRuler(), obj.getString(code.getCodeRuler()));
	        		 }
	        		 arrat_ret.add(obj_ret) ;
            	 }
            	 retObj.put("records", arrat_ret);
            }
            ret=retObj.toString();
		}else if(datatype.equals(DataTypeEnum.XML.getCode())) {
			
			Document doc = null;
            try{
            	doc = DocumentHelper.parseText(recv);//这种不会乱码
            }catch(Exception e){
            	throw new BaseException("XML解析异常:"+e.getMessage());
            }
            //返回码
            String hiscode = XmlUtil.getNodeText(doc,retcode_obj.getHiscode());
            
            String hismsg = XmlUtil.getNodeText(doc,retcode_obj.getHismsg());
            if(!hiscode.equals(retcode_obj.getRightcode())) 
            	throw new BaseException(hismsg);
            
            JSONObject retObj=ResultBody.success(hismsg).toJson();
            if(titles!=null) {
	            for(Code code:titles) {
	            	String val="";
	            	if(!StringUtils.isEmpty(code.getChangeRuler())) {
	            		val=XmlUtil.getNodeText(doc,code.getCodeRuler());
	            		
	            		if(!StringUtils.isEmpty(code.getFunction())) {
	            			val=this.outputInvoke(code.getFunction(), val);
	            			val=(val==null?"":val);
	            		} //函数反射
							
	            		retObj.put(code.getChangeRuler(), val);
	            	}
	            	if(!StringUtils.isEmpty(code.getDbfield())) {
	            		dbData.put(code.getDbfield(), val);
	            	}
	            }
            }
            if(records!=null&&records.size()>0) {
            	String path = records.get(0).getCodeRuler();
            	if(path!=null) {
            		 path = path.substring(0,path.lastIndexOf("/")).trim();
                     List<Element> elements = doc.selectNodes(path);
                     JSONArray array=new JSONArray();
                 	for(Element et: elements){
                 		JSONObject obj=new JSONObject();
                 		for(Code code:records) {
                 			String codename=code.getCodeRuler().substring(code.getCodeRuler().lastIndexOf("/")+1).trim();
                 			String value=et.element(codename).getText();
                 			obj.put(code.getChangeRuler(), value);
                 		}
                 		array.add(obj);
                 	}
                 	retObj.put("records", array);
            	}
            }       
            ret=retObj.toString();
            
		}else {
			throw new BaseException("数据类型非法");
		}
		return ret;
	}
	

	public static void main(String[] args) throws Exception {
		SAXReader saxb = new SAXReader();
        Document doc=saxb.read(new java.io.StringReader("<Input></Input>"));
        
        Element root = doc.getRootElement();
        
        XmlUtil.addNode(doc, "/Input/InBody/Param0", "00");
        
        JSONArray array=new JSONArray();
        JSONObject obj1=new JSONObject();
        obj1.put("param0", "val0");
        obj1.put("param1", "val1");
        obj1.put("param2", "val2");
        
        JSONObject obj2=new JSONObject();
        obj2.put("param0", "val4");
        obj2.put("param1", "val5");
        obj2.put("param2", "val6");
        array.add(obj1);
        array.add(obj2);
        
        XmlUtil.addNode(doc, "/Input/InBody/Records", "");
        
        Element element=(Element) root.selectSingleNode("/Input/InBody/Records");
        for(int i=0;i<array.size();i++) {
        	Element son_element = element.addElement("Record");
        	JSONObject obj=array.getJSONObject(i);
        	  Iterator iter = obj.entrySet().iterator();
              while (iter.hasNext()) {
                  Map.Entry entry = (Map.Entry) iter.next();
                  son_element.addElement(entry.getKey().toString())
                  .addText(entry.getValue().toString());
              }
        }
        
        doc.setRootElement(root);
        

        String ret=doc.asXML();
        System.out.println(ret);
	}
	
	public String inputInvoke(String func, String param) {
		// TODO Auto-generated method stub
		try {
			Class<?> classType = InputDicHandleServiceImp.class;
	    	Object inputDicHandleService = classType.newInstance();
	    	 Method echoMethod = classType.getDeclaredMethod(func, new Class[]{String.class});
	         Object result2 = echoMethod.invoke(inputDicHandleService, new Object[]{param});
	         return (String) result2;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BaseException(func+"反射异常:"+e.getMessage());
		}
		
	}
	
	public String outputInvoke(String func, String param) {
		// TODO Auto-generated method stub
		try {
			Class<?> classType = OutputDicHandleServiceImp.class;
	    	Object OutputDicHandleServiceImp = classType.newInstance();
	    	 Method echoMethod = classType.getDeclaredMethod(func, new Class[]{String.class});
	         Object result2 = echoMethod.invoke(OutputDicHandleServiceImp, new Object[]{param});
	         return (String) result2;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BaseException(func+"反射异常:"+e.getMessage());
		}
		
		
	}
}
