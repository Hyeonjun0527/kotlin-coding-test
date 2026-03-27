public class VarEx {
    public static void main(String[] args) {

        /**
         * 수업 때 진행한 것
         */
        //hello 출력하기
        System.out.println("hello");

        //변수에 담고 출력하기
        int age = 20;
        int 나이 = 30;
        System.out.println("age : " + age + " 나이 : " + 나이);

        //변수이름 정할때 주의할 점
        // int i1dx1 = 0;
        // int 1idx = 2;
        // idx1 - idx2;
        // idx1-idx2;

        //묵시적 형변환
        System.out.println(10 / 3);
        System.out.println(10.0 / 3);
        System.out.println(10 / 3.0);

        //문자열 출력
        System.out.println("안녕하세요" + age);
        // System.out.println("안녕하세요" * 3); 는 불가능

        //명시적 형변환
        System.out.println((int) 3.14);

        /**
         * 변수 선언
         * */
        int a = 10;
        double b = 3.14;
        char c = 'A';
        boolean d = true;
        String e = "Kim";

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println();

        /**
         * 변수 값 변경
         * */
        a = 20;
        System.out.println(a);
        System.out.println();

        /**
         * 산술 연산자
         * */
        int f = 10;
        int g = 3;
        System.out.println(f + g);
        System.out.println(f - g);
        System.out.println(f * g);
        System.out.println(f / g);
        System.out.println(f % g);
        System.out.println();

        /**
         * 정수 나눗셈과 실수 나눗셈
         * */
        System.out.println(10 / 3);
        System.out.println(10.0 / 3);
        System.out.println(10 / 3.0);
        System.out.println();

        /**
         * 자동 형변환 */
        int h = 10;
        double i = h;
        System.out.println(i);
        System.out.println();

        /**
         * 강제 형변환
         * */
        double j = 3.14;
        int k = (int) j;
        System.out.println(k);
        System.out.println();

        /** 비교 연산자 */
        System.out.println(f == g);
        System.out.println(f != g);
        System.out.println(f > g);
        System.out.println(f < g);
        System.out.println();

        /** 논리 연산자 */
        System.out.println(f > g && a > g);
        System.out.println(f < g || a > g);
        System.out.println(!(f == g));
        System.out.println();

        /** 복합 대입 연산자 */
        int l = 10;
        l += 5;
        System.out.println(l);
        l *= 2;
        System.out.println(l);
        System.out.println();

        /** 나머지 연산 활용 */
        int m = 10;
        System.out.println(m % 2 == 0);
    }
}