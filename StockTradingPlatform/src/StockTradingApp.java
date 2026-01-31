import java.util.*;

public class StockTradingApp {
    static Scanner sc = new Scanner(System.in);
    static Portfolio portfolio = new Portfolio();

    public static void main(String[] args) {

        Stock s1 = new Stock("TCS", "Tata Consultancy Services", 3500);
        Stock s2 = new Stock("1" +
                "INFY", "Infosys", 1450);

        while (true) {
            System.out.println("\n--- STOCK TRADING PLATFORM ---");
            System.out.println("1. View Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("TCS - ₹" + s1.getPrice());
                    System.out.println("INFY - ₹" + s2.getPrice());
                    break;

                case 2:
                    System.out.print("Enter Stock (TCS/INFY): ");
                    String buy = sc.next();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    portfolio.buyStock(buy, qty);
                    System.out.println("Stock Purchased Successfully!");
                    break;

                case 3:
                    System.out.print("Enter Stock: ");
                    String sell = sc.next();
                    System.out.print("Enter Quantity: ");
                    int sqty = sc.nextInt();
                    portfolio.sellStock(sell, sqty);
                    System.out.println("Stock Sold Successfully!");
                    break;

                case 4:
                    portfolio.displayPortfolio();
                    break;

                case 5:
                    System.out.println("Thank you for trading!");
                    System.exit(0);
            }
        }
    }
}
