// importing of the libraries to be used in the code
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.DataInputStream;  

// declare class reader
public class Reader { 
	// declare variables
	private static final int fileColumns = 64; // will be used to represent the columns in the file
	static int[][] readFile(String fileName) { // method readFile that returns an array of data read and stored in an array
		// variable storing a single line in the data read
		String strline = null;
		ArrayList<String> readArray = new ArrayList<String>(); // declare an empty array of data type string
		
		try {
			// create object fileToUse which reads file specified by the variable fileName. 
			FileInputStream fileToUse = new FileInputStream(fileName); 
			// create an object that reads primitive data types from the file referenced by the fileToUse object
			DataInputStream fileRead = new DataInputStream(fileToUse); 
			// InputStreamReader object is used to convert the bytes of data from the file into characters, 
			// and the BufferedReader object is used to read the characters from the InputStreamReader.
			BufferedReader buffer = new BufferedReader(new InputStreamReader(fileRead));
			// loop that reads line by line from the buffer and stores it in the readArr array
			while ((strline = buffer.readLine()) != null) {
				readArray.add(strline);
			}
			buffer.close(); // close the file
		} catch (Exception error) { // error handling section
            error.printStackTrace(System.out); // print out the error
        }
		//splitting the data and storing individual rows and columns
		int[][] data = new int[readArray.size()][65]; // array to store the data
        for (int row = 0; row < readArray.size(); row++) {
        	String lineString = readArray.get(row); // get the string row in the readArray Array at position row
            String[] indivLineValue = lineString.split(","); // split the data by comma
            for (int column = 0; column < indivLineValue.length; column++) {
                data[row][column] = Integer.valueOf(indivLineValue[column]); // store the data in the data array
            }
           
        }
        
		return data; // return the data array
	}
	
	// method choosing whether you would like to use the first fold or the second
	String [] foldChoose(int userInput) { 
		// array fileLocationArray to store the location of the files
		String[] fileLocationArray = new String[2];
		switch (userInput) {
    		case 1: // if the user chooses 1 use the first fold
    		//where the fist data is the training data and the second is the test data
    			fileLocationArray[0] = "C:/temp/cw2DataSet1.csv";
    			fileLocationArray[1] = "C:/temp/cw2DataSet2.csv";    
    			break;
    		case 2:
    			// if the user chooses 2 use the second fold
    			// interchanging the data from the first fold
    			fileLocationArray[0] = "C:/temp/cw2DataSet2.csv";
    			fileLocationArray[1] = "C:/temp/cw2DataSet1.csv";
    			break;
		}
    			
		// return the array fileLocationName with the order of the files 
		// indicating which fold has been chosen
		return fileLocationArray;	
	}
}
