public class Runner {
    private RunnerID id;
    private Tree23<Run> runs;
    private RunnerMinRun minRun;
    private RunnerAvgRun avgRun;
    private float avg;

    public Runner(RunnerID id){
        super();
        this.id = id;
        Run maxKey = new Run(Float.MAX_VALUE, null);
        Run minKey = new Run(Float.MIN_VALUE, null);
        runs = new Tree23<Run>(maxKey, minKey);
        minRun = new RunnerMinRun(Float.MAX_VALUE, id);
        avgRun = new RunnerAvgRun(Float.MAX_VALUE, id);
        //avg = 0;
    }

    //public float getAvg() {
      //  return avg;
    //}


    public RunnerID getId() {
        return id;
    }

    public RunnerAvgRun getAvgRun() {
        return avgRun;
    }

    public Tree23<Run> getRuns(){
        return runs;
    }

    public void addRun(float time){ // h done?
        Run newRun = new Run(time, id);
        Node<Run> newRunNode = new Node<Run>(newRun ,this, null);
        float oldSum = avgRun.getAvgRun() * runs.getTreeSize();
        this.runs.insert(newRunNode);
        if (time < this.minRun.getMinRun()){
            this.minRun.setMinRun(time);
        }
        float newAvg = (oldSum + time) / runs.getTreeSize();
        this.avgRun.setAvgRun(newAvg);
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

    public RunnerMinRun getMinRun() {
        return minRun;
    }

}