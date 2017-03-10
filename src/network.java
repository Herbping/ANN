import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import weka.core.Instances;

public class network {

	public static void main(String[] args) throws IOException {

		BufferedReader train_reader = new BufferedReader(new FileReader(args[0]));
		Instances train_data = new Instances(train_reader);
		nerualnet net = new nerualnet(train_data, Integer.parseInt(args[1]), Double.parseDouble(args[2]),
				Integer.parseInt(args[3]));

		net.print_weights();

	}

}

class nerualnet {
	int numUnit;
	int num_folds;
	double rate;
	Instances train_data;
	int num_epochs;

	List<List<Double>> hidden_weights;
	List<Double> output_weights;

	nerualnet(Instances train_data, int folds, double rate, int epochs) {
		this.train_data = train_data;
		this.num_folds = folds;
		this.rate = rate;
		this.num_epochs = epochs;

		this.numUnit = train_data.numAttributes();
		this.hidden_weights = new ArrayList<List<Double>>();
		output_weights = new ArrayList<Double>();
		for (int i = 0; i < numUnit - 1; i++) {
			List<Double> tmp = new ArrayList<Double>();
			for (int j = 0; j < numUnit; j++) {
				tmp.add(ThreadLocalRandom.current().nextDouble());
				if(i == 0){
					output_weights.add(ThreadLocalRandom.current().nextDouble());
				}
			}
			hidden_weights.add(tmp);
		}
	}

	public void print_weights() {
		System.out.println(hidden_weights);
		System.out.println(output_weights);
	}
}