package models;

import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Users model.
 *
 * @author Ivan Masich w3cvalid@gmail.com
 */
@Entity
public class User extends Model {

    @Column(unique=true, nullable=false)
    public String username;

    public String password;

    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public static User findByUsername(String username) {
        return find("byUsername", username).first();
    }

    @Override
    public String toString() {
        return username;
    }
}
