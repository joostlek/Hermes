package nl.jtosti.hermes.config.acl;


import nl.jtosti.hermes.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.*;
import org.springframework.stereotype.Service;

@Service
public class MyAclService implements AclServiceInterface {
    private final JdbcMutableAclService aclService;


    @Autowired
    public MyAclService(JdbcMutableAclService aclService) {
        this.aclService = aclService;
    }

    @Override
    public void addUser(User user) {
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(User.class, user.getId());
        Sid sid = new PrincipalSid(user.getEmail());
        MutableAcl acl = aclService.createAcl(objectIdentity);
        acl.insertAce(acl.getEntries().size(), BasePermission.ADMINISTRATION, sid, true);
        aclService.updateAcl(acl);
    }

    @Override
    public void addPermissionsToObject(Object object, Sid sid, Permission permission) {
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(object);
        MutableAcl acl = getAclEntry(objectIdentity);
        acl.insertAce(acl.getEntries().size(), permission, sid, true);
        aclService.updateAcl(acl);
    }

    @Override
    public void addChildObjectWithParent(Object child, Object parent) {
        ObjectIdentity childIdentity = new ObjectIdentityImpl(child);
        ObjectIdentity parentIdentity = new ObjectIdentityImpl(parent);
        MutableAcl childAcl = getAclEntry(childIdentity);
        MutableAcl parentAcl = getAclEntry(parentIdentity);
        childAcl.setParent(parentAcl);
        aclService.updateAcl(childAcl);
    }

    @Override
    public void removePermissionFromObject(Object object, Sid sid, Permission permission) {
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(object);
        MutableAcl acl = getAclEntry(objectIdentity);
        for (AccessControlEntry ace : acl.getEntries()) {
            if (ace.getSid().equals(sid) && ace.getPermission().equals(permission)) {
                acl.deleteAce(acl.getEntries().indexOf(ace));
            }
        }
        aclService.updateAcl(acl);
    }

    private MutableAcl getAclEntry(ObjectIdentity objectIdentity) {
        try {
            return (MutableAcl) aclService.readAclById(objectIdentity);
        } catch (NotFoundException nfe) {
            return aclService.createAcl(objectIdentity);
        }
    }
}
