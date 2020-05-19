package notes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Stream;

public class MainWindow {
    private static final String periodKey = "period";

    private final JToolBar tools = new JToolBar();
    private final Random random = new Random();
    private final JFrame frame = new JFrame("Practice Guitar");
    private final JLabel countdownLabel = new JLabel("10");
    private final JLabel noteLabel = new JLabel("A");
    private final Timer timer = new Timer(100, ev -> tick());
    private final Preferences preferences =Preferences.userNodeForPackage(getClass());

    private Position position;
    private int trials = 100;
    private int period = 50;
    private int countdown = 0;

    public MainWindow() {
        this.period = preferences.getInt(periodKey, 50);
        setupUI();
        tick();
        timer.start();
    }

    private void setupUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        addLabels(panel);
        addToolbar(panel);
        frame.add(panel);
        frame.pack();
    }

    private void addLabels(JPanel panel) {
        countdownLabel.setHorizontalAlignment(JLabel.CENTER);
        countdownLabel.setFont(countdownLabel.getFont().deriveFont(24F));
        panel.add(countdownLabel, BorderLayout.SOUTH);
        noteLabel.setPreferredSize(new Dimension(400, 400));
        noteLabel.setHorizontalAlignment(JLabel.CENTER);
        noteLabel.setFont(noteLabel.getFont().deriveFont(100F));
        panel.add(noteLabel, BorderLayout.CENTER);
    }

    private void addToolbar(JPanel panel) {
        tools.add(changePeriod("Slower", 1));
        tools.add(changePeriod("Faster", -1));
        tools.add(exitAction());
        panel.add(tools, BorderLayout.NORTH);
    }

    private Action changePeriod(String name, int delta) {
        return new AbstractAction(name) {
            @Override
            public void actionPerformed(ActionEvent e) {
                period += delta;
                if (period < 0)
                    period = 0;
                setCountdownLabel();
            }
        };
    }

    private Action exitAction() {
        return new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        };
    }

    private void exit() {
        try {
            preferences.putInt(periodKey, period);
            preferences.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void show() {
        frame.setVisible(true);
    }

    private void tick() {
        if (countdown == 0)
            changePosition();
        else
            countdown--;
        setCountdownLabel();
    }

    private void changePosition() {
        nextPosition();
        noteLabel.setText(position.toString());
        countdown = period;
        if (trials == 0)
            exit();
        else
            trials--;
    }

    private void nextPosition() {
        position = Stream.generate(() -> new Position(random))
                .dropWhile(pos -> pos.isSimilarTo(position))
                .findFirst()
                .orElseThrow();
    }

    private void setCountdownLabel() {
        countdownLabel.setText(countdown + " / " + period + " / " + trials);
    }

    public static void main(String[] args) throws InterruptedException {
        MainWindow window = new MainWindow();
        window.show();
    }
}
