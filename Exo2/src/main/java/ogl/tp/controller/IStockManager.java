package ogl.tp.controller;

import ogl.tp.entity.Product;

public interface IStockManager {



    public int  getProductQte(Product product) ;

    public boolean removeProductStock(Product product,int qte) ;

    public boolean addProductStock(Product product,int qte) ;


}
