// importing of the libraries to be used in the code
import java.util.Scanner;

//  main class
public class Main {
	// variable that stores the categorisation success 
	private static double success = 0;
	private static final int fileColumns = 64; // will be used to represent the columns in the file
	private static final int fileRows = 2810; // will be used to represent the rows in the file
	// eucleanDistance method
	public static void eucleanDistance(int [][] trainData, int [][] testData, int [][] collectTestData, int [] getTrainCategoriser ) {
		double minimunDistance=0; // variable storing the minimum distance after comparing one row of the train data with all the rows of the test data
		int[] getTestCategoriser = new int [fileRows]; // array storing the last row of the test data which is the data categoriser
		for (int trainCount=0; trainCount<trainData.length; trainCount++) { // loop through the train data
			int minimunDistancePosition=0; // the position (row) of the column with the minimum distance
			for (int testCount=0; testCount<testData.length; testCount++) { // loop through the test data
				double Sum = 0; // variable Sum to get the sum of the test data to be used to calculate the distance
				double Distance = 0; // variable Distance to store the distance
				// loop calculating the distance of column in the test data and column in the train data
				for (int count=0; count<64; count++) { // loop to go through each column in the test data
					Sum = Sum + Math.pow((trainData[trainCount][count]-testData[testCount][count]),2); // 
				}
				Distance = Math.sqrt(Sum); // square root of the Sum is the distance
				if (testCount ==0) { // if it is the first row in the test data 
					minimunDistance = Distance; // set the minimunDistance to be the Distance
				}
				else if(Distance<minimunDistance) { //else if the distance is less than the minimunDistance
					minimunDistance = Distance; // set the minimunDistance to be the Distance
					minimunDistancePosition = testCount; // set the minimunDistancePosition variable with the position of the data in the array (row number)
				}
			}// close the loop that goes through the test data
			// set array get Test Categoriser with the last categoriser of every coordinates in the test data
			//with the nearest distance as the coordinates in the train data
			getTestCategoriser[trainCount] = collectTestData[minimunDistancePosition][fileColumns];
			// if the getTestCategoriser is the same as the categoriser element in the train data 
			// then that data can be classified to be in the same category
			// hence incrementing success
			if (getTestCategoriser[trainCount] == getTrainCategoriser[trainCount]) {
				success += 1;
			} // end of the if loop
		} //end of the loop through the train data
	} // end of the method
// check the distances when you get back

	public static void main(String[] args) { // main method 
		final int fileLocationCount=1;
		String [] fileNameArray = new String[2]; // array with the file locations to be read
		Reader reader = new Reader(); // calling the class reader
	
		Scanner input = new Scanner(System.in); // input prompt
		int userInput = fileColumns; // setting the value of userInput to a high number
		int fileLocation = fileLocationCount; // variable storing the file location
		
		boolean codeRun = true; // boolean variable to determine if the code runs or not
		
		while (codeRun==true) { 
			if (userInput== 1){ // if the user has entered on or chose the choice 1 which means he wants the code to run then
				for (int count = 0; count<2; count++) { // loop to run booth folds with files interchanging
					fileNameArray = reader.foldChoose(fileLocation); // calling the method fold choose to return which fold to use
					int [] getTrainCategoriser = new int [fileRows]; // initialise the getTrainCategoriser variable
					int [][] collectTrainData = reader.readFile(fileNameArray[0]);//  read train data
					int [][] collectTestData =  reader.readFile(fileNameArray[1]); // read test data
					int [][] trainData = new int[collectTrainData.length][fileColumns];  // initialise the array to store the train data without the last categoriser
					int [][] testData = new int[collectTestData.length][fileColumns];  // initialise the array to store the test data without the last categoriser
					
					for (int row=0; row<fileRows; row++) { 
						for (int column=0; column<fileColumns; column++) {
							trainData[row][column] = collectTrainData[row][column]; // get the train data without the last categoriser
							testData[row][column] = collectTestData[row][column]; //get the test data without the last categoriser
						}
						getTrainCategoriser[row] = collectTrainData[row][fileColumns]; //  get the categoriser of the training data
					}
					eucleanDistance(trainData,testData,collectTestData, getTrainCategoriser); // call the eucleanDistance function
					fileLocation +=1; // increment the fileLication
				 }
				 codeRun = false; // stop the code from running after the second fold
			 }
			 else { // if the user has not entered 1 
				 // prompt the user to enter a choice again
				  System.out.print("Welcome please choose from the following list:\n"+
						"1. Run the code\n" +
						"Enter your choice:");
		          userInput = input.nextInt(); // read the user input
			}
		}
		double accuracy = ((success/5620) * 100); // find the accuracy by dividing the number of success 
		//with the total number of rows in the two files which in these case is 5620
		System.out.println("The accuracy of categorizing the train data by the test data and vice versa is:"+accuracy); // print out the accuracy
	}
}