package ir.maktab.unit_test.role_test;

import ir.maktab.model.role.Feature;
import ir.maktab.model.role.Role;
import ir.maktab.model.role.dao.RoleDAO;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;


public class RoleCrudTest {
    private RoleDAO roleDAO ;
    @Before
    public void start(){
        roleDAO = new RoleDAO();
    }
    @Test
    public void add(){
        Set ceofeatures = new HashSet();
        Set mngFeatures = new HashSet();
        Set userFeatures = new HashSet();
        Set guestFeature = new HashSet();
        ceofeatures.add(new Feature("CreateUser"));
        ceofeatures.add(new Feature("DeleteUser"));
        ceofeatures.add(new Feature("ReadUser"));
        ceofeatures.add(new Feature("UpdateUser"));
        ceofeatures.add(new Feature("CreateContact"));
        ceofeatures.add(new Feature("DeleteContact"));
        ceofeatures.add(new Feature("ReadContact"));
        ceofeatures.add(new Feature("UpdateContact"));
        ceofeatures.add(new Feature("SeeUsersActivity"));

        assertTrue(roleDAO.add(new Role("CEO",ceofeatures)));
        System.out.println(roleDAO.getFeatureById(5));
        mngFeatures.add(roleDAO.getFeatureById(5));
        mngFeatures.add(roleDAO.getFeatureById(6));
        mngFeatures.add(roleDAO.getFeatureById(7));
        mngFeatures.add(roleDAO.getFeatureById(8));
        mngFeatures.add(roleDAO.getFeatureById(9));


        userFeatures.add(roleDAO.getFeatureById(7));
        userFeatures.add(roleDAO.getFeatureById(5));

        guestFeature.add(roleDAO.getFeatureById(7));

        assertTrue(roleDAO.add(new Role("MANAGER",mngFeatures)));
        assertTrue(roleDAO.add(new Role("USER",userFeatures)));
        assertTrue(roleDAO.add(new Role("GUEST",guestFeature)));
    }

    @Test
    public void delete(){
     //   roleDAO.delete()
    }

    @Test
    public void edit(){
    }

    @Test
    public void getById(){
        Role role = (Role) roleDAO.getById(1);
        System.out.println(role.toString());
    }

    @Test
    public void getAll(){
    }
}
