import java.util.Scanner;
import java.util.Optional;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
            int choice = getUserChoice();
            switch (choice){
                case 1 -> register();
                case 2 -> login();
                case 0 -> exitApp();
                default -> System.out.println("lựa chọn không hợp lệ.");
            }
        }
    }
    private static void showMainMenu(){
        System.out.println("\n=== Ứng Dụng Ví Điện Tử===");
        System.out.println("Nhấn phím để chọn chức năng");
        System.out.println("Phím 1: Đăng ký tải khoản");
        System.out.println("Phím 2: Đăng nhập tải khoản");
        System.out.println("Phím 0: Thoát");
    }
    private static int getUserChoice(){
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
    private static void register(){
        System.out.println("Nhập tên người dùng:");
        String username= scanner.nextLine();
        System.out.println("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        try{
            userService.registerUser(username,password);
            System.out.println("Đăng ký thành công! ");
        }catch (Exception e){
            System.out.println("Đăng ký thất bại: "+e.getMessage());
        }
    }
    private static void login(){
        System.out.println("Nhập tên người dùng: ");
        String username = scanner.nextLine();
        System.out.println("Nhập mật khẩu: ");
        String password = scanner.nextLine();

        Optional<User> optionalUser = userService.authenticate(username,password);
        if (optionalUser.isPresent()){
            System.out.println("Đăng nhập thành công!");
            User currentUser = optionalUser.get();
            handleUserActions(currentUser);
        }else {
            System.out.println("Đăng nhập thất bại. Kiểm tra tên người dùng hoặc mật khẩu");
        }
    }
    private static void handleUserActions(User user)
    {
        int choice;
        do {
            showUserMenu();
            choice = getUserChoice();
            switch (choice){
                case 1 -> System.out.println("số hư hiện tại: "+ user.getWallet().getBalance());
                case 2 -> depositToWallet(user);
                case 3 -> withdrawFromWallet(user);
                case 4 -> transferMoney(user);
                case 0 -> System.out.println("Đăng xuất thành công.");
                default -> System.out.println("lựa chọn không hợp lệ.");
            }
        }while (choice!=0);
    }
    private static void showUserMenu(){
        System.out.println("\n===Chọn một thao tác===");
        System.out.println("1.Xem số dư ví");
        System.out.println("2.Nạp tiền vào ví");
        System.out.println("3.Rút tiền vào ví");
        System.out.println("4.Chuyển tiền");
        System.out.println("0.Đăng Xuất");
        System.out.println("Chọn một lựa chọn: ");
    }
    private static void depositToWallet(User user){
        System.out.println("Nhập số tiền nạp:");
        Double amuont = scanner.nextDouble();
        scanner.nextLine();
        user.getWallet().deposit(amuont);
    }
    private static void withdrawFromWallet(User user){
        System.out.println("Nhập số tiền rút: ");
        double amuont = scanner.nextDouble();
        scanner.nextLine();
        user.getWallet().withdraw(amuont);
    }
    private static void transferMoney(User user){
        System.out.println("Nhập tên người nhận: ");
        String recipientUsername = scanner.nextLine();
        Optional<User> optionalRecipient = userService.getUserByUsername(recipientUsername);
        if (optionalRecipient.isPresent()) {
            User recipient = optionalRecipient.get();
            System.out.println("Nhập số tiê chuyển: ");
            double amuont = scanner.nextDouble();
            scanner.nextLine();
            if (amuont > 0 && amuont <= user.getWallet().getBalance()) {
                user.getWallet().transfer(recipient.getWallet(), amuont);
                Transaction transaction = new Transaction(user, recipient, amuont);
                transaction.displayTransaction();
            } else {
                System.out.println("Số tiền chuyển không hợp lệ hoặc không đủ số dư. ");
            }
        }else {
            System.out.println("Người nhận không tồn tại.");
        }
    }
    private static void exitApp(){
        System.out.println("Thoát ứng dụng");
        scanner.close();
        System.exit(0);
    }
}

