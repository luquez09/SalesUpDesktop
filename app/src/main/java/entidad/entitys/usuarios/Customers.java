package entidad.entitys.usuarios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ivanLuquez
 * @version 1.0
 * @since 26/09/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customers {
    int idCustomers;
    String name;
    String address;
    String phone;
    String typeDocument;
    String numberDocument;
}
