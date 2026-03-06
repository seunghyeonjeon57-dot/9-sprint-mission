package com.sprint.mission.discodeit.mapper;


import com.sprint.mission.discodeit.dto.data.ChannelDto;
import com.sprint.mission.discodeit.dto.data.UserDto;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChannelMapper {

  private final MessageRepository messageRepository;
  private final ReadStatusRepository repository;
  private final UserMapper userMapper;

  public ChannelDto toDto(Channel channel) {
    List<ReadStatus> readStatuses = repository.findAllByChannelId(channel.getId());
    List<UserDto> participantIds = readStatuses.stream()
        .map(status -> userMapper.toDto(status.getUser()))
        .toList();

    Instant lastMessageAt = messageRepository.findFirstByChannelIdOrderByCreatedAtDesc(
            channel.getId())
        .map(message -> message.getCreatedAt())
        .orElse(null);

    return new ChannelDto(
        channel.getId(),
        channel.getType(),
        channel.getName(),
        channel.getDescription(),
        participantIds,
        lastMessageAt

    );
  }

}
