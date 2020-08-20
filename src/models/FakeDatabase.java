package models;

import java.util.ArrayList;

public class FakeDatabase {

    public ArrayList<User> users = new ArrayList<>();

    FakeDatabase() {
        users.add(new User("Guest"));
        users.add(new User(1, "Izzat", "abc"));
        users.add(new User(2, "Ikhmal", "123"));
        users.add(new User(3, "Syakil", "abc123"));
    }

    public Boolean authenticate(String name, String password) {

        for (User user : users) {
            if (! name.equals(user.name)) continue;
            return (password.equals(user.password));
        }

        return false;
    }

    public User findUser(int id) {
        return users.get(id);
    }

    public User findUserByName(String name) {
        for (User user : users) if (user.name.equals(name)) return user;
        return users.get(0);
    }

    public int updateUserCoordinate(int id, int distance) {
        User user = findUser(id);
        user.coordinate += distance;
        users.set(user.id, user);

        return user.coordinate;
    }
}
