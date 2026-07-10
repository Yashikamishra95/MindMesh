import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel {

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPanel(MainFrame frame, UserManager userManager) {

        setBackground(Theme.BACKGROUND);
        setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(450, 420));
        card.setBackground(Theme.CARD);
        card.setLayout(new GridLayout(0,1,10,10));

        JLabel title = new JLabel("🌸 Login");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(Theme.TITLE_FONT);

        JLabel emailLabel = new JLabel("Email");
        emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");

        loginButton.setBackground(Theme.PRIMARY);
        loginButton.setForeground(Color.WHITE);

        loginButton.addActionListener(e -> {

            String email = emailField.getText().trim();
            String password = String.valueOf(passwordField.getPassword());

            if (userManager.login(email, password)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Welcome " + userManager.getCurrentUser().getName() + "!"
                );

                frame.loginSuccess();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Email or Password!"
                );

            }

        });

        backButton.addActionListener(e -> frame.showPage("WELCOME"));

        card.add(title);
        card.add(emailLabel);
        card.add(emailField);
        card.add(passwordLabel);
        card.add(passwordField);
        card.add(loginButton);
        card.add(backButton);

        add(card);

    }

}