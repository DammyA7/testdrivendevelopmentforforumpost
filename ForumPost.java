import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


//Oluwadamilola Adebayo - 20721685
public class ForumPost extends User {
	private String forumTitle;//variable for the post title
	private LocalDateTime forumtimestamp; //variable for when the post was created or when it should be created
	private String forumText;//variable for the posts contents 
	private static String uniqueID;//generated when a post is created
	
	
	public ForumPost() {//#1
		forumtimestamp = getTimestamp();
		setUniqueID(UUID.randomUUID().toString());
	}
	public ForumPost(LocalDateTime dateTime) {//#2
		setTimeStamp(dateTime);
	}
	public ForumPost(String title) {//#3
		forumTitle = title;
		forumtimestamp = getTimestamp();
	}
	
	//the constructors #1, #2, & #3 are only created for testing purposes. as in production the constructors #4 & #5 will be used for instances 
	//when a post is created without specifying its timestamp the current time is used. posts can't be created with dates in the past
	//in such cases the current time is assigned. In the case a future localdatetime value is passed the post will be scheduled for that date/time.
	public ForumPost(String title, String text) {//#4
		forumTitle = title;
		forumText = text;
		forumtimestamp = getTimestamp();//defaults to current time
		setUniqueID(UUID.randomUUID().toString());
	}
	public ForumPost(String title, String text, LocalDateTime dateTime) {//#5
		forumTitle = title;
		forumText = text;
		setTimeStamp(dateTime);
		setUniqueID(UUID.randomUUID().toString());
	}
	
	LocalDateTime getTimestamp() {//formats the current datetime and returns the value
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm");
		String text = now.format(format);
		LocalDateTime parsedDateTime = LocalDateTime.parse(text, format);
		return parsedDateTime;
	}
	
	void setTimeStamp(LocalDateTime dateTime) {//checks if the provided datetime is in the past, if yes sets the the forumTimeStamp variable to the current datetime
		forumtimestamp = dateTime.isAfter(getTimestamp()) ? dateTime : getTimestamp();
		if(dateTime.isBefore(getTimestamp())) {
			System.out.println("A date in the past cannot be assigned to your post. So the current date and time is assigned!");
		}
	}
	
	String getForumPostTitle() {//returns the value of the post's title
		return forumTitle;
	}
	
	String getForumPostText(){//returns the value of the post's content
		return forumText;
	}
	LocalDateTime getForumTimeStamp(){//returns the value of the post's timestamp
		return forumtimestamp;
	}
	
	void makeTextEdit(String text) {//Enables the user to make edits to the post's contents
		forumText = text;
	}
	
	void makeTitleEdit(String title) {//Enables the user to make edits to the post's title
		forumTitle = title;
	}
	
	String getPost() {// returns entire information of the users post
		return "TimeStamp: " + getForumTimeStamp().toString() + "\nTitle: " + getForumPostTitle() + "\nPost: " + getForumPostText();
	}
	public static String getUniqueID() {//gives access to read the value assigned to the uniqueID variable
		return uniqueID;
	}
	public static void setUniqueID(String uniqueID) {//allows uniqueID to be assigned to a value
		ForumPost.uniqueID = uniqueID;
	}
	
}
