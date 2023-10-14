import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/**
 * Алгоритм опирается на методы алгебраической теории чисел. Этот алгоритм известен
 * как решето Аткина. Его основная идея состоит в том, что существуют квадратичные формы
 * a*x^2 + b*y^2 = n
 * для определенных классов n, имеющие нечетное количество решений только тогда, когда
 * n - либо простое, либо имеет делитель p^2. Это позволяет с помощью квадратичных форм
 * отсеить все составные числа, не делящиеся на квадрат простого числа, а потом  отсеить
 * числа кратные квадратам простых чисел.
 *
 * Сложность реализованного алгоритма O(n).
 */

public class Main {
    static Long x2;
    static Long y2;
    static Long n;
    static Integer limit;

    static Set<Long> listOfPrimeNumbers = new HashSet<>();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Введите верхнюю границу поиска простых чисел ");
        System.out.print("Limit = ");
        limit = in.nextInt();

        double sqrtLimitD = (Math.sqrt(limit));
        int sqrtLimit = (int) sqrtLimitD;

        //составляем список простых чисел
        for (Long x = 1L; x <= sqrtLimit; x++) {
            x2 = x * x;
            for (Long y = 1L; y <= sqrtLimit; y++) {
                y2 = y * y;

                n = 4 * x2 + y2;
                if (n <= limit && (n % 12 == 1 || n % 12 == 5))
                    if (listOfPrimeNumbers.contains(n))
                        listOfPrimeNumbers.remove(n);
                    else
                        listOfPrimeNumbers.add(n);

                n = 3 * x2 + y2;
                if (n <= limit && (n % 12 == 7))
                    if (listOfPrimeNumbers.contains(n))
                        listOfPrimeNumbers.remove(n);
                    else
                        listOfPrimeNumbers.add(n);

                if (x2 > y2) {
                    n = 3 * x2 - y2;
                    if (n <= limit && (n % 12 == 11))
                        if (listOfPrimeNumbers.contains(n))
                            listOfPrimeNumbers.remove(n);
                        else
                            listOfPrimeNumbers.add(n);
                }
            }
        }

        // исключаем квадраты простых чисел
        Set<Long> remElement = new HashSet<>();
        for (Long p : listOfPrimeNumbers) {
           long p2 = p * p;
            for (long i = p2; i < limit; i += p2) {
                remElement.add(i);
            }
        }
        listOfPrimeNumbers.removeAll(remElement);

        listOfPrimeNumbers.add(2L);
        listOfPrimeNumbers.add(3L);

        System.out.println("Простые числа с верхней границей " + limit + ":");
        listOfPrimeNumbers.forEach(System.out::println);
    }
}
