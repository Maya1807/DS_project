public class Race {
    Tree23ByAvg<RunnerID> runners;
    NodeMin<RunnerID> minByAvg;
    NodeMin<RunnerID> minByMin;
    public void init()
    {
        runners = new Tree23ByAvg<>();
        minByMin = null;
        minByAvg = null;
    }
    public void addRunner(RunnerID id)
    {
        //implement insert (from 49)
        NodeMin<RunnerID> node = new NodeMin<>(id);
        runners.insert(node);
        if( ((RunnerIDInt)id).getMinRun() < ((RunnerIDInt)minByMin.key).getMinRun()){
            minByMin = node;
        }
        if( ((RunnerIDInt)id).getAvg() < ((RunnerIDInt)minByAvg.key).getAvg()){
            minByAvg = node;
        }

    }

    public void removeRunner(RunnerID id)
    {
        NodeMin<RunnerID> node = runners.search(runners.root, id);
        runners.delete(node);

        //finding the new min if current min deleted
        if(minByAvg.key == id){
            minByAvg = runners.minimumAvg();
        }
        if(minByMin.key == id){
            minByMin = runners.minimumMinRun();
        }

    }

    public void addRunToRunner(RunnerID id, float time)
    {
        NodeMin<RunnerID> node = runners.search(runners.root, id);
        ((RunnerIDInt) node.key).addRun(time);
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
