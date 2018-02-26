package ir.maktab.model.role;

/**
 * Created by Hamed-Abbaszadeh -> 09385136659 on 2/19/2018.
 */
public class Feature {
    private String name;
    private int id;
    public Feature() {
    }

    public Feature( String name) {
        this.name = name;
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

        Feature feature = (Feature) o;

        return name != null ? name.equals(feature.name) : feature.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
