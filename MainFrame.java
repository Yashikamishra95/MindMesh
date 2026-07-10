import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private DashboardPanel dashboardPanel;
    private ProfilePanel profilePanel;
    private Sidebar sidebar;
    private UserManager userManager;
    private SkillGraph skillGraph;
    private RequestQueue requestQueue;
    private LeaderboardManager leaderboardManager;
    private LeaderboardPanel leaderboardPanel;
    private MatchPanel matchPanel;
    private RequestPanel requestPanel;

    public MainFrame() {

        userManager = new UserManager();
        skillGraph = new SkillGraph();
        requestQueue = new RequestQueue();
        leaderboardManager = new LeaderboardManager();
        setTitle("MindMesh");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create Sidebar
        sidebar = new Sidebar();
        sidebar.setVisible(false); // Hide until login

        add(sidebar, BorderLayout.WEST);

        // Main Content
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Authentication Pages
        contentPanel.add(new WelcomePanel(this), "WELCOME");
        contentPanel.add(new LoginPanel(this, userManager), "LOGIN");
        contentPanel.add(new RegisterPanel(this, userManager), "REGISTER");

        // Main Application Pages
        dashboardPanel = new DashboardPanel();
        contentPanel.add(dashboardPanel,"DASHBOARD");
        profilePanel = new ProfilePanel(this);
        contentPanel.add(profilePanel,"PROFILE");
        matchPanel = new MatchPanel(
        userManager,
        requestQueue,
        skillGraph
);
        contentPanel.add(matchPanel,"MATCH");
        requestPanel = new RequestPanel(this);
        contentPanel.add(requestPanel, "REQUESTS");
        leaderboardPanel = new LeaderboardPanel();
        contentPanel.add(leaderboardPanel, "LEADERBOARD");

        add(contentPanel, BorderLayout.CENTER);

        connectSidebar();

        // Show Welcome Page First
        showPage("WELCOME");

        setVisible(true);
    }

    // ==========================
    // Sidebar Navigation
    // ==========================

    private void connectSidebar() {

        sidebar.getDashboardButton().addActionListener(e ->
                showPage("DASHBOARD"));

        sidebar.getProfileButton().addActionListener(e ->
                showPage("PROFILE"));

        sidebar.getMatchButton().addActionListener(e ->
                showPage("MATCH"));

        sidebar.getRequestButton().addActionListener(e ->
                showPage("REQUESTS"));

        sidebar.getLeaderboardButton().addActionListener(e ->
                showPage("LEADERBOARD"));

        sidebar.getLogoutButton().addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Logout from MindMesh?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                sidebar.setVisible(false);

                userManager.logout();

                showPage("WELCOME");

            }

        });

    }

    // ==========================
    // Change Screen
    // ==========================

    public void showPage(String page) {

        cardLayout.show(contentPanel, page);

    }

    // ==========================
    // Called after Login/Register
    // ==========================

    public void loginSuccess(){

    sidebar.setVisible(true);

    dashboardPanel.setWelcomeUser(
            userManager.getCurrentUser().getName()
    );
    profilePanel.loadUser(
        userManager.getCurrentUser()
);
    skillGraph.buildGraph(
        userManager.getAllUsers()
);
matchPanel.refreshMatches();
requestPanel.refresh(requestQueue);



 leaderboardManager.buildLeaderboard(
            userManager.getAllUsers()
    );

    leaderboardPanel.refresh(
            leaderboardManager
        );
showPage("DASHBOARD");

}

    // ==========================
    // Getter
    // ==========================

    public UserManager getUserManager() {

        return userManager;

    }
    public void refreshApplication() {

    if (userManager.getCurrentUser() == null)
        return;

    profilePanel.loadUser(userManager.getCurrentUser());

    // Rebuild Skill Graph
    skillGraph.buildGraph(userManager.getAllUsers());

    // Refresh Match Panel
    matchPanel.refreshMatches();

    // Refresh Requests
    requestPanel.refresh(requestQueue);

    // Rebuild Leaderboard
    leaderboardManager.buildLeaderboard(userManager.getAllUsers());

    leaderboardPanel.refresh(leaderboardManager);

    // Refresh Dashboard
    dashboardPanel.refresh(
            userManager,
            requestQueue,
            leaderboardManager
    );
}

}