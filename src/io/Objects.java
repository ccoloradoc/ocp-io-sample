package io;

import io.pojo.Animal;
import io.utils.PerformanceMonitor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Objects {
	public static void main(String... args) {
		File destiny = new File("cp/object/animal_list.sr");
		List<Animal> animalList = new ArrayList<>();
		animalList.add(new Animal("Jumpio", 23.0));
		animalList.add(new Animal("Lily", 13.0));
		animalList.add(new Animal("Lexa", 18.0));
		animalList.add(new Animal("Chona", 18.0));
		animalList.add(new Animal("Aluxe", 18.0));

		serialize(destiny, animalList);

		List<Animal> readList = deserialize(destiny);
		System.out.println(readList);
	}

	public static void serialize(File destiny, List<Animal> list) {
		PerformanceMonitor performanceMonitor = new PerformanceMonitor("ObjectWrite");
		new File(destiny.getParent()).mkdirs();

		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(destiny))) {
			for(Animal a: list) {
				out.writeObject(a);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			performanceMonitor.finish().report();
		}
	}

	public static List<Animal> deserialize(File source) {
		List<Animal> list = new ArrayList<>();
		PerformanceMonitor monitor = new PerformanceMonitor("ObjectRead");

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(source))) {
			while (true) {
				Object obj = in.readObject();
				if (obj instanceof Animal) {
					list.add((Animal) obj);
				}
			}
		} catch(EOFException e) {
			System.out.println("Finish reading...");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			monitor.finish().report();
		}

		return list;
	}
}
