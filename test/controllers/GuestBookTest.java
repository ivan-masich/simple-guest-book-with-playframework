package controllers;

import org.junit.Test;
import play.mvc.Http;
import play.test.FunctionalTest;

public class GuestBookTest extends FunctionalTest {

    @Test
    public void testThatIndexPageIsLocked() {
        Http.Response response = GET("/");
        assertStatus(302, response);
        assertHeaderEquals("Location", "/login", response);
    }
}
