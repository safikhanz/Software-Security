package softwareSecurity;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Random;

public class Assignment2 extends Assignment1 {
	public static LinkedList<String> passList = new LinkedList<String>();//LinkedList to store list of password

	public static final int max = 8;
	public static final String Username = "user";
	public static final String Password = "abcdefghijklmnopqrstuvwxyz";
	public static final String Salt = "abc";
	
	public static String generateID(int i) {
		StringBuilder result = new StringBuilder()	;
		result.append(Username + i);
		return result.toString();
	}
	
	public static String generateRandomString(int max){
        int size = 3 + (int)(Math.random()* ((max-3)+ 1));
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<size; i++){
            int number = getRandomNumber();
            char ch = Password.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }
     
    /**
     * This method generates random numbers
     * @return int
     */
    public static int  getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(Password.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String password ="";
//		Assignment1.readAccountFile("Type1.txt", passList);
//		for(int i =0; i<passList.size(); i++) {
//		System.out.println(passList);
//		}
			try {
				for (int i=0; i<1000;i++) {
				Assignment1.writeToFile(generateID(i),generateRandomString(max),"Type1.txt");
				
				//Assignment1.writeToFile(generateID(i),Assignment1.generateHash(generateRandomString(max), "MD5"),"Type2.txt");
				//Assignment1.writeToFileSalt(generateID(i),Assignment1.generateHashSalt(generateRandomString(max), "MD5", Salt), Salt, "Type3.txt");
			
				}
				Assignment1.readAccountFile("Type1.txt", passList);
				
				for (int i=0; i<1000;i++) {
					password = passList.get(i);
					Assignment1.writeToFile(generateID(i),Assignment1.generateHash(password, "MD5"),"Type2.txt");
					Assignment1.writeToFileSalt(generateID(i),Assignment1.generateHashSalt(password, "MD5", Salt), Salt, "Type3.txt");
					password ="";
				}
				
			}
			catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}

}
