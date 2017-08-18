package storm.contrib.spring.topology;

import org.apache.storm.Config;
import org.apache.storm.generated.StormTopology;

import java.util.HashMap;
import java.util.Map;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/6/12
 */
public class SingleTopologySubmission implements TopologySubmission {

    private final Map<String, StormTopology> stormTopologies;
    private Config config;

    public SingleTopologySubmission(final String topologyId, final StormTopology stormTopology) {
        stormTopologies = new HashMap<String, StormTopology>();
        stormTopologies.put(topologyId, stormTopology);
        this.config = new Config();
    }

    public Map<String, StormTopology> getStormTopologies() {
        return stormTopologies;
    }

    public void setConfig(final Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }
}
