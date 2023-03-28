/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Product;
import entity.Purchase;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import session.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class PurchaseFacade extends AbstractFacade<Purchase> {
    @EJB private ProductFacade productFacade;

    @PersistenceContext(unitName = "jptv21ivtsenkovshopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseFacade() {
        super(Purchase.class);
    }
public Map<Product,Integer> getTakedBooksInPeriod(String year, String month, String day) {
        if(year== null || year.isEmpty()){
            return new HashMap<>();
        }
        List<Purchase> listPurchase = null;
        //Если выбран только год
        if((month == null || month.isEmpty()) && (day == null || day.isEmpty())){
            LocalDateTime date1 = LocalDateTime.of(Integer.parseInt(year),1, 1, 0, 0, 0); 
            LocalDateTime date2 = date1.plusYears(1);
            Date beginYear = Date.from(date1.atZone(ZoneId.systemDefault()).toInstant());
            Date beginNextYear = Date.from(date2.atZone(ZoneId.systemDefault()).toInstant());
            listPurchase = em.createQuery("SELECT p FROM Purchase p WHERE p.date > :beginYear AND p.date < :beginNextYear")
                .setParameter("beginYear", beginYear)
                .setParameter("beginNextYear", beginNextYear)
                .getResultList();
        //Если выбран год и месяц
        }else if((month != null || !month.isEmpty()) && (day == null || day.isEmpty())){
            LocalDateTime date1 = LocalDateTime.of(Integer.parseInt(year),Integer.parseInt(month), 1, 0, 0, 0); 
            LocalDateTime date2 = date1.plusMonths(1);
            Date beginMonth = Date.from(date1.atZone(ZoneId.systemDefault()).toInstant());
            Date beginNextMonth = Date.from(date2.atZone(ZoneId.systemDefault()).toInstant());
            listPurchase = em.createQuery("SELECT p FROM Purchase p WHERE p.date > :beginMonth and p.date < :beginNextMonth")
                .setParameter("beginMonth", beginMonth)
                .setParameter("beginNextMonth", beginNextMonth)
                .getResultList();
        }else{//Если выбран год, месяц и день
            LocalDateTime date1 = LocalDateTime.of(Integer.parseInt(year),Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0); 
            LocalDateTime date2 = date1.plusDays(1);
            Date begin = Date.from(date1.atZone(ZoneId.systemDefault()).toInstant());
            Date next = Date.from(date2.atZone(ZoneId.systemDefault()).toInstant());
            listPurchase = em.createQuery("SELECT p FROM Purchase p WHERE p.date > :begin AND p.date < :beginNext")
                .setParameter("begin", begin)
                .setParameter("beginNext", next)
                .getResultList();
        }
        //Map для хранения сопоставления книга -> сколько раз выдана
        Map<Product, Integer>mapBooksRange = new HashMap<>();
        List<Product> products = productFacade.findAll();
        for (Product product : products) { //перебираем все книги
            mapBooksRange.put(product, 0);
            for (Purchase purchase : listPurchase) {
                if(purchase.getProduct().equals(product)){
                    Integer n = mapBooksRange.get(product);
                    n++;//к n добавляем 1, если книга есть в истории
                    mapBooksRange.put(product, n);//обновляем значение n для книги
                }
            }
        }
        return mapBooksRange; // возвращаем карту Книга->сколько раз выдана за указанный период
     }   
}
