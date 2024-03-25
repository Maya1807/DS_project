public class Runner {
    private RunnerID id;
    private Tree23<Run> runs;
    private RunnerMinRun minRun;
    private RunnerAvgRun avgRun;
    private float avg;
    private Run maxKey = new Run(Float.MAX_VALUE, null);
    private Run minKey = new Run(Float.MIN_VALUE, null);

    public Runner(RunnerID id){
        super();
        this.id = id;
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
        if (runs.searchByTime(runs.getRoot(), time) != null){
            throw new IllegalArgumentException("run already exists, cant add run!");
        }
        else {
            Run newRun = new Run(time, id);
            Node<Run> newRunNode = new Node<Run>(newRun, this, null);
            float oldSum = avgRun.getAvgRun() * runs.getTreeSize();
            runs.insert(newRunNode);
            if (time < this.minRun.getMinRun()) {
                this.minRun.setMinRun(time);
            }
            float newAvg = (oldSum + time) / runs.getTreeSize();
            this.avgRun.setAvgRun(newAvg);
        }
    }

    public void removeRun(float time){
        Node<Run> runNode = runs.searchByTime(runs.getRoot(), time);
        if (runNode == null){
            throw new IllegalArgumentException("run doesnt exist, cant remove run!");
        }
        else {
            Run run = runNode.getKey();
            float oldSum = avgRun.getAvgRun() * runs.getTreeSize();
            this.runs.delete(runNode);
            if (runs.getTreeSize() == 0){
                this.avgRun.setAvgRun(Float.MAX_VALUE);
                this.minRun.setMinRun(Float.MAX_VALUE);
                return;
            }
            if (this.minRun.getMinRun() == time) {
                //finding the new min if current min deleted
                this.minRun.setMinRun(runs.minimum(maxKey).getKey().getRunTime());
            }
            float newAvg = (oldSum - time) / runs.getTreeSize();
            this.avgRun.setAvgRun(newAvg);
        }
    }

    public RunnerMinRun getMinRun() {
        return minRun;
    }

}