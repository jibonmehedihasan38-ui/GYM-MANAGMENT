import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class GymManagement extends JFrame implements FileOperations {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer = new JPanel(cardLayout);
    private DefaultTableModel tableModel;
    private JTable membershipTable;

    public GymManagement() {
        super("Iron Paradise Gym Management");
        setSize(900, 900); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
		
        ImageIcon smallLogo = new ImageIcon("D:\\Code\\GymManagement\\Logos\\SmallLogo.png");
        this.setIconImage(smallLogo.getImage());

        createLoginInterface();
        createMemberInterface();

        add(mainContainer);
        setVisible(true);
    }

    private void createLoginInterface() {
        JPanel loginPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bg = new ImageIcon("D:\\Code\\GymManagement\\Background\\Background.png");
                g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        ImageIcon bigIcon = new ImageIcon("D:\\Code\\GymManagement\\Logos\\BigLogo.png");
        Image scaledBig = bigIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel bigLogoLabel = new JLabel(new ImageIcon(scaledBig));

        JLabel uL = new JLabel("Staff Username:");
        uL.setForeground(Color.WHITE);
        uL.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel pL = new JLabel("Password:");
        pL.setForeground(Color.WHITE);
        pL.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField userField = new JTextField(15);
        userField.setFont(new Font("Arial", Font.PLAIN, 14));
        JPasswordField passField = new JPasswordField(15);
        passField.setFont(new Font("Arial", Font.PLAIN, 14));
        JButton loginBtn = new JButton("Access System");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.gridy = 0; loginPanel.add(bigLogoLabel, gbc);
        gbc.gridy = 1; loginPanel.add(uL, gbc);
        gbc.gridy = 2; loginPanel.add(userField, gbc);
        gbc.gridy = 3; loginPanel.add(pL, gbc);
        gbc.gridy = 4; loginPanel.add(passField, gbc);
        gbc.gridy = 5; loginPanel.add(loginBtn, gbc);

        loginBtn.addActionListener(e -> {
            if (userField.getText().equals("MEHEDI") && new String(passField.getPassword()).equals("Bongolover")) {
                cardLayout.show(mainContainer, "dashboard");
            } else {
                JOptionPane.showMessageDialog(this, "Access Denied!");
            }
        });
        mainContainer.add(loginPanel, "login");
    }

    private void createMemberInterface() {
        JPanel gymPanel = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bg = new ImageIcon("D:\\Code\\GymManagement\\Background\\Background.png");
                g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        gymPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerPanel.setOpaque(false);
        headerPanel.add(createIconButton("D:\\Code\\GymManagement\\Icons\\phone.png", "Support: +1-888-420-69"));
        headerPanel.add(createIconButton("D:\\Code\\GymManagement\\Icons\\profile.png", "Logged in as: Admin"));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setOpaque(false);

        // NEW: Name and ID Fields
        JLabel nameL = new JLabel("Member Full Name:"); nameL.setForeground(Color.WHITE);
        nameL.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField nameField = new JTextField(20);

        JLabel idL = new JLabel("Member ID:"); idL.setForeground(Color.WHITE);
        idL.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField idField = new JTextField(20);

        JLabel pl = new JLabel("Membership Plan:"); pl.setForeground(Color.WHITE);
        pl.setFont(new Font("Arial", Font.BOLD, 16));
        String[] plans = {"Basic ($30/mo)", "Pro ($60/mo)", "Elite ($100/mo)"};
        JComboBox<String> planList = new JComboBox<>(plans);
        
        JLabel payL = new JLabel("Payment:"); payL.setForeground(Color.WHITE);
        payL.setFont(new Font("Arial", Font.BOLD, 16));
        JRadioButton cash = new JRadioButton("Cash", true);
        JRadioButton card = new JRadioButton("Card/Online");
        cash.setForeground(Color.WHITE); card.setForeground(Color.WHITE);
        cash.setFont(new Font("Arial", Font.BOLD, 14));
        card.setFont(new Font("Arial", Font.BOLD, 14));
        cash.setOpaque(false); card.setOpaque(false);
        ButtonGroup payGroup = new ButtonGroup();
        payGroup.add(cash); payGroup.add(card);

        JLabel addL = new JLabel("Add-ons:"); addL.setForeground(Color.WHITE);
        addL.setFont(new Font("Arial", Font.BOLD, 16));
        JCheckBox pt = new JCheckBox("Personal Trainer ($50)");
        JCheckBox locker = new JCheckBox("Locker Room ($10)");
        JCheckBox sauna = new JCheckBox("Sauna Access ($20)");
        JCheckBox protein = new JCheckBox("Supplements ($15)");
        JCheckBox[] boxes = {pt, locker, sauna, protein};
        for(JCheckBox cb : boxes) {
            cb.setForeground(Color.WHITE);
            cb.setFont(new Font("Arial", Font.PLAIN, 14));
            cb.setOpaque(false);
        }

        JButton enrollBtn = new JButton("Complete Enrollment");
        enrollBtn.setFont(new Font("Arial", Font.BOLD, 14));

        // Add to panel
        inputPanel.add(nameL); inputPanel.add(nameField);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        inputPanel.add(idL); inputPanel.add(idField);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        inputPanel.add(pl); inputPanel.add(planList);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        inputPanel.add(payL); inputPanel.add(cash); inputPanel.add(card);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        inputPanel.add(addL);
        for(JCheckBox cb : boxes) inputPanel.add(cb);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        inputPanel.add(enrollBtn);

        // Updated Table Columns
        String[] columns = {"Name", "ID", "Plan", "Payment", "Add-ons", "Total"};
        tableModel = new DefaultTableModel(columns, 0);
        membershipTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(membershipTable);
        scrollPane.setPreferredSize(new Dimension(600, 250));

        enrollBtn.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            
            if(name.isEmpty() || id.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill Name and ID!");
                return;
            }

            double total = 0;
            String plan = (String) planList.getSelectedItem();
            if (plan.contains("30")) total += 30;
            else if (plan.contains("60")) total += 60;
            else if (plan.contains("100")) total += 100;

            StringBuilder addons = new StringBuilder();
            if(pt.isSelected()) { total += 50; addons.append("Trainer "); }
            if(locker.isSelected()) { total += 10; addons.append("Locker "); }
            if(sauna.isSelected()) { total += 20; addons.append("Sauna "); }
            if(protein.isSelected()) { total += 15; addons.append("Supps "); }

            String finalTotal = "$" + String.format("%.2f", total);
            String payment = cash.isSelected() ? "Cash" : "Card";

            // Add to UI Table
            tableModel.addRow(new Object[]{name, id, plan, payment, addons.toString(), finalTotal});
            
            // Save to File
            saveMemberToFile(name, id, plan, payment, addons.toString(), finalTotal);
            
            JOptionPane.showMessageDialog(this, "Enrollment Successful for: " + name);
            
            // Clear fields
            nameField.setText(""); idField.setText("");
            for(JCheckBox cb : boxes) cb.setSelected(false);
        });

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.setOpaque(false);
        topContainer.add(headerPanel, BorderLayout.NORTH);
        topContainer.add(inputPanel, BorderLayout.CENTER);

        gymPanel.add(topContainer, BorderLayout.NORTH);
        gymPanel.add(scrollPane, BorderLayout.CENTER);

        mainContainer.add(gymPanel, "dashboard");
    }

    private JLabel createIconButton(String path, String message) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { JOptionPane.showMessageDialog(null, message); }
        });
        return label;
    }

    @Override
    public void saveMemberToFile(String name, String id, String plan, String pay, String extras, String total) {
        try (FileWriter fw = new FileWriter("D:\\Code\\GymManagement\\gym_bookings.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("NAME: " + name + " | ID: " + id + " | PLAN: " + plan + " | PAY: " + pay + " | ADDONS: " + extras + " | TOTAL: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}