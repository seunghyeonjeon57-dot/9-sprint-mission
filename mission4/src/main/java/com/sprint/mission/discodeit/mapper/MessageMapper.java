package com.sprint.mission.discodeit.mapper;

import com.sprint.mission.discodeit.dto.data.BinaryContentDto;
import com.sprint.mission.discodeit.dto.data.MessageDto;
import com.sprint.mission.discodeit.dto.data.UserDto;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageMapper {

  private final BinaryContentMapper binaryContentMapper;
  private final UserMapper userMapper;

  public MessageDto toDto(Message message) {
    if (message == null) {
      return null;
    }

    UserDto author = Optional.ofNullable(message.getAuthor())
        .map(userMapper::toDto)
        .orElse(null);
    UUID channelId = Optional.ofNullable(message.getChannel())
        .map(channel -> channel.getId())
        .orElse(null);

    List<BinaryContentDto> attachments = Optional.ofNullable(message.getAttachments())
        .orElse(Collections.emptyList())
        .stream()
        .map(binaryContent -> binaryContentMapper.toDto(binaryContent))
        .toList();

    return new MessageDto(
        message.getId(),
        message.getCreatedAt(),
        message.getUpdatedAt(),
        message.getContent(),
        channelId,
        author,
        attachments

    );

  }

}
