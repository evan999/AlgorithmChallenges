import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */
     
     

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
    // Write your code here
        List<Integer> rank = new ArrayList<>();
        List<Integer> playerRank = new ArrayList<>();
        
        
        for (int score = 0; score < ranked.size()-1; score++) {
            if (ranked.get(score) == ranked.get(score+1)) {
                ranked.remove(score+1);
            }
            rank.add(score+1);
            //System.out.println(rank.get(score));
            //System.out.println(ranked.get(score));
        }
        
        
        int ranking = rank.size()+1;
        for (int score = 0; score < player.size(); score++) {
            if (player.get(score) < ranked.get(score)) {
                ranking++;
                playerRank.add(ranking);
                ranking = rank.size() + 1;
                //continue;
            }
            else if (player.get(score) > ranked.get(score)) {
                ranking--;
            }
            else if (player.get(score) == ranked.get(score)) {
                playerRank.add(ranking);
            }
            
        }
        
        /*
        for (int score = 0; score < rank.size(); score++) {
            System.out.println(rank.get(score));
        }
        */
        return playerRank;
        
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}