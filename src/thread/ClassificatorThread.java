package thread;

import ag.AG;
import classificator.Classification;

public class ClassificatorThread{

	public ClassificatorThread(int sizePopulation, int maxInteration, int repetition, ThreadManager observer,
			String Classifier) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					new AG(sizePopulation, maxInteration, repetition, new Classification(Classifier));
					System.out.println(Classifier + " Fim da interação " + repetition);
					observer.update(Classifier);
				} catch (Exception e) {
				}
			}
		});
		thread.start();
	}

}
