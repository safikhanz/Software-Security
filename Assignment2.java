package softwareSecurity;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Assignment2 extends Assignment1 {

	public static final String Username = "user";
	public static final String Password = "abcdefghijklmnopqrstuvwxyz";
	public static final String Salt = "abc";
	
	public static String generateID(int i) {
		StringBuilder result = new StringBuilder()	;
		result.append(Username + i);
		return result.toString();
	}
	
	public static String generateRandomString(){
        int size = 3 + (int)(Math.random()* ((8-3)+ 1));
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
    private static int  getRandomNumber() {
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
		

		for (int i=0; i<10;i++) {
			try {
				
				Assignment1.writeToFile(generateID(i),generateRandomString(),"Type1.txt");
				Assignment1.writeToFile(generateID(i),Assignment1.generateHash(generateRandomString(), "MD5"),"Type2.txt");
				Assignment1.writeToFileSalt(generateID(i),Assignment1.generateHashSalt(generateRandomString(), "MD5", Salt), Salt, "Type3.txt");
			} 
			
			catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
