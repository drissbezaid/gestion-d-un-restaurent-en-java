package gestion_restaurent;

import javax.swing.*;
import java.awt.*;

public class MenuUI extends JFrame{

	public MenuUI() {
		setTitle("MENU");
		setSize(1200,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new BorderLayout());
		add(panelMenu, BorderLayout.CENTER);
		panelMenu.add(new JLabel ("MENU", SwingConstants.CENTER), BorderLayout.NORTH);
		//liste des menu//
		DefaultListModel<String> modelMenu = new DefaultListModel<>();
		JList<String> listMenu = new JList<>(modelMenu);
		panelMenu.add(new JScrollPane(listMenu), BorderLayout.CENTER);
		JButton btnAjouterplat = new JButton("Ajouter ");
		JButton btnSupprimerplat = new JButton("Supprimer");
		setVisible(true);
		
	}
	

}
