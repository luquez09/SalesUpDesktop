package entidad.entitys.inventario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author ivanl
 * @since 22/09/2024
 * @version 1.0
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    private Integer idStore;
    private String name;
    private String description;
    private String ubication;
}
