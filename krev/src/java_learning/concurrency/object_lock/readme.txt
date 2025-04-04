Класс ObjSync:
    Если метод НЕ статик:
        1) если пишем
            public void method() {
                synchronized (this) {
                    ...
                }
            }
        или (то же самое)
            public synchronized void method() {
                ...
            }

            a) и используем РАЗНЫЕ инстансы класса ObjSync в разных тредах, то все идет в параллель (у тредом нет общих объектов) => тотал время = 5с
                и можно даже ниче не синхронизировать!
            b) НО если используем один и тот же инстанс класс ObjSync в разных тредах, то треды будут конкурировать => тотал время = 10с

        2) если пишем
            public void method() {
                synchronized (ObjSync.class) {
                    ...
                }
            }
            то у всех объектов данного класса будет один и тот же монитор! =>
            даже если используем РАЗНЫЕ инстансы класса ObjSync в разных тредах, то все треды будут конкурировать за один и тот же монитор => тотал время = 10с

        3) если пишем статический монитор
            private static Object mutexStatic = new Object();

            public void method() {
                synchronized (mutexStatic) {
                    ...
                }
            }
            то у всех объектов данного класса будет один и тот же монитор! =>
            даже если используем РАЗНЫЕ инстансы класса ObjSync в разных тредах, то все треды будут конкурировать за один и тот же монитор => тотал время = 10с

    Если метод статик:
        1) если пишем
            public static void methodStatic() {
                synchronized (ObjSync.class) {
                    ...
                }
            }
            или (то же самое)
            public synchronized static void methodStatic() {
                ...
            }
            то синхронизация осуществляется по классу => для всех тредов будет один и тот же монитор => тотал время = 10с

Если мы хотим синхронизировать более хитрые области кода (залочить в одном методе, разлочить в другом), то вместо synchronized юзаем ReentrantLock
Также ReentrantLock имеет tryLock(long timeout, TimeUnit unit)
   https://sky.pro/wiki/java/sravnenie-lock-i-synchronized-v-java-preimuschestva-praktika/

   lock.lock();
   try {
       // здесь находится критический участок кода, где важно не потерять ключ
   } finally {
       lock.unlock(); // это обязательно для избежания блокировок
   }
