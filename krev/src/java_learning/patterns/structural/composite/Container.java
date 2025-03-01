package java_learning.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * abstract entity that may contain smth inside
 */
public class Container implements ICalcPrice {
    private List<ICalcPrice> list;

    public Container() {
        this.list = new ArrayList<>();
    }

    public Container(List<ICalcPrice> list) {
        this.list = list;
    }

    public void add(ICalcPrice item) {
        this.list.add(item);
    }

    @Override
    public int calcPrice() {
        int sum = 0;
        for (ICalcPrice item : list) {
            sum += item.calcPrice();
        }

        return sum;
    }
}
