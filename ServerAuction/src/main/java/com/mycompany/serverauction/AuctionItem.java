package com.mycompany.serverauction;

/**
 *
 * @author Nathan
 */
public class AuctionItem {
    private final String name;
    private final int minimumBid;
    private final int minimumBetweenBids;

    public AuctionItem(String name, int minimumBid, int minimumBetweenBids) {
        this.name = name;
        this.minimumBid = minimumBid;
        this.minimumBetweenBids = minimumBetweenBids;
    }

    public String getName() {
        return name;
    }

    public int getMinimumBid() {
        return minimumBid;
    }

    public int getMinimumBetweenBids() {
        return minimumBetweenBids;
    }
    
    
}
