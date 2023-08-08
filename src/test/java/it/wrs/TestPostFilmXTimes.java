package it.wrs;

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

public class TestPostFilmXTimes {

    //When function is tested change first arg of testPostFilm to VIDEO_STREAM_FUNCTION
    private final String VIDEO_STREAM_ENDPOINT = "https://europe-central2-video-streaming-390616.cloudfunctions.net/film/?description=test";
    private final String VIDEO_STREAM_FUNCTION = "https://europe-central2-video-streaming-390616.cloudfunctions.net/postFilmFunction/?description=test";

    @Test
    public void testPostFilmFor2Mins() throws IOException, InterruptedException {
        int sleepTimeSeconds = 5;
        for (int i = 0; i < sleepTimeSeconds * 24; i++) {
            testPostFilm(VIDEO_STREAM_ENDPOINT, sleepTimeSeconds);
        }
    }

    private void testPostFilm(String url, int sleepTimeSeconds) throws IOException, InterruptedException {
        String filePath = "src/main/resources/testVideo.mp4";
        File videoFile = new File(filePath);

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        FileBody uploadFilePart = new FileBody(videoFile);
        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("video", uploadFilePart);
        httpPost.setEntity(reqEntity);

        HttpResponse response = httpclient.execute(httpPost);
        assertEquals(201, response.getStatusLine().getStatusCode());
        Thread.sleep(sleepTimeSeconds * 1000L);

    }

}
