package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Так себе сервис в вашем захолустье
 */
@Service
@AllArgsConstructor
public class UserService {
    /**
     * Штат сотрудников отеля
     */
    private final UserRepository userRepository;

    /**
     * Игра в прятки
     * @return сомнительное удовольствие
     */
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * Не забываем сохраняться
     * @param user user
     * @return все ваши вклады заморожены
     */
    public User saveUser(User user){
        return userRepository.save(user);
    }

    /**
     * Пшёл вон
     * @param id и id свой забери
     */
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    /**
     * Обновки любят все
     * @param user обновлённый помолодевший пользователь
     */
    public void updateUser(User user) {
        userRepository.update(user);
    }
}
