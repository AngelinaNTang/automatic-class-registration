import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class Schedule
{
    private HashMap<String, Course> allCourses;
    private int numberOfCourses;

    public Schedule()
    {
        this.allCourses = new HashMap<>();
        this.numberOfCourses = 0;
    }

    public Schedule(Schedule schedule)
    {
        this.allCourses = schedule.getAllCourses();
        this.numberOfCourses = getNumberOfCourses();
    }

    public Schedule (HashMap courses, int numberOfCourses)
    {
        this.allCourses = courses;
        this.numberOfCourses = numberOfCourses;
    }

    public Schedule(HashMap courses)
    {
        this.allCourses = courses;
        this.numberOfCourses = allCourses.size();
    }

    public int getNumberOfCourses()
    {
        return numberOfCourses;
    }

    public void setNumberOfCourses(int numberOfCourses)
    {
        this.numberOfCourses = numberOfCourses;
    }

    public HashMap<String, Course> getAllCourses()
    {
        Gson gson = new Gson();
        String jsonString = gson.toJson(allCourses);

        Type type = new TypeToken<HashMap<String, Course>>(){}.getType();
        HashMap<String, Course> clonedMap = gson.fromJson(jsonString, type);

        return clonedMap;
    }

    public void setAllCourses(HashMap<String, Course> allCourses)
    {
        this.allCourses = allCourses;
    }

    public void addCourse(String classcode, Course course)
    {
        if (!allCourses.containsKey(classcode))
        {
            allCourses.put(classcode, course);
            numberOfCourses++;
        }
        else
        {
            removeCourse(classcode);
            allCourses.put(classcode, course);
        }
    }

    public void removeCourse(String classcode)
    {
        if (allCourses.containsKey(classcode))
        {
            allCourses.remove(classcode);
        }
    }

    public void replaceCourse(String oldCode, String newCode, Course newCourse)
    {
        addCourse(newCode, newCourse);
        removeCourse(oldCode);
    }

    public Course findCourse(String classcode)
    {
        if (allCourses.containsKey(classcode))
        {
            return allCourses.get(classcode);
        }
        return null;
    }
}
