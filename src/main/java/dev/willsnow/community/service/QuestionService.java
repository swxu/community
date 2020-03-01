package dev.willsnow.community.service;

import dev.willsnow.community.dto.PaginationDTO;
import dev.willsnow.community.dto.QuestionDTO;
import dev.willsnow.community.mapper.QuestionMapper;
import dev.willsnow.community.mapper.UserMapper;
import dev.willsnow.community.model.Question;
import dev.willsnow.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author will
 */

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer totalCount = questionMapper.count();
        Integer totalPage = (totalCount % size == 0) ? (totalCount / size) : ((totalCount / size) + 1);
        // handle exception
        page = (page < 1) ? 1 : ((page > totalPage) ? totalPage : page);

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(size, offset);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }
}

