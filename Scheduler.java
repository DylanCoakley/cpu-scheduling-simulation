/**
 * Class: Scheduler
 * 
 * The Scheduler class defines what attributes and methods
 * must be included by a class that implements the scheduling of
 * one of the indicated scheduling algoritms (FCFS, SJF, RR).
 * All concrete scheduler classes must extend this class.
 */

import java.util.*;

public abstract class Scheduler
{
    protected String filename;
    protected int numJobs;
    protected int completedJobs;
    protected int totalProcessingTime;
    protected int totalWaitingTime;
    protected int totalTurnAroundTime;
    protected int totalCPUShots;
    protected Scanner jobQueue;
    protected BlockedQueue blockedQueue;
    protected CPU cpu;
    
    public abstract void schedule();
    public abstract PCB getProcessFromMemory();
    public abstract void outputSystemUtilization();
    public abstract void outputFinalStatistics();
}
