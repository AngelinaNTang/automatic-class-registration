import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * Author: Angelina Tang
 *
 * This class asks for user input and drives the program.
 *
 */
public class Driver
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        WebDriver driver = signInSuccessfully(scanner);
        String htmlSource = driver.getPageSource();

        ArrayList<Schedule> schedules = creatingSchedules(scanner);

        for (int i = 0; i < schedules.size(); i++)
        {
            WebScrape webScrape = new WebScrape(htmlSource);
            webScrape.parseCurrentSchedule();
            webScrape.compareSchedules(schedules.get(i));
            boolean success = webScrape.enterNewCourses(driver, schedules.get(i));
            if (success)
           {
               System.out.println("Schedule #" + (i + 1) + " was registered successfully.\nNow exiting program." );
               System.exit(0);
           }
        }
    }

    public static WebDriver signInSuccessfully(Scanner input)
    {
        boolean signInSuccessful = false;
        WebDriver driver = null;

        while (!signInSuccessful)
        {
            System.out.println("\nPlease insert username:");
            String userLogin = input.nextLine();
            System.out.println("\nPlease insert password:");
            String userPassword = input.nextLine();

            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            driver = new ChromeDriver();

            Login instance = new Login(userLogin, userPassword, driver);
            signInSuccessful = instance.signIn();
        }
        return driver;
    }

    public static ArrayList<Schedule> creatingSchedules(Scanner scanner)
    {
        System.out.println("\n Enter the name of the text file containing the schedules: ");
        String fileName = scanner.nextLine();

        Scanner input = null;
        ArrayList<Schedule> schedules = new ArrayList<>();

        try
        {
            input = new Scanner(new FileInputStream((fileName)));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
            System.exit(0);
        }

        int numberOfSchedules = input.nextInt();

        for (int i = 0; i < numberOfSchedules; i++)
        {
            Schedule newestSchedule = new Schedule();
            int numberOfCourses = input.nextInt();

            for (int j = 0; j < numberOfCourses; j++)
            {
                String sln = input.next();
                String credits = input.next();
                String classCode = input.next();

                Course course = new Course(sln, credits);
                newestSchedule.addCourse(classCode, course);
            }
            schedules.add(newestSchedule);
        }
        input.close();

        return schedules;
    }
}