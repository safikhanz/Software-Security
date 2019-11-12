# Software-Security
Task 1 
Program that allows a user to create an account and then authenticate whether or not they have an account. 
However, the trick is that this is done through three different password files. 
So when the program starts, it should ask the user which one of those they should like to either log in or authenticate through. 
Once they have done that, your program will ask them if they want to make an account or log in. If they want to log in, your program should accept their username and password and verify that using the appropriate password file. 
If they want to create an account, the program should create the appropriate data in the appropriate password file. 
Note the primary purpose of this program is to allow testing of parts 2 and 3. 
For ease of cracking later, I want your passwords to be restricted to only lowercase letters (a..z) of configurable length. The length of the salt should be one byte for this project. In practice, salt should be of much greater size than this. We have restricted the size for this project in order to make cracking it easier.
Usernames should be restricted to 8 alphanumeric characters.

The three password files should be as follows:
A plaintext username password pair, stored in text in a file
A username and a hashed password, stored in some format in the file
A username, a salt and the result of the hashed (password + salt), stored in some format in the file


Task2 

Extend your previous program such that you can get it to, when given bounds on password size (for example, all passwords between length 3 and 8) and a number of accounts (100 for example), will then create usernames and passwords for 100 accounts in the specified ranges for each of the 3 password files. 
For this purpose it is probably useful for this program to create usernames of a particular format (user00001) for example.

Task3 

In this task we are replicating a situation where you have somehow gotten ahold of a password file and you wish to crack it. You can assume that you have full knowledge of the format of the particular file you are working with. This program should take as an option one of the password files above (only 2 or 3, 1 is trivial), as well as a maximum password size. It should then try to figure out one of the passwords in that file by brute force. That is, if ran with a password file of type2, it would attempt to find a password that hashed to one of the passwords of a username in the file, trying all combinations of passwords up to some specified length. If called with a password file of type3, it would attempt to find a password that when hashed with the salt would match one of the usernames.

Note that trying to crack a file of type 2, you can compare your hash against all of the usernames in the file. This you can do by loading this file into some structure. As you are searching for a particular match, this is done much more efficiently if it is stored in a binary search tree or similar structure. For files of type 3, whether your need to do something like this or not is something I leave up to you to consider.
