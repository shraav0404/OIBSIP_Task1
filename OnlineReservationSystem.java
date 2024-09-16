import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class OnlineReservationSystem {
    private ArrayList<Reservation> reservations;
    private JFrame frame;
    private JTextField nameField;
    private JTextField prnField;
    private JTextField trainField;
    private JTextField typeField;
    private JTextField dateField;
    private JTextField splaceField;
    private JTextField dplaceField;
    private JTextArea outputArea;
    private JLabel trainNameLabel;  // New label to display train name

    // HashMap for train number and train name mapping
    private HashMap<String, String> trainMap;

    public OnlineReservationSystem() {
        reservations = new ArrayList<>();
        trainMap = new HashMap<>();
        populateTrainData();  // Populate train numbers and names
        createGUI();
    }

    // Populate the HashMap with train numbers and corresponding names
    private void populateTrainData() {
        trainMap.put("12345", "Express Train");
        trainMap.put("67890", "Superfast Train");
        trainMap.put("11223", "Local Train");
        // Add more train numbers and names as needed
    }

    private void createGUI() {
        frame = new JFrame("Online Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2, 10, 10)); // Update GridLayout for the new train name label

        // Row 1: Name
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        // Row 2: PRN
        inputPanel.add(new JLabel("PRN:"));
        prnField = new JTextField();
        inputPanel.add(prnField);

        // Row 3: Train Number
        inputPanel.add(new JLabel("Train Number:"));
        trainField = new JTextField();
        inputPanel.add(trainField);

        // Row 4: Train Name (automatically displayed)
        inputPanel.add(new JLabel("Train Name:"));
        trainNameLabel = new JLabel();
        inputPanel.add(trainNameLabel);

        // Row 5: Class Type
        inputPanel.add(new JLabel("Class Type:"));
        typeField = new JTextField();
        inputPanel.add(typeField);

        // Row 6: Date of Journey
        inputPanel.add(new JLabel("Date of Journey:"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        // Row 7: Starting Place
        inputPanel.add(new JLabel("From:"));
        splaceField = new JTextField();
        inputPanel.add(splaceField);

        // Row 8: Destination Place
        inputPanel.add(new JLabel("Destination:"));
        dplaceField = new JTextField();
        inputPanel.add(dplaceField);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 5, 5)); // Adjust layout to account for one less button

        JButton newRecordButton = new JButton("New Record");
        newRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        buttonPanel.add(newRecordButton);

        JButton insertButton = new JButton("Insert Record");
        insertButton.addActionListener(new InsertButtonListener());
        buttonPanel.add(insertButton);

        JButton showButton = new JButton("Show All Records");
        showButton.addActionListener(new ShowButtonListener());
        buttonPanel.add(showButton);

        JButton cancelButton = new JButton("Cancel Booking");
        cancelButton.addActionListener(new CancelBookingListener());
        buttonPanel.add(cancelButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        outputArea = new JTextArea(10, 20);
        outputArea.setEditable(false);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(new JScrollPane(outputArea), BorderLayout.EAST);

        // Add ActionListener to train number field to automatically display train name
        trainField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trainNumber = trainField.getText();
                String trainName = trainMap.get(trainNumber);
                if (trainName != null) {
                    trainNameLabel.setText(trainName);  // Display train name
                } else {
                    trainNameLabel.setText("Train not found");  // Error message if train number is invalid
                }
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    // Method to clear all input fields
    private void clearFields() {
        nameField.setText("");
        prnField.setText("");
        trainField.setText("");
        trainNameLabel.setText("");  // Clear train name label
        typeField.setText("");
        dateField.setText("");
        splaceField.setText("");
        dplaceField.setText("");
        outputArea.append("Fields cleared for new record!\n");
    }

    private class InsertButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String prn = prnField.getText();
            String train = trainField.getText();
            String type = typeField.getText();
            String date = dateField.getText();
            String splace = splaceField.getText();
            String dplace = dplaceField.getText();

            Reservation reservation = new Reservation(name, prn, train, type, date, splace, dplace);
            reservations.add(reservation);

            outputArea.append("Record inserted successfully!\n");
        }
    }

    private class ShowButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (reservations.isEmpty()) {
                outputArea.append("No records found!\n");
            } else {
                for (Reservation reservation : reservations) {
                    outputArea.append("Name: " + reservation.getName() + "\n");
                    outputArea.append("PRN: " + reservation.getPrn() + "\n");
                    outputArea.append("Train Number: " + reservation.getTrain() + "\n");
                    outputArea.append("Class Type: " + reservation.getType() + "\n");
                    outputArea.append("Date of Journey: " + reservation.getDate() + "\n");
                    outputArea.append("Starting Place: " + reservation.getSplace() + "\n");
                    outputArea.append("Destination Place: " + reservation.getDplace() + "\n");
                    outputArea.append("-------------------------------------\n");
                }
            }
        }
    }

    private class CancelBookingListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String prn = JOptionPane.showInputDialog(frame, "Enter PRN to cancel booking:");
            if (prn != null && !prn.isEmpty()) {
                Reservation reservationToCancel = null;
                for (Reservation reservation : reservations) {
                    if (reservation.getPrn().equals(prn)) {
                        reservationToCancel = reservation;
                        break;
                    }
                }

                if (reservationToCancel != null) {
                    int confirmation = JOptionPane.showConfirmDialog(frame,
                            "Name: " + reservationToCancel.getName() + "\n" +
                                    "Train Number: " + reservationToCancel.getTrain() + "\n" +
                                    "Class Type: " + reservationToCancel.getType() + "\n" +
                                    "Date of Journey: " + reservationToCancel.getDate() + "\n" +
                                    "Starting Place: " + reservationToCancel.getSplace() + "\n" +
                                    "Destination Place: " + reservationToCancel.getDplace() + "\n\n" +
                                    "Do you want to cancel this Ticket?", "Confirm Cancellation", JOptionPane.OK_CANCEL_OPTION);

                    if (confirmation == JOptionPane.OK_OPTION) {
                        reservations.remove(reservationToCancel);
                        outputArea.append("Booking canceled successfully!\n");
                    } else {
                        outputArea.append("Cancellation aborted.\n");
                    }
                } else {
                    outputArea.append("Booking with PRN not found!\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OnlineReservationSystem());
    }
}
