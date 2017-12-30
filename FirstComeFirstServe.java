
/**
 * Class: FirstComeFirstServe
 * 
 * The FirstComeFirstServe class controls the scheduling of all
 * processes between the ReadyQueue, BlockedQueue, and CPU when
 * using the FCFS algorithm. It records statistics about the
 * processing and displays it periodically, with a final
 * set of statistics displayed at the end of the simulation.
 * 
 */

import java.io.*;
import java.util.*;

public class FirstComeFirstServe extends Scheduler
{   
    private FCFSReadyQueue readyQueue;
    
    public FirstComeFirstServe(String f) {
        filename = f;
        numJobs = 0;
        completedJobs = 0;
        totalProcessingTime = 0;
        totalWaitingTime = 0;
        totalTurnAroundTime = 0;
        totalCPUShots = 0;
        jobQueue = null;
        readyQueue = new FCFSReadyQueue();
        blockedQueue = new BlockedQueue();
        cpu = new CPU();
    }
    
    public void schedule() {
        
        // Opens the file that will act as the JobQueue, and makes it able to be read
        try {
            jobQueue = new Scanner(new File(filename));
        } catch(FileNotFoundException e) {
            System.out.println("File: " + filename + " not found in directory!");
            System.exit(0);
        }
        
        // Load the first 10 jobs from the JobQueue into the ReadyQueue
        for(int i = 0; i < 10; i++) {
            if(jobQueue.hasNextLine()) {
               readyQueue.addProcess(getProcessFromMemory()); 
            }
            
        }
        numJobs += 10;
        
        int prevClock = cpu.getClockTime();
        outputSystemUtilization();
        
        while(!readyQueue.isEmpty() || !blockedQueue.isEmpty()) {
            
            // Output system utilization information every ~200 time units
            if(cpu.getClockTime() - prevClock >= 200) {
                outputSystemUtilization();
                prevClock = cpu.getClockTime();
            }
            
            // Check if a process in the blockedQueue is ready to execute,
            // and puts it at the back of the readyQueue. Then it will 
            // execute the process at the front of the readyQueue.
            PCB currentProcess = null;
            if(!blockedQueue.isEmpty()) {
                
                // Check if any jobs in the blockedQueue is ready to run again, if any are, add them to the readyQueue
                int completionTime = blockedQueue.retrieveFirstProcess().getCurrentIOCompletionTime();
                if(completionTime <= cpu.getClockTime()) {
                    currentProcess = blockedQueue.retrieveFirstProcess();
                    currentProcess.setState(PCB.State.READY);
                    blockedQueue.removeFirstProcess();
                    readyQueue.addProcess(currentProcess);
                } 
                else if(readyQueue.isEmpty()) {
                    // No processes in the readyQueue, and all processes in the blockedQueue are
                    // not yet ready to execute.
                    // Increment the CPU clock by the amount of time needed to execute the next
                    // process in the blockedQueue, and skip to the next iteration of the loop.
                    cpu.addToClock(completionTime - cpu.getClockTime());
                    continue;
                }
            }
            
            // Fetch next process to execute from the readyQueue
            currentProcess = readyQueue.retrieveFirstProcess();
            readyQueue.removeProcess(currentProcess);
     
            // Execute process on the CPU
            cpu.execute(currentProcess);
            
            // Check if the process that just executed has completed all bursts
            if(!currentProcess.finishedAllBursts()) {
                // Process has not completed all bursts, send back to blockedQueue
                currentProcess.setState(PCB.State.BLOCKED);
                blockedQueue.addProcess(currentProcess, cpu.getClockTime());
            } else {
                // Process has completed all execution
                currentProcess.setCompletionTime(cpu.getClockTime());
                currentProcess.outputStatistics();
                completedJobs++;
                totalProcessingTime += currentProcess.getProgCounter();
                totalWaitingTime += currentProcess.getWaitingTime();
                totalTurnAroundTime += currentProcess.getTurnAroundTime();
                totalCPUShots += currentProcess.getCpuShots();
                
                // Add a process from jobQueue to the readyQueue if it is possible
                if(jobQueue.hasNextLine()) {
                    PCB newProcess = getProcessFromMemory();
                    readyQueue.addProcess(newProcess);
                    numJobs++;
                }
            }
            
        }
        
        // Output the final status of the system and statistics
        jobQueue.close();
        outputSystemUtilization();
        outputFinalStatistics();
    }
    
    // Returns the next available job from the JobQueue text file as a PCB object
    public PCB getProcessFromMemory() {
        
        String currentLine = jobQueue.nextLine();
        String[] numbers = currentLine.split(" ");
                    
        PCB process = null;

        int numBursts = Integer.parseInt(numbers[2]);
        LinkedList<Integer> processBursts = new LinkedList<Integer>();
        for(int i = 3; i < numBursts + 3; i++) {
            processBursts.offer(Integer.parseInt(numbers[i]));
        }
        
        process = new PCB(Integer.valueOf(numbers[0]), Integer.valueOf(numbers[1]), processBursts);

        return process;
    }
    
    // Outputs total system utilization to the terminal
    public void outputSystemUtilization() {
        System.out.println(" ------- System Utilization ------- ");
        System.out.println("# of jobs in ReadyQueue:   " + readyQueue.getSize());
        System.out.println("# of jobs in BlockedQueue: " + blockedQueue.getSize());
        System.out.println("# of jobs completed:       " + completedJobs);
        System.out.println();
    }
    
    // After all processes are finished execution, the final stats of the simulation are shown
    public void outputFinalStatistics() {
        System.out.println("********* FINAL STATISTICS **********");
        System.out.println("Scheduling Algorithm:    FCFS");
        System.out.println("Current CPU Clock:       " + cpu.getClockTime());
        System.out.println("Average Processing Time: " + String.format("%.2f", (totalProcessingTime * 1.0 / numJobs)));
        System.out.println("Average Waiting Time:    " + String.format("%.2f", (totalWaitingTime * 1.0 / numJobs)));
        System.out.println("Average Turnaround Time: " + String.format("%.2f", (totalTurnAroundTime * 1.0/ numJobs)));
        System.out.println("Average # of CPU Shots:  " + String.format("%.2f", (totalCPUShots * 1.0/ numJobs)));
        System.out.println();
    }
}
