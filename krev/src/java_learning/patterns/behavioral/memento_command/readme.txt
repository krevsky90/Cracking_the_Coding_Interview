Refactor command example using memento pattern

i.e. each command will not backup only part of the object That will be changed by this command)
BUT each command will create full snapshot of the object, using memento pattern.
In this case, we can store reference to snapshot in AbstractCommand class, but not different fields in different command classes (that affected these fields)

NOTE: if we are sure that all commands use the same receiver (which = originator), we can move it to AbstractCommand
=> write saveBackup method in AbstractCommand class

But in general case, each command should
1) implement execute()
2) store reference to its receiver
3) store snapshot of the state of receiver
4) implement method saveBackup to create snapshot of receiver
    and call this method at the 1st line of execute()
5)  implement undo() method to restore backup, like
    public void undo() {
        snapshot.restore();
    }