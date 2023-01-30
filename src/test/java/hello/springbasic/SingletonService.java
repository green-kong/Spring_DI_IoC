package hello.springbasic;

public class SingletonService {
    private static SingletonService INSTANCE = new SingletonService();

    private SingletonService() {
    }

    public static SingletonService getInstance() {
        return INSTANCE;
    }
}
