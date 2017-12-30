/**
 * Class: BlockedQueue
 * 
 * The BlockedQueue class implements a queue
 * in which process wait while they request I/O
 * It contains methods for manipulating the queue,
 * such as methods for adding processes and removing
 * processes.
 *
 */

import java.util.*;

public class BlockedQueue
{
    private LinkedList<PCB> blockedQueue;

    public BlockedQueue() 
    {
        blockedQueue = new LinkedList<PCB>();
    }
    
    // Add a process to the end of the BlockedQueue
    public void addProcess(PCB process, int clockTime) {
        // The IO Completion Time of the process is set to the clock time plus 10, plus another
        // 10 time units for every process already in the BlockedQueue
        process.setCurrentIOCompletionTime(clockTime + 10 + 10 * getSize());
        blockedQueue.offer(process);
    }
    
    // Removes the process from the head of the BlockedQueue
    public void removeFirstProcess() {
        if(getSize() > 0) {
            blockedQueue.poll();
        }
    }
    
    // Returns the process at the head of the BlockedQueue
    public PCB retrieveFirstProcess() {
        return blockedQueue.peek();
    }
    
    // Returns the size of the BlockedQueue
    public int getSize() {
        return blockedQueue.size();
    }
    
    // Checks whether or not the BlockedQueue is empty
    public boolean isEmpty() {
        return blockedQueue.size() == 0;
    }
    
}
