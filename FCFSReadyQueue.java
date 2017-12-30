/**
 * Class: FCFSReadyQueue 
 * 
 * The FCFSReadyQueue implements the ready queue that is
 * is to be used by the First Come First Serve scheduler.
 */
import java.util.*;

public class FCFSReadyQueue implements ReadyQueue
{
    private LinkedList<PCB> readyQueue;

    public FCFSReadyQueue()
    {
        readyQueue = new LinkedList<PCB>();
    }

    // Add a process to the end of the ReadyQueue    
    public void addProcess(PCB process) {
        if(getSize() < MAX_SIZE) {
            readyQueue.offer(process);
        }
    }
    
    // Removes the process at the head of the ReadyQueue    
    public void removeProcess(PCB process) {
        if(getSize() > 0) {
            readyQueue.poll();
        }
    }

    // Returns the process at the head of the ReadyQueue    
    public PCB retrieveFirstProcess() {
        return readyQueue.peek();
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
