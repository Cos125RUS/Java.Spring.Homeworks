package com.example.demo.repositories;

import com.example.demo.model.SQLRequestsProperties;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Банка с юзерами
 */
@Repository
@AllArgsConstructor
public class UserRepository {

    /**
     * Вот так мы и общаемся с БД
     */
    private final JdbcTemplate jdbc;
    /**
     * Хранилище SQL-запросов
     */
    private final SQLRequestsProperties requests;

    /**
     * Найди мне всех пользователей, глупая машина!
     * @return Слушаюсь и повинуюсь
     */
    public List<User> findAll() {

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(requests.getSelectAll(), userRowMapper);
    }

    /**
     * Сохраняем пользователя в БД
     * @param user тот самый
     * @return Чей юзер? Заберите!
     */
    public User save(User user) {
        jdbc.update(requests.getSave(), user.getFirstName(), user.getLastName());
        return user;
    }

    /**
     * Выселение
     * @param id номер квартиры
     */
    public void deleteById(int id){
        jdbc.update(requests.getDeleteById(), id);
    }

    /**
     * Обновить ремонт в квартире
     * @param user "опытный" специалист с востока
     */
    public void update(User user) {
        jdbc.update(requests.getUpdate(), user.getFirstName(), user.getLastName(), user.getId());
    }
}
