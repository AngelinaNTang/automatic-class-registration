import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class WebScrape
{
		private String html;
		private HashMap<String, Course> tracker;
		private ArrayList<String> allCodes;
		private ArrayList<String> removeCourses;
		private ArrayList<String> addCourses;

		public WebScrape()
		{
			this.html = null;
			this.tracker = new HashMap();
			this.allCodes = new ArrayList<>();
			this.removeCourses = new ArrayList<>();
			this.addCourses = new ArrayList<>();
		}

		public WebScrape(String html)
		{
			this.html = html;
			this.tracker = new HashMap();
			this.allCodes = new ArrayList<>();
			this.removeCourses = new ArrayList<>();
			this.addCourses = new ArrayList<>();
		}

		public void parseCurrentSchedule()
		{
			Document doc = Jsoup.parse(html);
			Element registrationForm = doc.select("form#regform").first();
			Element currentScheduleTable = registrationForm.select("table.sps_table").first();
			Elements tableData = currentScheduleTable.getElementsByTag("td");
			tableData.remove(tableData.size() - 1);
			tableData.remove(tableData.size() - 1);

			String sln = null;
			String classCode = null;
			String credits = null;

			int count = 0;

			for (Element e: tableData)
			{
				count++;

				if (count % 11 == 2) //sln
				{
					sln = e.text().substring(0,5);
				}
				if (count % 11 == 3) //classCode
				{
					classCode = e.text().replaceAll("\\s", "");
					classCode = classCode.substring(0, classCode.length() - 1);
				}
				if (count % 11 == 5) //credits
				{
					credits = e.text();
				}
				if (count % 11 == 0)
				{
					Course course = new Course(sln, credits);
					tracker.put(classCode, course);
				}
			}
		}

		public void compareSchedules(Schedule wantedSchedule)
		{
			Set<String> currentClassCodes = tracker.keySet();

			for (String s : currentClassCodes)
			{
				if (!wantedSchedule.getAllCourses().containsKey(s))
				{
					removeCourses.add(s);
				}
			}

			Set<String> wantedClassCodes = wantedSchedule.getAllCourses().keySet();

			for (String a : wantedClassCodes)
			{
				if (!tracker.containsKey(a))
				{
					addCourses.add(a);
				}
			}
		}

		public boolean enterNewCourses(WebDriver driver, Schedule wantedSchedule)
		{
			Document doc = Jsoup.parse(html);
			Element registrationForm = doc.select("form#regform").first();
			Element currentScheduleTable = registrationForm.select("table.sps_table").first();
			Elements tableData = currentScheduleTable.getElementsByTag("td");
			tableData.remove(tableData.size() - 1);
			tableData.remove(tableData.size() - 1);

			int count = 0;
			String classCode;

			for (int i = 0; i < removeCourses.size(); i++)
			{
				for (Element e: tableData)
				{
					count++;

					if (count % 11 == 3) //classCode
					{
						classCode = e.text().replaceAll("\\s", "");
						classCode = classCode.substring(0, classCode.length() - 1);
						if (classCode.equals(removeCourses.get(i)))
						{
							String xpath = removeXpath(e.previousElementSibling().previousElementSibling()
									.previousElementSibling().previousElementSibling().previousElementSibling()
									.previousElementSibling().getElementsByTag("input").first());

							WebElement checkbox = driver.findElement(By.xpath(xpath));
							checkbox.click();
						}
					}
				}
			}

			int currentNumberOfCourses = count / 11;

			Element newCoursesTable = registrationForm.select("table.sps_table").last();
			Elements tableData2 = newCoursesTable.getElementsByTag("td");

			for (int i = 0; i < addCourses.size(); i++)
			{
				currentNumberOfCourses += 1;
				String slnTextBoxName = "sln" + currentNumberOfCourses;
				String creditsTextBoxName = "credits" + currentNumberOfCourses;

				Course courseToAdd = wantedSchedule.findCourse(addCourses.get(i));
				String slnToAdd = courseToAdd.getSln();
				String creditsToAdd = courseToAdd.getCredits();

				WebElement slnInsert = driver.findElement(By.name(slnTextBoxName));
				slnInsert.click();
				slnInsert.sendKeys(slnToAdd);

				if (!creditsToAdd.equals("0"))
				{
					WebElement creditsInsert = driver.findElement(By.name(creditsTextBoxName));
					creditsInsert.click();
					creditsInsert.sendKeys(creditsToAdd);
				}
			}

			Element submitButton = registrationForm.getElementsByAttributeValue("type", "submit").first();
			String submitXpath = submitXpath(submitButton);
			WebElement submit = driver.findElement(By.xpath(submitXpath));
			submit.click();

//			try
//			{
//				driver.findElement(By.xpath("/html/body/div[2]/b"));
//			}
//			catch (NoSuchElementException e)
//			{
//				return true;
//			}

			WebElement successMessage = driver.findElement(By.xpath("/html/body/div[2]/b"));

			if (successMessage.getText().equals("Schedule not updated. Resolve errors listed below and resubmit."))
			{
				count = 0;
				classCode  = null;

				for (int i = 0; i < removeCourses.size(); i++)
				{
					for (Element e: tableData)
					{
						count++;

						if (count % 11 == 3) //classCode
						{
							classCode = e.text().replaceAll("\\s", "");
							classCode = classCode.substring(0, classCode.length() - 1);
							if (classCode.equals(removeCourses.get(i)))
							{
								String xpath = removeXpath(e.previousElementSibling().previousElementSibling()
										.previousElementSibling().previousElementSibling().previousElementSibling()
										.previousElementSibling().getElementsByTag("input").first());

								WebElement checkbox = driver.findElement(By.xpath(xpath));
								checkbox.click();
							}
						}
					}
				}

				currentNumberOfCourses = count / 11;

				for (int i = 0; i < addCourses.size(); i++)
				{
					currentNumberOfCourses += 1;
					String slnTextBoxName = "sln" + currentNumberOfCourses;
					String creditsTextBoxName = "credits" + currentNumberOfCourses;

					Course courseToAdd = wantedSchedule.findCourse(addCourses.get(i));
					String creditsToAdd = courseToAdd.getCredits();

					WebElement slnInsert = driver.findElement(By.name(slnTextBoxName));
					slnInsert.click();
					slnInsert.clear();

					if (!creditsToAdd.equals("0"))
					{
						WebElement creditsInsert = driver.findElement(By.name(creditsTextBoxName));
						creditsInsert.click();
						creditsInsert.clear();
					}
				}
				return false;
			}
			return true;
		}

	public static String removeXpath(Element element)
	{
		String xpath = "/html/body[1]/div[2]/form[1]/p[1]/";
		List<String> components = new ArrayList<>();

		Element child = element.tagName().isEmpty() ? element.parent() : element;
		while (child.parent() != null)
		{
			Element parent = child.parent();
			Elements siblings = parent.children();
			String componentToAdd = null;

			if (siblings.size() == 1)
			{
				componentToAdd = child.tagName();
			}
			else
			{
				int x = 1;
				for(Element sibling: siblings)
				{
					if (child.tagName().equals(sibling.tagName()))
					{
						if (child == sibling)
						{
							break;
						}
						else
						{
							x++;
						}
					}
				}
				componentToAdd = String.format("%s[%d]", child.tagName(), x);
			}
			if (!componentToAdd.equals("form[1]"))
			{
				components.add(componentToAdd);
				child = parent;
			}
			else
			{
				break;
			}
		}

		List<String> reversedComponents = new ArrayList<>();
		for (int i = components.size() - 1; i > 0; i--)
		{
			reversedComponents.add(components.get(i));
		}
		xpath = xpath + String.join("/", reversedComponents);

		return xpath;
	}

	public static String submitXpath(Element element)
	{
		String xpath = "/html/body[1]/div[2]/form[1]/";
		List<String> components = new ArrayList<>();

		Element child = element.tagName().isEmpty() ? element.parent() : element;
		while (child.parent() != null)
		{
			Element parent = child.parent();
			Elements siblings = parent.children();
			String componentToAdd = null;

			if (siblings.size() == 1)
			{
				componentToAdd = child.tagName();
			}
			else
			{
				int x = 1;
				for(Element sibling: siblings)
				{
					if (child.tagName().equals(sibling.tagName()))
					{
						if (child == sibling)
						{
							break;
						}
						else
						{
							x++;
						}
					}
				}
				componentToAdd = String.format("%s[%d]", child.tagName(), x);
			}
			if (!componentToAdd.equals("form[1]"))
			{
				components.add(componentToAdd);
				child = parent;
			}
			else
			{
				break;
			}
		}

		List<String> reversedComponents = new ArrayList<>();
		for (int i = components.size() - 1; i > 0; i--)
		{
			reversedComponents.add(components.get(i));
		}
		xpath = xpath + String.join("/", reversedComponents);

		xpath += "input[@type = 'submit']";

		return xpath;
	}
}
