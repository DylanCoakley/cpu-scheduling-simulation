
//**********************************************************
// Dylan Coakley                                                                                                  
// CSCI-375 Operating Systems                                                                       
// Programming Assignment #1
//
// This program implements and simulates the running of
// 3 different CPU scheduling algoritms:
//  1. FCFS (First Come First Serve)
//  2. SJF  (Shortest Job First)
//  3. RR   (Round Robin)
// The program will be given the algorithm to use,
// the text file from which it will read process
// information from, and possibly a time quantum
// from user input from the terminal. It will then
// call upon the appropriate scheduler to simulate
// the indicated algorithm.
// **********************************************************

public class CPUSim
{
    public static void main(String[] args)
    {
        if(args.length == 3) {
            if("RR".equalsIgnoreCase(args[0])) {
                RoundRobin RR = new RoundRobin(args[2], Integer.parseInt(args[1]));
                RR.schedule();
            }
        } else if(args.length == 2) {
            if("FCFS".equalsIgnoreCase(args[0])) {
                FirstComeFirstServe FCFS = new FirstComeFirstServe(args[1]);
                FCFS.schedule();
            } else if("SJF".equalsIgnoreCase(args[0])) {
                ShortestJobFirst SJF = new ShortestJobFirst(args[1]);
                SJF.schedule();
            }
        }
    }
    
}
