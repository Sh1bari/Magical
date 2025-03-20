package ru.noxly.efs.webClient.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.noxly.efs.webClient.auth.model.dto.UserDto;
import ru.noxly.efs.webClient.auth.model.requests.ValidateUserReq;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthClient {

    private final AuthWebClient authWebClient;

    public UserDto validateUser(String token) {
        val uri = "/api/internal/**";
        var response = authWebClient.post(uri,
                ValidateUserReq.init()
                        .setToken(token)
                        .build()
                , UserDto.class);

        return response;
    }
}
