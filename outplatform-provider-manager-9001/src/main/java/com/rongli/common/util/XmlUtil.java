package com.rongli.common.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.MessageDigest;
import java.util.*;
public class XmlUtil {
    public static String getNodeText(Document doc,String path)  //throws Exception
    {
        if ( (doc == null) || (path == null)) {
            return "";
        }
        Element element = (Element) doc.selectSingleNode(path);
        if (element != null) {
            String temp = element.getText();
            return temp.replaceAll(String.valueOf( (char) 10), "").
                replaceAll(String.valueOf( (char) 9), "").trim();
        }
        else {
            return "";
        }

    }
    
    public Document addNode(Document doc, String path, String nodeValue) throws Exception {

        String array[] = path.split("/");
        //取root结点name
        String temp = path.substring(1);
        String rootName = temp.substring(0, temp.indexOf("/"));
        // 取子结点
        String nodeName = path.substring(path.lastIndexOf("/") + 1);
     
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
    
    public static Document xml2Doc(String xml) {
    	 Document doc = null;
         try{
         	
         	doc = DocumentHelper.parseText(xml);//这种不会乱码
             //返回码
            return doc;
         }catch(Exception e){
            throw new RuntimeException("xml解析异常:"+e.getMessage());
         }
    }
}
