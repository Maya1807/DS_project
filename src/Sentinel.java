public class Sentinel extends RunnerID{
    private static Sentinel instance1 = null; //min
    private static Sentinel instance2 = null; //max
    private Sentinel(){}
    public static Sentinel getInstance1() {
        if (instance1 == null) {
            instance1 = new Sentinel();
        }
        return instance1;
    }

    public static Sentinel getInstance2() {
        if (instance2 == null) {
            instance2 = new Sentinel();
        }
        return instance2;
    }

    @Override
    public boolean isSmaller(RunnerID other) {
        if(this == instance1)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
