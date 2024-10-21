package negocio.users;

import accessdata.users.AccessRole;
import entidad.constantes.Constants;
import entidad.entitys.usuarios.Role;
import negocio.utils.UtilsValidation;
import java.text.ParseException;
import java.util.List;

public class LogicalRoles {

    private final AccessRole accessRole = new AccessRole();

    public String addRoles(Role role) throws ParseException {
        String result = validateData(role);
        if (result.isEmpty() || result.isBlank()) {
            return accessRole.callSaveRoleEmployee(role);
        } else {
            result = Constants.ERROR.concat(Constants.BREAK_LINE).concat(result);
            return result;
        }
    }

    public String updateRoles(Role role) {
        String result = validateData(role);
        if (result.isEmpty() || result.isBlank()) {
            return accessRole.callUpdateRole(role);
        } else {
            result = Constants.ERROR.concat(Constants.BREAK_LINE).concat(result);
            return result;
        }
    }

    public String deleteRoles(int idRole) {
        return accessRole.callDeleteRole(idRole);
    }

    public List<Role> findAllRole() throws ParseException {
        return accessRole.callFindAllRoles();
    }

    public String validateData(Role role) {
        String result;

        result = UtilsValidation.validateString(role.getCode(), 10, "Codigo rol", true);
        result += UtilsValidation.validateString(role.getDescription(), 50, "Descripcion del rol", true);

        return result;
    }
}
