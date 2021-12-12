package connector;

import dto.League;
import dto.Logos;
import dto.Season;
import dto.Team;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class FootballApiConnector {

    public static final String URL = "https://api-football-standings.azharimm.site/";

    public List<League> getAllLeagues() {
        List<League> leagueList = new ArrayList<>();

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(URL + "leagues"))
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = HttpClient.newHttpClient()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(httpResponse.body());
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            jsonArray.iterator().forEachRemaining(s -> {
                League league = new League();
                JSONObject object = (JSONObject) s;
                league.setId(object.getString("id"));
                league.setName(object.getString("name"));
                leagueList.add(league);
            });


        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return leagueList;
    }

    public League leagueDetails(String id) {

        League league = new League();
        Logos logos = new Logos();

        String url = URL + "leagues/" + id;

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(URL + "leagues/" + id))
                    .GET()
                    .build();

            HttpResponse httpResponse = HttpClient.newHttpClient()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(httpResponse.body().toString());


            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject jlogos = data.getJSONObject("logos");


            logos.setLight(jlogos.getString("light"));
            logos.setDark(jlogos.getString("dark"));

            league.setId(data.getString("id"));
            league.setName(data.getString("name"));
            league.setSlug(data.getString("slug"));
            league.setAbbr(data.getString("abbr"));
            league.setLogos(logos);

            System.out.println(league);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return league;
    }

    public List<Team> winnerTeamOfSeasons(String leagueId) {

        List<Season> listOfAvailableSeasons = getListofAvailableSeasons(leagueId);
        int firstYear = listOfAvailableSeasons.stream().mapToInt(o -> o.getYear()).min().getAsInt();
        int lastYear = listOfAvailableSeasons.stream().mapToInt(o -> o.getYear()).max().getAsInt();

        List<Team> winners = new ArrayList<>();
        for (int i = firstYear; i <= lastYear; i++) {
            try {
                Team team = new Team();
                team.setYear(i);
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .uri(new URI(URL + "leagues/" + leagueId + "/standings?season=" + i + "&sort=asc"))
                        .GET()
                        .build();

                HttpResponse<String> httpResponse = HttpClient.newHttpClient()
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());


                JSONObject jsonObject = new JSONObject(httpResponse.body().toString());
                JSONObject data = jsonObject.getJSONObject("data");
                JSONArray jsonArray = data.getJSONArray("standings");
                JSONObject first = (JSONObject) jsonArray.iterator().next();
                JSONObject jsonTeam = first.getJSONObject("team");
                team.setName(jsonTeam.getString("name"));

                winners.add(team);

            } catch (URISyntaxException | IOException | InterruptedException e) {

            }
        }
        return winners;
    }

    public List<Team> standingsAfterSeason(String leagueId, int year) {

        List<Team> standings = new ArrayList<>();
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(URL + "leagues/" + leagueId + "/standings?season=" + year + "&sort=asc"))
                    .GET().build();
            HttpResponse httpResponse = HttpClient.newHttpClient()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(httpResponse.body().toString());
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray jsonArray = data.getJSONArray("standings");

            jsonArray.iterator().forEachRemaining(s -> {
                Team team = new Team();
                team.setYear(year);
                JSONObject object = (JSONObject) s;
                JSONObject jteam = object.getJSONObject("team");
                team.setName(jteam.getString("name"));
                standings.add(team);
            });
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return standings;
    }

    public List<Season> getListofAvailableSeasons(String leagueId) {

        List<Season> seasonList = new ArrayList<>();

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(URL + "leagues/" + leagueId + "/seasons"))
                    .GET().build();

            HttpResponse<String> httpResponse = HttpClient.newHttpClient()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(httpResponse.body());
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray seasons = data.getJSONArray("seasons");
            seasons.iterator().forEachRemaining(s -> {
                Season season = new Season();
                JSONObject jsonObject1 = (JSONObject) s;
                season.setYear(jsonObject1.getInt("year"));
                seasonList.add(season);

            });

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return seasonList;
    }
}