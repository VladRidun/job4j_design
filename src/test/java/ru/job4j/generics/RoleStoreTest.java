package ru.job4j.generics;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenameIsAdmin() {
        UserStore store = new UserStore();
        store.add(new User("1", "Admin"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Admin");
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Admin"));
        User result = store.findById("2");
        assertThat((result)).isEqualTo(null);
    }

    @Test
    public void whenAddDuplicateAndFindRoleNameIsUser() {
        UserStore store = new UserStore();
        store.add(new User("1", "User"));
        store.add(new User("1", "SuperAdmin"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("User");
    }

    @Test
    public void whenReplaceThenRoleNameIsUser() {
        UserStore store = new UserStore();
        store.add(new User("1", "Admin"));
        store.replace("1", new User("1", "User"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("User");
    }

    @Test
    public void whenNoReplaceUserThenNoChangeRoleName() {
        UserStore store = new UserStore();
        store.add(new User("1", "User"));
        store.replace("2", new User("2", "Admin"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("User");
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Admin"));
        store.delete("1");
        User result = store.findById("1");
        assertThat((result)).isEqualTo(null);
    }

    @Test
    public void whenNoDeleteRoleThenRoleNameIsSuperUser() {
        UserStore store = new UserStore();
        store.add(new User("1", "SuperUser"));
        store.delete("2");
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("SuperUser");
    }
}