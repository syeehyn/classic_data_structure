/*
 * NAME: Jackie Yuan
 */
import java.util.LinkedList;
import java.util.List;

/**
 * the class contians the Student object.
 * @author Shuibenyang Yuan
 * @since 05.27.2018
 */
public class Student implements Student_Interface {

    private String studentID;
    private String name;
    private List<Course> myEnrolledCourses;
    private List<Course> myWaitlist;
    private int courseCoins;

    /**
     * Constructor that populates the instance variables with parameters passed
     *
     * @param id StudentID
     * @param name Name of the student
     * @param coins Course Coins
     */
    public Student(String id, String name, int coins) {
        studentID = id;
        this.name = name;
        courseCoins = coins;
        myEnrolledCourses = new LinkedList<>();
        myWaitlist = new LinkedList<>();
    }


    /**
     * Adds course c to the list of enrolled courses Also removes c from the
     * waitlisted courses
     *
     * @param c Course to be enrolled in
     */
    @Override
    public void enrollCourse(Course c) {
        myWaitlist.remove(c);
        myEnrolledCourses.add(c);
    }

    /**
     * Adds course c to the waitlist
     *
     * @param c course to be waitlisted
     */
    @Override
    public void waitlistCourse(Course c) {
        myWaitlist.add(c);
    }

    /**
     * Accessor for name
     *
     * @return name - Name of the student
     */
    @Override
    public String getStudentName() {
        return name;
    }

    /**
     * Accessor for Student ID
     *
     * @return studentID - Student ID
     */
    @Override
    public String getStudentID() {
        return studentID;
    }

    /**
     * Returns a list of all enrolled courses
     *
     * @return List of enrolled courses
     */
    @Override
    public List<Course> getmyEnrolledCourses() {
        return myEnrolledCourses;
    }

    /**
     * Returns a list of all waitlisted courses
     *
     * @return List of waitlisted courses
     */
    @Override
    public List<Course> getmyWaitlist() {
        return myWaitlist;
    }

    /**
     * Accessor for course coins
     *
     * @return course coins
     */
    @Override
    public int getCoins() {
        return courseCoins;
    }

    /**
     * Deducts numCoins from coursecoins
     *
     * @param numCoins Number of coins to be deducted
     */
    @Override
    public void deductCoins(int numCoins) {
        courseCoins = courseCoins - numCoins;
    }



    /**
     * Returns a string representation of the Student that includes the name and
     * the studentID
     *
     * @return String representation of the student
     */
    @Override
    public String toString() {
        return this.name + "(" + this.studentID + ")";
    }
}
