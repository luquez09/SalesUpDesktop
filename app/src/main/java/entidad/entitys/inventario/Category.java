package entidad.entitys.inventario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author ivanl
 */
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

    public Category(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.idCategory, ((Category) obj).getIdCategory());
    }
    
}
