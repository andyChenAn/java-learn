# LinkedList
LinkedList是一个双向链表。在LinkedList内部有一个很重要的内部类"Node"，这个类的实例就是存储在链表中所对应的节点。"Node"中包含三个成员变量：previous，next，item。previous表示当前节点的上一个节点，next表示当前节点的下一个节点，item表示当前节点。

LinkedList的构造器中其中有一个可以传入一个集合类型作为参数，那么它是怎么将集合中的数据存放在自己里面呢？我们来看一下源码是怎么实现的：主要就是在addAll方法中实现：
```java
// 在指定的index上面插入一个集合数据
public boolean addAll(int index, Collection<? extends E> c) {
    // 首先检查index是否合法，主要看是否有角标越界，如果越界，就抛出异常
    checkPositionIndex(index);
    
    // 将集合类型转换成数组类型
    Object[] a = c.toArray();
    int numNew = a.length;
    // 如果集合没有元素。那么返回false
    if (numNew == 0)
        return false;

    Node<E> pred, succ;
    // 如果插入的位置刚好是LinkedList的最后一个或者是一个空链表，那么将当前节点的前一个节点设置为插入之前的最后一个节点
    if (index == size) {
        succ = null;
        pred = last;
    } else {
        succ = node(index);
        pred = succ.prev;
    }
    // 将数组中的元素保存在LinkedList链表中
    // 这里执行的步骤是：
    // 1、遍历数组中的每一个元素
    // 2、如果pred为null，则表示链表是一个空链表，那么链表中的第一个元素就是新创建的Node实例
    // 3、如果pred不为null，则表示链表不是一个空链表，那么pred的下一个节点就是新创建的Node实例
    // 4、最后更新pred变量的值，这样在遍历下一个元素的时候，上一个节点变成了刚才新创建的Node实例
    for (Object o : a) {
        @SuppressWarnings("unchecked") E e = (E) o;
        // 这里遍历的时候，当前新创建的Node实例的下一个节点都是null。
        Node<E> newNode = new Node<>(pred, e, null);
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        pred = newNode;
    }
    
    // 如果是一个空链表或者插入的元素是在最后一个，那么最后一个节点就是遍历集合的最后一个Node实例
    // 如果是在index索引前插入一个集合，那么集合的最后一个元素的下一个节点就是index索引对应的节点，而index索引对应的节点的前一个节点遍历集合元素创建Node实例的最后一个
    // 这里是更新插入集合后的节点操作
    if (succ == null) {
        last = pred;
    } else {
        pred.next = succ;
        succ.prev = pred;
    }

    size += numNew;
    modCount++;
    return true;
}
```
从上面的源码中，我们可以知道，调用addAll(index , collection)方法，主要做了以下几件事情：

- 首先检查是否角标越界，并将集合转换为数组
- 获取LinkedList中的节点的上一个节点和下一个节点
- 向LinkedList中的索引index中插入集合，并更新插入的节点的上一个节点和下一个节点
- 更新插入后的节点的最后一个节点的上一个节点和下一个节点
### add方法
当我们调用add方法时，向LinkedList添加一个元素时，都是添加在LinkedList链表的末尾，LinkedList内部是如何做的呢？

```java
// 将数据添加到LinkedList末尾
void linkLast(E e) {
    // 这里在添加数据前，会获取LinkedList链表最后一个元素
    final Node<E> l = last;
    // 创建一个Node实例
    final Node<E> newNode = new Node<>(l, e, null);
    // 将添加的元素作为链表的最后一个元素
    last = newNode;
    // 判断如果之前链表的最后一个元素值为null，那么表示在添加数据前，该链表是一个空链表，还没有任何数据，那么该链表的第一个元素也是现在添加的这个元素
    // 如果之前链表的最后一个原值不为null，那么表示在添加数据前，该链表是有数据的，那么就将新添加的元素作为添加前链表最后一个元素的下一个元素
    if (l == null)
        first = newNode;
    else
        l.next = newNode;
    // 每添加一个元素，增加链表的长度
    size++;
    modCount++;
}
```
当我们调用add(index , element)方法时，向链表指定的位置上添加一个元素，LinkedList内部是如何执行的呢？

```java
// 向链表指定的位置上添加一个元素
public void add(int index, E element) {
    // 首先检查index是否合法，主要看是否有角标越界，如果越界，就抛出异常
    checkPositionIndex(index);
    // 如果index等于size，那么表示元素插入的位置就是从链表末尾插入
    if (index == size)
        linkLast(element);
    else
        linkBefore(element, node(index));
}

// 调用node(index)方法来查找index对应的node元素，并返回该元素
Node<E> node(int index) {
    // assert isElementIndex(index);
    // 如果index小于链表size的一半，那么就从链表的开始位置向后遍历元素，直到找到index对应的元素为止，并返回
    // 如果index大于链表size的一半，那么就从链表的末尾向前遍历元素，直到找到index对应的元素为止，并返回
    if (index < (size >> 1)) {
        Node<E> x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    } else {
        Node<E> x = last;
        for (int i = size - 1; i > index; i--)
            x = x.prev;
        return x;
    }
}

// 在index对应元素前插入指定的元素
void linkBefore(E e, Node<E> succ) {
    // assert succ != null;
    // 在插入的时候先获取index对应元素的前一个元素，因为当我们在index位置插入一个新的元素时，新元素的前一个元素就是之前index对应元素的前一个元素
    final Node<E> pred = succ.prev;
    // 创建一个Node实例，并会设置插入元素的前一个元素和后一个元素
    final Node<E> newNode = new Node<>(pred, e, succ);
    // 这里需要设置index对应元素的前一个元素为插入的这个元素
    succ.prev = newNode;
    // 如果index对应元素的前一个元素是null，那么表示index对应元素是链表的第一个元素，那么就重新设置链表的第一个元素的值为新插入的元素。否则就重新设置index对应元素的前一个元素的下一个元素为新插入的元素
    if (pred == null)
        first = newNode;
    else
        pred.next = newNode;
    // 更新链表大小
    size++;
    modCount++;
}
```
除了在指定的位置上插入一个元素，我们也可以调用addFirst或addLast方法将元素插入到链表的最前面或末尾。

```java
// 在链表最前面添加一个元素
// 首先获取第一个节点，然后创建一个新节点，并更新第一个节点为新节点
// 如果链表的第一个节点为空，那么表示该链表是一个空列表，那么将最后一个节点也更新为新节点
// 如果第一个节点不为空，那么将这个节点的上一个节点更新为新创建的节点
// 更新链表尺寸大小
private void linkFirst(E e) {
    final Node<E> f = first;
    final Node<E> newNode = new Node<>(null, e, f);
    first = newNode;
    if (f == null)
        last = newNode;
    else
        f.prev = newNode;
    size++;
    modCount++;
}

// 在链表最末尾添加一个元素
// 这里的操作和上面的方式类似
void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null)
        first = newNode;
    else
        l.next = newNode;
    size++;
    modCount++;
}
```
### clear方法
该方法用于清空链表所有元素。来看一下源码是怎么写的？

```java
// 从头遍历链表中的每一个元素，将所有的元素赋值为null，并且更新size的值为0
public void clear() {
    for (Node<E> x = first; x != null; ) {
        Node<E> next = x.next;
        x.item = null;
        x.next = null;
        x.prev = null;
        x = next;
    }
    first = last = null;
    size = 0;
    modCount++;
}
```
### clone方法
该方法用于克隆链表，返回一个链表副本。

```java
// 克隆链表，返回一个副本
public Object clone() {
    LinkedList<E> clone = superClone();

    // Put clone into "virgin" state
    // 将副本设置为初始状态
    clone.first = clone.last = null;
    clone.size = 0;
    clone.modCount = 0;

    // Initialize clone with our elements
    // 将链表中的元素添加到副本中
    for (Node<E> x = first; x != null; x = x.next)
        clone.add(x.item);

    return clone;
}
```
### contains(Object o)方法
该方法用于表示集合中是否包含指定的元素，返回一个布尔值。

```java
// 内部调用indexOf()方法，来查找指定元素的下标，如果找到就返回该元素的下标，否则返回-1
public boolean contains(Object o) {
    return indexOf(o) != -1;
}

// 查找元素的下标位置
public int indexOf(Object o) {
    // 初始值为0，没遍历一次，加1。
    int index = 0;
    // 如果查找的元素是null，那么就返回元素为null的下标
    // 如果查找的元素不为null，那么就遍历链表，直到找到该元素
    // 如果没有找到该元素，那么就返回-1。
    if (o == null) {
        for (Node<E> x = first; x != null; x = x.next) {
            if (x.item == null)
                return index;
            index++;
        }
    } else {
        for (Node<E> x = first; x != null; x = x.next) {
            if (o.equals(x.item))
                return index;
            index++;
        }
    }
    return -1;
}
```
### element方法
该方法用于获取链表中的第一个元素。

```java
public E element() {
    return getFirst();
}

public E getFirst() {
    // 这里其实就是获取第一个节点的元素
    final Node<E> f = first;
    if (f == null)
        throw new NoSuchElementException();
    return f.item;
}
```
### get(int index)方法
该方法用于获取链表中的指定的元素。

```java
public E get(int index) {
    // 检查角标是否越界
    checkElementIndex(index);
    return node(index).item;
}

// 获取链表中指定的index下的节点
// 如果index的值大于size的一半，那么就从后往前遍历，否则就从前往后遍历，直到找到index对应的节点
Node<E> node(int index) {
    // assert isElementIndex(index);

    if (index < (size >> 1)) {
        Node<E> x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    } else {
        Node<E> x = last;
        for (int i = size - 1; i > index; i--)
            x = x.prev;
        return x;
    }
}
```
### getFirst方法
该方法用于获取链表中的第一个元素。
### getLast方法
该方法用于获取链表中的最后一个元素。
### indexOf(Object o)方法
该方法用于查找对应元素第一次在链表中出现的下标位置。

```java
// 查找元素的下标位置
public int indexOf(Object o) {
    // 初始值为0，没遍历一次，加1。
    int index = 0;
    // 如果查找的元素是null，那么就返回元素为null的下标
    // 如果查找的元素不为null，那么就遍历链表，直到找到该元素
    // 如果没有找到该元素，那么就返回-1。
    if (o == null) {
        for (Node<E> x = first; x != null; x = x.next) {
            if (x.item == null)
                return index;
            index++;
        }
    } else {
        for (Node<E> x = first; x != null; x = x.next) {
            if (o.equals(x.item))
                return index;
            index++;
        }
    }
    return -1;
}
```
### lastIndexOf(Object o)方法
该方法用于查找对应元素最后一次在链表中出现的下标位置。这个方法与indexOf方法的区别在于，lastIndexOf是从后往前遍历，而indexOf是从前往后遍历。

```java
// 该方法用于查找对应元素最后一次在链表中出现的下标位置
public int lastIndexOf(Object o) {
    // 获取链表的size
    // 然后从后往前遍历链表，直到找到该元素，并返回下标位置
    int index = size;
    if (o == null) {
        for (Node<E> x = last; x != null; x = x.prev) {
            index--;
            if (x.item == null)
                return index;
        }
    } else {
        for (Node<E> x = last; x != null; x = x.prev) {
            index--;
            if (o.equals(x.item))
                return index;
        }
    }
    return -1;
}
```
### offer(E e)方法
该方法用于在链表末尾添加一个元素。

```java

public boolean offer(E e) {
    return add(e);
}

public boolean add(E e) {
    linkLast(e);
    return true;
}

void linkLast(E e) {
    // 这里获取最后一个节点
    final Node<E> l = last;
    // 创建一个新的节点，并更新节点的前一个节点和后一个节点
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null)
        first = newNode;
    else
        l.next = newNode;
    size++;
    modCount++;
}
```
### offerFirst(E e)和offerLast(E e)方法
这两个方法是在链表的最前面和最后面添加元素。
### peek方法
该方法获取链表的第一个元素，和前面的element方法类似。

```java
public E peek() {
    // 获取第一个元素，如果为链表为空，那么就返回null
    final Node<E> f = first;
    return (f == null) ? null : f.item;
}
```
### peekFirst和peekLast方法
该方法返回链表的第一个元素和最后一个元素，如果是空链表，那么返回null。
### poll方法
该方法表示删除链表的第一个元素，并返回删除的元素。

```java
public E poll() {
    // 获取链表中的第一个元素
    final Node<E> f = first;
    return (f == null) ? null : unlinkFirst(f);
}

private E unlinkFirst(Node<E> f) {
    // assert f == first && f != null;
    // 获取链表中的第一个元素
    final E element = f.item;
    // 获取当前元素的下一个元素
    final Node<E> next = f.next;
    // 删除当前元素
    f.item = null;
    f.next = null; // help GC
    // 更新链表的第一个元素，即将当前元素的下一个元素更新为第一个元素
    first = next;
    // 如果链表的元素大于1个，那么就将更新后的第一个元素的前一个元素设置为null
    // 如果链表的元素只有1个，那么当删除掉第一个元素时，first和last都为null
    if (next == null)
        last = null;
    else
        next.prev = null;
    // 更新size
    size--;
    modCount++;
    return element;
}
```
### pollFirst方法和pollLast方法
删除链表的第一个元素和最后一个元素，如果是空链表，那么返回null，并返回删除的元素。
### pop方法
该方法和poll方法类似。
### push方法
该方法与addFirst方法类似。
### remove方法
- remove()方法，删除链表的第一个元素
- remove(int index)方法，删除链表中指定index的元素
- remove(Object o)方法，删除链表中第一次出现的o元素。
- removeFirst()方法，删除链表中的第一个元素。
- removeLast()方法，删除链表中的最后一个元素。
- removeFirstOccurrence(Object o)方法，删除链表中第一次出现的o元素。（从前往后遍历）
- removeLastOccurrence(Object o)方法，删除链表中最后一次出现的o元素。（从后往前遍历）
### set(int index , E e)方法
该方法用于将index对应的元素设置为新值e，并返回旧值。

```java
public E set(int index, E element) {
    // 检查角标是否越界
    checkElementIndex(index);
    // 获取index对应的元素
    Node<E> x = node(index);
    // 更新元素，并返回旧值
    E oldVal = x.item;
    x.item = element;
    return oldVal;
}
```
### size方法
获取链表的大小。
### iterator方法
该方法返回一个iterator对象，用于集合迭代。这个方法是所有集合都有的一个方法。

```java
public Iterator<E> iterator() {
    return listIterator();
}

public ListIterator<E> listIterator() {
    return listIterator(0);
}

public ListIterator<E> listIterator(final int index) {
    // 检查是否角标越界
    rangeCheckForAdd(index);
    // 创建一个ListItr实例
    // ListItr类继承了Itr类并实现了ListIterator接口
    return new ListItr(index);
}

// Itr内部类实现了Iterator接口
private class Itr implements Iterator<E> {
    // 定义一个光标索引，每次调用next方法就加1
    int cursor = 0;

    // 调用next方法或者previous方法返回对应元素的索引
    // 如果调用remove方法，那么会重新设置这个值为-1
    // 其实这个表示的就是最近一次调用next或previous方法返回的元素的索引下标，如果是调用remove就重新设置为-1
    int lastRet = -1;

     // 如果这两个值不相等，那么表示该集合正在进行并发修改操作
    int expectedModCount = modCount;
    
    // 调用hasNext方法来判断是否还有下一个元素
    // 通过光标索引与集合的size来判断，如果不等于就返回true，如果等于，就返回false，表示集合已经没有下一个元素了
    public boolean hasNext() {
        return cursor != size();
    }
    
    // 获取集合的下一个元素
    public E next() {
        // 检查是否在进行并发修改操作
        checkForComodification();
        // 这里需要使用try/catch来捕获异常，是因为集合的角标越界属性运行时异常，在编译的时候是检查不出来，只有运行时才知道，所以需要用try/catch来捕获
        try {
            // 调用链表的get方法，来获取下标位置对应的元素
            // 光标加1，返回该元素
            int i = cursor;
            E next = get(i);
            lastRet = i;
            cursor = i + 1;
            return next;
        } catch (IndexOutOfBoundsException e) {
            checkForComodification();
            throw new NoSuchElementException();
        }
    }

    public void remove() {
        if (lastRet < 0)
            throw new IllegalStateException();
        checkForComodification();
        
        // 调用集合的remove方法来删除元素
        // 光标减1，并重设lastRet为-1
        try {
            AbstractList.this.remove(lastRet);
            if (lastRet < cursor)
                cursor--;
            lastRet = -1;
            expectedModCount = modCount;
        } catch (IndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }
    //如果modCount和expectedModCount不相等，那么表示当前集合在进行并发操作，因为在单线程中，这两个值一定是相等的
    // 如果不相等就抛出异常
    final void checkForComodification() {
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
    }
}

private class ListItr extends Itr implements ListIterator<E> {
    // 初始化光标位置
    ListItr(int index) {
        cursor = index;
    }
    // 判断是否有上一个元素
    // 如果cursor不等于0，那么表示有上一个元素
    public boolean hasPrevious() {
        return cursor != 0;
    }
    
    // 这里的实现和next类似，获取集合的上一个元素，光标减1，并设置lastRet
    public E previous() {
        checkForComodification();
        try {
            int i = cursor - 1;
            E previous = get(i);
            lastRet = cursor = i;
            return previous;
        } catch (IndexOutOfBoundsException e) {
            checkForComodification();
            throw new NoSuchElementException();
        }
    }
    
    // 获取下一个元素的索引
    public int nextIndex() {
        return cursor;
    }
    
    // 获取上一个元素的索引
    public int previousIndex() {
        return cursor-1;
    }
    
    public void set(E e) {
        // 这里必须要在调用next方法或者previous方法之后再调用set方法来能执行，不然会抛出异常
        if (lastRet < 0)
            throw new IllegalStateException();
        checkForComodification();
        // 通过调用set方法来设置
        try {
            AbstractList.this.set(lastRet, e);
            expectedModCount = modCount;
        } catch (IndexOutOfBoundsException ex) {
            throw new ConcurrentModificationException();
        }
    }
    // 添加元素到链表的第一个位置上。
    public void add(E e) {
        checkForComodification();

        try {
            int i = cursor;
            AbstractList.this.add(i, e);
            lastRet = -1;
            cursor = i + 1;
            expectedModCount = modCount;
        } catch (IndexOutOfBoundsException ex) {
            throw new ConcurrentModificationException();
        }
    }
}
```
### toArray方法
该方法将链表转为数组。

```java
public Object[] toArray() {
    // 创建一个size大小的数组
    Object[] result = new Object[size];
    int i = 0;
    // 将链表中的元素添加到数组中，并返回数组
    for (Node<E> x = first; x != null; x = x.next)
        result[i++] = x.item;
    return result;
}
```