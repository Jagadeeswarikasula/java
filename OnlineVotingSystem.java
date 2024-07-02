import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// User class to represent registered users
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

// Candidate class to represent registered candidates
class Candidate {
    private String name;
    private String party;

    public Candidate(String name, String party) {
        this.name = name;
        this.party = party;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }
}

// Main class to manage the voting system
public class OnlineVotingSystem {
    private static List<User> users = new ArrayList<>();
    private static Map<String, Candidate> candidates = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        User currentUser = null;

        while (!loggedIn) {
            System.out.println("Welcome to the Online Voting System!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // User registration
                    System.out.print("Enter username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String newPassword = scanner.nextLine();
                    users.add(new User(newUsername, newPassword));
                    System.out.println("Registration successful!");
                    break;
                case 2:
                    // User login
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    // Check if user exists and login
                    currentUser = authenticateUser(username, password);
                    if (currentUser != null) {
                        loggedIn = true;
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        // Candidate registration (for demonstration purposes)
        System.out.println("\nCandidate Registration:");
        System.out.print("Enter candidate name: ");
        String candidateName = scanner.nextLine();
        System.out.print("Enter candidate party: ");
        String candidateParty = scanner.nextLine();
        candidates.put(candidateName, new Candidate(candidateName, candidateParty));
        System.out.println("Candidate registered successfully!");

        // Voting phase
        System.out.println("\nVote Casting:");
        System.out.println("List of Candidates:");
        for (Candidate candidate : candidates.values()) {
            System.out.println("- " + candidate.getName() + " (" + candidate.getParty() + ")");
        }
        System.out.print("Enter candidate name to vote (or 'exit' to finish voting): ");
        String voteChoice = scanner.nextLine();
        if (candidates.containsKey(voteChoice)) {
            System.out.println("You voted for: " + voteChoice);
        } else {
            System.out.println("Invalid candidate.");
        }

        scanner.close();
    }

    // Method to authenticate user login
    private static User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Authentication failed
    }
}
