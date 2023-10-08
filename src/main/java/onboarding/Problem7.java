package onboarding;

import java.util.*;
import java.util.stream.Collectors;

public class Problem7 {

    static FriendShip friendShip = new FriendShip();
    static Recommendation recommendation = new Recommendation();

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        //friendShip 친구 정보 주입
        friendShip.createFriendShip(friends);
        return recommendation.recommendUsers(user, friendShip.createRecommendedScore(user, visitors));
    }

    enum Point{
        RELATED_POINT(10),
        VISITED_POINT(1);

        private final int point;
        Point(int point) {
            this.point = point;
        }
    }

    static class FriendShip{

        private Map<String, Set<String>> friendShip;
        private Map<String, Integer> recommendedScore;

        public FriendShip(){
            friendShip = new HashMap<>();
            recommendedScore = new HashMap<>();
        }

        public Map<String, Set<String>> createFriendShip(List<List<String>> friends){
            friendShip = new HashMap<>();

            for (List<String> friend : friends) {
                String name1 = friend.get(0);
                String name2 = friend.get(1);
                connect(name1, name2);
                connect(name2, name1);
            }
            return friendShip;
        }

        private void connect(String user, String friend){
            //user가 friendShip map에 등록되어있다면 friend만 추가
            //등록 x있다면 HashSet 공간 할당해주고 set(user,friend)추가
            friendShip.computeIfAbsent(user, s->new HashSet<>()).add(friend);
        }

        public Map<String, Integer> createRecommendedScore(String user, List<String> visitors){
            recommendedScore = new HashMap<>();

            for(String other : friendShip.keySet()){
                relatedFriends(user, other);
            }

            for (String visitor : visitors) {
                addVisitedScore(user, visitor);
            }

            return recommendedScore;
        }

        private void relatedFriends(String user, String other) {
            Set<String> userFriends = friendShip.getOrDefault(user, new HashSet<>());
            for (String friend : friendShip.get(other)) {
                addRelatedScore(other, userFriends, friend);
                //other: friendShip에 등록된 모든 key값
                //userFriend : userfriend 목록들
                //friend other의 friend
            }
        }

        private void addRelatedScore(String other, Set<String> userFriends, String friend) {
            //user는 친추 x, user의 friend가 친추 o
            if(!userFriends.contains(other) && userFriends.contains(friend)){
                recommendedScore.put(other,
                        recommendedScore.getOrDefault(other, 0)
                                +Point.RELATED_POINT.point);
            }
        }

        private void addVisitedScore(String user, String visitor) {
            Set<String> userFriends = friendShip.getOrDefault(user, new HashSet<>());
            if(!userFriends.contains(visitor)){
                recommendedScore.put(visitor,
                        recommendedScore.getOrDefault(visitor,0) + Point.VISITED_POINT.point);
            }
        }
    }

    static class Recommendation {

        public List<String> recommendUsers(String user, Map<String, Integer> recommendedScore) {
            return recommendedScore.entrySet().stream()
                    .filter(entry -> !entry.getKey().equals(user) && entry.getValue() != 0)
                    .sorted(Map.Entry.<String, Integer>comparingByValue(Collections.reverseOrder())
                            .thenComparing(Map.Entry::getKey))
                    .limit(5)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
    }
}
