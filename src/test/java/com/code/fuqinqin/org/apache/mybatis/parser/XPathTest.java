package com.code.fuqinqin.org.apache.mybatis.parser;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

/**
 * <p>XPath UT</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/4 10:37
 */
public class XPathTest {
    @Test
    public void test() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        // 开启验证
        documentBuilderFactory.setValidating(true);
        documentBuilderFactory.setNamespaceAware(false);
        documentBuilderFactory.setIgnoringComments(true);
        documentBuilderFactory.setIgnoringElementContentWhitespace(false);
        documentBuilderFactory.setCoalescing(false);
        documentBuilderFactory.setExpandEntityReferences(true);

        // 创建DocumentBuilder
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        // 设置异常处理对象
        documentBuilder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                throw exception;
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                throw exception;
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                throw exception;
            }
        });

        // 将文档加载进一个Document对象中
        Document document = documentBuilder.parse("src/test/resources/mybatis/test/library.xml");

        // 构建XPathFactory
        XPathFactory xPathFactory = XPathFactory.newInstance();
        // 创建XPath对象
        XPath xPath = xPathFactory.newXPath();

        System.out.println("查询作者为罗贯中的图书标题：");
        // 编译XPath表达式
        XPathExpression xPathExpression = xPath.compile("//book[author='罗贯中']/title/text()");
        // 通过XPAth表达式得到结果，第一个参数制定了XPath表达式进行查询的上下文节点，也就是指定节点下查找符合XPath的节点，本例中的上下文节点是整个文档；第二个参数制定了XPath表达式的返回类型
        Object result1 = xPathExpression.evaluate(document, XPathConstants.NODESET);
        NodeList nodeList = (NodeList) result1;
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println("\t" + nodeList.item(i).getNodeValue());
        }

        System.out.println("查询1997年之后的图书的标题：");
        NodeList result2 = (NodeList) xPath.evaluate("//book[@year>1997]/title/text()", document, XPathConstants.NODESET);
        for (int i = 0; i < result2.getLength(); i++) {
            System.out.println("\t" + result2.item(i).getNodeValue());
        }

        System.out.println("查询1997年之后的图书的属性和标题：");
        NodeList result3 = (NodeList) xPath.evaluate("//book[@year>1997]/@*|//book[@year>1997]/title/text()", document, XPathConstants.NODESET);
        for (int i = 0; i < result3.getLength(); i++) {
            System.out.println("\t" + result3.item(i).getNodeValue());
        }
    }
}
