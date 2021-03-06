/* a class that represents the data required to either buy or sell a stock */
public class Order {
  
  /* the symbols of the stock */
  private char symbol;
  
  /* the number of shares */
  private int shares;
  
  /* the price of the stock */
  private double price;
  
  /* the variable that determines whether some shares can be traded or all shares must be traded */
  private boolean allornone;
  
  /* the trader who placed the order */
  private Trader trader;
  
  /* a constructor that takes five inputs */
  public Order(char symbol , int shares , double price , boolean allornone, Trader trader) {
    this.symbol=symbol;
    this.shares=shares;
    this.price=price;
    this.allornone=allornone;
    this.trader=trader;
  }
  
  /* a method to get stock symbol */
  public char getStockSymbol() {
    return symbol;
  }
  
  /* a method to get the number of shares */
  public int getNumberShares() {
    return shares;
  }
  
  /* a method to set the number of shares */
  public void setNumberShares(int shares) {
    this.shares=shares;
  }
  
  /* a method to get the price */
  public double getPrice() {
    return price;
  }
  
  /* a method to set the price */
  public void setPrice(double price) {
    this.price=price;
  }
  
  /* a method to get the variable that determines if all of the shares must be traded in one time */
  public boolean getAllOrNone() {
    return allornone;
  }
  
  /* a method to set the variable that determines if all of the shares must be traded in one time */
  public void setAllOrNone (boolean allornone) {
    this.allornone=allornone;
  }
  
  /* a method to get the Trader value */
  public Trader getTrader() {
    return trader;
  }
  
}