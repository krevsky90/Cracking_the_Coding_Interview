package java_learning.patterns.behavioral.mediator.chat;

import java.util.ArrayList;
import java.util.List;

public class ChatMediator implements IMediator {
    //contains all interacting components:
    private Admin admin;
    private List<AbstractUser> users;

    public ChatMediator() {
        this.users = new ArrayList<>();
    }

    public void addUserToChat(AbstractUser user) {
        users.add(user);
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public void notify(AbstractUser user, String message) {
        for (AbstractUser tempUser : users) {
            if (tempUser != user) {
                tempUser.readMessage(message);
            }
        }
        admin.readMessage(message);
    }
}
