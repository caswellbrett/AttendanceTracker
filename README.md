# My Personal Project

## Attendance tracker

As a swim coach, I'm tasked with recording
the attendance of each practice/occasion that my
swimmers are expected to attend. However, the
software used to record attendance at my swim 
club is *impractical*. As a result, I wish to 
create an attendance tracker that stores
names/dates of practices (or other occasions),
along with the names of swimmers who attended
the particular practice/occasions.

Although this project was inspired by my job
as a swim coach, it could be used by any
person or organization who wishes to record
attendance for meetings, occasions, practice,
**and more**.

This project is especially interesting to me,
because I feel there could be many interesting
add-ons to the program. For example,
it would be helpful if the program could
keep track of each individual swimmer's
attendance and return their overall attendance
rate over the season.

## User Stories
- P0 user stories
  - As a user, I wish to create an occasion with 
  an associated date.
  - As a user, I wish to add a person to a
  particular occasion, showing that they
  attended the occasion.
  - As a user, I wish to create as many
  occasions as I would like and add as many
  people to those occasions as I'd like.
  - As a user, I wish to search for a
  particular occasion and see how many people
  attended that occasion.
- P1 user stories
    - As a user, I want to be able to save my
  list of occasions in Attendance Tracker to a 
  file
    - As a user, I want to be able to load my
  occasions from Attendance Tracker from file

# Instructions for Grader
- You can generate the first required occasion 
related to adding Xs to a Y by clicking the 
'add occasion' button on the main window. A new
'add occasion' window will appear. After filling in 
the occasion's name and date, click 'Add occasion' 
on the pop-up window.
- You can generate the second required occasion 
related to adding Xs to a Y by clicking the 
'add attendee' button. A new window will appear.
Add the name of the attendee and click the
'add attendee' button on the new window.
- You can locate my visual component by
directing your attention to the lower-left
corner of the application's main window. You
will find a logo of a calendar.
- You can save the state of my application by
clicking the 'save file' button on the main
window.
- You can reload the state of my application by
clicking the 'load file' button on the main
window.

## Phase 4: Task 2
- An occasion named "Practice" was added at 
Tue Nov 22 16:08:49 PST 2022
- An occasion named "Funeral" was added at 
Tue Nov 22 16:09:00 PST 2022
- An occasion named "Wedding" was added at
Tue Nov 22 16:09:40 PST 2022

## Phase 4: Task 3
If I were to refactor my project, there are a
few things I would do to improve the design of
my project. Firstly, I would seperate GuiMain
into 2 classes. The first class would be
solely responsible for running the project and
the second class would be responsible for
generating/operating the main window. This
design change would improve cohesion since
our current class handles two different
responsibilities. I would also wish to improve
cohesion by refactoring EventList into two
classes. Currently, there are two
responsibilities associated with the EventList
class: (1) keep a list of events and (2) 
generate appropriate dates for these events.
As a result, making a Date class would ensure
each class handles only one responsibility.
Lastly, I would potentially consider 
implementing an abstract class called "Window",
but this implementation would come with 
trade-offs. Although this refactoring would 
reduce duplication in code, it would also 
increase coupling between classes.