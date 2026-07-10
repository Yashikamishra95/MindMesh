import java.awt.*;
import javax.swing.*;

public class DashboardPanel extends JPanel {

    private JLabel welcomeLabel;
    private JLabel usersValue;
    private JLabel matchesValue;
    private JLabel requestsValue;
    private JLabel leaderboardValue;

    public DashboardPanel() {

        setBackground(Theme.BACKGROUND);
        setLayout(new BorderLayout());

        welcomeLabel = new JLabel("Welcome to MindMesh 👋");
        welcomeLabel.setFont(Theme.TITLE_FONT);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        add(welcomeLabel, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(2,2,20,20));
cards.setBackground(Theme.BACKGROUND);
cards.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

usersValue = new JLabel("0");
matchesValue = new JLabel("0");
requestsValue = new JLabel("0");
leaderboardValue = new JLabel("#0");

cards.add(createCard("👥 Users", usersValue));
cards.add(createCard("🎯 Matches", matchesValue));
cards.add(createCard("📨 Requests", requestsValue));
cards.add(createCard("🏆 Rank", leaderboardValue));

        add(cards, BorderLayout.CENTER);

    }

    private JPanel createCard(String title, JLabel value){

    JPanel card = new JPanel(new BorderLayout());

    card.setBackground(Color.WHITE);

    card.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

    JLabel titleLabel = new JLabel(title);

    titleLabel.setFont(Theme.HEADER_FONT);

    value.setHorizontalAlignment(SwingConstants.CENTER);

    value.setFont(new Font("Segoe UI", Font.BOLD, 32));

    card.add(titleLabel, BorderLayout.NORTH);
    card.add(value, BorderLayout.CENTER);

    return card;

}

    public void setWelcomeUser(String name){

        welcomeLabel.setText("Welcome Back, " + name + " 👋");

    }
    public void refresh(UserManager manager,
                    RequestQueue queue,
                    LeaderboardManager leaderboard){

    usersValue.setText(
            String.valueOf(manager.getAllUsers().size())
    );

    matchesValue.setText(
            String.valueOf(
                    manager.getCurrentUser()
                           .getMatches().size()
            )
    );

    requestsValue.setText(
            String.valueOf(
                    queue.getAllRequests().size()
            )
    );

    leaderboardValue.setText(
            "#"
            + leaderboard.getRank(
                    manager.getCurrentUser()
            )
    );

}
    

}