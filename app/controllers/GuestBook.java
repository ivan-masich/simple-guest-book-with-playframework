package controllers;

import models.Message;
import models.User;
import models.Vote;
import play.data.validation.Required;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

/**
 * Guest book controller.
 *
 * @author Ivan Masich w3cvalid@gmail.com
 */
@With(Secure.class)
public class GuestBook extends Controller {

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            renderArgs.put("user", Security.connected());
        }
    }

    /**
     * Main page.
     */
    public static void index() {
        List<Message> userMessages = Message.getAllOrderedByPostedDate();
        render(userMessages);
    }

    /**
     * Post new message.
     *
     * @param content Message content
     */
    public static void postMessage(@Required String content) {
        if (validation.hasErrors()) {
            render("GuestBook/index.html");
        } else {
            new Message(content, getCurrentUser()).save();
            index();
        }
    }

    /**
     * Vote up for message.
     *
     * @param messageId Message unique identifier
     */
    public static void voteUp(Long messageId) {
        vote(messageId, Vote.Actions.UP);
    }

    /**
     * Vote down for message.
     *
     * @param messageId Message unique identifier
     */
    public static void voteDown(Long messageId) {
        vote(messageId, Vote.Actions.DOWN);
    }

    private static void vote(Long messageId, Vote.Actions action) {
        User user = getCurrentUser();
        Message message = Message.findById(messageId);

        if (Vote.isVoteExist(user, message)) {
            renderJSON("{\"error\":\"Vote already exist!\"}");
        } else {
            new Vote(user, message).save();

            if (Vote.Actions.UP.equals(action)) {
                message.rateUp();
            } else {
                message.rateDown();
            }

            renderJSON("{\"success\":\"Vote successfully added!\"}");
        }
    }

    private static User getCurrentUser() {
        return User.findByUsername(Security.connected());
    }
}