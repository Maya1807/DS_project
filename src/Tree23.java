public class Tree23<T extends RunnerID> {
    private Node<T> root;
    private int treeSize;

    public Tree23(T maxKey, T minKey){
        this.init(maxKey, minKey);
        this.treeSize = 0;
    }

    //constructor for id Trees
    public Tree23(boolean idTree){
        if(idTree) {
            this.initIDTree();
        }
        this.treeSize = 0;
    }

    private void init(T maxKey, T minKey){
        Node<T> x = new Node<>();
        Node<T> left = new Node<>();
        Node<T> middle = new Node<>();
        left.setKey(minKey);
        middle.setKey(maxKey);
        left.setParent(x);
        middle.setParent(x);
        left.setSize(0);
        middle.setSize(0);
        x.setKey(maxKey);
        x.setLeft(left);
        x.setMiddle(middle);
        x.setSize(0);
        this.root = x;
    }

    private void initIDTree(){
        Node<T> x = new Node<>();
        Node<T> left = new Node<>();
        left = left.getMinSentinel();
        Node<T> middle = new Node<>();
        middle = middle.getMaxSentinel();
        //keys are already set
        left.setParent(x);
        middle.setParent(x);
        left.setSize(0);
        middle.setSize(0);
        x.setKey(middle.getKey());
        x.setLeft(left);
        x.setMiddle(middle);
        x.setSize(0);
        this.root = x;
    }

    public Node<T> search(Node<T> x, T key){
        if (x.isLeaf()){
            if(isEqual(x.getKey(), key)){
                return x;
            }
            else{
                return null;
            }
        }
        if(!sensitiveIsSmaller(x.getLeft().getKey(), key)) {
            return search(x.getLeft(), key);
        } else if (!sensitiveIsSmaller(x.getMiddle().getKey(), key)) {
            return search(x.getMiddle(), key);
        }
        else{
            return search(x.getRight(), key);
        }
    }


    public Node<T> searchByTime(Node<T> x, float time){
        if (x.getKey().getClass() != Run.class){
            return null;
        }
        if (x.isLeaf()){
            if(((Run)x.getKey()).getRunTime() == time){
                return x;
            }
            else{
                return null;
            }
        }
        if (time <= ((Run)x.getLeft().getKey()).getRunTime()){
            return searchByTime(x.getLeft(), time);
        } else if (time <= ((Run)x.getMiddle().getKey()).getRunTime()) {
            return searchByTime(x.getMiddle(), time);
        }
        else{
            return searchByTime(x.getRight(), time);
        }
    }


    public Node<T> minimum(T maxKey) {
        Node<T> x = this.root;
        while (!x.isLeaf()){
            x = x.getLeft();
        }
        x = x.getParent().getMiddle();
        if(x.getKey() != maxKey || x.getKey().getClass() == Run.class){
            return x;
        } else{
            return null;
        }
    }

    public void updateKey(Node<T> x){
        Node<T> l = x.getLeft(), m = x.getMiddle(), r = x.getRight();
        x.setKey(l.getKey());
        if (m != null){
            x.setKey(m.getKey());
        }
        if (r != null){
            x.setKey(r.getKey());
        }
    }

    public void setChildren(Node<T> x, Node<T> l,Node<T> m,Node<T> r){
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
    public Node<T> insertAndSplit(Node<T> x, Node<T> z){
        Node<T> l = x.getLeft(), m = x.getMiddle(), r = x.getRight(), y = new Node<>();
        if (r == null){
            if (z.isSmaller(l)) { setChildren(x,z,l,m); }
            else if (z.isSmaller(m)) { setChildren(x,l,z,m); }
            else { setChildren(x,l,m,z); }
            updateSize(x);
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
        updateSize(x);
        updateSize(y);
        return y;
    }

    public void insert(Node<T> z){
        this.treeSize++;
        z.setSize(1);
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
            w.setSize(x.getSize() + z.getSize());
            setChildren(w,x,z,null);
            this.root = w;
        }

    }

    public Node<T> borrowOrMerge(Node<T> y){
        Node<T> z = y.getParent(), x;
        boolean mergedOrBorrowed = false;
        if (y == z.getLeft()){
            x = z.getMiddle();
            if (x.getRight() != null){
                setChildren(y, y.getLeft(), x.getLeft(), null);
                setChildren(x, x.getMiddle(), x.getRight(), null);
                mergedOrBorrowed = true;
            }
            else {
                setChildren(x, y.getLeft(), x.getLeft(), x.getMiddle());
                disconnect(y);
                setChildren(z, x, z.getRight(), null);
                mergedOrBorrowed = true;
            }
        } else if (y == z.getMiddle()){
            x = z.getLeft();
            if (x.getRight() != null){
                setChildren(y, x.getRight(), y.getLeft(), null);
                setChildren(x, x.getLeft(), x.getMiddle(), null);
                mergedOrBorrowed = true;
            }
            else {
                setChildren(x, x.getLeft(), x.getMiddle(), y.getLeft());
                disconnect(y);
                setChildren(z, x, z.getRight(), null);
                mergedOrBorrowed = true;
            }
        }else {
            x = z.getMiddle();
            if (x.getRight() != null) {
                setChildren(y, x.getRight(), y.getLeft(), null);
                setChildren(x, x.getLeft(), x.getMiddle(), null);
                mergedOrBorrowed = true;
            } else {
                setChildren(x, x.getLeft(), x.getMiddle(), y.getLeft());
                disconnect(y);
                setChildren(z, z.getLeft(), x, null);
                mergedOrBorrowed = true;
            }
        }
        return z;
    }

    public void delete(Node<T> x){
        this.treeSize--;
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
        disconnect(x);
        y.setSize(y.getSize() - 1);
        while (y != null) {
            if (y.getMiddle() == null) {
                if (y != this.root) {
                    y = borrowOrMerge(y);
                } else {
                    this.root = y.getLeft();
                    y.getLeft().setParent(null);
                    disconnect(y);
                    return;
                }
            } else {
                decreaseSize(y);
                updateKey(y);
                y = y.getParent();
            }
        }
    }

    public void disconnect(Node<T> x) {
        x.setParent(null);
        x.setRight(null);
        x.setMiddle(null);
        x.setLeft(null);
        x.setSize(0);
    }

    public int rank(Node<T> x){
        int rank = 1;
        Node<T> y = x.getParent();
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

    private void updateSize(Node<T> node) {
        while (node != null) {
            int leftSize = node.getLeft() == null ? 0 : node.getLeft().getSize();
            int middleSize = node.getMiddle() == null ? 0 : node.getMiddle().getSize();
            int rightSize = node.getRight() == null ? 0 : node.getRight().getSize();
            node.setSize(leftSize + middleSize + rightSize);
            node = node.getParent();
        }
    }

    private void decreaseSize(Node<T> node){
        while (node != null) {
            node.setSize(node.getSize() - 1);
            node = node.getParent();
        }
    }

    public Node<T> getRoot() {
        return root;
    }

    public int getTreeSize() {
        return treeSize;
    }

    private boolean isEqual(T key1, T key2){
        return !sensitiveIsSmaller(key1, key2) && !sensitiveIsSmaller(key2, key1);
    }

    //this is Smaller method is sensitive to Sentinels (comparing keys)
    private boolean sensitiveIsSmaller(T key1, T key2){
        if(key2 instanceof Sentinel){
            if(key2 == Sentinel.getInstance1()){
                return false;
            }
            else if (key2 == Sentinel.getInstance2()){
                return true;
            }
        }
        return key1.isSmaller(key2);
    }


}