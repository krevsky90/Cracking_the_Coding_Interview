info:
https://refactoring.guru/ru/design-patterns/decorator

example:
https://www.youtube.com/watch?v=2aB2B3b3bQA&list=PLlsMRoVt5sTPgGbinwOVnaF1mxNeLAD7P&index=13

1) create Interface
2) implement interface in base service
3) create BaseDecorator for base service that just calls service's method, BaseDecorator implements interface from p.1
4) add the other decorators that
    extends BaseDecorator
    implements interface
    add some functionality to the func of wrapped object