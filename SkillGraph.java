import java.util.*;

public class SkillGraph {

    // Graph using Adjacency List
    private HashMap<User, ArrayList<User>> graph;

    public SkillGraph() {
        graph = new HashMap<>();
    }

    // -------------------------
    // Add User to Graph
    // -------------------------
    public void addUser(User user) {
        graph.putIfAbsent(user, new ArrayList<>());
    }

    // -------------------------
    // Build Graph
    // -------------------------
    public void buildGraph(Collection<User> users) {

        graph.clear();

        // Create graph nodes and clear previous matches
        for (User user : users) {
            addUser(user);
            user.clearMatches();
        }

        // Create edges between compatible users
        for (User u1 : users) {

            for (User u2 : users) {

                if (u1 == u2)
                    continue;

                if (isCompatible(u1, u2)) {

                    graph.get(u1).add(u2);

                    // Also store inside User object
                    u1.addMatch(u2);
                }
            }
        }
    }

    // -------------------------
    // Check Compatibility
    // -------------------------
    private boolean isCompatible(User learner, User teacher) {

        for (String skill : learner.getSkillsLearning()) {

            if (teacher.getSkillsKnown().contains(skill)) {
                return true;
            }
        }

        return false;
    }

    // -------------------------
    // Get Matches for User
    // -------------------------
    public ArrayList<User> getMatches(User user) {

        return graph.getOrDefault(user, new ArrayList<>());

    }

    // -------------------------
    // Print Graph (for testing)
    // -------------------------
    public void printGraph() {

        System.out.println("\n===== Skill Graph =====");

        for (User user : graph.keySet()) {

            System.out.println(user.getName() + " -> ");

            for (User match : graph.get(user)) {

                System.out.println("   " + match.getName());

            }

            System.out.println();

        }

    }

}