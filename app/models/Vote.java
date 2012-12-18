package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Votes model.
 *
 * @author Ivan Masich w3cvalid@gmail.com
 */
@Entity
public class Vote extends Model {

    public static enum Actions {
        UP, DOWN
    }

    @ManyToOne
    public User user;

    @ManyToOne
    public Message message;

    public Vote(User user, Message message) {
        this.message = message;
        this.user = user;
    }

    public static boolean isVoteExist(User user, Message message) {
        return find("byUserAndMessage", user, message).first() != null;
    }
}
