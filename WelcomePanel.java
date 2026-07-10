import java.awt.*;
import javax.swing.*;

public class WelcomePanel extends JPanel {

    public WelcomePanel(MainFrame frame){

        setBackground(Theme.BACKGROUND);
        setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setBackground(Theme.CARD);
        card.setPreferredSize(new Dimension(500,450));
        card.setLayout(new BoxLayout(card,BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("🧠");
        logo.setFont(new Font("Segoe UI Emoji",Font.PLAIN,60));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("MindMesh");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(Theme.TITLE_FONT);

        JLabel subtitle = new JLabel("Connect • Learn • Grow");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(Theme.NORMAL_FONT);

        JButton login = new JButton("Login");
        JButton register = new JButton("Register");

        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        register.setAlignmentX(Component.CENTER_ALIGNMENT);

        login.setPreferredSize(new Dimension(180,45));
        register.setPreferredSize(new Dimension(180,45));

        login.addActionListener(e->frame.showPage("LOGIN"));

        register.addActionListener(e->frame.showPage("REGISTER"));

        card.add(Box.createVerticalStrut(40));
        card.add(logo);
        card.add(Box.createVerticalStrut(10));
        card.add(title);
        card.add(subtitle);
        card.add(Box.createVerticalStrut(40));
        card.add(login);
        card.add(Box.createVerticalStrut(15));
        card.add(register);

        add(card);

    }

}