class RunnerIDInt extends RunnerID{
    private int id;
    public RunnerIDInt(int id){
        super();
        this.id = id;
    }
    @Override
    public boolean isSmaller(RunnerID other) {
        return this.id < ((RunnerIDInt)other).id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
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
        race.addRunToRunner(id1, (float)20);
        System.out.println("The runner with the smallest minimum time is "+ race.getFastestRunnerMin());
        race.addRunToRunner(id2, (float)10);
        System.out.println("The runner with the smallest minimum time is "+ race.getFastestRunnerMin());
        race.addRunToRunner(id1, (float)5);
        System.out.println("The runner with the smallest minimum time is "+ race.getFastestRunnerMin());
        race.removeRunFromRunner(id1,(float)20);
        System.out.println("The runner with the smallest minimum time is "+ race.getFastestRunnerMin());
        race.removeRunFromRunner(id1,(float)5);
        System.out.println("The runner with the smallest minimum time is "+ race.getFastestRunnerMin());
        race.addRunToRunner(id1, (float)50);
        race.addRunToRunner(id1, (float)40);
        race.addRunToRunner(id1, (float)30);
        race.addRunToRunner(id1, (float)5);
        System.out.println();
        System.out.println("The min running time of" + id2.toString() + "is " + race.getMinRun(id2));
        System.out.println("The avg running time of" + id1.toString() + "is " + race.getAvgRun(id1));
        System.out.println("The avg running time of" + id2.toString() + "is " + race.getAvgRun(id2));
        System.out.println("The runner with the smallest minimum time is "+ race.getFastestRunnerMin());
        System.out.println("The runner with the smallest average time is "+ race.getFastestRunnerAvg());
    }
}