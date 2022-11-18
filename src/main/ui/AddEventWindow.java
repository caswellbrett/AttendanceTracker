package ui;

import model.Event;
import model.EventList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddEventWindow extends JFrame implements ActionListener {
    private JFrame addEventFrame;
    private JPanel addEventPanel;
    private JButton addButton;
    private JComboBox monthsBox;
    private JTextField yearBox;
    private JTextField dayBox;
    private JLabel dateLabel;
    private JTextField nameBox;
    private JLabel nameLabel;
    private EventList eventList;
    private GuiMain gui;
    private JPanel datePanel;
    private static final int HEIGHT = 300;
    private static final int WIDTH = 300;

    public AddEventWindow(GuiMain gui) {
        this.gui = gui;
        eventList = gui.getEventList();
        initializeComponents();

        addEventFrame.setSize(WIDTH, HEIGHT);
        addEventFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addEventFrame.add(addEventPanel);
        addEventPanel.setLayout(new BoxLayout(addEventPanel, BoxLayout.Y_AXIS));

        nameLabel.setAlignmentX(LEFT_ALIGNMENT);
        addEventPanel.add(nameLabel);

        nameBox.setAlignmentX(LEFT_ALIGNMENT);
        addEventPanel.add(nameBox);

        dateLabel.setAlignmentX(LEFT_ALIGNMENT);
        addEventPanel.add(dateLabel);

        createDatePanel();

        addButton.setAlignmentX(LEFT_ALIGNMENT);
        addEventPanel.add(addButton);
        addButton.addActionListener(this);

        addEventFrame.pack();
        addEventFrame.setVisible(true);
    }

    public void initializeComponents() {
        addEventFrame = new JFrame();
        addEventPanel = new JPanel();
        yearBox = new JTextField();
        monthsBox = new JComboBox(getMonths());
        dayBox = new JTextField();
        addButton = new JButton("Add Event");
        nameLabel = new JLabel("Please type in the event name here:");
        dateLabel = new JLabel("Please type in the event date here (in year-month-day format):");
        nameBox = new JTextField();
    }

    public void createDatePanel() {
        datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout());

        yearBox.setPreferredSize(new Dimension(100, 20));
        datePanel.add(yearBox);

        datePanel.add(monthsBox);

        dayBox.setPreferredSize(new Dimension(100,20));
        datePanel.add(dayBox);

        datePanel.setAlignmentX(LEFT_ALIGNMENT);
        addEventPanel.add(datePanel);
    }

    public String[] getMonths() {
        String[] monthList = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
                "Nov", "Dec"};
        return monthList;
    }

    public void makeEvent() {
        try {
            String yearString = yearBox.getText();
            String dayString = dayBox.getText();
            String eventName = nameBox.getText();
            int year = Integer.parseInt(yearString);
            String monthString = (String) monthsBox.getSelectedItem();
            int day = Integer.parseInt(dayString);
            if (eventList.isValidDayInput(monthString, day)) {
                Event userEvent = new Event(eventName,
                        eventList.makeEventDate(year, monthString, day), new ArrayList<>());
                eventList.addEvent(userEvent);
                gui.getGuiEvents().addElement(userEvent);
            }
        } catch (NumberFormatException e) {
            new DateErrorWindow();
        } catch (NullPointerException e) {
            new DateErrorWindow();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            makeEvent();
        }
    }
}
