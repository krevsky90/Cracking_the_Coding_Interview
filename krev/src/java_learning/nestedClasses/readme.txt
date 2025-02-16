info:
https://www.youtube.com/watch?v=10R_2-2HIX0&list=PLqj7-hRTFl_oDMBjI_EstsFcDAwt-Arhs&index=65

Nested classes:
    1) static
        оч похож на внешний, но нах-ся внутри другого класса (для него так же действуют access modifiers)
        static nested класс может содержать static и non-static элементы
        static nested класс может обращаться к private элементам внешнего класса, НО только если они static
        внешний класс может обращаться даже к private эл-там static nested класса
        static класс может имплементить интерфейсы, экстендить классы (и его могут экстендить), он мб final/abstract
        внутри static класса мб свои static классы
    2) inner
        каждый объект иннер класса ассоциируется с объектом внешнего класса (нельзя создать иннер объект без предварительного создания объекта внешнего класса)
        иннер класс содержит только NON-static элементы. Исключение: если сделать поле final (т.е. получится константа), тогда можно
        inner class has access even to private static and non-static elements of external class
        external class has access to private elements of object of inner class (after we create object of inner class)
        inner класс может имплементить интерфейсы, экстендить классы (и его могут экстендить), он мб final/abstract
        внутри inner класса мб свои inner классы

    3) local (inside method or constructor)
        local класс, как local-переменная! т.е. может быть внутри конструктора или метода
        не м иметь access modifier
        виден только в пределах блока кода, где объявлен этот класс (т.е. метод или конструктор)
        не м б static
        может обращаться даже к private элементам внешнего класса
        может обращаться к эл-там блока, ЕСЛИ они final или effectively final
    4) anonymous
        не имеют имени
        могут юзаться там же, где inner class-ы
        объявление класса и создание его объекта происходит одновременно
        в анон классах НЕвозможно написать конструктор
        анон класс м юзать даже private элементы внешнего класса
        lambda expression - краткая форма для анон классов
        Анон интерфейсы НЕ существуют!
