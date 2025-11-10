package practice.arrays.prefixarray;

public class Pair implements Comparable<Pair>{
    private int value;
    private int index;

    public Pair(int value, int index) {
        this.value = value;
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(Pair other) {
        return Integer.compare(this.value, other.value);
    }
}
