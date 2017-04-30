package com.imooc.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class Dom4j implements XmlDocument {

	@Test
	@Override
	public void test() {
		createXml("employees.xml");
		createMyXml("rssnews.xml");
		parseXml("employees.xml");
	}

	@Override
	public void createXml(String fileName) {
		// 1.创建document对象，代表整个xml文档
		Document document = DocumentHelper.createDocument();
		// 2.创建根节点
		Element employees = document.addElement("employees");
		// 3.生成子节点及节点内容
		Element employee = employees.addElement("employee");
		Element name = employee.addElement("name");
		name.setText("madison");
		Element sex = employee.addElement("sex");
		sex.setText("男");
		Element age = employee.addElement("age");
		age.setText("18");

		// 5.设置生成xml的格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		try {
			File file = new File(fileName);
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(file), format);
			// Writer fileWriter = new FileWriter(fileName);
			// XMLWriter xmlWriter = new XMLWriter(fileWriter,format);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createMyXml(String fileName) {
		// 1.创建document对象，代表整个xml文档
		Document document = DocumentHelper.createDocument();
		// 2.创建根节点rss
		Element rss = document.addElement("rss");
		// 3.向rss节点中添加version属性
		rss.addAttribute("version", "2.0");
		// 4.生成子节点及节点内容
		Element channel = rss.addElement("channel");
		Element title = channel.addElement("title");
		title.setText("<![CDATA[上海移动互联网产业促进中心正式揭牌 ]]>");
		// 5.设置生成xml的格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");
		// 6.生成xml文件
		File file = new File(fileName);
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			// 设置是否转义，默认值是true，代表转义
			writer.setEscapeText(false);
			writer.write(document);
			writer.close();
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
