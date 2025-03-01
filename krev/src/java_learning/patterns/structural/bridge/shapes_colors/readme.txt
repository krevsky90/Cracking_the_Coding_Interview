info:
https://refactoring.guru/ru/design-patterns/bridge

idea:
create parallel hierarchies (Colors and Shapes) and use Color inside Shape using decomposition

for the situation when we need to have 10 colors and 3 shapes:
without this pattern we will create 10*3 = 30 subclasses of Shapes
with this pattern we will create 10 + 3 = 13 subclasses (10 of Color and 3 of Shape)