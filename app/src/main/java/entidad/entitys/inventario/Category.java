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
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
   
    private Integer idCategory;
    private String name;
    private String description;
    private LocalDateTime dateCreate;
    private LocalDateTime dateUpdate;
    
}
