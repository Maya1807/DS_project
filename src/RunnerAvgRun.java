public class RunnerAvgRun extends RunnerID{
    private float avgRun;
    private RunnerID id;

    public RunnerAvgRun(float avgRun, RunnerID id){
        this.avgRun = avgRun;
        this.id = id;
    }

    public float getAvgRun() {
        return avgRun;
    }

    public void setAvgRun(float avgRun) {
        this.avgRun = avgRun;
    }

    public RunnerID getId() {
        return id;
    }

    @Override
    public boolean isSmaller(RunnerID other) {
        //if (other.getClass() != this.getClass()){
        //throw new IllegalArgumentException("illegal compare isSmaller");
        //}
        if (this.avgRun == ((RunnerAvgRun)other).getAvgRun()){
            return this.id.isSmaller(((RunnerAvgRun)other).getId());
        }
        return this.avgRun < ((RunnerAvgRun)other).getAvgRun();
    }

    @Override
    public String toString(){
        return null;
    }
}