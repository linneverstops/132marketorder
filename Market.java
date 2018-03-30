/* a class that represents the market */
public class Market extends Trader {
  
  /* the name of the market */
  private String name;
  
  /* the stock symbol for this market */
  private char symbol;
  
  /* the commission charged anytime there is a trade */
  private double commission;
  
  /* the trade fee charged anytime there is a trade */
  private double fee;
  
  /* the size of the market */
  private int size;
  
  /* the best buy order */
  private BuyOrder bestbuyorder;
  
  /* second best buy order */
  private BuyOrder secbestbuyorder;
  
  /* the best sell order */
  private SellOrder bestsellorder;
  
  /* second best sell order */
  private SellOrder secbestsellorder;
  
  /* the current market maker(mm) buy order */
  private MarketBuyOrder mmbuyorder;
  
  /* the current market maker(mm) sell order */
  private MarketSellOrder mmsellorder;
  
  /* the change of price of the order when the marketmaker order is sold or purchased */
  private double offset;
  
  /* a constructor that takes six inputs */
  public Market (String name , char symbol , double fee , double commission , int size , double offset) {
  super(name);
  this.name=name;
  this.symbol=symbol;
  this.fee=fee;
  this.commission=commission;
  this.size=size;
  this.offset=offset;
  }
  
  /* a method to get the stocksymbol */
  public char getStockSymbol() {
    return symbol;
  }
  
  /* a method to get the commission */
  public double getCommission() {
    return commission;
  }
  
  /* a method to set the commission */
  public void setCommission(double commission) {
    this.commission=commission;
  }
  
  /* a method to get the trade fee*/
  public double getTradeFee() {
    return fee;
  }
  
  /* a method to set the trade fee */
  public void setTradeFee(double fee) {
    this.fee=fee;
  }
  
  /* a method to get the market order size*/
  public int getMarketOrderSize() {
    return size;
  }
  
  /* a method to set the market order size */
  public void setMarketOrderSize(int size) {
    this.size=size;
  }
  
  /* a method to get the price offset */
  public double getPriceOffset() {
    return offset;
  }
  
  /* a method to set the price offset */
  public void setPriceOffset(double offset) {
    this.offset=offset;
  }
  
  /* a method to get the best buy order */
  public BuyOrder getBestBuyOrder() {
    return bestbuyorder;
  }
  
  /* a method to get the best sell order */
  public SellOrder getBestSellOrder() {
    return bestsellorder;
  }
  
  /* a method to get the second best buy order */
  public BuyOrder get2ndBestBuyOrder() {
    return secbestbuyorder;
  }
  
  /* a method to get the second best sell order */
  public SellOrder get2ndBestSellOrder() {
    return secbestsellorder;
  }
  
  /* a method to get the market buy order */
  public MarketBuyOrder getMarketBuyOrder() {
    return mmbuyorder;
  }
  
  /* a method to set the market buy order */
  public void setMarketBuyOrder(MarketBuyOrder mmbuyorder) {
    this.mmbuyorder=mmbuyorder;
  }
  
  /* a method to get the market sell order */
  public MarketSellOrder getMarketSellOrder() {
    return mmsellorder;
  }
  
  /* a method to set the market sell order */
  public void setMarketSellOrder(MarketSellOrder mmsellorder) {
    this.mmsellorder=mmsellorder;
  }
  
  /* a method to return the current market buy price */
  public double currentMarketBuyPrice() {
    if (bestbuyorder == null) {
      return mmbuyorder.getPrice();
    }
    else
      return bestbuyorder.getPrice();
  }
  
  /* a method to return the current market sell price */
  public double currentMarketSellPrice() {
    if (bestsellorder == null) {
      return mmsellorder.getPrice();
    }
    else
      return bestsellorder.getPrice();
  }
  
  /* a method to show if the market is open */
  public boolean isOpen() {
    if ( mmbuyorder != null && mmsellorder != null && mmsellorder.getPrice() > mmbuyorder.getPrice() ) 
      return true;
    else
      return false;
  }
  
  /* a method to show if the order is valid */
  public boolean isValidOrder(Order order) {
    if (order.getStockSymbol() == this.symbol) {
      if (order.getPrice() >= mmbuyorder.getPrice() && order.getPrice() <= mmsellorder.getPrice())
        return true;
      else 
        return false;
    }
      else 
        return false;
  }
  
  /* a method to show if the orders are matching */
  public boolean matchingOrders(BuyOrder buyorder, SellOrder sellorder) {
    if (buyorder.getPrice() >= sellorder.getPrice()) {
      if (buyorder.getAllOrNone() == true) {
        if (sellorder.getNumberShares() >= buyorder.getNumberShares())
          return true;
          else 
          return false;
      }
        
      if (sellorder.getAllOrNone() == true) {
        if (buyorder.getNumberShares() >= sellorder.getNumberShares())
          return true;
        else
          return false;
    }
      else 
      return true;
    }
      
      else
        return false;
        }
    
    /* a method to add buy order to market */
    public void addOrderToMarket(BuyOrder buyorder) {
      if (buyorder.getStockSymbol() == this.symbol) {
        if (this.bestbuyorder == null || buyorder.getPrice() > bestbuyorder.getPrice()) {
          this.secbestbuyorder = this.bestbuyorder;
          this.bestbuyorder = buyorder;
        }
        else {
          if (secbestbuyorder == null || buyorder.getPrice() > secbestbuyorder.getPrice()) {
            this.secbestbuyorder = buyorder;
          }
        }
      }
    }
    
    /* a method to add sell order to market */
    public void addOrderToMarket(SellOrder sellorder) {
      if (sellorder.getStockSymbol() == this.symbol) {
        if (this.bestsellorder == null || sellorder.getPrice() > bestsellorder.getPrice()) {
          this.secbestsellorder = this.bestsellorder;
          this.bestsellorder = sellorder;
        }
        else {
          if (secbestsellorder == null || sellorder.getPrice() > secbestsellorder.getPrice()) {
            this.secbestsellorder = sellorder;
          }
        }
      }
    }
    
    /* a method to process buy order */
    public Transaction placeOrder(BuyOrder buyorder) {
      int shares; //declare the variable shares so that it can be used below
      if (isOpen() == false || isValidOrder(buyorder) == false) {
        return null;
      }
      if (matchingOrders(buyorder, bestsellorder) == false || matchingOrders(buyorder, secbestsellorder) == false || matchingOrders(buyorder, mmsellorder) == false) {
        addOrderToMarket(buyorder);
        return null;
      }
      else if (matchingOrders(buyorder, bestsellorder) == true) {
        if (buyorder.getNumberShares() >= bestsellorder.getNumberShares()) {
          shares = buyorder.getNumberShares();
        }
        else 
          shares = bestsellorder.getNumberShares();
        
        bestsellorder = secbestsellorder;
        secbestsellorder = null; 
        Transaction transaction = new Transaction(this.symbol, shares, bestsellorder.getPrice(), buyorder.getTrader(), bestsellorder.getTrader(), this);
        return transaction;
      }
      else if (matchingOrders(buyorder, secbestsellorder) == true) {
        if (buyorder.getNumberShares() >= secbestsellorder.getNumberShares()) {
          shares = buyorder.getNumberShares();
        }
        else
          shares = secbestsellorder.getNumberShares();
        
        secbestsellorder = null;
        Transaction transaction = new Transaction(this.symbol, shares, secbestsellorder.getPrice(), buyorder.getTrader(), secbestsellorder.getTrader(), this);
        return transaction;
      }
      else {
        mmsellorder.setNumberShares(this.size);
        if (buyorder.getNumberShares() >= mmsellorder.getNumberShares()) {
          shares = buyorder.getNumberShares();
        }
        else {
          shares = mmsellorder.getNumberShares();
        }
   
        Transaction transaction = new Transaction(this.symbol, shares, mmsellorder.getPrice() + this.offset, buyorder.getTrader(), mmsellorder.getTrader(), this);
        return transaction;
      }
    }
    
    /* a method to process sell order */
    public Transaction placeOrder(SellOrder sellorder) {
      int shares; //declare the variable shares so that it can be used below
      if (isOpen() == false || isValidOrder(sellorder) == false) {
        return null;
      }
      if (matchingOrders(bestbuyorder, sellorder) == false || matchingOrders(secbestbuyorder, sellorder) == false || matchingOrders(mmbuyorder, sellorder) == false) {
        addOrderToMarket(sellorder);
        return null;
      }
      else if (matchingOrders(bestbuyorder, sellorder) == true) {
        if (sellorder.getNumberShares() >= bestbuyorder.getNumberShares()) {
          shares = sellorder.getNumberShares();
        }
        else {
          shares = bestbuyorder.getNumberShares();
        }
        bestbuyorder = secbestbuyorder;
        secbestbuyorder = null;
        Transaction transaction = new Transaction(this.symbol, shares, bestbuyorder.getPrice(), sellorder.getTrader(), sellorder.getTrader(), this);
        return transaction;
      }
     else if (matchingOrders(secbestbuyorder, sellorder) == true) {
        if (sellorder.getNumberShares() >= secbestbuyorder.getNumberShares()) {
          shares = sellorder.getNumberShares();
        }
        else {
          shares = secbestbuyorder.getNumberShares();
        }
        secbestbuyorder = null;
        Transaction transaction = new Transaction(this.symbol, shares, secbestbuyorder.getPrice(), secbestbuyorder.getTrader(), sellorder.getTrader(), this);
        return transaction;
      }
      else {
        if (sellorder.getNumberShares() >= mmbuyorder.getNumberShares()) {
          shares = sellorder.getNumberShares();
        }
        else {
          shares = mmbuyorder.getNumberShares();
        }
        Transaction transaction = new Transaction(this.symbol, shares, mmbuyorder.getPrice() - this.offset, mmbuyorder.getTrader(), sellorder.getTrader(), this);
        return transaction;
      }
    }
}
          
       
  
