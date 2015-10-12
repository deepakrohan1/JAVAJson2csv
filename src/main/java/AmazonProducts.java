/**
 * Created by rohan on 10/11/15.
 */
public class AmazonProducts {
    private String asin, salesRank, imUrl, title,description;
    private String alsoBought;
    private String alsoViewed;
    private String buy_after_viewing;

    public String getAlsoViewed() {
        return alsoViewed;
    }

    public void setAlsoViewed(String alsoViewed) {
        this.alsoViewed = alsoViewed;
    }



    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getSalesRank() {
        return salesRank;
    }

    public void setSalesRank(String salesRank) {
        this.salesRank = salesRank;
    }

    public String getImUrl() {
        return imUrl;
    }

    public void setImUrl(String imUrl) {
        this.imUrl = imUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlsoBought() {
        return alsoBought;
    }

    public void setAlsoBought(String alsoBought) {
        this.alsoBought = alsoBought;
    }


    public String getAlso_viewed() {
        return also_viewed;
    }

    public void setAlso_viewed(String also_viewed) {
        this.also_viewed = also_viewed;
    }

    public String getBuy_after_viewing() {
        return buy_after_viewing;
    }

    public void setBuy_after_viewing(String buy_after_viewing) {
        this.buy_after_viewing = buy_after_viewing;
    }

    private String also_viewed;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "AmazonProducts{" +
                "asin='" + asin + '\'' +
                ", salesRank='" + salesRank + '\'' +
                ", imUrl='" + imUrl + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", alsoBought='" + alsoBought + '\'' +
                ", alsoViewed='" + alsoViewed + '\'' +
                ", buy_after_viewing='" + buy_after_viewing + '\'' +
                ", also_viewed='" + also_viewed + '\'' +
                ", price=" + price +
                '}';
    }
}
