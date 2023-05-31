package org.dl.app.log;

import java.util.ArrayList;
import java.util.LinkedList;

public class LogWindowSource {
    private int m_iQueueLength;
    private LinkedList<LogEntry> messages;
    private final ArrayList<LogChangeListener> m_listeners;
    private volatile LogChangeListener[] m_activeListeners;

    public LogWindowSource(int m_iQueueLength) {
        this.m_iQueueLength = m_iQueueLength;
        messages = new LinkedList<>();
        m_listeners = new ArrayList<>();
    }

    public void registerListener(LogChangeListener listener) {
        synchronized(m_listeners) {
            m_listeners.add(listener);
            m_activeListeners = null;
        }
    }

    public void unregisterListener(LogChangeListener listener) {
        synchronized(m_listeners) {
            m_listeners.remove(listener);
            m_activeListeners = null;
        }
    }

    public void append(LogLevel logLevel, String strMessage) {
        LogEntry entry = new LogEntry(logLevel, strMessage);
        addMessage(entry);
        onUpdateListeners();
    }

    public void onUpdateListeners() {
        if (m_activeListeners == null)
            synchronized (m_listeners) {
                if (m_activeListeners == null)
                    m_activeListeners = m_listeners.toArray(new LogChangeListener[0]);
            }
        for (LogChangeListener listener : m_activeListeners)
            listener.onLogChanged();
    }

    public void addMessage(LogEntry entry) {
        if (size() >= m_iQueueLength)
            messages.remove();
        messages.add(entry);
    }

    public int size() {
        return messages.size();
    }

    public Iterable<LogEntry> all() {
        return messages;
    }
}