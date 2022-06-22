package edu.ucr.cs.cs167.mvinc006;


import java.util.function.Function;

// Primary Class
public class App {

    // Class for IsEven.
    static class IsEven implements Function<Integer, Boolean> {
        @Override
        public Boolean apply(Integer x) {
            return x % 2 == 0;
        }
    }

    // Class for IsOdd.
    static class IsOdd implements Function<Integer, Boolean> {
        @Override
        public Boolean apply(Integer x) {
            return x % 2 != 0;
        }
    }

    // printNumbers based on filter.
    public static void printNumbers(int from, int to, Function<Integer, Boolean> filter) {
        // Print init info statement:
        System.out.println("Printing numbers in the range [" + from + "," + to + "]");
        // Print out all the numbers.
        for (int i = from; i <= to; i++) {
            if (filter.apply(i)) {
                System.out.println(i);
            }
        }
    }

    // Prints odd numbers
    public static void printOddNumbers(int from, int to) {

        // Print init info statement:
        System.out.println("Printing numbers in the range [" + from + "," + to + "]");

        for (int i = from; i <= to; i++) {

            if ((i % 2) != 0) {
                // print the odd number.
                System.out.println(i);
            }

        }
    }

    // Prints even numbers
    public static void printEvenNumbers(int from, int to) {

        // Print init info statement:
        System.out.println("Printing numbers in the range [" + from + "," + to + "]");

        for (int i = from; i <= to; i++) {

            if ((i % 2) == 0) {
                // print the even number.
                System.out.println(i);
            }

        }
    }

    // combine filters AND
    public static Function<Integer, Boolean> combineWithAnd(Function<Integer, Boolean> ... filters) {

        Function<Integer, Boolean> function = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                Boolean returnVal = filters[0].apply(integer);
                for (int i = 0; i < filters.length; i++) {
                    returnVal = returnVal && filters[i].apply(integer);
                }
                return returnVal;
            }
        };
        return function;

    }

    // combine filters OR
    public static Function<Integer, Boolean> combineWithOr(Function<Integer, Boolean> ... filters) {

        Function<Integer, Boolean> function = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                Boolean returnVal = filters[0].apply(integer);
                for (int i = 0; i < filters.length; i++) {
                    returnVal = returnVal || filters[i].apply(integer);
                }
                return returnVal;
            }
        };
        return function;

    }

    // Main Method
    public static void main(String[] args) {

        // Check if arguments passes are correct.
        if (args.length != 3) {
            System.out.println("Error: At least three parameters expected, from, to and odd.");
            System.exit(-1);
        }

        // Variables
        int from = Integer.parseInt(args[0]);
        int to = Integer.parseInt(args[1]);

        // Init array of filters.
        // Function<Integer, Boolean>[] filters = new Function[];

        // Parse out the third parameter correctly.
        if (args[2].contains("^")) {
            String[] bases = args[2].split("\\^");

            Function<Integer, Boolean>[] filters = new Function[bases.length];
            for (int i = 0; i < filters.length; i++) {
                int base = Integer.parseInt(bases[i]);
                filters[i] = x -> x % base == 0;
            }
            Function<Integer, Boolean> filter = combineWithAnd(filters);
            printNumbers(from, to, filter);

        } else if (args[2].contains("v")) {
            String[] bases = args[2].split("v");
            Function<Integer, Boolean>[] filters = new Function[bases.length];
            for (int i = 0; i < filters.length; i++) {
                int base = Integer.parseInt(bases[i]);
                filters[i] = x -> x % base == 0;
            }
            Function<Integer, Boolean> filter = combineWithOr(filters);
            printNumbers(from, to, filter);

        } else {
            Function<Integer, Boolean> filter = x -> x % (Integer.parseInt(args[2]))== 0;
            printNumbers(from, to, filter);
        }

        /**
        // int odd = Integer.parseInt(args[2]);
        // int base = Integer.parseInt(args[2]);
        // test our functions.
        // Function<Integer, Boolean> filter = null;
//        if (odd == 2) {
//            //printEvenNumbers(from, to);
//            filter = new IsEven();
//        } else if (odd == 1) {
//            //printOddNumbers(from, to);
//            filter = new IsOdd();
//        }
        // Print numbers based on the filter.
        // printNumbers(from, to, filter);
        // Uses anonymous classes
//        Function<Integer, Boolean> divisibleByFive = new Function<Integer, Boolean>() {
//            @Override
//            public Boolean apply(Integer x) {
//                return x % 5 == 0;
//            }
//        };
        // Uses lambda expression
        // Function<Integer, Boolean> divisibleByTen = x -> x % 10 == 0;
        // Tests the new two functions
        // printNumbers(from, to, divisibleByFive);
        // printNumbers(from, to, divisibleByTen);
        // Function that tests if a number is divisible by base
        // Function<Integer, Boolean> divisibleByBase = x -> x % base == 0;
        // Tests our new function
        // printNumbers(from, to, divisibleByBase);
        // For Q2 and Q3
        // base = 0;
        **/

    }

}
