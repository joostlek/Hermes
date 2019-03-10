package nl.jtosti.hermes.config.acl;

import org.springframework.security.acls.domain.AbstractPermission;
import org.springframework.security.acls.model.Permission;

public class MyPermission extends AbstractPermission {
    public static final Permission READ = new MyPermission(1 << 0, 'R'); // 1
    public static final Permission WRITE = new MyPermission(1 << 1, 'W'); // 2
    public static final Permission CREATE = new MyPermission(1 << 2, 'C'); // 4
    public static final Permission DELETE = new MyPermission(1 << 3, 'D'); // 8
    public static final Permission ADMINISTRATION = new MyPermission(1 << 4, 'A');
    public static final Permission EMPLOYEE = new MyPermission(1 << 5, 'E');

    protected MyPermission(int mask) {
        super(mask);
    }

    protected MyPermission(int mask, char code) {
        super(mask, code);
    }
}
