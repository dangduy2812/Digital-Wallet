import java.util.Scanner;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        while (true) {
            System.out.println("\n=== Ứng Dụng Ví Điện Tử ===");
            System.out.println("1. Đăng ký");
            System.out.println("2. Đăng nhập");
            System.out.println("0. Thoát");
            System.out.print("Chọn một tùy chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự newline

            switch (choice) {
                case 1:
                    // Đăng ký người dùng
                    System.out.print("Nhập tên người dùng: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Nhập mật khẩu: ");
                    String regPassword = scanner.nextLine();
                    try {
                        userService.registerUser(regUsername, regPassword);
                        System.out.println("Đăng ký thành công!");
                    } catch (Exception e) {
                        System.out.println("Đăng ký thất bại: " + e.getMessage());
                    }
                    break;
                case 2:
                    // Đăng nhập
                    System.out.print("Nhập tên người dùng: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Nhập mật khẩu: ");
                    String loginPassword = scanner.nextLine();

                    Optional<User> optionalUser = userService.authenticate(loginUsername, loginPassword);
                    if (optionalUser.isPresent()) {
                        User currentUser = optionalUser.get();
                        System.out.println("Đăng nhập thành công!");

                        // Giao diện nạp, rút, chuyển tiền
                        int userChoice = -1;
                        while (userChoice != 0) {
                            System.out.println("\nChọn một thao tác:");
                            System.out.println("1. Xem số dư ví");
                            System.out.println("2. Nạp tiền vào ví");
                            System.out.println("3. Rút tiền từ ví");
                            System.out.println("4. Chuyển tiền");
                            System.out.println("0. Đăng xuất");
                            System.out.print("Chọn một tùy chọn: ");
                            userChoice = scanner.nextInt();
                            scanner.nextLine(); // Đọc ký tự newline

                            switch (userChoice) {
                                case 1:
                                    System.out.println("Số dư hiện tại: " + currentUser.getWallet().getBalance());
                                    break;
                                case 2:
                                    System.out.print("Nhập số tiền nạp: ");
                                    double depositAmount = scanner.nextDouble();
                                    scanner.nextLine();
                                    currentUser.getWallet().deposit(depositAmount);
                                    break;
                                case 3:
                                    System.out.print("Nhập số tiền rút: ");
                                    double withdrawAmount = scanner.nextDouble();
                                    scanner.nextLine();
                                    currentUser.getWallet().withdraw(withdrawAmount);
                                    break;
                                case 4:
                                    System.out.print("Nhập tên người nhận: ");
                                    String recipientUsername = scanner.nextLine();
                                    Optional<User> optionalRecipient = userService.getUserByUsername(recipientUsername);
                                    if (optionalRecipient.isPresent()) {
                                        User recipient = optionalRecipient.get();
                                        System.out.print("Nhập số tiền chuyển: ");
                                        double transferAmount = scanner.nextDouble();
                                        scanner.nextLine();
                                        if (transferAmount > 0 && transferAmount <= currentUser.getWallet().getBalance()) {
                                            currentUser.getWallet().transfer(recipient.getWallet(), transferAmount);
                                            // Ghi lại giao dịch
                                            Transaction transaction = new Transaction(currentUser, recipient, transferAmount);
                                            transaction.displayTransaction();
                                        } else {
                                            System.out.println("Số tiền chuyển không hợp lệ hoặc không đủ số dư.");
                                        }
                                    } else {
                                        System.out.println("Người nhận không tồn tại.");
                                    }
                                    break;

                                case 0:
                                    System.out.println("Đăng xuất thành công.");
                                    break;
                                default:
                                    System.out.println("Lựa chọn không hợp lệ.");
                            }
                        }
                    } else {
                        System.out.println("Đăng nhập thất bại. Kiểm tra lại tên người dùng hoặc mật khẩu.");
                    }
                    break;
                case 0:
                    System.out.println("Thoát ứng dụng.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
