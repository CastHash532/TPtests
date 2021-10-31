package ogl.tp;

import ogl.tp.controller.*;
import ogl.tp.dao.*;
import ogl.tp.entity.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;


public class OrderDaoTest {
    

    OrderDao dao = new OrderDao();
    Order o = new Order();
    Customer c = new Customer();
    Product p = new Product();
    Orderline ol = new Orderline();
    ArrayList<Orderline> ols = new ArrayList<Orderline>();
    DatabaseConnection connection = new DatabaseConnection("sa", "", "org.h2.Driver", "jdbc:h2:mem:test");
    Connection conn = connection.connect();


    @Before
    public void before() {

        connection.createDb(conn);
        c.setCustomerID("ID01");
        p.setProductID("IP12");
        ol.setProduct(p);
        ol.setOrderedQte(1);
        ols.add(ol);
        o.setOrderlines(ols);
        o.setOrderNum(1);
        o.setCustomer(c);
        dao.setConn(conn);

    }


    @Test
    public void CRUOrderTest() throws SQLException  {  

        dao.insertOrder(o);
        assertArrayEquals(ols.toArray(), dao.getOrderDetails(1).toArray());
        dao.deleteOrder(1);
        assertNull(dao.getOrderDetails(1));
    }



}
