package entidad.entitys.usuarios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ivanl
 * @since 25/09/2024
 * @version 1.0
 */

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    private Integer idEmployee;
    private String nameEmployee;
    private String addressEmployee;
    private String phoneNumberEmployee;
    private String passwordEmployee;
    private String userEmployee;
    private Integer fkRoleEmployee;
    private String identification;
}
