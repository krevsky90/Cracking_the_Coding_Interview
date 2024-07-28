package live_coding.real_systems.rateLimiter;

public class Request {
    private final int userId;
    private final int requestId;
    private final String content;

    public Request(int userId, int requestId, String content) {
        this.userId = userId;
        this.requestId = requestId;
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getContent() {
        return content;
    }
}
