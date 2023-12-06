public interface AbstractUserFactory {
    User createUser (String username, String password, StoreInterface store);
}
