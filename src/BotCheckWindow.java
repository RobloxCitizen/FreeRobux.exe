import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BotCheckWindow extends JFrame {
    private JTextField inputField;
    private JLabel instructionLabel;
    private JButton submitButton;
    private String correctText = "roblox";

    public BotCheckWindow() {
        setUndecorated(true);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
                setState(Frame.NORMAL);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                toFront();
                requestFocus();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBackground(Color.BLACK);

        instructionLabel = new JLabel("Посмотри в исходном коде", SwingConstants.CENTER);
        instructionLabel.setForeground(Color.WHITE);
        panel.add(instructionLabel);

        inputField = new JTextField();
        inputField.setBackground(Color.BLACK);
        inputField.setForeground(Color.WHITE);
        inputField.setCaretColor(Color.WHITE);
        inputField.setEditable(true);
        inputField.addActionListener(e -> checkInput());
        panel.add(inputField);

        submitButton = new JButton("Проверить");
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> checkInput());
        panel.add(submitButton);

        add(panel);

        getContentPane().setBackground(Color.BLACK);

        setAlwaysOnTop(true);
        setResizable(false);
        setFocusable(true);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                toFront();
                requestFocus();
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                setVisible(true);
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                setLocationRelativeTo(null);
            }
        });

        SwingUtilities.invokeLater(() -> inputField.requestFocusInWindow());
    }

    private void checkInput() {
        String input = inputField.getText().trim();
        if (input.equals(correctText)) {
            JOptionPane.showMessageDialog(this, "Ладно");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, "Неверный текст! Попробуйте еще раз.");
            inputField.setText("");
            inputField.requestFocusInWindow();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BotCheckWindow window = new BotCheckWindow();
            window.setVisible(true);
            window.toFront();
            window.requestFocus();

            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(
                    new WindowEvent(window, WindowEvent.WINDOW_OPENED));
        });
    }
}
