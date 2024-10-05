package entidad.entitys.ventas;

import java.time.LocalDateTime;

/**
 *
 * @author ivanl
 */
public class DetailSales {

    private Integer idDetailSales;
    private long amount;
    private Double price;
    private Double total;
    private LocalDateTime dateSale;
    private Integer fk_product;
    private Integer fk_sales;

    /**
     * @return the fk_sales
     */
    public Integer getFk_sales() {
        return fk_sales;
    }

    /**
     * @param fk_sales the fk_sales to set
     */
    public void setFk_sales(Integer fk_sales) {
        this.fk_sales = fk_sales;
    }
    
    /**
     * @return the idDetailSales
     */
    public Integer getIdDetailSales() {
        return idDetailSales;
    }

    /**
     * @param idDetailSales the idDetailSales to set
     */
    public void setIdDetailSales(Integer idDetailSales) {
        this.idDetailSales = idDetailSales;
    }

    /**
     * @return the amount
     */
    public long getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(long amount) {
        this.amount = amount;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return the dateSale
     */
    public LocalDateTime getDateSale() {
        return dateSale;
    }

    /**
     * @param dateSale the dateSale to set
     */
    public void setDateSale(LocalDateTime dateSale) {
        this.dateSale = dateSale;
    }

    /**
     * @return the fk_product
     */
    public Integer getFk_product() {
        return fk_product;
    }

    /**
     * @param fk_product the fk_product to set
     */
    public void setFk_product(Integer fk_product) {
        this.fk_product = fk_product;
    }
}
