package com.github.drone.subb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import SimpleTester.TesterThread;

public class textParser {

	
	public void parseTestFiles(TesterThread cum) throws IOException{

		BufferedReader br = new BufferedReader(new FileReader("/home/ben/rosjava/src/drone/subb/src/main/java/prop"));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.equals("testfiles")){
				String featureLine;
				while (!(featureLine = br.readLine()).toLowerCase().equals("testfiles")){
					cum.addFeature(featureLine);
				}
			}
		}
		br.close();
	}

}
