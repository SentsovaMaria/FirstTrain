package list;

import java.util.List;

public class MyArrayListMain {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        System.out.println("Тестирование добавления элементов-----------------------------------------");
        testAdd(list);
        System.out.println("Тестирование поиска элементов---------------------------------------_");
        testContain(list);
        System.out.println("Тестирование удаления элементов--------------------------------------");
        testRemove(list);
    }

    private static void testAdd(MyArrayList<Integer> list) {
        System.out.println("Сгенерирован массив путем добавления элементов в конец ");
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list.toString());
        System.out.println("Текущая длина массива " + list.getCurrentLength() + "; Размер массива " + list.getSize());

        //Добавление в середину
        list.add(5, 90);
        System.out.println("Добавление элемента со значением 90 в индекс 5");
        System.out.println(list.toString());
        System.out.println("Текущая длина массива " + list.getCurrentLength() + "; Размер массива " + list.getSize());

        //Добавление списка
        System.out.println("Добавление списка {22,23,24} на позицию 2");
        List<Integer> collection = List.of(22, 23, 24);
        list.addAll(2, collection);
        System.out.println(list.toString());
        System.out.println("Текущая длина массива " + list.getCurrentLength() + "; Размер массива " + list.getSize());
    }

    private static void testContain(MyArrayList<Integer> list) {
        System.out.println("Искомый элемент стоит в начале списка, его индекс равен " + list.indexOf(list.get(0)));
        System.out.println("Индекс элемента со значением пять равен  " + list.indexOf(5));
        System.out.println("Последний элемент списка равен " + (list.get(list.getCurrentLength() - 1)));
        System.out.println("Искомый элемент стоит в конце списка, его индекс равен   " + list.indexOf(list.get(list.getCurrentLength() - 1)));
        System.out.println("В списке нет искомого элемента, функция возвращает значние равное " + list.indexOf(9999));
        list.add(null);
        System.out.println("Поиск значения null в списке " + list.indexOf(null));
        MyArrayList<Integer> list1 = new MyArrayList<Integer>();
        System.out.println("Поиск в пустом массиве возвращает значение равное " + list1.indexOf(1));

        System.out.println("Поиск коллекции {1,2,3} в массиве " + list.containsAll(List.of(1, 2, 3)));
        System.out.println("Поиск коллекции {1,2,3,777} в массиве " + list.containsAll(List.of(1, 2, 3, 777)));

        System.out.println("Поиск с конца повторяющегося элемента");
    }

    private static void testRemove(MyArrayList<Integer> list) {
        System.out.println("Удаление первого элемента");
        list.remove(0);
        System.out.println("Текущая длина массива " + list.getCurrentLength() + "; Размер массива " + list.getSize());
        System.out.println( list.toString());


        System.out.println("Удаление элемента со значением 3");
        list.remove(3);
        System.out.println("Текущая длина массива " + list.getCurrentLength() + "; Размер массива " + list.getSize());
        System.out.println( list.toString());

        System.out.println("Удаление элемента со значением 333");
        list.remove(3);
        System.out.println("Текущая длина массива " + list.getCurrentLength() + "; Размер массива " + list.getSize());
        list.toString();

        System.out.println("Удаление коллекции {1,2,3} в массиве");
        System.out.println("Текущая длина массива " + list.getCurrentLength() + "; Размер массива " + list.getSize());
        list.removeAll(List.of(1, 2, 3));
        System.out.println( list.toString());


    }


}
