import java.util.ArrayList;

/**
 *
 * Author: Angelina Tang
 *
 */
public class CourseTracker
{
//    private ArrayList<Course> allCourses;
//    private ArrayList<Course> nonAddedCourses;
//    private int numberOfCurrentCourses;
//
//    public ArrayList<Course> getAllCourses()
//    {
//        return allCourses;
//    }
//
//    public void setAllCourses(ArrayList<Course> allCourses)
//    {
//        this.allCourses = allCourses;
//    }
//
//    public ArrayList<Course> getNonAddedCourses()
//    {
//        return nonAddedCourses;
//    }
//
//    public void setNonAddedCourses(ArrayList<Course> nonAddedCourses)
//    {
//        this.nonAddedCourses = nonAddedCourses;
//    }
//
//    public int getNumberOfCurrentCourses()
//    {
//        return numberOfCurrentCourses;
//    }
//
//    public void setNumberOfCurrentCourses(int numberOfCurrentCourses)
//    {
//        this.numberOfCurrentCourses = numberOfCurrentCourses;
//    }
//
//    public String nonAddedCoursesToString()
//    {
//        if (nonAddedCourses.size() == 0)
//        {
//            return "All courses already added.";
//        }
//        if (nonAddedCourses.size() == 1)
//        {
//            String s = "Non-Added Courses: " + nonAddedCourses.get(0).getSln();
//            return s;
//        }
//        else
//        {
//            String s = "Non-Added Courses: " + nonAddedCourses.get(0).getSln();
//
//            for (int i = 1; i < nonAddedCourses.size(); i++)
//            {
//                s += ", ";
//                s += nonAddedCourses.get(i).getSln();
//            }
//            return s;
//        }
//    }
//
//    public String allCoursesToString()
//    {
//        if (allCourses.size() == 0)
//        {
//            return "No courses listed.";
//        }
//        if (allCourses.size() == 1)
//        {
//            String s = "All Courses: " + allCourses.get(0).getSln();
//            return s;
//        }
//        else
//        {
//            String s = "All Courses: " + allCourses.get(0).getSln();
//
//            for (int i = 1; i < allCourses.size(); i++)
//            {
//                s += ", ";
//                s += allCourses.get(i).getSln();
//            }
//            return s;
//        }
//    }
//
//    public void add(Course course)
//    {
//        allCourses.add(course);
//        nonAddedCourses.add(course);
//    }
//
//    public void remove(Course course)
//    {
//        for (int i = 0; i < allCourses.size(); i++)
//        {
//            if (allCourses.get(i).equals(course));
//            {
//                if (!allCourses.get(i).isOnSchedule())
//                {
//                    for (int j = 0; j < nonAddedCourses.size(); j++)
//                    {
//                        if (nonAddedCourses.get(j).equals(course))
//                        {
//                            allCourses.remove(j);
//                        }
//                    }
//                }
//                allCourses.remove(i);
//            }
//        }
//    }
}