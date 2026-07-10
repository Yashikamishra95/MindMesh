import java.util.ArrayList;

public class User {

    // Personal Details
    private String name;
    private String email;
    private String password;
    private String college;
    private String city;
    private String bio;

    // Skills
    private ArrayList<String> skillsKnown;
    private ArrayList<String> skillsLearning;

    // Progress
    // Progress
private int completedExchanges;
private int points;

// Graph Matches
private ArrayList<User> matches;

    public User(String name,
                String email,
                String password,
                String college,
                String city,
                String bio,
                ArrayList<String> skillsKnown,
                ArrayList<String> skillsLearning) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.college = college;
        this.city = city;
        this.bio = bio;

        this.skillsKnown = skillsKnown;
        this.skillsLearning = skillsLearning;

        this.completedExchanges = 0;
        this.points = 0;
        this.matches = new ArrayList<>();
    }

    // ---------------- Getters ----------------

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCollege() {
        return college;
    }

    public String getCity() {
        return city;
    }

    public String getBio() {
        return bio;
    }

    public ArrayList<String> getSkillsKnown() {
        return skillsKnown;
    }

    public ArrayList<String> getSkillsLearning() {
        return skillsLearning;
    }

    public int getCompletedExchanges() {
        return completedExchanges;
    }

    public int getPoints() {
        return points;
    }
    public ArrayList<User> getMatches() {
    return matches;
}

    // ---------------- Setters ----------------

    public void setName(String name) {
        this.name = name;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setSkillsKnown(ArrayList<String> skillsKnown) {
        this.skillsKnown = skillsKnown;
    }

    public void setSkillsLearning(ArrayList<String> skillsLearning) {
        this.skillsLearning = skillsLearning;
    }

    public void setCompletedExchanges(int completedExchanges) {
        this.completedExchanges = completedExchanges;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void setMatches(ArrayList<User> matches) {
    this.matches = matches;
}
    public void addPoints(int points){

    this.points += points;

}

public void incrementCompletedExchanges(){

    completedExchanges++;

}

    // ---------------- Utility ----------------

    @Override
    public String toString() {

        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", college='" + college + '\'' +
                ", city='" + city + '\'' +
                ", points=" + points +
                '}';

    }
    public void addMatch(User user){

    if(!matches.contains(user))
        matches.add(user);

}

public void clearMatches(){

    matches.clear();

}
}
