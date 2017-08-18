package storm.contrib.spring.topology.component.spout;

import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.SpoutDeclarer;
import org.apache.storm.topology.TopologyBuilder;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/3/12
 */
public class RichSpout extends GenericSpout {

    private final IRichSpout stormSpout;

    public RichSpout(final String componentId, final IRichSpout stormSpout) {
        super(componentId);
        this.stormSpout = stormSpout;
    }

    public IRichSpout getStormSpout() {
        return stormSpout;
    }

    public void addToTopology(final TopologyBuilder builder) {
        SpoutDeclarer spoutDeclarer;
        if (parallelismHint == null) {
            spoutDeclarer = builder.setSpout(componentId, stormSpout);
        } else {
            spoutDeclarer = builder.setSpout(componentId, stormSpout, parallelismHint);
        }
        addConfigToComponent(spoutDeclarer);
    }
}
