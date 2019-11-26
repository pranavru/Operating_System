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

        int index = 0;
        while (index < numberOfProcesses) {
            process[index][0] = index + 1;
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
                        final int temp[] = process[j];
                        process[j] = process[k];
                        process[k] = temp;
                    } else if (process[j][1] == process[k][1]) {
                        if (process[j][0] > process[k][0]) {
                            final int temp[] = process[j];
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
            sum += process[i][2];
            i++;
        }
        return sum;
    }

    public static void sequenceOfArrivedProcesses(final int totalTime) {

        int x = 0;
        arrivalOfProcesses = new int[numberOfProcesses][totalTime];
        while (x < numberOfProcesses) {
            System.out.println("x " + x + " P" + process[x][0] + " LL " + process[x][1] + " UL " + process[x][2]
                    + " TT " + totalTime);
            for (int j = process[x][1]; j < (process[x][1] + process[x][2]); j++) {
                arrivalOfProcesses[x][j] = process[x][0];
            }
            x++;
        }
    }

    public static void displayProcessTable(int[][] process) {
        System.out.println("The Process and its timings are as follows\n");
        System.out.println("Process No | Arrival | Service | Waiting | Response | Total Time | C. S. ");
        for (int i = 0; i < process.length; i++) {
            System.out.println("\tP" + process[i][0] + "\t" + process[i][1] + "\t" + process[i][2] + "\t"
                    + process[i][3] + "\t" + process[i][4] + "\t" + process[i][5] + "\t" + process[i][6]);
        }
    }

    public static void arrivalOfProcesses(final int totalTime) {
        System.out.println("The Process and its Arrival are as follows\n");
        for (int i = 0; i < totalTime; i++)
            System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < arrivalOfProcesses.length; i++) {
            for (int j = 0; j < arrivalOfProcesses[0].length; j++) {
                System.out.print(arrivalOfProcesses[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(final String args[]) {

        new FCFS();
        System.out.println("Please enter the Process Arrival Time");
        acceptInput(1);

        System.out.println("Please enter the Process Service Time");
        acceptInput(2);

        // Algorithm Calculation of the Processes
        int totalServiceTime = sumOfArrivalTime();
        System.out.println("Total Service Time " + totalServiceTime);

        sortProcesses();
        sequenceOfArrivedProcesses(totalServiceTime);
        arrivalOfProcesses(totalServiceTime);

        displayProcessTable(process);

    }
}