public class RunnerMinRun extends RunnerID{
    private float minRun;
    private RunnerID id;

    public RunnerMinRun(float minRun, RunnerID id){
        this.minRun = minRun;
        this.id = id;
    }

    public float getMinRun() {
        return minRun;
    }

    public void setMinRun(float minRun) {
        this.minRun = minRun;
    }

    public RunnerID getId() {
        return id;
    }

    @Override
    public boolean isSmaller(RunnerID other) {
        if(other instanceof RunnerMinRun){
            if(((RunnerMinRun) other).isMinSentinel()) { return false;}
            if(((RunnerMinRun) other).isMaxSentinel()) { return true;}
        }
        if(this.isMinSentinel()) { return true;}
        if(this.isMaxSentinel()) { return false;}

        if (this.minRun == ((RunnerMinRun)other).getMinRun()){
            return this.id.isSmaller(((RunnerMinRun)other).getId());
        }
        return this.minRun < ((RunnerMinRun)other).getMinRun();
    }

    @Override
    public String toString(){
        return null;
    }
    private boolean isMinSentinel(){
        if(minRun == Float.MIN_VALUE && id == Sentinel.getInstance1())
            return true;
        return false;
    }
    private boolean isMaxSentinel(){
        if(minRun == Float.MAX_VALUE && id == Sentinel.getInstance2())
            return true;
        return false;
    }
}