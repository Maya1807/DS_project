class RunnerIDStr extends RunnerID{
    private String id;
    public RunnerIDStr(String id){
        super();
        this.id = id;
    }
    @Override
    public boolean isSmaller(RunnerID other) {
        return this.id.compareTo(((RunnerIDStr) other).id) < 0;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }


}
public class Tests {
    public static void test1(){
        //passed test
        RunnerIDStr id1 = new RunnerIDStr("a");
        RunnerIDStr id2 = new RunnerIDStr("b");
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

    public static void test2(){
        //pass test
        //should get exception - adding an existing Runner
        RunnerIDInt id1 = new RunnerIDInt(3);
        RunnerIDInt id2 = new RunnerIDInt(3);
        Race race = new Race();
        race.addRunner(id1);
        race.addRunner(id2);
    }
    public static void test3(){
        //passed test
        //should get exception - removing run that doesnt exist

        RunnerIDInt id1 = new RunnerIDInt(3);
        Race race = new Race();
        race.addRunner(id1);
        race.addRunToRunner(id1, (float)20);
        race.removeRunFromRunner(id1,(float)0.5);
    }

    public static void test4(){
        //passed test
        //should get exception - adding run to a runner that doesnt exist

        RunnerIDInt id1 = new RunnerIDInt(3);
        Race race = new Race();
        race.addRunner(id1);
        race.removeRunner(id1);
        race.addRunToRunner(id1, (float)1 );
    }
    public static void test5(){
        //passed test

        RunnerIDInt id1 = new RunnerIDInt(3);
        Race race = new Race();
        race.removeRunner(id1);
    }
    public static void test6(){
        RunnerIDInt id1 = new RunnerIDInt(1);
        RunnerIDInt id2 = new RunnerIDInt(2);
        RunnerIDInt id3 = new RunnerIDInt(3);
        RunnerIDInt id4 = new RunnerIDInt(4);
        RunnerIDInt id5 = new RunnerIDInt(5);
        RunnerIDInt id6 = new RunnerIDInt(6);
        RunnerIDInt id7 = new RunnerIDInt(7);
        RunnerIDInt id8 = new RunnerIDInt(8);
        Race race = new Race();
        race.addRunner(id1);
        race.addRunner(id2);
        race.addRunner(id3);
        race.addRunner(id4);
        race.addRunner(id5);
        race.addRunner(id6);
        race.addRunner(id7);
        race.addRunner(id8);

        race.addRunToRunner(id1, (float)50);
        race.addRunToRunner(id1, (float)40);
        race.addRunToRunner(id1, (float)30);
        race.addRunToRunner(id1, (float)5);

        race.addRunToRunner(id2, (float)500);
        race.addRunToRunner(id2, (float)400);
        race.addRunToRunner(id2, (float)300);
        race.addRunToRunner(id2, (float)50);
        race.addRunToRunner(id2, (float)1);

        race.removeRunFromRunner(id2,  (float)1);

        race.removeRunFromRunner(id2, (float) 500);
        race.removeRunFromRunner(id2, (float) 400);
        race.removeRunFromRunner(id2, (float) 300);
        race.removeRunFromRunner(id2, (float) 50);

        System.out.println(race.getRankMin(id2));
        System.out.println(race.getRankAvg(id2));

        race.addRunToRunner(id2,(float) 1);

        System.out.println(race.getRankMin(id1));
        System.out.println(race.getRankAvg(id1));



    }
    //tests to add:
    //1. checking rank methods

    public static void test7(){
        RunnerIDStr id1 = new RunnerIDStr("MayaHarram");
        RunnerIDStr id2 = new RunnerIDStr("MayaM");
        Race race = new Race();
        race.addRunner(id1);
        race.addRunner(id2);
        race.addRunToRunner(id1, (float)1 );
        race.addRunToRunner(id1, (float)10 );
        race.addRunToRunner(id1, (float)1000 );

        race.addRunToRunner(id2, (float)0.5 );
        race.removeRunner(id2);
        System.out.println(race.getFastestRunnerAvg());
        race.removeRunner(id1);

        race.addRunner(id1);




    }



}
