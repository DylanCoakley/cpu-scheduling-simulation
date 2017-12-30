/**
 * Class: RRReadyQueue
 * 
 * The RRReadyQueue implements the ready queue that is
 * is to be used by the Round Robin scheduler.
 */
import java.util.*;

public class RRReadyQueue implements ReadyQueue
{
    private LinkedList<PCB> readyQueue;

    public RRReadyQueue()
    {
        readyQueue = new LinkedList<PCB>();
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
