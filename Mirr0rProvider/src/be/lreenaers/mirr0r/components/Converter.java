package be.lreenaers.mirr0r.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import be.lreenaers.mirr0r.components.utils.CsvFileFilter;
import be.lreenaers.mirr0r.components.utils.Utils;
import be.lreenaers.mirr0r.schema.SchemaManager;

import com.csvreader.CsvReader;

/**
 * Mirror converter's main GUI component. it extends JPanel and implements
 * ActionListener.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since beta
 * 
 */
@SuppressWarnings("serial")
public class Converter extends JPanel implements ActionListener {
	/**
	 * Mirror converter's main GUI component. it extends JPanel and implements
	 * ActionListener.
	 * 
	 * @see JPanel
	 * @see ActionListener
	 * @since beta
	 */
	static private final String newline = "\n";
	private JButton openButton, saveButton, writeButton, addAssocButton,
			addSchButton;
	private JPanel buttonPanel;
	private ImageIcon xmlIcon, addAssocButtonIcon, addSchButtonIcon,
			openButtonIcon, saveButtonIcon, genButtonIcon;
	private JScrollPane logScrollPane;
	protected JTextArea log;
	private JFileChooser fc;
	protected JTabbedPaneWithCloseIcons tabbedPane;
	private JFrame frame;

	private CsvReader reader;
	private SchemaManager schManager;
	private ResourceBundle translator;

	private String saveDirectoryPath;
	private String[] csvColumnNames;
	private Method[] methList;

	private int associationsCount;
	private int selectedTabIndex;
	private String selectedTabName;

	/**
	 * Only available constructor, uses ResourceBundle to initialize
	 * application's main panel.
	 * 
	 * @param frame
	 *            application's Frame.
	 * @param translator
	 *            ResourceBundle for internationalization.
	 * @see JFrame
	 * @see ResourceBundle
	 * @since beta
	 */
	public Converter(JFrame frame, ResourceBundle translator) {

		super(new BorderLayout());
		this.translator = translator;
		this.frame = frame;
		this.initiateDefaultValues();
		this.setUpComponents();
	}

	private void setUpComponents() {
		this.createLogger();

		fc = new JFileChooser();

		this.createIcons();

		this.createPanels();

		this.createButtons();

		this.setUpPanels();
	}

	private void setUpPanels() {

		add(buttonPanel, BorderLayout.PAGE_START);
		add(this.tabbedPane, BorderLayout.CENTER);
		add(logScrollPane, BorderLayout.PAGE_END);
		this.setPreferredSize(new Dimension(800, 600));
	}

	protected void setSelectedTabIndex(int index) {
		this.selectedTabIndex = index;
	}

	protected void setSelectedTabName(String name) {
		this.selectedTabName = name;
	}

	private void createButtons() {
		addSchButton = new JButton(addSchButtonIcon);
		addSchButton.setToolTipText(translator.getString("addSch"));
		addSchButton.setMargin(Utils.noInsets);
		addSchButton.setEnabled(false);
		addSchButton.addActionListener(this);

		addAssocButton = new JButton(addAssocButtonIcon);
		addAssocButton.setToolTipText(translator.getString("addAssoc"));
		addAssocButton.setMargin(Utils.noInsets);
		addAssocButton.setEnabled(false);
		addAssocButton.addActionListener(this);

		openButton = new JButton(openButtonIcon);
		openButton.setToolTipText(translator.getString("openSourceFile"));
		openButton.setMargin(Utils.noInsets);
		openButton.addActionListener(this);

		saveButton = new JButton(saveButtonIcon);
		saveButton.setToolTipText(translator.getString("saveToDirectory"));
		saveButton.setMargin(Utils.noInsets);
		saveButton.addActionListener(this);

		writeButton = new JButton(genButtonIcon);
		writeButton.setToolTipText(translator.getString("generate"));
		writeButton.setMargin(Utils.noInsets);
		writeButton.setEnabled(false);
		writeButton.addActionListener(this);

		buttonPanel.add(openButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(addAssocButton);
		buttonPanel.add(addSchButton);
		buttonPanel.add(writeButton);
	}

	private void createPanels() {

		buttonPanel = new JPanel();

		tabbedPane = new JTabbedPaneWithCloseIcons();
		tabbedPane.setParent(this);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.addChangeListener(new TabbedPaneListener(this));
	}

	private void addATab(String label, JComponent panel) {
		tabbedPane.addTab(label, panel, xmlIcon);

	}

	private void createLogger() {
		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		logScrollPane = new JScrollPane(log);
	}

	private void createIcons() {

		xmlIcon = Utils.createImageIcon("img/xml16x16.png");
		addAssocButtonIcon = Utils.createImageIcon("img/mapper32X32.png");
		addSchButtonIcon = Utils.createImageIcon("img/csvxml.jpg");
		openButtonIcon = Utils.createImageIcon("img/open.png");
		saveButtonIcon = Utils.createImageIcon("img/save.png");
		genButtonIcon = Utils.createImageIcon("img/generate.png");
	}

	private void initiateDefaultValues() {
		saveDirectoryPath = "";
		associationsCount = 0;
		schManager = new SchemaManager();
	}

	protected void log(String info) {
		log.append(info);
		log.setCaretPosition(log.getDocument().getLength());
	}

	/**
	 * Inherited from ActionListener, this methods handles components related
	 * actions performed within the application.
	 * 
	 * @param e
	 *            The event itself.
	 * @see ActionListener
	 * @see ActionEvent
	 * @since beta
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == openButton) {
			try {
				if (this.tabbedPane.getTabCount() > 0) {
					this.tabbedPane.removeAll();
					this.schManager.removeAllActiveSchema();
				}
				if (this.selectSourceFile()) {
					this.addAssocButton.setEnabled(true);
					this.writeButton.setEnabled(true);
					this.addSchButton.setEnabled(true);
					this.addAssociationPanelsFromBeanProperties(this.schManager
							.getDefaultSchemaName());
				}
			} catch (IOException e1) {
				this.log(this.translator.getString("noSourceFile"));
			} catch (ClassNotFoundException e1) {
				this.log(this.translator.getString("noSuchClass"));
				this.log(e1.getMessage());
			}
		} else if (e.getSource() == saveButton) {
			this.selectTargetDirectory();
		} else if (e.getSource() == addSchButton) {
			Object[] possibilities = this.schManager.getAvailableSchemaNames();
			String schName = (String) JOptionPane.showInputDialog(this.frame,
					translator.getString("schemaSelection"), translator
							.getString("addSchema"), JOptionPane.PLAIN_MESSAGE,
					this.xmlIcon, possibilities, this.schManager
							.getDefaultSchemaName());
			schName = this.schManager.addSchema(schName);
			if (schName != null) {
				try {
					this.addAssociationPanelsFromBeanProperties(schName);
				} catch (ClassNotFoundException e1) {
					this.log(this.translator.getString("noSuchClass"));
					this.log(e1.getMessage());
				}
			}
		} else if (e.getSource() == addAssocButton) {

			this.addAssociationPanel(0, this.getActiveTabPanel(),
					this.selectedTabName);
			this.tabbedPane.validate();
		} else if (e.getSource() == writeButton) {
			try {
				this.writeDown();
			} catch (SecurityException e1) {
				this.log(this.translator.getString("SecurityException"));
			} catch (IllegalArgumentException e1) {
				this.log(this.translator.getString("IllegalArgumentException"));
			} catch (IOException e1) {
				this.log(this.translator.getString("noSourceFile"));
			} catch (ClassNotFoundException e1) {
				this.log(this.translator.getString("noSuchClass"));
				this.log(e1.getMessage());
			} catch (NoSuchMethodException e1) {
				this.log(this.translator.getString("noSuchMethod"));
			} catch (IllegalAccessException e1) {
				this.log(this.translator.getString("IllegalAccessException"));
			} catch (InvocationTargetException e1) {
				this
						.log(this.translator
								.getString("InvocationTargetException"));
			}
		}
		
	}



	protected JPanel getActiveTabPanel() {
		JScrollPane scroll = (JScrollPane) this.tabbedPane
				.getComponentAt(this.selectedTabIndex);
		JPanel panel = (JPanel) scroll.getViewport().getComponent(0);
		return panel;
	}

	private void selectTargetDirectory() {
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showSaveDialog(Converter.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			this.saveDirectoryPath = file.getPath();
		} else {
			this.log(translator.getString("saveCanceled") + newline);
		}
	}

	private boolean selectSourceFile() throws IOException {
		fc.setFileFilter(new CsvFileFilter(translator.getString("CsvOnly")));
		int returnVal = fc.showOpenDialog(Converter.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.getSourceFile();
			return true;
		} else {
			this.log(translator.getString("openCanceled") + newline);
			return false;
		}
	}

	private void getSourceFile() throws IOException {
		this.reader = new CsvReader(fc.getSelectedFile().getPath());
		this.reader.readHeaders();
		this.csvColumnNames = this.reader.getHeaders();
		if (this.csvColumnNames.length<=0){
			this.log(this.translator.getString("noHeaders"));
		}

		this.schManager.setReader(this.reader);

	}

	private void writeDown() throws SecurityException,
			IllegalArgumentException, IOException, ClassNotFoundException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {

		this.schManager.serializeSchemas(this.saveDirectoryPath);

	}

	@SuppressWarnings("unchecked")
	private void setMethList(String schemaName) throws ClassNotFoundException {
		Class cls = Class.forName(this.schManager.getSchema(schemaName)
				.getBeanClassName());
		methList = cls.getMethods();
	}

	private void addAssociationPanelsFromBeanProperties(String schemaName)
			throws ClassNotFoundException {
		JPanel wrapPanel = new JPanel();
		wrapPanel.setName(schemaName);
		JScrollPane scroll = new JScrollPane(wrapPanel);
		wrapPanel.setLayout(new BoxLayout(wrapPanel, BoxLayout.PAGE_AXIS));
		this.setMethList(schemaName);
		for (int i = 0; i < methList.length; i++) {
			if (methList[i].getName().substring(0, 3).equals("set")) {
				addAssociationPanel(i, wrapPanel, schemaName);
			}
		}

		this.addATab(schemaName, scroll);
		this.tabbedPane.validate();
	}

	private void addAssociationPanel(int panelCount,
			JPanel comp, String schemaName) {
		
		JPanel panel = new AssociationPanel(schemaName, this.translator, panelCount, this.methList, this.csvColumnNames, this.schManager, this);
		comp.add(panel);
		this.associationsCount++;
	}
	protected void removeSchema(String schemaName) {
		this.schManager.removeSchema(schemaName);
	}	
}
