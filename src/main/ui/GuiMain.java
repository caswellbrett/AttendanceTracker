package ui;

import model.Event;
import model.EventLog;
import model.Occasion;
import model.OccasionList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.imageio.*;
import java.io.File;

// represents the main panel that is initially ran when the program starts
public class GuiMain extends JPanel implements ActionListener, ListSelectionListener {

    private JFrame frame;
    private JButton saveButton;
    private JButton loadButton;
    private JButton eventAddButton;
    private JButton attendeeAddButton;
    private JPanel buttonPanel;
    private JList<Occasion> guiEventList;
    private JList guiAttendeeList;
    private JScrollPane eventScroll;
    private JScrollPane attendeeScroll;
    private DefaultListModel<Occasion> occasionListModel;
    private DefaultListModel<String> attendeeListModel;
    private OccasionList occasionList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/eventListGui.json";
    private JPanel logoPanel;
    private JPanel leftPanel;
    private ImageIcon logoImage;
    private JLabel logoPic;
    private JLabel logoText;

    // EFFECTS: creates the main window with an occasion list, attendance list, buttons, and a logo
    public GuiMain() {
        occasionList = new OccasionList("User's Events");
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

    // MODIFIES: this
    // EFFECTS: creates the JFrame where all the main window's components will be placed
    public void createFrame() {
        frame = new JFrame("Attendance Tracker");
        frame.setLayout(new GridLayout(1, 2));
        //frame.pack();
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event);
                }
                super.windowClosing(e);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates a panel containing main panel's buttons
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

    // MODIFIES: this
    // EFFECTS: creates the events list and the attendees list panels
    public void createLists() {
        occasionListModel = new DefaultListModel<>();
        guiEventList = new JList<>();
        guiEventList.setModel(occasionListModel);
        guiEventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        guiEventList.addListSelectionListener(this);

        attendeeListModel = new DefaultListModel<>();
        guiAttendeeList = new JList<>();
        guiAttendeeList.setModel(attendeeListModel);

        eventScroll = new JScrollPane(guiEventList);
        attendeeScroll = new JScrollPane(guiAttendeeList);
    }

    // MODIFIES: this
    // EFFECTS: creates a logo to be displayed on main panel
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

    // MODIFIES: this
    // EFFECTS: combines button panel, logo panel, and event list panel with preferred layout
    public void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(eventScroll);
        leftPanel.add(buttonPanel);
        leftPanel.add(logoPanel);
    }

    // EFFECTS: saves user's event list as JSON file
    private void saveOccasionList() {
        try {
            jsonWriter.open();
            jsonWriter.write(occasionList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads a JSON file
    private void loadOccasionList() {
        try {
            occasionList = jsonReader.read();
            for (Occasion occasion : occasionList.getOccasionsList()) {
                occasionListModel.addElement(occasion);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: employs the intended action of a button if pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == eventAddButton) {
            new AddEventWindow(this);
        }
        if (e.getSource() == attendeeAddButton) {
            new AddAttendeeWindow(this);
        }
        if (e.getSource() == saveButton) {
            saveOccasionList();
        }
        if (e.getSource() == loadButton) {
            loadOccasionList();
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the value of attendee panel when a different event is selected
    @Override
    public void valueChanged(ListSelectionEvent e) {
        attendeeListModel.clear();

        Occasion occasion = guiEventList.getSelectedValue();
        for (String attendee : occasion.getAttendees()) {
            attendeeListModel.addElement(attendee);
        }
    }

    public DefaultListModel<Occasion> getGuiEvents() {
        return occasionListModel;
    }

    public DefaultListModel<String> getGuiAttendees() {
        return attendeeListModel;
    }

    public Occasion getSelectedEvent() {
        return guiEventList.getSelectedValue();
    }

    public OccasionList getEventList() {
        return occasionList;
    }

    // EFFECTS: runs the main window
    public static void main(String[] args) {
        new GuiMain();
    }
}