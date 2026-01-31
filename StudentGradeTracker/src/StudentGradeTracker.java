import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Student {
    String name;
    int marks;
    String grade;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
        this.grade = calculateGrade(marks);
    }

    private String calculateGrade(int marks) {
        if (marks >= 75)
            return "A";
        else if (marks >= 50)
            return "B";
        else
            return "C";
    }
}

public class StudentGradeTracker extends JFrame {

    private final JTextField nameField;
    private final JTextField marksField;
    private final JTextArea displayArea;
    private final ArrayList<Student> students = new ArrayList<>();

    public StudentGradeTracker() {
        setTitle("Student Grade Tracker");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Student Name:");
        nameField = new JTextField(15);

        JLabel marksLabel = new JLabel("Marks:");
        marksField = new JTextField(5);

        JButton addButton = new JButton("Add Student");
        JButton reportButton = new JButton("Show Report");

        displayArea = new JTextArea(20, 35);
        displayArea.setEditable(false);

        add(nameLabel);
        add(nameField);
        add(marksLabel);
        add(marksField);
        add(addButton);
        add(reportButton);
        add(new JScrollPane(displayArea));

        addButton.addActionListener(_ -> addStudent());
        reportButton.addActionListener(_ -> showReport());

        setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText();
        int marks = Integer.parseInt(marksField.getText());

        students.add(new Student(name, marks));
        nameField.setText("");
        marksField.setText("");
    }

    private void showReport() {
        if (students.isEmpty()) return;

        int total = 0;
        int highest = students.getFirst().marks;
        int lowest = students.getFirst().marks;

        displayArea.setText("--- Student Summary Report ---\n");

        for (Student s : students) {
            total += s.marks;
            highest = Math.max(highest, s.marks);
            lowest = Math.min(lowest, s.marks);

            displayArea.append(
                    "Name: " + s.name +
                            " | Marks: " + s.marks +
                            " | Grade: " + s.grade + "\n"
            );
        }

        double average = (double) total / students.size();

        displayArea.append("\nAverage Marks: " + average);
        displayArea.append("\nHighest Marks: " + highest);
        displayArea.append("\nLowest Marks: " + lowest);
    }

    public static void main(String[] args) {
        new StudentGradeTracker();
    }
}
