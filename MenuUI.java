package gestion_restaurent;

import javax.swing.*;
import java.awt.*;

public class MenuUI extends JFrame {

    public static DefaultListModel<String> modelMenu = new DefaultListModel<>();

    public MenuUI() {
        setTitle("Menu");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        add(RestoUI.buildSidebar("Menu", this), BorderLayout.WEST);

        JPanel topBar = RestoUI.buildTopBar("Menu");
        JButton btnAjouter  = RestoUI.actionButton("+ Ajouter", new Color(29, 158, 117), Color.WHITE);
        JButton btnSupprimer = RestoUI.actionButton("Supprimer", new Color(252, 235, 235), new Color(163, 45, 45));
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        btns.setOpaque(false);
        btns.add(btnSupprimer);
        btns.add(btnAjouter);
        topBar.add(btns, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        JList<String> listMenu = new JList<>(modelMenu);
        listMenu.setFont(new Font("SansSerif", Font.PLAIN, 14));
        listMenu.setSelectionBackground(new Color(225, 245, 238));
        listMenu.setSelectionForeground(new Color(15, 110, 86));
        listMenu.setFixedCellHeight(48);
        listMenu.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        add(new JScrollPane(listMenu), BorderLayout.CENTER);

        btnAjouter.addActionListener(e -> {
            String nom = JOptionPane.showInputDialog(this, "Nom du plat :");
            if (nom != null && !nom.trim().isEmpty()) {
                String prixStr = JOptionPane.showInputDialog(this, "Prix (DH) :");
                if (prixStr != null && !prixStr.trim().isEmpty()) {
                    try {
                        double prix = Double.parseDouble(prixStr.trim());
                        modelMenu.addElement(nom.trim() + "  —  " + prix + " DH");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Prix invalide !");
                    }
                }
            }
        });

        btnSupprimer.addActionListener(e -> {
            int i = listMenu.getSelectedIndex();
            if (i != -1) modelMenu.remove(i);
        });

        setVisible(true);
    }
}