package i5.las2peer.services.images;


import java.net.HttpURLConnection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import com.fasterxml.jackson.core.JsonProcessingException;

import i5.las2peer.api.Service;
import i5.las2peer.restMapper.HttpResponse;
import i5.las2peer.restMapper.MediaType;
import i5.las2peer.restMapper.RESTMapper;
import i5.las2peer.restMapper.annotations.ContentParam;
import i5.las2peer.restMapper.annotations.Version;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.jaxrs.Reader;
import io.swagger.models.Swagger;
import io.swagger.util.Json;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
 

/**
 * 
 * Image Service
 * 
 * This microservice was generated by the CAE (Community Application Editor). If you edit it, please
 * make sure to keep the general structure of the file and only add the body of the methods provided
 * in this main file. Private methods are also allowed, but any "deeper" functionality should be
 * outsourced to (imported) classes.
 * 
 */
@Path("images/")
@Version("0.1") // this annotation is used by the XML mapper
@Api
@SwaggerDefinition(
    info = @Info(title = "Image Service", version = "0.1",
        description = "A LAS2peer microservice generated by the CAE.",
        termsOfService = "none",
        contact = @Contact(name = "Thomas Winkler", email = "CAEAddress@gmail.com") ,
        license = @License(name = "BSD",
            url = "https://github.com/cae-development/microservice-Image-Service/blob/master/LICENSE.txt") ) )
public class Images extends Service {





  public Images() {
    // read and set properties values
    setFieldValues();

  }

  // //////////////////////////////////////////////////////////////////////////////////////
  // Service methods.
  // //////////////////////////////////////////////////////////////////////////////////////


  /**
   * 
   * putImage
   * 
   * @param image a JSONObject 
   * 
   * @return HttpResponse  
   * 
   */
  @PUT
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @ApiResponses(value = {
       @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "created")
  })
  @ApiOperation(value = "putImage", notes = " ")
  public HttpResponse putImage(@ContentParam String image) {
    JSONObject image_JSON = (JSONObject) JSONValue.parse(image);

    // created
    boolean created_condition = true;
    if(created_condition) {
      JSONObject createdResult = new JSONObject(); 
      createdResult.put("status","stored");
      HttpResponse created = new HttpResponse(createdResult.toJSONString(), HttpURLConnection.HTTP_CREATED);
      return created;
    }
    return null;
  }




  // //////////////////////////////////////////////////////////////////////////////////////
  // Methods required by the LAS2peer framework.
  // //////////////////////////////////////////////////////////////////////////////////////

  
  /**
   * 
   * This method is needed for every RESTful application in LAS2peer. Please don't change.
   * 
   * @return the mapping
   * 
   */
  public String getRESTMapping() {
    String result = "";
    try {
      result = RESTMapper.getMethodsAsXML(this.getClass());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }


}
