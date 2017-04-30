package com.imooc.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Dom implements XmlDocument {
	

	@Override
	public void test() {
		createXml("");
		parseXml("");
	}

	private DocumentBuilder getDocumentBuilder() {
		// 创建一个DocumentBuilderFactory的对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 创建DocumentBuilder对象
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return db;
	}

	@Override
	public void createXml(String fileName) {
		DocumentBuilder db = getDocumentBuilder();
		Document document = db.newDocument();
		document.setXmlStandalone(true);
		Element bookstore = document.createElement("bookStore");
		// 向bookstore根节点中添加子节点book
		Element book = document.createElement("book");
		Element name = document.createElement("name");
		// name.setNodeValue("小王子");
		name.setTextContent("小王子");

		book.appendChild(name);
		book.setAttribute("id", "1");
		// 将book节点添加到bookstore根节点中
		bookstore.appendChild(book);
		// 将bookstore节点（已经包含了book）添加到dom树中
		document.appendChild(bookstore);
		// 创建TransformerFactory对象
		TransformerFactory tff = TransformerFactory.newInstance();
		try {
			// 创建Transformer对象
			Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.transform(new DOMSource(document), new StreamResult(new File(fileName)));
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void parseXml(String fileName) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(fileName);
			NodeList books = document.getChildNodes();
			for (int i = 0; i < books.getLength(); i++) {
				Node book = books.item(i);
				NodeList bookInfo = book.getChildNodes();
				for (int j = 0; j < bookInfo.getLength(); j++) {
					Node node = bookInfo.item(j);
					NodeList bookMeta = node.getChildNodes();
					for (int k = 0; k < bookMeta.getLength(); k++) {
						System.out.println(bookMeta.item(k).getNodeName() + ":" + bookMeta.item(k).getTextContent());
					}
				}
			}
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
