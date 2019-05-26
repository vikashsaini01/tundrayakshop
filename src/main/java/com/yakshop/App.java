package com.yakshop;

import java.util.List;

import com.yakshop.dataloader.Source;
import com.yakshop.dataloader.YakLoader;
import com.yakshop.dataloader.YakXmlLoaderImpl;
import com.yakshop.model.Yak;
import com.yakshop.model.YakProduce;

public class App {

	YakLoader yakLoader;
	Source source;
	QueryYaks queryYaks;
	int dayZeroWools ;

	public App(String xmlFilePath) {
		yakLoader = new YakXmlLoaderImpl();
		source = new Source.DefaultXmlSource(xmlFilePath);
		queryYaks = new QueryYaks();
	}

	public static void main(String[] args) {
		validateInput(args);
		String filePath = args[0];
		int daysElapsed = Integer.parseInt(args[1]);
		App app = new App(filePath);
		List<Yak> yaks = app.yakLoader.loadYaks(app.source);
		app.dayZeroWools = (int) yaks.stream().filter(yak -> yak.isAlive() && yak.isProducingWool()).count();
		app.displayStock(app.queryYaks.calculateInStockProduce(yaks, daysElapsed));
		app.displayHerd(yaks);
	}

	public void displayStock(YakProduce produce) {
		System.out.println("In Stock");
		System.out.println("\t" + produce.getMilkInLitres() + " Liters of Milk");
		System.out.println("\t" + (produce.getNoOfWools()+this.dayZeroWools) + " skins of wool");
		System.out.println("Herd");
	}

	public void displayHerd(List<Yak> yaks) {
		yaks.stream().filter(yak->yak.isAlive()).forEach(yak -> System.out.println(yak.toString()));
	}

	private static void validateInput(String... args) {
		if (args == null || args.length < 2 || args[0] == null || args[1] == null || args[0].isEmpty()
				|| args[1].isEmpty()) {
			throw new IllegalArgumentException(getDisplayMsg());
		}
	}

	private static String getDisplayMsg() {
		return "\n \n The program should take 2 parameters:\n" + "1. The XML file to read, provide complete path \n"
				+ "2. An integer T, representing the elapsed time in days.\n"
				+ "N.B. T=13 means that day 12 has elapsed, but day 13 has yet to begin";
	}
}
