package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.ComponentStatus;
import com.github.drone.subb.IDrone;

public abstract class ComponentSentence extends Sentence {
	
	public ComponentSentence(){
	}
	
	public ComponentSentence(Application app, IDrone drone){
		super(app,drone);
	}

	public abstract boolean checkStatus(ComponentStatus status);

}
