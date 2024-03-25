public class Run extends RunnerID{
    private float runTime;
    private RunnerID id;


    public Run(){
        this.runTime = 0;
        this.id = null;
    }

    public Run(float runTime, RunnerID id){
        this.runTime = runTime;
        this.id = id;
    }

    public float getRunTime() { return this.runTime; }

    public void setRunTime(float runTime) {
        this.runTime = runTime;
    }
    @Override
    public boolean isSmaller(RunnerID other) {
        return this.runTime < ((Run)other).getRunTime();
    }

    @Override
    public String toString() {
        return null;
    }
}