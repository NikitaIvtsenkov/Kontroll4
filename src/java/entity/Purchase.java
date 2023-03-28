/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Stefan
 */
@Entity
public class Purchase implements Serializable{
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Customer customer; 
    private Product product;
   @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    private int quantity;
    private int history;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "History{" 
                + "customer=" + customer 
                + ", product=" + product 
                + ", date=" + date 
                + ", quantity=" + quantity 
                + '}';
    }

    

}
