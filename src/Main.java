class RunnerIDInt extends RunnerID{
    private int id;
    Tree23<Float> runs;
    Node<Float> minRun;
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

    @Override
    public boolean isSmaller(RunnerID other) {
        return this.id < ((RunnerIDInt)other).id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
    public void addRun(float time){
        avg = (1 / (runs.size + 1)) * (avg * runs.size + time);
        Node<Float> node = new Node<Float>(time);
        runs.insert(node);
        if(time < minRun.key){
            minRun = node;
        }
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


public class Main {
    public static void main(String[] args) {
        // The ids which we will check will not necessarily be RunnerIDInt
        // This is just for the example
        RunnerIDInt id1 = new RunnerIDInt(3);
        RunnerIDInt id2 = new RunnerIDInt(5);
        Race race = new Race();
        race.addRunner(id1);
        race.addRunner(id2);
        race.addRunToRunner(id1, (float)118.0);
        System.out.println("The min running time of" + id2.toString() + "is " + race.getMinRun(id2));
        System.out.println("The avg running time of" + id1.toString() + "is " + race.getAvgRun(id1));
        System.out.println("The runner with the smallest minimum time is "+ race.getFastestRunnerMin());
        }
    }
