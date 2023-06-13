package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class.getName());

    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     *
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("save user", e);
        }
        return user;
    }

    /**
     * Обновить в базе пользователя.
     *
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("update user", e);
        }
    }

    /**
     * Удалить пользователя по id.
     *
     * @param userId ID
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            var usr = new User();
            usr.setId(userId);
            session.delete(usr);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("delete user", e);
        }
    }

    /**
     * Список пользователь отсортированных по id.
     *
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        List<User> result = List.of();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery("from User order by id", User.class).list();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("find all users", e);
        }
        return result;
    }

    /**
     * Найти пользователя по ID
     *
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        Optional<User> result = Optional.empty();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            result = Optional.of(session.get(User.class, userId));
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("find by id", e);
        }
        return result;
    }

    /**
     * Список пользователей по login LIKE %key%
     *
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        List<User> result = List.of();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery(
                            "from User as u where u.login like :key", User.class)
                    .setParameter("key", "%" + key + "%").list();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("find by like login", e);
        }
        return result;
    }

    /**
     * Найти пользователя по login.
     *
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Optional<User> result = Optional.empty();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery(
                    "from User as u where u.login = :login", User.class);
            query.setParameter("login", login);
            result = query.uniqueResultOptional();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("find by login", e);
        }
        return result;
    }
}