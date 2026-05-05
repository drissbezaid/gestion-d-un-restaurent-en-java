package gestion_restaurent;

import javax.swing.*;
import java.awt.*;

public class MaterielUI extends JFrame {

    public MaterielUI() {
        setTitle("Materiel");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        add(RestoUI.buildSidebar("Materiel", this), BorderLayout.WEST);

        JPanel topBar = RestoUI.buildTopBar("Materiel");
        JButton btnAjouter  = RestoUI.actionButton("+ Ajouter", new Color(29, 158, 117), Color.WHITE);
        JButton btnSupprimer = RestoUI.actionButton("Supprimer", new Color(252, 235, 235), new Color(163, 45, 45));
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        btns.setOpaque(false);
        btns.add(btnSupprimer);
        btns.add(btnAjouter);
        topBar.add(btns, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        DefaultListModel<String> modelMat = new DefaultListModel<>();
        JList<String> listMat = new JList<>(modelMat);
        listMat.setFont(new Font("SansSerif", Font.PLAIN, 14));
        listMat.setSelectionBackground(new Color(225, 245, 238));
        listMat.setSelectionForeground(new Color(15, 110, 86));
        listMat.setFixedCellHeight(48);
        listMat.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        add(new JScrollPane(listMat), BorderLayout.CENTER);

        btnAjouter.addActionListener(e -> {
            String mat = JOptionPane.showInputDialog(this, "Nom du materiel :");
            if (mat != null && !mat.trim().isEmpty()) {
                modelMat.addElement(mat.trim());
            }
        });

        btnSupprimer.addActionListener(e -> {
            int i = listMat.getSelectedIndex();
            if (i != -1) modelMat.remove(i);
        });

        setVisible(true);
    }
}