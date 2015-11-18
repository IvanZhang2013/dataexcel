import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Jaca {
	public static void main(String[] args) throws DocumentException {
		// 读取文档
		SAXReader reader = new SAXReader();
		// 获取文档内容
		Document document = reader
				.read(new File("F:\\GitHub\\Eclipse_GitHub\\dataexcel\\src\\main\\resource\\xml\\text.xml"));

		Element root = document.getRootElement();
		System.out.println(document.selectNodes("//root/columeen").size());
		List<Node> list =root.selectNodes("//table");
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Node object = (Node) iterator.next();
			System.out.println(object.getText()+">>>>"+object.valueOf("@status"));
		} 
		
		
		for(Iterator it=root.elementIterator();it.hasNext();){     
	        Element element = (Element) it.next();
	        System.out.println(element.getName());
		}
		System.out.println("".trim()==null);
		System.out.println("   ".trim()+"99");

	}
}
