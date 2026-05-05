package gestion_restaurent;
import javax.swing.*;
import java.awt.*;

public class AdditionUI extends JFrame {

    private DefaultListModel<String> modelAddition = new DefaultListModel<>();
    private JLabel lblTotal;
    private JLabel lblTable;
    private int numTable = 0;

    public AdditionUI() {
        setTitle("Addition");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        add(RestoUI.buildSidebar("Addition", this), BorderLayout.WEST);

        // ── Top bar ──────────────────────────────────────────────────────────
        JPanel topBar = RestoUI.buildTopBar("Addition");
        JButton btnImprimer  = RestoUI.actionButton("Imprimer", new Color(29, 158, 117), Color.WHITE);
        JButton btnReinitialiser = RestoUI.actionButton("Réinitialiser", new Color(252, 235, 235), new Color(163, 45, 45));
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        btns.setOpaque(false);
        btns.add(btnReinitialiser);
        btns.add(btnImprimer);
        topBar.add(btns, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        // ── Center: ticket de caisse ─────────────────────────────────────────
        JPanel ticketWrapper = new JPanel(new GridBagLayout());
        ticketWrapper.setBackground(new Color(248, 248, 246));

        JPanel ticket = new JPanel();
        ticket.setLayout(new BoxLayout(ticket, BoxLayout.Y_AXIS));
        ticket.setBackground(Color.WHITE);
        ticket.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 218, 210), 1),
            BorderFactory.createEmptyBorder(24, 32, 24, 32)
        ));
        ticket.setPreferredSize(new Dimension(480, 600));

        JLabel nomResto = new JLabel("RESTAURANT");
        nomResto.setFont(new Font("SansSerif", Font.BOLD, 20));
        nomResto.setForeground(new Color(29, 158, 117));
        nomResto.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblTable = new JLabel("Table : —");
        lblTable.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblTable.setForeground(new Color(120, 118, 110));
        lblTable.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator sep1 = new JSeparator();
        sep1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep1.setForeground(new Color(220, 218, 210));

        JPanel headers = new JPanel(new BorderLayout());
        headers.setOpaque(false);
        headers.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        JLabel hPlat = new JLabel("Plat");
        JLabel hPrix = new JLabel("Prix");
        hPlat.setFont(new Font("SansSerif", Font.BOLD, 12));
        hPrix.setFont(new Font("SansSerif", Font.BOLD, 12));
        hPlat.setForeground(new Color(120, 118, 110));
        hPrix.setForeground(new Color(120, 118, 110));
        headers.add(hPlat, BorderLayout.WEST);
        headers.add(hPrix, BorderLayout.EAST);

        JList<String> listItems = new JList<>(modelAddition);
        listItems.setFont(new Font("SansSerif", Font.PLAIN, 13));
        listItems.setBackground(Color.WHITE);
        listItems.setSelectionBackground(new Color(225, 245, 238));
        listItems.setSelectionForeground(new Color(15, 110, 86));
        listItems.setFixedCellHeight(36);
        listItems.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        listItems.setCellRenderer(new TwoColumnRenderer());

        JScrollPane scrollItems = new JScrollPane(listItems);
        scrollItems.setBorder(BorderFactory.createEmptyBorder());
        scrollItems.setAlignmentX(Component.LEFT_ALIGNMENT);

        JSeparator sep2 = new JSeparator();
        sep2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep2.setForeground(new Color(220, 218, 210));

        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setOpaque(false);
        totalPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        JLabel lTotalLabel = new JLabel("TOTAL");
        lTotalLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTotal = new JLabel("0.00 DH");
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTotal.setForeground(new Color(29, 158, 117));
        totalPanel.add(lTotalLabel, BorderLayout.WEST);
        totalPanel.add(lblTotal, BorderLayout.EAST);

        JSeparator sep3 = new JSeparator();
        sep3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep3.setForeground(new Color(220, 218, 210));

        JLabel merci = new JLabel("Merci de votre visite !");
        merci.setFont(new Font("SansSerif", Font.ITALIC, 12));
        merci.setForeground(new Color(150, 148, 140));
        merci.setAlignmentX(Component.CENTER_ALIGNMENT);

        ticket.add(nomResto);
        ticket.add(Box.createVerticalStrut(4));
        ticket.add(lblTable);
        ticket.add(Box.createVerticalStrut(16));
        ticket.add(sep1);
        ticket.add(Box.createVerticalStrut(8));
        ticket.add(headers);
        ticket.add(Box.createVerticalStrut(4));
        ticket.add(scrollItems);
        ticket.add(Box.createVerticalStrut(8));
        ticket.add(sep2);
        ticket.add(Box.createVerticalStrut(12));
        ticket.add(totalPanel);
        ticket.add(Box.createVerticalStrut(12));
        ticket.add(sep3);
        ticket.add(Box.createVerticalStrut(12));
        ticket.add(merci);

        ticketWrapper.add(ticket);
        add(ticketWrapper, BorderLayout.CENTER);

        // ── Right panel ───────────────────────────────────────────────────────
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(260, 0));
        rightPanel.setBackground(new Color(248, 248, 246));
        rightPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(220, 218, 210)));

        JLabel rightTitle = new JLabel("  Commandes");
        rightTitle.setFont(new Font("SansSerif", Font.PLAIN, 11));
        rightTitle.setForeground(new Color(150, 148, 140));
        rightTitle.setPreferredSize(new Dimension(0, 36));
        rightPanel.add(rightTitle, BorderLayout.NORTH);

        JList<String> listCommandes = new JList<>(new DefaultListModel<>());
        listCommandes.setFont(new Font("SansSerif", Font.PLAIN, 13));
        listCommandes.setBackground(new Color(248, 248, 246));
        listCommandes.setSelectionBackground(new Color(225, 245, 238));
        listCommandes.setSelectionForeground(new Color(15, 110, 86));
        listCommandes.setFixedCellHeight(40);
        rightPanel.add(new JScrollPane(listCommandes), BorderLayout.CENTER);

        JButton btnManuel  = RestoUI.actionButton("Saisie manuelle", new Color(243, 242, 238), new Color(80, 78, 72));
        JButton btnCharger = RestoUI.actionButton("Charger la commande", new Color(29, 158, 117), Color.WHITE);
        btnManuel.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 8));
        btnCharger.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 8));

        JPanel southBtns = new JPanel(new GridLayout(2, 1, 0, 4));
        southBtns.setOpaque(false);
        southBtns.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        southBtns.add(btnManuel);
        southBtns.add(btnCharger);
        rightPanel.add(southBtns, BorderLayout.SOUTH);

        add(rightPanel, BorderLayout.EAST);

        // ── Actions ───────────────────────────────────────────────────────────
        btnManuel.addActionListener(e -> {
            String tStr = JOptionPane.showInputDialog(this, "Numéro de table :");
            if (tStr == null || tStr.trim().isEmpty()) return;
            try {
                numTable = Integer.parseInt(tStr.trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Numéro invalide !");
                return;
            }
            lblTable.setText("Table : " + numTable);
            modelAddition.clear();

            boolean continuer = true;
            while (continuer) {
                String[] platsArray = new String[MenuUI.modelMenu.size()];
                for (int i = 0; i < MenuUI.modelMenu.size(); i++) platsArray[i] = MenuUI.modelMenu.get(i);
                if (platsArray.length == 0) {
                    JOptionPane.showMessageDialog(this, "Le menu est vide. Ajoutez des plats dans la section Menu.");
                    break;
                }
                String choix = (String) JOptionPane.showInputDialog(
                    this, "Sélectionnez un plat :", "Ajouter un plat",
                    JOptionPane.PLAIN_MESSAGE, null, platsArray, platsArray[0]
                );
                if (choix != null) { modelAddition.addElement(choix); recalculerTotal(); }
                continuer = JOptionPane.showConfirmDialog(this, "Ajouter un autre plat ?", "Suite",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            }
        });

        btnReinitialiser.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Réinitialiser l'addition ?", "Confirmation",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                modelAddition.clear();
                lblTotal.setText("0.00 DH");
                lblTable.setText("Table : —");
                numTable = 0;
            }
        });

        btnImprimer.addActionListener(e -> {
            if (modelAddition.isEmpty()) { JOptionPane.showMessageDialog(this, "L'addition est vide !"); return; }
            StringBuilder sb = new StringBuilder();
            sb.append("===== ADDITION =====\n");
            sb.append("Table : ").append(numTable == 0 ? "—" : numTable).append("\n\n");
            for (int i = 0; i < modelAddition.size(); i++) sb.append("  ").append(modelAddition.get(i)).append("\n");
            sb.append("\n--------------------\n");
            sb.append("TOTAL : ").append(lblTotal.getText()).append("\n");
            sb.append("====================\n\nMerci de votre visite !");
            JTextArea area = new JTextArea(sb.toString());
            area.setFont(new Font("Monospaced", Font.PLAIN, 13));
            area.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(area), "Aperçu impression", JOptionPane.PLAIN_MESSAGE);
        });

        setVisible(true);
    }

    private void recalculerTotal() {
        double total = 0;
        for (int i = 0; i < modelAddition.size(); i++) {
            try {
                String[] parts = modelAddition.get(i).split("—");
                if (parts.length >= 2)
                    total += Double.parseDouble(parts[parts.length - 1].trim().replace(" DH", "").trim());
            } catch (NumberFormatException ignored) {}
        }
        lblTotal.setText(String.format("%.2f DH", total));
    }

    private static class TwoColumnRenderer extends JPanel implements ListCellRenderer<String> {
        private final JLabel lblNom  = new JLabel();
        private final JLabel lblPrix = new JLabel();

        TwoColumnRenderer() {
            setLayout(new BorderLayout());
            setOpaque(true);
            lblNom.setFont(new Font("SansSerif", Font.PLAIN, 13));
            lblPrix.setFont(new Font("SansSerif", Font.PLAIN, 13));
            lblPrix.setForeground(new Color(29, 158, 117));
            add(lblNom,  BorderLayout.WEST);
            add(lblPrix, BorderLayout.EAST);
            setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value,
                int index, boolean isSelected, boolean cellHasFocus) {
            String[] parts = value != null ? value.split("—") : new String[]{"", ""};
            lblNom.setText(parts[0].trim());
            lblPrix.setText(parts.length >= 2 ? parts[parts.length - 1].trim() : "");
            setBackground(isSelected ? new Color(225, 245, 238) : Color.WHITE);
            lblNom.setForeground(isSelected ? new Color(15, 110, 86) : new Color(40, 40, 40));
            return this;
        }
    }
}