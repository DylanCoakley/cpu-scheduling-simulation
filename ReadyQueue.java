/**
 * Class: ReadyQueue
 * 
 * The ReadyQueue interface defines what attributes and methods
 * must be included by a class that implements the ready queue of
 * one of the indicated scheduling algoritms (FCFS, SJF, RR).
 * All concrete ready queue classes must implement this interface.
 */

public interface ReadyQueue
{
    public final int MAX_SIZE = 10;
    
    public void addProcess(PCB process);
    public void removeProcess(PCB process);
    public int getSize();
    public boolean isEmpty();
}
