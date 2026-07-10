import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class RequestPanel extends JPanel {

    private JPanel requestsContainer;
    private MainFrame mainFrame;

    public RequestPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);

        JLabel title = new JLabel("📨 Skill Requests");
        title.setFont(Theme.TITLE_FONT);
        title.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        add(title, BorderLayout.NORTH);

        requestsContainer = new JPanel();
        requestsContainer.setLayout(new BoxLayout(requestsContainer, BoxLayout.Y_AXIS));
        requestsContainer.setBackground(Theme.BACKGROUND);

        JScrollPane scrollPane = new JScrollPane(requestsContainer);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void refresh(RequestQueue queue) {

        requestsContainer.removeAll();

        User currentUser = mainFrame.getUserManager().getCurrentUser();

        ArrayList<Request> requests = queue.getAllRequests();

        boolean found = false;

        

        for (Request request : requests) {

            if (request.getReceiver() != currentUser)
                continue;

            found = true;

            requestsContainer.add(createRequestCard(request));
            requestsContainer.add(Box.createVerticalStrut(15));
        }

        if (!found) {

            JLabel empty = new JLabel("No Requests Yet.");
            empty.setFont(Theme.NORMAL_FONT);
            empty.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            requestsContainer.add(empty);
        }

        requestsContainer.revalidate();
        requestsContainer.repaint();
    }

    private JPanel createRequestCard(Request request) {

        JPanel card = new JPanel(new BorderLayout());

        card.setMaximumSize(new Dimension(850,170));

        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(15,15,15,15)
        ));

        JLabel details = new JLabel(
                "<html>"
                        + "<h3>" + request.getSender().getName() + "</h3>"
                        + "<b>Skill:</b> " + request.getSkill()
                        + "<br><b>Status:</b> " + request.getStatus()
                        + "</html>"
        );

        details.setFont(Theme.NORMAL_FONT);

        card.add(details, BorderLayout.CENTER);

        if (request.getStatus().equals("Pending")) {

            JPanel buttonPanel = new JPanel();
            buttonPanel.setOpaque(false);

            JButton accept = new JButton("✅ Accept");
            JButton reject = new JButton("❌ Reject");

            accept.setBackground(new Color(76,175,80));
            accept.setForeground(Color.WHITE);

            reject.setBackground(new Color(229,57,53));
            reject.setForeground(Color.WHITE);

            buttonPanel.add(accept);
            buttonPanel.add(reject);

            card.add(buttonPanel, BorderLayout.EAST);

            accept.addActionListener(e -> {

                request.setStatus("Accepted");
                request.setCompleted(true);

                request.getSender().addPoints(10);
                request.getReceiver().addPoints(10);

                request.getSender().incrementCompletedExchanges();
                request.getReceiver().incrementCompletedExchanges();

                JOptionPane.showMessageDialog(
                        this,
                        "Request Accepted!"
                );

                mainFrame.refreshApplication();

            });

            reject.addActionListener(e -> {

                request.setStatus("Rejected");

                JOptionPane.showMessageDialog(
                        this,
                        "Request Rejected!"
                );

                mainFrame.refreshApplication();

            });

        }

        return card;

    }

}