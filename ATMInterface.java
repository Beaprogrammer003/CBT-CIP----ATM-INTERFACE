import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATMInterface {
    private static Map<String, String> userCredentials = new HashMap<>();
    private static Map<String, Double> accountBalances = new HashMap<>();
    private static List<String> transactionHistory = new ArrayList<>();
    private static Map<String, String> recipientAccounts = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RegistrationFrame registrationFrame = new RegistrationFrame();
                registrationFrame.setVisible(true);
            }
        });
    }

    static class RegistrationFrame extends JFrame {
        private JTextField userIdField;
        private JPasswordField pinField;

        public RegistrationFrame() {
            // Set frame properties (size, title, etc.)
            setTitle("ATM Registration");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            // Initialize UI components
            JLabel userIdLabel = new JLabel("User ID:");
            userIdField = new JTextField(10);
            JLabel pinLabel = new JLabel("PIN:");
            pinField = new JPasswordField(10);
            JButton registerButton = new JButton("Register");

            // Add UI components to the frame using GridBagConstraints
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(userIdLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            add(userIdField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            add(pinLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            add(pinField, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(registerButton, gbc);

            // Add action listener to register button
            registerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    registerUser();
                }
            });
        }

        private void registerUser() {
            String userId = userIdField.getText();
            String pin = new String(pinField.getPassword());

            // Validate user input
            if (userId.isEmpty() || pin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter valid credentials.");
                return;
            }

            // Check if the user already exists
            if (userCredentials.containsKey(userId)) {
                JOptionPane.showMessageDialog(this, "User already exists. Please choose a different User ID.");
                return;
            }

            // Register the user
            userCredentials.put(userId, pin);
            accountBalances.put(userId, 0.0);
            JOptionPane.showMessageDialog(this, "Registration successful!");

            // Proceed to login
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);

            // Close the registration frame
            dispose();
        }
    }

    static class LoginFrame extends JFrame {
        private JTextField userIdField;
        private JPasswordField pinField;

        public LoginFrame() {
            // Set frame properties (size, title, etc.)
            setTitle("ATM Login");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            // Initialize UI components
            JLabel userIdLabel = new JLabel("User ID:");
            userIdField = new JTextField(10);
            JLabel pinLabel = new JLabel("PIN:");
            pinField = new JPasswordField(10);
            JButton loginButton = new JButton("Login");

            // Add UI components to the frame using GridBagConstraints
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(userIdLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            add(userIdField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            add(pinLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            add(pinField, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(loginButton, gbc);

            // Add action listener to login button
            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    login();
                }
            });
        }

        private void login() {
            String userId = userIdField.getText();
            String pin = new String(pinField.getPassword());

            // Validate user credentials
            if (isValidCredentials(userId, pin)) {
                // Open MainATMFrame upon successful login
                MainATMFrame mainATMFrame = new MainATMFrame(userId);
                mainATMFrame.setVisible(true);

                // Close the login frame
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
            }
        }

        private boolean isValidCredentials(String userId, String pin) {
            // Check if the user credentials exist in the stored map
            return userCredentials.containsKey(userId) && userCredentials.get(userId).equals(pin);
        }
    }

    static class MainATMFrame extends JFrame {
        private String userId;

        public MainATMFrame(String userId) {
            this.userId = userId;

            // Set frame properties (size, title, etc.)
            setTitle("ATM");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            // Initialize UI components
            JButton transactionsButton = new JButton("Transactions History");
            JButton withdrawButton = new JButton("Withdraw");
            JButton depositButton = new JButton("Deposit");
            JButton transferButton = new JButton("Transfer");
            JButton registerRecipientButton = new JButton("Register Recipient");
            JButton quitButton = new JButton("Quit");

            // Add UI components to the frame using GridBagConstraints
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            add(transactionsButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            add(withdrawButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(depositButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            add(transferButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.CENTER;
            add(registerRecipientButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.CENTER;
            add(quitButton, gbc);

            // Add action listeners to the buttons
            transactionsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showTransactionHistory();
                }
            });

            withdrawButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    performWithdrawal();
                }
            });

            depositButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    performDeposit();
                }
            });

            transferButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    performTransfer();
                }
            });

            registerRecipientButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    registerRecipient();
                }
            });

            quitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    quit();
                }
            });
        }

        private void showTransactionHistory() {
            TransactionsHistoryFrame transactionsHistoryFrame = new TransactionsHistoryFrame(userId);
            transactionsHistoryFrame.setVisible(true);
        }

        private void performWithdrawal() {
            WithdrawFrame withdrawFrame = new WithdrawFrame(userId);
            withdrawFrame.setVisible(true);
        }

        private void performDeposit() {
            DepositFrame depositFrame = new DepositFrame(userId);
            depositFrame.setVisible(true);
        }

        private void performTransfer() {
            TransferFrame transferFrame = new TransferFrame(userId);
            transferFrame.setVisible(true);
        }

        private void registerRecipient() {
            RegisterRecipientFrame registerRecipientFrame = new RegisterRecipientFrame(userId);
            registerRecipientFrame.setVisible(true);
        }

        private void quit() {
            System.exit(0);
        }
    }

    static class TransactionsHistoryFrame extends JFrame {
        private JTextArea historyTextArea;
        private String userId;

        public TransactionsHistoryFrame(String userId) {
            this.userId = userId;

            // Set frame properties (size, title, etc.)
            setTitle("Transaction History");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());

            // Initialize UI components
            historyTextArea = new JTextArea();
            historyTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(historyTextArea);

            // Add UI components to the frame
            add(scrollPane, BorderLayout.CENTER);
            // Load and display transaction history
            loadTransactionHistory();
        }

        private void loadTransactionHistory() {
            // Retrieve the transaction history for the user
            List<String> userTransactionHistory = getUserTransactionHistory(userId);

            // Set the transaction history to the text area
            StringBuilder sb = new StringBuilder();
            sb.append("Transaction History:\n\n");
            for (String transaction : userTransactionHistory) {
                sb.append(transaction);
                sb.append("\n");
            }
            historyTextArea.setText(sb.toString());
        }

        private List<String> getUserTransactionHistory(String userId) {
            List<String> userTransactionHistory = new ArrayList<>();
            for (String transaction : transactionHistory) {
                if (transaction.startsWith("User: " + userId)) {
                    userTransactionHistory.add(transaction);
                }
            }
            return userTransactionHistory;
        }
    }

    static class WithdrawFrame extends JFrame {
        private JTextField amountField;
        private String userId;
        JButton exitButton = new JButton("Exit");

        public WithdrawFrame(String userId) {
            this.userId = userId;

            // Set frame properties (size, title, etc.)
            setTitle("Withdraw");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            // Initialize UI components
            JLabel amountLabel = new JLabel("Amount:");
            amountField = new JTextField(10);
            JButton withdrawButton = new JButton("Withdraw");

            // Add UI components to the frame using GridBagConstraints
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(amountLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            add(amountField, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            add(withdrawButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(exitButton, gbc);

            // Add action listener to withdraw button
            withdrawButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    withdrawAmount();
                }
            });
            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close the frame
                }
            });
        }

        private void withdrawAmount() {
            String amountStr = amountField.getText();
            // Validate the amount and perform withdrawal
            double amount;
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                return;
            }

            double currentBalance = accountBalances.getOrDefault(userId, 0.0);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a positive amount.");
            } else if (currentBalance < amount) {
                JOptionPane.showMessageDialog(this, "Insufficient funds. Current balance: $" + currentBalance);
            } else {
                // Update account balance
                double newBalance = currentBalance - amount;
                accountBalances.put(userId, newBalance);

                // Add transaction to history
                String transaction = "User: " + userId + " | Type: Withdrawal | Amount: $" + amount;
                transactionHistory.add(transaction);

                JOptionPane.showMessageDialog(this, "Amount withdrawn successfully!");

                // Clear the input field
                amountField.setText("");
            }
        }
    }

    static class DepositFrame extends JFrame {
        private JTextField amountField;
        private String userId;
        JButton exitButton = new JButton("Exit");

        public DepositFrame(String userId) {
            this.userId = userId;

            // Set frame properties (size, title, etc.)
            setTitle("Deposit");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            // Initialize UI components
            JLabel amountLabel = new JLabel("Amount:");
            amountField = new JTextField(10);
            JButton depositButton = new JButton("Deposit");

            // Add UI components to the frame using GridBagConstraints
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(amountLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            add(amountField, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            add(depositButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(exitButton, gbc);

            // Add action listener to deposit button
            depositButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    depositAmount();
                }
            });
            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close the frame
                }
            });
        }

        private void depositAmount() {
            String amountStr = amountField.getText();
            // Validate the amount and perform deposit
            double amount;
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                return;
            }

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a positive amount.");
            } else {
                // Update account balance
                double currentBalance = accountBalances.getOrDefault(userId, 0.0);
                double newBalance = currentBalance + amount;
                accountBalances.put(userId, newBalance);

                // Add transaction to history
                String transaction = "User: " + userId + " | Type: Deposit | Amount: $" + amount;
                transactionHistory.add(transaction);

                JOptionPane.showMessageDialog(this, "Amount deposited successfully!");

                // Clear the input field
                amountField.setText("");
            }
        }
    }

    static class TransferFrame extends JFrame {
        private JTextField amountField;
        private JTextField recipientField;
        private String userId;
        JButton exitButton = new JButton("Exit");

        public TransferFrame(String userId) {
            this.userId = userId;

            // Set frame properties (size, title, etc.)
            setTitle("Transfer");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            // Initialize UI components
            JLabel amountLabel = new JLabel("Amount:");
            amountField = new JTextField(10);
            JLabel recipientLabel = new JLabel("Recipient Account:");
            recipientField = new JTextField(10);
            JButton transferButton = new JButton("Transfer");

            // Add UI components to the frame using GridBagConstraints
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(amountLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            add(amountField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            add(recipientLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            add(recipientField, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(transferButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            add(exitButton, gbc);

            // Add action listener to transfer button
            transferButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    transferAmount();
                }
            });
            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close the frame
                }
            });
        }

        private void transferAmount() {
            String amountStr = amountField.getText();
            String recipient = recipientField.getText();
            // Validate the amount and recipient account
            double amount;
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                return;
            }

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a positive amount.");
            } else if (!recipientAccounts.containsKey(recipient)) {
                JOptionPane.showMessageDialog(this, "Recipient account does not exist.");
            } else {
                double senderBalance = accountBalances.getOrDefault(userId, 0.0);
                double recipientBalance = accountBalances.getOrDefault(recipient, 0.0);

                if (senderBalance < amount) {
                    JOptionPane.showMessageDialog(this, "Insufficient funds. Current balance: $" + senderBalance);
                } else {
                    // Update account balances
                    double newSenderBalance = senderBalance - amount;
                    double newRecipientBalance = recipientBalance + amount;
                    accountBalances.put(userId, newSenderBalance);
                    accountBalances.put(recipient, newRecipientBalance);

                    // Add transactions to history
                    String senderTransaction = "User: " + userId + " | Type: Transfer Sent | Recipient: " + recipient + " | Amount: $" + amount;
                    String recipientTransaction = "User: " + recipient + " | Type: Transfer Received | Sender: " + userId + " | Amount: $" + amount;
                    transactionHistory.add(senderTransaction);
                    transactionHistory.add(recipientTransaction);

                    JOptionPane.showMessageDialog(this, "Amount transferred successfully!");

                    // Clear the input fields
                    amountField.setText("");
                    recipientField.setText("");
                }
            }
        }
    }

    static class RegisterRecipientFrame extends JFrame {
        private JTextField recipientField;
        private String userId;
        JButton exitButton = new JButton("Exit");

        public RegisterRecipientFrame(String userId) {
            this.userId = userId;

            // Set frame properties (size, title, etc.)
            setTitle("Register Recipient");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            // Initialize UI components
            JLabel recipientLabel = new JLabel("Recipient Account:");
            recipientField = new JTextField(10);
            JButton registerButton = new JButton("Register");

            // Add UI components to the frame using GridBagConstraints
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(recipientLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            add(recipientField, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            add(registerButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(exitButton, gbc);

            // Add action listener to register button
            registerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    registerRecipientAccount();
                }
            });
            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close the frame
                }
            });
        }

        private void registerRecipientAccount() {
            String recipientAccount = recipientField.getText();

            // Validate recipient account
            if (recipientAccount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid recipient account.");
                return;
            }

            // Check if the recipient account already exists
            if (recipientAccounts.containsKey(recipientAccount)) {
                JOptionPane.showMessageDialog(this, "Recipient account already registered.");
                return;
            }

            // Register the recipient account
            recipientAccounts.put(recipientAccount, userId);
            JOptionPane.showMessageDialog(this, "Recipient account registered successfully!");

            // Clear the input field
            recipientField.setText("");
        }
    }
}
