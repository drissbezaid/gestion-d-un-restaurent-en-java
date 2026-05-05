package gestion_restaurent;

import javax.swing.*;
import java.awt.*;

public class RestoUI extends JFrame {

    public RestoUI() {
        setTitle("Gestion Restaurant");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(248, 248, 246));

        add(buildSidebar("", this), BorderLayout.WEST);

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(new Color(248, 248, 246));

        JPanel welcome = new JPanel();
        welcome.setLayout(new BoxLayout(welcome, BoxLayout.Y_AXIS));
        welcome.setBackground(new Color(248, 248, 246));

        JLabel title = new JLabel("Bienvenue");
        title.setFont(new Font("SansSerif", Font.PLAIN, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel("Selectionnez une section pour commencer");
        sub.setFont(new Font("SansSerif", Font.PLAIN, 14));
        sub.setForeground(new Color(120, 118, 110));
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcome.add(title);
        welcome.add(Box.createVerticalStrut(8));
        welcome.add(sub);
        center.add(welcome);

        add(center, BorderLayout.CENTER);
        setVisible(true);
    }

    static JButton navButton(String text, java.awt.event.ActionListener action) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        btn.setBackground(new Color(243, 242, 238));
        btn.setForeground(new Color(80, 78, 72));
        btn.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(action);
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(225, 245, 238));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(243, 242, 238));
            }
        });
        return btn;
    }

    static JButton actionButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    static JPanel buildSidebar(String active, JFrame frame) {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBackground(new Color(243, 242, 238));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(220, 218, 210)));

        JLabel sideTitle = new JLabel("NAVIGATION");
        sideTitle.setFont(new Font("SansSerif", Font.PLAIN, 10));
        sideTitle.setForeground(new Color(150, 148, 140));
        sideTitle.setBorder(BorderFactory.createEmptyBorder(20, 16, 8, 16));
        sideTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(sideTitle);

        String[] labels = {"Menu", "Commandes", "Materiel", "Addition"};  
        for (String label : labels) {
            JButton btn = navButton("  " + label, e -> {
                frame.dispose();
                switch (label) {
                    case "Menu"      -> new MenuUI();
                    case "Commandes" -> new CommandeUI();
                    case "Materiel"  -> new MaterielUI();
                    case "Addition"  -> new AdditionUI();  
                }
            });
            if (label.equals(active)) {
                btn.setBackground(new Color(225, 245, 238));
                btn.setForeground(new Color(15, 110, 86));
                btn.setFont(new Font("SansSerif", Font.BOLD, 13));
            }
            sidebar.add(btn);
        }
        sidebar.add(Box.createVerticalGlue());
        return sidebar;
    }


    static JPanel buildTopBar(String title) {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(Color.WHITE);
        bar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 218, 210)));
        bar.setPreferredSize(new Dimension(0, 52));

        JLabel lbl = new JLabel("  " + title);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 15));
        bar.add(lbl, BorderLayout.WEST);
        return bar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RestoUI::new);
    }
}