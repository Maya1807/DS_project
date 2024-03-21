public class Race {
    //private Tree23<RunnerIDInt> runnersByID;
    private Tree23<RunnerID> runnersByID;
    //NodeMin<RunnerID> minByAvg;
    //NodeMin<RunnerID> minByMin;
    private Tree23<RunnerMinRun> runnersByMin;
    private Tree23<RunnerAvgRun> runnersByAvg;
    private RunnerMinRun minRunByMin;
    private RunnerAvgRun minRunByAvg;
    private RunnerIDInt maxKey_RunnerIDInt = new RunnerIDInt(Integer.MAX_VALUE); ///can we use runner id int???????
    private RunnerIDInt minKey_RunnerIDInt = new RunnerIDInt(Integer.MIN_VALUE);
    private RunnerMinRun maxKey_RunnerMinRun = new RunnerMinRun(Float.MAX_VALUE, maxKey_RunnerIDInt);
    private RunnerMinRun minKey_RunnerMinRun = new RunnerMinRun(Float.MIN_VALUE, minKey_RunnerIDInt);
    private RunnerAvgRun maxKey_RunnerAvgRun = new RunnerAvgRun(Float.MAX_VALUE, maxKey_RunnerIDInt);
    private RunnerAvgRun minKey_RunnerAvgRun = new RunnerAvgRun(Float.MIN_VALUE, minKey_RunnerIDInt);


    public Race()
    {
        runnersByID = new Tree23<RunnerID>(maxKey_RunnerIDInt, minKey_RunnerIDInt);
        runnersByMin = new Tree23<RunnerMinRun>(maxKey_RunnerMinRun, minKey_RunnerMinRun);
        runnersByAvg = new Tree23<RunnerAvgRun>(maxKey_RunnerAvgRun, minKey_RunnerAvgRun);
        minRunByMin = new RunnerMinRun(Float.MIN_VALUE, null);
        minRunByAvg = new RunnerAvgRun(Float.MIN_VALUE, null);
    }
    public void addRunner(RunnerID id) // h done?
    {
        if (runnersByID.search(runnersByID.getRoot(), id) == null){
            Runner newRunner = new Runner(id);
            runnersByID.insert(new Node<RunnerID>(id, newRunner));
            runnersByAvg.insert(new Node<RunnerAvgRun>(newRunner.getAvgRun(), newRunner));
            runnersByMin.insert(new Node<RunnerMinRun>(newRunner.getMinRun(), newRunner));
        }
        else{
            throw new IllegalArgumentException("Runner already exists, can't add!");
        }

    }

    public void removeRunner(RunnerID id) { //h done?
        Node runnerID = runnersByID.search(runnersByID.getRoot(), id);
        if (runnerID == null){
            throw new IllegalArgumentException("Runner doesn't exist, can't remove!");
        }
        else {
            Runner runner = runnerID.getData();
            RunnerAvgRun runnerAvgRun = runner.getAvgRun();
            RunnerMinRun runnerMinRun = runner.getMinRun();

            runnersByID.delete(runnerID);
            runnersByAvg.delete(runnersByAvg.search(runnersByAvg.getRoot(), runnerAvgRun));
            runnersByMin.delete(runnersByMin.search(runnersByMin.getRoot(), runnerMinRun));

            if (this.minRunByAvg == runnerAvgRun){
                this.minRunByAvg = runnersByAvg.minimum(maxKey_RunnerAvgRun).getKey();
            }
            if (this.minRunByMin == runnerMinRun){
                this.minRunByMin = runnersByMin.minimum(maxKey_RunnerMinRun).getKey();
            }
        }
    }


    public void addRunToRunner(RunnerID id, float time) { //h done?
        Runner runner = runnersByID.search(runnersByID.getRoot(), id).getData();
        if (runner == null) {
            throw new IllegalArgumentException("runner doesnt exist, cant add run!");
        }
        else {
            Node<RunnerMinRun> runnerMinNode = runnersByMin.search(runnersByMin.getRoot(), runner.getMinRun());
            runnersByMin.delete(runnerMinNode);

            Node<RunnerAvgRun> runnerAvgNode = runnersByAvg.search(runnersByAvg.getRoot(), runner.getAvgRun());
            runnersByAvg.delete(runnerAvgNode);

            runner.addRun(time);

            //handle run sorting by avg and by min in 2 trees
            runnersByMin.insert(runnerMinNode);
            this.minRunByMin = runnersByMin.minimum(maxKey_RunnerMinRun).getKey(); //update min runner by min run

            //handle new sorting of average tree in any case because average was updated anyway
            runnersByAvg.insert(runnerAvgNode);
            this.minRunByAvg = runnersByAvg.minimum(maxKey_RunnerAvgRun).getKey(); // update min runner by avg run
        }
    }


    public void removeRunFromRunner(RunnerID id, float time) //check
    {
        Runner runner = runnersByID.search(runnersByID.getRoot(), id).getData();
        if (runner == null){
            throw new IllegalArgumentException("runner doesnt exist, cant remove run!");
        }
        else {
            float oldMinRunTime = runner.getMinRun().getMinRun();

            Node<RunnerMinRun> runnerMinNode = runnersByMin.search(runnersByMin.getRoot(), runner.getMinRun());
            runnersByMin.delete(runnerMinNode);

            Node<RunnerAvgRun> runnerAvgNode = runnersByAvg.search(runnersByAvg.getRoot(), runner.getAvgRun());
            runnersByAvg.delete(runnerAvgNode);

            runner.removeRun(time);

            //handle run sorting by avg and by min in 2 trees
            runnersByMin.insert(runnerMinNode);
            this.minRunByMin = runnersByMin.minimum(maxKey_RunnerMinRun).getKey(); //update min runner by min run

            //handle new sorting of average tree in any case because average was updated anyway
            runnersByAvg.insert(runnerAvgNode);
            this.minRunByAvg = runnersByAvg.minimum(maxKey_RunnerAvgRun).getKey(); // update min runner by avg run
        }
    }

    public RunnerID getFastestRunnerAvg() //done
    {
        return this.minRunByAvg.getId();
    }

    public RunnerID getFastestRunnerMin() //done
    {
        return this.minRunByMin.getId();
    }

    public float getMinRun(RunnerID id) //h done
    {
        return runnersByID.search(runnersByID.getRoot(), id).getData().getMinRun().getMinRun();
    }
    public float getAvgRun(RunnerID id) //h done
    {
        return runnersByID.search(runnersByID.getRoot(), id).getData().getAvgRun().getAvgRun();
    }

    public int getRankAvg(RunnerID id) //h done? something more to update in here?
    {
        RunnerAvgRun avgRun = runnersByID.search(runnersByID.getRoot(), id).getData().getAvgRun();
        Node avgRunNode = runnersByAvg.search(runnersByAvg.getRoot(), avgRun);
        return runnersByAvg.rank(avgRunNode);
    }

    public int getRankMin(RunnerID id) //h done? something more to update in here?
    {
        RunnerMinRun minRun = runnersByID.search(runnersByID.getRoot(), id).getData().getMinRun();
        Node minRunNode = runnersByMin.search(runnersByMin.getRoot(), minRun);
        return runnersByMin.rank(minRunNode);
    }
}