import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Приложение определяет наибольшее число в массиве, являющееся полным квадратом.
 * Исходным массивом является doubleList. Организуется поток. В потоке последовательно
 * элементы массива фильтруются и выделяются целые числа. Из них извлекается квадратный
 * корень. Если дробная часть корня равна нулю (корень соответствует целому числу),
 * то элемент проходит на дальнейшую обработку. Он  преобразуются к целому типу, и, далее,
 * из потока извлекается максимальный элемент.
 *
 * Сложность алгоритма O(n).
 */

public class Main {

    public static void main(String[] args) {

        List<Double> doubleList = Arrays.asList(1.1, 25.0, 90.4, 34.0, 36.0, 37.0);

        int maxElement = doubleList.stream()
                .filter(e -> e % 1 == 0)
                .filter(e-> Math.sqrt(e) % 1 == 0)
                .map(e -> (int)(e - e % 1))
                .mapToInt(e->e)
                .max().orElseThrow(NoSuchElementException::new);

        System.out.println("Исходный массив: ");
        doubleList.forEach(System.out::println);

        System.out.println();

        System.out.println( "Максимальный элемент в массиве," +
                " являющейся полным квадратом: " + maxElement);
    }
}