package org.dl.app.View;

import org.dl.app.TheGame.Parasite;

import javax.swing.*;
import java.awt.*;

public class CreatorTimer extends JFrame {
    Parasite parasite;
    private int TTL = 150; // in seconds
    private static final int DELAY_MS = 50; // частота обновления времени в миллисекундах

    private int lifeTimeSeconds; // текущее время жизни робота в секундах
    JProgressBar progressBar;

    public CreatorTimer() {
        super("Robot Life Timer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null);

        progressBar = new JProgressBar();
        progressBar.setMaximum(TTL);
        add(progressBar, BorderLayout.NORTH);

        // создаем таймер для обновления времени
        Timer timer = new Timer(DELAY_MS, e -> {
            lifeTimeSeconds++;
            progressBar.setValue(lifeTimeSeconds);
            if (lifeTimeSeconds >= TTL) {
                ((Timer)e.getSource()).stop(); // останавливаем таймер при истечении времени жизни робота
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }
}
