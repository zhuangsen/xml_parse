package com.imooc.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class Dom4j implements XmlDocument {
	
	@Test
	@Override
	public void test(){
		createXml("");
		parseXml("");
	}

	@Override
	public void createXml(String fileName) {
		Document document = DocumentHelper.createDocument();
		Element employees = document.addElement("employees");
		Element employee = employees.addElement("employee");
		Element name = employee.addElement("name");
		name.setText("madison");
		Element sex = employee.addElement("sex");
		sex.setText("男");
		Element age = employee.addElement("age");
		age.setText("18");
		try {
			Writer fileWriter = new FileWriter(fileName);
			XMLWriter xmlWriter = new XMLWriter(fileWriter);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void parseXml(String fileName) {
		// 解析books.xml文件
		// 创建SAXReader的对象reader
		SAXReader reader = new SAXReader();
		try {
			// 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
			Document document = reader.read(new File(fileName));
			// 通过document对象获取根节点bookstore
			Element bookStore = document.getRootElement();
			// 通过element对象的elementIterator方法获取迭代器
			Iterator<?> it = bookStore.elementIterator();
			// 遍历迭代器，获取根节点中的信息（书籍）
			while (it.hasNext()) {
				System.out.println("=====开始遍历某一本书=====");
				Element book = (Element) it.next();
				// 获取book的属性名以及 属性值
				List<Attribute> bookAttrs = book.attributes();
				for (Attribute attr : bookAttrs) {
					System.out.println("属性名：" + attr.getName() + "--属性值：" + attr.getValue());
				}
				Iterator<?> itt = book.elementIterator();
				while (itt.hasNext()) {
					Element bookChild = (Element) itt.next();
					System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
				}
				System.out.println("=====结束遍历某一本书=====");
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

}
