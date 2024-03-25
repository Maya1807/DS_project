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
        if(other instanceof RunnerAvgRun){
            if(((RunnerAvgRun) other).isMinSentinel()) { return false;}
            if(((RunnerAvgRun) other).isMaxSentinel()) { return true;}
        }
        if(this.isMinSentinel()) { return true;}
        if(this.isMaxSentinel()) { return false;}

        if (this.avgRun == ((RunnerAvgRun)other).getAvgRun()){
            return this.id.isSmaller(((RunnerAvgRun)other).getId());
        }
        return this.avgRun < ((RunnerAvgRun)other).getAvgRun();
    }

    @Override
    public String toString(){
        return null;
    }
    private boolean isMinSentinel(){
        if(avgRun == Float.MIN_VALUE && id == Sentinel.getInstance1())
            return true;
        return false;
    }
    private boolean isMaxSentinel(){
        if(avgRun == Float.MAX_VALUE && id == Sentinel.getInstance2())
            return true;
        return false;
    }
}