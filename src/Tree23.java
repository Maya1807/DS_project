public class Tree23<T extends RunnerID> {
    Node<T> root;
    int size;

    public void init(Tree23<T> Tree){
        //implement from 45
        //O(1)
    }

    public Node<T> search(Node<T> x, T key){
        //implement from 45
        //O(lg(n))
        return null;
    }

    public Node<T> minimum(){
        //implement from 46
        //O(lg(n))
        return null;
    }

    public void setChildren(Node<T> x, Node<T> l,Node<T> m,Node<T> r){
        //implement from 47
        //O(lg(n))
        return;
    }
    public void insertAndSplit(Node<T> x, Node<T> z){
        //implement from 48
        //O(lg(n))
        return;
    }

    public void insert(Node<T> z){
        //implement from 49
        //O(lg(n))
        size++;
        return;
    }

    public void borrowOrMerge(Node<T> y){
        //implement from 48
        //O(lg(n))
        return;
    }

    public void delete(Node<T> x){
        //implement from 51
        //O(lg(n))
        size--;
        return;
    }



}
