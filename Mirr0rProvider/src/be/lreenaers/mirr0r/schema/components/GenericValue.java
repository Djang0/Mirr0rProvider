package be.lreenaers.mirr0r.schema.components;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import be.lreenaers.mirr0r.components.utils.Utils;
import be.lreenaers.mirr0r.schema.interfaces.XmlObject;
import be.lreenaers.mirr0r.schema.interfaces.XmlValue;

public class GenericValue implements XmlValue {
	protected String value;
	protected JComboBox columnCombo;
	
	public GenericValue(){
		this.setUp();
	}
	public GenericValue(String s){
		this.value = s;
		this.setUp();
	}
	private void setUp(){
		this.columnCombo = Utils.getColumnCombo();
	}
	@Override
	public String getContent() {
		return this.value;
	}

	@Override
	public void setContent(String value) {
		this.value = value;

	}

	@Override
	public JComponent getSwing() {
		return this.columnCombo;
	}

	@Override
	public XmlObject getXml() {
		return new JdomValue(this.value);
	}

}
