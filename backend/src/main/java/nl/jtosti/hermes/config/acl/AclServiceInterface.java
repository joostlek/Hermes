package nl.jtosti.hermes.config.acl;

import nl.jtosti.hermes.user.User;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

public interface AclServiceInterface {
    void addUser(User user);

    void addPermissionsToObject(Object object, Sid sid, Permission permission);

    void addChildObjectWithParent(Object object, Object parent);

    void removePermissionFromObject(Object object, Sid sid, Permission permission);
}
