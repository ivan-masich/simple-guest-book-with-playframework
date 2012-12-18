package models;

import org.junit.*;
import play.test.*;

public class VoteTest extends UnitTest {

    @Before
    public void setUp() {
        Fixtures.deleteDatabase();
    }

    @Test
    public void testIsVoteExist() {
        User user = new User("test1", "qaz123").save();
        User user1 = new User("test2", "qaz123").save();
        Message message = new Message("test content", user).save();
        new Vote(user, message).save();

        assertTrue(Vote.isVoteExist(user, message));
        assertFalse(Vote.isVoteExist(user1, message));
    }
}
