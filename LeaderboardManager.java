import java.util.*;

public class LeaderboardManager {

    private PriorityQueue<User> leaderboard;

    public LeaderboardManager() {

        leaderboard = new PriorityQueue<>((u1, u2) -> {

            if (u1.getPoints() != u2.getPoints()) {
                return u2.getPoints() - u1.getPoints();
            }

            return u2.getCompletedExchanges()
                    - u1.getCompletedExchanges();

        });

    }

    // ===========================
    // Build Leaderboard
    // ===========================

    public void buildLeaderboard(Collection<User> users) {

        leaderboard.clear();

        leaderboard.addAll(users);

    }

    // ===========================
    // Top User
    // ===========================

    public User getTopUser() {

        return leaderboard.peek();

    }

    // ===========================
    // Get Ranking
    // ===========================

    public ArrayList<User> getRanking() {

        ArrayList<User> ranking = new ArrayList<>(leaderboard);

        ranking.sort((u1, u2) -> {

            if (u1.getPoints() != u2.getPoints()) {
                return u2.getPoints() - u1.getPoints();
            }

            return u2.getCompletedExchanges()
                    - u1.getCompletedExchanges();

        });

        return ranking;

    }

    // ===========================
    // Get Rank of User
    // ===========================

    public int getRank(User user) {

        ArrayList<User> ranking = getRanking();

        for (int i = 0; i < ranking.size(); i++) {

            if (ranking.get(i).getEmail().equals(user.getEmail())) {
                return i + 1;
            }

        }

        return -1;

    }

}