package gestion_restaurent;

import javax.swing.*;
import java.awt.*;

public class CommandeUI extends JFrame {

    public static DefaultListModel<String> modelCommande = new DefaultListModel<>();

    public CommandeUI() {
        setTitle("Commandes");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        add(RestoUI.buildSidebar("Commandes", this), BorderLayout.WEST);

        JPanel topBar = RestoUI.buildTopBar("Commandes");
        JButton btnAjouter   = RestoUI.actionButton("+ Nouvelle commande", new Color(29, 158, 117), Color.WHITE);
        JButton btnSupprimer = RestoUI.actionButton("Supprimer", new Color(252, 235, 235), new Color(163, 45, 45));
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        btns.setOpaque(false);
        btns.add(btnSupprimer);
        btns.add(btnAjouter);
        topBar.add(btns, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        JList<String> listCommande = new JList<>(modelCommande);
        listCommande.setFont(new Font("SansSerif", Font.PLAIN, 14));
        listCommande.setSelectionBackground(new Color(225, 245, 238));
        listCommande.setSelectionForeground(new Color(15, 110, 86));
        listCommande.setFixedCellHeight(48);
        listCommande.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(220, 0));
        rightPanel.setBackground(new Color(248, 248, 246));
        rightPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(220, 218, 210)));// top left bottom right//

        JLabel rightTitle = new JLabel("  Plats disponibles");
        rightTitle.setFont(new Font("SansSerif", Font.PLAIN, 11));
        rightTitle.setForeground(new Color(150, 148, 140));
        rightTitle.setPreferredSize(new Dimension(0, 36));
        rightPanel.add(rightTitle, BorderLayout.NORTH);

        JList<String> listPlats = new JList<>(MenuUI.modelMenu);
        listPlats.setFont(new Font("SansSerif", Font.PLAIN, 13));
        listPlats.setBackground(new Color(248, 248, 246));
        listPlats.setSelectionBackground(new Color(225, 245, 238));
        listPlats.setSelectionForeground(new Color(15, 110, 86));
        listPlats.setFixedCellHeight(40);
        rightPanel.add(new JScrollPane(listPlats), BorderLayout.CENTER);

        JButton btnAddPlat = RestoUI.actionButton("Ajouter à la commande", new Color(29, 158, 117), Color.WHITE);
        btnAddPlat.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 8));
        rightPanel.add(btnAddPlat, BorderLayout.SOUTH);

        JPanel content = new JPanel(new BorderLayout());
        content.add(new JScrollPane(listCommande), BorderLayout.CENTER);
        content.add(rightPanel, BorderLayout.EAST);
        add(content, BorderLayout.CENTER);

        btnAjouter.addActionListener(e -> {
            String numcStr = JOptionPane.showInputDialog(this, "Numéro de commande :");
            if (numcStr == null || numcStr.trim().isEmpty()) return;
            String numtStr = JOptionPane.showInputDialog(this, "Numéro de table :");
            if (numtStr == null || numtStr.trim().isEmpty()) return;
            try {
                int numc = Integer.parseInt(numcStr.trim());
                int numt = Integer.parseInt(numtStr.trim());
                for (int i = 0; i < modelCommande.size(); i++) {
                    if (modelCommande.get(i).startsWith("Commande #" + numc + "  —")) {
                        JOptionPane.showMessageDialog(this,
                            "La commande #" + numc + " existe déjà !",
                            "Doublon", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                modelCommande.addElement("Commande #" + numc + "  —  Table " + numt);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Numéro invalide !");
            }
        });

        btnSupprimer.addActionListener(e -> {
            int i = listCommande.getSelectedIndex();
            if (i != -1) {
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Supprimer « " + modelCommande.get(i) + " » ?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) modelCommande.remove(i);
            }
        });

        btnAddPlat.addActionListener(e -> {
            String plat = listPlats.getSelectedValue();
            int i = listCommande.getSelectedIndex();
            if (plat != null && i != -1) {
                modelCommande.set(i, modelCommande.get(i) + "  |  " + plat);
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez une commande et un plat !");
            }
        });

        setVisible(true);
    }
}