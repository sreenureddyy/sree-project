package com.sree.web;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("skinBean")
@Scope(value = "session")
@SuppressWarnings("serial")
public class SkinBean implements Serializable {
	private String skin = "blueSky";
	private Object skinChooserState;

	public String getSkin() {
		String param = getSkinParam();
		if (param != null) {
			setSkin(param);
		}
		return this.skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	private String getSkinParam() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String param = (String) fc.getExternalContext()
				.getRequestParameterMap().get("s");
		if ((param != null) && (param.trim().length() > 0)) {
			return param;
		}
		return null;
	}

	public String changeSkin() {
		String param = getSkinParam();
		if (param != null) {
			setSkin(param);
		}
		return null;
	}

	public Object getSkinChooserState() {
		return this.skinChooserState;
	}

	public void setSkinChooserState(Object skinChooserState) {
		this.skinChooserState = skinChooserState;
	}
}