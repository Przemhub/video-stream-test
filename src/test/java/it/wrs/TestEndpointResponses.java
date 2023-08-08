package it.wrs;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestEndpointResponses {
    private final String VIDEO_STREAM_SERVER = "https://video-streaming-390616.lm.r.appspot.com";

    @Test
    public void testGetFilmList() {
        String url = VIDEO_STREAM_SERVER + "/film";
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response =
                webResource
                        .accept("application/json")
                        .header("Accept-Encoding", "gzip")
                        .get(ClientResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testGetFilm() {
        String url = VIDEO_STREAM_SERVER + "/film/?videoName=Leonardo%20DiCaprio%20on%20Taxi%20Driver";
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response =
                webResource
                        .accept("video/mp4")
                        .header("Accept-Encoding", "gzip")
                        .get(ClientResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testPostFilm() throws IOException {
        String url = VIDEO_STREAM_SERVER + "/film/?description=test";
        String filePath = "src/main/resources/testVideo.mp4";
        File videoFile = new File(filePath);

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        FileBody uploadFilePart = new FileBody(videoFile);
        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("video", uploadFilePart);
        httpPost.setEntity(reqEntity);

        HttpResponse response = httpclient.execute(httpPost);
        System.out.println(response.getStatusLine());
        assertEquals(200, response.getStatusLine().getStatusCode());

    }

}
