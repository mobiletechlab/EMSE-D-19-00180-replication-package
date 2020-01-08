package Investigation.backend.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The RequestDTO encapsulates all request data that is sent by the client.
 * It can be used to create a corresponding REST request.
 * 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Provides all possible HTTP methods.
     * Can later be extended to support more types.
     */
    public enum RequestMethod {
        GET, POST, PUT, DELETE
    }
    
    @XmlElement
    protected String url;
    
    @XmlElement
    protected RequestMethod requestMethod;
    
    @XmlElement
    protected List<CustomHashMapEntry> queryParams = new ArrayList<CustomHashMapEntry>();
    
    @XmlElement
    protected List<CustomHashMapEntry> body = new ArrayList<CustomHashMapEntry>();
    
    public RequestDTO (){}   
    
    
    /* Getter and Setter */
    
    public String getUrl() {
        return url;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public List<CustomHashMapEntry> getQueryParams() {
        return queryParams;
    }

    public List<CustomHashMapEntry> getBody() {
        return body;
    }
    
        
    public void setUrl(String url){
        this.url = url;
    }

    public void setRequestMethod(RequestMethod method){
    	this.requestMethod = method;
    }    

    public void setQueryParams(List<CustomHashMapEntry> queryparams){
    	this.queryParams = queryparams;
    }    

    public void setBody(List<CustomHashMapEntry> body){
    	this.body = body;
    }

    @XmlRootElement
    public static class CustomHashMapEntry {
    
    	@XmlElement
        public String key; 

    	@XmlElement
        public String value;
    
        public String getKey() {
            return key;
    	}

    	public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
    		return value;
    	}

    	public void setValue(String value) {
    		this.value = value;
        }

        public String toString() {
            return this.key + ": " + this.value;
        }
    }
}
