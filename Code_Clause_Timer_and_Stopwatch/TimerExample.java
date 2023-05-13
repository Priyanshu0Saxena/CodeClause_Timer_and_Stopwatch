import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerExample implements ActionListener {

    private JFrame frame;
    private JLabel timerLabel;
    private JTextField timerField;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private Timer timer;
    private int seconds = 0;
    private int limit = 0;
    private boolean isTimerRunning = false;

    public TimerExample() {
        // Create the JFrame and set its properties
        frame = new JFrame("Timer Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Create the timer label and add it to the frame
        timerLabel = new JLabel("00:00:00", JLabel.CENTER);
        timerLabel.setPreferredSize(new Dimension(120, 40));
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        frame.add(timerLabel);

        // Create the timer field and add it to the frame
        timerField = new JTextField("0", 5);
        frame.add(timerField);

        // Create the start button and add it to the frame
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        frame.add(startButton);

        // Create the stop button and add it to the frame
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        frame.add(stopButton);

        // Create the reset button and add it to the frame
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        frame.add(resetButton);

        // Create the timer and set its delay to 1000ms (1 second)
        timer = new Timer(1000, this);

        // Set the frame properties and display it
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!isTimerRunning) {
                limit = Integer.parseInt(timerField.getText());
                isTimerRunning = true;
                timer.start();
            }
        } else if (e.getSource() == stopButton) {
            if (isTimerRunning) {
                isTimerRunning = false;
                timer.stop();
            }
        } else if (e.getSource() == resetButton) {
            isTimerRunning = false;
            timer.stop();
            seconds = 0;
            timerLabel.setText("00:00:00");
        } else if (e.getSource() == timer) {
            seconds++;
            int minute = (seconds / 60) % 60;
            int hour = seconds / 3600;
            timerLabel.setText(String.format("%02d:%02d:%02d", hour, minute, seconds % 60));
            if (seconds >= limit) {
                isTimerRunning = false;
                timer.stop();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TimerExample();
            }
        });
    }
}