package storm.contrib.spring.topology.component.spout;

import org.apache.storm.topology.IComponent;
import org.apache.storm.topology.TopologyBuilder;
import storm.contrib.spring.topology.component.IComponentConfig;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/5/12
 */
public interface ISpout<T extends IComponent> extends IComponentConfig {

    public String getComponentId();

    public T getStormSpout();

    public void addToTopology(final TopologyBuilder builder);

}
