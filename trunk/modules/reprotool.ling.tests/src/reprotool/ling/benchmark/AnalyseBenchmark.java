package reprotool.ling.benchmark;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import reprotool.ling.tools.Parser;

/**
 * @author ofiala
 * 
 */
public class AnalyseBenchmark {
	// default file name
	String fileName = "data.csv";
	// loaded sentences
	ArrayList<BenchmarkSentence> sentences = new ArrayList<BenchmarkSentence>();
	
	public enum ActionCode {
		GOTO,
		ABORT,
		INTERNAL,
		FROM,
		TO,
		INCLUDE,
		X;
	}


	/**
	 * Analyse selected loaded sentence
	 * 
	 * @return boolean success at sentence
	 */
	public boolean analyse (int i) {
		return sentences.get(i).analyse();
	}
	
	/**
	 * Analyse all loaded sentences
	 * 
	 * @return boolean success at all sentences
	 */
	public boolean analyseAll() {
		// result
		boolean result = true;
		for (BenchmarkSentence bs : sentences) {
			result = result && bs.analyse();
		}
		return result;
	}
	
	public String getResults () {
		// result
		String result;
		// errors
		String error = "";
		// subjects stats
		int subjects = 0;
		int foundSubjects = 0;
		for (BenchmarkSentence bs : sentences) {
			if (bs.inResults.subjectNumber > 0) {
				subjects++;
				if (bs.outResults.subjectNumber == bs.inResults.subjectNumber)
					foundSubjects++;
				else
					error += bs.getId() + ": input subjectNumber: "
							+ bs.inResults.subjectNumber
							+ " output subjectNumber: "
							+ bs.outResults.subjectNumber + "\n";
			}

		}
		result = "SUBJECTS: " + "Count: " + subjects + " Found: "
				+ foundSubjects + " | "
				+ (int) ((foundSubjects * 100) / subjects) + "%\n";
		result += error;

		// verbs stats
		int verbs = 0;
		int foundVerbs = 0;
		error = "";
		for (BenchmarkSentence bs : sentences) {
			if(bs.inResults.subjectNumber > 0) {
				verbs++;
				if (bs.inResults.verbLemma.equalsIgnoreCase(bs.outResults.verbLemma))
					foundVerbs++;
				else
					error += bs.getId() + ": input verbLemma: \""
							+ bs.inResults.verbLemma
							+ "\" output verbLemma: \""
							+ bs.outResults.verbLemma + "\"\n";
			}

		}		
		result += "VERBS: " + "Count: " + verbs + " Found: " + foundVerbs
				+ " | " + (int) ((foundVerbs * 100) / verbs) + "%\n";
		result += error;
		
		// objects stats
		int objects = 0;
		int foundObjects = 0;
		error = "";
		for (BenchmarkSentence bs : sentences) {
			if(bs.inResults.objectNumber > 0) {
				objects++;
				if (bs.outResults.objectNumber == bs.inResults.objectNumber)
					foundObjects++;
				else
					error += bs.getId() + ": input objectNumber: "
							+ bs.inResults.objectNumber
							+ " output objectNumber: "
							+ bs.outResults.objectNumber + "\n";
			}

		}		
		result += "OBJECTS: " + "Count: " + objects + " Found: " + foundObjects + " | " + (int)((foundObjects * 100)/objects) + "%\n";
		result += error;
		
		return result;
	}
	
	/**
	 * Number of loaded sentences
	 * 
	 * @return int number of loaded sentences
	 */
	public int getSentenceCount() {
		return sentences.size();
	}
	
	public boolean loadData () {
		return loadData("");
	}	
	
	public boolean loadData (String path) {
		// success
		boolean result = false;
		
		// input
		if (path.isEmpty()) {
				String rootPath = new java.io.File(Parser.class.getResource("/").getPath()).getParentFile().getParent();
				path = rootPath + "/../tools/benchmark/" + fileName;
		}
		
		File dataFile = new File(path);
		try {
			InputStream in = new FileInputStream(dataFile);
			String line;
			// buffering
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			boolean body = false;
			while ((line = br.readLine()) != null) {
				if(!line.isEmpty()) {
					if(body) {
						BenchmarkSentence bs = new BenchmarkSentence(line);
						sentences.add(bs);
					} else {
						// skip heading of table
						body = true;
					}
				}

			}
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * Parse all loaded sentences
	 * 
	 * @return boolean success at all sentences
	 */
	public boolean parseAll() {
		// result
		boolean result = true;
		for (BenchmarkSentence bs : sentences) {
			result = result && bs.parse();
		}
		return result;
	}

	/**
	 * Parse selected loaded sentence
	 * 
	 * @return boolean success at sentence
	 */
	public boolean parse(int i) {
		return sentences.get(i).parse();
	}
	
}