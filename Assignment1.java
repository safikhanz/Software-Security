package softwareSecurity;

import java.io.*;
import java.security.*;
import java.util.*;


public class Assignment1 {
	public static LinkedList<String> userNameList = new LinkedList<String>();//LinkedList to store list of user name
	public static LinkedList<String> passwordList = new LinkedList<String>();//LinkedList to store list of password
	public static String FILE_NAME = "";// text file name
	
		
public static void main(String[] args) {
		Scanner userInput = null;// User Input Scanner
		
		try {
			userInput = new Scanner(System.in);// User Input Scanner
			//iterating for infinite time, it will terminated one user enters 3
			while (true) {
				System.out.println("Please Enter 1 to Create new Account; 2 to Check Existing Account; 3 to Quit");
				
				int userDecission = userInput.nextInt();// Getting input for user decision
				//If user decision is 1, the account creation started,for 2, existing account checking started and 3 to quite
				if (userDecission == 1) {
					System.out.println("Account Creation Started!");
					System.out.println ("Please choose how you want to save your credentials ?\n");
					System.out.println("1. A plaintext username password pair, stored in text in a file\n");
					System.out.println("2. A username and a hashed password, stored in some format in the file\n");
					System.out.println("3. A username, a salt and the result of the hashed (password + salt), stored in some format in the file\n");
					
					int fileDecision = userInput.nextInt();
					if(fileDecision == 1) {
						FILE_NAME = "Type1.txt";
						System.out.println("Enter User Name:");
						String userName = userInput.next();// Getting input for user name to create
						System.out.println("Enter Password:");
						String password = userInput.next();// Getting input for user password to create
						writeToFile(userName,password,FILE_NAME);//Writing account info into file
					}else if (fileDecision == 2) {
						FILE_NAME = "Type2.txt";
						System.out.println("Enter User Name:");
						String userName = userInput.next();// Getting input for user name to create
						System.out.println("Enter Password:");
						String password = userInput.next();// Getting input for user password to create
						writeToFile(userName,generateHash(password, "MD5"),FILE_NAME);//Writing account info into file
					}else if (fileDecision == 3) {
						FILE_NAME = "Type3.txt";
						String Salt = "abc"; // Salt for hashing the password
						System.out.println("Enter User Name:");
						String userName = userInput.next();// Getting input for user name to create
						System.out.println("Enter Password:");
						String password = userInput.next();// Getting input for user password to create
						writeToFileSalt(userName,generateHashSalt(password,"MD5", Salt),Salt ,FILE_NAME);//Writing account info into file
					}
				}else if (userDecission == 2) {
					System.out.println("Existing Account Checking!");
					System.out.println ("Please choose how you want to verify your credentials ?\n");
					System.out.println("1. A plaintext username password pair, stored in text in a file\n");
					System.out.println("2. A username and a hashed password, stored in some format in the file\n");
					System.out.println("3. A username, a salt and the result of the hashed (password + salt), stored in some format in the file\n");
					int fileDecision = userInput.nextInt();
					if(fileDecision == 1) {
						FILE_NAME = "Type1.txt";
						readAccountFile(FILE_NAME, passwordList);//Reading account info from file and updating LinkedList for both user name and password
						System.out.println("Enter User Name:");
						String userName = userInput.next();// Getting input for user names to check
						//checking weather user name entered is not blank and already exits
						if (userName!= null && !userName.equalsIgnoreCase("") && isUserNameExists(userName)) {
							System.out.println("That account exists.");
							System.out.println("Enter Password:");
							String password = userInput.next();// Getting input for user password to check
							//checking weather password entered is not blank and is matching with existing user name
							if (password!= null && !password.equalsIgnoreCase("") && isCorrectPassword(password) && (isCorrectPass(password) == isUsernameExists(userName))) {
								System.out.println("Password matched.");
							}else {
								System.out.println("Password does not match.");
							}
						}else {
							System.out.println("That account does not exist.");
						}
					}else if (fileDecision == 2) {
						FILE_NAME = "Type2.txt";
						readAccountFile(FILE_NAME, passwordList);//Reading account info from file and updating LinkedList for both user name and password
						System.out.println("Enter User Name:");
						String userName = userInput.next();// Getting input for user name to create
						//checking weather user name entered is not blank and already exits
						if (userName!= null && !userName.equalsIgnoreCase("") && isUserNameExists(userName)) {
							System.out.println("That account exists.");
							System.out.println("Enter Password:");
							String password = userInput.next();// Getting input for user password to check
							//checking weather password entered is not blank and is matching with existing user name
							password = generateHash(password,"MD5");
							if (password!= null && !password.equalsIgnoreCase("") && isCorrectPassword(password) && (isCorrectPass(password) == isUsernameExists(userName))) {
								System.out.println("Password matched.");
							}else {
								System.out.println("Password does not match.");
							}
						}else {
							System.out.println("That account does not exist.");
						}
					}else if (fileDecision == 3) {
						FILE_NAME = "Type3.txt";
						readAccountFile(FILE_NAME, passwordList);//Reading account info from file and updating LinkedList for both user name and password
						String Salt = "abc"; // Salt for hashing the password
						System.out.println("Enter User Name:");
						String userName = userInput.next();// Getting input for user name to create
						//checking weather user name entered is not blank and already exits
						if (userName!= null && !userName.equalsIgnoreCase("") && isUserNameExists(userName)) {
							System.out.println("That account exists.");
							System.out.println("Enter Password:");
							String password = userInput.next();// Getting input for user password to check
							password = generateHashSalt(password, "MD5", Salt);
							//checking weather password entered is not blank and is matching with existing user name
							if (password!= null && !password.equalsIgnoreCase("") && isCorrectPassword(password) && (isCorrectPass(password) == isUsernameExists(userName))) {
								System.out.println("Password matched.");
							}else {
								System.out.println("Password does not match.");
							}
						}else {
							System.out.println("That account does not exist.");
						}
					}
//					readAccountFile("Type1.txt", passwordList);//Reading account info from file and updating LinkedList for both user name and password
//					System.out.println("Enter User Name:");
//					String userName = userInput.next();// Getting input for user names to check
//					//checking weather user name entered is not blank and already exits
//					if (userName!= null && !userName.equalsIgnoreCase("") && isUserNameExists(userName)) {
//						System.out.println("That account exists.");
//						System.out.println("Enter Password:");
//						String password = userInput.next();// Getting input for user password to check
//						//checking weather password entered is not blank and is matching with existing user name
//						if (password!= null && !password.equalsIgnoreCase("") && isCorrectPassword(password) && (isCorrectPass(password) == isUsernameExists(userName))) {
//							System.out.println("Password matched.");
//						}else {
//							System.out.println("Password does not match.");
//						}
//					}else {
//						System.out.println("That account does not exist.");
//					}
				}else if (userDecission == 3) {
					System.out.println("Goodbye.");
				System.exit(0);//Terminating the while loop and program
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			} finally {
				try {
					if (userInput!= null) {
					userInput.close();//closing scanner
					}
				}catch (Exception e) {
					e.printStackTrace();
					}
				}
			}
			//This method is to check weather user name is exits in LinkedList
			public static boolean isUserNameExists(String userName) {
				boolean isExistsFlag = false;//user name exits flag, if exits set as true else false
				
				if (userNameList!= null) {
					//iterating user name LinkedList
					for (int i=0;i<userNameList.size();i++) {
						//Check each user name is matching with input user name
						if (userNameList.get(i).equals(userName)) {
							isExistsFlag = true;//If it is matched flag set as true
							break;//terminating the loop as already matched
						}
					}
				}
				//returning flag
				return isExistsFlag;
			}
			
			//This method is to check weather user name is exits in LinkedList
			public static int isUsernameExists(String userName) {
				boolean isExistsFlag = false;//user name exits flag, if exits set as true else false
				int indexC =0;
				if (userNameList!= null) {
					//iterating user name LinkedList
					for (int i=0;i<userNameList.size();i++) {
						//Check each user name is matching with input user name
						if (userNameList.get(i).equals(userName)) {
							isExistsFlag = true;//If it is matched flag set as true
							indexC =i;
							break;//terminating the loop as already matched
						}
					}
				}
				//returning flag
				return indexC;
			}
		//This method is to check weather password is exits in LinkedList
			public static boolean isCorrectPassword(String password) {
				boolean isCorrectFlag = false;//password checking flag, if matching set as true else false
				int indexP = 0;
				if (passwordList!= null) {
					//iterating password LinkedList
					for (int i=0;i<passwordList.size();i++) {
						//Check each password is matching with input password
						if (passwordList.get(i).equals(password)) {
							isCorrectFlag = true;//If it is matched flag set as true
							indexP =i;
							break;//terminating the loop as already matched
						}
					}
				}
				//returning flag
				return isCorrectFlag ;
			}
			
			public static int isCorrectPass(String password) {
				boolean isCorrectFlag = false;//password checking flag, if matching set as true else false
				int indexP = 0;
				if (passwordList!= null) {
					//iterating password LinkedList
					for (int i=0;i<passwordList.size();i++) {
						//Check each password is matching with input password
						if (passwordList.get(i).equals(password)) {
							isCorrectFlag = true;//If it is matched flag set as true
							indexP =i;
							break;//terminating the loop as already matched
						}
					}
				}
				//returning flag
				return indexP ;
			}
		//This method is to read account text file and update user name and password LinkedList
			public static void readAccountFile( String file, LinkedList<String> PassList) {
				Scanner inStream = null;
				try {
					userNameList.clear();//Initially clearing LinkedList for fresh update
					PassList.clear();//Initially clearing LinkedList for fresh update
					File fileInput = new File(file); //Creating new file
					inStream = new Scanner(fileInput); //Creating new Scanner in stream
					// Iterating each line of file
					if(inStream.hasNext()==true)
					{
						inStream.nextLine();
					}
					else
					{
					    System.out.println("Error: File is empty");
					    
					}

					while (inStream.hasNext()) {
						String line = inStream.nextLine();//getting new line from text file
						String[] lineArray = line.trim().split("\\t+");//Splitting each line by space to get string array
						String userName = lineArray[0];//Getting user name from first index of string array
						userNameList.add(userName);//Adding user name into LinkedList
						String password = lineArray[1];//Getting password from second index of string array
						PassList.add(password);//Adding password into LinkedList in same index
					}
				}catch (FileNotFoundException e) {
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if (inStream != null) {
						inStream.close();
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
		}
			
			
			public static void writeToFile(String userName, String password, String fileName) {
				FileWriter fw = null;
				BufferedWriter bw = null;
				try {
					 File file = new File(fileName);//Getting file object based on file name
					//if not exits, creating new one
					if (!file.exists()) {
						file.createNewFile();//creating new file
						fw = new FileWriter(fileName);// creating File Writer to write and append text file, true argument is to append new line
						bw = new BufferedWriter(fw);// creating Buffered Writer to write text file
						if(file.length() == 0){
							bw.write("Username  \t\t Password");
							bw.newLine();//going to next line
						}
						bw.append(userName + "\t\t\t" + password);//adding new line of user name and password separated by space
						bw.newLine();//going to next line
						
						bw.flush();// Flushing Buffered Writer
						fw.flush();// Flushing File Writer
					}
					else{
					fw = new FileWriter(fileName, true);// creating File Writer to write and append text file, true argument is to append new line
					bw = new BufferedWriter(fw);// creating Buffered Writer to write text file
					if(file.length() == 0){
						bw.write("Username \t Password");
						bw.newLine();//going to next line
					}
					bw.append(userName + "\t\t\t" +password);//adding new line of user name and password separated by space
					bw.newLine();//going to next line
					
					bw.flush();// Flushing Buffered Writer
					fw.flush();// Flushing File Writer
					}
				}catch (IOException ioe) {
					ioe.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if (fw != null) {
						fw.close();// closing File Writer
						}
						if (bw != null) {
						bw.close();// closing Buffered Writer
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			public static void writeToFileSalt(String userName, String password, String Salt, String fileName) {
				FileWriter fw = null;
				BufferedWriter bw = null;
				try {
					 File file = new File(fileName);//Getting file object based on file name
					//if not exits, creating new one
					if (!file.exists()) {
						file.createNewFile();//creating new file
						fw = new FileWriter(fileName);// creating File Writer to write and append text file, true argument is to append new line
						bw = new BufferedWriter(fw);// creating Buffered Writer to write text file
						if(file.length() == 0){
							bw.write("Username \tPassword \t\t\t\t\t Salt");
							bw.newLine();//going to next line
						}
						bw.append(userName + "\t\t" + password + "\t\t" + Salt);//adding new line of user name and password separated by space
						bw.newLine();//going to next line
						
						bw.flush();// Flushing Buffered Writer
						fw.flush();// Flushing File Writer
					}
					else{
					fw = new FileWriter(fileName, true);// creating File Writer to write and append text file, true argument is to append new line
					bw = new BufferedWriter(fw);// creating Buffered Writer to write text file
					if(file.length() == 0){
						bw.write("Username \tPassword \t\t\t\t\t Salt");
						bw.newLine();//going to next line
					}
					bw.append(userName + "\t\t" + password + "\t\t" + Salt);//adding new line of user name and password separated by space
					bw.newLine();//going to next line
					
					bw.flush();// Flushing Buffered Writer
					fw.flush();// Flushing File Writer
					}
				}catch (IOException ioe) {
					ioe.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if (fw != null) {
						fw.close();// closing File Writer
						}
						if (bw != null) {
						bw.close();// closing Buffered Writer
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			public static String generateHash(String data, String algorithm) throws NoSuchAlgorithmException {
				MessageDigest digest = MessageDigest.getInstance(algorithm);
				digest.reset();
				byte [] hash = digest.digest(data.getBytes());
				return bytesToStringHex(hash);
			}
			
			public static String generateHashLink(LinkedList<String> data, String algorithm) throws NoSuchAlgorithmException {
				MessageDigest digest = MessageDigest.getInstance(algorithm);
				byte [] hash = null;
				digest.reset();
				for(int i=0; i<data.size(); i++) {
				 hash = digest.digest((data.get(i)).getBytes());
				}
				return bytesToStringHex(hash);
			}
			public static String generateHashSalt(String data, String algorithm, String Salt) throws NoSuchAlgorithmException {
				MessageDigest digest = MessageDigest.getInstance(algorithm);
				digest.reset();
				digest.update(Salt.getBytes());
				byte [] hash = digest.digest(data.getBytes());
				return bytesToStringHex(hash);
			}
			
			public final static char[] hexArray = "0123456789ABCDEF".toCharArray();
			
			public static String bytesToStringHex(byte [] bytes) {
				char [] hexChars = new char [bytes.length * 2];
				for (int j= 0; j< bytes.length; j++) {
					int v = bytes[j] & 0xFF;
					hexChars[j* 2]= hexArray[v >>> 4];
					hexChars[j * 2 + 1] = hexArray[v & 0x0F];
					
				}
				return new String (hexChars);
			}
}
