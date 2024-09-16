import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    private JFrame frame;
    private JTextField userIdField;
    private JPasswordField passwordField;

    public LoginForm() {
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // User ID label and text field
        JLabel userIdLabel = new JLabel("User ID:");
        userIdField = new JTextField(10);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(10);

        // Login button with action listener
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String password = new String(passwordField.getPassword());

                // Check login credentials
                if (userId.equals("user") && password.equals("password")) {
                    // Close the login form
                    frame.dispose();

                    // Open the reservation system if login is successful
                    new OnlineReservationSystem();  // This will invoke the constructor and call createGUI()
                } else {
                    // Show error message if login fails
                    JOptionPane.showMessageDialog(frame, "Invalid User ID or Password");
                }
            }
        });

        // Add components to the frame
        frame.add(userIdLabel);
        frame.add(userIdField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);

        // Set up the frame
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
