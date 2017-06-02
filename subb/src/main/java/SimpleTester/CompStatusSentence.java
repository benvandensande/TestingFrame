package SimpleTester;

import com.github.drone.subb.ComponentStatus;

public class CompStatusSentence extends Sentence {
	
	private ComponentStatus status = null;
	private ComponentSentence sentence;

	public CompStatusSentence(String status, ComponentSentence sent){
		this.status = ComponentStatus.valueOf(status);
		this.sentence = sent;	
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		return this.sentence.checkStatus(status);
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		return this.sentence.checkStatus(status);
	}

	@Override
	public boolean runThen() {
		return this.sentence.checkStatus(status);
	}

}
