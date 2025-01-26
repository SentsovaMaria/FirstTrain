package map;

import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashMap<Key, Product> map = new HashMap<>();
        //генерируем 8 записей, чтобы они попали в одну корзину
        //первоначальные настройки мапы - количество бакетов - 16. причем таблица создается при первом put
        //с помощь.ю метода ресайз
        //n = 7 - все записи попали в одну корзину
        //n = 8  - получается, что все записи в одной корзине и количество их = TREEIFY_THRESHOLD,
        //значит вызывается метод treeifyBin, в котором вот это условие дает true (n = tab.length) < MIN_TREEIFY_CAPACITY)
        //так как n = 16 и min_treeify_capacity = 64, то мы просто перестраиваем(увеличиваем в два раза).
        // причем все элементы также попадают в одну корзину. Размер таблицы = 32
        //n = 9 аналогично происходит перестроение, и теперь размер таблицы равен 64, все элементы в одной корзине
        //n = 10 - уже дерево

        for (int i=0;i<=10;i++){
            Key key = new Key("key"+i);
            int h = key.hashCode();
            System.out.println("Ключ "+key.getKey()+" имеет хешкод "+ key.hashCode()+ "после смещения "+ h);
            Product product = new Product("code"+i,"name"+i);
            map.put(key,product);
        }
       /* try {
            // Получаем класс объекта
            Class<?> mapClass = map.getClass();
            // Получаем приватное поле по имени
            Field field = mapClass.getDeclaredField("table");
            // Делаем поле доступным, чтобы можно было читать его значение
            //не разрешает. еще нужна какая-то настройка
             field.setAccessible(true);
            // Получаем значение поля
            //Пропускаем те значения, которые null
            //и смотрим тип узла
            String value = (String) field.get(mapClass);
            // Выводим значение
            System.out.println("индекс такой-то" + value+ "тип бакета");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //заберем несколько элементов из мапы
        for (int i=0;i<=5;i++){
             Product p = map.remove(new  Key ("key"+i));
            //System.out.println(map.size());
        }

        //когда удаляем элементы из мапы. то мы не смотрим по сути на константу.
        //дерево перестраивается обратно в список, когда очень мало остается элементов
        //у меня было 3
        for (int i=6;i<=10;i++){
            Product p = map.remove(new  Key ("key"+i));
        }

        System.out.println(map.size());
    }
}
