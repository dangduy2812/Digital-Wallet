public class Wallet {
    private double balance;

    public Wallet() {
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Đã nạp " + amount + " vào ví.");
        } else {
            System.out.println("Số tiền không hợp lệ.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Đã rút " + amount + " từ ví.");
        } else {
            System.out.println("Số dư không đủ hoặc số tiền không hợp lệ.");
        }
    }

    public void transfer(Wallet recipientWallet, double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.withdraw(amount);
            recipientWallet.deposit(amount);
            System.out.println("Đã chuyển " + amount + " vào ví nhận.");
        } else {
            System.out.println("Giao dịch không hợp lệ.");
        }
    }
}
