import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.Month;

//Oluwadamilola Adebayo - 20721685
class test {
	
	@Test
	void testForumPostTimeStampAsParameter() {//accepts localdatetime value as parameter
		ForumPost user = new ForumPost(LocalDateTime.of(2022, Month.SEPTEMBER, 20, 17, 54));
		assertTrue(user.getForumTimeStamp().equals(LocalDateTime.parse("2022-09-20T17:54")));
	}
	
	
	@Test
	void testForumPostTimeStamp() {//queries post for the timestamp
		ForumPost user = new ForumPost();
		assertTrue(user.getForumTimeStamp().equals(LocalDateTime.parse("2022-09-20T16:01")));
	}
	
	
	@Test
	void testForumPostTitle() {//queries post for title that was set when post was created
		ForumPost user = new ForumPost("Ikea bed for sell");
		assertTrue(user.getForumPostTitle() == "Ikea bed for sell");
	}
	
	
	@Test
	void testForumPostText(){//queries post for content that was set when post was created
		ForumPost user = new ForumPost("Ikea bed for sell", "IKEA bed. No longer needed.");
		assertTrue(user.getForumPostText() == "IKEA bed. No longer needed.");
	}
	
	@Test
	void testForumPostTextTitleEdit() {//enables for title and content to be edited
		ForumPost user = new ForumPost("Ikea bed for sell", "IKEA bed. No longer needed.");
		assertTrue(user.getForumPostText() == "IKEA bed. No longer needed.");
		
		user.makeTextEdit("Ikea bed. Is still needed");
		user.makeTitleEdit("Ikea bed for sale");
		assertTrue(user.getForumPostTitle() == "Ikea bed for sale");
		assertTrue(user.getForumPostText() == "Ikea bed. Is still needed");
	}

	
	@Test 
	void testForumCannotCreatePostThePast() {//schedules post to be created in the future
		ForumPost user = new ForumPost(LocalDateTime.of(2022, Month.SEPTEMBER, 18, 16, 24));
		assertFalse(user.getForumTimeStamp().isEqual(LocalDateTime.parse("2022-09-18T16:24")));
	}
	
	
	@Test
	void testForumPostHasUniqueId() {
		ForumPost user = new ForumPost();
		assertTrue(ForumPost.getUniqueID() != null);
	}
	
	@Test 
	void testForumPostToStringFormat(){//gets entire post content
		ForumPost user = new ForumPost("IKEA bed for sale €100 ono", "IKEA bed. No longer needed. Practically new. "
				+ "Low price for quick sale. Will consider exchange for Taylor Swift CDs.", LocalDateTime.of(2022, Month.SEPTEMBER, 25, 16, 00));
		assertTrue(user.getPost().equals("TimeStamp: 2022-09-25T16:00\nTitle: IKEA bed for sale €100 ono\nPost: IKEA bed. No longer needed. Practically new. Low price for quick sale. Will consider exchange for Taylor Swift CDs."));
	}

}

