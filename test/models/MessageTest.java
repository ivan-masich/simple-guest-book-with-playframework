package models;

import org.junit.*;
import play.test.*;

import java.util.List;

public class MessageTest extends UnitTest {

    @Before
    public void setUp() {
        Fixtures.deleteDatabase();
    }

    @Test
    public void createAndRetrieveMessage() {
        User author = new User("test1", "qaz123").save();
        new Message("test content", author).save();
        Message message = Message.find("byAuthor", author).first();

        assertNotNull(message);
        assertEquals(author, message.author);
        assertEquals("test content", message.content);
    }

    @Test
    public void testGetAllOrderedByPostedDate() {
        User author = new User("test1", "qaz123").save();
        Message firstMessage = new Message("test1 content", author).save();
        Message secondMessage = new Message("test2 content", author).save();
        List<Message> messages = Message.getAllOrderedByPostedDate();

        assertEquals(secondMessage, messages.get(0));
        assertEquals(firstMessage, messages.get(1));
    }

    @Test
    public void testRateUp() {
        User author = new User("test1", "qaz123").save();
        Message message = new Message("test content", author).save();

        assertEquals(1, message.rateUp().rating);
        assertEquals(2, message.rateUp().rating);
    }

    @Test
    public void testRateDown() {
        User author = new User("test1", "qaz123").save();
        Message message = new Message("test content", author).save();

        assertEquals(-1, message.rateDown().rating);
        assertEquals(-2, message.rateDown().rating);
    }
}
