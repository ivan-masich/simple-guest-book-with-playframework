package models;

import org.junit.*;
import play.test.*;

public class UserTest extends UnitTest {

    @Before
    public void setUp() {
        Fixtures.deleteDatabase();
    }

    @Test
    public void testCreateAndRetrieveUser() {
        new User("test1", "qaz123").save();
        User test = User.find("byUsername", "test1").first();

        assertNotNull(test);
        assertEquals("test1", test.username);
        assertEquals("qaz123", test.password);
    }

    @Test
    public void testFindByUsername() {
        User user = new User("test2", "qaz123").save();

        assertEquals(user, User.findByUsername("test2"));
    }

    @Test
    public void testToString() {
        User user = new User("test2", "qaz123");

        assertEquals("test2", user.toString());
    }
}
