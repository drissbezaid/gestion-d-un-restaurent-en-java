package gestion_restaurent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MaterielUI extends JFrame {

    private static DefaultListModel<String> modelMat = new DefaultListModel<>();

    public MaterielUI() {
        setTitle("Matériel");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        add(RestoUI.buildSidebar("Materiel", this), BorderLayout.WEST);

        JPanel topBar = RestoUI.buildTopBar("Matériel");
        JButton btnAjouter   = RestoUI.actionButton("+ Ajouter",new Color(29, 158, 117), Color.WHITE);
        JButton btnSupprimer = RestoUI.actionButton("Supprimer", new Color(252, 235, 235), new Color(163, 45, 45));
        JButton btnCasse     = RestoUI.actionButton("⚠ Signaler cassé",new Color(255, 243, 205), new Color(133, 77, 14));
        JButton btnRapport   = RestoUI.actionButton("Rapport", new Color(243, 242, 238), new Color(80, 78, 72));
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        btns.setOpaque(false);
        btns.add(btnRapport);
        btns.add(btnCasse);
        btns.add(btnSupprimer);
        btns.add(btnAjouter);
        topBar.add(btns, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        JList<String> listMat = new JList<>(modelMat);
        listMat.setFont(new Font("SansSerif", Font.PLAIN, 14));
        listMat.setSelectionBackground(new Color(225, 245, 238));
        listMat.setSelectionForeground(new Color(15, 110, 86));
        listMat.setFixedCellHeight(48);
        listMat.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        listMat.setCellRenderer(new MaterielRenderer());
        add(new JScrollPane(listMat), BorderLayout.CENTER);

        btnAjouter.addActionListener(e -> {
            String mat = JOptionPane.showInputDialog(this, "Nom du matériel :");
            if (mat == null || mat.trim().isEmpty()) return;
            for (int i = 0; i < modelMat.size(); i++) {
                String nom = modelMat.get(i).split("  —  ")[0].trim();
                if (nom.equalsIgnoreCase(mat.trim())) {
                    JOptionPane.showMessageDialog(this,
                        "« " + mat.trim() + " » existe déjà !",
                        "Doublon", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            modelMat.addElement(mat.trim() + "  —  OK");
        });

        btnSupprimer.addActionListener(e -> {
            int i = listMat.getSelectedIndex();
            if (i != -1) {
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Supprimer « " + modelMat.get(i).split("  —  ")[0] + " » ?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) modelMat.remove(i);
            }
        });

        btnCasse.addActionListener(e -> {
            int i = listMat.getSelectedIndex();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "Sélectionnez un matériel à signaler.");
                return;
            }
            String entry = modelMat.get(i);
            String nom   = entry.split("  —  ")[0].trim();
            if (entry.contains("CASSÉ")) {
                JOptionPane.showMessageDialog(this, "« " + nom + " » est déjà signalé cassé.");
                return;
            }
            String raison = JOptionPane.showInputDialog(this, "Description du problème pour « " + nom + " » :");
            if (raison == null || raison.trim().isEmpty()) return;
            modelMat.set(i, nom + "  —  CASSÉ : " + raison.trim());
        });

        btnRapport.addActionListener(e -> {
            List<String> ok    = new ArrayList<>();
            List<String> casse = new ArrayList<>();
            for (int i = 0; i < modelMat.size(); i++) {
                String entry = modelMat.get(i);
                if (entry.contains("CASSÉ")) casse.add(entry);
                else ok.add(entry);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("====== RAPPORT MATÉRIEL ======\n\n");
            sb.append("✅ En état (").append(ok.size()).append(")\n");
            ok.forEach(s -> sb.append("  • ").append(s.split("  —  ")[0]).append("\n"));
            sb.append("\n⚠ Cassé / à réparer (").append(casse.size()).append(")\n");
            if (casse.isEmpty()) {
                sb.append("  Aucun matériel cassé.\n");
            } else {
                casse.forEach(s -> {
                    String[] p = s.split("  —  CASSÉ : ");
                    sb.append("  • ").append(p[0].trim());
                    if (p.length > 1) sb.append(" → ").append(p[1].trim());
                    sb.append("\n");
                });
            }
            sb.append("\n==============================");
            JTextArea area = new JTextArea(sb.toString());
            area.setFont(new Font("Monospaced", Font.PLAIN, 13));
            area.setEditable(false);
            area.setBackground(new Color(248, 248, 246));
            JOptionPane.showMessageDialog(this, new JScrollPane(area),
                "Rapport matériel", JOptionPane.PLAIN_MESSAGE);
        });

        setVisible(true);
    }

    private static class MaterielRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            String txt = value != null ? value.toString() : "";
            if (txt.contains("CASSÉ")) {
                setForeground(isSelected ? new Color(163, 45, 45) : new Color(200, 50, 50));
                setText("⚠  " + txt);
            } else {
                setForeground(isSelected ? new Color(15, 110, 86) : new Color(40, 40, 40));
                setText("✓  " + txt);
            }
            return this;
        }
    }
}