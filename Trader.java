/* Tung Ho, Lin Project 1 */

/* a class that represents an entity that is allowed to trade strocks */
public class Trader {
  
  /* the name of the trader */
  private String name;
  
  /* the current balance of the account */
  private double balance;
  
  /* the amount of money withdrawed from the account */
  private double withdraw;
  
  /* the amount of money deposited into the account */
  private double deposit;
  
  /* a constructor to create a trader */
  public Trader(String name) {
    this.name=name;
  }
  
  /* a method to get the name */
  public String getName() {
    return name;
  }
  
  /* a method to set the name */
  public void setName(String name) {
    this.name=name;
  }
  
  /* a method to get the current balance */
  public double getBalance() {
    return balance;
  }
    
  /* a method to withdraw from the account */
  public void withdraw(double withdraw) {
    this.balance=balance - withdraw;
  }
  
  /* a method to deposit into the account */
  public void deposit(double deposit) {
    this.balance=balance + deposit;
  }
}
