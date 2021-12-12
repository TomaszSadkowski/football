package repository;

import dto.League;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class LeagueRepository extends Repository<League> {

    private final EntityManager entityManager;

    public LeagueRepository(EntityManager entityManager) {
        super(entityManager, League.class);
        this.entityManager=entityManager;
    }

    public List<League> leaguesList(){
        Query query = entityManager.createQuery("FROM leagues");
        return query.getResultList();
    }
}
