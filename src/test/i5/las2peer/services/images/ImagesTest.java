package i5.las2peer.services.images;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import i5.las2peer.p2p.LocalNode;
import i5.las2peer.p2p.ServiceNameVersion;
import i5.las2peer.restMapper.MediaType;
import i5.las2peer.restMapper.RESTMapper;
import i5.las2peer.restMapper.data.Pair;
import i5.las2peer.restMapper.tools.ValidationResult;
import i5.las2peer.restMapper.tools.XMLCheck;
import i5.las2peer.security.ServiceAgent;
import i5.las2peer.security.UserAgent;
import i5.las2peer.testing.MockAgentFactory;
import i5.las2peer.webConnector.WebConnector;
import i5.las2peer.webConnector.client.ClientResponse;
import i5.las2peer.webConnector.client.MiniClient;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


/**
 * 
 * Image Service - Test Class
 * 
 * This class provides a basic testing framework for the microservice Image Service. It was
 * generated by the CAE (Community Application Framework).
 *
 */
public class ImagesTest {

  private static final String HTTP_ADDRESS = "http://127.0.0.1";
  private static final int HTTP_PORT = WebConnector.DEFAULT_HTTP_PORT;

  private static LocalNode node;
  private static WebConnector connector;
  private static ByteArrayOutputStream logStream;

  private static UserAgent testAgent;
  private static final String testPass = "adamspass";
  
  // version does not matter in tests
  private static final ServiceNameVersion testTemplateService = new ServiceNameVersion(Images.class.getCanonicalName(),"0.1");

  private static final String mainPath = "images/";


  /**
   * 
   * Called before the tests start.
   * 
   * Sets up the node and initializes connector and users that can be used throughout the tests.
   * 
   * @throws Exception
   * 
   */
  @BeforeClass
  public static void startServer() throws Exception {

    // start node
    node = LocalNode.newNode();
    testAgent = MockAgentFactory.getAdam();
    testAgent.unlockPrivateKey(testPass); // agent must be unlocked in order to be stored
    node.storeAgent(testAgent);
    node.launch();

    ServiceAgent testService = ServiceAgent.createServiceAgent(testTemplateService, "a pass");
    testService.unlockPrivateKey("a pass");

    node.registerReceiver(testService);

    // start connector
    logStream = new ByteArrayOutputStream();

    connector = new WebConnector(true, HTTP_PORT, false, 1000);
    connector.setLogStream(new PrintStream(logStream));
    connector.start(node);
    Thread.sleep(1000); // wait a second for the connector to become ready
    testAgent = MockAgentFactory.getAdam();

    connector.updateServiceList();
    // avoid timing errors: wait for the repository manager to get all services before continuing
    try {
      System.out.println("waiting..");
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }


  /**
   * 
   * Test for the putImage method.
   * 
   */
  @Test
  public void testputImage() {
    MiniClient c = new MiniClient();
    c.setAddressPort(HTTP_ADDRESS, HTTP_PORT);
    try {
      JSONObject imageJson = new JSONObject();
      c.setLogin(Long.toString(testAgent.getId()), testPass);
      @SuppressWarnings("unchecked")
      ClientResponse result = c.sendRequest("PUT", mainPath + "/", imageJson.toJSONString(),
        MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON, new Pair[] {});
      assertTrue(true); // change here
      System.out.println("Result of 'testputImage': " + result.getResponse().trim());
    } catch (Exception e) {
      e.printStackTrace();
      fail("Exception: " + e);
    }
  }




  /**
   * 
   * Test the Image Service for valid rest mapping. Important for development.
   * 
   */
  @Test
  public void testDebugMapping() {
    Images cl = new Images();
    String XML_LOCATION = "./restMapping.xml";
    String xml = cl.getRESTMapping();
  
    try {
      RESTMapper.writeFile(XML_LOCATION, xml);
    } catch (IOException e) {
      e.printStackTrace();
    }
    XMLCheck validator = new XMLCheck();
    ValidationResult result = validator.validate(xml);
    if (!result.isValid()) {
      fail();
    }
  }


  /**
   * 
   * Called after the tests have finished. Shuts down the server and prints out the connector log
   * file for reference.
   * 
   * @throws Exception
   * 
   */
  @AfterClass
  public static void shutDownServer() throws Exception {

    connector.stop();
    node.shutDown();

    connector = null;
    node = null;

    LocalNode.reset();

    System.out.println("Connector-Log:");
    System.out.println("--------------");

    System.out.println(logStream.toString());

  }

}
