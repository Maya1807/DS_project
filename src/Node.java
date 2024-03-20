public class Node<T extends RunnerID> {
    private T key;
    private Runner data;
    private Node<T> parent;
    private Node<T> left = null;
    private Node<T> middle = null;
    private Node<T> right = null;
    private int size;

    public Node(){
        this.key = null;
        this.data = null;
        this.parent = null;
    }
    public Node(T key, Runner data){
        this.key = key;
        this.data = data;
    }

    public Node(T key, Runner data, Node<T> parent){
        this.key = key;
        this.data = data;
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getMiddle() {
        return middle;
    }

    public void setMiddle(Node<T> middle) {
        this.middle = middle;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public T getKey() { return this.key; }

    public void setKey(T key) {
        this.key = key;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public Runner getData(){
        return data;
    }

    public boolean isSmaller(Node<T> other){
        return this.key.isSmaller(other.getKey());
    }

}