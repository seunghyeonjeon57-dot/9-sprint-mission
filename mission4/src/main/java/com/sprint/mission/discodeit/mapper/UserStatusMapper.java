package com.sprint.mission.discodeit.mapper;

import com.sprint.mission.discodeit.dto.data.UserStatusDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserStatusMapper {

  public UserStatusDto toDto(UserStatus userStatus) {
    User user = userStatus.getUser();
    return new UserStatusDto(
        userStatus.getId(),
        user.getId(),
        userStatus.getLastActiveAt()

    );
  }

}
