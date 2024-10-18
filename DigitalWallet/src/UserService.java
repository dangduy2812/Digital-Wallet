import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public User registerUser(String username, String password) throws Exception {
        if (users.containsKey(username)) {
            throw new Exception("Username đã tồn tại.");
        }
        User user = new User(username, password);
        users.put(username, user);
        return user;
    }

    public Optional<User> authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.login(username, password)) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }
}
