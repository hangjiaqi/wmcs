package com.laurus.wmcs.context;


import com.laurus.wmcs.model.User;

public class UserContext {

    private static ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static void setCurrentUser(User obj) {
        currentUser.set(obj);
    }

    public static User getCurrentUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }

    public static Long getId() {
        Long id = 0L;
        try {
            if (currentUser.get() != null) {
                id = currentUser.get().getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public static String getName() {
        if (currentUser.get() != null) {
            return currentUser.get().getName();
        } else {
            return "";
        }
    }
}