package me.will.community.cache;

import me.will.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author will
 */

public class TagCache {

    private final static List<String> PROGRAM_LANGUAGES = new ArrayList<>(Arrays.asList("javascript", "php",
            "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c",
            "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua",
            "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));

    private final static List<String> FRAMEWORKS = new ArrayList<>(Arrays.asList("laravel", "spring", "express",
            "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));

    private final static List<String> SERVERS = new ArrayList<>(Arrays.asList("linux", "nginx", "docker",
            "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"));

    private final static List<String> DATABASES = new ArrayList<>(Arrays.asList("mysql", "redis", "mongodb",
            "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));

    private final static List<String> DEV_TOOLS = new ArrayList<>(Arrays.asList("git", "github",
            "visual-studio-code", "vim", "sublime-text", "xcode intellij-idea", "eclipse",
            "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));

    public static List<TagDTO> get() {
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO programLanguage = new TagDTO();
        programLanguage.setCategoryName("编程语言");
        programLanguage.setTags(PROGRAM_LANGUAGES);
        tagDTOS.add(programLanguage);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("框架");
        framework.setTags(FRAMEWORKS);
        tagDTOS.add(framework);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(SERVERS);
        tagDTOS.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(DATABASES);
        tagDTOS.add(db);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(DEV_TOOLS);
        tagDTOS.add(tool);

        return tagDTOS;
    }

    public static String filterValid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream())
                .collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> StringUtils.isBlank(t) || !tagList.contains(t))
                .collect(Collectors.joining(","));
        return invalid;
    }

}
