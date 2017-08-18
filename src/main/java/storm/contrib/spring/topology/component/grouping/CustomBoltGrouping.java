package storm.contrib.spring.topology.component.grouping;

import org.apache.storm.grouping.CustomStreamGrouping;
import org.apache.storm.topology.BoltDeclarer;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/4/12
 */
public class CustomBoltGrouping extends GenericBoltGrouping implements IBoltGrouping {

    public CustomStreamGrouping customStreamGrouping;

    public CustomBoltGrouping(final String componentId, final String streamId, final CustomStreamGrouping customStreamGrouping) {
        super(componentId, streamId);
        this.customStreamGrouping = customStreamGrouping;
    }

    public CustomBoltGrouping(final String componentId, final CustomStreamGrouping customStreamGrouping) {
        super(componentId);
        this.customStreamGrouping = customStreamGrouping;
    }

    public void addToBolt(final BoltDeclarer boltDeclarer) {
        if (streamId == null) {
            boltDeclarer.customGrouping(componentId, customStreamGrouping);
        } else {
            boltDeclarer.customGrouping(componentId, streamId, customStreamGrouping);
        }
    }
}
