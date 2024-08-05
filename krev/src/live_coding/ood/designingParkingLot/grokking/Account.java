package live_coding.ood.designingParkingLot.grokking;

import live_coding.ood.designingParkingLot.grokking.constants.AccountStatus;

public abstract class Account {
    private final String username;
    private String password;
    private Person person;
    private AccountStatus accountStatus;

    public Account(String username, String password, Person person) {
        this.username = username;
        this.password = password;
        this.person = person;
        this.accountStatus = AccountStatus.NONE;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
