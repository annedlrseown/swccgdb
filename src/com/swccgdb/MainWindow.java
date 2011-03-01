package com.swccgdb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainWindow
{

    private JFrame	     frmswipStarWars;
    private final ButtonGroup  buttonGroup = new ButtonGroup();
    private JTextField	 textFieldInventory;
    private JTextField	 textField;
    private JTextField	 textFieldNickname;
    private JTextField	 textFieldCounterpart;
    private JPanel	     panelCardList;
    private JPanel	     panel_8;
    private JRadioButton       rdbtnLightside;
    private JRadioButton       rdbtnDarkSide;
    private DatabaseController dbc;
    private JList	      listCardList;
    private CardListModel      cardListModel;
    private DefaultListModel   filterListModel;
    private JLabel	     lblNumCards;
    private JScrollPane	scrollPane;
    private JScrollPane	scrollPaneFilter;
    private JComboBox	  comboBoxFilter1;
    private JComboBox	  comboBoxFilter2;
    private JComboBox	  comboBoxFilter3;
    private JList	      listFilterList;
    private About	      about;

    private Font	       stdFont;
    private Font	       bldFont;

    private Color	      editColor   = Color.RED;
    private Color	      stdColor    = UIManager.getColor("control");

    private final List<String> column, op, term;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
	EventQueue.invokeLater(new Runnable()
	{
	    public void run()
	    {
		try
		{
		    MainWindow window = new MainWindow();
		    window.frmswipStarWars.setVisible(true);
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public MainWindow()
    {
	dbc = new DatabaseController();
	cardListModel = new CardListModel(dbc.getCardNames(""));
	filterListModel = new DefaultListModel();
	stdFont = new Font("Tahoma", Font.PLAIN, 12);
	bldFont = new Font("Tahoma", Font.BOLD, 12);
	column = new ArrayList<String>();
	op = new ArrayList<String>();
	term = new ArrayList<String>();
	about = new About();
	about.setVisible(false);
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
	frmswipStarWars = new JFrame();
	frmswipStarWars.setIconImage(Toolkit.getDefaultToolkit().getImage(
		MainWindow.class
			.getResource("/com/swccgdb/resources/imperial.png")));
	frmswipStarWars.setResizable(false);
	frmswipStarWars
		.setTitle("[sw-ip] Star Wars: CCG Information Pool - Java\n");
	frmswipStarWars.setBounds(100, 100, 999, 751);
	frmswipStarWars.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JMenuBar menuBar = new JMenuBar();
	frmswipStarWars.setJMenuBar(menuBar);

	JMenu mnFile = new JMenu("File");
	menuBar.add(mnFile);

	JMenuItem mntmExitswip = new JMenuItem("Exit [sw-ip]");
	mntmExitswip.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent e)
	    {
		System.exit(0);
	    }
	});
	mnFile.add(mntmExitswip);

	JMenu mnTools = new JMenu("Tools");
	menuBar.add(mnTools);

	JMenuItem mntmOptions = new JMenuItem("Options");
	mntmOptions.setEnabled(false);
	mnTools.add(mntmOptions);

	JMenu mnHelp = new JMenu("Help");
	menuBar.add(mnHelp);

	JMenuItem mntmInfo = new JMenuItem("About");
	mntmInfo.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent arg0)
	    {
		about.setVisible(true);
	    }
	});
	mnHelp.add(mntmInfo);

	JMenuItem mntmHelp = new JMenuItem("Help");
	mntmHelp.setEnabled(false);
	mnHelp.add(mntmHelp);
	frmswipStarWars.getContentPane().setLayout(null);

	lblNumCards = new JLabel("0 Cards");
	lblNumCards.setHorizontalAlignment(SwingConstants.TRAILING);
	lblNumCards.setBounds(203, 6, 91, 16);
	frmswipStarWars.getContentPane().add(lblNumCards);

	JPanel panelCardDetails = new JPanel();
	panelCardDetails.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
		null, null, null));
	panelCardDetails.setBounds(306, 28, 285, 276);
	frmswipStarWars.getContentPane().add(panelCardDetails);
	panelCardDetails.setLayout(new BorderLayout(0, 0));

	JScrollPane scrollPane_11 = new JScrollPane();
	panelCardDetails.add(scrollPane_11, BorderLayout.CENTER);

	final JTextArea dtrpnCarginfo = new JTextArea();
	dtrpnCarginfo.setEditable(false);
	dtrpnCarginfo.setLineWrap(true);
	dtrpnCarginfo.setWrapStyleWord(true);
	dtrpnCarginfo.setFont(stdFont);
	scrollPane_11.setViewportView(dtrpnCarginfo);

	JPanel panelFilter = new JPanel();
	panelFilter.setBorder(new TitledBorder(null, "Filter",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panelFilter.setBounds(603, 28, 285, 276);
	frmswipStarWars.getContentPane().add(panelFilter);
	panelFilter.setLayout(null);

	rdbtnDarkSide = new JRadioButton("Dark Side");
	rdbtnDarkSide.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent arg0)
	    {
		if (rdbtnDarkSide.isSelected())
		{
		    for (int i = 0; i < column.size(); i++)
		    {
			if (column.get(i).equals("Grouping")
				&& term.get(i).equals("Dark"))
			    return;
			if (column.get(i).equals("Grouping")
				&& term.get(i).equals("Light"))
			{
			    column.remove(i);
			    op.remove(i);
			    term.remove(i);
			    break;
			}
		    }

		    column.add("Grouping");
		    op.add("=");
		    term.add("Dark");

		    populateFilters();
		}
	    }
	});
	buttonGroup.add(rdbtnDarkSide);
	rdbtnDarkSide.setBounds(95, 17, 85, 23);
	panelFilter.add(rdbtnDarkSide);

	rdbtnLightside = new JRadioButton("Light Side");
	rdbtnLightside.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent arg0)
	    {
		if (rdbtnLightside.isSelected())
		{
		    for (int i = 0; i < column.size(); i++)
		    {
			if (column.get(i).equals("Grouping")
				&& term.get(i).equals("Light"))
			    return;
			if (column.get(i).equals("Grouping")
				&& term.get(i).equals("Dark"))
			{
			    column.remove(i);
			    op.remove(i);
			    term.remove(i);
			}
		    }

		    column.add("Grouping");
		    op.add("=");
		    term.add("Light");

		    populateFilters();
		}
	    }
	});
	buttonGroup.add(rdbtnLightside);
	rdbtnLightside.setBounds(6, 17, 85, 23);
	panelFilter.add(rdbtnLightside);

	panel_8 = new JPanel();
	panel_8.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
		null, null));
	panel_8.setBounds(6, 172, 176, 91);
	panelFilter.add(panel_8);
	panel_8.setLayout(null);

	scrollPaneFilter = new JScrollPane();
	scrollPaneFilter.setBounds(2, 2, 172, 87);
	panel_8.add(scrollPaneFilter);

	listFilterList = new JList();
	listFilterList.setFont(stdFont);
	listFilterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	listFilterList.setModel(filterListModel);
	listFilterList.setVisibleRowCount(10);
	scrollPaneFilter.setViewportView(listFilterList);

	JButton btnFilter = new JButton("Filter");
	btnFilter.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent arg0)
	    {
		updateFilterList();
		clearFilters();
		listCardList.requestFocusInWindow();
	    }
	});
	btnFilter.setBounds(194, 126, 79, 29);
	panelFilter.add(btnFilter);

	scrollPaneFilter = new JScrollPane();

	comboBoxFilter1 = new JComboBox();
	comboBoxFilter1.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent arg0)
	    {
		String selection = (String) comboBoxFilter1.getSelectedItem();
		if (blank(selection))
		    clearFilters();
		else if (number(selection))
		    numberFilters();
		else
		    wordFilters();
	    }
	});
	comboBoxFilter1.setFont(stdFont);
	setComboFilter1Model();
	comboBoxFilter1.setBounds(6, 52, 176, 24);
	panelFilter.add(comboBoxFilter1);

	comboBoxFilter2 = new JComboBox();
	comboBoxFilter2.setModel(new DefaultComboBoxModel(new String[] {}));
	comboBoxFilter2.setFont(stdFont);
	comboBoxFilter2.setBounds(6, 89, 176, 24);
	panelFilter.add(comboBoxFilter2);

	comboBoxFilter3 = new JComboBox();
	comboBoxFilter3.setFont(stdFont);
	comboBoxFilter3.setBounds(6, 126, 176, 29);
	panelFilter.add(comboBoxFilter3);

	JButton btnRemove = new JButton("Remove");
	btnRemove.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent arg0)
	    {
		int index = listFilterList.getSelectedIndex();
		if (index >= 0)
		{
		    if (column.get(index).equals("Grouping")
			    && term.get(index).equals("Light"))
			buttonGroup.clearSelection();
		    if (column.get(index).equals("Grouping")
			    && term.get(index).equals("Dark"))
			buttonGroup.clearSelection();
		    column.remove(index);
		    op.remove(index);
		    term.remove(index);
		    populateFilters();
		}
	    }
	});
	btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 12));
	btnRemove.setBounds(194, 192, 79, 29);
	panelFilter.add(btnRemove);

	JPanel panelInventory = new JPanel();
	panelInventory.setBorder(new TitledBorder(null, "Cards",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panelInventory.setBounds(894, 28, 87, 134);
	frmswipStarWars.getContentPane().add(panelInventory);
	panelInventory.setLayout(null);

	textFieldInventory = new JTextField();
	textFieldInventory.setHorizontalAlignment(SwingConstants.CENTER);
	textFieldInventory.setEditable(false);
	textFieldInventory.setText("0");
	textFieldInventory.setBounds(6, 34, 34, 28);
	panelInventory.add(textFieldInventory);
	textFieldInventory.setColumns(10);

	JLabel lblInventory = new JLabel("Inventory");
	lblInventory.setBounds(6, 17, 59, 16);
	panelInventory.add(lblInventory);

	JLabel lblNeeds = new JLabel("Needs");
	lblNeeds.setBounds(6, 63, 61, 16);
	panelInventory.add(lblNeeds);

	textField = new JTextField();
	textField.setHorizontalAlignment(SwingConstants.CENTER);
	textField.setEditable(false);
	textField.setText("0");
	textField.setBounds(6, 83, 34, 28);
	panelInventory.add(textField);
	textField.setColumns(10);

	JPanel panelViewInfo = new JPanel();
	panelViewInfo.setBorder(new TitledBorder(UIManager
		.getBorder("TitledBorder.border"), "Information",
		TitledBorder.LEADING, TitledBorder.TOP, null,
		new Color(0, 0, 0)));
	panelViewInfo.setBounds(901, 175, 87, 61);
	frmswipStarWars.getContentPane().add(panelViewInfo);
	panelViewInfo.setLayout(new BorderLayout(0, 0));

	JCheckBox chckbxChangeView = new JCheckBox("View");
	chckbxChangeView.setEnabled(false);
	chckbxChangeView.setSelected(true);
	panelViewInfo.add(chckbxChangeView);

	JPanel panelExpRarity = new JPanel();
	panelExpRarity.setBorder(new TitledBorder(null, "Expansion and Rarity",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panelExpRarity.setBounds(10, 320, 229, 44);
	frmswipStarWars.getContentPane().add(panelExpRarity);
	panelExpRarity.setLayout(new BorderLayout(0, 0));

	final JLabel lblExpansion = new JLabel("Expansion");
	panelExpRarity.add(lblExpansion);

	final JLabel lblRarity = new JLabel("Rarity");
	panelExpRarity.add(lblRarity, BorderLayout.EAST);

	JPanel panelCardHelp = new JPanel();
	panelCardHelp.setBorder(new TitledBorder(null, "Card Help",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panelCardHelp.setBounds(10, 376, 229, 103);
	frmswipStarWars.getContentPane().add(panelCardHelp);
	panelCardHelp.setLayout(null);

	JLabel lblNickname = new JLabel("Abbreviation / Nickname");
	lblNickname.setBounds(65, 16, 158, 16);
	panelCardHelp.add(lblNickname);

	textFieldNickname = new JTextField();
	textFieldNickname.setFont(stdFont);
	textFieldNickname.setBackground(stdColor);
	textFieldNickname.setEditable(false);
	textFieldNickname.setBounds(6, 32, 217, 22);
	panelCardHelp.add(textFieldNickname);
	textFieldNickname.setColumns(10);

	JLabel lblCounterpart = new JLabel("Counterpart");
	lblCounterpart.setBounds(148, 57, 75, 16);
	panelCardHelp.add(lblCounterpart);

	textFieldCounterpart = new JTextField();
	textFieldCounterpart.setFont(stdFont);
	textFieldCounterpart.setEditable(false);
	textFieldCounterpart.setBounds(6, 74, 217, 22);
	panelCardHelp.add(textFieldCounterpart);
	textFieldCounterpart.setColumns(10);

	JPanel panelPulling = new JPanel();
	panelPulling.setBorder(new TitledBorder(null, "Pulling",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panelPulling.setBounds(10, 491, 229, 187);
	frmswipStarWars.getContentPane().add(panelPulling);
	panelPulling.setLayout(null);

	JLabel lblNewLabel = new JLabel("Pulls");
	lblNewLabel.setBounds(188, 19, 35, 16);
	panelPulling.add(lblNewLabel);

	JScrollPane scrollPane_2 = new JScrollPane();
	scrollPane_2.setBounds(6, 36, 217, 61);
	panelPulling.add(scrollPane_2);

	final JTextArea txtrPulls = new JTextArea();
	txtrPulls.setFont(stdFont);
	scrollPane_2.setViewportView(txtrPulls);
	txtrPulls.setBackground(stdColor);
	txtrPulls.setWrapStyleWord(true);
	txtrPulls.setEditable(false);
	txtrPulls.setLineWrap(true);

	JLabel lblPulledBy = new JLabel("Is pulled by");
	lblPulledBy.setBounds(150, 102, 73, 16);
	panelPulling.add(lblPulledBy);

	JScrollPane scrollPane_3 = new JScrollPane();
	scrollPane_3.setBounds(6, 122, 217, 52);
	panelPulling.add(scrollPane_3);

	final JTextArea txtrPulledBy = new JTextArea();
	txtrPulledBy.setFont(stdFont);
	scrollPane_3.setViewportView(txtrPulledBy);
	txtrPulledBy.setBackground(stdColor);
	txtrPulledBy.setWrapStyleWord(true);
	txtrPulledBy.setEditable(false);
	txtrPulledBy.setLineWrap(true);

	JPanel panel_1 = new JPanel();
	panel_1.setBorder(new TitledBorder(null, "Information",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panel_1.setBounds(251, 316, 242, 286);
	frmswipStarWars.getContentPane().add(panel_1);
	panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

	JScrollPane scrollPane_1 = new JScrollPane();
	panel_1.add(scrollPane_1);

	final JTextArea txtpnCardinfo = new JTextArea();
	txtpnCardinfo.setLineWrap(true);
	txtpnCardinfo.setWrapStyleWord(true);
	txtpnCardinfo.setFont(stdFont);
	scrollPane_1.setViewportView(txtpnCardinfo);
	txtpnCardinfo.setBackground(stdColor);
	txtpnCardinfo.setEditable(false);

	JPanel panel_2 = new JPanel();
	panel_2.setBorder(new TitledBorder(null, "Characteristics",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panel_2.setBounds(251, 608, 242, 70);
	frmswipStarWars.getContentPane().add(panel_2);
	panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

	JScrollPane scrollPane_4 = new JScrollPane();
	panel_2.add(scrollPane_4);

	final JTextArea txtrCharacteristics = new JTextArea();
	txtrCharacteristics.setWrapStyleWord(true);
	txtrCharacteristics.setLineWrap(true);
	txtrCharacteristics.setFont(bldFont);
	scrollPane_4.setViewportView(txtrCharacteristics);
	txtrCharacteristics.setEditable(false);
	txtrCharacteristics.setBackground(stdColor);

	JPanel panel_3 = new JPanel();
	panel_3.setBorder(new TitledBorder(null, "Combo", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	panel_3.setBounds(505, 316, 242, 181);
	frmswipStarWars.getContentPane().add(panel_3);
	panel_3.setLayout(new BorderLayout(0, 0));

	JScrollPane scrollPane_10 = new JScrollPane();
	panel_3.add(scrollPane_10, BorderLayout.CENTER);

	final JTextArea txtrCombo = new JTextArea();
	txtrCombo.setFont(stdFont);
	scrollPane_10.setViewportView(txtrCombo);
	txtrCombo.setLineWrap(true);
	txtrCombo.setBackground(stdColor);
	txtrCombo.setWrapStyleWord(true);
	txtrCombo.setEditable(false);

	JPanel panel_4 = new JPanel();
	panel_4.setBorder(new TitledBorder(null, "Rules", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	panel_4.setBounds(505, 502, 242, 176);
	frmswipStarWars.getContentPane().add(panel_4);
	panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

	JScrollPane scrollPane_5 = new JScrollPane();
	panel_4.add(scrollPane_5);

	final JTextArea txtrRules = new JTextArea();
	txtrRules.setFont(stdFont);
	txtrRules.setLineWrap(true);
	scrollPane_5.setViewportView(txtrRules);
	txtrRules.setBackground(stdColor);
	txtrRules.setWrapStyleWord(true);
	txtrRules.setEditable(false);

	JPanel panel_5 = new JPanel();
	panel_5.setBorder(new TitledBorder(null, "Canceling",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panel_5.setLayout(null);
	panel_5.setBounds(759, 316, 229, 181);
	frmswipStarWars.getContentPane().add(panel_5);

	JLabel lblCancels = new JLabel("Cancels");
	lblCancels.setBounds(166, 16, 57, 16);
	panel_5.add(lblCancels);

	JScrollPane scrollPane_6 = new JScrollPane();
	scrollPane_6.setBounds(6, 36, 217, 52);
	panel_5.add(scrollPane_6);

	final JTextArea txtrCancels = new JTextArea();
	txtrCancels.setFont(stdFont);
	scrollPane_6.setViewportView(txtrCancels);
	txtrCancels.setBackground(stdColor);
	txtrCancels.setWrapStyleWord(true);
	txtrCancels.setEditable(false);
	txtrCancels.setLineWrap(true);

	JLabel lblIsCanceledBy = new JLabel("Is canceled by");
	lblIsCanceledBy.setBounds(134, 94, 89, 16);
	panel_5.add(lblIsCanceledBy);

	JScrollPane scrollPane_7 = new JScrollPane();
	scrollPane_7.setBounds(6, 112, 217, 56);
	panel_5.add(scrollPane_7);

	final JTextArea txtrCanceledBy = new JTextArea();
	txtrCanceledBy.setFont(stdFont);
	scrollPane_7.setViewportView(txtrCanceledBy);
	txtrCanceledBy.setBackground(stdColor);
	txtrCanceledBy.setWrapStyleWord(true);
	txtrCanceledBy.setEditable(false);
	txtrCanceledBy.setLineWrap(true);

	JPanel panel_6 = new JPanel();
	panel_6.setBorder(new TitledBorder(null, "Matching",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panel_6.setLayout(null);
	panel_6.setBounds(759, 502, 229, 176);
	frmswipStarWars.getContentPane().add(panel_6);

	JLabel lblMatchingStarship = new JLabel("Matching Starship");
	lblMatchingStarship.setBounds(110, 16, 113, 16);
	panel_6.add(lblMatchingStarship);

	JScrollPane scrollPane_8 = new JScrollPane();
	scrollPane_8.setBounds(6, 36, 217, 53);
	panel_6.add(scrollPane_8);

	final JTextArea txtrMStarship = new JTextArea();
	txtrMStarship.setFont(stdFont);
	scrollPane_8.setViewportView(txtrMStarship);
	txtrMStarship.setBackground(stdColor);
	txtrMStarship.setWrapStyleWord(true);
	txtrMStarship.setEditable(false);
	txtrMStarship.setLineWrap(true);

	JLabel lblMatchingWeapon = new JLabel("Matching Weapon");
	lblMatchingWeapon.setBounds(110, 92, 113, 16);
	panel_6.add(lblMatchingWeapon);

	JScrollPane scrollPane_9 = new JScrollPane();
	scrollPane_9.setBounds(6, 108, 217, 55);
	panel_6.add(scrollPane_9);

	final JTextArea txtrMWeapon = new JTextArea();
	txtrMWeapon.setFont(stdFont);
	scrollPane_9.setViewportView(txtrMWeapon);
	txtrMWeapon.setBackground(stdColor);
	txtrMWeapon.setWrapStyleWord(true);
	txtrMWeapon.setEditable(false);
	txtrMWeapon.setLineWrap(true);

	JPanel panel_7 = new JPanel();
	panel_7.setBorder(new TitledBorder(null, "Edit Fields",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panel_7.setBounds(901, 243, 87, 61);
	frmswipStarWars.getContentPane().add(panel_7);
	panel_7.setLayout(new BorderLayout(0, 0));

	final JButton btnAddCard = new JButton("+");
	btnAddCard.setVisible(false);
	btnAddCard.setBounds(23, 4, 41, 21);
	frmswipStarWars.getContentPane().add(btnAddCard);

	final JButton btnRemoveCard = new JButton("-");
	btnRemoveCard.setVisible(false);
	btnRemoveCard.setBounds(76, 4, 41, 21);
	frmswipStarWars.getContentPane().add(btnRemoveCard);

	final JCheckBox chckbxFieldsEditable = new JCheckBox("Edit");
	chckbxFieldsEditable.setEnabled(false);
	chckbxFieldsEditable.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent e)
	    {
		boolean editable = chckbxFieldsEditable.isSelected();

		if (editable)
		{
		    int ans = JOptionPane
			    .showConfirmDialog(
				    frmswipStarWars,
				    "This will edit the card information in your datablase,\nAre you sure you want to continue?",
				    "Make fields editable?",
				    JOptionPane.OK_CANCEL_OPTION,
				    JOptionPane.WARNING_MESSAGE);
		    if (ans != 0)
		    {
			chckbxFieldsEditable.setSelected(false);
			return;
		    }
		}

		textFieldNickname.setEditable(editable);
		if (editable)
		    textFieldNickname.setBackground(editColor);
		else
		    textFieldNickname.setBackground(stdColor);

		textFieldCounterpart.setEditable(editable);
		if (editable)
		    textFieldCounterpart.setBackground(editColor);
		else
		    textFieldCounterpart.setBackground(stdColor);

		txtrPulls.setEditable(editable);
		if (editable)
		    txtrPulls.setBackground(editColor);
		else
		    txtrPulls.setBackground(stdColor);

		txtrPulledBy.setEditable(editable);
		if (editable)
		    txtrPulledBy.setBackground(editColor);
		else
		    txtrPulledBy.setBackground(stdColor);

		txtpnCardinfo.setEditable(editable);
		if (editable)
		    txtpnCardinfo.setBackground(editColor);
		else
		    txtpnCardinfo.setBackground(stdColor);

		txtrCombo.setEditable(editable);
		if (editable)
		    txtrCombo.setBackground(editColor);
		else
		    txtrCombo.setBackground(stdColor);

		txtrRules.setEditable(editable);
		if (editable)
		    txtrRules.setBackground(editColor);
		else
		    txtrRules.setBackground(stdColor);

		txtrCancels.setEditable(editable);
		if (editable)
		    txtrCancels.setBackground(editColor);
		else
		    txtrCancels.setBackground(stdColor);

		txtrCanceledBy.setEditable(editable);
		if (editable)
		    txtrCanceledBy.setBackground(editColor);
		else
		    txtrCanceledBy.setBackground(stdColor);

		txtrMStarship.setEditable(editable);
		if (editable)
		    txtrMStarship.setBackground(editColor);
		else
		    txtrMStarship.setBackground(stdColor);

		txtrMWeapon.setEditable(editable);
		if (editable)
		    txtrMWeapon.setBackground(editColor);
		else
		    txtrMWeapon.setBackground(stdColor);

		txtrCharacteristics.setEditable(editable);
		if (editable)
		    txtrCharacteristics.setBackground(editColor);
		else
		    txtrCharacteristics.setBackground(UIManager
			    .getColor("control"));

		btnAddCard.setVisible(editable);
		btnRemoveCard.setVisible(editable);
	    }
	});
	panel_7.add(chckbxFieldsEditable, BorderLayout.CENTER);

	panelCardList = new JPanel();
	panelCardList.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
		null, null, null));
	panelCardList.setBounds(10, 28, 284, 276);
	frmswipStarWars.getContentPane().add(panelCardList);
	panelCardList.setLayout(null);

	scrollPane = new JScrollPane();
	scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null,
		null, null, null));
	scrollPane.setBounds(0, 0, 284, 276);
	panelCardList.add(scrollPane);

	listCardList = new JList();
	listCardList.setFont(stdFont);
	listCardList.addListSelectionListener(new ListSelectionListener()
	{
	    public void valueChanged(ListSelectionEvent arg0)
	    {
		String name = cardListModel.getCardName(listCardList
			.getSelectedIndex());

		dtrpnCarginfo.setText(dbc.getCardInfo(name));
		dtrpnCarginfo.setCaretPosition(0);

		String[] extras = dbc.getCardExtras(name);

		if (extras != null)
		{
		    lblExpansion.setText(extras[0]);
		    lblRarity.setText(extras[1]);
		    textFieldNickname.setText(extras[2]);
		    textFieldCounterpart.setText(extras[3]);
		    txtrPulls.setText(extras[4]);
		    txtrPulls.setCaretPosition(0);
		    txtrPulledBy.setText(extras[5]);
		    txtrPulledBy.setCaretPosition(0);
		    txtpnCardinfo.setText(extras[6]);
		    txtpnCardinfo.setCaretPosition(0);
		    txtrCharacteristics.setText(extras[7]);
		    txtrCharacteristics.setCaretPosition(0);
		    txtrCombo.setText(extras[8]);
		    txtrCombo.setCaretPosition(0);
		    txtrRules.setText(extras[9]);
		    txtrRules.setCaretPosition(0);
		    txtrCancels.setText(extras[10]);
		    txtrCancels.setCaretPosition(0);
		    txtrCanceledBy.setText(extras[11]);
		    txtrCanceledBy.setCaretPosition(0);
		    txtrMStarship.setText(extras[12]);
		    txtrMStarship.setCaretPosition(0);
		    txtrMWeapon.setText(extras[13]);
		    txtrMWeapon.setCaretPosition(0);
		}
	    }
	});
	scrollPane.setViewportView(listCardList);
	listCardList.setVisibleRowCount(14);
	listCardList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	listCardList.setModel(cardListModel);
	this.lblNumCards.setText(cardListModel.getSize() + " Cards");

	JButton btnClearFilter = new JButton("Clear");
	btnClearFilter.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent arg0)
	    {
		filterListModel.clear();
		rdbtnLightside.setSelected(false);
		rdbtnDarkSide.setSelected(false);
		column.clear();
		op.clear();
		term.clear();
		updateCardList(dbc.getCardNames(""));
		buttonGroup.clearSelection();
	    }
	});
	btnClearFilter.setBounds(194, 234, 79, 29);
	panelFilter.add(btnClearFilter);

	JPanel panel = new JPanel();
	panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
		null));
	panel.setBounds(194, 44, 79, 69);
	panelFilter.add(panel);
	panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

	JCheckBox chckbxOr = new JCheckBox("OR  ");
	chckbxOr.setEnabled(false);
	panel.add(chckbxOr);

	JCheckBox chckbxNot = new JCheckBox("NOT");
	chckbxNot.setEnabled(false);
	panel.add(chckbxNot);

    }

    private void setComboFilter1Model()
    {
	comboBoxFilter1.setModel(new DefaultComboBoxModel(new String[] {
		"[Select One]", "Ability", "Armor", "Card Name", "Card Type",
		"Characteristics, Attributes, etc.", "Deploy Cost", "Destiny",
		"Expansion", "Ferocity", "Force Aptitude", "Forfeit",
		"Game Text", "Hyperspeed", "Icons", "Influence", "Landspeed",
		"Lore", "Maneuver", "Model Type", "Politics", "Power",
		"Rarity", "Subtype", "Uniqueness", "", "Force Icons Dark Side",
		"Force Icons Light Side", "Parsec Number", "",
		"Abbreviation / Nickname", "Pulls", "Is Pulled", "Cancels",
		"Is Canceled By", "Combo", "Information", "Rules", "Errata",
		"", "Inventory", "Needs" }));
    }

    private boolean blank(String selection)
    {
	if (selection.equals("") || selection.equals("[Select One]"))
	    return true;
	return false;
    }

    private boolean number(String selection)
    {
	if (selection.equals("Ability") || selection.equals("Armor")
		|| selection.equals("Deploy Cost")
		|| selection.equals("Destiny") || selection.equals("Ferocity")
		|| selection.equals("Forfeit")
		|| selection.equals("Hyperspeed")
		|| selection.equals("Influence")
		|| selection.equals("Landspeed")
		|| selection.equals("Maneuver") || selection.equals("Politics")
		|| selection.equals("Power")
		|| selection.equals("Force Icons Dark Side")
		|| selection.equals("Force Icons Light Side")
		|| selection.equals("Parsec Number"))
	    return true;
	return false;
    }

    private void wordFilters()
    {
	comboBoxFilter2.setModel(new DefaultComboBoxModel(
		new String[] { "Contains" }));
	comboBoxFilter3.setEditable(true);
	comboBoxFilter3.requestFocusInWindow();
    }

    private void numberFilters()
    {
	comboBoxFilter2.setModel(new DefaultComboBoxModel(new String[] { "=",
		"<", "<=", ">", ">=", "<>" }));
	comboBoxFilter3.setEditable(true);
	comboBoxFilter3.requestFocusInWindow();
    }

    private void clearFilters()
    {
	comboBoxFilter1.setSelectedIndex(0);
	comboBoxFilter2.setModel(new DefaultComboBoxModel(new String[] { "" }));
	comboBoxFilter3.setModel(new DefaultComboBoxModel(new String[] { "" }));
	comboBoxFilter3.setEditable(false);
    }

    private void updateFilterList()
    {
	Object selection = comboBoxFilter3.getSelectedItem();
	if (selection == null)
	    return;

	if (!comboBoxFilter3.getSelectedItem().equals(""))
	{
	    column.add((String) comboBoxFilter1.getSelectedItem());
	    op.add((String) comboBoxFilter2.getSelectedItem());
	    term.add((String) comboBoxFilter3.getSelectedItem());

	    populateFilters();
	}
	/*
	 * List<String> one = new ArrayList<String>(); List<String> two = new
	 * ArrayList<String>(); List<String> three = new ArrayList<String>();
	 * one.add("cardname"); two.add("LIKE"); three.add("luke");
	 * updateCardList(dbc.getFilteredList(one, two, three));
	 */
    }

    private void updateCardList(List<String> cardnames)
    {
	cardListModel.updateModel(cardnames);
	listCardList.setSelectedIndex(0);
	scrollPane.repaint();
	scrollPane.revalidate();
	scrollPane.getViewport().setViewPosition(new Point(10, 10));
	scrollPane.getViewport().setViewPosition(new Point(0, 0));
	this.lblNumCards.setText("Cards: " + cardnames.size());
    }

    private void populateFilters()
    {
	filterListModel.clear();

	for (int i = 0; i < column.size(); i++)
	{
	    filterListModel.addElement(column.get(i) + " " + op.get(i) + " "
		    + term.get(i));
	}

	listFilterList.setSelectedIndex(0);
	scrollPaneFilter.repaint();
	scrollPaneFilter.revalidate();
	scrollPaneFilter.getViewport().setViewPosition(new Point(10, 10));
	scrollPaneFilter.getViewport().setViewPosition(new Point(0, 0));

	updateCardList(dbc.getFilteredList(column, op, term));
    }
}

/*
 * @SuppressWarnings("serial") class FilterListModel extends DefaultListModel {
 * List<String> values;
 * 
 * FilterListModel() { values = new ArrayList<String>(); }
 * 
 * public int getSize() { return values.size(); }
 * 
 * public String getElementAt(int index) { return values.get(index); }
 * 
 * public void add(String value) { values.add(value); }
 * 
 * public Object remove(int index) { values.remove(index); return index; } }
 */
@SuppressWarnings("serial")
class CardListModel extends AbstractListModel
{
    List<String> values = new ArrayList<String>();

    CardListModel(List<String> list)
    {
	updateModel(list);
    }

    @Override
    public Object getElementAt(int index)
    {
	return values.get(index);
    }

    public String getCardName(int index)
    {
	return values.get(index);
    }

    @Override
    public int getSize()
    {
	return values.size();
    }

    public int updateModel(List<String> list)
    {
	values.clear();
	values = list;
	return getSize();
    }
}

/*
 * , "Ability", "Armor", "Card Name", "Card Type",
 * "Characteristics, Attributes, etc.", "Deploy Cost", "Destiny", "Expansion",
 * "Ferocity", "Force Aptitude", "Forfeit", "Game Text", "Hyperspeed", "Icons",
 * "Influence", "Landspeed", "Lore", "Maneuver", "Model Type", "Politics",
 * "Power", "Rarity", "Subtype", "Uniqueness", "", "Force Icons Dark Side",
 * "Force Icons Light Side", "Parsec Number", "", "Abbreviation / Nickname",
 * "Pulls", "Is Pulled", "Cancels", "Is Canceled By", "Combo", "Information",
 * "Rules", "Errata", "", "Inventory", "Needs"
 */