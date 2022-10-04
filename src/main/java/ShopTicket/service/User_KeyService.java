package ShopTicket.service;

import ShopTicket.controller.dto.User_KeyDto;
import ShopTicket.model.User_Key;

import java.util.Optional;

public interface User_KeyService {

    User_Key save(User_KeyDto user_keydto);
    boolean exists(String key_id);
    User_Key loadUserByKey(String key_id);
    User_Key loadUserById(Long id);
}

