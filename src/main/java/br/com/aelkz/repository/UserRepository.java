package br.com.aelkz.repository;

import br.com.aelkz.exception.EntityException;
import br.com.aelkz.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class UserRepository implements GenericRepository<User> {

    private final Logger LOGGER = Logger.getLogger(UserRepository.class.getName());

    private Set<User> storage = new HashSet<>();
    private AtomicInteger index = new AtomicInteger();

    public Set<User> getStorage() {
        return storage;
    }

    public void setStorage(Set<User> storage) {
        this.storage = storage;
    }

    public Integer getNextVal() {
        return index.incrementAndGet();
    }

    public User find(User user) throws EntityException {
        try {
            return getStorage().stream().filter(
                    e->e.getFirstName().equalsIgnoreCase(user.getFirstName()) &&
                            e.getLastName().equalsIgnoreCase(user.getLastName()) &&
                            e.getId().equals(user.getId()) &&
                            e.getGender().equals(user.getGender())
            ).findFirst().get();
        }catch (Exception e) {
            return null;
        }
    }

    public User findBy(Integer id) throws EntityException {
        try {
            return getStorage().stream().filter(e->e.getId().equals(id)).findFirst().get();
        }catch (Exception e) {
            return null;
        }
    }

    public User findBy(String firstName, String lastName) throws EntityException {
        try {
            return getStorage().stream().filter(e->e.getFirstName().equalsIgnoreCase(firstName) && e.getLastName().equalsIgnoreCase(lastName)).findFirst().get();
        }catch (Exception e) {
            return null;
        }
    }

    public void save(User user) throws EntityException {
        synchronized (getStorage()) {
            User _p = findBy(user.getFirstName(), user.getLastName());
            if (_p == null) {
                user.setId(getNextVal());
                getStorage().add(user);
            }else {
                throw new EntityException("this person already exists in database.");
            }
        }
    }

    public void update(User user) throws EntityException {
        User _p = findBy(user.getId());
        if (_p != null) {
            remove(_p);
            getStorage().add(user);
        }else {
            LOGGER.severe("Unable to update user.");
        }
    }

    public void remove(User user) throws EntityException {
        synchronized(getStorage()) {
            Set<User> found = new HashSet<>();
            for(User u : getStorage()){
                if(u.hashCode() == user.hashCode()){
                    LOGGER.info("[HASHCODE]="+user.hashCode()+" equals to [HASHCODE]="+u.hashCode());
                    found.add(u);
                }
            }

            if (!found.isEmpty()) {
                getStorage().removeAll(found);
                setStorage(getStorage().stream().collect(Collectors.toSet()));
            }
        }
    }

}
