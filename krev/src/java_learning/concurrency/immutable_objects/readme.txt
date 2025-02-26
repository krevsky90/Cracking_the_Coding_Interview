info:
https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html

Idea: use immutable object to avoid any problems with Concurrency

How to change class of mutable object to immutable?
1) Don't provide "setter" methods — methods that modify fields or objects referred to by fields.
2) Make all fields final and private.
3) Don't allow subclasses to override methods.
    The simplest way to do this is to declare the class as final.
    A more sophisticated approach is to make the constructor private and construct instances in factory methods.
4) If the instance fields include references to mutable objects, don't allow those objects to be changed:
    a) Don't provide methods that modify the mutable objects.
    b) Don't share references to the mutable objects
        i.e. don't do this:
        private MyMutableClass obj;

        MyMutableClass getObj() {
            return obj;
        }
      what to do: create and return copies of your internal mutable objects when necessary to avoid returning the originals in your methods.

    c) Never store references to external, mutable objects passed to the constructor
        i.e. don't do this:
            private MyMutableClass obj;

            MyClass(MyMutableClass obj) {
                this.obj = obj;
            }
        if necessary, create copies, and store references to the copies.
