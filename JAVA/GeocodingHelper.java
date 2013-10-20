import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class GeocodingHelper {

	private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://maps.google.com/maps/api/geocode/xml";
	
	private Logger logger = Logger.getLogger(GeocodingHelper.class);

	private float latitude;

	private float length;

	public GeocodingHelper(String address) throws IOException,
			XPathExpressionException, ParserConfigurationException,
			SAXException {
		
		logger.info("Address:"+address);
		// String address = "TORINO VIA GIAGLIONE, 1 10126";

		URL url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?address="
				+ URLEncoder.encode(address, "UTF-8") + "&sensor=false");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		Document geocoderResultDocument = null;
		try {
			conn.connect();
			InputSource geocoderResultInputSource = new InputSource(
					conn.getInputStream());
			geocoderResultDocument = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(geocoderResultInputSource);
		} finally {
			conn.disconnect();
		}

		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList resultNodeList = null;

		// get address
		resultNodeList = (NodeList) xpath.evaluate(
				"/GeocodeResponse/result/formatted_address",
				geocoderResultDocument, XPathConstants.NODESET);
		for (int i = 0; i < resultNodeList.getLength(); ++i) {
			logger.info(resultNodeList.item(i).getTextContent());
		}

		// get Province
		
		resultNodeList = (NodeList) xpath
				.evaluate(
						"/GeocodeResponse/result[1]/address_component[type/text()='locality']/long_name",
						geocoderResultDocument, XPathConstants.NODESET);
		for (int i = 0; i < resultNodeList.getLength(); ++i) {
			logger.info(resultNodeList.item(i).getTextContent());
		}
		
		// get coordinates
		resultNodeList = (NodeList) xpath.evaluate(
				"/GeocodeResponse/result[1]/geometry/location/*",
				geocoderResultDocument, XPathConstants.NODESET);
		float lat = Float.NaN;
		float lng = Float.NaN;
		for (int i = 0; i < resultNodeList.getLength(); ++i) {
			Node node = resultNodeList.item(i);
			if ("lat".equals(node.getNodeName()))
				lat = Float.parseFloat(node.getTextContent());
			if ("lng".equals(node.getNodeName()))
				lng = Float.parseFloat(node.getTextContent());
		}
		this.latitude=lat;
		this.length=lng;
		logger.info("lat/lng=" + lat + "," + lng);

	}

	public float getLatitude() {
		return latitude;
	}

	public float getLength() {
		return length;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setLength(float length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "GeocodingHelper [latitude=" + latitude + ", length=" + length
				+ "]";
	}

}
