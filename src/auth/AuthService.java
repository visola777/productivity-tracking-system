package auth;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private List<User> users = new ArrayList<>();

    public boolean register(User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                return false; // username mavjud
            }
        }
        users.add(user);
        return true; // ro‘yxatga olindi
    }

    public boolean login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true; // login muvaffaqiyatli
            }
        }
        return false; // username yoki password noto‘g‘ri
    }
}
