package com.zcr.model.message.req;

public class VoiceMessage extends BaseMessage {
	private String MediaId;
	private String Formate;
	private String Recognition;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormate() {
		return Formate;
	}

	public void setFormate(String formate) {
		Formate = formate;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

}
