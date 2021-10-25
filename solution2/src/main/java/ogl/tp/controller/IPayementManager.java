package ogl.tp.controller;

import ogl.tp.entity.Order;
import ogl.tp.entity.Payment;

public interface IPayementManager {

    public boolean addPayement(Payment payment);

    public boolean isPaid(int orderNum);
}
