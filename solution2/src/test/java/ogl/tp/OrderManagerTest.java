package ogl.tp;

import ogl.tp.controller.*;
import ogl.tp.dao.*;
import ogl.tp.entity.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.command.dml.MergeUsing.When;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class OrderManagerTest {


    OrderDao mod = Mockito.mock(OrderDao.class);
    IStockManager msm = Mockito.mock(IStockManager.class);
    IPayementManager mpm = Mockito.mock(IPayementManager.class);
    OrderManager om = new OrderManager(mod,msm,mpm);
    Order o = new Order();
    Customer c = new Customer();
    Product p = new Product();
    Orderline ol = new Orderline();
    ArrayList<Orderline> ols = new ArrayList<Orderline>();
    
    @Before
    public void before(){
        ol.setProduct(p);
        ols.add(ol);
        o.setOrderlines(ols);
        when(msm.getProductQte(p)).thenReturn(10);


    }


    @Test
    public void createOrderTest() {

        //quantité disponible
        ol.setOrderedQte(5);
        assertTrue(om.createOrder(o));
        verify(mod).insertOrder(o);
        verify(msm).getProductQte(p);

        //quantité non disponible
        ol.setOrderedQte(15);
        assertFalse(om.createOrder(o));
    }


    @Test
    public void cancelOrderTest() {
        
        int orderNum = 1;
        o.setOrderNum(orderNum);

        when(mpm.isPaid(orderNum)).thenReturn(true);
        assertFalse(om.cancelOrder(orderNum));

        when(mpm.isPaid(orderNum)).thenReturn(false);
        when(mod.getOrderDetails(orderNum)).thenReturn(ols);
        assertTrue(om.cancelOrder(orderNum));
        verify(mod).deleteOrder(orderNum);
        verify(msm).addProductStock(p, ol.getOrderedQte());

    }

}