package com.github.drone.subb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import SimpleTester.TesterThread;

public class textParser {

	
	public void parseTestFiles(TesterThread cum) throws IOException{
		
		File dir = new File(System.getProperty("user.dir"));
        dir =  dir.getParentFile().getParentFile().getParentFile().getParentFile();
		BufferedReader br = new BufferedReader(new FileReader(dir+ "/prop"));
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
