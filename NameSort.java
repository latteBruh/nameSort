import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NameSort
{
    public static void main(String[] args) throws IOException
    {

        long startTime = System.currentTimeMillis(); // run time

        // booleans used to test if sorter should keep sorting
        boolean runAgain = true;
        boolean didSwap = false;

        // booleans used to increase sort speed in doSort method
        boolean doIncrease = true;
        boolean doDecrease = false;

        String fileName = "nameList.txt"; // file name goes here

        File nameList = new File(fileName); // input file
        FileWriter outputFile = new FileWriter("outputFile.txt"); // output file

        if (!nameList.exists()) // tests if file exists
        {
            outputFile.write("Error. File name \"" + fileName + "\" could not be found.");
            outputFile.close();
            System.exit(404);
        }

        Scanner doesInitialLength = new Scanner(nameList); // indexes file
        Scanner doesReadNames = new Scanner(nameList); // reads file data

        int totalLines = 0; // initialize accumulator

        // determine total number of names
        while (doesInitialLength.hasNext())
        {
            doesInitialLength.nextLine(); // // //
            totalLines++;
        }

        if (totalLines <= 1) { // test for no input
            outputFile.write("Error, not enough, or no input was found.");
            outputFile.close();
            System.exit(0);
        }

        String[] holdsNames = new String[totalLines]; // master array

        // assign array names - NOT SORTED
        for (int a = 0; a < holdsNames.length; a++)
        {
            holdsNames[a] = doesReadNames.nextLine(); // // //
        }

        int currentIndex = 0; // initialize index

        int holdsNamesSize = holdsNames.length; // 2nd array size determined by this variable

        long sortTime = System.currentTimeMillis(); // sort time

        //noinspection ConstantConditions
        doSort(runAgain, didSwap, holdsNames, currentIndex, holdsNamesSize, doIncrease, doDecrease); // sorts names

        long sortEndTime = System.currentTimeMillis(); // sort time

        for (String holdsName : holdsNames)
        {
            //System.out.println(holdsName);
            outputFile.write(holdsName + "\n");
        }

        // close files here
        doesReadNames.close();
        outputFile.close();

        long endTime = System.currentTimeMillis(); // total time

        if (endTime < 1000)
        {
            System.out.println("Total Time: " + (endTime - startTime) + "s");
            System.out.println(" Sort Time: " + (sortEndTime - sortTime) + "s");
        }
        else
        {
            System.out.println("Total Time: " + ((endTime - startTime)/1000) + "s " + ((endTime - startTime)%1000) + "ms");
            System.out.println(" Sort Time: " + ((sortEndTime - sortTime)/1000) + "s " + ((sortEndTime - sortTime)%1000) + "ms");
        }

    }

    private static void doSort(boolean runAgain, boolean didSwap, String[] holdsNames, int currentIndex, int holdsNamesSize, boolean doIncrease, boolean doDecrease)
    {
        do {
            // name sorting algorithm

            if (doTestName(holdsNames[currentIndex], holdsNames[currentIndex + 1]))
            {
                doSwap(holdsNames, currentIndex);
                didSwap = true;
            }

            if (doIncrease)
                currentIndex++; // update
            if (doDecrease)
                currentIndex--;

            if (currentIndex == (holdsNamesSize - 1))
            {
                if (!didSwap)
                {
                    runAgain = false;
                }
                //currentIndex = 0;
                currentIndex--;
                didSwap = false;
                doDecrease = true;
                doIncrease = false;
            }
            if (currentIndex == 0)
            {
                doDecrease = false;
                doIncrease = true;
            }

        }
        while (runAgain); // end while
    }

    private static void doSwap(String[] holdsNames, int currentIndex)
    {
        String swapper;
        swapper = holdsNames[currentIndex];
        holdsNames[currentIndex] = holdsNames[currentIndex + 1];
        holdsNames[currentIndex + 1] = swapper;
    }

    private static boolean doTestName(String a, String b)
    {
        //System.out.println(a > b); // debug
        int localVar = a.compareToIgnoreCase(b);
        return localVar > 0;
    }
}
