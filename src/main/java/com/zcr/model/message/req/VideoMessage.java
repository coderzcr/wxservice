package com.zcr.model.message.req;

public class VideoMessage extends BaseMessage {
	private String MediaId;
	private String ThumMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumMediaId() {
		return ThumMediaId;
	}

	public void setThumMediaId(String thumMediaId) {
		ThumMediaId = thumMediaId;
	}
}
