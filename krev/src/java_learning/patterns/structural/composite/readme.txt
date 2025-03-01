info:
https://refactoring.guru/ru/design-patterns/composite

when structure is tree structure, that may contain:
1) leaf - some object that has business sense
2) container that may hav the other containers and/or leaf

using Composite we can get some aggregation metric for all big tree (big container that has ...)
IF each container and leaf implements the same Interface with this aggregation function

examples:
count total weight/price of container and its parts
print all content of container