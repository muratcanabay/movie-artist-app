package com.uniyaz.core.dao;

import com.uniyaz.core.domain.MovieArtist;
import com.uniyaz.core.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class MovieArtistDao {
    public MovieArtist saveMovieArtist(MovieArtist movieArtist) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            movieArtist = (MovieArtist) session.merge(movieArtist);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return movieArtist;
    }

    public List<MovieArtist> findAllMovieArtist() {
        List<MovieArtist> movieArtistList = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            String hql =
                    "Select movieArtist " +
                            "From MovieArtist movieArtist " +
                            "left join fetch movieArtist.artist artist " +
                            "left join fetch movieArtist.movie movie ";
            Query query = session.createQuery(hql);
            movieArtistList = query.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return movieArtistList;
    }
}
