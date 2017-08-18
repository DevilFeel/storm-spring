package storm.contrib.spring.topology;

import org.apache.storm.LocalCluster;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.utils.Utils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Map;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/3/12
 */
public final class TopologySubmitterLocal {

    private TopologySubmitterLocal() {}

    private static void validateArgs(final String[] args) {
        if (args[0] == null) {
            throw new IllegalArgumentException("Argument 1: XmlApplicationContext was not defined");
        }

        if (args[1] == null) {
            throw new IllegalArgumentException("Argument 2: TopologySubmission bean was not defined");
        }
    }

    private static void submitTopologies(final TopologySubmission topologySubmission) throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {
        LocalCluster cluster = new LocalCluster();
        for (Map.Entry<String, StormTopology> entry : topologySubmission.getStormTopologies().entrySet()) {
//            StormSubmitter.submitTopology(entry.getKey(), topologySubmission.getConfig(), entry.getValue());
            cluster.submitTopology(entry.getKey(), topologySubmission.getConfig(), entry.getValue());

            Utils.sleep(10000);
            cluster.shutdown();
        }
    }

    public static void main(final String[] args) throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {
//        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:app.xml"); // 从类文件目录中获取
        final ApplicationContext applicationContext = new FileSystemXmlApplicationContext("file:/home/test/app.xml"); //从系统文件路径获取
        final TopologySubmission topologySubmission = (TopologySubmission) applicationContext.getBean("topologySubmission");
        submitTopologies(topologySubmission);
    }
}
