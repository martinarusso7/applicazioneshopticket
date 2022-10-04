package ShopTicket.service;
import ShopTicket.controller.dto.User_KeyDto;
import ShopTicket.model.User_Key;
import ShopTicket.repository.User_KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class User_KeyServiceImpl implements User_KeyService{
    @Autowired
    User_KeyRepository user_keyRepository;

    @Override
    public User_Key save(User_KeyDto user_keydto) {
        User_Key user_key = new User_Key(user_keydto.getKey_id(), user_keydto.getSaldo());
        return user_keyRepository.save(user_key);
    }

    @Override
    public boolean exists(String key_id) {
        User_Key user_key = user_keyRepository.findbykey(key_id);
        if(user_key != null)
            return true;
        else
            return false;
    }

    @Override
    public User_Key loadUserByKey(String key_id) {
        return user_keyRepository.findbykey(key_id);
    }

    @Override
    public User_Key loadUserById(Long id) {
        return user_keyRepository.findbyId(id);
    }
}
