package org.elasticsearch.plugin.example;
import org.elasticsearch.rest.*;
import org.elasticsearch.client.*;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import static org.elasticsearch.rest.RestRequest.Method.GET;
import static org.elasticsearch.rest.RestStatus.OK;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
public class HelloRestHandler implements RestHandler { 
private static final int ports = 9300;
private InetAddress host;
private static Client client = null;
@Inject 
public HelloRestHandler(RestController restController) throws UnknownHostException {
	restController.registerHandler(GET, "/_hello", this); 
	Settings settings = Settings.settingsBuilder()
			.put("client.transport.ignore_cluster_name", true)
			.put("node.client", true)
			.put("client.transport.sniff", true)
            .build();
	InetAddress host= InetAddress.getByName("localhost");
	if(client==null){
		client  = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(host, ports));
	}
}
@Override 
public void handleRequest(final RestRequest request, final RestChannel channel) { 
XContentBuilder builder;
try {
	
	String st =  client.admin().cluster().prepareHealth().get().toString();
	builder = JsonXContent.contentBuilder();
	builder.startObject();
	builder.field("JVM statistic",st);
	builder.field("description", "This is a sample response: "
	        + new Date().toString());
	builder.endObject();
	channel.sendResponse(new BytesRestResponse(OK, builder));
} catch (IOException e) {
	e.printStackTrace();
}
 
}
}
