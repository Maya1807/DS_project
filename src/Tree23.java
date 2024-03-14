public class Tree23<T extends RunnerID> {
    Node<T> root;
    int size;

    public Tree23(T maxKey, T minKey){//m done
        this.init(maxKey, minKey);
        this.size = 0;
    }

    public void init(T maxKey, T minKey){
        Node<T> x = new Node<>();
        Node<T> left = new Node<>();
        Node<T> middle = new Node<>();
        left.setKey(minKey);
        middle.setKey(maxKey);
        left.setParent(x);
        middle.setParent(x);
        x.setKey(maxKey);
        x.setLeft(left);
        x.setMiddle(middle);
        this.root = x;
    }



    public Node<T> search(Node<T> x, T key){//m done
        if (x.getLeft() == null && x.getMiddle() == null){
            if(x.getKey() == key){
                return x;
            }
            else{
                return null;
            }
        }
            if (key.isSmaller(x.getLeft().getKey())){
            return search(x.getLeft(), key);
        } else if (key.isSmaller(x.getMiddle().getKey())) {
            return search(x.getMiddle(), key);
        }
        else{
            return search(x.getRight(), key);
        }
    }

    public Node<T> minimum(T maxKey){//m done
        Node x = this.root;
        while (!(x.getLeft() == null && x.getMiddle() == null)){
            x = x.getLeft();
        }
        x = x.getParent().getMiddle();
        if(x.getKey() != maxKey){
            return x;
        }
        else{
            return null;
            //error: T is empty
            //throw error?
        }
    }

    public void updateKey(Node<T> x){//h done
        Node<T> l = x.getLeft(), m = x.getMiddle(), r = x.getRight();
        x.setKey(l.getKey());
        if (m != null){
            x.setKey(m.getKey());
        }
        if (r != null){
            x.setKey(r.getKey());
        }
    }

    public void setChildren(Node<T> x, Node<T> l,Node<T> m,Node<T> r){//h done
        //implement from 47
        //O(lg(n))
        x.setLeft(l);
        x.setMiddle(m);
        x.setRight(r);
        l.setParent(x);
        if(m != null){
            m.setParent(x);
        }
        if(r != null){
            r.setParent(x);
        }
        updateKey(x);
    }
    public Node<T> insertAndSplit(Node<T> x, Node<T> z){//h done
        //implement from 48
        //O(lg(n))
        Node<T> l = x.getLeft(), m = x.getMiddle(), r = x.getRight(), y = new Node<T>();
        if (r == null){
            if (z.isSmaller(l)) { setChildren(x,z,l,m); }
            else if (z.isSmaller(m)) { setChildren(x,l,z,m); }
            else { setChildren(x,l,m,z); }
            return null;
        }
        if (z.isSmaller(l)){
            setChildren(x,z,l,null);
            setChildren(y,m,r,null);
        }
        else if (z.isSmaller(m)) {
            setChildren(x,l,z,null);
            setChildren(y,m,r,null);
        }
        else if (z.isSmaller(r)) {
            setChildren(x,l,m,null);
            setChildren(y,z,r,null);
        }
        else {
            setChildren(x,l,m,null);
            setChildren(y,r,z,null);
        }
        return y;
    }

    public void insert(Node<T> z){//h done
        //implement from 49
        //O(lg(n))
        Node<T> y = this.root, l = y.getLeft(), m = y.getMiddle(), r = y.getRight(), x;
        while (!(l == null && m == null && r == null)){
            if (z.isSmaller(l)) { y = l; }
            else if (z.isSmaller(m)) { y = m; }
            else { y = r; }
            l = y.getLeft(); m = y.getMiddle(); r = y.getRight();
        }
        x = y.getParent();
        z = insertAndSplit(x,z);
        while (x != this.root) {
            x = x.getParent();
            if (z != null) { z = insertAndSplit(x,z); }
            else { updateKey(x); }
        }
        if (z != null) {
            Node<T> w = new Node<>();
            setChildren(w,x,z,null);
            this.root = w;
        }

        this.size++;
    }

    public Node<T> borrowOrMerge(Node<T> y){//h done
        //implement from 48
        //O(lg(n))
        Node<T> z = y.getParent(), x;
        if (y == z.getLeft()){
            x = z.getMiddle();
            if (x.getRight() != null){
                setChildren(y, y.getLeft(), x.getLeft(), null);
                setChildren(x, x.getMiddle(), x.getRight(), null);
            }
            else {
                setChildren(x, y.getLeft(), x.getLeft(), x.getMiddle());
                delete(y);
                setChildren(z, x, z.getRight(), null);
            }
            return z;
        }
        if (y == z.getMiddle()){
            x = z.getLeft();
            if (x.getRight() != null){
                setChildren(y, x.getRight(), y.getLeft(), null);
                setChildren(x, x.getLeft(), x.getMiddle(), null);
            }
            else {
                setChildren(x, x.getLeft(), x.getMiddle(), y.getLeft());
                delete(y);
                setChildren(z, x, z.getRight(), null);
            }
            return z;
        }
        x = z.getMiddle();
        if (x.getRight() != null){
            setChildren(y, x.getRight(), y.getLeft(), null);
            setChildren(x, x.getLeft(), x.getMiddle(), null);
        }
        else{
            setChildren(x, x.getLeft(), x.getMiddle(), y.getLeft());
            delete(y);
            setChildren(z, z.getLeft(), x, null);
        }
        return z;
    }

    public void delete(Node<T> x){//h done
        //implement from 51
        //O(lg(n))
        Node<T> y = x.getParent();
        if (x == y.getLeft()){
            setChildren(y, y.getMiddle(), y.getRight(), null);
        }
        else if (x == y.getMiddle()) {
            setChildren(y, y.getLeft(), y.getRight(), null);
        }
        else {
            setChildren(y, y.getLeft(), y.getMiddle(), null);
        }
        delete(x);
        while (y != null){
            if (y.getMiddle() == null){
                if (y != this.root){
                    y = borrowOrMerge(y);
                }
                else {
                    this.root = y.getLeft();
                    y.getLeft().setParent(null);
                    delete(y);
                    return;
                }
            }
            else {
                updateKey(y);
                y = y.getParent();
            }
        }

        size--;
    }

    public int rank(Node<T> x){//m done
        int rank = 1;
        Node y = x.getParent();
        while(y != null){
            if(x == y.getMiddle()){
                rank = rank + y.getLeft().getSize();
            } else if (x == y.getRight()) {
                rank = rank + y.getLeft().getSize() + y.getMiddle().getSize();
            }
            x = y;
            y = y.getParent();
        }
        return rank;
    }



}