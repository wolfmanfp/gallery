/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ptemik.gallery;

import hu.ptemik.gallery.hibernate.HibernateUtil;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author János
 */
public class TestForReal {
      
    public void TestForReal() {
        
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            HibernateUtil.getSessionFactory().openSession().beginTransaction();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
