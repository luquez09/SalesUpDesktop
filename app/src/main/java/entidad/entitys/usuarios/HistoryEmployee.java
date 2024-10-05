package entidad.entitys.usuarios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author IvanLuquez
 * @version 1.0
 * @since 25/09/2024
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryEmployee {
    private Integer idHistoryEmployee;
    private LocalDateTime dateInit;
    private LocalDateTime dateFinish;
    private double salary;
    private String description;
    private int fkEmployee;
}
