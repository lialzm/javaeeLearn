package bean;

import java.util.Set;

/**
 * Created by apple on 17/2/12.
 */
public class RoleEntity {
    private int id;
    private String name;
    private Set<PermissionEntity> permissionEntities;


    public Set<PermissionEntity> getPermissionEntities() {
        return permissionEntities;
    }

    public void setPermissionEntities(Set<PermissionEntity> permissionEntities) {
        this.permissionEntities = permissionEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
