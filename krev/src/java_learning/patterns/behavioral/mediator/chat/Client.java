package java_learning.patterns.behavioral.mediator.chat;

public class Client {
    public static void main(String[] args) {
        ChatMediator chatMediator = new ChatMediator();

        Admin admin = new Admin(chatMediator, "Admin");
        UsualUser u1 = new UsualUser(chatMediator, "user1");
        UsualUser u2 = new UsualUser(chatMediator, "user2");
        UsualUser u3 = new UsualUser(chatMediator, "user3");

        chatMediator.setAdmin(admin);
        chatMediator.addUserToChat(u1);
        chatMediator.addUserToChat(u2);
        chatMediator.addUserToChat(u3);

        u1.sendMessage("Hello all from " + u1.getName());
        admin.sendMessage("Updates from admin!");
    }
}
