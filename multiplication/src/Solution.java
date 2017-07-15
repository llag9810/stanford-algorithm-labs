/**
 * Created by yifan on 3/29/17.
 */
public class Solution {

    public StringBuilder add(StringBuilder arg1, StringBuilder arg2) {


        int count = Math.max(arg1.length(), arg2.length());
        int carry = 0;
        StringBuilder sb = new StringBuilder("0");

        for(int i = 0; i < count; i++) {

            carry = 0;

            int a = (arg1.length() - i) >= 0 ? arg1.charAt(arg1.length() - i) - '0' : 0;
            int b = (arg2.length() - i) >= 0 ? arg1.charAt(arg2.length() - i) - '0' : 0;

            int sum = (a + b) % 10;
            carry = (a + b) / 10;

            sb.replace()
        }

    }


    public StringBuilder minus(StringBuilder arg1, StringBuilder arg2) {

    }



    public StringBuilder multiplication(StringBuilder arg1, StringBuilder arg2) {

        assert arg1.length() == arg2.length();

        if (arg1.length() == 1 && arg2.length() == 1) {

            int result = (arg1.charAt(0) - '0') * (arg2.charAt(0) - '0');
            return new StringBuilder(result + "");
        }

        StringBuilder a = arg1.delete(0, arg1.length() / 2);
        StringBuilder b = arg1.delete(arg1.length() / 2, arg1.length();
        StringBuilder c = arg2.delete(0, arg1.length() / 2);
        StringBuilder d = arg2.delete(arg2.length() / 2, arg2.length();

        StringBuilder ac = multiplication(a, c);

        StringBuilder bd = multiplication(b, d);

        StringBuilder adbc = multiplication(add(a, d), add(b, c));


    }

    public static void main(String[] args) {
        System.out.println(new Solution().multiplication(new StringBuilder("3"), new StringBuilder("5")));
    }

}
