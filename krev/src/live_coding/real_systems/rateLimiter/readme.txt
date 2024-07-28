info:
https://www.youtube.com/watch?v=nxraKcWpBvs&list=PLJN9ydlFnJsiEgyjO3D3yBhtiENymhF8G

ПРОБЛЕМА №1:
непонятно, почему иногда не обрабатываются некоторые реквесты.
то ли их нет в очереди, то ли их не видно процессору
пример:
Map is empty
pool-1-thread-1: [time = 2024-07-28 20:42:11.580]: [user = 0]: able to add request = 11 to the application
pool-1-thread-2: [time = 2024-07-28 20:42:11.586]: [user = 1]: able to add request = 21 to the application
pool-1-thread-2: [time = 2024-07-28 20:42:11.864]: [user = 1]: able to add request = 22 to the application
pool-1-thread-2: [time = 2024-07-28 20:42:11.961]: [user = 1]: able to add request = 23 to the application
pool-1-thread-1: [time = 2024-07-28 20:42:12.078]: [user = 0]: able to add request = 12 to the application
Request 11 is handled for user = 0: Content: r11
pool-1-thread-2: [time = 2024-07-28 20:42:12.203]: [user = 1]: able to add request = 24 to the application
pool-1-thread-2: [time = 2024-07-28 20:42:12.393]: [user = 1]: Unable to add request = 25
pool-1-thread-2: [time = 2024-07-28 20:42:12.593]: [user = 1]: Unable to add request = 26
pool-1-thread-2: [time = 2024-07-28 20:42:12.726]: [user = 1]: Unable to add request = 27
Request 21 is handled for user = 1: Content: r21
pool-1-thread-1: [time = 2024-07-28 20:42:13.040]: [user = 0]: able to add request = 13 to the application
Request 22 is handled for user = 1: Content: r22
pool-1-thread-1: [time = 2024-07-28 20:42:13.454]: [user = 0]: able to add request = 14 to the application
pool-1-thread-1: [time = 2024-07-28 20:42:13.499]: [user = 0]: able to add request = 15 to the application
Request 23 is handled for user = 1: Content: r23
Request 24 is handled for user = 1: Content: r24
Request 12 is handled for user = 0: Content: r12

где r13, r14, r15 ???

ОТВЕТ: в классе RequestProcessor надо передавать размер usersWithTasks.size()!
int r = new Random().nextInt(usersWithTasks.size());

попытался выводить состояние очереди из треда с процессором - появилась проблема №2

=========
ПРОБЛЕМА №2: застряло вот так:
pool-1-thread-1: [time = 2024-07-28 20:01:43.096]: [user = 1]: able to add request = 21 to the application
pool-1-thread-1: [time = 2024-07-28 20:01:43.242]: [user = 1]: able to add request = 22 to the application
pool-1-thread-1: [time = 2024-07-28 20:01:43.342]: [user = 1]: able to add request = 23 to the application
pool-1-thread-1: [time = 2024-07-28 20:01:43.360]: [user = 1]: able to add request = 24 to the application
pool-1-thread-1: [time = 2024-07-28 20:01:43.377]: [user = 1]: Unable to add request = 25
pool-1-thread-1: [time = 2024-07-28 20:01:43.549]: [user = 1]: Unable to add request = 26
pool-1-thread-1: [time = 2024-07-28 20:01:43.575]: [user = 1]: Unable to add request = 27

ВОПРОС: почему вызов любого метода LeakyBucket из треда с процессором вешает систему? см Applications
    System.out.println("size = " + userBucketRepo.getMap().get(1).<любой метод>
