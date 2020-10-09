/**
 *
 * Author: Angelina Tang
 *
 * This class holds an instance of a course.
 *
 */
public class Course
{
    private String sln;
    private String credits;
    private boolean isOnSchedule;

    public Course()
    {
        this.sln = null;
        this.credits = null;
        this.isOnSchedule = false;
    }

    public Course(String sln, String credits)
    {
        this.sln = sln;
        this.credits = credits;
        this.isOnSchedule = false;
    }

    public String getSln()
    {
        return sln;
    }

    public void setSln(String sln)
    {
        this.sln = sln;
    }

    public String getCredits()
    {
        return credits;
    }

    public void setCredits(String credits)
    {
        this.credits = credits;
    }

    public boolean isOnSchedule()
    {
        return isOnSchedule;
    }

    public void setOnSchedule(boolean onSchedule)
    {
        isOnSchedule = onSchedule;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Course course = (Course) o;
        return sln.equals(course.sln);
    }
}