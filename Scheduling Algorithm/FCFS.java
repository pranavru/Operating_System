import java.util.*;

public class FCFS {

    static Scanner sc = new Scanner(System.in);
    static int numberOfProcesses = 0;

    static int process[][];
    static int arrivalOfProcesses[][];

    FCFS() {
        System.out.println("---Welcome to First Come First Serve Algorithm---");
        System.out.println("Please enter the number of Processes");

        numberOfProcesses = sc.nextInt();
        process = new int[numberOfProcesses][7];
        arrivalOfProcesses = new int[1][numberOfProcesses];

        int index = 0;
        while (index < numberOfProcesses) {
            process[index][0] = index;
            index++;
        }

    }

    public static void acceptInput(final int col) {
        for (int i = 0; i < process.length; i++) {
            process[i][col] = sc.nextInt();
        }
    }

    public static void sortProcesses() {
        int i = 0;
        while (i < numberOfProcesses) {
            for (int j = 0; j < numberOfProcesses - 1; j++) {
                for (int k = j; k < numberOfProcesses; k++) {
                    if (process[j][1] > process[k][1]) {
                        int temp[] = process[j];
                        process[j] = process[k];
                        process[k] = temp;
                    } else if (process[j][1] == process[k][1]) {
                        if (process[j][0] > process[k][0]) {
                            int temp[] = process[j];
                            process[j] = process[k];
                            process[k] = temp;
                        }
                    }
                }
            }
            i++;
        }
    }

    public static int sumOfArrivalTime() {
        int i = 0;
        int sum = 0;
        while (i < numberOfProcesses) {
            sum += process[i][1];
            i++;
        }
        return sum;
    }

    public static void displayTable(final int[][] process) {
        System.out.println("The Process and its timings are as follows\n");
        System.out.println("Process No | Arrival | Service | Waiting | Response | Total Time | C. S. ");
        for (int i = 0; i < process.length; i++) {
            System.out.println("\tP" + process[i][0] + "\t" + process[i][1] + "\t" + process[i][2] + "\t"
                    + process[i][3] + "\t" + process[i][4] + "\t" + process[i][5] + "\t" + process[i][6]);
        }
    }

    public static void main(final String args[]) {

        new FCFS();
        System.out.println("Please enter the Process Arrival Time");
        acceptInput(1);

        System.out.println("Please enter the Process Service Time");
        acceptInput(2);

        // Algorithm Calculation of the Processes
        sortProcesses();
        int totalArrivalTime = sumOfArrivalTime();
        System.out.println("Total Arrival Time " + totalArrivalTime);

        displayTable(process);

    }
}