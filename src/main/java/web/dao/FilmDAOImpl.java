package web.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Film;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class FilmDAOImpl implements FilmDAO {
    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Film> allFilms() {
        return entityManager.createQuery("SELECT f FROM Film f", Film.class).getResultList();
    }

    @Override
    public void add(Film film) {
        entityManager.persist(film);
    }

    @Override
    public void delete(Film film) {
        entityManager.remove(entityManager.contains(film) ? film : entityManager.merge(film));
    }

    @Override
    public void edit(Film film) {
        entityManager.merge(film);
    }

    @Override
    public Film getById(int id) {
        return entityManager.find(Film.class, id);
    }
}
