/**
 * 
 */
package scene;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import elements.AmbientLight;
import geometries.*;
import primitives.*;

/**
 * @author m.n.y.1234
 *
 */
public class SceneBuilder {

	private Scene scene;
	private static final String filePath = System.getProperty("user.dir") + "/xml/";

	/**
	 * @param scene - scene to Builder
	 */
	public SceneBuilder(Scene scene) {
		this.scene = scene;
	}

	/**
	 * 
	 * @param nameFile
	 */
	public void loadSceneFromFile(String nameFile) {
		File xmlFile = new File(filePath + nameFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			var lst = doc.getElementsByTagName("background");
			setBackgroundColor(lst.item(0));
			lst = doc.getElementsByTagName("ambient-light");
			setAmbientLight(lst.item(0));
			lst = doc.getElementsByTagName("geometries");
			setGeometries(lst.item(0).getChildNodes());
			// now XML is loaded as Document in memory, lets convert it to Object List

		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}
	}

	private void setBackgroundColor(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			var stringColor = element.getAttributeNode("color").getValue();
			var colorRBG = stringColor.toString().split(" ");
			scene.setBackground(new Color(Double.parseDouble(colorRBG[0]), Double.parseDouble(colorRBG[1]),
					Double.parseDouble(colorRBG[2])));
		}
	}

	private void setAmbientLight(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			var stringColor = element.getAttributeNode("color").getValue();
			var colorRBG = stringColor.toString().split(" ");
			scene.setAmbientLight(new AmbientLight(new Color(Double.parseDouble(colorRBG[0]),
					Double.parseDouble(colorRBG[1]), Double.parseDouble(colorRBG[2])), 1.0));
		}
	}

	private void setGeometries(NodeList lst) {
		Geometries geometries = new Geometries();
		for (int i = 0; i < lst.getLength(); i++) {
			if (lst.item(i).getNodeName() == "sphere") {
				var result = setSphere(lst.item(i));
				if (result != null) {
					geometries.add(result);
					continue;
				}
			}
			if (lst.item(i).getNodeName() == "triangle") {
				var result = setTriangle(lst.item(i));
				if (result != null) {
					geometries.add(result);
					continue;
				}
			}
		}
		scene.setGeometries(geometries);
	}

	private Sphere setSphere(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			var r = element.getAttributeNode("radius").getValue().toString();
			var center = element.getAttributeNode("center").getValue().toString().split(" ");
			return new Sphere(new Point3D(Double.parseDouble(center[0]), Double.parseDouble(center[1]),
					Double.parseDouble(center[2])), Integer.parseInt(r));
		}
		return null;
	}

	private Triangle setTriangle(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			var p0 = element.getAttributeNode("p0").getValue().toString().split(" ");
			var p1 = element.getAttributeNode("p1").getValue().toString().split(" ");
			var p2 = element.getAttributeNode("p2").getValue().toString().split(" ");
			return new Triangle(
					new Point3D(Double.parseDouble(p0[0]), Double.parseDouble(p0[1]), Double.parseDouble(p0[2])),
					new Point3D(Double.parseDouble(p1[0]), Double.parseDouble(p1[1]), Double.parseDouble(p1[2])),
					new Point3D(Double.parseDouble(p2[0]), Double.parseDouble(p2[1]), Double.parseDouble(p2[2])));
		}
		return null;
	}
}
