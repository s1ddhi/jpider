package com.spider;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;

public class WebCrawlerTest {
    @Test
    public void FetchesHTMLFromRootNode() {
        WebCrawler crawler = new WebCrawler();
        Connection expectedConnection = Mockito.mock(Connection.class);
        String url = "https://example.com";
        Document expectedDocument = Mockito.mock(Document.class);
        try (MockedStatic<Jsoup> jsoupMockedStatic = Mockito.mockStatic(Jsoup.class)) {
            jsoupMockedStatic.when(() -> Jsoup.connect(Mockito.anyString())).thenReturn(expectedConnection);
            Mockito.when(expectedConnection.get()).thenReturn(expectedDocument);

            Document htmlDocument = crawler.getPageHTML(url);

            assert(htmlDocument).equals(expectedDocument);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}