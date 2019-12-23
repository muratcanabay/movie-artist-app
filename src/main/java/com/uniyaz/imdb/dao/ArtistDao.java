package com.uniyaz.imdb.dao;

import com.uniyaz.imdb.domain.Artist;
import com.uniyaz.imdb.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;

public class ArtistDao {
    public Artist saveArtist(Artist artist){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession();){
            session.getTransaction().begin();
            artist = (Artist) session.merge(artist);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return artist;
    }

    public List<Artist> findAllArtist(){
        List<Artist> artistList = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession();){
            String hql = "Select artist From Artist artist left join fetch artist.movie movie";
            Query query = session.createQuery(hql);
            artistList = query.list();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return artistList;
    }
}
