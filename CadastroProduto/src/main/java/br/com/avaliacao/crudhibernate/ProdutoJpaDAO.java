package br.com.avaliacao.crudhibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.avaliacao.model.Produto;

public class ProdutoJpaDAO {

	 private static ProdutoJpaDAO instance;
     protected EntityManager entityManager;

     public static ProdutoJpaDAO getInstance(){
       if (instance == null){
          instance = new ProdutoJpaDAO();
       }

       return instance;
     }

     private ProdutoJpaDAO() {
       entityManager = getEntityManager();
     }

     private EntityManager getEntityManager() {
      EntityManagerFactory factory =
      Persistence.createEntityManagerFactory("crudHibernatePU");
      if (entityManager == null) {
        entityManager = factory.createEntityManager();
      }

      return entityManager;
     }

     public Produto getById(final int id) {
       return entityManager.find(Produto.class, id);
     }

     @SuppressWarnings("unchecked")
     public List<Produto> findAll() {
       return entityManager.createQuery("FROM " +
    	Produto.class.getName()).getResultList();
     }

     public void persist(Produto produto) {
       try {
          entityManager.getTransaction().begin();
          entityManager.persist(produto);
          entityManager.getTransaction().commit();
       } catch (Exception ex) {
          ex.printStackTrace();
          entityManager.getTransaction().rollback();
       }
     }

     public void merge(Produto produto) {
       try {
          entityManager.getTransaction().begin();
          entityManager.merge(produto);
          entityManager.getTransaction().commit();
       } catch (Exception ex) {
          ex.printStackTrace();
          entityManager.getTransaction().rollback();
       }
     }

     public void remove(Produto produto) {
       try {
          entityManager.getTransaction().begin();
          produto = entityManager.find(Produto.class, produto.getId());
          entityManager.remove(produto);
          entityManager.getTransaction().commit();
       } catch (Exception ex) {
          ex.printStackTrace();
          entityManager.getTransaction().rollback();
       }
     }

     public void removeById(final int id) {
       try {
    	  Produto produto = getById(id);
          remove(produto);
       } catch (Exception ex) {
          ex.printStackTrace();
       }
     }
}
