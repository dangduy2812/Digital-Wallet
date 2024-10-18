public class User {
    private String username;
    private String password;
    private Wallet wallet;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.wallet = new Wallet();
    }

    // Getter và Setter cho username, password, và wallet
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

/*
    public void setPassword(String password) {
        this.password = password;
    }
*/

    public Wallet getWallet() {
        return wallet;
    }

    // Phương thức đăng nhập
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
