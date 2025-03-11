If Snapshot is separate class => it must contain getters for its state
Originator (i.e. Data class) will call these getters to restore originator's state

BUT these getters should be available ONLY for originator => all other classes (including CareTaker) should have limited access to Snapshot's methods
to do this, we create ISnapshot interface with limited methods like getSnapshotName()
And CareTaker will operate with ISnapshot abstraction, but not DataSnapshot class itself!

BUT since concrete originator can restore its state from concrete Snapshot class
=> need to cast ISnapshot to DataSnapshot inside Data # restore() method

BUT in fact if some developer will use DataSnapshot class, but not ISnapshot interface, nobody can restrict him technically!
this is drawback!