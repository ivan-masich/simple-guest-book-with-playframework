package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

/**
 * Messages model.
 *
 * @author Ivan Masich w3cvalid@gmail.com
 */
@Entity
public class Message extends Model {

    @Lob
    public String content;

    @ManyToOne
    public User author;

    public int rating = 0;

    public Date postedAt;

    public Message(String content, User author) {
        this.author = author;
        this.content = content;
        this.postedAt = new Date();
    }

    /**
     * Get all messages ordered by posted date desc.
     *
     * @return Messages list.
     */
    public static List<Message> getAllOrderedByPostedDate() {
        return Message.find("order by postedAt desc").fetch();
    }

    /**
     * Rate up.
     *
     * @return Current message.
     */
    public Message rateUp() {
        rating++;
        return save();
    }

    /**
     * Rate down.
     *
     * @return Current message.
     */
    public Message rateDown() {
        rating--;
        return save();
    }
}
