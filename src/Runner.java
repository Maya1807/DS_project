public class Runner {
    private RunnerID id;
    Tree23<Float> runs;
    RunnerMinRun minRun;
    RunnerAvgRun avgRun;



    float avg;

    public RunnerIDInt(int id){
        super();
        this.id = id;
        runs = new Tree23<Float>();
        minRun = null;
        avg = 0;
    }

    public float getAvg() {
        return avg;
    }


    public void removeRun(float time){
        avg = (1 / (runs.size - 1)) * (avg * runs.size - time);
        Node<Float> node = runs.search(runs.root, time);
        runs.delete(node);
        //finding the new min if current min deleted
        if(minRun.key == time){
            minRun = runs.minimum();
        }
    }

    public float getMinRun(){
        return minRun.key;
    }


}
