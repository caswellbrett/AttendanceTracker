package ui;

import model.Occasion;
import model.OccasionList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// represents the window ran when you wish to add an event to AttendanceTracker
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
    private OccasionList occasionList;
    private GuiMain gui;
    private JPanel datePanel;
    private static final int HEIGHT = 300;
    private static final int WIDTH = 300;

    // REQUIRES: gui is a functional main panel
    // EFFECTS: creates an 'Add Event' window, where a player can add info on events they wish to add
    public AddEventWindow(GuiMain gui) {
        this.gui = gui;
        occasionList = gui.getEventList();
        initializeComponents();

        addEventFrame.setSize(WIDTH, HEIGHT);
        addEventFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addEventFrame.add(addEventPanel);
        addEventFrame.setTitle("Adding an Event");
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

    // MODIFIES: this
    // EFFECTS: creates instances of objects required in the window
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

    // MODIFIES: this
    // EFFECTS: creates a panel to be added to 'Add Event' panel, allowing user to input year,
    //          month, day, and name
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

    // EFFECTS: returns an array of the months of the year
    public String[] getMonths() {
        String[] monthList = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
                "Nov", "Dec"};
        return monthList;
    }

    // MODIFIES: this, gui
    // EFFECTS: adds an event if 'add event' inputs are appropriate
    public void makeEvent() {
        try {
            String yearString = yearBox.getText();
            String dayString = dayBox.getText();
            String eventName = nameBox.getText();
            int year = Integer.parseInt(yearString);
            String monthString = (String) monthsBox.getSelectedItem();
            int day = Integer.parseInt(dayString);
            if (occasionList.isValidDayInput(monthString, day)) {
                Occasion userOccasion = new Occasion(eventName,
                        occasionList.makeOccasionDate(year, monthString, day), new ArrayList<>());
                occasionList.addOccasion(userOccasion);
                gui.getGuiEvents().addElement(userOccasion);
            }
        } catch (NumberFormatException e) {
            // the code should not do anything if this error is caught
        } catch (NullPointerException e) {
            // the code should not do anything if this error is caught
        }
    }

    // MODIFIES: this
    // EFFECTS: makes an event if add event button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            makeEvent();
        }
    }
}
