import java.util.*;

public class FCFS {

    static Scanner sc = new Scanner(System.in);
    static int numberOfProcesses = 0;
    static int totalServiceTime = 0;

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
        sequenceOfArrivedProcesses();
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

    public static void sequenceOfArrivedProcesses() {

        int x = 0;
        arrivalOfProcesses = new int[numberOfProcesses][totalServiceTime];
        while (x < numberOfProcesses) {
            for (int j = process[x][1]; j < (process[x][1] + process[x][2]); j++) {
                // if (j > 0 && x > 0) {
                // if (arrivalOfProcesses[x - 1][j] > 0)
                // j++;
                // } else {
                arrivalOfProcesses[x][j] = process[x][0];
                // }
            }
            x++;
        }
        arrivalOfProcesses();
    }

    public static void calculateTotalTime() {
        System.out.println("Calculating the Total Time of each Process");

        for (int i = 0; i < arrivalOfProcesses.length; i++) {
            int count = arrivalOfProcesses[0].length * i;
            for (int j = 0; j < arrivalOfProcesses[0].length; j++) {
                if (arrivalOfProcesses[i][j] > 0) {
                    count = j;
                }
            }
            process[i][5] = count - process[i][1] + 1;
            process[i][6] = 1;
        }
    }

    public static void displayProcessTable() {
        System.out.println("The Process and its timings are as follows\n");
        System.out.println("Process No | Arrival | Service | Waiting | Response | Total Time | C. S. ");
        for (int i = 0; i < process.length; i++) {
            System.out.println("\tP" + process[i][0] + "\t" + process[i][1] + "\t" + process[i][2] + "\t"
                    + process[i][3] + "\t" + process[i][4] + "\t" + process[i][5] + "\t" + process[i][6]);
        }
    }

    public static void arrivalOfProcesses() {
        System.out.println("The Process and its Arrival are as follows\n");
        for (int i = 0; i < totalServiceTime; i++)
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
        totalServiceTime = sumOfArrivalTime();
        System.out.println("Total Service Time " + totalServiceTime);

        sortProcesses();
        calculateTotalTime();

        displayProcessTable();

    }
}