package ru.noxly.efs.webClient.auth.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.noxly.efs.webClient.auth.model.enums.InviteStatus;
import ru.noxly.efs.webClient.auth.model.enums.Role;
import ru.noxly.efs.webClient.auth.model.enums.UserStatus;

import java.util.Set;
import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class UserDto {

    private final UUID id;

    private final String mail;

    private final String username;

    private final UserInfoDto userInfo;

    private final Set<Role> roles;

    private final UserStatus userStatus;

    private final InviteStatus inviteStatus;

    private final String createTime;
}
