package onboarding;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Problem6 {

    static Valid valid = new Valid();
    public static List<String> solution(List<List<String>> forms) {
        return findDuplicationNickname(forms);
    }

    public static List<String> findDuplicationNickname(List<List<String>> forms){

        HashMap<String, Integer> continuesNicknameStorage = new HashMap<>();
        ArrayList<String> duplicateNameList = new ArrayList<>();

        for (List<String> form : forms) { // 두 글자씩 map에 저장
            if(valid.validate(form)){
                separateAndStoreNickname(form.get(1), continuesNicknameStorage);
            }
        }

        for (List<String> form : forms) { //중복이라면 추가
            if(valid.validate(form)) {
                if (!isUniqueNickname(form.get(1), continuesNicknameStorage)){
                    duplicateNameList.add(form.get(0));
                }
            }
        }

        return removeAndSortDuplicateNicknameList(duplicateNameList);
    }

    public static List<String> removeAndSortDuplicateNicknameList(ArrayList<String> duplicateNameList) {
        HashSet<String> uniqueSet = new HashSet<>(duplicateNameList);
        ArrayList<String> DuplicateRemoveList = new ArrayList<>(uniqueSet);
        Collections.sort(DuplicateRemoveList);

        return DuplicateRemoveList;
    }

    public static boolean isUniqueNickname(String nickname, HashMap<String, Integer> continuesNicknameStorage){
        if(nickname.length() < 1) {
            return true;
        }

        for(int i = 0 ; i < nickname.length() - 1; i++){
            String separateNicknameOfTwoWord = nickname.substring(i, i + 2);
            if(continuesNicknameStorage.get(separateNicknameOfTwoWord) > 1){
                return false;
            }
        }

        return true;
    }

    public static void separateAndStoreNickname(String nickname, HashMap<String, Integer> continuesNicknameStorage) {
        if(nickname.length() < 1) {
            return;
        }

        for(int i = 0 ; i < nickname.length() - 1; i++){
            String separateNicknameOfTwoWord = nickname.substring(i, i + 2);
            continuesNicknameStorage.put(separateNicknameOfTwoWord, continuesNicknameStorage.getOrDefault(separateNicknameOfTwoWord, 0) + 1);
        }
    }

    static class Valid{
        public boolean validate(List<String> form) {
            return validateEmailForm(form.get(0)) && validateNickName(form.get(1));
        }

        public boolean validateEmailForm(String email){
            return validateEmailDomain(email) && validateEmailLength(email);
        }

        public boolean validateEmailDomain(String email){
            return email.endsWith("email.com");
        }

        public boolean validateEmailLength(String email){
            return 11 <= email.length() && email.length() < 20;
        }

        public boolean validateNickName(String nickname){
            return validateNicknameLength(nickname) && validateNicknameIsKorean(nickname);
        }

        public boolean validateNicknameLength(String nickname){
            return 1 <= nickname.length() && nickname.length() < 20;
        }

        public boolean validateNicknameIsKorean(String nickname) {
            String regex = "^[가-힣]*$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(nickname);

            return matcher.matches();
        }
    }
}
