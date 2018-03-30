/* a class that represents hte successful trade of stock */
public class Transaction {
  
  /* the symbols of the stock */
  private char symbol;
  
  /* the number of shares */
  private int shares;
  
  /* the price of the stock */
  private double price;
  
  /* the trader buying the stock */
  private Trader buyer;
  
  /* the trader selling the stock */
  private Trader seller;
    
  /* the market handling transaction */
  private Market maker;
    
  /* indicator of whether the transaction has been closed */
  private boolean isclosed;
  
  /* a constructor that takes six inputs */
  public Transaction(char symbol, int shares, double price, Trader buyer, Trader seller, Market maker) {
    this.symbol=symbol;
    this.shares=shares;
    this.price=price;
    this.buyer=buyer;
    this.seller=seller;
    this.maker=maker;
  }
  
  /* a method to get stock symbol */
  public char getStockSymbol() {
    return symbol;
  }
  
  /* a method to get the number of shares */
  public int getNumberShares() {
    return shares;
  }
  
  /* a method to get the price */
  public double getPrice() {
    return price;
  }
  
  /* a method to get the buyer value */
  public Trader getBuyer() {
    return buyer;
  }
  
  /* a method to get the seller value */
  public Trader getSeller() {
    return seller;
  }
  
  /* a method to get the marketmaker handling the transaction */
  public Market getMarketMaker() {
    return maker;
  }
    
  /* a method to find out the indicator whether the transaction has been closed */
  public boolean IsClosed() {
    return isclosed;
  }
  
  /* a method to process transaction */
  public void processTransaction() {
    if ( isclosed==false ) {
      buyer.withdraw(this.getPrice()*this.getNumberShares());
      seller.deposit(this.getPrice()*this.getNumberShares());
      buyer.withdraw((this.maker.getCommission()*this.maker.getTradeFee())/2);
      seller.withdraw((this.maker.getCommission()*this.maker.getTradeFee())/2);
      this.isclosed=true;
    }
  }
}