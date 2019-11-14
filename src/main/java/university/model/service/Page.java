package university.model.service;

public class Page {
    private final Integer currentPage;
    private final Integer recordsPerPage;

    public Page(Integer currentPage, Integer recordsPerPage) {
        this.currentPage = currentPage;
        this.recordsPerPage = recordsPerPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getRecordsPerPage() {
        return recordsPerPage;
    }
}


