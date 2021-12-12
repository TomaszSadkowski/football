package service;

import connector.FootballApiConnector;
import dto.League;
import dto.Season;
import dto.Team;
import repository.LeagueRepository;
import repository.Repository;

import java.util.List;

public class FootballAppService {

    private FootballApiConnector footballApiConnector;
    private Repository repository;

    public FootballAppService(FootballApiConnector footballApiConnector,Repository repository) {
        this.footballApiConnector = footballApiConnector;
        this.repository=repository;
    }

    public List<League> getAllLeagues() {
        LeagueRepository leagueRepository = new LeagueRepository(en)
        return footballApiConnector.getAllLeagues();
    }

    public League leagueDetails(String id) {

        return footballApiConnector.leagueDetails(id);
    }

    public List<Team> winnerTeamOfSeasons(String leagueId) {
        return footballApiConnector.winnerTeamOfSeasons(leagueId);
    }

    public List<Team> standingsAfterSeason(String leagueId, int year) {
        return footballApiConnector.standingsAfterSeason(leagueId, year);
    }

    public List<Season> getListofAvailableSeasons(String leagueId) {
        return footballApiConnector.getListofAvailableSeasons(leagueId);
    }
}
