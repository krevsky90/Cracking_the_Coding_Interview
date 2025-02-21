info:
https://www.youtube.com/watch?v=QiO_-GXLP-8&list=PLqj7-hRTFl_oDMBjI_EstsFcDAwt-Arhs&index=38
lessons 37-39

CopyOnWriteArrayListExample - use when read >> write
    each time  when collection is changed, the copy of this collection is created

ConcurrentHashMap
    read operation does NOT block map
    write operation blocks only corresponding bucket (i.e. segment). ConcurrentHashMap has 16 buckets (initially?)