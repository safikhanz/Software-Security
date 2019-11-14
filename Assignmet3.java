package softwareSecurity;


import java.security.*;
import java.util.*;

public class Assignmet3 extends Assignment2 {

	public static LinkedList<String> passList = new LinkedList<String>();//LinkedList to store list of password
	
	
	public static void main(String[] args) 
	{
		Scanner userInput = null;// User Input Scanner
		userInput = new Scanner(System.in);// User Input Scanner
		System.out.println("Please Enter 1 for file type2.txt ; 2 for file type3.txt ; 3. for typr1.txt");
		int userDecision = userInput.nextInt();
		String filename ="";
		char[] charset = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		System.out.println("Please enter the maximum size of the password");
		int userdecision = userInput.nextInt();
		BruteForce bf = new BruteForce(charset, userdecision);
		long start;
		long end;
		
		if (userDecision ==1) {
			filename ="type2.txt";
			Assignment1.readAccountFile(filename, passList);
			
			
			start = System.nanoTime();


			String attempt = bf.toString();
		
			while (true) 
			{
			
				if (isCorrectPassword(attempt, passList))  
				{
					System.out.println("Password Found: " + attempt);
					break;
				
				}
			
				attempt = bf.toString();
				try {
					attempt = Assignment1.generateHash(attempt, "MD5");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("" + attempt);
				bf.increment();

			}
			
			end = System.nanoTime();
			System.out.println("Processing Time: " + ((end - start)/1000000000) + " Seconds " + ((end - start)/1000000) + " Millseconds");
		}
		else if (userDecision == 2){
			filename ="type3.txt";
			Assignment1.readAccountFile(filename, passList);
			System.out.println("Please enter the salt ");	
			String salt = userInput.next();
			
			start = System.nanoTime();


			String attempt = bf.toString();
		
			while (true) 
			{
			
				if (isCorrectPassword(attempt, passList))  
				{
					System.out.println("Password Found: " + attempt);
					break;
				
				}
			
				attempt = bf.toString();
				try {
					attempt = Assignment1.generateHashSalt(attempt, "MD5", salt );
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("" + attempt);
				bf.increment();

			}
			
			end = System.nanoTime();
			System.out.println("Processing Time: " + ((end - start)/1000000000) + " Seconds " + ((end - start)/1000000) + " Millseconds");
			
		}else if (userDecision ==3) {
			filename = "type1.txt";
			Assignment1.readAccountFile(filename, passList);
			start = System.nanoTime();


			String attempt = bf.toString();
			while (true) 
			{
			
				if (isCorrectPassword(attempt, passList))  
				{
					System.out.println("Password Found: " + attempt);
					break;
				
				}
			
				attempt = bf.toString();
				
				System.out.println("" + attempt);
				bf.increment();

			}
			
			end = System.nanoTime();
			System.out.println("Processing Time: " + ((end - start)/1000000000) + " Seconds " + ((end - start)/1000000) + " Millseconds");
		}
	
		if (userInput!= null) {
			userInput.close();//closing scanner
			}
	
	
	}
	
	public static boolean isCorrectPassword(String password, LinkedList<String> passWordList) {
		boolean isCorrectFlag = false;//password checking flag, if matching set as true else false
		
		if (passWordList!= null) {
			//iterating password LinkedList
			for (int i=0;i<passWordList.size();i++) {
				//Check each password is matching with input password
				if (passWordList.get(i).equals(password)) {
					isCorrectFlag = true;//If it is matched flag set as true
					
					break;//terminating the loop as already matched
				}
			}
		}
		//returning flag
		return isCorrectFlag ;
	}
	
	
}