package com.rongli.common.utils;

import org.dom4j.Document;
import org.dom4j.Element;

public class XmlUtil {

    public static String getNodeText(Document doc,String path)  //throws Exception
    {
        if ( (doc == null) || (path == null)) {
            return "";
        }
        Element element = (Element) doc.selectSingleNode(path);
        if (element != null) {
            String temp = element.getText();
          
            System.out.println("取出的节点："+path+"="+temp);
            return temp.replaceAll(String.valueOf( (char) 10), "").
                replaceAll(String.valueOf( (char) 9), "").trim();
        }
        else {
            return "";
        }

    }
    
    public static Document addNode(Document doc, String path, String nodeValue) throws Exception {
        String array[] = path.split("/");
        //取root结点name
        String temp = path.substring(1);
        String rootName = temp.substring(0, temp.indexOf("/"));
        // 取子结点
        String nodeName = path.substring(path.lastIndexOf("/") + 1);
        
        System.out.println("打包节点："+nodeName +"="+ nodeValue);

        String temppath = "";
        Element parentNode = (Element) doc.selectSingleNode("/" + rootName);
        for (int i = 1; i < array.length; i++) {
            if (array[i].equals(nodeName)) {
                parentNode.addElement(array[i]).setText(nodeValue);
                break;
            }
            else {
                temppath += "/" + array[i];
                Element node = (Element) doc.selectSingleNode(temppath);
                if (node == null) {
                    parentNode.addElement(array[i]);
                    parentNode = (Element) doc.selectSingleNode(temppath);
                }
                else {
                    parentNode = (Element) doc.selectSingleNode(temppath);
                }
            }
        }
        return doc;

    }
 	
}
