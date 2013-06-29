import org.drools.KnowledgeBase;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class MainApp {
    private final static String changeSetPath = ".\\src\\main\\resources\\ChangeSet.xml";
    private static KnowledgeBase kbase;
    private static KnowledgeAgent kagent;

    public static void main(final String[] args) {
        final StatefulKnowledgeSession session = getStatefulKnowledgeSession();

        session.fireAllRules();
    }

    private static StatefulKnowledgeSession getStatefulKnowledgeSession() {
        if (kbase == null) {
            setupKbase();
            System.out.println("yes");
        }
        return kbase.newStatefulKnowledgeSession();
    }

    private static void setupKbase() {
        //set up kagent..
        kagent = KnowledgeAgentFactory.newKnowledgeAgent("MyAgent");
        final Resource changeSet = ResourceFactory.newFileResource(changeSetPath);
        kagent.applyChangeSet(changeSet);

        //get the knowledge base.. 
        kbase = kagent.getKnowledgeBase();
    }
}
