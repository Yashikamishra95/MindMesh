import java.awt.*;
import javax.swing.*;

public class Sidebar extends JPanel {

    private JButton dashboardButton;
    private JButton profileButton;
    private JButton matchButton;
    private JButton requestButton;
    private JButton leaderboardButton;
    private JButton settingsButton;
    private JButton logoutButton;

    public Sidebar() {

        setPreferredSize(new Dimension(250,720));
        setBackground(Theme.SIDEBAR);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20));

        // Logo
        JLabel logo = new JLabel("🧠");
        logo.setFont(new Font("Segoe UI Emoji", Font.PLAIN,48));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("MindMesh");
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Connect • Learn • Grow");
        subtitle.setFont(new Font("Segoe UI",Font.PLAIN,13));
        subtitle.setForeground(new Color(255,245,245));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(logo);
        add(Box.createVerticalStrut(5));
        add(title);
        add(subtitle);
        add(Box.createVerticalStrut(20));

        dashboardButton = createButton("🏠 Dashboard");
        profileButton = createButton("👤 My Profile");
        matchButton = createButton("🎯 Skill Match");
        requestButton = createButton("📨 Requests");
        leaderboardButton = createButton("🏆 Leaderboard");
        settingsButton = createButton("⚙ Settings");
        logoutButton = createButton("🚪 Logout");

        add(dashboardButton);
        add(profileButton);
        add(matchButton);
        add(requestButton);
        add(leaderboardButton);
        add(settingsButton);

        add(Box.createVerticalGlue());

        add(logoutButton);

    }

    private JButton createButton(String text){

        JButton button = new JButton(text);

        button.setMaximumSize(new Dimension(250,55));
        button.setPreferredSize(new Dimension(250,55));

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setBackground(Theme.SIDEBAR);
        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setForeground(Color.WHITE);

        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.setFont(new Font("Segoe UI",Font.BOLD,16));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setBorder(BorderFactory.createCompoundBorder(

                BorderFactory.createMatteBorder(
                        0,
                        0,
                        1,
                        0,
                        Theme.DIVIDER
                ),

                BorderFactory.createEmptyBorder(
                        15,
                        25,
                        15,
                        10
                )

        ));

        button.addMouseListener(new java.awt.event.MouseAdapter(){

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){

                button.setBackground(Theme.HOVER);

            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e){

                button.setBackground(Theme.SIDEBAR);

            }

        });

        return button;

    }

    public JButton getDashboardButton() {
        return dashboardButton;
    }

    public JButton getProfileButton() {
        return profileButton;
    }

    public JButton getMatchButton() {
        return matchButton;
    }

    public JButton getRequestButton() {
        return requestButton;
    }

    public JButton getLeaderboardButton() {
        return leaderboardButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }

}