package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Lancamento;
import util.JPAUtil;

public class LancamentoDAO {
	public static void salvar(Lancamento l) {
		EntityManager em = JPAUtil.createEntityManager();
		em.getTransaction().begin();
		em.persist(l);
		em.getTransaction().commit();
		em.close();
	}

	public static void editar(Lancamento l) {
		EntityManager em = JPAUtil.createEntityManager();
		em.getTransaction().begin();
		em.merge(l);
		em.getTransaction().commit();
		em.close();
	}

	public static void remover(Lancamento l) {
		EntityManager em = JPAUtil.createEntityManager();
		em.getTransaction().begin();
		l = em.find(Lancamento.class, l.getId());
		em.remove(l);
		em.getTransaction().commit();
		em.close();
	}

	public static Lancamento acharPorId(Integer id) {
		EntityManager em = JPAUtil.createEntityManager();
		Lancamento l = em.find(Lancamento.class, id);
		em.close();
		return l;
	}

	public static List<Lancamento> acharTodos() {
		EntityManager em = JPAUtil.createEntityManager();
		Query q = em.createQuery("select l from Lancamento l");
		List<Lancamento> lista = q.getResultList();
		em.close();
		return lista;
	}
	

}

