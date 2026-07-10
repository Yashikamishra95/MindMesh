import java.util.*;

public class UserManager {

    // HashMap<Email, User>
    private HashMap<String, User> users;

    // Currently Logged In User
    private User currentUser;

    public UserManager() {

        users = new HashMap<>();

        // Load demo users
        loadDemoUsers();

    }

    // ===========================
    // Register New User
    // ===========================

    public boolean registerUser(User user) {

        if(users.containsKey(user.getEmail())){

            return false;

        }

        users.put(user.getEmail(), user);

        return true;

    }

    // ===========================
    // Login
    // ===========================

    public boolean login(String email, String password){

        if(!users.containsKey(email))
            return false;

        User user = users.get(email);

        if(user.getPassword().equals(password)){

            currentUser = user;

            return true;

        }

        return false;

    }

    // ===========================
    // Logout
    // ===========================

    public void logout(){

        currentUser = null;

    }

    // ===========================
    // Get Logged In User
    // ===========================

    public User getCurrentUser(){

        return currentUser;

    }

    // ===========================
    // Find User
    // ===========================

    public User getUser(String email){

        return users.get(email);

    }

    // ===========================
    // Total Users
    // ===========================

    public int getTotalUsers(){

        return users.size();

    }

    // ===========================
    // Demo Users
    // ===========================

    private void loadDemoUsers(){

        registerUser(new User(
                "Aman",
                "aman@gmail.com",
                "1234",
                "LPU",
                "Punjab",
                "Java Developer",
                new java.util.ArrayList<>(),
                new java.util.ArrayList<>()
        ));

        registerUser(new User(
                "Riya",
                "riya@gmail.com",
                "1234",
                "LPU",
                "Delhi",
                "Frontend Developer",
                new java.util.ArrayList<>(),
                new java.util.ArrayList<>()
        ));

        registerUser(new User(
                "Rahul",
                "rahul@gmail.com",
                "1234",
                "IIT Delhi",
                "Delhi",
                "DSA Enthusiast",
                new java.util.ArrayList<>(),
                new java.util.ArrayList<>()
        ));

    }
    public Collection<User> getAllUsers(){

    return users.values();}

}