public class RunNode extends RunnerID{
    private float key; //runTime
    private Runner data;
    private RunNode parent;
    private RunNode left = null;
    private RunNode middle = null;
    private RunNode right = null;


    public RunNode(){
        this.key = 0;
        this.data = null;
        this.parent = null;
    }

    public RunNode(float key, Runner data, RunNode parent){
        this.key = key;
        this.data = data;
        this.parent = parent;
    }

    public RunNode getParent() {
        return parent;
    }

    public void setParent(RunNode parent) {
        this.parent = parent;
    }

    public RunNode getLeft() {
        return left;
    }

    public void setLeft(RunNode left) {
        this.left = left;
    }

    public RunNode getMiddle() {
        return middle;
    }

    public void setMiddle(RunNode middle) {
        this.middle = middle;
    }

    public RunNode getRight() {
        return right;
    }

    public void setRight(RunNode right) {
        this.right = right;
    }

    public float getKey() { return this.key; }

    public void setKey(float key) {
        this.key = key;
    }
    @Override
    public boolean isSmaller(RunnerID other) {
        return this.key < ((RunNode)other).getKey();
    }

    @Override
    public String toString() {
        return null;
    }
}
