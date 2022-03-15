import java.util.Scanner;

public class get {
    public static void main(String[] args) {
        String s;
        Scanner in = new Scanner(System.in);
        s = in.next();
        char[] a = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            a[i] = s.charAt(i);
        }

        //2
        a = s.toCharArray();

    }
}
