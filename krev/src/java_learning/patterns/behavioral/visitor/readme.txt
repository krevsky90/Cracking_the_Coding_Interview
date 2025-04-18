info:
https://refactoring.guru/ru/design-patterns/visitor/

idea:
разместить новое поведение в отдельном классе, вместо того чтобы множить его сразу в нескольких классах.
Объекты, с которыми должно было быть связано поведение, не будут выполнять его самостоятельно.
Вместо этого вы будете передавать эти объекты в методы посетителя.

Юзает механизм двойной диспетчеризации - вместо того, чтобы самим искать нужный метод,
    мы можем поручить это объектам, которые передаём в параметрах посетителю. А они уже вызовут правильный метод посетителя.

Применимость:
1)   Когда вам нужно выполнить какую-то операцию над всеми элементами сложной структуры объектов, например, деревом.

2) Когда над объектами сложной структуры объектов надо выполнять некоторые не связанные между собой операции,
    но вы не хотите «засорять» классы такими операциями.

3) Когда новое поведение имеет смысл только для некоторых классов из существующей иерархии:
     Посетитель позволяет определить поведение только для этих классов, оставив его пустым для всех остальных.

4)  Упрощает добавление операций, работающих со сложными структурами объектов.

НЕ оправдан,
    если иерархия элементов часто меняется.