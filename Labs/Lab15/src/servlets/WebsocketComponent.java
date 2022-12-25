import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws")
public class WebsocketComponent extends Endpoint {

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig){
        RemoteEndpoint.Basic remoteEndpointBasic = session.getBasicRemote();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        try{
            while(true){
                Thread.sleep(2000);
                remoteEndpointBasic.sendText(format.format(new Date()));
            }
        }
        catch( IOException e){
            e.printStackTrace();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}