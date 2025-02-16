iteration:
    forEach, iterator
search:
    findFirst, findAny
check:
    allMatch, anyMatch, noneMatch
aggregators:
    reduction - should use associative operation (like + or *) since stream might be unordered by default
        reduce
    collectors - 30+ predefined collectors:
        toList()
        toSet()
        toCollection(Supplier<Collection<T>>) => Collection<T> - for custom collections
        partitioningBy(Predicate<T>) => Map<Boolean, List<T>>
        groupingBy(Function<T,K>) => Map<K, List<T>>
        toMap(Function<T,K>, Function<T,U) => Map<K,U> - we get Key from element of stream (i.e. from T) and we get Value (U) from the same element T