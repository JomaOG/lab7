/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        // for(int hour = 0; hour < hourCounts.length; hour++) {
            // System.out.println(hour + ": " + hourCounts[hour]);
        // }
        int hour = 0;
        while(hour < hourCounts.length) {
            System.out.println(hour + ": " +hourCounts[hour]);
            hour = hour + 1;
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /** * Print all the values in the marks arra that are greater than mean.
     *  @param marks An array of mark values
     *  @param mean The mean (average) mark
     */
    public void printGreater(double[] marks, double mean) {
        for(int index = 0; index < marks.length; index++) {
            if(marks[index] > mean) {
                System.out.println(marks[index]);
            }
        }
    }
    
    /** * Return the number of accesses recorded in the log file. */ 
        public int numberOfAccesses() { 
        int total = 0;
        // Add the value in each element of hourCounts to // total. ...
        for(int index = 0; index < hourCounts.length; index++) {
            if(hourCounts[index] != 0) {
                total += hourCounts[index];
            }
        }
        return total; 
    }
    
    public int busiestHour() {
        int busiest = 0;
        int hourBusiest = 0;
        // Replace value busiest value and index if busiest is higher
        // Replace value busiest value and index if busiest is higher
        for(int index = 0; index < hourCounts.length; index++) {
            if(hourCounts[index] > busiest) {
                busiest = hourCounts[index];
                hourBusiest = index;
            }
        }
        return hourBusiest;
    }
    
    public int quietestHour() {
        int quietest = hourCounts[0];
        int hourQuietest = 0;
        // Replace value busiest value and index if busiest is higher
        // Replace value busiest value and index if busiest is higher
        for(int index = 0; index < hourCounts.length; index++) {
            if(hourCounts[index] < quietest) {
                quietest = hourCounts[index];
                hourQuietest = index;
            }
        }
        return hourQuietest;
    }
    
    public int busiestTwo() {
        int busiestTwo = 0;
        int hourBusiestTwo = 0;
        for(int index = 0; index < hourCounts.length-1; index++) {
            if(hourCounts[index]+hourCounts[index+1] > busiestTwo) {
                busiestTwo = hourCounts[index]+hourCounts[index+1];
                hourBusiestTwo = index;
            }
        }
        return hourBusiestTwo;
    }
}

