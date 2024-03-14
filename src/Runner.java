public class Runner {
    private RunnerID id;
    Tree23<RunNode> runs;
    RunnerMinRun minRun;
    RunnerAvgRun avgRun;
    float avg;

    public Runner(RunnerID id){
        super();
        this.id = id;
        RunNode maxKey = new RunNode(Float.MAX_VALUE, null, null);
        RunNode minKey = new RunNode(Float.MIN_VALUE, null, null);
        runs = new Tree23<RunNode>(maxKey, minKey);
        minRun = null;
        avg = 0;
    }

    public float getAvg() {
        return avg;
    }
    public Tree23<RunNode> getRuns(){
        return runs;
    }


    public void removeRun(float time){
//        avg = (1 / (runs.size - 1)) * (avg * runs.size - time);
//        Node<Float> node = runs.search(runs.root, time);
//        runs.delete(node);
//        //finding the new min if current min deleted
//        if(minRun.key == time){
//            minRun = runs.minimum();
//        }
    }

    public float getMinRun(){
        return minRun.getMinRun();
    }


}