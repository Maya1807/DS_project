public class Race {
    //private Tree23<RunnerIDInt> runnersByID;
    private Tree23<RunnerID> runnersByID;
    //NodeMin<RunnerID> minByAvg;
    //NodeMin<RunnerID> minByMin;
    private Tree23<RunnerMinRun> runnersByMin;
    private Tree23<RunnerAvgRun> runnersByAvg;
    private RunnerMinRun minRunByMin;
    private RunnerAvgRun minRunByAvg;


    public Race()
    {
        RunnerIDInt maxKey_RunnerIDInt = new RunnerIDInt(Integer.MAX_VALUE);
        RunnerIDInt minKey_RunnerIDInt = new RunnerIDInt(Integer.MIN_VALUE);
        RunnerMinRun maxKey_RunnerMinRun = new RunnerMinRun(Float.MAX_VALUE, null);
        RunnerMinRun minKey_RunnerMinRun = new RunnerMinRun(Float.MIN_VALUE, null);
        RunnerAvgRun maxKey_RunnerAvgRun = new RunnerAvgRun(Float.MAX_VALUE, null);
        RunnerAvgRun minKey_RunnerAvgRun = new RunnerAvgRun(Float.MIN_VALUE, null);

        runnersByID = new Tree23<RunnerID>(maxKey_RunnerIDInt, minKey_RunnerIDInt);
        runnersByMin = new Tree23<RunnerMinRun>(maxKey_RunnerMinRun, minKey_RunnerMinRun);
        runnersByAvg = new Tree23<RunnerAvgRun>(maxKey_RunnerAvgRun, minKey_RunnerAvgRun);
        minRunByMin = new RunnerMinRun(Float.MIN_VALUE, null);
        minRunByAvg = new RunnerAvgRun(Float.MIN_VALUE, null);
    }
    public void addRunner(RunnerID id)
    {
        Runner newRunner = new Runner(id);
        runnersByID.insert(new Node<RunnerID>(id, newRunner));
        runnersByAvg.insert(new Node<RunnerAvgRun>(newRunner.getAvgRun(), newRunner));
        runnersByMin.insert(new Node<RunnerMinRun>(newRunner.getMinRun(), newRunner));

    }

    public void removeRunner(RunnerID id)
    {

    }


    public void addRunToRunner(RunnerID id, float time)
    {
//        NodeMin<RunnerID> node = runners.search(runners.root, id);
//        ((RunnerIDInt) node.key).addRun(time);
        addRunToRunnerTreeSpecific(runnersByID, id, time);
        addRunToRunnerTreeSpecific(runnersByAvg, id, time);
        addRunToRunnerTreeSpecific(runnersByMin, id, time);

    }
    private void addRunToRunnerTreeSpecific(Tree23 tree, RunnerID id, float time){
        Node runnerNode = tree.search(tree.root, id);
        Node newRun = new Node(runnerNode.getKey() ,runnerNode.getData(), null);
        runnerNode.getData().getRuns().insert(newRun);
    }

    public void removeRunFromRunner(RunnerID id, float time)
    {
        NodeMin<RunnerID> node = runners.search(runners.root, id);
        ((RunnerIDInt) node.key).removeRun(time);
    }

    public RunnerID getFastestRunnerAvg()
    {
        return runners.minimumAvg().key;
    }

    public RunnerID getFastestRunnerMin()
    {
        return runners.minimumMinRun().key;
    }

    public float getMinRun(RunnerID id)
    {
        return ((RunnerIDInt)id).getMinRun();
    }
    public float getAvgRun(RunnerID id){

        return ((RunnerIDInt)id).getAvg();
    }

    public int getRankAvg(RunnerID id)
    {
        throw new java.lang.UnsupportedOperationException("not implemented");
    }

    public int getRankMin(RunnerID id)
    {
        throw new java.lang.UnsupportedOperationException("not implemented");
    }
}