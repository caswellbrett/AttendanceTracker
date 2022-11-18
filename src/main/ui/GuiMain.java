package ui;

import model.Event;
import model.EventList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.imageio.*;
import java.io.File;

public class GuiMain extends JPanel implements ActionListener, ListSelectionListener {

    private JFrame frame;
    private JButton saveButton;
    private JButton loadButton;
    private JButton eventAddButton;
    private JButton attendeeAddButton;
    private JPanel buttonPanel;
    private JList<Event> guiEventList;
    private JList guiAttendeeList;
    private JScrollPane eventScroll;
    private JScrollPane attendeeScroll;
    private DefaultListModel<Event> eventListModel;
    private DefaultListModel<String> attendeeListModel;
    private EventList eventList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/eventListGui.json";
    private JPanel logoPanel;
    private JPanel leftPanel;
    private ImageIcon logoImage;
    private JLabel logoPic;
    private JLabel logoText;

    public GuiMain() {
        eventList = new EventList("User's Events");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        createFrame();
        createButtonPanel();
        createLists();
        createLogo();
        createLeftPanel();

        frame.add(leftPanel);
        frame.add(attendeeScroll);
        frame.setVisible(true);
    }

    public void createFrame() {
        frame = new JFrame("Attendance Tracker");
        frame.setLayout(new GridLayout(1, 2));
        //frame.pack();
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createButtonPanel() {
        saveButton = new JButton("Save file");
        loadButton = new JButton("Load file");
        eventAddButton = new JButton("Add event");
        attendeeAddButton = new JButton("Add attendee");

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(eventAddButton);
        buttonPanel.add(attendeeAddButton);

        eventAddButton.addActionListener(this);
        attendeeAddButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
    }

    public void createLists() {
        eventListModel = new DefaultListModel<>();
        guiEventList = new JList<>();
        guiEventList.setModel(eventListModel);
        guiEventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        guiEventList.addListSelectionListener(this);

        attendeeListModel = new DefaultListModel<>();
        guiAttendeeList = new JList<>();
        guiAttendeeList.setModel(attendeeListModel);

        eventScroll = new JScrollPane(guiEventList);
        attendeeScroll = new JScrollPane(guiAttendeeList);
    }

    public void createLogo() {
        logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout());

        logoPic = new JLabel();
        logoPic.setSize(100,100);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("./data/calendar-icon.png"));
        } catch (IIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dimg = img.getScaledInstance(logoPic.getWidth(), logoPic.getHeight(),
                Image.SCALE_SMOOTH);

        logoImage = new ImageIcon(dimg);
        logoPic.setIcon(logoImage);
        logoText = new JLabel("AttendanceTracker");

        logoPanel.add(logoPic);
        logoPanel.add(logoText);
    }

    public void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(eventScroll);
        leftPanel.add(buttonPanel);
        leftPanel.add(logoPanel);
    }

    // EFFECTS: saves user's event list as JSON file
    private void saveEventList() {
        try {
            jsonWriter.open();
            jsonWriter.write(eventList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads a JSON file
    private void loadEventList() {
        try {
            eventList = jsonReader.read();
            for (Event event : eventList.getEventsList()) {
                eventListModel.addElement(event);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == eventAddButton) {
            new AddEventWindow(this);
        }
        if (e.getSource() == attendeeAddButton) {
            new AddAttendeeWindow(this);
        }
        if (e.getSource() == saveButton) {
            saveEventList();
        }
        if (e.getSource() == loadButton) {
            loadEventList();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        attendeeListModel.clear();
        Event event = guiEventList.getSelectedValue();
        for (String attendee : event.getAttendees()) {
            attendeeListModel.addElement(attendee);
        }
    }

    public DefaultListModel<Event> getGuiEvents() {
        return eventListModel;
    }

    public DefaultListModel<String> getGuiAttendees() {
        return attendeeListModel;
    }

    public Event getSelectedEvent() {
        return guiEventList.getSelectedValue();
    }

    public EventList getEventList() {
        return eventList;
    }

    public static void main(String[] args) {
        new GuiMain();
    }
}

// TODO create user stories thingies
// TODO implement a graphic
// TODO create documentation