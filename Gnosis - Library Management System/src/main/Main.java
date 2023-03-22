package main;

import facade.LMSFacade;

public class Main {

	public Main() {
		LMSFacade lmsFacade = new LMSFacade();
		lmsFacade.startApplication();
	}

	public static void main(String[] args) {
		new Main();
	}

}
