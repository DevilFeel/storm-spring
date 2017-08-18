package storm.contrib.spring.topology.component.grouping;

import org.apache.storm.generated.GlobalStreamId;
import org.apache.storm.generated.Grouping;
import org.apache.storm.topology.BoltDeclarer;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/4/12
 */
public class GroupBoltGrouping implements IBoltGrouping {

    private GlobalStreamId globalStreamId;
    private Grouping grouping;

    public GroupBoltGrouping(final GlobalStreamId globalStreamId, final Grouping grouping) {
        this.globalStreamId = globalStreamId;
        this.grouping = grouping;
    }

    public GlobalStreamId getGlobalStreamId() {
        return globalStreamId;
    }

    public Grouping getGrouping() {
        return grouping;
    }

    public void addToBolt(final BoltDeclarer boltDeclarer) {
        boltDeclarer.grouping(globalStreamId, grouping);
    }
}
