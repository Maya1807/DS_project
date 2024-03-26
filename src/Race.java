
public class Race {
    // Stores runners indexed by their ID.
    private Tree23<RunnerID> runnersByID;

    // Stores runners indexed by their minimum run time.
    private Tree23<RunnerMinRun> runnersByMin;

    // Stores runners indexed by their average run time.
    private Tree23<RunnerAvgRun> runnersByAvg;

    // Tracks the runner with the overall shortest run time.
    private RunnerMinRun minRunByMin;

    // Tracks the runner with the overall shortest average run time.
    private RunnerAvgRun minRunByAvg;

    // Sentinel values
    private final RunnerMinRun maxKey_RunnerMinRun = new RunnerMinRun(Float.MAX_VALUE, Sentinel.getInstance2());
    private final RunnerMinRun minKey_RunnerMinRun = new RunnerMinRun(Float.MIN_VALUE, Sentinel.getInstance1());
    private final RunnerAvgRun maxKey_RunnerAvgRun = new RunnerAvgRun(Float.MAX_VALUE, Sentinel.getInstance2());
    private final RunnerAvgRun minKey_RunnerAvgRun = new RunnerAvgRun(Float.MIN_VALUE, Sentinel.getInstance1());


    public Race() {
        init();
    }

    /**
     * Constructs a new Race instance.
     * Initializes the trees with sentinel values and the min run trackers.
     * runtime - O(1)
     */
    public void init(){
        runnersByID = new Tree23<>(true);
        runnersByMin = new Tree23<>(maxKey_RunnerMinRun, minKey_RunnerMinRun);
        runnersByAvg = new Tree23<>(maxKey_RunnerAvgRun, minKey_RunnerAvgRun);
        minRunByMin = new RunnerMinRun(Float.MAX_VALUE, Sentinel.getInstance2());
        minRunByAvg = new RunnerAvgRun(Float.MAX_VALUE, Sentinel.getInstance2());
    }

    /**
     * Adds a new runner to the race.
     * @param id The unique identifier of the new runner.
     * @throws IllegalArgumentException if a runner with the given ID already exists.
     * runtime - O(log n)
     */
    public void addRunner(RunnerID id){
        if (runnersByID.search(runnersByID.getRoot(), id) == null){
            Runner newRunner = new Runner(id);
            Node<RunnerMinRun> newMin = new Node<>(newRunner.getMinRun(), newRunner);
            Node<RunnerAvgRun> newAvg = new Node<>(newRunner.getAvgRun(), newRunner);

            if (this.runnersByID.getTreeSize() == 0){
                this.minRunByMin = newMin.getKey();
                this.minRunByAvg = newAvg.getKey();
            }
            runnersByID.insert(new Node<>(id, newRunner));
            runnersByAvg.insert(newAvg);
            runnersByMin.insert(newMin);
        }
        else{
            throw new IllegalArgumentException("Runner already exists, can't add!");
        }
    }

    /**
     * Removes a runner from the race.
     * @param id The unique identifier of the runner to remove.
     * @throws IllegalArgumentException if the runner does not exist.
     * runtime - O(log n)
     */
    public void removeRunner(RunnerID id){
        Node<RunnerID> runnerID = runnersByID.search(runnersByID.getRoot(), id);
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
                Node<RunnerAvgRun> avgMin = runnersByAvg.minimum(maxKey_RunnerAvgRun);
                if(avgMin == null){
                    this.minRunByAvg = new RunnerAvgRun(Float.MAX_VALUE, Sentinel.getInstance2());
                }
                else {
                    this.minRunByAvg = avgMin.getKey();
                }
            }

            if (this.minRunByMin == runnerMinRun){
                Node<RunnerMinRun> minRun = runnersByMin.minimum(maxKey_RunnerMinRun);
                if(minRun == null){
                    this.minRunByMin = new RunnerMinRun(Float.MAX_VALUE, Sentinel.getInstance2());
                }
                else {
                    this.minRunByMin = runnersByMin.minimum(maxKey_RunnerMinRun).getKey();
                }
            }
        }
    }

    /**
     * Adds a run time to a specific runner and updates their statistics.
     * @param id The unique identifier of the runner.
     * @param time The time of the run to add.
     * @throws IllegalArgumentException if the runner does not exist.
     * @throws IllegalArgumentException if the run time is negative.
     * runtime - O(log n)
     */
    public void addRunToRunner(RunnerID id, float time) {
        Node<RunnerID> runnerNode = runnersByID.search(runnersByID.getRoot(), id);
        if(runnerNode == null) {
            throw new IllegalArgumentException("runner doesnt exist, cant add run!");
        } else if (time <= 0) {
            throw new IllegalArgumentException("negative run time, cant add run!");
        } else {
            Runner runner = runnerNode.getData();
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

    /**
     * Removes a run from a specific runner and updates their statistics.
     * @param id The unique identifier of the runner.
     * @param time The time of the run to remove.
     * @throws IllegalArgumentException if the runner does not exist.
     * @throws IllegalArgumentException if run doesn't exist
     * runtime - O(log n + log m)
     */
    public void removeRunFromRunner(RunnerID id, float time){
        Node<RunnerID> runnerNode = runnersByID.search(runnersByID.getRoot(), id);
        if (runnerNode == null){
            throw new IllegalArgumentException("runner doesnt exist, cant remove run!");
        } else if (runnerNode.getData().getRuns().searchByTime(runnerNode.getData().getRuns().getRoot(), time) == null) {
            throw new IllegalArgumentException("run doesnt exist, cant remove run!");
        } else {
            Runner runner = runnerNode.getData();

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

    /**
     * Retrieves the ID of the fastest runner based on average run times.
     * @return The ID of the runner with the best average time.
     * @throws IllegalArgumentException if the race is empty.
     * runtime - O(1)
     */
    public RunnerID getFastestRunnerAvg(){
        if(this.runnersByID.getTreeSize() == 0){
            throw new IllegalArgumentException("empty race, cant return fastest runner by avg");
        }
        return this.minRunByAvg.getId();
    }

    /**
     * Retrieves the ID of the runner with the fastest minimum run time.
     * @return The ID of the fastest runner by minimum time.
     * @throws IllegalArgumentException if the race is empty.
     * runtime - O(1)
     */
    public RunnerID getFastestRunnerMin(){
        if(this.runnersByID.getTreeSize() == 0){
            throw new IllegalArgumentException("empty race, cant return fastest runner by avg");
        }
        return this.minRunByMin.getId();
    }

    /**
     * Gets the minimum run time of a specific runner.
     * @param id The unique identifier of the runner.
     * @return The minimum run time of the specified runner.
     * @throws IllegalArgumentException if the runner does not exist.
     * runtime - O(log n)
     */
    public float getMinRun(RunnerID id){
        Node<RunnerID> runnerNode = runnersByID.search(runnersByID.getRoot(), id);
        if (runnerNode == null){
            throw new IllegalArgumentException("runner doesnt exist, cant return min run!");
        }
        Runner runner = runnerNode.getData();
        return runner.getMinRun().getMinRun();
    }

    /**
     * Gets the average run time of a specific runner.
     * @param id The unique identifier of the runner.
     * @return The average run time of the specified runner.
     * @throws IllegalArgumentException if the runner does not exist.
     * runtime - O(log n)
     */
    public float getAvgRun(RunnerID id){
        Node<RunnerID> runnerNode = runnersByID.search(runnersByID.getRoot(), id);
        if (runnerNode == null){
            throw new IllegalArgumentException("runner doesnt exist, cant return min run!");
        }
        Runner runner = runnerNode.getData();
        return runner.getAvgRun().getAvgRun();
    }

    /**
     * returns the position of the runners average run time in the linear order of the runners averages.
     * @param id The unique identifier of the runner.
     * @return The rank of the runner based on average run times, where a rank of 1 means the fastest average.
     * @throws IllegalArgumentException if the runner does not exist.
     * runtime - O(log n)
     */
    public int getRankAvg(RunnerID id)
    {
        Node<RunnerID> runnerNode = runnersByID.search(runnersByID.getRoot(), id);
        if (runnerNode == null){
            throw new IllegalArgumentException("runner doesnt exist, cant return rank by avg!");
        }

        RunnerAvgRun avgRun = runnersByID.search(runnersByID.getRoot(), id).getData().getAvgRun();
        Node avgRunNode = runnersByAvg.search(runnersByAvg.getRoot(), avgRun);
        return runnersByAvg.rank(avgRunNode);
    }
    /**
     * returns the position of the runners min run time in the linear order of the runners min run.
     * @param id The unique identifier of the runner.
     * @return The rank of the runner based on min run times, where a rank of 1 means the shortest run.
     * @throws IllegalArgumentException if the runner does not exist.
     * runtime - O(log n)
     */
    public int getRankMin(RunnerID id)
    {
        Node<RunnerID> runnerNode = runnersByID.search(runnersByID.getRoot(), id);
        if (runnerNode == null){
            throw new IllegalArgumentException("runner doesnt exist, cant return rank by min!");
        }
        RunnerMinRun minRun = runnersByID.search(runnersByID.getRoot(), id).getData().getMinRun();
        Node minRunNode = runnersByMin.search(runnersByMin.getRoot(), minRun);
        return runnersByMin.rank(minRunNode);
    }
}