To restrict access from CareTaker to state of Data class, we can apply the same approach as for 'outer' case:
i.e. to create interface like IData with limiter methods. In fact, it will not technically prohibit CareTaker to get originator's state
(if Caretaker will use class instead of interface), but... nevertheless.
More over it CareTaker is not tied with particular implementations of originator (and snapshot, if we still keep ISnapshot interface)
That's why we can create different types of Originators and its snapshot classes
and works with them using IOriginator and ISnapshot

//BUT we can't change data of originator from CareTaker class! since we interact with dot and rectangle through IData
        // => do not understand profit of this approach!