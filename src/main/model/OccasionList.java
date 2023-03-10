package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;

// represents a list of occasions
public class OccasionList implements Writable {
    private List<Occasion> listOfOccasions;
    private String name;

    // EFFECTS: instantiates an occasion list
    public OccasionList(String name) {
        listOfOccasions = new ArrayList<>();
        this.name = name;
    }

    // EFFECTS: Returns true if the given occasion name is already an occasion name in our list
    public boolean isNameTaken(String nameEntry) {
        boolean takenName = false;
        for (Occasion occasion : listOfOccasions) {
            if (nameEntry.equals(occasion.getName())) {
                takenName = true;
                break;
            }
        }
        return takenName;
    }

    // REQUIRES:    nameEntry cannot already be found in list of occasions
    //              date must be valid date, with month being first 3 letters of valid month and
    //              day being a day of the applicable month
    // MODIFIES:    this
    // EFFECTS:     adds an occasion created by the user to our occasion list
    public void addOccasion(Occasion occasion) {
        listOfOccasions.add(occasion);
        EventLog.getInstance().logEvent(new Event("An occasion named \"" + occasion.getName() + "\" was added at "));
    }

    // REQUIRES: searchName is the name of an occasion in our list of occasions
    // EFFECTS: returns the index of the occasion list that the user wishes to search
    public int findOccasionIndex(String searchName) {
        int index = 0;
        for (Occasion occasion : listOfOccasions) {
            String name = occasion.getName();
            if (name.equals(searchName)) {
                break;
            }
            index++;
        }
        return index;
    }

    // REQUIRES: month input is the first 3 letters of a month in a calendar
    // EFFECTS: returns true if the given day is an acceptable day of the given month, false if not
    //          (ex. returns true if given "Feb" and 15 but return false given "Feb" and 30)
    public boolean isValidDayInput(String monthInput, int dayInput) {
        monthInput = monthInput.toLowerCase();
        boolean valid = false;
        if (monthInput.equals("feb")) {
            if (dayInput > 0 && dayInput <= 29) {
                valid = true;
            }
        } else if (monthInput.equals("apr")
                || monthInput.equals("jun")
                || monthInput.equals("sep")
                || monthInput.equals("nov")) {
            if (dayInput > 0 && dayInput <= 30) {
                valid = true;
            }
        } else if (dayInput > 0 && dayInput <= 31) {
            valid = true;
        }
        return valid;
    }

    // EFFECTS: produces true if the given month input is the first 3 letters of a month name
    public boolean isValidMonthInput(String monthInput) {
        monthInput = monthInput.toLowerCase();
        return monthInput.equals("jan")
                || monthInput.equals("feb")
                || monthInput.equals("mar")
                || monthInput.equals("apr")
                || monthInput.equals("may")
                || monthInput.equals("jun")
                || monthInput.equals("jul")
                || monthInput.equals("aug")
                || monthInput.equals("sep")
                || monthInput.equals("oct")
                || monthInput.equals("nov")
                || monthInput.equals("dec");
    }

    // EFFECTS: transforms occasion list in JSON file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("occasions", occasionsToJson());
        return json;
    }

    // EFFECTS: returns occasions in occasion list as JSON array
    private JSONArray occasionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Occasion o : listOfOccasions) {
            jsonArray.put(o.toJson());
        }

        return jsonArray;
    }


    // REQUIRES:    month input is the first three letters of a month on the calendar
    //              day input is an appropriate date for the given month
    // EFFECTS:     takes given year, month, and date and returns the occasion date in the
    //              correct format (ex. "Feb 12, 2022")
    public String makeOccasionDate(int yearInput, String monthInput, int dayInput) {
        return (monthInput.substring(0,1).toUpperCase()
                + monthInput.substring(1,3).toLowerCase() + " " + dayInput + ", " + yearInput);
    }

    public List<Occasion> getOccasionsList() {
        return listOfOccasions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
