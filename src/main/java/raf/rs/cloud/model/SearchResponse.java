package raf.rs.cloud.model;

import java.util.List;

public class SearchResponse {

    private List<Machine> data ;

    public SearchResponse(List<Machine> data) {
        this.data = data;
    }

    public List<Machine> getData() {
        return data;
    }

    public void setData(List<Machine> data) {
        this.data = data;
    }
}
