import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * Author: Angelina Tang
 *
 * This class uses user input to log the student into the UW registration website.
 *
 */
public class Login
{
    private WebDriver driver;
    private CharSequence username;
    private CharSequence password;

    public Login()
    {
        this.username = null;
        this.password = null;
        this.driver = null;
    }

    public Login(String userLogin, String userPassword, WebDriver driver)
    {
        this.username = userLogin;
        this.password = userPassword;
        this.driver = driver;
    }

    /**
     *
     * This method returns the password as a string.
     *
     * @return String - the user's password
     *
     */
    public String getPassword()
    {
        return (String) password;
    }

    /**
     *
     * This method sets the class variable password to the CharSequence given by the user.
     *
     * @param CharSequence password - the user's password
     *
     */
    public void setPassword(CharSequence password)
    {
        this.password = password;
    }

    /**
     *
     * This method sets the class variable password to the String given by the user.
     *
     * @param String password - the user's password
     *
     */
    public void setPassword(String password)
    {
        this.password = (CharSequence) password;
    }

    /**
     *
     * This method returns the username as a string.
     *
     * @return String - the user's username
     *
     */
    public String getUsername()
    {
        return (String) username;
    }

    /**
     *
     * This method sets the class variable password to the CharSequence given by the user.
     *
     * @param CharSequence username - the user's username
     *
     */
    public void setUsername(CharSequence username)
    {
        this.username = username;
    }

    /**
     *
     * This method sets the class variable password to the String given by the user.
     *
     * @param String username - the user's username
     *
     */
    public void setUsername(String username)
    {
        this.username = (CharSequence) username;
    }

    /**
     *
     * This method completes the sign in process by using the driver to access the UW registration website. It enters
     * the username and password given by the user in their respective boxes. Finally, it clicks the sign in button
     * to log the user in.
     *
     * @return boolean - if the sign-in was successful or not.
     *
     */
    public boolean signIn() {
        boolean signInSuccessful;

        //goes to the site
        driver.get("https://sdb.admin.uw.edu/students/uwnetid/register.asp");

        //enters username
        WebElement usernameBox = driver.findElement(By.id("weblogin_netid"));
        usernameBox.click();
        usernameBox.sendKeys(username);

        //enters password
        WebElement passwordBox = driver.findElement(By.id("weblogin_password"));
        passwordBox.click();
        passwordBox.sendKeys(password);

        //clicks sign in
        WebElement signInBox = driver.findElement(By.id("submit_button"));
        signInBox.click();

        //login success
        if (driver.getTitle().equals("Registration")) {
            signInSuccessful = true;
        } else {
            System.out.println("Sign-in failed, please try again!");
            driver.quit();
            signInSuccessful = false;
        }
        return signInSuccessful;
    }
}