package com.example.gallery;

import java.util.ArrayList;
import java.util.Arrays;

/*
"total":30866,
    "totalHits":500,
    "hits":[
        {
            "id":6742560,
            "pageURL":"https://pixabay.com/photos/lily-flower-yellow-flower-pistil-6742560/",
            "type":"photo",
 */
public class Pixabay {

    Pixabay(){

    }
    int total;
    int totalHits;
    ArrayList<PhotoItem> hits;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public ArrayList<PhotoItem> getHits() {
        return hits;
    }

    public void setHits(ArrayList<PhotoItem> hits) {
        this.hits = hits;
    }
}
