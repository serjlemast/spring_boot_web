package com.serjlemast1.service;

import com.serjlemast1.model.UserInfo;
import com.serjlemast1.repository.CatRepository;
import com.serjlemast1.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final CatRepository catRepository;
    private final DogRepository dogRepository;

    public UserInfo findById(Integer userId) {

        // 6. Create UserInfo object and populate it with collected data
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);

        // 1. Retrieve cat-related data for the given user
        Map<Integer, String> catMap = catRepository.say(userId); // catRepository.say(userInfo)

        // 2. Extract the first cat entry (catId -> catSay), fail fast if none exists
        Map.Entry<Integer, String> catEntry = catMap.entrySet()
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("No cat found for user " + userId)
                );

        // 3. Get cat identifier from the entry
        Integer catId = catEntry.getKey();

        // 4. Get cat response/message for the user
        String catSay = catEntry.getValue();

        // 5. Retrieve dog-related data using userId and catId
        String dogSay = dogRepository.say(userId, catId); // dogRepository.say(userInfo)

        userInfo.setSayCat(catSay);
        userInfo.setSayDog(dogSay);

        // 7. Return fully populated UserInfo
        return userInfo;
    }
}
