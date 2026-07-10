import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class RegisterPanel extends JPanel {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField collegeField;
    private JTextField cityField;
    private JTextArea bioArea;

    public RegisterPanel(MainFrame frame, UserManager userManager) {

        setBackground(Theme.BACKGROUND);
        setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(500,650));
        card.setBackground(Theme.CARD);
        card.setLayout(new GridLayout(0,1,8,8));

        JLabel title = new JLabel("🌸 Create Account");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(Theme.TITLE_FONT);

        nameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        collegeField = new JTextField();
        cityField = new JTextField();
        bioArea = new JTextArea(3,20);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");

        registerButton.setBackground(Theme.PRIMARY);
        registerButton.setForeground(Color.WHITE);

        registerButton.addActionListener(e -> {

            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = String.valueOf(passwordField.getPassword());
            String college = collegeField.getText().trim();
            String city = cityField.getText().trim();
            String bio = bioArea.getText().trim();

            if(name.isEmpty() || email.isEmpty() || password.isEmpty()){

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all required fields."
                );
                return;
            }

            ArrayList<String> known = new ArrayList<>();
            ArrayList<String> learning = new ArrayList<>();

            User user = new User(
                    name,
                    email,
                    password,
                    college,
                    city,
                    bio,
                    known,
                    learning
            );

            boolean success = userManager.registerUser(user);

            if(success){

                JOptionPane.showMessageDialog(
                        this,
                        "Registration Successful!"
                );

                frame.showPage("LOGIN");

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Email already exists."
                );

            }

        });

        backButton.addActionListener(e ->
                frame.showPage("WELCOME"));

        card.add(title);

        card.add(new JLabel("Full Name"));
        card.add(nameField);

        card.add(new JLabel("Email"));
        card.add(emailField);

        card.add(new JLabel("Password"));
        card.add(passwordField);

        card.add(new JLabel("College"));
        card.add(collegeField);

        card.add(new JLabel("City"));
        card.add(cityField);

        card.add(new JLabel("About Yourself"));
        card.add(new JScrollPane(bioArea));

        card.add(registerButton);
        card.add(backButton);

        add(card);

    }

}