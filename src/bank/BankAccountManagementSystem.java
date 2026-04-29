package bank;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

class BankAccount {
    private final String accountNumber;
    private final String accountHolderName;
    private final double balance;
    private final double minimumBalance;

    public BankAccount(String accountNumber, String accountHolderName) {
        this(accountNumber, accountHolderName, 0.0, 0.0);
    }

    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this(accountNumber, accountHolderName, initialBalance, 0.0);
    }

    public BankAccount(
            String accountNumber,
            String accountHolderName,
            double initialBalance,
            double minimumBalance
    ) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.minimumBalance = minimumBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public String getAccountType() {
        return "Bank Account";
    }

    public String getAccountDetails() {
        return "Account Type       : " + getAccountType() + "\n"
                + "Account Number     : " + accountNumber + "\n"
                + "Account Holder     : " + accountHolderName + "\n"
                + "Current Balance    : " + balance + "\n"
                + "Minimum Balance    : " + minimumBalance;
    }
}

class SavingsAccount extends BankAccount {
    public SavingsAccount(String accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName);
    }

    public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
    }

    public SavingsAccount(
            String accountNumber,
            String accountHolderName,
            double initialBalance,
            double minimumBalance
    ) {
        super(accountNumber, accountHolderName, initialBalance, minimumBalance);
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }
}

class CurrentAccount extends BankAccount {
    public CurrentAccount(String accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName);
    }

    public CurrentAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
    }

    public CurrentAccount(
            String accountNumber,
            String accountHolderName,
            double initialBalance,
            double minimumBalance
    ) {
        super(accountNumber, accountHolderName, initialBalance, minimumBalance);
    }

    @Override
    public String getAccountType() {
        return "Current Account";
    }
}

public class BankAccountManagementSystem extends JFrame {
    private final JComboBox<String> accountTypeBox;
    private final JTextField accountNumberField;
    private final JTextField accountHolderField;
    private final JTextField initialBalanceField;
    private final JTextField minimumBalanceField;
    private final JRadioButton basicConstructorButton;
    private final JRadioButton balanceConstructorButton;
    private final JRadioButton fullConstructorButton;
    private final JTextArea detailsArea;
    private final JTable accountTable;
    private final DefaultTableModel tableModel;
    private final List<BankAccount> accounts;

    public BankAccountManagementSystem() {
        accounts = new ArrayList<>();
        accountTypeBox = new JComboBox<>(new String[]{"Savings Account", "Current Account"});
        accountNumberField = new JTextField(18);
        accountHolderField = new JTextField(18);
        initialBalanceField = new JTextField(18);
        minimumBalanceField = new JTextField(18);
        basicConstructorButton = new JRadioButton("Account No. + Holder");
        balanceConstructorButton = new JRadioButton("Add Initial Balance");
        fullConstructorButton = new JRadioButton("Add Minimum Balance");
        detailsArea = new JTextArea();
        tableModel = new DefaultTableModel(
                new String[]{"Type", "Account Number", "Holder", "Balance", "Minimum Balance"},
                0
        );
        accountTable = new JTable(tableModel);

        configureWindow();
        add(buildHeader(), BorderLayout.NORTH);
        add(buildContent(), BorderLayout.CENTER);
        add(buildFooter(), BorderLayout.SOUTH);
        updateFieldState();
    }

    private void configureWindow() {
        setTitle("Bank Account Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 620);
        setMinimumSize(new Dimension(850, 540));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));
    }

    private JPanel buildHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(16, 18, 8, 18));

        JLabel title = new JLabel("Bank Account Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel subtitle = new JLabel("Constructor overloading demo for Savings and Current accounts");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(new Color(70, 70, 70));

        panel.add(title, BorderLayout.NORTH);
        panel.add(subtitle, BorderLayout.SOUTH);
        return panel;
    }

    private JSplitPane buildContent() {
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                buildFormPanel(),
                buildDisplayPanel()
        );
        splitPane.setResizeWeight(0.34);
        splitPane.setBorder(BorderFactory.createEmptyBorder(0, 18, 0, 18));
        return splitPane;
    }

    private JPanel buildFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 215, 215)),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        ButtonGroup constructorGroup = new ButtonGroup();
        constructorGroup.add(basicConstructorButton);
        constructorGroup.add(balanceConstructorButton);
        constructorGroup.add(fullConstructorButton);
        fullConstructorButton.setSelected(true);

        basicConstructorButton.addActionListener(event -> updateFieldState());
        balanceConstructorButton.addActionListener(event -> updateFieldState());
        fullConstructorButton.addActionListener(event -> updateFieldState());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 0, 7, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;

        addFormRow(panel, gbc, "Account Type", accountTypeBox);
        addFormRow(panel, gbc, "Account Number", accountNumberField);
        addFormRow(panel, gbc, "Account Holder Name", accountHolderField);
        addFormRow(panel, gbc, "Initial Balance", initialBalanceField);
        addFormRow(panel, gbc, "Minimum Balance", minimumBalanceField);

        JLabel constructorLabel = new JLabel("Constructor Option");
        constructorLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridy++;
        panel.add(constructorLabel, gbc);

        gbc.gridy++;
        panel.add(basicConstructorButton, gbc);
        gbc.gridy++;
        panel.add(balanceConstructorButton, gbc);
        gbc.gridy++;
        panel.add(fullConstructorButton, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        JButton createButton = new JButton("Create Account");
        JButton clearButton = new JButton("Clear");
        createButton.addActionListener(event -> createAccount());
        clearButton.addActionListener(event -> clearForm());
        buttonPanel.add(createButton);
        buttonPanel.add(clearButton);

        gbc.gridy++;
        gbc.insets = new Insets(18, 0, 0, 0);
        panel.add(buttonPanel, gbc);

        gbc.gridy++;
        gbc.weighty = 1;
        panel.add(new JPanel(), gbc);
        return panel;
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, String labelText, java.awt.Component field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridy++;
        panel.add(label, gbc);
        gbc.gridy++;
        panel.add(field, gbc);
    }

    private JPanel buildDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 0));

        accountTable.setRowHeight(26);
        accountTable.setFillsViewportHeight(true);
        accountTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int row = accountTable.getSelectedRow();
                if (row >= 0 && row < accounts.size()) {
                    detailsArea.setText(accounts.get(row).getAccountDetails());
                }
            }
        });

        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        detailsArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JScrollPane(accountTable), BorderLayout.CENTER);
        panel.add(new JScrollPane(detailsArea), BorderLayout.SOUTH);
        detailsArea.setRows(7);
        return panel;
    }

    private JPanel buildFooter() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 18, 14, 18));
        JButton deleteButton = new JButton("Delete Selected Account");
        JButton exitButton = new JButton("Exit");
        deleteButton.addActionListener(event -> deleteSelectedAccount());
        exitButton.addActionListener(event -> dispose());
        panel.add(deleteButton);
        panel.add(exitButton);
        return panel;
    }

    private void updateFieldState() {
        boolean usesInitialBalance = balanceConstructorButton.isSelected() || fullConstructorButton.isSelected();
        boolean usesMinimumBalance = fullConstructorButton.isSelected();
        initialBalanceField.setEnabled(usesInitialBalance);
        minimumBalanceField.setEnabled(usesMinimumBalance);
    }

    private void createAccount() {
        String accountType = (String) accountTypeBox.getSelectedItem();
        String accountNumber = accountNumberField.getText().trim();
        String accountHolder = accountHolderField.getText().trim();

        if (accountNumber.isEmpty() || accountHolder.isEmpty()) {
            showError("Account number and account holder name are required.");
            return;
        }

        if (isDuplicateAccountNumber(accountNumber)) {
            showError("An account with this account number already exists.");
            return;
        }

        try {
            BankAccount account = buildAccount(accountType, accountNumber, accountHolder);
            accounts.add(account);
            tableModel.addRow(new Object[]{
                    account.getAccountType(),
                    account.getAccountNumber(),
                    account.getAccountHolderName(),
                    account.getBalance(),
                    account.getMinimumBalance()
            });
            detailsArea.setText(account.getAccountDetails());
            clearForm();
        } catch (NumberFormatException exception) {
            showError("Balance fields must contain valid numbers.");
        } catch (IllegalArgumentException exception) {
            showError(exception.getMessage());
        }
    }

    private BankAccount buildAccount(String accountType, String accountNumber, String accountHolder) {
        if (basicConstructorButton.isSelected()) {
            if ("Savings Account".equals(accountType)) {
                return new SavingsAccount(accountNumber, accountHolder);
            }
            return new CurrentAccount(accountNumber, accountHolder);
        }

        double initialBalance = parseMoney(initialBalanceField.getText(), "Initial balance");
        if (balanceConstructorButton.isSelected()) {
            if ("Savings Account".equals(accountType)) {
                return new SavingsAccount(accountNumber, accountHolder, initialBalance);
            }
            return new CurrentAccount(accountNumber, accountHolder, initialBalance);
        }

        double minimumBalance = parseMoney(minimumBalanceField.getText(), "Minimum balance");
        if ("Savings Account".equals(accountType)) {
            return new SavingsAccount(accountNumber, accountHolder, initialBalance, minimumBalance);
        }
        return new CurrentAccount(accountNumber, accountHolder, initialBalance, minimumBalance);
    }

    private double parseMoney(String value, String fieldName) {
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required for the selected constructor.");
        }

        double amount = Double.parseDouble(value.trim());
        if (amount < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative.");
        }
        return amount;
    }

    private boolean isDuplicateAccountNumber(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    private void deleteSelectedAccount() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow < 0) {
            showError("Please select an account from the table to delete.");
            return;
        }

        int modelRow = accountTable.convertRowIndexToModel(selectedRow);
        BankAccount selectedAccount = accounts.get(modelRow);
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Delete account " + selectedAccount.getAccountNumber() + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            accounts.remove(modelRow);
            tableModel.removeRow(modelRow);
            detailsArea.setText("");
        }
    }

    private void clearForm() {
        accountNumberField.setText("");
        accountHolderField.setText("");
        initialBalanceField.setText("");
        minimumBalanceField.setText("");
        accountNumberField.requestFocus();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankAccountManagementSystem().setVisible(true));
    }
}
