/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.cequre.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "jptv21ivtsenkovshopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    public User findByLogin(String login) {
        try {
            User user = (User) em.createQuery("SELECT u FROM User u WHERE u.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }
    
}
