package com.zcr.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zcr.controller.LoginController;
import com.zcr.model.message.resp.TextMessage;
import com.zcr.util.MessageUtil;

public class CoreService {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	public static String processRequest(HttpServletRequest request) {
		String respxml = null;
		String respContent = "未知的消息类型！";
		try {
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "您发送的是文本消息!";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息!";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息!";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息!";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息!";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息!";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				String eventType = requestMap.get("Event");
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！";
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {

				} else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {

				} else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {

				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {

				}
			}

			textMessage.setContent(respContent);
			respxml = MessageUtil.messageToXml(textMessage);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return respxml;

	}

}
