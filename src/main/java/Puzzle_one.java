public class Puzzle_one {
    int a;
    int b;
    int c;

    /*
        It is impossible to create hashcode without collisions for this class cause even we wont
         count 'a' field there is still 'Integer.MAX_VALUE^2' possible unic objects and hashcode
         output range is 'Integer.MIN_VALUE...Integer.MAX_VALUE' (Integer.MAX_VALUE * 2 + 1).

        For situation where any 2 non-equal objects have different hashcode we need to have input
        (in our case fields) combination of probably values of which will be less than 'Integer.MAX_VALUE':

        [a1...a2].length * [b1...b2].length * [c1...c2].length < (((long) Integer.MAX_VALUE) * 2) + 1

         Smart answer - https://crypto.stackexchange.com/questions/8765/is-there-a-hash-function-which-has-no-collisions
     */

    @Override
    public int hashCode() {
        // classic hashcode from Effective Java (i hope, to lazy to found exact code snipped)
        int result = 17 + a;
        result = 31 * result + b;
        result = 31 * result + c;
        return result;
    }
}
