/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg261cashcache;

import java.io.IOException;
import java.io.Serializable;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 *
 * @author Phil
 * 
 * Stock from the YahooFinance API is not serializable. This class is intended as a workaround that problem.
 * 
 */
public class StockItem implements Serializable{
    
    private String symbol;
    private String price;
    private String open;
    private String change;
    
    public StockItem(String aSymbol){
        symbol = aSymbol;
        try{
            Stock stock = YahooFinance.get(symbol);
            price = stock.getQuote().getPrice().toString();
            open = stock.getQuote().getOpen().toString();
            change = stock.getQuote().getChange().toString();
        } catch (IOException e){
            e.printStackTrace();
        }
        
    }

    /**
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the open
     */
    public String getOpen() {
        return open;
    }

    /**
     * @param open the open to set
     */
    public void setOpen(String open) {
        this.open = open;
    }

    /**
     * @return the change
     */
    public String getChange() {
        return change;
    }

    /**
     * @param change the change to set
     */
    public void setChange(String change) {
        this.change = change;
    }
        
    
}
