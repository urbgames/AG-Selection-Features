package util;

public class PrintStatusChromosome {

	private static void print(String menssage) {
//		System.out.println(menssage);

	}

	public static void add(int gene, int ID) {
		print("Add gene (" + gene + ") from chromosome (" + ID + ")");
	}

	public static void remove(int gene, int ID) {
		print("Remove gene (" + gene + ") from chromosome (" + ID + ")");
	}

	public static void create(int ID) {
		print("Create chromosome (" + ID + ")");
	}

	public static void InitializeSelection() {
		print("Selection Initialized");
	}

	public static void InitializeMutation() {
		print("Mutation Initialized");
	}

	public static void InitializeCrossover() {
		print("Crossover Initialized");
	}

	public static void InitializeFitness() {
		print("Fitness Initialized");
	}

}
