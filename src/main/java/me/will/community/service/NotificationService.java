package me.will.community.service;

import lombok.extern.slf4j.Slf4j;
import me.will.community.dto.NotificationDTO;
import me.will.community.dto.PaginationDTO;
import me.will.community.enums.NotificationStatusEnum;
import me.will.community.enums.NotificationTypeEnum;
import me.will.community.exception.CustomErrorCode;
import me.will.community.exception.CustomException;
import me.will.community.mapper.NotificationMapper;
import me.will.community.model.Notification;
import me.will.community.model.NotificationExample;
import me.will.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author will
 */

@Service
@Slf4j
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);

        Integer totalPage = (totalCount % size == 0) ? (totalCount / size) : ((totalCount / size) + 1);
        // handle exception
        page = (page < 1) ? 1 : ((page > totalPage) ? totalPage : page);
        paginationDTO.setPagination(totalPage, page);

        int offset = size * (page - 1);
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper
                .selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        if (notifications.size() == 0) {
            return paginationDTO;
        }

        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null) {
            log.error("NotificationService.read: get notification error, {}", notification);
            throw new CustomException(CustomErrorCode.NOTIFICATION_NOT_FOUND);
        }

        if (!Objects.equals(notification.getReceiver(), user.getId())) {
            log.error("NotificationService.read: notification user id error, {}", notification);
            throw new CustomException(CustomErrorCode.READ_NOTIFICATION_FAILED);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
