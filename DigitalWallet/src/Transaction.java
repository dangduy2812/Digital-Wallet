import java.time.LocalDateTime;

public class Transaction {
    private User sender;
    private User receiver;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(User sender, User receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    // Phương thức để hiển thị thông tin giao dịch
    public void displayTransaction() {
        System.out.println("Giao dịch từ " + sender.getUsername() + " tới " + receiver.getUsername()
                + " số tiền: " + amount + " vào " + timestamp);
    }
}
