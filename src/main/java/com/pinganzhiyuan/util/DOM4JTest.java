package com.pinganzhiyuan.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fasterxml.jackson.core.sym.Name;

public class DOM4JTest {
	
	
	 public static void main(String[] args) {
        // 解析books.xml文件
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try {
            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
            Document document = reader.read(new File("/Users/nijun/Desktop/androidManifest.xml"));
            // 通过document对象获取根节点bookstore
            Element bookStore = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = bookStore.elementIterator();
            // 遍历迭代器，获取根节点中的信息（书籍）
            while (it.hasNext()) {
//	             System.out.println("=====开始遍历某一本书=====");
             Element book = (Element) it.next();
             // 获取book的属性名以及 属性值
             List<Attribute> bookAttrs = book.attributes();
             for (Attribute attr : bookAttrs) {
              System.out.println("属性名：" + attr.getName() + "--属性值：" + attr.getValue());
             }
             //解析子节点的信息
             Iterator itt = book.elementIterator();
             while (itt.hasNext()) {
              Element bookChild = (Element) itt.next();
              if (bookChild != null) {
            	  		if (bookChild.attribute(0) != null && bookChild.attribute(1) != null) {
            	  			String txt = "name: " + bookChild.attribute(0).getValue() + ", value: " + bookChild.attribute(1).getValue(); 
	            	  		System.out.println(txt);
            	  		}
              }
              
              System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
             }
//	             System.out.println("=====结束遍历某一本书=====");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
	 }
}
