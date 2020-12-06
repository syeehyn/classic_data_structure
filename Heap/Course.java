/*
 * NAME: Jackie Yuan
 */
import java.util.LinkedList;
import java.util.List;

/**
 * the class contians the implement of course Object.
 * @author Shuibenyang Yuan
 * @since 05.27.2018
 */
public class Course implements Course_Interface {
    private String courseName;
	private String courseCode;
	private MyPriorityQueue<Registration> waitlistQueue;
	private List<Student> roster;
	private int maxCapacity;

	public Course(String name, String code, int capacity) {
		courseName = name;
		courseCode = code;
		maxCapacity = capacity;
		waitlistQueue = new MyPriorityQueue<>(capacity);
		roster = new LinkedList<>();
	}
	/**
     * Accessor for course name
     *
     * @return course name
     */
    @Override
    public String getCourseName() {
        return courseName;
    }

    /**
     * Accessor for course code
     *
     * @return course code
     */
    @Override
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Accessor for course capacity
     *
     * @return course capacity
     */
    @Override
    public int getCourseCapacity() {
        return maxCapacity;
    }

    /**
     * Accessor for Course Roster
     *
     * @return course roster
     */
    @Override
    public List<Student> getCourseRoster() {
        return roster;
    }

    /**
     * Checks whether the course enrollment has reached its capacity
     *
     * @return Returns true if the number of enrolled students is equal to
     * capacity, false otherwise
     */
    @Override
    public boolean isFull() {
        return getCourseRoster().size() == getCourseCapacity();
    }

    /**
     * Enqueues the student to the waitlist for this course
     *
     * @param r Registration to be waitlisted
     */
    @Override
    public void addToWaitlist(Registration r) {
        // add wl to student.
        r.getStudent().waitlistCourse(r.getCourse());
        r.getStudent().deductCoins(r.getCoins());
        // add student to wl.
        waitlistQueue.offer(r);
    }

    /**
     * Enrolls the next student in the waitlist to the course. Does nothing if
     * the waitlist is empty
     *
     * @return Registration Request that was processed
     */
    @Override
    public Registration processWaitlist() {
        try { // exception happens when wl queue is empty.
            Registration toAdd = waitlistQueue.poll();
            roster.add(toAdd.getStudent());
            toAdd.getStudent().enrollCourse(toAdd.getCourse());
            return toAdd;
        } catch (NullPointerException e) {
            return null;
        }
    }

	@Override
	public String toString() {
		return courseCode;
	}
}
