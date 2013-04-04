package be.lreenaers.mirr0r.schema.components;

import be.lreenaers.mirr0r.schema.interfaces.XmlObject;

public class JdomValue implements XmlObject {
	private String s;
	public JdomValue(String s) {
		this.s=s;
	}
	public String toString(){
		return this.s;
	}

}
