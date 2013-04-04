package be.lreenaers.mirr0r.components.utils;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

import be.lreenaers.mirr0r.components.AssociationPanel;
/**
 * Ensures that the text used as common value for an XML property is well refreshed to the association objects.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since 1.0.1
 */
public class CommonValueVerifier extends InputVerifier{
	
	private AssociationPanel parent;
	/**
	 * Constructor.
	 * 
	 * @param parent The AssociationPanel that contains the verified input.
	 * @see AssociationPanel
	 * @see InputVerifier
	 * @since 1.0.1
	 */
	public CommonValueVerifier(AssociationPanel parent){
		this.parent = parent;
	}
	/**
	 * Overrided from InputVerifier. This handles verification process and relay changes to schema's objects.
	 * @param arg0   The component to be verified (here: JTextFiled).
	 * @see JTextField
	 * @see AssociationPanel
	 * @since 1.0.1
	 */
	@Override
	public boolean verify(JComponent arg0) {
		JTextField txt = (JTextField)arg0;
		this.parent.schemaManager.putXmlAssociation(this.parent.getName().split(":")[0], this.parent.remButton.getName(), this.parent.xmlElements.getSelectedItem().toString());
		this.parent.schemaManager.putCommonAssociation(this.parent.getName().split(":")[0], this.parent.remButton.getName(), txt.getText());
		return true;
	}
}