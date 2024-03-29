package reprotool.ling.benchmark;

import java.util.ArrayList;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

import reprotool.ling.LingConfig;
import reprotool.ling.LingFactory;
import reprotool.ling.LingTools;
import reprotool.ling.Sentence;
import reprotool.ling.SentenceNode;
import reprotool.ling.Word;
import reprotool.ling.analyser.Analyser;
import reprotool.ling.analyser.FindConstituent;
import reprotool.ling.benchmark.AnalyseBenchmark.ActionCode;
import reprotool.ling.impl.SentenceImpl;
import reprotool.model.linguistic.action.AbortUseCase;
import reprotool.model.linguistic.action.FromSystem;
import reprotool.model.linguistic.action.Goto;
import reprotool.model.linguistic.action.InternalAction;
import reprotool.model.linguistic.action.ToSystem;
import reprotool.model.linguistic.action.UseCaseInclude;
import reprotool.model.swproj.Actor;
import reprotool.model.swproj.SoftwareProject;
import reprotool.model.swproj.SwprojFactory;
import reprotool.model.usecase.Scenario;
import reprotool.model.usecase.UseCase;
import reprotool.model.usecase.UseCaseStep;
import reprotool.model.usecase.UsecaseFactory;

/**
 * @author ofiala
 * 
 */
public class BenchmarkSentence extends SentenceImpl {
	// basic parameters
	private String id = "";
	private String sentence = "";
	private Sentence sentenceObject = LingFactory.eINSTANCE.createSentence();
	private ArrayList<Actor> actors = new ArrayList<Actor>(LingConfig.actors);
	
	// same object for given and analysed results
	class BenchmarkResults {
		public int subjectNumber = 0;
		public String verbLemma = "";
		public int objectNumbers [] = new int [1];
		public int indirectObjectNumber = 0;
		public ActionCode actionCode = ActionCode.X;
	}
	// results and stats
	BenchmarkResults inResults = new BenchmarkResults();
	BenchmarkResults outResults = new BenchmarkResults();
	
	// is it parsed?
	private boolean parsed = false;
	// is it analysed?
	private boolean analysed = false;
	
	public BenchmarkSentence(String line) {
		// parse csv line
		String[] fields = line.split(";");
		// minimum arguments we need
		if (fields.length >= 3) {
			id = fields[0];
			sentence = fields[1];
			// add all new actors
			for(String acstr : fields[2].split(",")) {
				Actor ac = SwprojFactory.eINSTANCE.createActor();
				ac.setName(acstr.toLowerCase().trim());
				actors.add(ac);
			}
			if (fields.length >= 4) {
				try {
					inResults.subjectNumber = Integer.parseInt(fields[3]);
				} catch (NumberFormatException e) {}
			}
			if (fields.length >= 5) 
				inResults.verbLemma = fields[4];
			// we have more
			if (fields.length >= 6) {
				try {
					String[] ints = fields[5].split(",");
					inResults.objectNumbers = new int[ints.length];
					for (int i = 0; i < ints.length; i++) {
						inResults.objectNumbers[i] = Integer.parseInt(ints[i]);
					}
				} catch (NumberFormatException e) {}
				if (fields.length >= 7) {
					try {
						inResults.indirectObjectNumber = Integer.parseInt(fields[6]);
					} catch (NumberFormatException e) {}
					if (fields.length == 8)
						try {
							inResults.actionCode = ActionCode.valueOf(fields[7]);
						} catch (IllegalArgumentException e) {
							inResults.actionCode = ActionCode.X;
						}
				}
			}
		}
	}

	/**
	 * Get ID string of current sentence
	 * 
	 * @return id string
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Was this sentence analysed?
	 * 
	 * @return analysed
	 */
	public boolean isAnalysed(){
		return analysed;
	}
	
	/**
	 * Was this sentence parsed?
	 * 
	 * @return parsed
	 */
	public boolean isParsed(){
		return parsed;
	}
	
	/**
	 * Parse and fill parent Sentence object
	 * 
	 * @return boolean parsing success
	 */
	public boolean parse() {
		// empty string
		if (sentence.isEmpty()) 
			return false;

		// parent object
		sentenceObject = LingTools.parseSentence(sentence);
		
		if (sentenceObject == null) {
			return false;
		} else {
			// move created structures
			words = sentenceObject.getWords();
			sentenceTree = sentenceObject.getSentenceTree();
			passive = sentenceObject.isPassive();
			// set stat variable
			parsed = true;
			return true;
		}
	}
	
	/**
	 * Convert sentence object to string
	 * 
	 * @return String all sentence variables
	 */
	public String toString() {
		// printing objects array in nice way
		String inOb = "";
		for (int a : inResults.objectNumbers){
			inOb = inOb + a + ",";
		}
		if (inOb.endsWith(",")) inOb = inOb.substring(0, inOb.length()-1);
		
		// result
		String result = "ID: " + id + " SENTENCE: " + sentence + " SUBJECT: "
				+ inResults.subjectNumber + " VERB: " + inResults.verbLemma + " OBJECT: "
				+ inOb + " INDIRECT_OBJECT: " + inResults.indirectObjectNumber
				+ " ACTION: " + inResults.actionCode;
		return result;
	}

	/**
	 * Analyse Sentence object
	 * 
	 * @return boolean analyse success
	 */
	public boolean analyse() {
		// success - just in case of crash
		boolean result = true;
		// yet not parsed
		if (this.getSentenceTree() == null)
			return false;
		// find subjects
		Word word = FindConstituent.findSubject(this, this.actors);
		if (word != null) {
			int i = this.getIndexOf(word);
			if (i >= 0) {
				// data words counting from 1
				this.outResults.subjectNumber = i + 1;
			}
		}
		// find verbs
		word = FindConstituent.findMainVerb(this);
		if (word != null) {
			int i = this.getIndexOf(word);
			if (i >= 0) {
				// ignore case just for wrong data input
				this.outResults.verbLemma = word.getLemma();
			}
		}
		// find objects
		// find indirect object
		ArrayList<Word> words = FindConstituent.findIndirectObject(this, actors);
		// mark indirect object
		if (words.size() > 0) {
			// first object
			word = words.get(0);
			int i = this.getIndexOf(word);
			if (i >= 0) {
				// ignore case just for wrong data input
				this.outResults.indirectObjectNumber = i + 1;
			}
			// find representative objects
			words = FindConstituent.findRepresentativeObject(this, (SentenceNode)words.get(0).getParent());
		} else {
			words = FindConstituent.findRepresentativeObject(this, null);
		}
		// add representative objects
		this.outResults.objectNumbers = new int [words.size()];
		for(int k = 0; k < words.size() ; k++){
			// all objects
			int i = this.getIndexOf(words.get(k));
			if (i >= 0) {
				// ignore case just for wrong data input
				this.outResults.objectNumbers[k] = i + 1;
			} else {
				this.outResults.objectNumbers[k] = 0;			
			}
		}
		if(words.size() == 0)
			this.outResults.objectNumbers = new int [] {0};
//			this.outResults.objectNumbers = new int [] {0,2};

		// create SoftwareProject for analysis
		SoftwareProject sw = SwprojFactory.eINSTANCE.createSoftwareProject();
		sw.setName("TEST");
		sw.getActors().addAll(actors);
		// create UseCase for analysis
		UseCase uc = UsecaseFactory.eINSTANCE.createUseCase();
		uc.setId("TEST");
		uc.setName("TEST");
		uc.setSoftwareProjectShortcut(sw);
		// create UseCaseStep for analysis
		UseCaseStep ucs = UsecaseFactory.eINSTANCE.createUseCaseStep();
		ucs.setContent(sentence);
		// create Scenario for analysis
		Scenario scenario = UsecaseFactory.eINSTANCE.createScenario();
		scenario.getSteps().add(ucs);
		uc.setMainScenario(scenario);
		sw.getUseCases().add(uc);
		// analyse sentence
		EditingDomain editingDomain = null;		
		Command command = Analyser.analyseTree(editingDomain, ucs, sentenceObject);
		command.execute();
		
		// detect action code
		if(ucs.getAction() instanceof AbortUseCase)
			outResults.actionCode = ActionCode.ABORT;
		if(ucs.getAction() instanceof FromSystem)
			outResults.actionCode = ActionCode.FROM_SYSTEM;
		if(ucs.getAction() instanceof Goto)
			outResults.actionCode = ActionCode.GOTO;
		if(ucs.getAction() instanceof UseCaseInclude)
			outResults.actionCode = ActionCode.INCLUDE;
		if(ucs.getAction() instanceof InternalAction)
			outResults.actionCode = ActionCode.INTERNAL;
		if(ucs.getAction() instanceof ToSystem)
			outResults.actionCode = ActionCode.TO_SYSTEM;

		System.out.println(this.id + ": detected action " + ucs.getAction());		
			
		return result;
	}

	/**
	 * Gets index of a word in sentence, strips interpunction
	 * substitution for this.getWords().indexOf(word)
	 * 
	 * @param word to find
	 * @return index or -1 when word isn't in sentence
	 */
	private int getIndexOf(Word word) {
		// resutl
		int x = this.getWords().indexOf(word);
		// how many interpunction to jump
		int jump = 0;
		
		// remove all non word elements
		if (x != -1) {
			for (int i = 0; i < x ; i++){
				if(this.getWords().get(i).isInterpunction())
					jump++;
			}
		}

		return (x - jump);
	}
}
