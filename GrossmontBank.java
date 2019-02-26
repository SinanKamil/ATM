/*
 * Sinan Kamil
 * Nov 7, 2018
 * GrossmontBank.java
 * A bank account class that can hold accounts and manipulate (like a bank teller or atm)
 * Dependencies: Account.java
 * Credit: Prof. Steck's code, and robert the tutor for the math formula
 */

import java.util.Scanner;

public class GrossmontBank
{
    //class variables (global - accessible throughout this class, methods and all)

    //scanner object to be used throughout
    private static Scanner input = new Scanner(System.in);

    //an array of blank bank accounts
    private static final int MAX_ACCOUNTS = 50;
    private static Account[] accounts = new Account[MAX_ACCOUNTS];

    //total accounts created so far
    private static int totalAccounts = 0;

    //main class mimics a bank teller
    public static void main(String[] args)
    {
        char choice;

        //loop until 'Q' is entered
        do
        {
            choice = getChoice(); //getChoice() will only return with a C, B, W, D or Q
            if(choice != 'Q')
            {
                switch(choice)
                {
                    case 'C':   createAccount();
                        break;

                    case 'B':   checkBalance();
                        break;

                    case 'W':  withdrawal();
                        break;

                    case 'D':  deposit();
                        break;
                }
            }
        }while(choice != 'Q');

        closeBank(); //outputs all account holders and 'closes' the bank

    }

    /*method checkBalance  calls method findAccount()
    *                      if account exists, calls Account method checkBalance()
    */
    private static void checkBalance()
    {
        int accountIndex = findAccount();
        //findAccount() returns index of account if found, -1 if not found
        if(accountIndex != -1)
        {
            accounts[accountIndex].checkBalance();
        }
        else
        {
            System.out.println("Account does not exist");
        }
    }

    /*method checkIfExists  determines if account holder already exists
    *                      returns true if account holder exists, false otherwise
    */
    private static boolean checkIfExists(String firstName, String lastName)
    {
        //loops through account array to see if account name already exists
        for(int i = 0; i < totalAccounts;i++)
        {
            //compares the names, ignoring upper/lower
            if(accounts[i].getFirstName().equalsIgnoreCase(firstName)
                    && accounts[i].getLastName().equalsIgnoreCase(lastName))
            {
                System.out.println("Account holder already exists.  Please verify and re-enter. ");
                return true;
            }
        }
        return false;
    }

    /*method closeBank  prints out closing statement
    *                   prints out list of all account holders
    */
    private static void closeBank()
    {
        System.out.println("Closing the follow accounts:");

        for(int i = 0; i < totalAccounts;i++)
        {
            //printing an account object invokes the Account class method toString()
            //prints first and last name only
            accounts[i].printInfo();
        }
        System.out.println("Grossmont Bank is officially closed.");
    }

    /*method createAccount creates a single bank account
   *                       checks to ensure account holder does not already exist
   *                       creates a new account and adds to the array
   */
    private static void createAccount()
    {
        String first, last, initial;
        boolean exists = false;

        //only create a new account if MAX_ACCOUNTS has not been reached
        if(totalAccounts < MAX_ACCOUNTS )
        {
            //loop until a new account name has been entered, can't create an account with an existing name
            do
            {
                System.out.print("Enter your first name: ");
                first = input.next();
                System.out.print("Enter your last name: ");
                last = input.next();
                exists = checkIfExists(first,last);
            }while(exists == true);

            System.out.print("Will you be making an initial deposit? Enter Yes or No: ");
            initial = input.next();

            //if no initial deposit, call 2 parameter constructor, otherwise call 3 param one
            if(initial.equalsIgnoreCase("No"))
            {
                accounts[totalAccounts] = new Account(first,last);
            }
            else
            {
                System.out.print("Enter initial deposit amount: ");
                accounts[totalAccounts] = new Account(first,last, input.nextDouble());
            }

            //increment totalAccounts created (used throughout program)
            totalAccounts++;
        }
        else
        {
            System.out.println("Maximum number of accounts has been reached. ");
        }
    }

    /*method deposit  calls method findAccount()
     *                if account exists, calls Account method deposit()
     */
    private static void deposit()
    {
        int accountIndex = findAccount();
        //findAccount() returns index of account if found, -1 if not found
        if(accountIndex != -1)
        {
            accounts[accountIndex].deposite(); // takes the code from Account.java using a method called deposite();
            //use checkBalance() as a guide for how to do this!
        }
        else
        {
            System.out.println("Account does not exist");
        }
    }

    /*method findAccount   asks for first and last name
    *                      searchs for account holder in array
    *                      if exists, returns array index of this account
    *                      if doesn't exist, returns '-1'
    *                      called from checkBalance()
    */
    private static int findAccount()
    {
        String first, last;

        System.out.print("Enter first name: ");
        first = input.next();
        System.out.print("Enter last name: ");
        last = input.next();

        //loops through account array
        for(int i = 0; i < totalAccounts;i++)
        {
            //compares the names, ignoring upper/lower
            if(accounts[i].getFirstName().equalsIgnoreCase(first)
                    && accounts[i].getLastName().equalsIgnoreCase(last))
            {
                return i; //returns the index of the account
            }
        }
        return -1; //if account not found

    }


    /* method getChoice()   outputs options
   *                       inputs choice from user and validates
   *                       returns choice char
   */
    private static char getChoice()
    {
        char choice;

        //output menu options
        System.out.println();
        System.out.println("Welcome to Grossmont Bank.  Choose from the following options: ");
        System.out.println("    C - create new account");
        System.out.println("    B - check your balance");
        System.out.println("    D - deposit");
        System.out.println("    W - withdrawal");
        System.out.println("    Q - quit");

        //loop until a valid input is entered
        do
        {
            System.out.print("Enter choice: ");
            choice = input.next().charAt(0);
            //if choice is one of the options, return it.  Otherwise keep looping
            if(choice == 'C' || choice == 'B' || choice == 'D' || choice == 'W' || choice == 'Q')
                return choice;
            else
            {
                System.out.println("Invalid choice.  Ensure a capital letter. Please re-enter.");
                choice = '?';
            }
        }while(choice == '?');

        return choice;  //will never get here, but required to have a return statement to compile
    }

    /*method withdrawal  calls method findAccount()
     *                      if account exists, calls Account method withdrawal()
     */
    private static void withdrawal()
    {
        int accountIndex = findAccount();
        //findAccount() returns index of account if found, -1 if not found
        if(accountIndex != -1)
        {
            accounts[accountIndex].withdrawal();// takes the code from Account.java using a method called withdrawal();
            //use checkBalance() as a guide for how to do this!
        }
        else
        {
            System.out.println("Account does not exist");
        }
    }

}
