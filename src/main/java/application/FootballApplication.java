package application;

import connector.FootballApiConnector;
import connector.WebApiConnector;
import dto.League;
import dto.Season;
import dto.Team;

import java.util.List;
import java.util.Scanner;

public class FootballApplication implements WebApiConnector {

    private static final FootballApiConnector footballApiConnector = new FootballApiConnector();

    public void start() {

        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;

        do {
            System.out.println("football api");
            System.out.println("available options");
            System.out.println("1 - display available leagues");
            System.out.println("2 - download list of leagues and display");
            System.out.println("3 - download league object with details and display");
            System.out.println("4 - get list of league champions for selected league for all seasons and display");
            System.out.println("5 - get list of standings after selected season for selected league and display");
            System.out.println("6 - exit");
            userChoice = scanner.nextInt();
            scanner.nextLine();
            String leagueChoice;
            int yearChoice;


            switch (userChoice) {
                case 1:
                    break;
                case 2:
                    List<League> leagueList = footballApiConnector.getAllLeagues();
                    leagueList.stream().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Select league: ");
                    leagueChoice = scanner.nextLine();
                    footballApiConnector.leagueDetails(leagueChoice);
                    break;
                case 4:
                    System.out.println("Select league: ");
                    leagueChoice = scanner.nextLine();
                    List<Team> winners = footballApiConnector.winnerTeamOfSeasons(leagueChoice);
                    winners.forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Select league: ");
                    leagueChoice = scanner.nextLine();
                    System.out.println("Select year: ");
                    yearChoice = scanner.nextInt();
                    List<Team> standings = footballApiConnector.standingsAfterSeason(leagueChoice, yearChoice);
                    scanner.nextLine();
                    standings.forEach(System.out::println);
                    break;
                default:
                    break;
            }
        } while (userChoice < 6 && userChoice > 0);

    }
}

