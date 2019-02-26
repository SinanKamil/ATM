/*
 * Sinan Kamil
 * Nov 7, 2018
 * Account.java
 * a password protected account for a bank, includes name, pin, balance and time
 * no dependencies
 * Prof. Steck's code, and robert the tutor for the math formula
 */

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Random;

public class Account
{
    //class variables
    private static Scanner systemIn = new Scanner(System.in);

    //instance variables
    private String firstName;
    private String lastName;
    private String pin;
    private double balance;
    private LocalDateTime dateCreated;


    /* Account constructor default
    *
    */
    Account()
    {
        //call the other constructor but use a value of 0 for the balance and null for name
        //basically the "this" is being replaced by the current class which is Account
        //yay for reusing code!
        this(null,null,0.0);
    }

    /* Account constructor with no initial balance
    *
    */
    Account(String firstName, String lastName)
    {
        //call the other constructor but use a value of 0 for the balance
        //basically the "this" is being replaced by the current class which is Account
        //yay for reusing code!
        this(firstName, lastName, 0.0);
    }

    /* Account constructor with an initial balance
    *
    */
    Account(String firstName, String lastName, double initialBalance)
    {
        //set first and last name
        this.firstName = firstName;
        this.lastName = lastName;

        //set balance to initial amount
        balance = initialBalance;

        //set date created to today
        dateCreated = LocalDateTime.now();

        System.out.println("Your bank account has been created!!");
        System.out.printf("Your current balance: $%.2f%n",balance);
        System.out.println("Date/Time created: " + dateCreated);

        //generate a new pin number for this account
        generateNewPin();

    }

    /* method checkBalance() prints account balance if pin is entered correctly
    *
    */
    public void checkBalance()
    {
        if(verifyPin())
            System.out.printf("Your current balance: $%.2f%n",balance);
        else
            System.out.println("Unable to view balance.");
    }

    /* method generateNewPin()  creates a new random 4 digit pin number
    *                           sets pin and outputs to screen
    */
    private void generateNewPin()
    {
        //create secureRandom object
        Random randomGenerator = new Random();

        //generate four random numbers 0-9 converted to a String (maybe a loop would be better??)
        String num1 = Integer.toString(randomGenerator.nextInt(10));
        String num2 = Integer.toString(randomGenerator.nextInt(10));
        String num3 = Integer.toString(randomGenerator.nextInt(10));
        String num4 = Integer.toString(randomGenerator.nextInt(10));

        //combine (concatenate) all four number Strings into 1 String
        pin = num1 + num2 + num3 + num4;

        //output
        System.out.printf("Your new pin number is %s.%n",pin);

    }

    /* method getFirstName() returns the first name of the current account
    *
    */
    public String getFirstName()
    {
        return firstName;
    }

    /* method getLastName() returns the last name of the current account
    *
    */
    public String getLastName()
    {
        return lastName;
    }

    /* method printInfo()    prints account name
    */
    public void printInfo()
    {
        System.out.printf("Account: %s %s%n",firstName,lastName);
    }

    /* method verifyPin()   asks user to enter pin number
    *                       checks entry with actual pin
    *                       returns true if correct, false otherwise
    *                       user has three tries to enter correctly
    */
    private boolean verifyPin()
    {
        int maxTimes = 3;
        int times = 0;

        //loops until pin entered correctly or max tries reached
        do
        {
            System.out.print("Enter your pin number: ");
            if(systemIn.next().equals(pin))
                return true;
            else
            {
                times++;
                System.out.print("Invalid. ");
            }
        }while(times < maxTimes);

        System.out.println("You have entered your pin incorrectly too many times.");
        return false;
    }

    public void withdrawal(){
        if (verifyPin() == true){//makes sure that pin was entered correctly
            System.out.println("Please enter the amount you want to withdrawal!");//prints the statement
            int money = systemIn.nextInt();
            while (money < 0 || money > balance){//makes sure the amount is bigger than 0 and that them money requested is less than the balance
                System.out.println("Amount Invaild");//prints invaild if the amount bigger or the money needed is less than the balance
                System.out.println("Enter (1) try again OR (2) Main Menu! ");//picks one to either try again or back to main menu
                money = systemIn.nextInt();//allows to pick one or two
                if (money == 1) {//if choice one
                    System.out.println("Please enter the amount you want to withdrawal!");//it will print this statement
                    money = systemIn.nextInt();//allows them to enter
                }
                else if (money == 2) {//if choices two
                   return;//returns to the main menu
                }
                money = 0;//set money equals 0
            }
            balance = balance - money;//then the math formula if money withdrawal is subtracted from the to balance

        }


    }

    public void deposite(){
        if(verifyPin() == true){//makes sure that pin was entered correctly

            System.out.println("Please enter the amount you want to Deposit!");//prints the statement
            int money = systemIn.nextInt();//allows them to enter a number
            while (money < 0){//makes sure the amount is bigger than 0
                System.out.println("Amount Invaild");//prints invaild if not bigger than zero
                System.out.println("Enter (1) try again OR (2) Main Menu! ");//picks one to either try again or back to main menu
                money = systemIn.nextInt();//allows to pick one or two
                if (money == 1) {//if choice one
                    System.out.println("Please enter the amount you want to Deposit!");//it will print this statement
                    money = systemIn.nextInt();//allows them to enter
                }
                else if (money == 2) {//if choices two
                    return;//returns to the main menu
                }
                money = 0;//set money equals 0
            }
            balance = balance + money;//then the math formula if money deposited then add to balance
        }
    }


}
