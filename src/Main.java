import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		List<Integer> list1 = Stream
				.iterate(new Random().nextInt(1, 100)
						, x -> new Random().nextInt(1, 100))
				.limit(22)
				.toList();
		System.out.println(list1);
		findMinMax(list1.stream()
				, Comparator.naturalOrder()
				, (i1, i2) -> System.out.printf("min = %s, max = %s", i1, i2));
		findEvenNumber(list1);


		System.out.println("\nВыделение из списка четных чисел:");
		List<Integer> list2 = Stream.generate(Main::generateInt).limit(15).toList();
		System.out.println(list2);
		System.out.print("Список четных:\n" + list2.stream()
				.filter(x -> x % 2 == 0)
				.toList());
		findEvenNumber(list2);
	}

	public static Integer generateInt() {
		return new Random().nextInt(1, 100);
	}

	public static <A> void findMinMax(Stream<? extends A> stream
			, Comparator<? super A> order
			, BiConsumer<? super A, ? super A> minMaxConsumer) {
		List<? extends A> list = stream.sorted(order).toList();
		minMaxConsumer.accept(list.isEmpty() ? null : list.stream().min(order).get()
				, list.isEmpty() ? null : list.stream().max(order).get());
	}

	public static void findEvenNumber(List<Integer> list) {
		System.out.println();
		System.out.println(list.stream().filter(x -> x % 2 == 0).count() + " из " + list.size() + " чётные");
	}
}