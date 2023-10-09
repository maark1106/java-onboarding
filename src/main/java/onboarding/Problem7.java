package onboarding;

import java.util.*;
import java.util.stream.Collectors;

public class Problem7 {

    static final int RELATED_POINT = 10;
    static final int VISITED_POINT = 1;

    static Map<String, Set<String>> friendRelation;
    static Map<String, Integer> recommendedScore;
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        storeFriendRelation(friends);
        calculateRecommendedScore(user, visitors);
        System.out.println("recommendedScore = " + recommendedScore);

        return RecommendFromScore();
    }

    private static void calculateRecommendedScore(String user, List<String> visitors) {
        recommendedScore = new HashMap<>();
        
        calculateRelationScore(user);
        calculateVisitedScore(user, visitors);
    }

    private static void calculateVisitedScore(String user, List<String> visitors) {
        Set<String> userFriends = friendRelation.get(user);

        for (String visitor : visitors) {
            if(!userFriends.contains(visitor)){
                recommendedScore.put(visitor,
                        recommendedScore.getOrDefault(visitor, 0) + VISITED_POINT);
            }
        }
    }

    private static void calculateRelationScore(String user) {
        //user의 friend 목록들 검사
        Set<String> userFriends = friendRelation.get(user);

        for (String userFriend : userFriends) {
            checkUserFriendList(userFriends,userFriend, user);
        }
    }

    ////////////////////////////////친구 관계 확인
    private static void checkUserFriendList(Set<String> userFriends,String userFriend, String user) {

        //other는 userFriend의 친구
        Set<String> others = friendRelation.get(userFriend);
        others.remove(user); //userFriend 목록에 user도 포함되어 있으므로 제거

        for (String other : others) {
            userFriendIsNotOther(userFriends, other);
        }
    }

    private static void userFriendIsNotOther(Set<String> userFriends, String other) {
        if(!userFriends.contains(other)){
            recommendedScore.put(other,
                    recommendedScore.getOrDefault(other, 0) + RELATED_POINT );
        }
    }

    private static void storeFriendRelation(List<List<String>> friends) {

        friendRelation = new HashMap<>();

        for (List<String> friend : friends) {
            String friend1 = friend.get(0);
            String friend2 = friend.get(1);

            connectFriend(friend1, friend2);
            connectFriend(friend2, friend1);
        }
    }

    private static void connectFriend(String friend1, String friend2) {
        friendRelation.computeIfAbsent(friend1,s->new HashSet<>()).add(friend2);
    }

    private static List<String> RecommendFromScore() {//점수 결과로 추천
        return recommendedScore.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Collections.reverseOrder())
                        .thenComparing(Map.Entry::getKey))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
