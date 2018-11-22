# java泛型
泛型，指的是参数化类型，就是将类型作为参数（类似函数中的形参），然后再需要使用或者调用的时候再将具体的类型作为参数传递（类似函数中的实参）。

泛型的目的是为了将类型参数化，也就是说在使用泛型的过程中，操作的数据类型被指定为一个参数，而这种参数可以用在类，方法，接口中，分别叫做泛型类，泛型方法，泛型接口。

当我们使用容器用来储存不同类型对象的时候，如果不使用泛型，那么我们在获取对象的时候，必须进行强制类型转换，才能得到我们想要的具体类型，比如：
```
public static void main (String[] args) {
	ArrayList list = new ArrayList();
	list.add(123);
	list.add("andy");
	// 这里必须进行强制类型转换，不然在编译时就会报错
	int num = (int)list.get(0);
	String str = (String)list.get(1);
}
```
上面代码可以看出，如果我们要获取具体类型，那么必须先进行强制类型转换，否则会报错。通过使用泛型就不需要我们去强制转换，比如：
```
public static void main (String[] args) {
	ArrayList<String> list = new ArrayList<String>();
	list.add("andy");
	String str = list.get(0);
	System.out.println(str);
}
```
通过上面的例子，我们会发现，使用泛型，消除了类型转换。