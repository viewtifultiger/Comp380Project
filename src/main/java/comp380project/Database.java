package comp380project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String FILE_PATH = "users.data";
    private static List<User> users = new ArrayList<>();

    static {
        loadUsers();
    }

    @SuppressWarnings("unchecked")
    private static void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkCredentials(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.checkPassword(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean createAccount(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false; // User already exists
            }
        }
        users.add(new User(email, password));
        saveUsers();
        return true;
    }
}