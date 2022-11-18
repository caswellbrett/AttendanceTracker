package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class AddAttendeeWindow extends JFrame implements ActionListener {
    private JLabel attendeeLabel;
    private JButton attendeeButton;
    private JTextField attendeeField;
    private JFrame attendeeFrame;
    private JPanel attendeePanel;
    private GuiMain gui;

    public AddAttendeeWindow(GuiMain gui) {
        this.gui = gui;
        attendeeFrame = new JFrame("Adding an Attendee");
        attendeeFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        attendeeFrame.setVisible(true);

        attendeePanel = new JPanel();
        attendeePanel.setLayout(new BoxLayout(attendeePanel, BoxLayout.Y_AXIS));
        attendeeFrame.add(attendeePanel);

        attendeeLabel = new JLabel("Please add the name of the attendee:");
        attendeeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        attendeePanel.add(attendeeLabel);

        attendeeField = new JTextField();
        attendeeField.setAlignmentX(Component.LEFT_ALIGNMENT);
        attendeePanel.add(attendeeField);

        attendeeButton = new JButton("Add Attendee");
        attendeeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        attendeeButton.addActionListener(this);
        attendeePanel.add(attendeeButton);

        attendeeFrame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == attendeeButton) {
            try {
                String attendee = attendeeField.getText();
                gui.getSelectedEvent().addAttendee(attendee);
                gui.getGuiAttendees().addElement(attendee);
            } catch (NullPointerException npe) {
                // nothing should happen if text field is null
            }
        }
    }
}
