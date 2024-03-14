public class Tree23ByAvg<T> {  //////// dont use - delete after Tree23 works for everything
    NodeMin<T> root;
    int size;

    public void init(Tree23<T> Tree){ //m
        //implement from 45
        //O(1)
    }

    public NodeMin<T> search(NodeMin<T> x, T key){ //m
        //implement from 45
        //O(lg(n))
        return null;
    }

    public NodeMin<T> minimumAvg(){ //m
        //implement from 46
        //O(lg(n))
        return null;
    }
    public NodeMin<T> minimumMinRun(){ //m without pseudo
        return null;
    }


    public void setChildren(NodeMin<T> x, NodeMin<T> l,NodeMin<T> m,NodeMin<T> r){ //h
        //implement from 47
        //O(lg(n))
        return;
    }
    public void insertAndSplit(NodeMin<T> x, NodeMin<T> z){ //h
        //implement from 48
        //O(lg(n))
        return;
    }

    public void insert(NodeMin<T> z){ //h
        //implement from 49
        //O(lg(n))
        size++;
        return;
    }

    public void borrowOrMerge(NodeMin<T> y){ //h
        //implement from 48
        //O(lg(n))
        return;
    }

    public void delete(NodeMin<T> x){ //h
        //implement from 51
        //O(lg(n))
        size--;
        return;
    }

    public int rank(NodeMin<T> x){ //m
        int rank = 1;
        //implement from tutorial 6 , 14
        return 0;
    }//



}


