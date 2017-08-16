package thread;

import java.util.Random;

import ag.AG;
import classificator.Classification;
import systemStatusControl.StatusGA;

public class ClassificatorThread {

	public ClassificatorThread(int sizePopulation, int maxInteration, int repetition, ThreadManager observer,
			String Classifier, StatusGA... statusPSO) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					// remover esse seed
					int seed;
					if (statusPSO.length > 0)
						seed = statusPSO[0].getSeed();
					else
						seed = new Random().nextInt();
					new AG(sizePopulation, maxInteration, repetition, new Classification(Classifier, seed), seed, statusPSO);
					System.out.println(Classifier + " Fim da intera��o " + repetition);
					observer.update(Classifier);
				} catch (Exception e) {
				}
			}
		});
		thread.start();
	}

}
