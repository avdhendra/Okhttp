package com.example.retrofit;

import java.util.List;

public class UserResponse {
    private Integer totalCount;
    private Boolean incompleteResult;
    private List<GithubUser> items;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResult() {
        return incompleteResult;
    }

    public void setIncompleteResult(Boolean incompleteResult) {
        this.incompleteResult = incompleteResult;
    }

    public List<GithubUser> getItems() {
        return items;
    }

    public void setItems(List<GithubUser> items) {
        this.items = items;
    }
}
