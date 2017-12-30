/**
 * Class: SJFReadyQueue
 * 
 * The SJFeadyQueue implements the ready queue that is
 * is to be used by the Shortest Job First scheduler.
 * It is a priority queue implementation which is a min heap
 * based on the current burst of the processes.
 * 
 */
import java.util.*;

public class SJFReadyQueue implements ReadyQueue
{
    private PriorityQueue<PCB> readyQueue;

    public SJFReadyQueue()
    {
        readyQueue = new PriorityQueue<PCB>(MAX_SIZE, new SJFComparator());
    }
    
    // This SJFComparator class is used to define how the SJF ReadyQueue will sort the processes
    // inside it. It defines a min-heap based on the current burst of the process. Ties (equal
    // burst times) are broken by comparing the processes' job IDs (FCFS order).
    class SJFComparator implements Comparator<PCB> {
        public int compare(PCB process1, PCB process2)
        {
            
            int burst1 = process1.getCurrentBurst();
            int burst2 = process2.getCurrentBurst();
            
            if(burst1 < burst2) {
                return -1;
            } else if(burst1 == burst2) {
                if(process1.getJobID() < process2.getJobID()) {
                    return -1;
                } else {
                    return 1;
                }    
            } else {
                return 1;
            }
            
            // return process1.getCurrentBurst() - process2.getCurrentBurst();
        }
    }
   
    // Add a process to the end of the ReadyQueue
    public void addProcess(PCB process) {
        if(getSize() < MAX_SIZE) {
            readyQueue.offer(process);
        }
    }
    
    // Returns the process at the head of the ReadyQueue
    public PCB retrieveFirstProcess() {
        return readyQueue.peek();
    }
    
    // Removes the process from the head of the ReadyQueue    
    public void removeProcess(PCB process) {
        if(getSize() > 0) {
            readyQueue.poll();
        }
    }
    
    // Returns the size of the ReadyQueue
    public int getSize() {
        return readyQueue.size();
    }
    
    // Checks whether or not the ReadyQueue is empty
    public boolean isEmpty() {
        return readyQueue.size() == 0;
    }
}
