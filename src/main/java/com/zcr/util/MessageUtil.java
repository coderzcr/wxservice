package com.zcr.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.zcr.model.Article;
import com.zcr.model.message.resp.ImageMessage;
import com.zcr.model.message.resp.MusicMessage;
import com.zcr.model.message.resp.NewsMessage;
import com.zcr.model.message.resp.TextMessage;
import com.zcr.model.message.resp.VideoMessage;
import com.zcr.model.message.resp.VoiceMessage;

public class MessageUtil {

	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	public static final String EVENT_TYPE_SCAN = "scan";
	public static final String EVENT_TYPE_LOCATION = "location";
	public static final String EVENT_TYPE_CLICK = "click";

	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	public static final String RESP_MESSAGE_TYPE_LINK = "link";
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	public static Map<String, String> parseXml(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = request.getInputStream();
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		Element root = document.getRootElement();
		List<Element> lists = root.elements();
		for (Element e : lists) {
			map.put(e.getName(), e.getText());
		}
		inputStream.close();
		inputStream = null;

		return map;

	}

	private static XStream stream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;
				String createTime = "";

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					if (name != null && name.equals("CreateTime")) {
						createTime = "CreateTime";
					} else {
						createTime = name;
					}
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata && !createTime.equals("CreateTime")) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	public static String messageToXml(TextMessage textMessage) {
		stream.alias("xml", textMessage.getClass());
		return stream.toXML(textMessage);
	}

	public static String messageToXml(ImageMessage imageMessage) {
		stream.alias("xml", imageMessage.getClass());
		return stream.toXML(imageMessage);
	}

	public static String messageToXml(VoiceMessage voiceMessage) {
		stream.alias("xml", voiceMessage.getClass());
		return stream.toXML(voiceMessage);
	}

	public static String messageToXml(VideoMessage videoMessage) {
		stream.alias("xml", videoMessage.getClass());
		return stream.toXML(videoMessage);
	}

	public static String messageToXml(MusicMessage musicMessage) {
		stream.alias("xml", musicMessage.getClass());
		return stream.toXML(musicMessage);
	}

	public static String messageToXml(NewsMessage newsMessage) {
		stream.alias("xml", newsMessage.getClass());
		stream.alias("item", new Article().getClass());
		return stream.toXML(newsMessage);
	}

}
