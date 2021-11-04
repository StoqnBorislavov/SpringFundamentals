package softuni.mobile.model.view;

public class StatsView {

    private final int authRequest;
    private final int anonymousRequest;

    public StatsView(int authRequest, int anonymousRequest) {
        this.authRequest = authRequest;
        this.anonymousRequest = anonymousRequest;
    }
    public int getTotalRequest(){
        return getAnonymousRequest() + getAuthRequest();
    }

    public int getAuthRequest() {
        return authRequest;
    }


    public int getAnonymousRequest() {
        return anonymousRequest;
    }
}
