import java.awt.*;
import javax.swing.*;

public class LeaderboardPanel extends JPanel {

    private JTextArea rankingArea;

    public LeaderboardPanel() {

        setLayout(new BorderLayout());

        setBackground(Theme.BACKGROUND);

        JLabel title = new JLabel("🏆 Leaderboard");

        title.setFont(Theme.TITLE_FONT);

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        add(title, BorderLayout.NORTH);

        rankingArea = new JTextArea();

        rankingArea.setEditable(false);

        rankingArea.setFont(Theme.NORMAL_FONT);

        add(new JScrollPane(rankingArea), BorderLayout.CENTER);

    }

    public void refresh(LeaderboardManager manager) {

        rankingArea.setText("");

        int rank = 1;

        for(User user : manager.getRanking()) {

            rankingArea.append(

                    rank + ". "

                    + user.getName()

                    + " | Points : "

                    + user.getPoints()

                    + " | Exchanges : "

                    + user.getCompletedExchanges()

                    + "\n"

            );

            rank++;

        }

    }

}