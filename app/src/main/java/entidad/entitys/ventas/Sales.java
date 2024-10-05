package entidad.entitys.ventas;

import java.time.LocalDateTime;

/**
 * @since 30/08/2024
 * @author ivanl
 */
public class Sales {

    private Integer idSales;
    private String numberBill;
    private LocalDateTime dateSale;
    private Double totalSale;
    private Double iva;
    private long totalArticle;

    /**
     * @return the idSales
     */
    public Integer getIdSales() {
        return idSales;
    }

    /**
     * @param idSales the idSales to set
     */
    public void setIdSales(Integer idSales) {
        this.idSales = idSales;
    }

    /**
     * @return the numberBill
     */
    public String getNumberBill() {
        return numberBill;
    }

    /**
     * @param numberBill the numberBill to set
     */
    public void setNumberBill(String numberBill) {
        this.numberBill = numberBill;
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
     * @return the totalSale
     */
    public Double getTotalSale() {
        return totalSale;
    }

    /**
     * @param totalSale the totalSale to set
     */
    public void setTotalSale(Double totalSale) {
        this.totalSale = totalSale;
    }

    /**
     * @return the iva
     */
    public Double getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(Double iva) {
        this.iva = iva;
    }

    /**
     * @return the totalArticle
     */
    public long getTotalArticle() {
        return totalArticle;
    }

    /**
     * @param totalArticle the totalArticle to set
     */
    public void setTotalArticle(long totalArticle) {
        this.totalArticle = totalArticle;
    }

}
