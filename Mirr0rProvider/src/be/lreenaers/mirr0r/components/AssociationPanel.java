package be.lreenaers.mirr0r.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import be.lreenaers.mirr0r.components.utils.CommonValueVerifier;
import be.lreenaers.mirr0r.components.utils.Utils;
import be.lreenaers.mirr0r.schema.SchemaManager;

/**
 * This Panel contains Association widget. Such as mapping drop down selector.
 * 
 * @author Ludovic Reenaers
 * @see JPanel
 * @see ActionListener
 * @since 1.0.1
 *
 */
@SuppressWarnings("serial")
public class AssociationPanel extends JPanel implements ActionListener{
	private ResourceBundle translator;
	private int associationsCount;
	private String schemaName;
	private Method[] metList;
	private String[] csvColumnNames;
	public SchemaManager schemaManager;
	private Converter parent;
	
	private JCheckBox common;
	public JComboBox xmlElements;
	private JComboBox csvHeaders;
	public JButton remButton;
	private JTextField commonValue;
	private CommonValueVerifier verif = new CommonValueVerifier(this);
	/**
	 * Constructor.
	 * 
	 * @param schemaName Name of the schema for which the association has been created.
	 * @param translator ResourceBundle for internationalization.
	 * @param count The association count for the current schema.
	 * @param metList The list of bean setters to be used for mapping (a.k.a xml properties).
	 * @param csvColumnNames List of String representing Column names.
	 * @param schemaManager The Converter's SchemaManager.
	 * @param parent The Converter.
	 * @see Converter
	 * @see SchemaManager
	 * @see ResourceBundle
	 * @since 1.0.1
	 */
	public AssociationPanel(String schemaName,ResourceBundle translator,int count,Method[] metList,String[] csvColumnNames, SchemaManager schemaManager,Converter parent){
		this.translator = translator;
		this.associationsCount = count;
		this.parent = parent;
		this.schemaName = schemaName;
		this.schemaManager = schemaManager;
		this.csvColumnNames = csvColumnNames;
		this.metList = metList;
		this.setUpPanel();
	}
	private void setUpPanel(){
		this.setName(this.schemaName+":"+this.associationsCount);
		this.add(this.addRemoveButton());
		this.setUpAssociationComboForPanel();
		this.putOptionalCommonValueWidget();
	}
	private void putOptionalCommonValueWidget(){
		this.common = new JCheckBox(this.translator.getString("common"));
		this.common.setToolTipText(this.translator.getString("isCommon"));
		this.common.addActionListener(this);
		this.commonValue = new JTextField(20);
		this.commonValue.setInputVerifier(this.verif);
		this.commonValue.setEnabled(false);
		this.commonValue.setBackground(Utils.disabled);
		this.commonValue.addActionListener(this);
		this.add(common);
		this.add(commonValue);
	}
	private void setUpAssociationComboForPanel() {
		addSchemaBeanDropDown();
		addCsvDropDown();
	}
	private void addSchemaBeanDropDown() {
		this.xmlElements = new JComboBox();
		for (int i = 0; i < this.metList.length; i++) {
			if (this.metList[i].getName().substring(0, 3).equals("set")) {
				this.xmlElements.addItem(this.metList[i].getName().substring(3));
			}
		}
		this.xmlElements.setName("ass" + this.associationsCount);
		this.xmlElements.setSelectedItem(this.metList[this.associationsCount].getName().substring(3));

		this.schemaManager.putXmlAssociation(this.schemaName, "ass"
				+ this.associationsCount, this.xmlElements.getSelectedItem()
				.toString());

		this.xmlElements.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox combo = (JComboBox) e.getSource();

				schemaManager.putXmlAssociation(combo.getParent().getParent()
						.getName(), combo.getName(), combo.getSelectedItem()
						.toString());

			}
		});
		this.add(this.xmlElements);
	}

	private void addCsvDropDown() {
		this.csvHeaders = new JComboBox();
		if (this.csvColumnNames != null) {
			for (int i = 0; i < this.csvColumnNames.length; i++) {
				this.csvHeaders.addItem(this.csvColumnNames[i]);
			}
			this.csvHeaders.setName("ass" + this.associationsCount);

			this.schemaManager.putCsvAssociation(this.schemaName, "ass"
					+ this.associationsCount, this.csvHeaders.getSelectedItem()
					.toString());

			this.csvHeaders.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox combo = (JComboBox) e.getSource();
					schemaManager.putCsvAssociation(combo.getParent().getParent()
							.getName(), combo.getName(), combo
							.getSelectedItem().toString());

				}
			});
			this.add(this.csvHeaders);
		} 
	}
	private JButton addRemoveButton() {
		ImageIcon remButtonIcon = Utils.createImageIcon("img/remove.png");
		remButton = new JButton(remButtonIcon);
		remButton.setMargin(Utils.noInsets);
		remButton.setToolTipText(this.translator.getString("remove"));

		remButton.setActionCommand("remove");
		remButton.setName("ass" + this.associationsCount);
		remButton.addActionListener(this);
		return remButton;
	}
	/**
	 * Overrided from ActionListener, it handles action performed at GUI level.
	 * @param e  The ActionEvent performed.
	 * @see ActionEvent
	 * @see ActionListener
	 * @since 1.0.1
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("remove".equals(e.getActionCommand())) {
			this.removeAssociation(e);
		}
		if(e.getSource()== common){
			JCheckBox box = (JCheckBox)e.getSource();
			if (box.isSelected()){
				this.commonValue.setEnabled(true);
				this.commonValue.setBackground(Utils.enabled);
				this.csvHeaders.setEnabled(false);
			}else{
				this.commonValue.setEnabled(false);
				this.commonValue.setBackground(Utils.disabled);
				this.csvHeaders.setEnabled(true);
			}
			
		}
	
	}
	
	private void removeAssociation(ActionEvent e) {
		JButton but = (JButton) e.getSource();
		String assoc = but.getName();
		this.parent.getActiveTabPanel().remove(but.getParent());
		this.schemaManager.getSchema(but.getParent().getName().split(":")[0])
				.removeAssociation(assoc);
		this.parent.tabbedPane.validate();
	}
}