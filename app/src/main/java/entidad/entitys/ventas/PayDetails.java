package entidad.entitys.ventas;

import java.time.LocalDateTime;

/**
 *
 * @author ivanl
 */
public class PayDetails {

    private Integer idDetailsPay;
    private LocalDateTime datePay;
    private Double totalPay;
    private String typePay;
    private String referencePay;
    private String description;
    private Integer fk_sales;

    /**
     * @return the idDetailsPay
     */
    public Integer getIdDetailsPay() {
        return idDetailsPay;
    }

    /**
     * @param idDetailsPay the idDetailsPay to set
     */
    public void setIdDetailsPay(Integer idDetailsPay) {
        this.idDetailsPay = idDetailsPay;
    }

    /**
     * @return the datePay
     */
    public LocalDateTime getDatePay() {
        return datePay;
    }

    /**
     * @param datePay the datePay to set
     */
    public void setDatePay(LocalDateTime datePay) {
        this.datePay = datePay;
    }

    /**
     * @return the totalPay
     */
    public Double getTotalPay() {
        return totalPay;
    }

    /**
     * @param totalPay the totalPay to set
     */
    public void setTotalPay(Double totalPay) {
        this.totalPay = totalPay;
    }

    /**
     * @return the typePay
     */
    public String getTypePay() {
        return typePay;
    }

    /**
     * @param typePay the typePay to set
     */
    public void setTypePay(String typePay) {
        this.typePay = typePay;
    }

    /**
     * @return the referencePay
     */
    public String getReferencePay() {
        return referencePay;
    }

    /**
     * @param referencePay the referencePay to set
     */
    public void setReferencePay(String referencePay) {
        this.referencePay = referencePay;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

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
    
    
}
