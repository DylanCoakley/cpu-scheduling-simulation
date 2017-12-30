
/**
 * Class: CPU
 * 
 * The CPU class contains an attribute clock which
 * acts as the CPU Clock Timer for the system. It also
 * implements execute methods to simulate the running of
 * a process on the CPU.
 */
public class CPU
{
    // CPU CLOCK
    private int clock;
    
    public CPU() 
    {
        clock = 0;
    }
   
    // Execute used for FCFS and SJF schedules
    public void execute(PCB process)
    {
        process.setState(PCB.State.RUNNING);
        
        int executionTime = process.getCurrentBurst();
        
        process.addToCpuShots();
        process.addToProgCounter(executionTime);
        process.setNextBurst();
        
        clock += executionTime;
    }
    
    // Execute used for RR schedule
    public void execute(PCB process, int quantum)
    {
        process.setState(PCB.State.RUNNING);
        
        int executionTime;
        // If process doesn't complete execution after this time quantum
        if(process.getCurrentBurst() > quantum) {
            executionTime = quantum;
            process.setCurrentBurst(process.getCurrentBurst() - executionTime);
        } else {
            // Process will complete current execution after this quantum
            executionTime = process.getCurrentBurst();
            process.setCurrentBurst(0);
        }
        
        process.addToCpuShots();
        process.addToProgCounter(executionTime);
        clock += executionTime;
    }
    
    public void addToClock(int time) {
        clock += time;
    }
    
    public int getClockTime() {
        return clock;
    }
}
