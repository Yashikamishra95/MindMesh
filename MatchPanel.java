import java.awt.*;
import javax.swing.*;

public class MatchPanel extends JPanel {

    private UserManager userManager;
    private RequestQueue requestQueue;
    private SkillGraph skillGraph;

    private JPanel cardsPanel;

     public MatchPanel(UserManager userManager,
                  RequestQueue requestQueue,
                  SkillGraph skillGraph){

        this.userManager = userManager;
        this.requestQueue = requestQueue;

        this.skillGraph = skillGraph;

        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);

        JLabel title = new JLabel("🎯 Skill Matches");
        title.setFont(Theme.TITLE_FONT);
        title.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        add(title, BorderLayout.NORTH);

        cardsPanel = new JPanel();
        cardsPanel.setBackground(Theme.BACKGROUND);
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(cardsPanel);
        scroll.setBorder(null);

        add(scroll, BorderLayout.CENTER);

    }

    public void refreshMatches() {

        cardsPanel.removeAll();

        skillGraph.buildGraph(userManager.getAllUsers());

        User current = userManager.getCurrentUser();

        for(User match : skillGraph.getMatches(current)) {

            JPanel card = new JPanel(new BorderLayout());

            card.setMaximumSize(new Dimension(700,120));
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

            JLabel info = new JLabel(
                    "<html><b>" + match.getName() +
                    "</b><br>" +
                    match.getCollege() +
                    "<br>Can Teach: " +
                    match.getSkillsKnown() +
                    "</html>"
            );

            JButton send = new JButton("Send Request");

            send.addActionListener(e -> {

                String skill = "";

                if(!current.getSkillsLearning().isEmpty())
                    skill = current.getSkillsLearning().get(0);

                Request request = new Request(
                        current,
                        match,
                        skill
                );

                requestQueue.sendRequest(request);
                JOptionPane.showMessageDialog(
                    this,
                    "Request Sent Successfully!");


            });

            card.add(info, BorderLayout.CENTER);
            card.add(send, BorderLayout.EAST);

            cardsPanel.add(card);
            cardsPanel.add(Box.createVerticalStrut(15));

        }

        revalidate();
        repaint();

    }

}