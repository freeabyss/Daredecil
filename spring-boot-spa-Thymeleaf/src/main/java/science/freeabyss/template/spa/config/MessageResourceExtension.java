package science.freeabyss.template.spa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

/**
 * 从文件夹中加载多个国际化文件
 *
 * @author abyss
 * @date 2019-01-23 10:56
 */
@Component("messageSource")
public class MessageResourceExtension extends ResourceBundleMessageSource {
    private static final Logger logger = LoggerFactory.getLogger(MessageResourceExtension.class);
    static String I18N_ATTRIBUTE = "i18n_attribute";
    /**
     * 指定的国际化文件目录
     */
    @Value(value = "${spring.messages.baseFolder:i18n}")
    private String baseFolder;

    /**
     * 父MessageSource指定的国际化文件
     */
    @Value(value = "${spring.messages.basename:message}")
    private String basename;

    @PostConstruct
    public void init() throws IOException {
        logger.info("init MessageResourceExtension...");

        if (!StringUtils.isEmpty(baseFolder)) {
            this.setBasenames(getAllBaseNames(baseFolder));
        }
        //设置父MessageSource
        ResourceBundleMessageSource parent = new ResourceBundleMessageSource();
        parent.setBasename(basename);
        parent.setDefaultEncoding(StandardCharsets.UTF_8.name());
        this.setParentMessageSource(parent);
        this.setDefaultEncoding(StandardCharsets.UTF_8.name());
    }

    /**
     * 解析加载资源文件
     * 根据 I18nPrefix 注解加载指定的资源文件，假如没有注解，则加载全局的资源文件
     *
     * @param code
     * @param locale
     * @return
     */

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        // 获取request中设置的指定国际化文件名
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        final String i18File = (String) attr.getAttribute(I18N_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        if (!StringUtils.isEmpty(i18File)) {
            //获取在basenameSet中匹配的国际化文件名
            String basename = getBasenameSet().stream().filter(name -> name.endsWith(i18File)).findFirst().orElse(null);
            return getStringFromResourceBundle(basename, code, locale);
        }
        for (String name : getBasenameSet()) {
            String value = getStringFromResourceBundle(name, code, locale);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    private String getStringFromResourceBundle(String basename, String code, Locale locale) {
        if (!StringUtils.isEmpty(basename)) {
            //得到指定的国际化文件资源
            ResourceBundle bundle = getResourceBundle(basename, locale);
            if (bundle != null) {
                return getStringOrNull(bundle, code);
            }
        }
        return null;
    }


    /**
     * 获取文件夹下所有的国际化文件名
     *
     * @param folderName 文件名
     * @return
     * @throws IOException
     */
    private String[] getAllBaseNames(String folderName) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(folderName);
        if (null == url) {
            throw new RuntimeException("无法获取资源文件路径");
        }

        Set<String> baseNames = new HashSet<>();
        if (url.getProtocol().equalsIgnoreCase("file")) {
            baseNames = loadByFile(url, folderName);
        } else if (url.getProtocol().equalsIgnoreCase("jar")) {
            baseNames = loadByJar(url, folderName);
        }
        return baseNames.toArray(new String[0]);
    }

    /**
     * 把普通文件名转换成国际化文件名
     *
     * @param filename
     * @return
     */
    private String getI18FileName(String filename) {
        filename = filename.replace(".properties", "");
        for (int i = 0; i < 2; i++) {
            int index = filename.lastIndexOf("_");
            if (index != -1) {
                filename = filename.substring(0, index);
            }
        }
        return filename;
    }

    private Set<String> loadByFile(URL url, String folderName) throws IOException {
        Set<String> baseNames = new HashSet<>();
        // 文件夹形式,用File获取资源路径
        File file = new File(url.getFile());
        if (file.exists() && file.isDirectory()) {
            baseNames = Files.walk(file.toPath())
                    .filter(path -> path.toFile().isFile())
                    .map(Path::toString)
                    .map(path -> path.substring(path.indexOf(folderName)))
                    .map(this::getI18FileName)
                    .collect(Collectors.toSet());
        } else {
            logger.error("指定的baseFile不存在或者不是文件夹");
        }
        return baseNames;
    }

    private Set<String> loadByJar(URL url, String folderName) throws IOException {
        // jar包形式，用JarEntry获取资源路径
        String jarPath = url.getFile().substring(url.getFile().indexOf(":") + 2, url.getFile().indexOf("!"));
        JarFile jarFile = new JarFile(new File(jarPath));
        List<String> baseJars = jarFile.stream()
                .map(ZipEntry::toString)
                .filter(jar -> jar.endsWith(folderName + "/"))
                .collect(Collectors.toList());

        if (baseJars.isEmpty()) {
            logger.info("不存在{}资源文件夹", folderName);
            return new HashSet<>();
        }

        return jarFile.stream().map(ZipEntry::toString)
                .filter(jar -> baseJars.stream().anyMatch(jar::startsWith))
                .filter(jar -> jar.endsWith(".properties"))
                .map(jar -> jar.substring(jar.indexOf(folderName)))
                .map(this::getI18FileName)
                .collect(Collectors.toSet());
    }

}
