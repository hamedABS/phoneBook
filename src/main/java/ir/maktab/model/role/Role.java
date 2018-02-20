package ir.maktab.model.role;

import java.util.Set;

/**
 * Created by Hamed-Abbaszadeh on 2/19/2018.
 */
public class Role {
    private int id;
    private String name;
    private Set<Feature> features;

    public Role(String name, Set<Feature> features) {
        this.name = name;
        this.features = features;
    }

    public Role(){}

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

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", features=" + features +
                '}';
    }
}
