/*    
    Copyright 2011 Andrew Nelson
    
    This file is part of SWCCG Database.

    SWCCG Database is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SWCCG Database is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with SWCCG Database.  If not, see <http://www.gnu.org/licenses/>.
*/



package com.swccgdb;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;

@SuppressWarnings("serial")
public class About extends JDialog
{
    static About dialog;

    private final JPanel contentPanel = new JPanel();

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
	try
	{
	    dialog = new About();
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    dialog.setVisible(true);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    /**
     * Create the dialog.
     */
    public About()
    {
	setTitle("About SWCCG Database");
	setResizable(false);
	setIconImage(Toolkit.getDefaultToolkit().getImage(
		About.class.getResource("/com/swccgdb/resources/imperial.png")));
	setBounds(100, 100, 450, 300);
	getContentPane().setLayout(new BorderLayout());
	contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(contentPanel, BorderLayout.CENTER);
	contentPanel.setLayout(null);

	JPanel panel = new JPanel();
	panel.setBounds(168, 0, 264, 220);
	contentPanel.add(panel);
	panel.setLayout(new BorderLayout(0, 0));
	
	JScrollPane scrollPane = new JScrollPane();
	panel.add(scrollPane, BorderLayout.CENTER);
	
	JEditorPane txtrDisclosure = new JEditorPane();
	txtrDisclosure.setBackground(UIManager.getColor("control"));
	txtrDisclosure.setEditable(false);
	txtrDisclosure.setContentType("text/html");
	scrollPane.setViewportView(txtrDisclosure);
	txtrDisclosure.setFont(new Font("Tahoma", Font.PLAIN, 10));
	txtrDisclosure.setText("<html>\r\n<body>\r\nThis program is distributed by me under the GNU GPLv3. If you would like a copy of the license, please visit <a href=\"http://www.gnu.org/licenses/gpl-3.0-standalone.html\">this site</a>\r\n</body>\r\n</html>");

	JPanel panel_1 = new JPanel();
	panel_1.setBounds(12, 13, 142, 143);
	contentPanel.add(panel_1);
	panel_1.setLayout(new BorderLayout(0, 0));

	ImageIcon icon = new ImageIcon(
		About.class.getResource("/com/swccgdb/resources/imperial.png"));
	Image img = icon.getImage();
	img = img.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
	icon = new ImageIcon(img);

	JLabel lblImg = new JLabel(icon);

	panel_1.add(lblImg);

	JTextArea txtrSwccgDatabase = new JTextArea();
	txtrSwccgDatabase.setBackground(UIManager.getColor("control"));
	txtrSwccgDatabase.setFont(new Font("Tahoma", Font.PLAIN, 10));
	txtrSwccgDatabase
		.setText("SWCCG Database v0.3\r\nCopyright 2011 Andrew Nelson");
	txtrSwccgDatabase.setBounds(12, 170, 144, 37);
	contentPanel.add(txtrSwccgDatabase);
	{
	    JPanel buttonPane = new JPanel();
	    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    {
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent arg0)
		    {
			setV(false);
		    }
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	    }
	}
    }
    
    private void setV(boolean set)
    {
	this.setVisible(set);
    }
}
