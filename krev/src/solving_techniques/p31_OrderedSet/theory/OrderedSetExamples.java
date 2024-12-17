package solving_techniques.p31_OrderedSet.theory;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class OrderedSetExamples {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(4, "Four");
        treeMap.put(1, "One");
        treeMap.put(7, "Seven");
        treeMap.put(3, "Three");
        treeMap.put(9, "Nine");
        //stores in acs order: 1(One) 3(Three) 4(Four) 7(Seven) 9(Nine)

        //NOTE: each operation takes O(logN)
        System.out.println(treeMap.lowerEntry(7));  //4
        System.out.println(treeMap.lowerKey(7));    //4
        System.out.println(treeMap.floorKey(6));    //4
        System.out.println(treeMap.higherEntry(7)); //9
        System.out.println(treeMap.higherKey(7));   //9
        System.out.println(treeMap.ceilingKey(6));  //7 - return this key or (if not exist) - the closest key that is greater
        System.out.println(treeMap.floorKey(6));  //4 - return this key or (if not exist) - the closest key that is less
        System.out.println(treeMap.firstKey());  //1
        System.out.println(treeMap.lastKey());  //9

        treeMap.descendingKeySet(); // 9 7 4 3 1

        treeMap.headMap(3); //1(One) - part of map: to the beginning to given key
        treeMap.headMap(3, true); //1(One) 3(Three) - part of map: to the beginning to given key inclusively
        treeMap.tailMap(4); //4(Four) 7(Seven) 9(Nine) - part of map: from given key to the end
        treeMap.tailMap(4, false); // 7(Seven) 9(Nine) - part of map: from given key to the end, can exclude given key

        System.out.println("========================");
        TreeSet<int[]> ts = new TreeSet<>((a,b) -> b[0] - a[0]);
        ts.add(new int[]{3, 5});
        ts.add(new int[]{33, 45});
        Iterator<int[]> it = ts.iterator();

        while (it.hasNext()) {
            int[] arr = it.next();
            System.out.println(arr[0] + ", " + arr[1]);
        }


        //NOTE: the same methods as for TreeMap
    }
}