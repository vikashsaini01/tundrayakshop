package com.yakshop.dataloader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.yakshop.dataloader.Source.DefaultXmlSource;
import com.yakshop.model.LabYak;
import com.yakshop.model.Yak;

public class YakXmlLoaderImpl implements YakLoader {

	private static final DocumentBuilder dBuilder;
	static {
		try {
			dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("Exception in intializing DocumentBuilder.", e);
		}
	}

	public List<Yak> loadYaks(final Source srcInput) {
		checkSourceCompatible(srcInput);
		DefaultXmlSource source = (DefaultXmlSource) srcInput;
		Document document = null;
		try {
			document = dBuilder.parse(source.getXmlFilePath());
		} catch (SAXException | IOException e) {
			throw new RuntimeException("Exception in parsing xml file.", e);
		}

		document.getDocumentElement().normalize();
		NodeList nodeList = document.getElementsByTagName("labyak");
		List<Yak> yaks = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element el = (Element) node;
				yaks.add(new LabYak(el.getAttribute("name"), el.getAttribute("age"), el.getAttribute("sex")));
			}
		}
		return yaks;
	}

	private void checkSourceCompatible(Source source) {
		if (source == null || !(source instanceof DefaultXmlSource)) {
			throw new IllegalArgumentException("Source is null or not compatible to " + this.getClass().getName());
		}
	}
}