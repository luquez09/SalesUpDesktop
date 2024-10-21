package entidad.entitys.usuarios;

import entidad.entitys.inventario.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author ivanLuquez
 * @version 1.0
 * @since 25/09/2024
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    private int idRole;
    private String code;
    private String description;

    public Role(Integer idRole) {
        this.idRole = idRole;
    }

    public String toString() {
        return code;
    }

    public boolean equals(Object obj) {
        return Objects.equals(this.idRole, ((Role) obj).getIdRole());
    }
}
