package org.es4j.messaging.api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


// Represents a single element in a stream of events.
//[DataContract, Serializable]
public class EventMessage implements Serializable {

    // Initializes a new instance of the EventMessage class.
    //public EventMessage() {
    //    this.headers = new HashMap<String, Object>();
    //}
    public <T> EventMessage(T payload) {
        this(payload, new HashMap<String,Object>());
    }
    public <T> EventMessage(T payload, Map<String, Object> metadata) {
        if(payload == null) {
            throw new IllegalArgumentException();
        }
        this.payloadType = (Class<T>) payload.getClass();
        this.headers     = metadata;
        this.body        = payload;
    }


    // Gets the metadata which provides additional, unstructured information about this message.
    //[DataMember]
    private Map<String, Object> headers; // { get; private set; }

    // Gets or sets the actual event message body.
    //[DataMember]
    public Object body; // { get; set; }
    
    // payloadType is stored separately, because of Object.getClass() performance
    private final Class payloadType;

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    protected void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }
}