public class Node<T extends RunnerID> {
    private T key;
    private Runner data;
    private Node parent;
    private Node left;
    private Node middle;
    private Node right;
    private int size;


    public Node(T key){
        this.key = key;
    }

    public T getKey() { return this.key; }

    public boolean isSmaller(Node<T> other){
        return this.key.isSmaller(other.getKey());
    }

}
