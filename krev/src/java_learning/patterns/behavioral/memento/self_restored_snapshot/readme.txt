To solve the issue of 'outer' approach:
1) Snapshot class will store reference to originator object => will restore originator's state
i.e.
    public void restore() {
       originator.setX(x);
       originator.setY(y);
       originator.setS(s);
    }

That's why getters of snapshot class are not necessary => remove them! => nobody can see snapshot's state!