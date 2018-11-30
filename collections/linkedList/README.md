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
    // 如果插入的位置刚好是LinkedList的最后一个，那么将当前节点的前一个节点设置为插入之前的最后一个节点
    if (index == size) {
        succ = null;
        pred = last;
    } else {
        succ = node(index);
        pred = succ.prev;
    }
    // 将集合中的数据插入到节点上
    for (Object o : a) {
        @SuppressWarnings("unchecked") E e = (E) o;
        Node<E> newNode = new Node<>(pred, e, null);
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        pred = newNode;
    }

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


