1) Stream методы делятся на intermediate и terminal
если просто написать код с intermediate методом, то метод не отработает до тех пор, пока в конце выражения не вызван какой-то terminal метод

2) стримы нельзя переиспользовать!
    stream.count();
    stream.distinct().count() - нельзя, т.к. до того был stream.count();

3) Map никак нельзя загнать в стрим. Но можно работать с ее EntrySet-ом. Например, для печати:
    Map<String, List<Employee>> companyToEmployees = ...
    companyToEmployees.entrySet().stream().forEach(e -> System.out.println(
                    e.getKey() + " -> " + e.getValue().toString())
            );

4) Stream that is created from list is ordered stream (since list is ordered collection)

5) Diversity and speed:
    Sequential + Ordered = 10
    Sequential + Unordered = 10
    Parallel + Ordered  = 20
    Parallel + Unordered = 26
i.e. 26 - 20 = 6 is the price of order

to set stream unordered explicitly (явно), we write stream.unordered()....

theory:
videos from https://www.youtube.com/watch?v=Uh88SGdlrxk&list=PLqj7-hRTFl_oDMBjI_EstsFcDAwt-Arhs
https://www.youtube.com/watch?v=O8oN4KSZEXE
https://www.youtube.com/watch?v=i0Jr2l3jrDA

practice:
https://habr.com/ru/articles/684912/
https://skillbox.ru/media/base/java-stream-api-kopilka-retseptov/

