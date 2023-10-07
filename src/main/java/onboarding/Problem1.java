package onboarding;

import java.util.List;

class Problem1 {

    static Valid valid = new Valid();
    static Calculation calculation = new Calculation();
    static Result result = new Result();

    public static int solution(List<Integer> pobi, List<Integer> crong){

        if(valid.validate(pobi) && valid.validate(crong)){
            int pobiMaxPoint = calculation.getMaxPoint(pobi);
            int crongMaxPoint = calculation.getMaxPoint(crong);

            return result.whoIsWinner(pobiMaxPoint, crongMaxPoint);
        }
        return Result.EXCEPTION;
    }

    static class Valid{

        private static final int START_PAGE = 1;
        private static final int END_PAGE = 400;

        public boolean validate(List<Integer> pages){
            return isExistPages(pages) && isCorrectRange(pages) &&
                    isNotStartAndEndPage(pages) && isOddNumber(pages.get(0)) &&
                    isEvenNumber(pages.get(1)) && isContinues(pages);
        }

        public boolean isExistPages(List<Integer> pages){
            return pages.get(0) != null && pages.get(1) != null;
        }

        public boolean isCorrectRange(List<Integer> pages){
            int leftPage = pages.get(0);
            int rightPage = pages.get(1);

            return START_PAGE <= leftPage && leftPage <= END_PAGE
                    && START_PAGE <= rightPage && rightPage <= END_PAGE;
        }

        public boolean isNotStartAndEndPage(List<Integer> pages){
            int leftPage = pages.get(0);
            int rightPage = pages.get(1);

            return leftPage != START_PAGE && rightPage != END_PAGE;
        }

        public boolean isOddNumber(int page){
            return page % 2 == 1;
        }

        public boolean isEvenNumber(int page){
            return page % 2 == 0;
        }

        public boolean isContinues(List<Integer> pages){
            return pages.get(1) - 1 == pages.get(0) ;
        }
    }

    static class Calculation{
        public int getMaxPoint(List<Integer> pages){
            int plusMaxPoint = Math.max(plusEachPage(pages.get(0)), plusEachPage(pages.get(1)));
            int multipleMaxPoint = Math.max(multipleEachPage(pages.get(0)), multipleEachPage(pages.get(1)));
            return Math.max(plusMaxPoint,multipleMaxPoint);
        }

        public int plusEachPage(int page){
            int result = 0;

            while(page != 0){
                result += page % 10;
                page = page / 10;
            }

            return result;
        }

        public int multipleEachPage(int page){
            int result = 1;

            while(page != 0){
                result *= page % 10;
                page /= 10;
            }

            return result;
        }
    }

    static class Result{
        private static final int DRAW = 0;
        private static final int EXCEPTION = -1;
        public int whoIsWinner(int pobiPoint, int crongPoint){

            if(pobiPoint > crongPoint) {
                return 1;
            }
            else if(pobiPoint < crongPoint) {
                return 2;
            }
            return DRAW;
        }
    }
}
