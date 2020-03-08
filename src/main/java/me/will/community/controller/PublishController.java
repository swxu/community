package me.will.community.controller;

import me.will.community.dto.QuestionDTO;
import me.will.community.model.Question;
import me.will.community.model.User;
import me.will.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author will
 */

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}") // 通过 @PathVariable 拿到「id」
    public String edit(@PathVariable(name = "id") Long id,
                       Model model) {
        QuestionDTO question =  questionService.getById(id);

        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());

        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || "".equals(title)) {
            model.addAttribute("error", "Title shouldn't be empty.");
            return "/publish";
        }

        if (description == null || "".equals(description)) {
            model.addAttribute("error", "Description shouldn't be empty.");
            return "/publish";
        }

        if (tag == null || "".equals(tag)) {
            model.addAttribute("error", "Tag shouldn't be empty.");
            return "/publish";
        }

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            model.addAttribute("error", "Please login your account.");
            return "/publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        // 替换中文逗号
        StringUtils.replace(tag, "，", ",");
        question.setTag(tag);
        question.setCreator(user.getId());
        // Id may be null, and null id will be processed in questionService
        question.setId(id);

        questionService.createOrUpdate(question);
        return "redirect:/";
    }

}
