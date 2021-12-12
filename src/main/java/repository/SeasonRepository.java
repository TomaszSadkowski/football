package repository;

import dto.Season;

import javax.persistence.EntityManager;

public class SeasonRepository extends Repository<Season>{
    public SeasonRepository(EntityManager entityManager) {
        super(entityManager, Season.class);
    }
}
