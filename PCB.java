
/**
 * Class: PCB
 * 
 * The PCB class is used to define a Process Control Block, 
 * which represents a process in the system. It contains
 * attributes which provide information about a given
 * process. An object of PCB is referred to as a "process".
 * 
 */

import java.util.*;

public class PCB
{
    // Class attributes
    private int jobID;
    private int arrivalTime;
    private int completionTime;
    
    // The enumerated type State represents the current state of a given process
    public static enum State {
        READY,
        RUNNING,
        BLOCKED
    }
    
    private State state;
    private int progCounter;
    private LinkedList<Integer> bursts;
    private int cpuShots;
    private int currentIOCompletionTime;
    
    // Class constructors
    public PCB() {
        jobID = 0;
        arrivalTime = 0;
        state = State.READY;
        progCounter = 0;
        cpuShots = 0;
        bursts = new LinkedList<Integer>();
    }
    
    public PCB(int id, int a, LinkedList<Integer> b) {
        this();
        jobID = id;
        arrivalTime = a;
        bursts = b;
    }
    
    // Class methods
    
    // When a process completes all execution, it outputs some information about itself
    public void outputStatistics() {
        System.out.println(" ------ Process Completed ------ ");
        System.out.println("Process ID:       " + getJobID());
        System.out.println("Arrival Time:     " + getArrivalTime());
        System.out.println("Completion Time:  " + getCompletionTime());
        System.out.println("Processing Time:  " + getProgCounter());
        System.out.println("Waiting Time:     " + getWaitingTime());
        System.out.println("Turn-around Time: " + getTurnAroundTime());
        System.out.println("# of CPU Shots:   " + getCpuShots());
        System.out.println();
        
    }
    
    public void setJobID(int id) {
        jobID = id;
    }
    
    public int getJobID() {
        return jobID;
    }
    
    public void setArrivalTime(int time) {
        arrivalTime = time;
    }
    
    public int getArrivalTime() {
        return arrivalTime;
    }
    
    public void setCompletionTime(int time) {
        completionTime = time;
    }
    
    public int getCompletionTime() {
        return completionTime;
    }
    
    public void setState(State s) {
        state = s;
    }
    
    public State getState() {
        return state;
    }
    
    public void addToProgCounter(int count) {
        progCounter += count;
    }
    
    public int getProgCounter() {
        return progCounter;
    }
    
    public int getNumBursts() {
        return bursts.size();
    }
    
    public void addToCpuShots() {
        cpuShots += 1;
    }
    
    public int getCpuShots() {
        return cpuShots;
    }
    
    public void setBursts(LinkedList<Integer> b) {
        bursts = b;
    }
    
    public LinkedList<Integer> getBursts() {
        return bursts;
    }
    
    public void setCurrentIOCompletionTime(int time) {
        currentIOCompletionTime = time;
    }
    
    public int getCurrentIOCompletionTime() {
        return currentIOCompletionTime;
    }
    
    // Computes the waiting time based on the completion time, arrival time, and processing time of the process
    public int getWaitingTime() {
        return getCompletionTime() - getArrivalTime() - getProgCounter();
    }
    
    // Computes the turn-around time based on the completion time and the arrival time of the process
    public int getTurnAroundTime() {
        return getCompletionTime() - getArrivalTime();
    }
    
    // Returns the value of the current burst if there are any bursts left
    public int getCurrentBurst() {
        if(bursts.size() > 0) {
            return bursts.peek();
        } else {
            return -1;
        }
    }
    
    // Changes the value of the current burst to the given newBurst value
    public void setCurrentBurst(int newBurst) {
        if(bursts.size() > 0) {
            bursts.poll();
            bursts.offerFirst(newBurst);
        }
    }
    
    // Once a burst has fully completed, setNextBurst is called to prepare the next burst for execution
    public void setNextBurst() {
        if(bursts.size() > 0) {
            bursts.poll();
        }
    }
    
    // Checks if a process has completed all required execution
    public boolean finishedAllBursts() {
        return bursts.size() == 0;
    }
}
