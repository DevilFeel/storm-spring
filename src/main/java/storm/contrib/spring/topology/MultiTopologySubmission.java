package storm.contrib.spring.topology;

import org.apache.storm.Config;
import org.apache.storm.generated.StormTopology;

import java.util.Map;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/3/12
 */
public class MultiTopologySubmission implements TopologySubmission {

    private final Map<String, StormTopology> stormTopologies;
    private Config config;

    public MultiTopologySubmission(final Map<String, StormTopology> stormTopologies) {
        this.stormTopologies = stormTopologies;
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
