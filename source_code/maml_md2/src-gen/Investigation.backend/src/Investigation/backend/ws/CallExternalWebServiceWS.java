package Investigation.backend.ws;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Investigation.backend.Config;
import Investigation.backend.entities.RequestDTO;
import Investigation.backend.entities.RequestDTO.RequestMethod;

@Path("/externalWS")
@Stateless
public class CallExternalWebServiceWS {
    
    private final static Logger LOGGER = Logger.getLogger(CallExternalWebServiceWS.class.getName());
    
    /**
     * Receives a json-encoded object containing a url, a REST method type and a set of parameters.
     * Based on this data, a new request is created.
     * @param dto contains the request data.
     * @return A response signaling success or failure of the request.
     */
    @POST
    @Path("/callExternalWS")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response getMethod(RequestDTO dto) {
        
        logRequest(dto);
        Boolean responseOk = false;
        int code = 0;
        try {
            URL url;
            RequestMethod type = dto.getRequestMethod();
            
            // Add query parameters to URL
            if(dto.getQueryParams() != null){
                String urlParams = buildUrlParameters(dto.getQueryParams());
                url = new URL(dto.getUrl() + urlParams);
            } else {
                url = new URL(dto.getUrl());
            }

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(type.toString());
            
            if(type != RequestMethod.GET){
                // Only support JSON-encoded body data
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                
                // JSON-encode body content
                if(dto.getBody() != null){
                    String postParams = JSONValue.toJSONString(dto.getBody());
                    OutputStream os = conn.getOutputStream();
                    os.write(postParams.getBytes());
                    os.flush();
                }
            } else {
                conn.setDoOutput(false);
            }
            
            // Check if request was successful
            code = conn.getResponseCode();
            responseOk = (code == 200);
            
            // TODO: Implement real logging of requests
            logRequestResult(conn);
            
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (responseOk) {
            return Response.ok()
                    .header("MD2-Model-Version", Config.MODEL_VERSION).build();
        } else {
            return Response.status(502)
                    .header("MD2-Model-Version", Config.MODEL_VERSION).build();
        }
    }
    
    /**
     * Creates a string containing URL parameters, that can be added to a normal URL.
     * Example: {"hello": "world", "example": 42} will return '?hello=world&example=42'
     * @param map a HashMap containing all query parameters
     * @return a String containing query parameters
     */
    private String buildUrlParameters(List<RequestDTO.CustomHashMapEntry> map){
        String urlParams = "?";
        
        try {
            for (RequestDTO.CustomHashMapEntry entry : map) {
                urlParams += URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue().toString(), "UTF-8") + "&";
            }
            // remove trailing "&"
            urlParams = urlParams.substring(0, urlParams.length() - 1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlParams;
    }
    
    /**
     * Logs the result of a HttpURLConnection
     * @param conn the HttpURLConnection
     * @throws IOException when the inputStream cannot be read
     */
    private void logRequestResult(HttpURLConnection conn) throws IOException {
        String output;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            LOGGER.info("Server response content:");
            while ((output = br.readLine()) != null) {
                LOGGER.info(output);
            }
        } catch (IOException e) {
            throw e;
        }
    }
    
    private void logRequest(RequestDTO dto){
        LOGGER.info("New " + dto.getRequestMethod() + " request to " + dto.getUrl());
        if(dto.getQueryParams() != null){
            LOGGER.info("Query Params: " + dto.getQueryParams().toString());
        }
        if(dto.getBody() != null){
            LOGGER.info("Body Params: " + dto.getBody().toString());
        }
    }
}
