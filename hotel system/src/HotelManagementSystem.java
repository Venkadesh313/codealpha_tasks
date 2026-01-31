import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class HotelManagementSystem extends JFrame {

    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    JTextField nameField, phoneField;
    JComboBox<String> roomTypeBox;
    JTextArea displayArea;

    public HotelManagementSystem() {

        // Rooms
        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Deluxe", 2500));
        rooms.add(new Room(103, "Suite", 4000));

        setTitle("Hotel Management System");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        inputPanel.add(new JLabel("Customer Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        inputPanel.add(new JLabel("Room Type:"));
        roomTypeBox = new JComboBox<>(new String[]{"Standard", "Deluxe", "Suite"});
        inputPanel.add(roomTypeBox);

        JButton bookButton = new JButton("Book Room");
        inputPanel.add(bookButton);

        JButton viewButton = new JButton("View Bookings");
        inputPanel.add(viewButton);

        add(inputPanel, BorderLayout.NORTH);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Button Actions
        bookButton.addActionListener(_ -> bookRoom());
        viewButton.addActionListener(_ -> showBookings());

        setVisible(true);
    }

    void bookRoom() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String type = Objects.requireNonNull(roomTypeBox.getSelectedItem()).toString();

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        for (Room room : rooms) {
            if (!room.isBooked && room.type.equals(type)) {
                room.isBooked = true;
                Customer customer = new Customer(name, phone);
                Booking booking = new Booking(customer, room);
                bookings.add(booking);

                displayArea.setText("Room Booked Successfully!\n\n" + booking);
                nameField.setText("");
                phoneField.setText("");
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "No available room for selected type");
    }

    void showBookings() {
        if (bookings.isEmpty()) {
            displayArea.setText("No bookings available.");
            return;
        }

        displayArea.setText("");
        for (Booking b : bookings) {
            displayArea.append(b + "\n");
        }
    }

    public static void main(String[] args) {
        new HotelManagementSystem();
    }
}
