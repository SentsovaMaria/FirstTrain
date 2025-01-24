
package list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements List<E> {
    //коэффициент, на который умножается размер массива при расширении
    private static final int EXPANSION_COEFFICIENT = 2;
    //размер массива, по умолчанию = 10
    private int size = 10;
    //текущая длина массива(количество содержащихся в нем элементов)
    private int currentLength = 0;
    //элементы массива
    private Object[] elements;

    //конструкторы_______________________________________________________________
    public MyArrayList(int size) {
        if (size < 0) throw new IllegalArgumentException("Illegal Capacity: " + size);
        this.size = size;
        elements = new Object[size];
    }

    public MyArrayList() {
        elements = new Object[size];
    }

    //операции добавления----------------------------------------------------------------------

    /**
     * Вставка элемента element по индексу index
     * Если массив заполнен, то при вставке нового элемента он расширяется в EXPANSION_COEFFICIENT
     * раз
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    @Override
    public void add(int index, E element) {
        if (isNotValidIndex(index)) return;
        //создаем новый массив, если предыдущий массив заполнен, то создаем массив большего размера
        int newArraySize = currentLength == size ? size * EXPANSION_COEFFICIENT : size;
        Object[] newArray = new Object[newArraySize];

        //в новый массив копируем часть массива до места вставки
        for (int i = 0; i < index; i++) {
            newArray[i] = elements[i];
        }
        //вставляем элемент в позицию index
        newArray[index] = element;

        //копируем часть массива после места вставки
        for (int i = index + 1; i < currentLength + 1; i++) {
            newArray[i] = elements[i - 1];
        }
        // изменили ссылки
        elements = newArray;
        //изменили значения полей
        size = newArraySize;
        currentLength++;
    }

    /**
     * Проверка валидности индекса (корректный индекс от нуля до currentLength)
     *
     * @param index - проверяемый индекс
     * @return true, если индекс не валидный
     */
    private boolean isNotValidIndex(int index) {
        return (index < 0) || (index > currentLength + 1);
    }

    /**
     * Добавление элемента в конец массива
     *
     * @param e element whose presence in this collection is to be ensured
     * @return true, если операция прошла успешно
     */
    @Override
    public boolean add(E e) {
        int previousCurrentLength = currentLength;
        add(currentLength, e);
        return previousCurrentLength < currentLength;
    }

    /**
     * Вставка элементов коллекции c в позицию index
     * Если массив заполнен, то расширяем его на (количество элементов коллекции*2)
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     collection containing elements to be added to this list
     * @return true, если операция прошла успешно
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (isNotValidIndex(index)) return false;
        //вычисляем размер коллекции
        int sizeCollection = c.size();
        int newArraySize = (currentLength + sizeCollection >= size) ? size + sizeCollection * 2 : size;

        Object[] newArray = new Object[newArraySize];
        //копируем элементы до индекса в новый массив
        for (int i = 0; i < index; i++) {
            newArray[i] = elements[i];
        }
        //копируем коллекцию, начиная с позиции index
        Iterator iterator = c.iterator();
        for (int i = index; i < index + sizeCollection; i++)
            newArray[i] = iterator.next();
        //копируем оставшиеся элементы исходного массива
        for (int i = index + 1; i <= currentLength; i++) {
            newArray[i + sizeCollection - 1] = elements[i - 1];
        }

        elements = newArray;
        size = newArraySize;
        currentLength = currentLength + sizeCollection;

        return true;
    }

    /**
     * Добавление элементов коллекции в конец списка
     *
     * @param c collection containing elements to be added to this collection
     * @return true, если вставка прошла успешно
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(currentLength, c);
    }

    //поиск элементов______________________________________________________________

    /**
     * Поиск элемента о в списке
     *
     * @param o element to search for
     * @return индекс элемента или -1, если такого элемента нет
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i <= currentLength; i++) {
            if ((elements[i] == null) && (o == null) ||
                    (o != null) && (o.equals(elements[i]))) return i;
        }
        return -1;
    }

    @Override
    public boolean contains(Object e) {
        return indexOf(e) != -1;
    }


    @Override
    public int lastIndexOf(Object o) {
        for (int i = currentLength; i >= 0; i--) {
            if ((elements[i] == null) && (o == null) ||
                    (o != null) && (o.equals(elements[i]))) return i;
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean ok = true;
        Iterator iterator = c.iterator();
        while (ok && iterator.hasNext()) {
            ok = ok && contains(iterator.next());
        }

        return ok;
    }

    //размер________________________________________________________________________
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    //------------------------------------------------

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return elements;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    //удаление элементов-------------------------------------------------------------

    /**
     * Удаление элемента по индексу
     * Если индеекс выходит за границы массива, то выбрасывается исключение
     *
     * @param index the index of the element to be removed
     * @return удаленный элемент
     */
    @Override
    public E remove(int index) {
        if (isNotValidIndex(index))
            throw new ArrayIndexOutOfBoundsException("Индекс должен быть в диапазоне от 0 до " + currentLength +
                    ", а текущее значение индекса равно " + index);
        E element = (E) elements[index];
        for (int i = index; i <= currentLength; i++) {
            elements[i] = elements[i + 1];
        }
        elements[currentLength] = null;
        currentLength--;
        return element;
    }

    /**
     * Удаление элемента. Сначала осуществляется поиск элемента, затем
     * если элемент найден, то он удаляется
     *
     * @param o element to be removed from this list, if present
     * @return true, если получилось удалить элемент
     */
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) return false;
        int previousLength = currentLength;
        remove(index);
        return previousLength > currentLength;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean ok = true;
        Iterator iterator = c.iterator();
        while (ok && iterator.hasNext()) {
            ok = ok && remove(iterator.next());
        }

        return ok;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
    }

    @Override
    public E get(int index) {
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        return null;
    }


    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    public String toString() {
        if (currentLength == 0) return null;
        StringBuilder result = new StringBuilder(String.valueOf(elements[0]) + ", ");

        for (int i = 1; i < currentLength; i++) {
            result = result.append(String.valueOf(elements[i]) + ", ");
        }
        return result.toString();

    }
}

