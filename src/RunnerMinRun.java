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
        //if (other.getClass() != this.getClass()){
        //throw new IllegalArgumentException("illegal compare isSmaller");
        //}
        if (this.minRun == ((RunnerMinRun)other).getMinRun()){
            return this.id.isSmaller(((RunnerMinRun)other).getId());
        }
        return this.minRun < ((RunnerMinRun)other).getMinRun();
    }

    @Override
    public String toString(){
        return null;
    }

}