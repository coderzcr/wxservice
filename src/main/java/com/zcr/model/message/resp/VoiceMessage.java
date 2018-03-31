package com.zcr.model.message.resp;

import com.zcr.model.Voice;

public class VoiceMessage extends BaseMessage {
	private Voice voice;

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

}
