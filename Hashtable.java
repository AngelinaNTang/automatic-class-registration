import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class Hashtable
{
//    HashMap<String, Course> allCourses;
//    HashMap<String, Course> nonAddedCourses;
//    int numberOfCurrentCourses;
//
//    public Hashtable()
//    {
//        this.allCourses = new HashMap<>();
//        this.nonAddedCourses = new HashMap<>();
//        this.numberOfCurrentCourses = 0;
//    }
//
//    public Hashtable(HashMap nonAddedCourses)
//    {
//        this.allCourses = new HashMap<>();
//        this.nonAddedCourses = nonAddedCourses;
//        this.numberOfCurrentCourses = allCourses.size() - nonAddedCourses.size();
//    }
//
//    public Hashtable(HashMap allCourses, HashMap nonAddedCourses)
//    {
//        this.allCourses = allCourses;
//        this.nonAddedCourses = nonAddedCourses;
//        this.numberOfCurrentCourses = allCourses.size() - nonAddedCourses.size();
//    }
//
//    public void setAllCourses(HashMap<String, Course> allCourses)
//    {
//        this.allCourses = allCourses;
//    }
//
//    //TODO: test to make sure gson works T_T
//    public HashMap<String, Course> getAllCourses()
//    {
//        return getCloneHashMap(allCourses);
//    }
//
//    public void setNonAddedCourses(HashMap<String, Course> nonAddedCourses)
//    {
//        this.nonAddedCourses = nonAddedCourses;
//    }
//
//    //TODO: test to make sure gson works T_T
//    public HashMap<String, Course> getNonAddedCourses()
//    {
//        return getCloneHashMap(nonAddedCourses);
//    }
//
//    //TODO: this thing has the gson in it cuz i made a thingy
//    private HashMap<String, Course> getCloneHashMap(HashMap<String, Course> courses) {
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(courses);
//
//        Type type = new TypeToken<HashMap<String, Course>>(){}.getType();
//        HashMap<String, Course> clonedMap = gson.fromJson(jsonString, type);
//
//        return clonedMap;
//    }
//
//    public void setNumberOfCurrentCourses(int numberOfCurrentCourses)
//    {
//        this.numberOfCurrentCourses = numberOfCurrentCourses;
//    }
//
//    public int getNumberOfCurrentCourses()
//    {
//        return numberOfCurrentCourses;
//    }
//
//    public void addCourse(String classcode, Course course)
//    {
//        if (!allCourses.containsKey(classcode))
//        {
//            allCourses.put(classcode, course);
//            nonAddedCourses.put(classcode, course);
//        }
//        else
//        {
//            removeCourse(classcode);
//        }
//    }
//
//    public void removeCourse(String classcode)
//    {
//        if (allCourses.containsKey(classcode))
//        {
//            allCourses.remove(classcode);
//        }
//        if (nonAddedCourses.containsKey(classcode))
//        {
//            nonAddedCourses.remove(classcode);
//        }
//    }
//
//    public void replaceCourse(String oldCode, String newCode, Course newCourse)
//    {
//        addCourse(newCode, newCourse);
//        removeCourse(oldCode);
//    }
}
