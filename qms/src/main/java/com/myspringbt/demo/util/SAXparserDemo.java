package com.myspringbt.demo.util;

import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXparserDemo {
    public static void main(String[] args) {

    }


    /**
     * 增加防止部实体注入逻辑
     * <功能详细描述>
     * @param reader
     * @throws SAXException
     * @see [类、类#方法、类#成员]
     */
    public static void setReaderFeature(SAXReader reader)
            throws SAXException
    {

        reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

        reader.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);

        // 是否包含外部生成的实体。当正在解析文档时为只读属性，未解析文档的状态下为读写。
        reader.setFeature("http://xml.org/sax/features/external-general-entities", false);

        // 是否包含外部的参数，包括外部DTD子集。当正在解析文档时为只读属性，未解析文档的状态下为读写。
        reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

        reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

    }

}
