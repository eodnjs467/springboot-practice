package com.example.testdb.service;

import com.example.testdb.dto.UserInfoDto;
import com.example.testdb.entity.UserInfo;
import com.example.testdb.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository repository;

    @Override
    public String register(UserInfoDto dto) {

        log.info(dto);

        UserInfo entity = dtoToEntity(dto);

        repository.save(entity);

        return "register : " + entity.getUId();
    }

    @Override
    public UserInfoDto get(String uId) {
        Optional<UserInfo> result = repository.findById(uId);

        return result.isPresent() ? EntityToDto(result.get()) : null;
    }

    @Override
    public void delete(String uId) {

        repository.deleteById(uId);
    }

    @Override
    public void modify(UserInfoDto dto) {
        Optional<UserInfo> result = repository.findById(dto.getUId());

        if (result.isPresent()) {

            UserInfo entity = result.get();

            entity.changeName(dto.getName());
            entity.changePassword(dto.getPassword());

            repository.save(entity);
        }

    }
}
