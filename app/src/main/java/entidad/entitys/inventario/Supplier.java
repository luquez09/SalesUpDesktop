package entidad.entitys.inventario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ivanl
 * @since 22/09/2024
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
    private Integer idSupplier;
    private String name;
    private String direccion;
    private String phoneNumber1;
    private String phoneNumber2;
    private String name_social;
    private String description;

    public Supplier(Integer idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String toString() {
        return name + " - " + name_social;
    }

    public boolean equals(Object obj) {
        return this.idSupplier == ((Supplier) obj).getIdSupplier();
    }
}
