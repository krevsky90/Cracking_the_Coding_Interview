package java_learning.patterns.behavioral.iterator.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Developer implements MyIterable {
    private final String name;
    private List<String> skills;

    public Developer(String name) {
        this.name = name;
        this.skills = new ArrayList<>();
    }

    public void addSkills(String... skills) {
        this.skills.addAll(Arrays.asList(skills));
    }

    @Override
    public MyIterator getIterator() {
        return new SkillIterator();
    }

    private class SkillIterator implements MyIterator<String> {
        private int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < skills.size();
        }

        @Override
        public String next() {
            if (hasNext()) {
                String res = skills.get(pos);
                pos++;
                return res;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
